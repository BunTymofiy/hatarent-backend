package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.property.datalayer.PropertyDTO;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    @Override
    public List<PropertyEntity> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public PropertyEntity getPropertyByPropertyId(Integer propertyId) {
        return propertyRepository.findByPropertyId(propertyId).orElseThrow(() -> new NotFoundException("No property found for propertyId: " + propertyId));
    }

    @Override
    public PropertyEntity updateProperty(PropertyEntity propertyEntity, PropertyEntity UpdatePropertyEntity)
    {
        if(UpdatePropertyEntity.getDescription() != null && !UpdatePropertyEntity.getDescription().isEmpty())
        {
            propertyEntity.setDescription(UpdatePropertyEntity.getDescription());
        }
        if(UpdatePropertyEntity.getGuestLimit() != null)
        {
            propertyEntity.setGuestLimit(UpdatePropertyEntity.getGuestLimit());
        }
        if(UpdatePropertyEntity.getTitle() != null && !UpdatePropertyEntity.getTitle().isEmpty())
        {
            propertyEntity.setTitle(UpdatePropertyEntity.getTitle());
        }

        return propertyRepository.save(propertyEntity);
    }

    @Override
    public PropertyEntity createProperty(PropertyEntity propertyEntity) {
        return propertyRepository.save(propertyEntity);
    }

    @Override
    public void deletePropertyByPropertyId(Integer propertyId)
    {
        PropertyEntity property = getPropertyByPropertyId(propertyId);
        propertyRepository.delete(property);
    }

//    @Override
//    public List<PropertyDTO> getAllPropertiesDTO() {
//        List<PropertyEntity> propertyEntityList = getAllProperties();
//        List<PropertyDTO> propertyDTOList = propertyMapper.propertyEntityListToPropertyDTOList(propertyEntityList);
//        return propertyDTOList;
//    }
//
//    @Override
//    public PropertyDTO getPropertyDTOByPropertyId(UUID propertyId) {
//        PropertyEntity property = getPropertyByPropertyId(propertyId);
//        PropertyDTO propertyDTO = propertyMapper.propertyEntityToPropertyDTO(property);
//        return propertyDTO;
//    }
//
//    @Override
//    public PropertyDTO updatePropertyDTO(UUID propertyId, PropertyDTO UpdatePropertyEntity)
//    {
//        UpdatePropertyEntity.setPropertyId((propertyId));
//        PropertyEntity property = getPropertyByPropertyId(propertyId);
//        PropertyEntity propertyUpd = propertyMapper.propertyDTOToPropertyEntity(UpdatePropertyEntity);
//        return propertyMapper.propertyEntityToPropertyDTO(updateProperty(property,propertyUpd));
//    }
//
//    @Override
//    public PropertyDTO createPropertyDTO(PropertyDTO propertyDTO) {
//        PropertyEntity property = propertyMapper.propertyDTOToPropertyEntity(propertyDTO);
//        return propertyMapper.propertyEntityToPropertyDTO(property);
//    }
//
//    @Override
//    public void deletePropertyDTOByPropertyId(UUID propertyId) {
//        deletePropertyByPropertyId(propertyId);
//    }
}
