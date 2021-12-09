package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;

import java.util.List;
import java.util.UUID;

public interface PropertyService
{
    List<PropertyEntity> getAllProperties();
    PropertyEntity getPropertyByPropertyId(Integer propertyId);
    PropertyEntity updateProperty(PropertyEntity propertyEntity, PropertyEntity UpdatePropertyEntity);
    PropertyEntity createProperty(PropertyEntity propertyEntity);
    void deletePropertyByPropertyId(Integer propertyId);

//    List<PropertyDTO> getAllPropertiesDTO();
//    PropertyDTO getPropertyDTOByPropertyId(UUID propertyId);
//    PropertyDTO updatePropertyDTO(UUID propertyId, PropertyDTO UpdatePropertyDTO);
//    PropertyDTO createPropertyDTO(PropertyDTO propertyDTO);
//    void deletePropertyDTOByPropertyId(UUID propertyId);
}
