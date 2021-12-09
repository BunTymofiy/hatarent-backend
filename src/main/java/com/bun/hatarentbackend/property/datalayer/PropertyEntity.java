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

    @Column(name = "property_id", unique = true, updatable = false)
    private Integer propertyId;

    @Column(name = "host_user_id")
    private  Integer hostUserId;

    @Column(name = "address_id")
    private  Integer addressId;

    @Column(name = "guest_limit")
    private Integer guestLimit;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    public PropertyEntity(Integer id, Integer propertyId, Integer hostUserId, Integer addressId, Integer guestLimit, String description, String title) {
        this.id = id;
        this.propertyId = propertyId;
        this.hostUserId = hostUserId;
        this.addressId = addressId;
        this.guestLimit = guestLimit;
        this.description = description;
        this.title = title;
    }

    public PropertyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getHostUserId() {
        return hostUserId;
    }

    public void setHostUserId(Integer hostUserId) {
        this.hostUserId = hostUserId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getGuestLimit() {
        return guestLimit;
    }

    public void setGuestLimit(Integer guestLimit) {
        this.guestLimit = guestLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
