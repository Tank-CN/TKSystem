package com.tk.easemob;

/**
 * Created by Administrator on 2016/10/6.
 */
public class EasemobService {


    private String appKey;
    private String appClientId;
    private String appClientSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppClientId() {
        return appClientId;
    }

    public void setAppClientId(String appClientId) {
        this.appClientId = appClientId;
    }

    public String getAppClientSecret() {
        return appClientSecret;
    }

    public void setAppClientSecret(String appClientSecret) {
        this.appClientSecret = appClientSecret;
    }


    public void initEasemobUtils() {
        EasemobUtils.setAPPKEY(this.appKey);
        EasemobUtils.setAppClientId(this.appClientId);
        EasemobUtils.setAppClientSecret(this.appClientSecret);
    }



    public void checkUser(String phone){
        EasemobUtils.checkUser(phone);
    }




}
