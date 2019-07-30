package com.xxx.authentication.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xxx.authentication.mapper.UserMapper;
import com.xxx.authentication.model.UserDto;
import com.xxx.authentication.service.IUserService;


/**
 *
 * @author Wang926454
 * @date 2018/8/9 15:45
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
  @Resource
  private UserMapper userMapper;

  @Resource
  private RedisTemplate redisTemplate;


  public UserDto getByAccount(String account) {
    redisTemplate.opsForValue().set("a","a");
    Object v = redisTemplate.opsForValue().get("a");
    System.out.printf("vvvvvvvvvv"+v);
    return userMapper.getByAccount(account);
  }
}
