package com.tk.push;

import com.tk.model.BasUserDevice;

/**
 * 消息基类
 */
public interface PushMsgBaseService {


    /**
     * 验证
     * @param uid
     * @return
     */
    public BasUserDevice verification(Long uid);

    /**
     * 向单个用户发送消息
     * @param uid
     * @param pushInfo 消息体
     */
    public boolean pushAlertSigle(Long uid, PushInfo pushInfo);


    /**
     * 向单个用户发送消息
     * @param uid
     * @param pushInfo 消息体
     */
    public boolean pushMsgSigle(Long uid, PushInfo pushInfo);

    /**
     * 向批量用户发送消息
     * @param uids
     * @param pushInfo 消息体
     */
    public void pushBatch(Long[] uids, PushInfo pushInfo) ;

    /**
     * 向所有用户发送消息
     * @param pushInfo
     * @param hisids 医院IDS
     */
    public void pushAll(PushInfo pushInfo,Long[] hisids) ;

    /**
     * 向所有居民发送消息
     * @param pushInfo
     */
    public void pushPub (PushInfo pushInfo);

    /**
     * 发送消息给所有医生
     * @param pushInfo
     * @param hisids 医院ids
     */
    public void pushDoc(PushInfo pushInfo,Long[] hisids);


}
