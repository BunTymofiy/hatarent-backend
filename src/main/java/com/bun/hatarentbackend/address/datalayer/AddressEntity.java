package com.bun.hatarentbackend.address.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}
