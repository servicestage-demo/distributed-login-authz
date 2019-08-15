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

import com.xxx.authentication.service.RoleService;

/**
 * @Author wangqijun
 * @Date 09:03 2019-08-11
 **/
@RestSchema(schemaId = "role")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RoleController {

  @Resource
  RoleService roleService;

  @Path("/role")
  @GET
  public Map<String, Boolean> verifyRole(@QueryParam("account") String account,
      @QueryParam("roles") List<String> roles) {
    return roleService.verifyRole(account, roles);
  }

//  @Path("/role")
//  @GET
//  public List<RoleDto> findRoleByAccount(@QueryParam("account") String account) {
//    UserDto userDto = new UserDto();
//    userDto.setAccount(account);
//    List<RoleDto> roleList = roleService.findRoleByUser(userDto);
//    for (RoleDto roleDto : roleList) {
//      List<PermissionDto> permissionList = permissionService.findPermissionByRole(roleDto);
//      roleDto.setPermissionList(permissionList);
//    }
//    return roleList;
//  }
}
