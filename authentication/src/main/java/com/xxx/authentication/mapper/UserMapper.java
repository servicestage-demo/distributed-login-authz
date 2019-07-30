package com.xxx.authentication.mapper;

import org.springframework.stereotype.Repository;

import com.xxx.authentication.model.UserDto;
@Repository
public interface UserMapper {
    UserDto getByAccount(String account);
}