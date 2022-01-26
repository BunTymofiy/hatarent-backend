package com.bun.hatarentbackend.night.datalayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="night")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Night {
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID nightId = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull(message = "Property is required")
    @JoinColumn(name = "uuid")
    private Property propertyId; // The whole property obj

    @Column(name = "date")
    private String date;

    @Column(name = "price")
    private Float price;

    @Column(name = "state")
    private String state;
}
