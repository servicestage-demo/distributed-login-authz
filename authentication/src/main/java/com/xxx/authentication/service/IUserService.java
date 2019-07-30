package com.xxx.authentication.service;

import com.xxx.authentication.model.UserDto;

/**
 *
 * @author Wang926454
 * @date 2018/8/9 15:44
 */
public interface IUserService {
  UserDto getByAccount(String account);
}
