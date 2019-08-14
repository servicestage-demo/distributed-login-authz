package com.xxx.edge;

import java.util.Date;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxx.sso.constants.Constants;
import com.xxx.sso.util.JwtUtil;

/**
 * @Author wangqijun
 * @Date 09:41 2019-07-23
 **/

public class SsoHandler implements Handler {
  private static Logger LOGGER = LoggerFactory.getLogger(SsoHandler.class);


  @Override
  public void handle(Invocation invocation, AsyncResponse asyncResponse) throws Exception {
    String token = invocation.getLocalContext(Constants.X_TOKEN);
    if (token == null) {
      asyncResponse.consumerFail(new InvocationException(403, "forbidden", "need to login"));
      return;
    } else {
      try {
        DecodedJWT jwt = JWT.decode(token);
        Date date = jwt.getExpiresAt();
        LOGGER.info(date.toString());
        if (!JwtUtil.verify(token)) {
          asyncResponse.consumerFail(new InvocationException(403, "forbidden", "token is invalid."));
          return;
        }
      } catch (JWTDecodeException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    invocation.next(asyncResponse);
  }
}