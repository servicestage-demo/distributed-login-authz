package com.xxx.authentication.model;

public class RolePermissionDto {
  private Integer id;

  private Integer roleId;

  private Integer permissionId;

  public RolePermissionDto(Integer id, Integer roleId, Integer permissionId) {
    this.id = id;
    this.roleId = roleId;
    this.permissionId = permissionId;
  }

  public RolePermissionDto() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public Integer getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
  }
}