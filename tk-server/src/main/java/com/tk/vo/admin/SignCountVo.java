package com.tk.vo.admin;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/18.
 */
public class SignCountVo implements Serializable {
    public Long aid;
    public String atitle;
    public Integer counts;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
