package com.fdc.mtrg.network.token.util;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import static com.mastercard.developer.utils.EncodingUtils.base64Decode;

/**
 * Utility class for loading certificates and keys.
 */
public final class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String PKCS_1_PEM_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PKCS_1_PEM_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String PKCS_8_PEM_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PKCS_8_PEM_FOOTER = "-----END PRIVATE KEY-----";


    public static FieldLevelEncryptionConfig buildConfig(Certificate encryptionCertificate, PrivateKey decryptionKey) throws EncryptionException {
        FieldLevelEncryptionConfig encryptionConfig = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
                .withEncryptionPath("$.fundingAccountInfo.encryptedPayload.encryptedData", "$.fundingAccountInfo.encryptedPayload")
                .withEncryptionPath("$.encryptedPayload.encryptedData", "$.encryptedPayload")
                .withDecryptionPath("$.tokenDetail", "$.tokenDetail.encryptedData")
                .withDecryptionPath("$.encryptedPayload", "$.encryptedPayload.encryptedData")
                .withEncryptionCertificate(encryptionCertificate)
                .withDecryptionKey(decryptionKey)
                .withOaepPaddingDigestAlgorithm("SHA-512")
                .withEncryptedValueFieldName("encryptedData")
                .withEncryptedKeyFieldName("encryptedKey")
                .withIvFieldName("iv")
                .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
                .withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
                .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
                .build();

        return encryptionConfig;
    }

    /**
     * Load a RSA signing key out of a PKCS#12 container.
     */
    public static PrivateKey loadSigningKey(Resource resource,
                                            String signingKeyAlias,
                                            String signingKeyPassword)
            throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, IOException {
        KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12", "SunJSSE");
        pkcs12KeyStore.load(resource.getInputStream(), signingKeyPassword.toCharArray());
        return (PrivateKey) pkcs12KeyStore.getKey(signingKeyAlias, signingKeyPassword.toCharArray());
    }

    /**
     * Populate a X509 encryption certificate object with the certificate data at the given resource.
     */
    public static Certificate loadEncryptionCertificate(Resource resource) throws KeyStoreException, CertificateException, IOException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        return factory.generateCertificate(resource.getInputStream());
    }

    /**
     * Load a RSA decryption key from resource.
     */
    public static PrivateKey loadDecryptionKey(Resource resource) throws GeneralSecurityException, IOException {
        byte[] keyDataBytes = toByteArray(resource.getInputStream());
        String keyDataString = new String(keyDataBytes, StandardCharsets.UTF_8);

        if (keyDataString.contains(PKCS_1_PEM_HEADER)) {
            keyDataString = keyDataString.replace(PKCS_1_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_1_PEM_FOOTER, "");
            return readPkcs1PrivateKey(base64Decode(keyDataString));
        }

        if (keyDataString.contains(PKCS_8_PEM_HEADER)) {
            keyDataString = keyDataString.replace(PKCS_8_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_8_PEM_FOOTER, "");
            return readPkcs8PrivateKey(base64Decode(keyDataString));
        }
        return readPkcs8PrivateKey(keyDataBytes);
    }

    /**
     * Create a PrivateKey instance from raw PKCS#8 bytes.
     */
    private static PrivateKey readPkcs8PrivateKey(byte[] pkcs8Bytes) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8Bytes);
        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException("Unexpected key format!", e);
        }
    }

    /**
     * Create a PrivateKey instance from raw PKCS#1 bytes.
     */
    private static PrivateKey readPkcs1PrivateKey(byte[] pkcs1Bytes) throws GeneralSecurityException {
        // We can't use Java internal APIs to parse ASN.1 structures, so we build a PKCS#8 key Java can understand
        int pkcs1Length = pkcs1Bytes.length;
        int totalLength = pkcs1Length + 22;
        byte[] pkcs8Header = new byte[]{
                0x30, (byte) 0x82, (byte) ((totalLength >> 8) & 0xff), (byte) (totalLength & 0xff), // Sequence + total length
                0x2, 0x1, 0x0, // Integer (0)
                0x30, 0xD, 0x6, 0x9, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0xD, 0x1, 0x1, 0x1, 0x5, 0x0, // Sequence: 1.2.840.113549.1.1.1, NULL
                0x4, (byte) 0x82, (byte) ((pkcs1Length >> 8) & 0xff), (byte) (pkcs1Length & 0xff) // Octet string + length
        };
        byte[] pkcs8bytes = join(pkcs8Header, pkcs1Bytes);
        return readPkcs8PrivateKey(pkcs8bytes);
    }

    private static byte[] join(byte[] byteArray1, byte[] byteArray2) {
        byte[] bytes = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, bytes, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, bytes, byteArray1.length, byteArray2.length);
        return bytes;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }
}
