package com.tk.cache;

import com.tk.manage.BasUserDeviceManage;
import com.tk.model.BasUserDevice;
import com.tk.push.PushInfo;
import com.tk.push.impl.JPushService;
import com.tk.util.cache.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class DeviceCacheManage {

    @Autowired
    BasUserDeviceManage basUserDeviceManage;

    @Autowired
    JPushService jPushService;

    BaseCache<Long, BasUserDevice> deviceCache;

    public DeviceCacheManage() {
        deviceCache = new BaseCache<Long, BasUserDevice>(10000, 7 * 24 * 60 * 60);
    }

    /**
     * 更新或者插入设备信息 客户端登录之后每次启动会调用 (以后可以集成启动统计等功能)
     *
     * @param device
     */
    public void deviceInfo(BasUserDevice device) {
        BasUserDevice d = getDevice(device.getUid());
        if (null != d) {
            try {
                // 比对是否需要修改
                if (d.getApptype() != device.getApptype()
                        || !d.getDeviceId().equals(device.getDeviceId())
                        || d.getType() != device.getType()) {
                    // 设备变更，发送push消息-单点登录
                    // 先发送再更新信息
                    PushInfo pushInfo = new PushInfo();
                    pushInfo.setType(1);
                    pushInfo.setKinds(200);
                    pushInfo.setTitle("优亿佳");
                    pushInfo.setDescription("您的账号在其他设备登录,请重新登录!");
//					pushService.push(d, pushInfo);
//                  pushMsgService.sendInformation(d.getUid(), pushInfo.toJsonString());
                    jPushService.pushMsgSigle(d.getUid(),pushInfo);
                    // 更新新信息
                    basUserDeviceManage.update(device);
                    // 更新缓存
                    deviceCache.put(device.getUid(), device);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            basUserDeviceManage.save(device);
            //加入缓存
            deviceCache.put(device.getUid(), device);
        }
    }


    /**
     * 获取设备信息
     *
     * @return
     */
    private BasUserDevice getDevice(Long uid) {
        BasUserDevice d = deviceCache.get(uid);
        if (null == d) {
            d = basUserDeviceManage.getByUid(uid);
        }
        if (null != d) {
            deviceCache.put(uid, d);
        }
        return d;
    }


    /**
     * 退出（清空设备信息）
     *
     * @param uid
     * @return
     */
    public int logout(Long uid) {
        BasUserDevice d = getDevice(uid);
        if (null != d) {
            deviceCache.remove(uid);
            return basUserDeviceManage.deleteByUid(uid);
        } else {
            return 1;
        }
    }


    public void replace(Long uid, BasUserDevice d) {
        deviceCache.put(uid, d);
    }
}
