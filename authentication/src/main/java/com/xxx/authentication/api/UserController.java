package com.xxx.authentication.api;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.xxx.authentication.model.UserDto;
import com.xxx.authentication.service.IUserService;


@RestSchema(schemaId = "user")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

  @Resource
  private IUserService iUserService;

  @Path("/login")
  @POST
  public Boolean login(@RequestBody UserDto userDto) {
    UserDto result=iUserService.getByAccount(userDto.getAccount());
    if (userDto.getPassword().equals(result.getPassword())){
      return true;
    }
    return false;
  }
  @Path("/login2")
  @GET
  public Boolean login2() {
    return false;
  }
}