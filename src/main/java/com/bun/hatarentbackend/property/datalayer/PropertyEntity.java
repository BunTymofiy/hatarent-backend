package com.bun.hatarentbackend.property.datalayer;

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
    private int GuestLimit;

    @Column(name = "description")
    private String Description;
}
