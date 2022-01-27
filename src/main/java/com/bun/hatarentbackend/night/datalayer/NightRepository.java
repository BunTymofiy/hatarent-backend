package com.bun.hatarentbackend.night.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NightRepository extends JpaRepository<Night, UUID>{
    Integer deleteByNightId(UUID id);
}
