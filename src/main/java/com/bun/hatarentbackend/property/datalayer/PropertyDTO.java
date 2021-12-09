package com.bun.hatarentbackend.property.datalayer;

import javax.persistence.Column;
import java.util.UUID;

public class PropertyDTO
{
    private Integer PropertyId;

    private  Integer HostUserId;

    private  Integer AddressId;

    private Integer GuestLimit;

    private String Description;
    private String Title;


    public PropertyDTO(Integer propertyId, Integer hostUserId, Integer addressId, Integer guestLimit, String description, String title) {
        PropertyId = propertyId;
        HostUserId = hostUserId;
        AddressId = addressId;
        GuestLimit = guestLimit;
        Description = description;
        Title = title;
    }

    public PropertyDTO() {
    }

    public Integer getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(Integer propertyId) {
        PropertyId = propertyId;
    }

    public Integer getHostUserId() {
        return HostUserId;
    }

    public void setHostUserId(Integer hostUserId) {
        HostUserId = hostUserId;
    }

    public Integer getAddressId() {
        return AddressId;
    }

    public void setAddressId(Integer addressId) {
        AddressId = addressId;
    }

    public Integer getGuestLimit() {
        return GuestLimit;
    }

    public void setGuestLimit(Integer guestLimit) {
        GuestLimit = guestLimit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
