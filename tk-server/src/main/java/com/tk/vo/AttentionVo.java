package com.tk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AttentionVo implements Serializable {

    public UserVo userVo;
    public Date createdate;

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
