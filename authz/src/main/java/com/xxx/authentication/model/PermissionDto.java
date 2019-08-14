package com.xxx.authentication.model;

public class PermissionDto {
  private Integer id;

  private String name;

  private String perCode;

  public PermissionDto(Integer id, String name, String perCode) {
    this.id = id;
    this.name = name;
    this.perCode = perCode;
  }

  public PermissionDto() {
    super();
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

  public String getPerCode() {
    return perCode;
  }

  public void setPerCode(String perCode) {
    this.perCode = perCode == null ? null : perCode.trim();
  }
}