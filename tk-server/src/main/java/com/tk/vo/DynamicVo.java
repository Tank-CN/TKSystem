package com.tk.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 动态对象
 */
public class DynamicVo implements Serializable {
    public Long id;

    public Long uid;

    public UserVo userVo;

    public String title;

    public String content;

    public BigDecimal longitude;

    public BigDecimal latitude;

    public String address;

    public String imgurl;

    public Date createdate;

    public Integer replycount;

    public Integer likecount;

    public List<DynamicReplyVo> replyList;
    public List<DynamicLikeVo> likeVos;


    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public List<DynamicReplyVo> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<DynamicReplyVo> replyList) {
        this.replyList = replyList;
    }

    public List<DynamicLikeVo> getLikeVos() {
        return likeVos;
    }

    public void setLikeVos(List<DynamicLikeVo> likeVos) {
        this.likeVos = likeVos;
    }
}
