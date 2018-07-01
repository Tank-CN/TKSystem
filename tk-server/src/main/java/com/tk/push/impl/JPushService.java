package com.tk.push.impl;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.tk.manage.AppNoticeSettingManage;
import com.tk.manage.BasUserDeviceManage;
import com.tk.model.BasUserDevice;
import com.tk.push.PushInfo;
import com.tk.push.PushMsgBaseService;
import com.tk.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class JPushService implements PushMsgBaseService {

    @Autowired
    AppNoticeSettingManage appNoticeSettingManage;

    @Autowired
    BasUserDeviceManage basUserDeviceManage;

    @Autowired
    JPushClient jpushClient;


    public BasUserDevice verification(Long uid) {
        BasUserDevice deviceInfo = basUserDeviceManage.getByUid(uid);
        if (null == deviceInfo) {
            return null;
        }
        if (CommonUtils.isNull(deviceInfo.getType())) {
            return null;
        }
        if (CommonUtils.isNull(deviceInfo.getUniquecode())) {
            return null;
        }
//        AppNoticeSetting appNoticeSetting = appNoticeSettingManage.findByUid(deviceInfo.getUid());
//        if (appNoticeSetting != null) {//是否发生消息判断
//            if (StringUtils.hasText(appNoticeSetting.getText())) {//设置项有内容
//                JSONObject noticeSet = JSON.parseObject(appNoticeSetting.getText());
//                if (noticeSet.get("msg") != null && "0".equals(noticeSet.get("msg").toString())) {//0不发生,1发送
//                    return null;
//                }
//            }
//        }
        return deviceInfo;
    }


    @Override
    public boolean pushAlertSigle(Long uid, PushInfo pushInfo) {
        BasUserDevice deviceInfo = verification(uid);
        Notification notification = null;
        Platform platform = null;
        if (deviceInfo.getType().intValue() == 1) {
            //Android
            platform = Platform.android();
            notification = Notification.newBuilder().
                    addPlatformNotification(AndroidNotification.newBuilder()
                            .setAlert(pushInfo.getDescription())
                            .addExtra("kinds", pushInfo.getKinds())
                            .build())
                    .build();
        } else if (deviceInfo.getType().intValue() == 2) {
            //IOS
            platform = Platform.ios();
            notification = Notification.newBuilder()
                    .addPlatformNotification(IosNotification.newBuilder()
                            .setAlert(pushInfo.getDescription())
                            .setSound("default")
                            .autoBadge()
                            .addExtra("kinds", pushInfo.getKinds())
                            .build())
                    .build();
        }
        PushPayload.Builder builder = PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.registrationId(deviceInfo.getUniquecode())).setNotification(notification);
        PushPayload payload = builder.build();

        return send(payload);
    }

    @Override
    public boolean pushMsgSigle(Long uid, PushInfo pushInfo) {
        BasUserDevice deviceInfo = verification(uid);
        if (null != deviceInfo) {
            Message message = null;
            Platform platform = null;
            if (deviceInfo.getType().intValue() == 1) {
                //Android
                platform = Platform.android();
                message = Message.content(pushInfo.toJsonString());
            } else if (deviceInfo.getType().intValue() == 2) {
                //IOS
                platform = Platform.ios();
                message = Message.content(pushInfo.toJsonString());
            }
            PushPayload.Builder builder = PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.registrationId(deviceInfo.getUniquecode())).setMessage(message);
            PushPayload payload = builder.build();
            return send(payload);
        }
        return false;
    }

    @Override
    public void pushBatch(Long[] uids, PushInfo pushInfo) {

    }

    @Override
    public void pushAll(PushInfo pushInfo, Long[] hisids) {

    }

    @Override
    public void pushPub(PushInfo pushInfo) {

    }

    @Override
    public void pushDoc(PushInfo pushInfo, Long[] hisids) {

    }


    public boolean send(PushPayload payload) {
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("push  result : " + result);
            return true;
        } catch (APIConnectionException e) {
            e.printStackTrace();
            return false;
        } catch (APIRequestException e) {
            e.printStackTrace();
            return false;
        }
    }


}
