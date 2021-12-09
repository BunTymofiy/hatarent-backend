package com.bun.hatarentbackend.address.datalayer;

public class AddressDTO {
    private Integer AddressId;
    private String Street;
    private String Apartment;
    private String City;
    private String Country;
    private String Zip;

    public AddressDTO(Integer addressId, String street, String apartment, String city, String country, String zip) {
        AddressId = addressId;
        Street = street;
        Apartment = apartment;
        City = city;
        Country = country;
        Zip = zip;
    }
    public AddressDTO(){

    }

    public Integer getAddressId() {
        return AddressId;
    }

    public void setAddressId(Integer addressId) {
        AddressId = addressId;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getApartment() {
        return Apartment;
    }

    public void setApartment(String apartment) {
        Apartment = apartment;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }
}
