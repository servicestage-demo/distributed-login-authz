package com.xxx.authentication.service;

import java.util.List;
import java.util.Map;

/**
 * @Author wangqijun
 * @Date 19:51 2019-08-11
 **/
public interface PermissionService {
  Map<String, Boolean> verifyPermissions(String account, List<String> permissions);
}
