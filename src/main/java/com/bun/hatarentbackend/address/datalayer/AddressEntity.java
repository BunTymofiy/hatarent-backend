package com.bun.hatarentbackend.address.datalayer;

import javax.persistence.*;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address_id", unique = true, updatable = false)
    private Integer addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    public AddressEntity(Integer id, Integer addressId, String street, String apartment, String city, String country, String zip) {
        this.id = id;
        this.addressId = addressId;
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public AddressEntity(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
