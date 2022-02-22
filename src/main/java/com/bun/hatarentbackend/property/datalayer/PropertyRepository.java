package com.bun.hatarentbackend.property.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID>
{
    Integer deleteByUuid(UUID id);
    List<Property> findByHostUserUuid(UUID id);
    List<Property> findByAddressCity(String city);
}
