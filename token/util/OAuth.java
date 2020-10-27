package com.fdc.mtrg.network.token.util;

import com.mastercard.developer.oauth.Util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OAuth {

    public static final String EMPTY_STRING = "";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final Logger LOG = Logger.getLogger(com.mastercard.developer.oauth.OAuth.class.getName());
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int NONCE_LENGTH = 16;
    private static final String ALPHA_NUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private OAuth() {
    }

    public static String getAuthorizationHeader(URI uri, String method, String payload, Charset charset, String consumerKey, PrivateKey signingKey) {
        TreeMap<String, List<String>> queryParams = extractQueryParams(uri, charset);
        HashMap<String, String> oauthParams = new HashMap();
        oauthParams.put("oauth_consumer_key", consumerKey);
        oauthParams.put("oauth_nonce", getNonce());
        oauthParams.put("oauth_signature_method", "RSA-" + "SHA-256".replace("-", ""));
        oauthParams.put("oauth_timestamp", getTimestamp());
        oauthParams.put("oauth_version", "1.0");
        oauthParams.put("oauth_body_hash", getBodyHash(payload, charset, "SHA-256"));
        String paramString = toOauthParamString(queryParams, oauthParams);
        String baseUri = getBaseUriString(uri);
        String sbs = getSignatureBaseString(method, baseUri, paramString, charset);
        String signature = signSignatureBaseString(sbs, signingKey, charset);
        oauthParams.put("oauth_signature", Util.percentEncode(signature, charset));
        return getAuthorizationString(oauthParams);
    }

    static String getSignatureBaseString(String httpMethod, String baseUri, String paramString, Charset charset) {
        String sbs = httpMethod.toUpperCase() + "&" + Util.percentEncode(baseUri, charset) + "&" + Util.percentEncode(paramString, charset);
        LOG.log(Level.FINE, "Generated SBS: {0}", sbs);
        return sbs;
    }

    static TreeMap<String, List<String>> extractQueryParams(URI uri, Charset charset) {
        String decodedQueryString = uri.getQuery();
        String rawQueryString = uri.getRawQuery();
        if (decodedQueryString != null && !decodedQueryString.isEmpty() && rawQueryString != null && !rawQueryString.isEmpty()) {
            boolean mustEncode = !decodedQueryString.equals(rawQueryString);
            TreeMap<String, List<String>> queryPairs = new TreeMap();
            String[] pairs = decodedQueryString.split("&");
            String[] var7 = pairs;
            int var8 = pairs.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String pair = var7[var9];
                int idx = pair.indexOf(61);
                String key = idx > 0 ? pair.substring(0, idx) : pair;
                if (!queryPairs.containsKey(key)) {
                    key = mustEncode ? Util.percentEncode(key, charset) : key;
                    queryPairs.put(key, new LinkedList());
                }

                String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : "";
                value = mustEncode ? Util.percentEncode(value, charset) : value;
                ((List)queryPairs.get(key)).add(value);
            }

            return queryPairs;
        } else {
            return new TreeMap();
        }
    }

    static String toOauthParamString(SortedMap<String, List<String>> queryParamsMap, Map<String, String> oauthParamsMap) {
        TreeMap<String, List<String>> consolidatedParams = new TreeMap(queryParamsMap);
        Iterator var3 = oauthParamsMap.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var3.next();
            if (consolidatedParams.containsKey(entry.getKey())) {
                ((List)consolidatedParams.get(entry.getKey())).add(entry.getValue());
            } else {
                consolidatedParams.put(entry.getKey(), Arrays.asList((String)entry.getValue()));
            }
        }

        StringBuilder oauthParams = new StringBuilder();
        Iterator var10 = consolidatedParams.entrySet().iterator();

        while(var10.hasNext()) {
            Map.Entry<String, List<String>> entry = (Map.Entry)var10.next();
            String key = (String)entry.getKey();
            if (((List)entry.getValue()).size() > 1) {
                Collections.sort((List)entry.getValue());
            }

            Iterator var7 = ((List)entry.getValue()).iterator();

            while(var7.hasNext()) {
                String value = (String)var7.next();
                oauthParams.append(key).append("=").append(value).append("&");
            }
        }

        int stringLength = oauthParams.length() - 1;
        if (oauthParams.charAt(stringLength) == '&') {
            oauthParams.deleteCharAt(stringLength);
        }

        return oauthParams.toString();
    }

    static String getNonce() {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(16);

        for(int i = 0; i < 16; ++i) {
            sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(rnd.nextInt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length())));
        }

        return sb.toString();
    }

    private static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    static String getBaseUriString(URI uri) {
        String scheme = uri.getScheme().toLowerCase();
        String authority = uri.getAuthority().toLowerCase();
        if ("http".equals(scheme) && uri.getPort() == 80 || "https".equals(scheme) && uri.getPort() == 443) {
            int index = authority.lastIndexOf(58);
            if (index >= 0) {
                authority = authority.substring(0, index);
            }
        }

        String path = uri.getRawPath();
        if (path == null || path.length() <= 0) {
            path = "/";
        }

        try {
            return (new URI(scheme, authority, path, (String)null, (String)null)).toString();
        } catch (URISyntaxException var5) {
            throw new IllegalArgumentException("Unable to normalize provided URL due to: " + var5.getMessage());
        }
    }

    static String getBodyHash(String payload, Charset charset, String hashAlg) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(hashAlg);
        } catch (NoSuchAlgorithmException var6) {
            throw new IllegalStateException("Unable to obtain " + hashAlg + " message digest", var6);
        }

        digest.reset();
        byte[] byteArray = null == payload ? "".getBytes() : payload.getBytes(charset);
        byte[] hash = digest.digest(byteArray);
        return Util.b64Encode(hash);
    }

    static String signSignatureBaseString(String sbs, PrivateKey signingKey, Charset charset) {
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(signingKey);
            byte[] sbsBytes = sbs.getBytes(charset);
            signer.update(sbsBytes);
            byte[] signatureBytes = signer.sign();
            return Util.b64Encode(signatureBytes);
        } catch (GeneralSecurityException var6) {
            throw new IllegalStateException("Unable to RSA-SHA256 sign the given string with the provided key", var6);
        }
    }

    private static String getAuthorizationString(Map<String, String> oauthParams) {
        StringBuilder header = new StringBuilder();
        Iterator var2 = oauthParams.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, String> param = (Map.Entry)var2.next();
            header.append((String)param.getKey()).append("=\"").append((String)param.getValue()).append("\",");
        }

        header.deleteCharAt(header.length() - 1);
        return header.toString();
    }
}
