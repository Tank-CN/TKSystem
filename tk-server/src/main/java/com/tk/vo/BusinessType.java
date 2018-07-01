package com.tk.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/30.
 */
public class BusinessType implements Serializable {

    public long id;
    public String title;
    public long pid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
