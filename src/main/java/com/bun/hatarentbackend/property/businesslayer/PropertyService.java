package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyService
{
    List<PropertyEntity> findAll();
    Optional<PropertyEntity> findByUuid(UUID propertyId);
    PropertyEntity update(PropertyEntity propertyEntity);
    PropertyEntity create(PropertyEntity propertyEntity);
    void delete(UUID propertyId);


}
