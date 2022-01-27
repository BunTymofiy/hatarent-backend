package com.bun.hatarentbackend.night.businesslayer;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.night.datalayer.NightDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NightMapper {
    @Mapping(target = "nightId", ignore = true)
    Night nightDTOToNightEntity(NightDTO nightDTO);
    NightDTO nightEntityToNightDTO(Night night);
}
