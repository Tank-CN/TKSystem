package com.tk.image;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Image implements Serializable {

    //格式
    public List<ImageInfo> sizes;


    public List<ImageInfo> getSizes() {
        return sizes;
    }

    public void setSizes(List<ImageInfo> sizes) {
        this.sizes = sizes;
    }
}