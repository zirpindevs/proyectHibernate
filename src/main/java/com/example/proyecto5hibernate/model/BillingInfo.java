package com.example.proyecto5hibernate.model;

import javax.persistence.*;

@Entity
@Table(name="billing_info")
public class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String province;

    @Column(name = "postal_code", length = 5)
    private String postalCode;

    private String country;

    public BillingInfo() {
    }

    public String getStreet() {
        return street;
    }

    public BillingInfo setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public BillingInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingInfo setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "BillingInfo{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
