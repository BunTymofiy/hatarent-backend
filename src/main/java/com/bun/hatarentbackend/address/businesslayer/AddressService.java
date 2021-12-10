package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;

import java.util.List;

public interface AddressService {
    List<AddressEntity> getAllAddresses();
    AddressEntity getAddressByAddressId(Integer addressId);
    AddressEntity updateAddress(AddressEntity addressEntity, AddressEntity UpdateAddressEntity);
    AddressEntity createAddress(AddressEntity addressEntity);
    void deleteAddressByAddressId(Integer addressId);
}
