package com.bun.hatarentbackend.address.businesslayer;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.address.datalayer.AddressRepository;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<AddressEntity> findByUuid(UUID uuid) {
        log.info("Getting Address");
        Optional<AddressEntity> addressEntity = addressRepository.findById(uuid);
        log.info("Found Address");
        return addressEntity;
    }

    @Override
    public AddressEntity update(AddressEntity addressEntity) {
        log.info("Updating Address");
        AddressEntity address = addressRepository.save(addressEntity);
        log.info("Updated Address");
        return addressRepository.save(address);
    }

    @Override
    public AddressEntity create(AddressEntity addressEntity) {
        log.info("Creating Address");
        AddressEntity address = addressRepository.save(addressEntity);
        log.info("Created Address");
        return address;
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        log.info("deleting Address");
        final Integer deleted = addressRepository.deleteByUuid(uuid);
        if(deleted <= 0) {
            log.info("Address not found");
            return;
        }
        log.info("deleted Address");
    }
}
