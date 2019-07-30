package com.xxx.sso.mapper;

import org.springframework.stereotype.Repository;

import com.xxx.sso.model.UserDto;


@Repository
public interface UserMapper {
    UserDto getByAccount(String account);
}