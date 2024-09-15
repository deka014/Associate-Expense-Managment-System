package com.adp.IdentityService.IdentityService.mapper;


import com.adp.EntitiesService.entities.User;
import com.adp.IdentityService.IdentityService.Dto.AuthRequest;
import com.adp.IdentityService.IdentityService.Dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", expression = "java(user.map(User::getId).orElse(null))")
    @Mapping(target = "name", expression = "java(user.map(User::getName).orElse(null))")
    @Mapping(target = "jobTitle", expression = "java(user.map(User::getJobTitle).orElse(null))")
    @Mapping(target = "department", expression = "java(user.map(User::getDepartment).orElse(null))")
    @Mapping(target = "email", expression = "java(user.map(User::getEmail).orElse(null))")
    @Mapping(target = "phone", expression = "java(user.map(User::getPhone).orElse(null))")
    @Mapping(target = "managerId", expression = "java(user.map(User::getManagerId).orElse(null))")
    @Mapping(target = "role", expression = "java(user.map(User::getRole).orElse(null))")
    UserDto toUserDto(Optional<User> user);

//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "name", source = "auth.name")
//   // @Mapping(target = "jobTitle",source = "signUpDto.jobTitle")
//    //@Mapping(target = "department",source = "signUpDto.department")
//   // @Mapping(target = "timeShift", source = "signUpDto.timeShift")
//    User signUpToUser(AuthRequest authRequest);

}