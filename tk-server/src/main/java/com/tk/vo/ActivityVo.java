package com.tk.vo;

import com.tk.model.Activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ActivityVo extends Activity implements Serializable{
    //报名数
    public int signCount;
    //是否已参加
    public int isSign=0;

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
    }
}
