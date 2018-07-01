package com.tk.model;

import java.io.Serializable;

public class BasRegion  implements Serializable {
    private Integer id;

    private String title;

    private Integer pid;

    private Integer sort;

    private Integer level;

    private String longcode;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLongcode() {
        return longcode;
    }

    public void setLongcode(String longcode) {
        this.longcode = longcode == null ? null : longcode.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}