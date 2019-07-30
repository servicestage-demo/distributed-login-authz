package com.xxx.authentication;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author wangqijun
 * @Date 14:39 2019-07-23
 **/

public class AuthenticationMain {
  public static void main(String[] args) {
    try {
      Log4jUtils.init();
      BeanUtils.init();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
