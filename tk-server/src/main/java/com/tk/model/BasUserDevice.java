package com.tk.model;

import java.io.Serializable;
import java.util.Date;

public class BasUserDevice implements Serializable {
    private Long id;

    private Long uid;

    private Byte apptype;

    private String deviceId;

    private String uniquecode;

    private String uniquecode1;

    private String deviceName;

    private String deviceSysName;

    private String deviceSysVersion;

    private String phoneModel;

    private String lang;

    private Byte type;

    private Date gmtModified;

    private Date modifydate;

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

    public Byte getApptype() {
        return apptype;
    }

    public void setApptype(Byte apptype) {
        this.apptype = apptype;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode == null ? null : uniquecode.trim();
    }

    public String getUniquecode1() {
        return uniquecode1;
    }

    public void setUniquecode1(String uniquecode1) {
        this.uniquecode1 = uniquecode1 == null ? null : uniquecode1.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceSysName() {
        return deviceSysName;
    }

    public void setDeviceSysName(String deviceSysName) {
        this.deviceSysName = deviceSysName == null ? null : deviceSysName.trim();
    }

    public String getDeviceSysVersion() {
        return deviceSysVersion;
    }

    public void setDeviceSysVersion(String deviceSysVersion) {
        this.deviceSysVersion = deviceSysVersion == null ? null : deviceSysVersion.trim();
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel == null ? null : phoneModel.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}