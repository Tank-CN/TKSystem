package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class BasAppAdvise  implements Serializable {


	private Long id;

    private Long uid;

    private Byte kinds;

    private String content;

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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}