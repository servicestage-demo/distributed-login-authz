package com.xxx.authentication.client;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @Author wangqijun
 * @Date 11:32 2019-07-24
 **/
@Aspect
@Component
public class AuthenticationAspect {
  private RestTemplate restTemplate = RestTemplateBuilder.create();
  private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationAspect.class);


  @Pointcut(value = "@annotation(com.xxx.authentication.client.RequiresRoles)")
  private void pointCut() {

  }

  @Around(value = "@annotation(requiresRoles)")//(execution(* com.yyy.service1.ServiceController.*(..))) &&
  public Object doAround(ProceedingJoinPoint joinPoint,RequiresRoles requiresRoles) {
    System.out.println("9999999999999999999999999999999999999");
    LOGGER.info("9999999999999999999999999999999999999");
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    System.out.println("方法名：" + methodName);

    InvocationContext context = ContextUtils.getInvocationContext();
    String token = context.getContext("x-token");
    //System.out.printf(token);

    if (validate()){
      try {
        return joinPoint.proceed(); //执行程序
      } catch (Throwable throwable) {
        throw new InvocationException(javax.ws.rs.core.Response.Status.FORBIDDEN, throwable.getMessage());
      }
    }else {
      //MultiResponse403 resp = new MultiResponse403(403, "no login", null);
      throw new InvocationException(javax.ws.rs.core.Response.Status.FORBIDDEN, "");
    }
  }

  private boolean validate() {
    ResponseEntity<Integer> result = restTemplate.postForEntity("cse://sso/login?username=ddd", "",Integer.class);
    return result.getBody().equals("200");
  }


}
