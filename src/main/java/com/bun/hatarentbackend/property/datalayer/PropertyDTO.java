package com.bun.hatarentbackend.property.datalayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO
{
    private  UUID hostUserUuid;

    private  UUID addressUuid;

    private Integer guestLimit;

    private String description;

    private String title;

    private String contact_person;

    private String email;
}
