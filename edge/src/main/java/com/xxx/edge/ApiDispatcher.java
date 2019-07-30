package com.xxx.edge;

import java.util.Map;

import org.apache.servicecomb.edge.core.AbstractEdgeDispatcher;
import org.apache.servicecomb.edge.core.EdgeInvocation;

import com.xxx.sso.constants.Constants;

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
        if (token != null) {
          this.invocation.addContext(Constants.X_TOKEN, token);
        } else {
          Cookie sessionCookie = context.getCookie(Constants.X_TOKEN);
          if (sessionCookie != null) {
            this.invocation.addContext(Constants.X_TOKEN, sessionCookie.getValue());
          }
        }
        this.invocation.addLocalContext("path", path);
      }
    };
    invoker.init(microserviceName, context, path, httpServerFilters);
    invoker.edgeInvoke();
  }
}