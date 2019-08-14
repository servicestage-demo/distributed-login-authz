package com.xxx.authentication.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xxx.authentication.model.RoleDto;
import com.xxx.sso.model.UserDto;

@Repository
public interface RoleMapper {
  List<RoleDto> findRoleByUser(UserDto userDto);
}