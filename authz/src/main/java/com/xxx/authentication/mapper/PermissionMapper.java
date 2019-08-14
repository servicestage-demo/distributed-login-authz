package com.xxx.authentication.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xxx.authentication.model.PermissionDto;
import com.xxx.authentication.model.RoleDto;

@Repository
public interface PermissionMapper {
  List<PermissionDto> findPermissionByRole(RoleDto roleDto);
}