package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{

    //private final AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAllAddresses() {
        return null;
    }

    @Override
    public AddressEntity getAddressByAddressId(Integer addressId) {
        return null;
    }

    @Override
    public AddressEntity updateAddress(AddressEntity addressEntity) {
        return null;
    }

    @Override
    public AddressEntity createAddress(AddressEntity addressEntity) {
        return null;
    }

    @Override
    public void deleteAddressByAddressId(Integer addressId) {

    }
}
