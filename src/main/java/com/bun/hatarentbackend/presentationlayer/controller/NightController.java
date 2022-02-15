package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.night.businesslayer.NightMapper;
import com.bun.hatarentbackend.night.businesslayer.NightService;
import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.night.datalayer.NightDTO;
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
@CrossOrigin(origins = "http://localhost:3000")
public class NightController {
    private final NightService nightService;
    private final NightMapper nightMapper;

    public NightController(NightService nightService, NightMapper nightMapper) {
        this.nightService = nightService;
        this.nightMapper = nightMapper;
    }

    @GetMapping("/nights")
    public List<Night> getAllNights(){
        List<Night> nightList = nightService.findAllNights();
        log.info("Found nights");
        return nightList;
    }

    @GetMapping("/nights/{nightId}")
    public Night findNightById(@PathVariable  @NotNull UUID nightId) {
        Optional<Night> nightEntity = nightService.findNightById(nightId);
        if(nightEntity.isEmpty()) {
            log.info("item with night id {} not found", nightId);
            throw new NotFoundException();
        }
        final Night night = nightEntity.get();
        log.info("retrieved item by uuid {}", night.getNightId());

        return night;
    }

    @PostMapping( value = "/nights",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Night addNight(@RequestBody @Valid NightDTO nightDTO){
        Night nightMapped = nightMapper.nightDTOToNightEntity(nightDTO);
        Night nightCreated = nightService.createNight(nightMapped);
        log.info("Night created");
        return nightCreated;
    }

    @PutMapping(value = "/nights/{nightId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Night updateNight(@PathVariable UUID nightId, @RequestBody NightDTO nightDTO){
        log.info("updating night");
        Night nightMapped = nightMapper.nightDTOToNightEntity(nightDTO);
        nightMapped.setNightId(nightId);
        Night night = nightService.updateNight(nightMapped);
        log.info("updated night");
        return night;
    }

    @DeleteMapping(path = "/nights/{nightId}")
    public void deleteByNightId(@PathVariable UUID nightId){
        log.info("deleting night");
        nightService.deleteNight(nightId);
        log.info("deleted night");
    }
}
