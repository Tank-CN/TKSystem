package com.tk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasAppVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasAppVersionExample() {
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

        public Criteria andAppnameIsNull() {
            addCriterion("APPNAME is null");
            return (Criteria) this;
        }

        public Criteria andAppnameIsNotNull() {
            addCriterion("APPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAppnameEqualTo(String value) {
            addCriterion("APPNAME =", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotEqualTo(String value) {
            addCriterion("APPNAME <>", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameGreaterThan(String value) {
            addCriterion("APPNAME >", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameGreaterThanOrEqualTo(String value) {
            addCriterion("APPNAME >=", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLessThan(String value) {
            addCriterion("APPNAME <", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLessThanOrEqualTo(String value) {
            addCriterion("APPNAME <=", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLike(String value) {
            addCriterion("APPNAME like", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotLike(String value) {
            addCriterion("APPNAME not like", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameIn(List<String> values) {
            addCriterion("APPNAME in", values, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotIn(List<String> values) {
            addCriterion("APPNAME not in", values, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameBetween(String value1, String value2) {
            addCriterion("APPNAME between", value1, value2, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotBetween(String value1, String value2) {
            addCriterion("APPNAME not between", value1, value2, "appname");
            return (Criteria) this;
        }

        public Criteria andAppcodeIsNull() {
            addCriterion("APPCODE is null");
            return (Criteria) this;
        }

        public Criteria andAppcodeIsNotNull() {
            addCriterion("APPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andAppcodeEqualTo(String value) {
            addCriterion("APPCODE =", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeNotEqualTo(String value) {
            addCriterion("APPCODE <>", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeGreaterThan(String value) {
            addCriterion("APPCODE >", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeGreaterThanOrEqualTo(String value) {
            addCriterion("APPCODE >=", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeLessThan(String value) {
            addCriterion("APPCODE <", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeLessThanOrEqualTo(String value) {
            addCriterion("APPCODE <=", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeLike(String value) {
            addCriterion("APPCODE like", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeNotLike(String value) {
            addCriterion("APPCODE not like", value, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeIn(List<String> values) {
            addCriterion("APPCODE in", values, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeNotIn(List<String> values) {
            addCriterion("APPCODE not in", values, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeBetween(String value1, String value2) {
            addCriterion("APPCODE between", value1, value2, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppcodeNotBetween(String value1, String value2) {
            addCriterion("APPCODE not between", value1, value2, "appcode");
            return (Criteria) this;
        }

        public Criteria andAppversionIsNull() {
            addCriterion("APPVERSION is null");
            return (Criteria) this;
        }

        public Criteria andAppversionIsNotNull() {
            addCriterion("APPVERSION is not null");
            return (Criteria) this;
        }

        public Criteria andAppversionEqualTo(Integer value) {
            addCriterion("APPVERSION =", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotEqualTo(Integer value) {
            addCriterion("APPVERSION <>", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionGreaterThan(Integer value) {
            addCriterion("APPVERSION >", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionGreaterThanOrEqualTo(Integer value) {
            addCriterion("APPVERSION >=", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionLessThan(Integer value) {
            addCriterion("APPVERSION <", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionLessThanOrEqualTo(Integer value) {
            addCriterion("APPVERSION <=", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionIn(List<Integer> values) {
            addCriterion("APPVERSION in", values, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotIn(List<Integer> values) {
            addCriterion("APPVERSION not in", values, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionBetween(Integer value1, Integer value2) {
            addCriterion("APPVERSION between", value1, value2, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotBetween(Integer value1, Integer value2) {
            addCriterion("APPVERSION not between", value1, value2, "appversion");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("DES is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("DES is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("DES =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("DES <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("DES >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("DES >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("DES <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("DES <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("DES like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("DES not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("DES in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("DES not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("DES between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("DES not between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNull() {
            addCriterion("APPURL is null");
            return (Criteria) this;
        }

        public Criteria andAppurlIsNotNull() {
            addCriterion("APPURL is not null");
            return (Criteria) this;
        }

        public Criteria andAppurlEqualTo(String value) {
            addCriterion("APPURL =", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotEqualTo(String value) {
            addCriterion("APPURL <>", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThan(String value) {
            addCriterion("APPURL >", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlGreaterThanOrEqualTo(String value) {
            addCriterion("APPURL >=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThan(String value) {
            addCriterion("APPURL <", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLessThanOrEqualTo(String value) {
            addCriterion("APPURL <=", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlLike(String value) {
            addCriterion("APPURL like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotLike(String value) {
            addCriterion("APPURL not like", value, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlIn(List<String> values) {
            addCriterion("APPURL in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotIn(List<String> values) {
            addCriterion("APPURL not in", values, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlBetween(String value1, String value2) {
            addCriterion("APPURL between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andAppurlNotBetween(String value1, String value2) {
            addCriterion("APPURL not between", value1, value2, "appurl");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CREATEDATE is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CREATEDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CREATEDATE =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CREATEDATE <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CREATEDATE >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CREATEDATE <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CREATEDATE in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CREATEDATE not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNull() {
            addCriterion("CREATEUSER is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNotNull() {
            addCriterion("CREATEUSER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserEqualTo(Long value) {
            addCriterion("CREATEUSER =", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotEqualTo(Long value) {
            addCriterion("CREATEUSER <>", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThan(Long value) {
            addCriterion("CREATEUSER >", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATEUSER >=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThan(Long value) {
            addCriterion("CREATEUSER <", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThanOrEqualTo(Long value) {
            addCriterion("CREATEUSER <=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserIn(List<Long> values) {
            addCriterion("CREATEUSER in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotIn(List<Long> values) {
            addCriterion("CREATEUSER not in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserBetween(Long value1, Long value2) {
            addCriterion("CREATEUSER between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotBetween(Long value1, Long value2) {
            addCriterion("CREATEUSER not between", value1, value2, "createuser");
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

        public Criteria andModifyuserIsNull() {
            addCriterion("MODIFYUSER is null");
            return (Criteria) this;
        }

        public Criteria andModifyuserIsNotNull() {
            addCriterion("MODIFYUSER is not null");
            return (Criteria) this;
        }

        public Criteria andModifyuserEqualTo(Long value) {
            addCriterion("MODIFYUSER =", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserNotEqualTo(Long value) {
            addCriterion("MODIFYUSER <>", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserGreaterThan(Long value) {
            addCriterion("MODIFYUSER >", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFYUSER >=", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserLessThan(Long value) {
            addCriterion("MODIFYUSER <", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserLessThanOrEqualTo(Long value) {
            addCriterion("MODIFYUSER <=", value, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserIn(List<Long> values) {
            addCriterion("MODIFYUSER in", values, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserNotIn(List<Long> values) {
            addCriterion("MODIFYUSER not in", values, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserBetween(Long value1, Long value2) {
            addCriterion("MODIFYUSER between", value1, value2, "modifyuser");
            return (Criteria) this;
        }

        public Criteria andModifyuserNotBetween(Long value1, Long value2) {
            addCriterion("MODIFYUSER not between", value1, value2, "modifyuser");
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