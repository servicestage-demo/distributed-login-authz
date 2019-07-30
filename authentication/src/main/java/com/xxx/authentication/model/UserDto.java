package com.xxx.authentication.model;

import java.util.Date;

public class UserDto {
    private Integer id;

    private String account;

    private String password;

    private String username;

    private Date regTime;

    public UserDto(Integer id, String account, String password, String username, Date regTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.username = username;
        this.regTime = regTime;
    }

    public UserDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}