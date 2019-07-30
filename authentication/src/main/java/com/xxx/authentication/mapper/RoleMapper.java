package com.xxx.authentication.mapper;

import java.util.List;

import com.xxx.authentication.model.RoleDto;
import com.xxx.authentication.model.UserDto;

public interface RoleMapper {
  List<RoleDto> findRoleByUser(UserDto userDto);
}