package com.rz.xnetwork.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.rz.xnetwork.dto.AppUserDto;
import com.rz.xnetwork.models.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto toDto(AppUser user);

    List<AppUserDto> toDto(List<AppUser> user);
}
