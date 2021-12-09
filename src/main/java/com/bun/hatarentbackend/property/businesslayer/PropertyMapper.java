package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper
{
    @Mapping(target = "id", ignore = true)
    PropertyEntity propertyDTOToPropertyEntity(PropertyDTO propertyDTO);
    PropertyDTO propertyEntityToPropertyDTO(PropertyEntity propertyEntity);

    List<PropertyEntity> propertyDTOListToPropertyEntityList(List<PropertyDTO> propertyDTOList);
    List<PropertyDTO> propertyEntityListToPropertyDTOList(List<PropertyEntity> propertyEntityList);
}
