package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Property> findAll() {
        log.info("Getting all the data");
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> findByUuid(UUID uuid) {
        log.info("Getting property");
        Optional<Property> propertyEntity = propertyRepository.findById(uuid);
        log.info("Found property");
        return propertyEntity;
    }


    @Override
    public Property update(Property propertyEntity) {
        log.info("Updating property");
        Property property = propertyRepository.save(propertyEntity);
        log.info("Updated property");
        return property;
    }

    @Override
    public Property create(Property propertyEntity) {
        log.info("Creating property");
        Property property = propertyRepository.save(propertyEntity);
        log.info("Created property");
        return property;
    }

    @Override
    @Transactional
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
