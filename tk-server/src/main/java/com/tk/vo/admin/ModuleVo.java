package com.tk.vo.admin;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModuleVo implements Serializable {

    private String title;
    private String code;

    public List<SubModuleVo> list;

    public ModuleVo(String code, String title) {
        this.title = title;
        this.code = code;
        this.list = new ArrayList<>();
    }

    public void addSub(SubModuleVo vo) {
        vo.setPcode(this.code);
        vo.setCode(this.code + this.list.size());
        this.list.add(vo);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SubModuleVo> getList() {
        return list;
    }

    public void setList(List<SubModuleVo> list) {
        this.list = list;
    }


}
