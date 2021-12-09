package com.bun.hatarentbackend.property.presentationlayer.controllers;

import com.bun.hatarentbackend.property.businesslayer.PropertyService;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.utils.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/property")
@RestController
public class PropertyController
{
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @GetMapping()
    public List<PropertyEntity> showResourcesVetListEnabled() {
        List<PropertyEntity> propertyEntityList = propertyService.getAllProperties();
        return propertyEntityList;
    }
    @GetMapping("/{propertyId}")
    public PropertyEntity findPopertyById(@PathVariable int propertyId) {
        if(propertyId < 1) throw new InvalidInputException("Invalid propertyId: " + propertyId);
        PropertyEntity propertyEntity = propertyService.getPropertyByPropertyId(propertyId);
        return propertyEntity;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyEntity addProperty(@RequestBody PropertyEntity propertyEntity){return propertyService.createProperty(propertyEntity);}

    @PutMapping( value = "/{propertyId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PropertyEntity updateProperty(@PathVariable int propertyId, @RequestBody PropertyEntity propertyEntity)
    {

        return propertyService.updateProperty(propertyService.getPropertyByPropertyId(propertyId), propertyEntity);
    }
    @DeleteMapping(path = "/{propertyId}")
    public void deleteByPropertyId(@PathVariable int propertyId){propertyService.deletePropertyByPropertyId(propertyId);}
}
