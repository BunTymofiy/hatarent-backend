package com.bun.hatarentbackend.property.datalayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "property")

public class PropertyEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "property_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID PropertyId;

    @Column(name = "host_user_id")
    private  UUID HostUserId;

    @Column(name = "address_id")
    private  UUID AddressId;

    @Column(name = "guest_limit")
    private Integer GuestLimit;

    @Column(name = "description")
    private String Description;

    public PropertyEntity(Integer id, UUID propertyId, UUID hostUserId, UUID addressId, int guestLimit, String description) {
        this.id = id;
        PropertyId = propertyId;
        HostUserId = hostUserId;
        AddressId = addressId;
        GuestLimit = guestLimit;
        Description = description;
    }

    public PropertyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(UUID propertyId) {
        PropertyId = propertyId;
    }

    public UUID getHostUserId() {
        return HostUserId;
    }

    public void setHostUserId(UUID hostUserId) {
        HostUserId = hostUserId;
    }

    public UUID getAddressId() {
        return AddressId;
    }

    public void setAddressId(UUID addressId) {
        AddressId = addressId;
    }

    public Integer getGuestLimit() {
        return GuestLimit;
    }

    public void setGuestLimit(int guestLimit) {
        GuestLimit = guestLimit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
