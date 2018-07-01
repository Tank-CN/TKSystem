package com.tk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 * 
 */
public class MsgVo implements Serializable{
    /**
	 * Description: 
	 */
	private static final long serialVersionUID = -7840986409947252484L;
	private String kinds;
    private Date date;
    private String content;
    private Integer count;

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
