package com.tk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserInfoExample() {
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

        public Criteria andRegipIsNull() {
            addCriterion("REGIP is null");
            return (Criteria) this;
        }

        public Criteria andRegipIsNotNull() {
            addCriterion("REGIP is not null");
            return (Criteria) this;
        }

        public Criteria andRegipEqualTo(String value) {
            addCriterion("REGIP =", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotEqualTo(String value) {
            addCriterion("REGIP <>", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThan(String value) {
            addCriterion("REGIP >", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThanOrEqualTo(String value) {
            addCriterion("REGIP >=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThan(String value) {
            addCriterion("REGIP <", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThanOrEqualTo(String value) {
            addCriterion("REGIP <=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLike(String value) {
            addCriterion("REGIP like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotLike(String value) {
            addCriterion("REGIP not like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipIn(List<String> values) {
            addCriterion("REGIP in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotIn(List<String> values) {
            addCriterion("REGIP not in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipBetween(String value1, String value2) {
            addCriterion("REGIP between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotBetween(String value1, String value2) {
            addCriterion("REGIP not between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNull() {
            addCriterion("REGDATE is null");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNotNull() {
            addCriterion("REGDATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegdateEqualTo(Date value) {
            addCriterion("REGDATE =", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotEqualTo(Date value) {
            addCriterion("REGDATE <>", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThan(Date value) {
            addCriterion("REGDATE >", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThanOrEqualTo(Date value) {
            addCriterion("REGDATE >=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThan(Date value) {
            addCriterion("REGDATE <", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThanOrEqualTo(Date value) {
            addCriterion("REGDATE <=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateIn(List<Date> values) {
            addCriterion("REGDATE in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotIn(List<Date> values) {
            addCriterion("REGDATE not in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateBetween(Date value1, Date value2) {
            addCriterion("REGDATE between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotBetween(Date value1, Date value2) {
            addCriterion("REGDATE not between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateIsNull() {
            addCriterion("LASTLOGDATE is null");
            return (Criteria) this;
        }

        public Criteria andLastlogdateIsNotNull() {
            addCriterion("LASTLOGDATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogdateEqualTo(Date value) {
            addCriterion("LASTLOGDATE =", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateNotEqualTo(Date value) {
            addCriterion("LASTLOGDATE <>", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateGreaterThan(Date value) {
            addCriterion("LASTLOGDATE >", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateGreaterThanOrEqualTo(Date value) {
            addCriterion("LASTLOGDATE >=", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateLessThan(Date value) {
            addCriterion("LASTLOGDATE <", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateLessThanOrEqualTo(Date value) {
            addCriterion("LASTLOGDATE <=", value, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateIn(List<Date> values) {
            addCriterion("LASTLOGDATE in", values, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateNotIn(List<Date> values) {
            addCriterion("LASTLOGDATE not in", values, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateBetween(Date value1, Date value2) {
            addCriterion("LASTLOGDATE between", value1, value2, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogdateNotBetween(Date value1, Date value2) {
            addCriterion("LASTLOGDATE not between", value1, value2, "lastlogdate");
            return (Criteria) this;
        }

        public Criteria andLastlogipIsNull() {
            addCriterion("LASTLOGIP is null");
            return (Criteria) this;
        }

        public Criteria andLastlogipIsNotNull() {
            addCriterion("LASTLOGIP is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogipEqualTo(String value) {
            addCriterion("LASTLOGIP =", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipNotEqualTo(String value) {
            addCriterion("LASTLOGIP <>", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipGreaterThan(String value) {
            addCriterion("LASTLOGIP >", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipGreaterThanOrEqualTo(String value) {
            addCriterion("LASTLOGIP >=", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipLessThan(String value) {
            addCriterion("LASTLOGIP <", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipLessThanOrEqualTo(String value) {
            addCriterion("LASTLOGIP <=", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipLike(String value) {
            addCriterion("LASTLOGIP like", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipNotLike(String value) {
            addCriterion("LASTLOGIP not like", value, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipIn(List<String> values) {
            addCriterion("LASTLOGIP in", values, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipNotIn(List<String> values) {
            addCriterion("LASTLOGIP not in", values, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipBetween(String value1, String value2) {
            addCriterion("LASTLOGIP between", value1, value2, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andLastlogipNotBetween(String value1, String value2) {
            addCriterion("LASTLOGIP not between", value1, value2, "lastlogip");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("OPENID is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("OPENID =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("OPENID <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("OPENID >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("OPENID >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("OPENID <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("OPENID <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("OPENID like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("OPENID not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("OPENID in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("OPENID not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("OPENID between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("OPENID not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("IDCARD is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("IDCARD is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("IDCARD =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("IDCARD <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("IDCARD >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("IDCARD >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("IDCARD <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("IDCARD <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("IDCARD like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("IDCARD not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("IDCARD in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("IDCARD not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("IDCARD between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("IDCARD not between", value1, value2, "idcard");
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