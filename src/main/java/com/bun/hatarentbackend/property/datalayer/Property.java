package com.bun.hatarentbackend.property.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@Table(name = "property")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Property
{
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(name = "host_user_uuid")
    private UUID hostUserUuid;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull(message = "Address is required")
    @JoinColumn(name = "address_uuid")
    private Address address;

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

    @ElementCollection
    @Column(name = "images")
    private List<String> images;

}
