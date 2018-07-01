package com.tk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasUserDeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasUserDeviceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("UID is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("UID is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("UID =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("UID <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("UID >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("UID >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("UID <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("UID <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("UID in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("UID not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("UID between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("UID not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andApptypeIsNull() {
            addCriterion("APPTYPE is null");
            return (Criteria) this;
        }

        public Criteria andApptypeIsNotNull() {
            addCriterion("APPTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andApptypeEqualTo(Byte value) {
            addCriterion("APPTYPE =", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotEqualTo(Byte value) {
            addCriterion("APPTYPE <>", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeGreaterThan(Byte value) {
            addCriterion("APPTYPE >", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("APPTYPE >=", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeLessThan(Byte value) {
            addCriterion("APPTYPE <", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeLessThanOrEqualTo(Byte value) {
            addCriterion("APPTYPE <=", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeIn(List<Byte> values) {
            addCriterion("APPTYPE in", values, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotIn(List<Byte> values) {
            addCriterion("APPTYPE not in", values, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeBetween(Byte value1, Byte value2) {
            addCriterion("APPTYPE between", value1, value2, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotBetween(Byte value1, Byte value2) {
            addCriterion("APPTYPE not between", value1, value2, "apptype");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("DEVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("DEVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(String value) {
            addCriterion("DEVICE_ID =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(String value) {
            addCriterion("DEVICE_ID <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(String value) {
            addCriterion("DEVICE_ID >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_ID >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(String value) {
            addCriterion("DEVICE_ID <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_ID <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLike(String value) {
            addCriterion("DEVICE_ID like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotLike(String value) {
            addCriterion("DEVICE_ID not like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<String> values) {
            addCriterion("DEVICE_ID in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<String> values) {
            addCriterion("DEVICE_ID not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(String value1, String value2) {
            addCriterion("DEVICE_ID between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(String value1, String value2) {
            addCriterion("DEVICE_ID not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andUniquecodeIsNull() {
            addCriterion("UNIQUECODE is null");
            return (Criteria) this;
        }

        public Criteria andUniquecodeIsNotNull() {
            addCriterion("UNIQUECODE is not null");
            return (Criteria) this;
        }

        public Criteria andUniquecodeEqualTo(String value) {
            addCriterion("UNIQUECODE =", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeNotEqualTo(String value) {
            addCriterion("UNIQUECODE <>", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeGreaterThan(String value) {
            addCriterion("UNIQUECODE >", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeGreaterThanOrEqualTo(String value) {
            addCriterion("UNIQUECODE >=", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeLessThan(String value) {
            addCriterion("UNIQUECODE <", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeLessThanOrEqualTo(String value) {
            addCriterion("UNIQUECODE <=", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeLike(String value) {
            addCriterion("UNIQUECODE like", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeNotLike(String value) {
            addCriterion("UNIQUECODE not like", value, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeIn(List<String> values) {
            addCriterion("UNIQUECODE in", values, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeNotIn(List<String> values) {
            addCriterion("UNIQUECODE not in", values, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeBetween(String value1, String value2) {
            addCriterion("UNIQUECODE between", value1, value2, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecodeNotBetween(String value1, String value2) {
            addCriterion("UNIQUECODE not between", value1, value2, "uniquecode");
            return (Criteria) this;
        }

        public Criteria andUniquecode1IsNull() {
            addCriterion("UNIQUECODE1 is null");
            return (Criteria) this;
        }

        public Criteria andUniquecode1IsNotNull() {
            addCriterion("UNIQUECODE1 is not null");
            return (Criteria) this;
        }

        public Criteria andUniquecode1EqualTo(String value) {
            addCriterion("UNIQUECODE1 =", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1NotEqualTo(String value) {
            addCriterion("UNIQUECODE1 <>", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1GreaterThan(String value) {
            addCriterion("UNIQUECODE1 >", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1GreaterThanOrEqualTo(String value) {
            addCriterion("UNIQUECODE1 >=", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1LessThan(String value) {
            addCriterion("UNIQUECODE1 <", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1LessThanOrEqualTo(String value) {
            addCriterion("UNIQUECODE1 <=", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1Like(String value) {
            addCriterion("UNIQUECODE1 like", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1NotLike(String value) {
            addCriterion("UNIQUECODE1 not like", value, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1In(List<String> values) {
            addCriterion("UNIQUECODE1 in", values, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1NotIn(List<String> values) {
            addCriterion("UNIQUECODE1 not in", values, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1Between(String value1, String value2) {
            addCriterion("UNIQUECODE1 between", value1, value2, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andUniquecode1NotBetween(String value1, String value2) {
            addCriterion("UNIQUECODE1 not between", value1, value2, "uniquecode1");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("DEVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("DEVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("DEVICE_NAME =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("DEVICE_NAME <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("DEVICE_NAME >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("DEVICE_NAME <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("DEVICE_NAME like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("DEVICE_NAME not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("DEVICE_NAME in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("DEVICE_NAME not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameIsNull() {
            addCriterion("DEVICE_SYS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameIsNotNull() {
            addCriterion("DEVICE_SYS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameEqualTo(String value) {
            addCriterion("DEVICE_SYS_NAME =", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameNotEqualTo(String value) {
            addCriterion("DEVICE_SYS_NAME <>", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameGreaterThan(String value) {
            addCriterion("DEVICE_SYS_NAME >", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_SYS_NAME >=", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameLessThan(String value) {
            addCriterion("DEVICE_SYS_NAME <", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_SYS_NAME <=", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameLike(String value) {
            addCriterion("DEVICE_SYS_NAME like", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameNotLike(String value) {
            addCriterion("DEVICE_SYS_NAME not like", value, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameIn(List<String> values) {
            addCriterion("DEVICE_SYS_NAME in", values, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameNotIn(List<String> values) {
            addCriterion("DEVICE_SYS_NAME not in", values, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameBetween(String value1, String value2) {
            addCriterion("DEVICE_SYS_NAME between", value1, value2, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysNameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_SYS_NAME not between", value1, value2, "deviceSysName");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionIsNull() {
            addCriterion("DEVICE_SYS_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionIsNotNull() {
            addCriterion("DEVICE_SYS_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionEqualTo(String value) {
            addCriterion("DEVICE_SYS_VERSION =", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionNotEqualTo(String value) {
            addCriterion("DEVICE_SYS_VERSION <>", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionGreaterThan(String value) {
            addCriterion("DEVICE_SYS_VERSION >", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_SYS_VERSION >=", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionLessThan(String value) {
            addCriterion("DEVICE_SYS_VERSION <", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_SYS_VERSION <=", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionLike(String value) {
            addCriterion("DEVICE_SYS_VERSION like", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionNotLike(String value) {
            addCriterion("DEVICE_SYS_VERSION not like", value, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionIn(List<String> values) {
            addCriterion("DEVICE_SYS_VERSION in", values, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionNotIn(List<String> values) {
            addCriterion("DEVICE_SYS_VERSION not in", values, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionBetween(String value1, String value2) {
            addCriterion("DEVICE_SYS_VERSION between", value1, value2, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceSysVersionNotBetween(String value1, String value2) {
            addCriterion("DEVICE_SYS_VERSION not between", value1, value2, "deviceSysVersion");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNull() {
            addCriterion("PHONE_MODEL is null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNotNull() {
            addCriterion("PHONE_MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelEqualTo(String value) {
            addCriterion("PHONE_MODEL =", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotEqualTo(String value) {
            addCriterion("PHONE_MODEL <>", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThan(String value) {
            addCriterion("PHONE_MODEL >", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_MODEL >=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThan(String value) {
            addCriterion("PHONE_MODEL <", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThanOrEqualTo(String value) {
            addCriterion("PHONE_MODEL <=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLike(String value) {
            addCriterion("PHONE_MODEL like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotLike(String value) {
            addCriterion("PHONE_MODEL not like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIn(List<String> values) {
            addCriterion("PHONE_MODEL in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotIn(List<String> values) {
            addCriterion("PHONE_MODEL not in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelBetween(String value1, String value2) {
            addCriterion("PHONE_MODEL between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotBetween(String value1, String value2) {
            addCriterion("PHONE_MODEL not between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andLangIsNull() {
            addCriterion("LANG is null");
            return (Criteria) this;
        }

        public Criteria andLangIsNotNull() {
            addCriterion("LANG is not null");
            return (Criteria) this;
        }

        public Criteria andLangEqualTo(String value) {
            addCriterion("LANG =", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotEqualTo(String value) {
            addCriterion("LANG <>", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThan(String value) {
            addCriterion("LANG >", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThanOrEqualTo(String value) {
            addCriterion("LANG >=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThan(String value) {
            addCriterion("LANG <", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThanOrEqualTo(String value) {
            addCriterion("LANG <=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLike(String value) {
            addCriterion("LANG like", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotLike(String value) {
            addCriterion("LANG not like", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangIn(List<String> values) {
            addCriterion("LANG in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotIn(List<String> values) {
            addCriterion("LANG not in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangBetween(String value1, String value2) {
            addCriterion("LANG between", value1, value2, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotBetween(String value1, String value2) {
            addCriterion("LANG not between", value1, value2, "lang");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("GMT_MODIFIED is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("GMT_MODIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("GMT_MODIFIED =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("GMT_MODIFIED >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("GMT_MODIFIED <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("GMT_MODIFIED in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("GMT_MODIFIED not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNull() {
            addCriterion("MODIFYDATE is null");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNotNull() {
            addCriterion("MODIFYDATE is not null");
            return (Criteria) this;
        }

        public Criteria andModifydateEqualTo(Date value) {
            addCriterion("MODIFYDATE =", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotEqualTo(Date value) {
            addCriterion("MODIFYDATE <>", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThan(Date value) {
            addCriterion("MODIFYDATE >", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFYDATE >=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThan(Date value) {
            addCriterion("MODIFYDATE <", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThanOrEqualTo(Date value) {
            addCriterion("MODIFYDATE <=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateIn(List<Date> values) {
            addCriterion("MODIFYDATE in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotIn(List<Date> values) {
            addCriterion("MODIFYDATE not in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateBetween(Date value1, Date value2) {
            addCriterion("MODIFYDATE between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotBetween(Date value1, Date value2) {
            addCriterion("MODIFYDATE not between", value1, value2, "modifydate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}