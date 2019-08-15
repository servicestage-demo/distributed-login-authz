package com.xxx.authentication.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xxx.authentication.mapper.PermissionMapper;
import com.xxx.authentication.mapper.RoleMapper;
import com.xxx.authentication.model.PermissionDto;
import com.xxx.authentication.model.RoleDto;
import com.xxx.authentication.service.PermissionService;
import com.xxx.sso.model.UserDto;

/**
 * @Author wangqijun
 * @Date 19:48 2019-08-11
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
  @Resource
  PermissionMapper permissionMapper;

  @Resource
  RoleMapper roleMapper;

  @Resource
  private RedisTemplate redisTemplate;

  public Map<String, Boolean> verifyPermissions(String account, List<String> permissions) {
    List<RoleDto> roleList = null;
    Map<String, Boolean> result = new HashMap();
    Object roleListCache = redisTemplate.opsForValue().get("account_" + account);
    if (roleListCache == null) {
      UserDto userDto = new UserDto();
      userDto.setAccount(account);
      roleList = roleMapper.findRoleByUser(userDto);
      redisTemplate.opsForValue().set("account_" + account, roleList, 30, TimeUnit.SECONDS);
    } else {
      roleList = (List<RoleDto>) roleListCache;
    }
    for (RoleDto roleDto : roleList) {
      List<PermissionDto> permissionList = null;
      Object permissionListCache = redisTemplate.opsForValue().get("role_" + roleDto.getId());
      if (permissionListCache == null) {
        permissionList = permissionMapper.findPermissionByRole(roleDto);
        redisTemplate.opsForValue().set("role_" + account, permissionList, 30, TimeUnit.SECONDS);
      }
      hasPermission(permissions, result, permissionList);
    }
    return result;
  }

  private void hasPermission(List<String> permissions,
      Map<String, Boolean> result, List<PermissionDto> permissionList) {
    for (PermissionDto permissionDto : permissionList) {
      for (String permission : permissions) {
        if (permission.equalsIgnoreCase(permissionDto.getPerCode())) {
          result.put(permission, true);
        }
      }
    }
  }
}
