package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.night.businesslayer.NightMapper;
import com.bun.hatarentbackend.night.businesslayer.NightService;
import com.bun.hatarentbackend.night.datalayer.Night;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("nights")
    public List<Night> getAllNights(){
        List<Night> nightList = nightService.findAllNights();
        log.info("Found nights");
        return nightList;
    }
}
