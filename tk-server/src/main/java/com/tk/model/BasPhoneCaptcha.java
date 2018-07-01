package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class BasPhoneCaptcha  implements Serializable {

	private String mobile;

    private String code;

    private Date createdate;
    
    public BasPhoneCaptcha(){
    	
    }
    
    public BasPhoneCaptcha(String mobile, String code, Date createdate){
        this.mobile = mobile;
        this.code = code;
        this.createdate = createdate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}