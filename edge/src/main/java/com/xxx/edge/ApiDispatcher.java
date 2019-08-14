package com.xxx.edge;

import java.util.Map;

import org.apache.servicecomb.edge.core.AbstractEdgeDispatcher;
import org.apache.servicecomb.edge.core.EdgeInvocation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxx.sso.constants.Constants;
import com.xxx.sso.util.JwtUtil;

import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CookieHandler;

/**
 * @Author wangqijun
 * @Date 11:13 2019-07-23
 **/
public class ApiDispatcher extends AbstractEdgeDispatcher {


  @Override
  public int getOrder() {
    return 10003;
  }

  @Override
  public void init(Router router) {
    String regex = "/" + Constants.PREFIX + "/([^\\\\/]+)/([^\\\\/]+)/(.*)";
    router.routeWithRegex(regex).handler(CookieHandler.create());
    router.routeWithRegex(regex).handler(createBodyHandler());
    router.routeWithRegex(regex).failureHandler(this::onFailure).handler(this::onRequest);
  }

  protected void onRequest(RoutingContext context) {
    Map<String, String> pathParams = context.pathParams();
    String version = pathParams.get("param0");
    String microserviceName = pathParams.get("param1");
    String path = "/" + pathParams.get("param2");

    EdgeInvocation invoker = new EdgeInvocation() {
      protected void setContext() throws Exception {
        super.setContext();
        String token = context.request().getHeader(Constants.X_TOKEN);
        if (token == null) {
          Cookie sessionCookie = context.getCookie(Constants.X_TOKEN);
          if (sessionCookie != null) {
            token = sessionCookie.getValue();
          }
        }
        if (token != null) {
          DecodedJWT jwt = JWT.decode(token);
          String account = JwtUtil.getClaim(token, Constants.ACCOUNT);
          this.invocation.addContext(Constants.ACCOUNT, account);
          this.invocation.addLocalContext(Constants.X_TOKEN, token);
          this.invocation.addLocalContext(Constants.PATH, path);
        }
      }
    };
    invoker.init(microserviceName, context, path, httpServerFilters);
    invoker.edgeInvoke();
  }
}