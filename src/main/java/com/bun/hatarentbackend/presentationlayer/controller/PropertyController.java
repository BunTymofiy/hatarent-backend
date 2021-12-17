package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.property.businesslayer.PropertyMapper;
import com.bun.hatarentbackend.property.businesslayer.PropertyService;
import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.NonNull;
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
@CrossOrigin("*")
public class PropertyController
{
    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }
    @GetMapping("/property")
    public List<PropertyEntity> findAllProperties() {
        List<PropertyEntity> propertyEntityList = propertyService.findAll();
        log.info("Found properties");
        return propertyEntityList;
    }
    @GetMapping("/property/{uuid}")
    public PropertyEntity findPropertyById(@PathVariable  @NotNull UUID uuid) {
        Optional<PropertyEntity> propertyEntity = propertyService.findByUUI(uuid);
        if(propertyEntity.isEmpty()) {
            log.info("item with uuid {} not found", uuid);
            throw new NotFoundException();
        }
        final PropertyEntity property = propertyEntity.get();
        log.info("retrieved item by uuid {}", property.getUuid());

        return property;
    }

    @PostMapping( value = "/property",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyEntity addProperty(@RequestBody @Valid PropertyDTO propertyDTO){
        PropertyEntity propertyMapped = propertyMapper.propertyDTOToPropertyEntity(propertyDTO);
        PropertyEntity propertyCreated = propertyService.create(propertyMapped);
        log.info("Property created");
        return propertyCreated;
    }

    @PutMapping( value = "/property/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PropertyEntity updateProperty(@PathVariable UUID uuid, @RequestBody PropertyDTO propertyDTO)
    {
        log.info("updating property");
        PropertyEntity propertyMapped = propertyMapper.propertyDTOToPropertyEntity(propertyDTO);
        propertyMapped.setUuid(uuid);
        PropertyEntity property = propertyService.update(propertyMapped);
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
