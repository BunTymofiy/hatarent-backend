package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper
{
    @Mapping(target = "uuid", ignore = true)
    Property propertyDTOToPropertyEntity(PropertyDTO propertyDTO);
    PropertyDTO propertyEntityToPropertyDTO(Property property);

    List<Property> propertyDTOListToPropertyEntityList(List<PropertyDTO> propertyDTOList);
    List<PropertyDTO> propertyEntityListToPropertyDTOList(List<Property> propertyList);
}
