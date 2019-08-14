package com.xxx.authentication.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Author wangqijun
 * @Date 11:32 2019-07-24
 **/
@Aspect
@Component
public class RoleInterceptor extends AuthzInterceptor {
  private static Logger LOGGER = LoggerFactory.getLogger(RoleInterceptor.class);

  private RequiresRoles requiresRoles;

  @Pointcut(value = "@annotation(com.xxx.authentication.client.RequiresRoles))")
  private void pointCut() {

  }

  @Around(value = "@annotation(requiresRoles)")
  public Object doAround(ProceedingJoinPoint joinPoint, RequiresRoles requiresRoles) {
    this.requiresRoles = requiresRoles;
    return authz(joinPoint);
  }

  @Override
  String[] value() {
    return requiresRoles.value();
  }

  @Override
  String path() {
    return "role";
  }

  @Override
  String parameter() {
    return "roles";
  }
}
