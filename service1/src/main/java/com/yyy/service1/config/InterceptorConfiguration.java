package com.yyy.service1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.xxx.authentication.client.PermissionInterceptor;
import com.xxx.authentication.client.RoleInterceptor;

/**
 * @Author wangqijun
 * @Date 16:56 2019-07-24
 **/
@Configuration
@EnableAspectJAutoProxy
public class InterceptorConfiguration {
  @Bean
  public RoleInterceptor roleInterceptor() {
    return new RoleInterceptor();
  }

  @Bean
  public PermissionInterceptor permissionInterceptor() {
    return new PermissionInterceptor();
  }
}
