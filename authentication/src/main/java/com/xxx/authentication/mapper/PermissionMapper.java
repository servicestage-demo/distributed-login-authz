package com.xxx.authentication.mapper;

import java.util.List;

import com.xxx.authentication.model.PermissionDto;
import com.xxx.authentication.model.RoleDto;

public interface PermissionMapper {
  List<PermissionDto> findPermissionByRole(RoleDto roleDto);
}