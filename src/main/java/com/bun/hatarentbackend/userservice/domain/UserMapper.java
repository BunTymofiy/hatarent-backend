package com.bun.hatarentbackend.userservice.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPasswordLessDTO toPasswordLessDTO(User user);
    UserNameAndEmailDTO toNameAndEmailDTO(User user);
}
