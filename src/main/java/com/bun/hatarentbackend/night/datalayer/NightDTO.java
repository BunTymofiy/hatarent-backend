package com.bun.hatarentbackend.night.datalayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NightDTO {
    private Property property;
    private String date;
    private Float price;
    private String state;
}
