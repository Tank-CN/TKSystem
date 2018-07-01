package com.tk.model;

import java.util.ArrayList;
import java.util.List;

public class SysDictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDictionaryExample() {
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

        public Criteria andIidIsNull() {
            addCriterion("IID is null");
            return (Criteria) this;
        }

        public Criteria andIidIsNotNull() {
            addCriterion("IID is not null");
            return (Criteria) this;
        }

        public Criteria andIidEqualTo(Long value) {
            addCriterion("IID =", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotEqualTo(Long value) {
            addCriterion("IID <>", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThan(Long value) {
            addCriterion("IID >", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThanOrEqualTo(Long value) {
            addCriterion("IID >=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThan(Long value) {
            addCriterion("IID <", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThanOrEqualTo(Long value) {
            addCriterion("IID <=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidIn(List<Long> values) {
            addCriterion("IID in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotIn(List<Long> values) {
            addCriterion("IID not in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidBetween(Long value1, Long value2) {
            addCriterion("IID between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotBetween(Long value1, Long value2) {
            addCriterion("IID not between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andCidIsNull() {
            addCriterion("CID is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("CID is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(String value) {
            addCriterion("CID =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(String value) {
            addCriterion("CID <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(String value) {
            addCriterion("CID >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(String value) {
            addCriterion("CID >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(String value) {
            addCriterion("CID <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(String value) {
            addCriterion("CID <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLike(String value) {
            addCriterion("CID like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotLike(String value) {
            addCriterion("CID not like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<String> values) {
            addCriterion("CID in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<String> values) {
            addCriterion("CID not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(String value1, String value2) {
            addCriterion("CID between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(String value1, String value2) {
            addCriterion("CID not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("PID is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PID is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("PID =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("PID <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("PID >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("PID >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("PID <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("PID <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("PID in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("PID not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("PID between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("PID not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andSnIsNull() {
            addCriterion("SN is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("SN is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("SN =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("SN <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("SN >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("SN >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("SN <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("SN <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("SN like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("SN not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("SN in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("SN not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("SN between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("SN not between", value1, value2, "sn");
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

        public Criteria andAliasIsNull() {
            addCriterion("ALIAS is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("ALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("ALIAS =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("ALIAS <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("ALIAS >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("ALIAS >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("ALIAS <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("ALIAS <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("ALIAS like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("ALIAS not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("ALIAS in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("ALIAS not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("ALIAS between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("ALIAS not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andIdxIsNull() {
            addCriterion("IDX is null");
            return (Criteria) this;
        }

        public Criteria andIdxIsNotNull() {
            addCriterion("IDX is not null");
            return (Criteria) this;
        }

        public Criteria andIdxEqualTo(String value) {
            addCriterion("IDX =", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotEqualTo(String value) {
            addCriterion("IDX <>", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThan(String value) {
            addCriterion("IDX >", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThanOrEqualTo(String value) {
            addCriterion("IDX >=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThan(String value) {
            addCriterion("IDX <", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThanOrEqualTo(String value) {
            addCriterion("IDX <=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLike(String value) {
            addCriterion("IDX like", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotLike(String value) {
            addCriterion("IDX not like", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxIn(List<String> values) {
            addCriterion("IDX in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotIn(List<String> values) {
            addCriterion("IDX not in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxBetween(String value1, String value2) {
            addCriterion("IDX between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotBetween(String value1, String value2) {
            addCriterion("IDX not between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("SORT is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("SORT is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Short value) {
            addCriterion("SORT =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Short value) {
            addCriterion("SORT <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Short value) {
            addCriterion("SORT >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Short value) {
            addCriterion("SORT >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Short value) {
            addCriterion("SORT <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Short value) {
            addCriterion("SORT <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Short> values) {
            addCriterion("SORT in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Short> values) {
            addCriterion("SORT not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Short value1, Short value2) {
            addCriterion("SORT between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Short value1, Short value2) {
            addCriterion("SORT not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andGbcodeIsNull() {
            addCriterion("GBCODE is null");
            return (Criteria) this;
        }

        public Criteria andGbcodeIsNotNull() {
            addCriterion("GBCODE is not null");
            return (Criteria) this;
        }

        public Criteria andGbcodeEqualTo(String value) {
            addCriterion("GBCODE =", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeNotEqualTo(String value) {
            addCriterion("GBCODE <>", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeGreaterThan(String value) {
            addCriterion("GBCODE >", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeGreaterThanOrEqualTo(String value) {
            addCriterion("GBCODE >=", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeLessThan(String value) {
            addCriterion("GBCODE <", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeLessThanOrEqualTo(String value) {
            addCriterion("GBCODE <=", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeLike(String value) {
            addCriterion("GBCODE like", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeNotLike(String value) {
            addCriterion("GBCODE not like", value, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeIn(List<String> values) {
            addCriterion("GBCODE in", values, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeNotIn(List<String> values) {
            addCriterion("GBCODE not in", values, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeBetween(String value1, String value2) {
            addCriterion("GBCODE between", value1, value2, "gbcode");
            return (Criteria) this;
        }

        public Criteria andGbcodeNotBetween(String value1, String value2) {
            addCriterion("GBCODE not between", value1, value2, "gbcode");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNull() {
            addCriterion("ISOCODE is null");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNotNull() {
            addCriterion("ISOCODE is not null");
            return (Criteria) this;
        }

        public Criteria andIsocodeEqualTo(String value) {
            addCriterion("ISOCODE =", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotEqualTo(String value) {
            addCriterion("ISOCODE <>", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThan(String value) {
            addCriterion("ISOCODE >", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThanOrEqualTo(String value) {
            addCriterion("ISOCODE >=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThan(String value) {
            addCriterion("ISOCODE <", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThanOrEqualTo(String value) {
            addCriterion("ISOCODE <=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLike(String value) {
            addCriterion("ISOCODE like", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotLike(String value) {
            addCriterion("ISOCODE not like", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeIn(List<String> values) {
            addCriterion("ISOCODE in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotIn(List<String> values) {
            addCriterion("ISOCODE not in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeBetween(String value1, String value2) {
            addCriterion("ISOCODE between", value1, value2, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotBetween(String value1, String value2) {
            addCriterion("ISOCODE not between", value1, value2, "isocode");
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

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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