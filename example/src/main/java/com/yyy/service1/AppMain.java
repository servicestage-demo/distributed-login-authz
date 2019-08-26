package com.yyy.service1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.springframework.context.ApplicationContext;

import com.yyy.service1.controller.ServiceController;

/**
 * @Author wangqijun
 * @Date 14:39 2019-07-23
 **/

public class AppMain {
  public static void main(String[] args) {
    try {
      Log4jUtils.init();
      BeanUtils.init();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
