package com.bun.hatarentbackend.address.datalayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(name = "street")
    private String street;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;


}
