package com.tk.vo.admin;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/4.
 */
public class SubModuleVo implements Serializable {

    private String title;

    private String url;

    private String pcode;

    private String code;

    public SubModuleVo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public SubModuleVo(String code,String title, String url) {
        this.title = title;
        this.url = url;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
