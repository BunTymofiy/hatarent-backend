package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j

public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @Override
    public List<PropertyEntity> findAll() {
        log.info("Getting all the data");
        return propertyRepository.findAll();
    }

    @Override
    public Optional<PropertyEntity> findByUUI(UUID uuid) {
        log.info("Getting property");
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(uuid);
        log.info("Found property");
        return propertyEntity;
    }


    @Override
    public PropertyEntity update(PropertyEntity propertyEntity) {
        log.info("Updating property");
        PropertyEntity property = propertyRepository.save(propertyEntity);
        log.info("Updated property");
        return property;
    }

    @Override
    public PropertyEntity create(PropertyEntity propertyEntity) {
        log.info("Creating property");
        PropertyEntity property = propertyRepository.save(propertyEntity);
        log.info("Created property");
        return property;
    }

    @Override
    public void delete(UUID uuid) {
        log.info("deleting property");
        final Integer deleted = propertyRepository.deleteByUuid(uuid);
        if(deleted <= 0) {
            log.info("property not found");
            return;
        }
        log.info("deleted property");
    }
}
