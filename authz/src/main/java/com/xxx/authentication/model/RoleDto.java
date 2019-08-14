package com.xxx.authentication.model;

import java.util.List;

public class RoleDto {
  private Integer id;

  private String name;

  private List<PermissionDto> permissionList;

  public RoleDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public RoleDto() {
    super();
  }

  public List<PermissionDto> getPermissionList() {
    return permissionList;
  }

  public void setPermissionList(List<PermissionDto> permissionList) {
    this.permissionList = permissionList;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }
}