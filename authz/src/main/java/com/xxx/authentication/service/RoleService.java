package com.xxx.authentication.service;

import java.util.List;
import java.util.Map;

import com.xxx.authentication.model.RoleDto;
import com.xxx.sso.model.UserDto;

/**
 * @Author wangqijun
 * @Date 09:05 2019-08-11
 **/
public interface RoleService {
  List<RoleDto> findRoleByUser(UserDto userDto);

  Map<String, Boolean> verifyRole(String account, List<String> roles);
}
