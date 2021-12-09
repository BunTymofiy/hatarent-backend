package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;

import java.util.List;
import java.util.UUID;

public interface PropertyService
{
    public List<PropertyEntity> getAllProperties();
    public PropertyEntity getPropertyByPropertyId(UUID propertyId);
    public PropertyEntity updateProperty(PropertyEntity propertyEntity, PropertyEntity UpdatePropertyEntity);
    public PropertyEntity createProperty(PropertyEntity propertyEntity);
    public void deletePropertyByPropertyId(UUID propertyId);

    public List<PropertyDTO> getAllPropertiesDTO();
    public PropertyDTO getPropertyDTOByPropertyId(UUID propertyId);
    public PropertyDTO updatePropertyDTO(UUID propertyId, PropertyEntity UpdatePropertyEntity);
    public PropertyDTO createPropertyDTO(PropertyEntity propertyEntity);
    public void deletePropertyDTOByPropertyId(UUID propertyId);
}
