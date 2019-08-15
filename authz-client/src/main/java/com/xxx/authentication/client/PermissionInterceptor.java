package com.xxx.authentication.client;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author wangqijun
 * @Date 16:58 2019-08-12
 **/
@Aspect
@Component
public class PermissionInterceptor extends AuthzInterceptor {

  private static Logger LOGGER = LoggerFactory.getLogger(RoleInterceptor.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  private RequiresPermissions requiresPermissions;

  @Pointcut(value = "@annotation(com.xxx.authentication.client.RequiresPermissions)")
  private void pointCut() {

  }

  @Around(value = "@annotation(requiresPermissions)")
  public Object doAround(ProceedingJoinPoint joinPoint, RequiresPermissions requiresPermissions) {
    this.requiresPermissions = requiresPermissions;
    return super.authz(joinPoint);
  }

  @Override
  String[] value() {
    return requiresPermissions.value();
  }

  @Override
  String path() {
    return "permission";
  }

  @Override
  String parameter() {
    return "permissions";
  }
}
