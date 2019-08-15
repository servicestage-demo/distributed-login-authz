package com.xxx.authentication.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xxx.authentication.mapper.RoleMapper;
import com.xxx.authentication.model.RoleDto;
import com.xxx.authentication.service.RoleService;
import com.xxx.sso.model.UserDto;

/**
 * @Author wangqijun
 * @Date 09:08 2019-08-11
 **/

@Service
public class RoleServiceImpl implements RoleService {

  @Resource
  RoleMapper roleMapper;

  @Resource
  private RedisTemplate redisTemplate;

  public List<RoleDto> findRoleByUser(UserDto userDto) {
    return roleMapper.findRoleByUser(userDto);
  }

  public Map<String, Boolean> verifyRole(String account, List<String> roles) {
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
    for (String role : roles) {
      if (contains(roleList, role)) {
        result.put(role, true);
      }
    }
    return result;
  }

  private boolean contains(List<RoleDto> roleList, String role) {
    for (RoleDto roleDto : roleList) {
      if (role.equalsIgnoreCase(roleDto.getName())) {
        return true;
      }
    }
    return false;
  }
}
