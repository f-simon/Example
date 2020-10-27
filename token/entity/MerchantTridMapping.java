package com.fdc.mtrg.network.token.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MTRG_MERCHANT_TRID_MAPPING")
public class MerchantTridMapping {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "MERCHANT_ID", nullable = false)
    private String merchantId;

    @Column(name = "TOKEN_REQUESTOR_ID", nullable = false)
    private String tokenRequestorId;

    @Column(name = "TSP", nullable = false)
    private String tsp;

    @Column(name = "DATE_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public String getTsp() {
        return tsp;
    }

    public void setTsp(String tsp) {
        this.tsp = tsp;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
