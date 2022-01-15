package com.bun.hatarentbackend.presentationlayer.controller;


import com.bun.hatarentbackend.property.businesslayer.PropertyMapper;
import com.bun.hatarentbackend.property.businesslayer.PropertyService;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping()
@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class PropertyController
{
    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }
    @GetMapping("/property")
    public List<Property> findAllProperties() {
        List<Property> propertyList = propertyService.findAll();
        log.info("Found properties");
        return propertyList;
    }
    @GetMapping("/property/{uuid}")
    public Property findPropertyById(@PathVariable  @NotNull UUID uuid) {
        Optional<Property> propertyEntity = propertyService.findByUuid(uuid);
        if(propertyEntity.isEmpty()) {
            log.info("item with uuid {} not found", uuid);
            throw new NotFoundException();
        }
        final Property property = propertyEntity.get();
        log.info("retrieved item by uuid {}", property.getUuid());

        return property;
    }

    @PostMapping( value = "/property",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Property addProperty(@RequestBody @Valid PropertyDTO propertyDTO){
        Property propertyMapped = propertyMapper.propertyDTOToPropertyEntity(propertyDTO);
        Property propertyCreated = propertyService.create(propertyMapped);
        log.info("Property created");
        return propertyCreated;
    }

    @PutMapping( value = "/property/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Property updateProperty(@PathVariable UUID uuid, @RequestBody PropertyDTO propertyDTO)
    {
        log.info("updating property");
        Property propertyMapped = propertyMapper.propertyDTOToPropertyEntity(propertyDTO);
        propertyMapped.setUuid(uuid);
        Property property = propertyService.update(propertyMapped);
        log.info("updated property");
        return property;
    }
    @DeleteMapping(path = "/property/{uuid}")
    public void deleteByPropertyId(@PathVariable UUID uuid){
        log.info("deleting property");
        propertyService.delete(uuid);
        log.info("deleted property");
    }
}