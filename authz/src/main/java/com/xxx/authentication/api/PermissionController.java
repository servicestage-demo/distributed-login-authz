package com.xxx.authentication.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;

import com.xxx.authentication.service.PermissionService;

/**
 * @Author wangqijun
 * @Date 14:27 2019-08-12
 **/
@RestSchema(schemaId = "permission")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PermissionController {

  @Resource
  PermissionService permissionService;


  @Path("/permission")
  @GET
  public Map<String, Boolean> verify(@QueryParam("account") String account,
      @QueryParam("permissions") List<String> permissions) {
    return permissionService.verifyPermissions(account, permissions);
  }
}
