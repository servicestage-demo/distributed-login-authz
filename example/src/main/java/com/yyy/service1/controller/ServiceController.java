package com.yyy.service1.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xxx.authentication.client.RequiresPermissions;
import com.xxx.authentication.client.RequiresRoles;

/**
 * @Author wangqijun
 * @Date 14:42 2019-07-23
 **/
@RestSchema(schemaId = "se")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceController {
  private static Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

  @Path("/ss")
  @GET
  @RequiresRoles("admin")//user:view
  public ResponseEntity<String> ss() {
    return new ResponseEntity("hello ", HttpStatus.OK);
  }

  @Path("/sw")
  @GET
  @RequiresPermissions("user:view")
  public ResponseEntity<String> sw() {
    return new ResponseEntity("hello ", HttpStatus.OK);
  }

  @Path("/sq")
  @GET
  @RequiresPermissions("user:view1")
  public ResponseEntity<String> sq() {
    return new ResponseEntity("hello ", HttpStatus.OK);
  }
}
