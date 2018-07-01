package com.tk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/16.
 */
public class TipVo implements Serializable {

    private Long id;

    private Long uid;

    private Long tid;

    private Byte type;

    private Date createdate;

    private Byte status;

    private String typetext;

    private String name;

    private String image;

    private String nickname;

    public String getTypetext() {
        return typetext;
    }

    public void setTypetext(String typetext) {
        this.typetext = typetext;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
