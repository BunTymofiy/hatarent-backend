package com.bun.hatarentbackend.address.datalayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer addressId;
    private String street;
    private String apartment;
    private String city;
    private String country;
    private String zip;


}
