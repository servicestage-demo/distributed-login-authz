package com.xxx.sso.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xxx.sso.mapper.UserMapper;
import com.xxx.sso.model.UserDto;
import com.xxx.sso.service.UserService;


/**
 *
 * @author Wang926454
 * @date 2018/8/9 15:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {
  @Resource
  private UserMapper userMapper;


  public UserDto getByAccount(String account) {

    return userMapper.getByAccount(account);
  }
}
