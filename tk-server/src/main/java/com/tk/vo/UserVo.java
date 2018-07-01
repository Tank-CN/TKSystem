package com.tk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/2.
 */
public class UserVo implements Serializable {

    public Long id;

    public String nickname;

    public Byte sexcode;

    public Date birthdate;

    public String header;

    public String mobile;

    public Byte vip;

    public String info;
    //是否关注
    public boolean isAttention;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        this.header = header;
    }

    public Byte getVip() {
        return vip;
    }

    public void setVip(Byte vip) {
        this.vip = vip;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }
}
