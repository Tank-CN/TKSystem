package com.tk.image;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/16.
 */
public class ImageInfo implements Serializable{

    //格式
    public int w;
    public int h;


    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
