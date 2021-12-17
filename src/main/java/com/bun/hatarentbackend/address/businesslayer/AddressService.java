package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService {
    List<AddressEntity> findAll();
    Optional<AddressEntity> findByUuid(UUID addressId);
    AddressEntity update(AddressEntity addressEntity);
    AddressEntity create(AddressEntity addressEntity);
    void delete(UUID addressId);
}
