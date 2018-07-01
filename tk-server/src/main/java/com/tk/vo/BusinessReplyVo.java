package com.tk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/16.
 */
public class BusinessReplyVo implements Serializable {

    public Long id;

    public Long uid;

    public UserVo userVo;

    public Long bid;

    public Integer totalscore;

    public Integer serverscore;

    public Integer envirscore;

    public Date createdate;

    public String content;

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
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


    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
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
        this.content = content;
    }
}
