package com.tk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessReplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusinessReplyExample() {
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

        public Criteria andTotalscoreIsNull() {
            addCriterion("TOTALSCORE is null");
            return (Criteria) this;
        }

        public Criteria andTotalscoreIsNotNull() {
            addCriterion("TOTALSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalscoreEqualTo(Integer value) {
            addCriterion("TOTALSCORE =", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreNotEqualTo(Integer value) {
            addCriterion("TOTALSCORE <>", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreGreaterThan(Integer value) {
            addCriterion("TOTALSCORE >", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("TOTALSCORE >=", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreLessThan(Integer value) {
            addCriterion("TOTALSCORE <", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreLessThanOrEqualTo(Integer value) {
            addCriterion("TOTALSCORE <=", value, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreIn(List<Integer> values) {
            addCriterion("TOTALSCORE in", values, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreNotIn(List<Integer> values) {
            addCriterion("TOTALSCORE not in", values, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreBetween(Integer value1, Integer value2) {
            addCriterion("TOTALSCORE between", value1, value2, "totalscore");
            return (Criteria) this;
        }

        public Criteria andTotalscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("TOTALSCORE not between", value1, value2, "totalscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreIsNull() {
            addCriterion("SERVERSCORE is null");
            return (Criteria) this;
        }

        public Criteria andServerscoreIsNotNull() {
            addCriterion("SERVERSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andServerscoreEqualTo(Integer value) {
            addCriterion("SERVERSCORE =", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreNotEqualTo(Integer value) {
            addCriterion("SERVERSCORE <>", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreGreaterThan(Integer value) {
            addCriterion("SERVERSCORE >", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("SERVERSCORE >=", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreLessThan(Integer value) {
            addCriterion("SERVERSCORE <", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreLessThanOrEqualTo(Integer value) {
            addCriterion("SERVERSCORE <=", value, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreIn(List<Integer> values) {
            addCriterion("SERVERSCORE in", values, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreNotIn(List<Integer> values) {
            addCriterion("SERVERSCORE not in", values, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreBetween(Integer value1, Integer value2) {
            addCriterion("SERVERSCORE between", value1, value2, "serverscore");
            return (Criteria) this;
        }

        public Criteria andServerscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("SERVERSCORE not between", value1, value2, "serverscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreIsNull() {
            addCriterion("ENVIRSCORE is null");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreIsNotNull() {
            addCriterion("ENVIRSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreEqualTo(Integer value) {
            addCriterion("ENVIRSCORE =", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreNotEqualTo(Integer value) {
            addCriterion("ENVIRSCORE <>", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreGreaterThan(Integer value) {
            addCriterion("ENVIRSCORE >", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("ENVIRSCORE >=", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreLessThan(Integer value) {
            addCriterion("ENVIRSCORE <", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreLessThanOrEqualTo(Integer value) {
            addCriterion("ENVIRSCORE <=", value, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreIn(List<Integer> values) {
            addCriterion("ENVIRSCORE in", values, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreNotIn(List<Integer> values) {
            addCriterion("ENVIRSCORE not in", values, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreBetween(Integer value1, Integer value2) {
            addCriterion("ENVIRSCORE between", value1, value2, "envirscore");
            return (Criteria) this;
        }

        public Criteria andEnvirscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("ENVIRSCORE not between", value1, value2, "envirscore");
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

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
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