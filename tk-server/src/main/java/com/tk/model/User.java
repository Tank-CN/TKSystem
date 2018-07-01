package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String token;

    private String mobile;

    private String nickname;

    private Byte sexcode;

    private Date birthdate;

    private String header;

    private Byte vip;

    private Date viptime;

    private String info;

    private Byte flag;

    private Short regtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getSexcode() {
        return sexcode;
    }

    public void setSexcode(Byte sexcode) {
        this.sexcode = sexcode;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public Byte getVip() {
        return vip;
    }

    public void setVip(Byte vip) {
        this.vip = vip;
    }

    public Date getViptime() {
        return viptime;
    }

    public void setViptime(Date viptime) {
        this.viptime = viptime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Short getRegtype() {
        return regtype;
    }

    public void setRegtype(Short regtype) {
        this.regtype = regtype;
    }
}