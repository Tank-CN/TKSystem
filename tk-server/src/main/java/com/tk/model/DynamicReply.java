package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class DynamicReply implements Serializable {
    private Long id;

    private Long did;

    private Long uid;

    private String uname;

    private Long ruid;

    private String runame;

    private Byte status;

    private Date createdate;

    private Byte kinds;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Long getRuid() {
        return ruid;
    }

    public void setRuid(Long ruid) {
        this.ruid = ruid;
    }

    public String getRuname() {
        return runame;
    }

    public void setRuname(String runame) {
        this.runame = runame == null ? null : runame.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Byte getKinds() {
        return kinds;
    }

    public void setKinds(Byte kinds) {
        this.kinds = kinds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}