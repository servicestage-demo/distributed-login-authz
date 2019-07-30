package com.xxx.sso;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

/**
 * @Author wangqijun
 * @Date 14:39 2019-07-23
 **/
public class SsoMain {
  public static void main(String[] args) {
    try {
      Log4jUtils.init();
      BeanUtils.init();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
