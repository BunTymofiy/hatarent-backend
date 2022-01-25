package com.bun.hatarentbackend.night.businesslayer;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.night.datalayer.NightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j

public class NightServiceImpl implements NightService{

    private final NightRepository nightRepository;

    public NightServiceImpl(NightRepository nightRepository) {
        this.nightRepository = nightRepository;
    }

    @Override
    public List<Night> findAllNights() {
        log.info("Retrieving all nights");
        return nightRepository.findAll();
    }

    @Override
    public Optional<Night> findNightById(UUID nightId) {
        log.info("Retrieving Night");
        Optional<Night> night = nightRepository.findById(nightId);
        return night;
    }

    @Override
    public Night createNight(Night nightEntity) {
        log.info("Creating Night");
        Night night = nightRepository.save(nightEntity);
        log.info("Created Night");
        return night;
    }

    @Override
    public Night updateNight(Night nightEntity) {
        log.info("Creating Night");
        Night night = nightRepository.save(nightEntity);
        log.info("Created Night");
        return night;
    }

    @Override
    @Transactional
    public void deleteNight(UUID nightId) {
        log.info("Deleting Night");
        final Integer deleted = nightRepository.deleteByNightId(nightId);
        if(deleted<=0){
            log.info("Night not found");
            return;
        }
        log.info("Night was successfully deleted");
    }
}
