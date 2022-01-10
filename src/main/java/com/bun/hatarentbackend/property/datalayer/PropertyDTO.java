package com.bun.hatarentbackend.property.datalayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO
{
    private  UUID hostUserUuid;

    private AddressEntity address;

    private Integer guestLimit;

    private String description;

    private String title;

    private String contact_person;

    private String email;

    private List<String> images;
}
