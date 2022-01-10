package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.address.businesslayer.AddressMapper;
import com.bun.hatarentbackend.address.businesslayer.AddressService;
import com.bun.hatarentbackend.address.datalayer.AddressDTO;
import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.property.businesslayer.PropertyMapper;
import com.bun.hatarentbackend.property.businesslayer.PropertyService;
import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
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
    private final AddressMapper addressMapper;
    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper, AddressMapper addressMapper, AddressService addressService) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
        this.addressMapper = addressMapper;
        this.addressService = addressService;
    }
    @GetMapping("/property")
    public List<PropertyEntity> findAllProperties() {
        List<PropertyEntity> propertyEntityList = propertyService.findAll();
        log.info("Found properties");
        return propertyEntityList;
    }
    @GetMapping("/property/{uuid}")
    public PropertyEntity findPropertyById(@PathVariable  @NotNull UUID uuid) {
        Optional<PropertyEntity> propertyEntity = propertyService.findByUuid(uuid);
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
//    @GetMapping("/address")
//    public List<AddressEntity> findAllAddress() {
//        List<AddressEntity> addressEntityList = addressService.findAll();
//        log.info("Found Addresses");
//        return addressEntityList;
//    }
//    @GetMapping("/address/{uuid}")
//    public AddressEntity findAddressById(@PathVariable  @NotNull UUID uuid) {
//        Optional<AddressEntity> addressEntity = addressService.findByUuid(uuid);
//        if(addressEntity.isEmpty()) {
//            log.info("item with uuid {} not found", uuid);
//            throw new NotFoundException();
//        }
//        final AddressEntity address = addressEntity.get();
//        log.info("retrieved item by uuid {}", address.getUuid());
//
//        return address;
//    }
//
//    @PostMapping( value = "/address",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ResponseStatus(HttpStatus.CREATED)
//    public AddressEntity addAddress(@RequestBody @Valid AddressDTO addressDTO){
//        AddressEntity addressMapped = addressMapper.addressDTOToAddressEntity(addressDTO);
//        AddressEntity addressCreated = addressService.create(addressMapped);
//        log.info("Address created");
//        return addressCreated;
//    }
//
//    @PutMapping( value = "/address/{uuid}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public AddressEntity updateAddress(@PathVariable UUID uuid, @RequestBody AddressDTO addressDTO)
//    {
//        log.info("updating Address");
//        AddressEntity addressMapped = addressMapper.addressDTOToAddressEntity(addressDTO);
//        addressMapped.setUuid(uuid);
//        AddressEntity address = addressService.update(addressMapped);
//        log.info("updated Address");
//        return address;
//    }
//    @DeleteMapping(path = "/address/{uuid}")
//    public void deleteByAddressId(@PathVariable UUID uuid){
//        log.info("deleting Address");
//        addressService.delete(uuid);
//        log.info("deleted Address");
//    }
}
