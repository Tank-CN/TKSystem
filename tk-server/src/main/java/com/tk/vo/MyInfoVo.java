package com.tk.vo;

import java.io.Serializable;

/**
 * 个人信息主页
 */
public class MyInfoVo implements Serializable{

    public int dynamicCount;
    public int businessCollectCount;
    public UserVo userVo;

    public int getDynamicCount() {
        return dynamicCount;
    }

    public void setDynamicCount(int dynamicCount) {
        this.dynamicCount = dynamicCount;
    }

    public int getBusinessCollectCount() {
        return businessCollectCount;
    }

    public void setBusinessCollectCount(int businessCollectCount) {
        this.businessCollectCount = businessCollectCount;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
