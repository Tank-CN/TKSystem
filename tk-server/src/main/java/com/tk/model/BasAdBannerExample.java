package com.tk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasAdBannerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasAdBannerExample() {
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

        public Criteria andBidIsNull() {
            addCriterion("BID is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("BID is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(Long value) {
            addCriterion("BID =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(Long value) {
            addCriterion("BID <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(Long value) {
            addCriterion("BID >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(Long value) {
            addCriterion("BID >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(Long value) {
            addCriterion("BID <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(Long value) {
            addCriterion("BID <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<Long> values) {
            addCriterion("BID in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<Long> values) {
            addCriterion("BID not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(Long value1, Long value2) {
            addCriterion("BID between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(Long value1, Long value2) {
            addCriterion("BID not between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
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

        public Criteria andPicurlIsNull() {
            addCriterion("PICURL is null");
            return (Criteria) this;
        }

        public Criteria andPicurlIsNotNull() {
            addCriterion("PICURL is not null");
            return (Criteria) this;
        }

        public Criteria andPicurlEqualTo(String value) {
            addCriterion("PICURL =", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotEqualTo(String value) {
            addCriterion("PICURL <>", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThan(String value) {
            addCriterion("PICURL >", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThanOrEqualTo(String value) {
            addCriterion("PICURL >=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThan(String value) {
            addCriterion("PICURL <", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThanOrEqualTo(String value) {
            addCriterion("PICURL <=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLike(String value) {
            addCriterion("PICURL like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotLike(String value) {
            addCriterion("PICURL not like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlIn(List<String> values) {
            addCriterion("PICURL in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotIn(List<String> values) {
            addCriterion("PICURL not in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlBetween(String value1, String value2) {
            addCriterion("PICURL between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotBetween(String value1, String value2) {
            addCriterion("PICURL not between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNull() {
            addCriterion("WEBURL is null");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNotNull() {
            addCriterion("WEBURL is not null");
            return (Criteria) this;
        }

        public Criteria andWeburlEqualTo(String value) {
            addCriterion("WEBURL =", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotEqualTo(String value) {
            addCriterion("WEBURL <>", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlGreaterThan(String value) {
            addCriterion("WEBURL >", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlGreaterThanOrEqualTo(String value) {
            addCriterion("WEBURL >=", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLessThan(String value) {
            addCriterion("WEBURL <", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLessThanOrEqualTo(String value) {
            addCriterion("WEBURL <=", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLike(String value) {
            addCriterion("WEBURL like", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotLike(String value) {
            addCriterion("WEBURL not like", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlIn(List<String> values) {
            addCriterion("WEBURL in", values, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotIn(List<String> values) {
            addCriterion("WEBURL not in", values, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlBetween(String value1, String value2) {
            addCriterion("WEBURL between", value1, value2, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotBetween(String value1, String value2) {
            addCriterion("WEBURL not between", value1, value2, "weburl");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Byte value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Byte value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Byte value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Byte value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Byte value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Byte> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Byte> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Byte value1, Byte value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
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

        public Criteria andLastmodifyuserIsNull() {
            addCriterion("LASTMODIFYUSER is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserIsNotNull() {
            addCriterion("LASTMODIFYUSER is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserEqualTo(Long value) {
            addCriterion("LASTMODIFYUSER =", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotEqualTo(Long value) {
            addCriterion("LASTMODIFYUSER <>", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserGreaterThan(Long value) {
            addCriterion("LASTMODIFYUSER >", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserGreaterThanOrEqualTo(Long value) {
            addCriterion("LASTMODIFYUSER >=", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserLessThan(Long value) {
            addCriterion("LASTMODIFYUSER <", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserLessThanOrEqualTo(Long value) {
            addCriterion("LASTMODIFYUSER <=", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserIn(List<Long> values) {
            addCriterion("LASTMODIFYUSER in", values, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotIn(List<Long> values) {
            addCriterion("LASTMODIFYUSER not in", values, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserBetween(Long value1, Long value2) {
            addCriterion("LASTMODIFYUSER between", value1, value2, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotBetween(Long value1, Long value2) {
            addCriterion("LASTMODIFYUSER not between", value1, value2, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIsNull() {
            addCriterion("LASTMODIFYDATE is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIsNotNull() {
            addCriterion("LASTMODIFYDATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateEqualTo(Date value) {
            addCriterion("LASTMODIFYDATE =", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotEqualTo(Date value) {
            addCriterion("LASTMODIFYDATE <>", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateGreaterThan(Date value) {
            addCriterion("LASTMODIFYDATE >", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("LASTMODIFYDATE >=", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateLessThan(Date value) {
            addCriterion("LASTMODIFYDATE <", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateLessThanOrEqualTo(Date value) {
            addCriterion("LASTMODIFYDATE <=", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIn(List<Date> values) {
            addCriterion("LASTMODIFYDATE in", values, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotIn(List<Date> values) {
            addCriterion("LASTMODIFYDATE not in", values, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateBetween(Date value1, Date value2) {
            addCriterion("LASTMODIFYDATE between", value1, value2, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotBetween(Date value1, Date value2) {
            addCriterion("LASTMODIFYDATE not between", value1, value2, "lastmodifydate");
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