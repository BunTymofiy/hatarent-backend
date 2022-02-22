package com.bun.hatarentbackend.property.businesslayer;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;

import com.bun.hatarentbackend.reservation.businesslayer.ReservationService;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j

public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final ReservationService reservationService;
    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ReservationService reservationService) {
        this.propertyRepository = propertyRepository;
        this.reservationService = reservationService;
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
        if (property.getTitle() == null) {
            log.info("property not created");
        }
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

    @Override
    public List<Property> findByHostUuid(UUID hostId) {
        log.info("Getting property by host id");
        List<Property> propertyEntity = propertyRepository.findByHostUserUuid(hostId);
        log.info("Found property by host id");
        return propertyEntity;
    }


    @Override
    public List<Property> findPropertiesByCityDateAndGuests(String city, String startDate, String endDate, Integer guests) {
        log.info("Getting property by location");
        Date start = new Date(startDate);
        Date end = new Date(endDate);
        List<Property> properties = propertyRepository.findByAddressCity(city);
        log.info("Found property by location");
        List<Reservation> reservations = new ArrayList<>();
        List<Property> propertiesAvailable = new ArrayList<>();
        for (Property property : properties) {
            var res = reservationService.findAllReservationByPropertyUuid(property.getUuid());
            if(res.isEmpty()) propertiesAvailable.add(property);
            else reservations.addAll(res);
        }

        if(reservations.size() > 0) {
            for (Reservation reservation : reservations) {
                if (reservation.getCheckInDate().after(end) || reservation.getCheckOutDate().before(start) || reservation.getStatus().equals("denied")) {
                    propertiesAvailable.add(reservation.getProperty());
                }
            }
        }
        List<Property> propertiesAvailableByGuests = new ArrayList<>();
        for (Property property : propertiesAvailable) {
            if (property.getGuestLimit() >= guests) {
                propertiesAvailableByGuests.add(property);
            }
        }
        return propertiesAvailableByGuests;
    }
}
