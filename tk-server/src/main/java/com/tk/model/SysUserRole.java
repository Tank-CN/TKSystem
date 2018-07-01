package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class SysUserRole  implements Serializable {

	private Integer id;

    private Integer rid;

    private Long createuser;

    private Date createdate;

    private Long lastmodifyuser;

    private Date lastmodifydate;

    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Long getLastmodifyuser() {
        return lastmodifyuser;
    }

    public void setLastmodifyuser(Long lastmodifyuser) {
        this.lastmodifyuser = lastmodifyuser;
    }

    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}