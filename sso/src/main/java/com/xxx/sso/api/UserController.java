package com.xxx.sso.api;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import com.xxx.sso.constants.Constants;
import com.xxx.sso.model.UserDto;
import com.xxx.sso.service.UserService;
import com.xxx.sso.util.AesCipherUtil;
import com.xxx.sso.util.Base64ConvertUtil;
import com.xxx.sso.util.JwtUtil;


@RestSchema(schemaId = "user")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

  @Resource
  private UserService userService;

  @Path("/login")
  @POST
  public ResponseEntity<String> login(@RequestBody UserDto userDto) {
    UserDto result= userService.getByAccount(userDto.getAccount());
    MultiValueMap<String, String> headers =new HttpHeaders();
    if (result==null){
      //return new ResponseEntity<String>("can not find account.",headers,HttpStatus.UNAUTHORIZED);
      throw new InvocationException(401, "UNAUTHORIZED", "can not find account.");
    }
    try {
      String ç = AesCipherUtil.enCrypto(userDto.getPassword());
       if (AesCipherUtil.enCrypto(userDto.getPassword()).equals(result.getPassword())){
          String token = JwtUtil.sign(userDto.getAccount(), String.valueOf(System.currentTimeMillis()));
          headers.set(Constants.X_TOKEN,token);
          ResponseEntity<String> entity=new ResponseEntity<>(headers,HttpStatus.OK);
          return entity;
      }
    } catch (Exception e) {
      throw new InvocationException(500, e.getMessage(), e);
      //return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    throw new InvocationException(401, "UNAUTHORIZED", "account and password do not match.");
    //return new ResponseEntity<String>("account and password do not match.",HttpStatus.UNAUTHORIZED);
  }

}