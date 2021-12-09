package com.bun.hatarentbackend.property.datalayer;

import javax.persistence.Column;
import java.util.UUID;

public class PropertyDTO
{
    private UUID PropertyId;

    private  UUID HostUserId;

    private  UUID AddressId;

    private Integer GuestLimit;

    private String Description;
}
