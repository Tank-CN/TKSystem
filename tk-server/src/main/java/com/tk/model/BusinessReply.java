package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class BusinessReply implements Serializable {
    private Long id;

    private Long bid;

    private Long uid;

    private Byte flag;

    private Integer totalscore;

    private Integer serverscore;

    private Integer envirscore;

    private Date createdate;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public Integer getServerscore() {
        return serverscore;
    }

    public void setServerscore(Integer serverscore) {
        this.serverscore = serverscore;
    }

    public Integer getEnvirscore() {
        return envirscore;
    }

    public void setEnvirscore(Integer envirscore) {
        this.envirscore = envirscore;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}