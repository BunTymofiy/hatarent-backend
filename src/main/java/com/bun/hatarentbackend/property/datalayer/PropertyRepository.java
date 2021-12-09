package com.bun.hatarentbackend.property.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Integer>
{
    Optional<PropertyEntity> findByPropertyId(UUID PropertyId);
}
