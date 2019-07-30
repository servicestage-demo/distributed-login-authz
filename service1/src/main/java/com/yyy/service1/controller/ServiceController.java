package com.yyy.service1.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import com.xxx.authentication.client.RequiresRoles;
import com.xxx.authentication.client.Response;

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
  @POST
  @RequiresRoles("444")
  public Response login(@RequestParam("dd") String username) {
    LOGGER.info("fdsjlfdjsklfkjdslfjkdsljflds");
      return new Response(200, "Login success", "");
  }
}
