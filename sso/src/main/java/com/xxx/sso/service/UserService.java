package com.xxx.sso.service;

import com.xxx.sso.model.UserDto;

/**
 *
 * @author Wang926454
 * @date 2018/8/9 15:44
 */
public interface UserService {
  UserDto getByAccount(String account);
}
