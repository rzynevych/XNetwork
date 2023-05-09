package com.rz.xnetwork.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.xnetwork.dto.AppUserDto;
import com.rz.xnetwork.mappers.AppUserMapper;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.repos.AppUserRepository;


@Service
@Transactional
public class AppUserService {

    @Autowired
    private AppUserRepository userRepo;

    // @Autowired
    // private AppUserMapper mapper;

    // public AppUserDto findById(Long id) {
    //     AppUser user = userRepo.findByUserID(id);
    //     return mapper.toDto(user);
    // }

    // public List<UserDto> findByName(String name) {
    //     List<User> users = userRepo.findByName(name);

    //     return mapper.toDto(users);
    // }

    // public List<UserDto> search(String name, String surname) {
    //     List<User> users = userRepo.findAll(UserSpecificationUtil.specByParams(name, surname));

    //     return mapper.toDto(users);
    // }
}
