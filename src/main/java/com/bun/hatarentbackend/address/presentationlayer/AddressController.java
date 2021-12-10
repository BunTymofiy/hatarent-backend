package com.bun.hatarentbackend.address.presentationlayer;

import com.bun.hatarentbackend.address.businesslayer.AddressService;
import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.utils.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressEntity> showAddressesList(){
        List<AddressEntity> addressEntityList = addressService.getAllAddresses();
        return addressEntityList;
    }
    @GetMapping
    public AddressEntity findAddressById(@PathVariable int addressId){
        if(addressId < 1) throw new InvalidInputException("Invalid addressId: " + addressId);
        AddressEntity addressEntity = addressService.getAddressByAddressId(addressId);
        return addressEntity;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AddressEntity addressEntity(@RequestBody AddressEntity addressEntity){
        return addressService.createAddress(addressEntity);
    }

    @PutMapping( value = "/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AddressEntity updateAddress(@PathVariable int addressId, @RequestBody AddressEntity addressEntity){
        return addressService.updateAddress(addressService.getAddressByAddressId(addressId), addressEntity);
    }

    @DeleteMapping(path = "/{addressId}")
    public void deleteByAddressId(@PathVariable int addressId){addressService.deleteAddressByAddressId(addressId);}

}
