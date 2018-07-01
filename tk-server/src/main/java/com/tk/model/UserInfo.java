package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private Long id;

    private Long uid;

    private String regip;

    private Date regdate;

    private Date lastlogdate;

    private String lastlogip;

    private String openid;

    private String email;

    private String idcard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getLastlogdate() {
        return lastlogdate;
    }

    public void setLastlogdate(Date lastlogdate) {
        this.lastlogdate = lastlogdate;
    }

    public String getLastlogip() {
        return lastlogip;
    }

    public void setLastlogip(String lastlogip) {
        this.lastlogip = lastlogip == null ? null : lastlogip.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }
}