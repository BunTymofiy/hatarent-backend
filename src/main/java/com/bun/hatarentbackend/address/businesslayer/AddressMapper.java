package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressDTO;
import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    AddressEntity addressDTOToAddressEntity(AddressDTO addressDTO);
    AddressDTO addressEntityToAddressDTO(AddressEntity addressEntity);

    List<AddressEntity> addressDTOListToAddressEntityList(List<AddressDTO> addressDTOList);
    List<AddressDTO> addressEntityListToAddressDTOList(List<AddressEntity> addressEntityList);
}
