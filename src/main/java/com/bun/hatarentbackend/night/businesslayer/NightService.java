package com.bun.hatarentbackend.night.businesslayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.night.datalayer.Night;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NightService {

    List<Night> findAllNights();
    Optional<Night> findNightById(UUID nightId);
    Night createNight(Night nightEntity);
    Night updateNight(Night nightEntity);
    void deleteNight(UUID nightId);
}
