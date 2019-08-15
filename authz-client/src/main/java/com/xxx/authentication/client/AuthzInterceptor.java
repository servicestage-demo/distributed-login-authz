package com.xxx.authentication.client;

import java.util.Map;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author wangqijun
 * @Date 17:54 2019-08-12
 **/
public abstract class AuthzInterceptor {
  protected RestTemplate restTemplate = RestTemplateBuilder.create();

  abstract String[] value();

  abstract String path();

  abstract String parameter();

  protected Object authz(ProceedingJoinPoint joinPoint) {
    InvocationContext context = ContextUtils.getInvocationContext();
    String account = context.getContext("account");

    if (validate(account)) {
      try {
        return joinPoint.proceed();
      } catch (Throwable throwable) {
        throw new InvocationException(javax.ws.rs.core.Response.Status.FORBIDDEN, throwable.getMessage());
      }
    } else {
      throw new InvocationException(javax.ws.rs.core.Response.Status.FORBIDDEN, "");
    }
  }

  private boolean validate(String account) {
    StringBuilder urlStringBuilder = new StringBuilder("cse://authz/" + path() + "?account=" + account);
    for (String value : value()) {
      if (!value.isEmpty()) {
        urlStringBuilder.append("&" + parameter() + "=");
        urlStringBuilder.append(value);
      }
    }
    ResponseEntity<Map> result = restTemplate.getForEntity(urlStringBuilder.toString(), Map.class);
    if (result.getStatusCode().value() == HttpStatus.OK.value() && result.getBody().size() == value().length) {
      return true;
    }
    return false;
  }
}
