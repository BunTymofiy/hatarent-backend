package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.Property;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyService
{
    List<Property> findAll();
    Optional<Property> findByUuid(UUID propertyId);
    Property update(Property property);
    Property create(Property property);
    void delete(UUID propertyId);


}
