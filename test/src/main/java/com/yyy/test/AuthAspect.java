package com.yyy.test;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author wangqijun
 * @Date 11:32 2019-07-24
 **/
@Aspect
@Component
public class AuthAspect {
  private static RestTemplate restTemplate = RestTemplateBuilder.create();


  @Pointcut(value = "@annotation(com.yyy.test.RequiresRoles)")
  private void pointCut() {

  }
//  @Before(value = "(execution(* com.yyy.test.OrderImpl.*(..))) && @annotation(requiresRoles)")
//  public void before(JoinPoint point,RequiresRoles requiresRoles) {
//    System.out.println("666666666666666666666666666666666");
//
////    InvocationContext context = ContextUtils.getInvocationContext();
////    String token = context.getContext("x-token");
////    System.out.printf(token);
////    ResponseEntity<Integer> result = restTemplate
////        .getForEntity("rest://user/login", Integer.class);
////    if (result.getBody().equals("200")){
////      try {
////        return point..proceed(); //执行程序
////      } catch (Throwable throwable) {
////        throwable.printStackTrace();
////        return throwable.getMessage();
////      }
////    }
//  }

  @Around(value = "(execution(* com.yyy.test.OrderImpl.*(..))) && @annotation(requiresRoles)")
  public Object doAround(ProceedingJoinPoint joinPoint,RequiresRoles requiresRoles) {
    System.out.println("7777777777777777777777777777777");

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    System.out.println("方法名：" + methodName);
    if (!validate()) {
      return "没有权限";
    }
    try {
      return joinPoint.proceed();
    } catch (Throwable throwable) {
      return null;
    }
  }

  private boolean validate() {
    // TODO 实现自己的鉴权功能
    return false;
  }


}
