package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class BasAttention implements Serializable {
    private Long id;

    private Long uid;

    private Long auid;

    private Byte type;

    private Date createdate;

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

    public Long getAuid() {
        return auid;
    }

    public void setAuid(Long auid) {
        this.auid = auid;
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
}