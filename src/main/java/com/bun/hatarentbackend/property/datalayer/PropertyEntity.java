package com.bun.hatarentbackend.property.datalayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "property")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity
{
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(name = "host_user_uuid")
    private UUID hostUserUuid;

    @Column(name = "address_uuid")
    private UUID addressUuid;

    @Column(name = "guest_limit")
    private Integer guestLimit;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "contact_person")
    private String contact_person;

    @Column(name = "email")
    private String email;

}
