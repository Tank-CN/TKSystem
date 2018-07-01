package com.tk.model;

import java.io.Serializable;

public class SysSetting  implements Serializable {

	private Long id;

    private String code;

    private String text;

    private Byte type;

    private String memo;

    private String module;//模块
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
    
}