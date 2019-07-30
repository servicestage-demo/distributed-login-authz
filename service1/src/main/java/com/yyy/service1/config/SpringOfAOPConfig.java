package com.yyy.service1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.xxx.authentication.client.AuthenticationAspect;

/**
 * @Author wangqijun
 * @Date 16:56 2019-07-24
 **/
//该配置类是为了将我们前2个类（切面类、被切面类）加入到Spring容器中
@Configuration
//启动AOP（一定要加这个注解，切记！！！）
@EnableAspectJAutoProxy
public class SpringOfAOPConfig {
  @Bean
  public AuthenticationAspect authAspect () {
    return new AuthenticationAspect();
  }

}
