package com.tk.push;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 激光推送消息体
 */
public class PushInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public String title;
    public String description;
    //默认为消息
    // 1 消息 2 通知 3 消息+事件
    public Integer kinds = 1;
    //预留
    public int type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKinds() {
        return kinds;
    }

    public void setKinds(Integer kinds) {
        this.kinds = kinds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toJsonString() {
        JSONObject ob = new JSONObject();
        ob.put("title", title);
        ob.put("description", description);
        ob.put("type", type);
        ob.put("kinds", kinds);
        return ob.toJSONString();
    }

}
