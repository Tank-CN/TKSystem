package com.tk.vo;


import com.tk.model.SysModule;

import java.io.Serializable;
import java.util.List;

public class ModuleVo implements Serializable{

	private Integer id;

	private String title;

	private String intro;

	private String url;

	private Byte level;

	private String pcode;

	private String code;
	// 是否选择
	private Byte flag = 0;

	public List<SysModule> list;

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
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
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

	public List<SysModule> getList() {
		return list;
	}

	public void setList(List<SysModule> list) {
		this.list = list;
	}

	public Byte getFlag() {
		return flag;
	}

	public void setFlag(Byte flag) {
		this.flag = flag;
	}

}
