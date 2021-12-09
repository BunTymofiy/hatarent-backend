package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.address.datalayer.AddressRepository;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public AddressEntity getAddressByAddressId(Integer addressId) {
        return addressRepository.findByAddressId(addressId).orElseThrow(()->new NotFoundException("No address found for addressId: " + addressId));
    }

    @Override
    public AddressEntity updateAddress(AddressEntity addressEntity, AddressEntity UpdateAddressEntity) {
        if(UpdateAddressEntity.getStreet() != null && !UpdateAddressEntity.getStreet().isEmpty()){
            addressEntity.setStreet(UpdateAddressEntity.getStreet());
        }
        if(UpdateAddressEntity.getApartment() != null && !UpdateAddressEntity.getApartment().isEmpty()){
            addressEntity.setApartment(UpdateAddressEntity.getApartment());
        }
        if(UpdateAddressEntity.getCity() != null && !UpdateAddressEntity.getCity().isEmpty()){
            addressEntity.setCity(UpdateAddressEntity.getCity());
        }
        if(UpdateAddressEntity.getCountry() != null && !UpdateAddressEntity.getCountry().isEmpty()){
            addressEntity.setCountry(UpdateAddressEntity.getCountry());
        }
        if(UpdateAddressEntity.getZip() != null && !UpdateAddressEntity.getZip().isEmpty()){
            addressEntity.setZip(UpdateAddressEntity.getZip());
        }
        return addressRepository.save(addressEntity);
    }

    @Override
    public AddressEntity createAddress(AddressEntity addressEntity) {
        return addressRepository.save(addressEntity);
    }

    @Override
    public void deleteAddressByAddressId(Integer addressId) {
        AddressEntity address = getAddressByAddressId(addressId);
        addressRepository.delete(address);
    }
}
