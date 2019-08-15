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
    } catch (Exception e) {
      e.printStackTrace();
    }
    BeanUtils.init();
    ApplicationContext context = BeanUtils.getContext();
    String[] str = context.getBeanDefinitionNames();
    for (String string : str) {
      System.out.println("..." + string);
    }
    System.out.println("...===============================");
//    Annotation[] annotations = ServiceController.class.getAnnotations();
//    for (Annotation string : annotations) {
//      System.out.println("..."+string);
//    }

    Method[] methods = ServiceController.class.getDeclaredMethods();
    for (Method method : methods) {
      Annotation[] annotations = method.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        System.out.println(ServiceController.class.getSimpleName().concat(".").concat(method.getName()).concat(".")
            .concat(annotation.annotationType().getSimpleName()));
      }
    }
  }
}
