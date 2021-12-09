package com.bun.hatarentbackend.property.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Integer>
{
    Optional<PropertyEntity> findByPropertyId(int propertyId);
}
