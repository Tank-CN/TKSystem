package com.tk.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasBusinessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasBusinessExample() {
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

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("LONGITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("LONGITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("LONGITUDE >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("LONGITUDE <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("LONGITUDE in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("LONGITUDE not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONGITUDE between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONGITUDE not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("LATITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("LATITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("LATITUDE =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("LATITUDE <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("LATITUDE >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LATITUDE >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("LATITUDE <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LATITUDE <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("LATITUDE in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("LATITUDE not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LATITUDE between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LATITUDE not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("TYPEID is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("TYPEID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Long value) {
            addCriterion("TYPEID =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Long value) {
            addCriterion("TYPEID <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Long value) {
            addCriterion("TYPEID >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPEID >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Long value) {
            addCriterion("TYPEID <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Long value) {
            addCriterion("TYPEID <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Long> values) {
            addCriterion("TYPEID in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Long> values) {
            addCriterion("TYPEID not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Long value1, Long value2) {
            addCriterion("TYPEID between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Long value1, Long value2) {
            addCriterion("TYPEID not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNull() {
            addCriterion("TYPENAME is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("TYPENAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("TYPENAME =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("TYPENAME <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("TYPENAME >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPENAME >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("TYPENAME <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("TYPENAME <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("TYPENAME like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("TYPENAME not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("TYPENAME in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("TYPENAME not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("TYPENAME between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("TYPENAME not between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypeiidIsNull() {
            addCriterion("TYPEIID is null");
            return (Criteria) this;
        }

        public Criteria andTypeiidIsNotNull() {
            addCriterion("TYPEIID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeiidEqualTo(Long value) {
            addCriterion("TYPEIID =", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidNotEqualTo(Long value) {
            addCriterion("TYPEIID <>", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidGreaterThan(Long value) {
            addCriterion("TYPEIID >", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPEIID >=", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidLessThan(Long value) {
            addCriterion("TYPEIID <", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidLessThanOrEqualTo(Long value) {
            addCriterion("TYPEIID <=", value, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidIn(List<Long> values) {
            addCriterion("TYPEIID in", values, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidNotIn(List<Long> values) {
            addCriterion("TYPEIID not in", values, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidBetween(Long value1, Long value2) {
            addCriterion("TYPEIID between", value1, value2, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypeiidNotBetween(Long value1, Long value2) {
            addCriterion("TYPEIID not between", value1, value2, "typeiid");
            return (Criteria) this;
        }

        public Criteria andTypennameIsNull() {
            addCriterion("TYPENNAME is null");
            return (Criteria) this;
        }

        public Criteria andTypennameIsNotNull() {
            addCriterion("TYPENNAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypennameEqualTo(String value) {
            addCriterion("TYPENNAME =", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameNotEqualTo(String value) {
            addCriterion("TYPENNAME <>", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameGreaterThan(String value) {
            addCriterion("TYPENNAME >", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPENNAME >=", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameLessThan(String value) {
            addCriterion("TYPENNAME <", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameLessThanOrEqualTo(String value) {
            addCriterion("TYPENNAME <=", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameLike(String value) {
            addCriterion("TYPENNAME like", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameNotLike(String value) {
            addCriterion("TYPENNAME not like", value, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameIn(List<String> values) {
            addCriterion("TYPENNAME in", values, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameNotIn(List<String> values) {
            addCriterion("TYPENNAME not in", values, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameBetween(String value1, String value2) {
            addCriterion("TYPENNAME between", value1, value2, "typenname");
            return (Criteria) this;
        }

        public Criteria andTypennameNotBetween(String value1, String value2) {
            addCriterion("TYPENNAME not between", value1, value2, "typenname");
            return (Criteria) this;
        }

        public Criteria andProvinceidIsNull() {
            addCriterion("PROVINCEID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceidIsNotNull() {
            addCriterion("PROVINCEID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceidEqualTo(String value) {
            addCriterion("PROVINCEID =", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotEqualTo(String value) {
            addCriterion("PROVINCEID <>", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThan(String value) {
            addCriterion("PROVINCEID >", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCEID >=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThan(String value) {
            addCriterion("PROVINCEID <", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThanOrEqualTo(String value) {
            addCriterion("PROVINCEID <=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLike(String value) {
            addCriterion("PROVINCEID like", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotLike(String value) {
            addCriterion("PROVINCEID not like", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidIn(List<String> values) {
            addCriterion("PROVINCEID in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotIn(List<String> values) {
            addCriterion("PROVINCEID not in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidBetween(String value1, String value2) {
            addCriterion("PROVINCEID between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotBetween(String value1, String value2) {
            addCriterion("PROVINCEID not between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvincenameIsNull() {
            addCriterion("PROVINCENAME is null");
            return (Criteria) this;
        }

        public Criteria andProvincenameIsNotNull() {
            addCriterion("PROVINCENAME is not null");
            return (Criteria) this;
        }

        public Criteria andProvincenameEqualTo(String value) {
            addCriterion("PROVINCENAME =", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameNotEqualTo(String value) {
            addCriterion("PROVINCENAME <>", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameGreaterThan(String value) {
            addCriterion("PROVINCENAME >", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCENAME >=", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameLessThan(String value) {
            addCriterion("PROVINCENAME <", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameLessThanOrEqualTo(String value) {
            addCriterion("PROVINCENAME <=", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameLike(String value) {
            addCriterion("PROVINCENAME like", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameNotLike(String value) {
            addCriterion("PROVINCENAME not like", value, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameIn(List<String> values) {
            addCriterion("PROVINCENAME in", values, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameNotIn(List<String> values) {
            addCriterion("PROVINCENAME not in", values, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameBetween(String value1, String value2) {
            addCriterion("PROVINCENAME between", value1, value2, "provincename");
            return (Criteria) this;
        }

        public Criteria andProvincenameNotBetween(String value1, String value2) {
            addCriterion("PROVINCENAME not between", value1, value2, "provincename");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("CITYID is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("CITYID is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(String value) {
            addCriterion("CITYID =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(String value) {
            addCriterion("CITYID <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(String value) {
            addCriterion("CITYID >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(String value) {
            addCriterion("CITYID >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(String value) {
            addCriterion("CITYID <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(String value) {
            addCriterion("CITYID <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLike(String value) {
            addCriterion("CITYID like", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotLike(String value) {
            addCriterion("CITYID not like", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<String> values) {
            addCriterion("CITYID in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<String> values) {
            addCriterion("CITYID not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(String value1, String value2) {
            addCriterion("CITYID between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(String value1, String value2) {
            addCriterion("CITYID not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCitynameIsNull() {
            addCriterion("CITYNAME is null");
            return (Criteria) this;
        }

        public Criteria andCitynameIsNotNull() {
            addCriterion("CITYNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCitynameEqualTo(String value) {
            addCriterion("CITYNAME =", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotEqualTo(String value) {
            addCriterion("CITYNAME <>", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameGreaterThan(String value) {
            addCriterion("CITYNAME >", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameGreaterThanOrEqualTo(String value) {
            addCriterion("CITYNAME >=", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLessThan(String value) {
            addCriterion("CITYNAME <", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLessThanOrEqualTo(String value) {
            addCriterion("CITYNAME <=", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLike(String value) {
            addCriterion("CITYNAME like", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotLike(String value) {
            addCriterion("CITYNAME not like", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameIn(List<String> values) {
            addCriterion("CITYNAME in", values, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotIn(List<String> values) {
            addCriterion("CITYNAME not in", values, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameBetween(String value1, String value2) {
            addCriterion("CITYNAME between", value1, value2, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotBetween(String value1, String value2) {
            addCriterion("CITYNAME not between", value1, value2, "cityname");
            return (Criteria) this;
        }

        public Criteria andDistrictidIsNull() {
            addCriterion("DISTRICTID is null");
            return (Criteria) this;
        }

        public Criteria andDistrictidIsNotNull() {
            addCriterion("DISTRICTID is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictidEqualTo(String value) {
            addCriterion("DISTRICTID =", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotEqualTo(String value) {
            addCriterion("DISTRICTID <>", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidGreaterThan(String value) {
            addCriterion("DISTRICTID >", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICTID >=", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidLessThan(String value) {
            addCriterion("DISTRICTID <", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidLessThanOrEqualTo(String value) {
            addCriterion("DISTRICTID <=", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidLike(String value) {
            addCriterion("DISTRICTID like", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotLike(String value) {
            addCriterion("DISTRICTID not like", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidIn(List<String> values) {
            addCriterion("DISTRICTID in", values, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotIn(List<String> values) {
            addCriterion("DISTRICTID not in", values, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidBetween(String value1, String value2) {
            addCriterion("DISTRICTID between", value1, value2, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotBetween(String value1, String value2) {
            addCriterion("DISTRICTID not between", value1, value2, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictnameIsNull() {
            addCriterion("DISTRICTNAME is null");
            return (Criteria) this;
        }

        public Criteria andDistrictnameIsNotNull() {
            addCriterion("DISTRICTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictnameEqualTo(String value) {
            addCriterion("DISTRICTNAME =", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameNotEqualTo(String value) {
            addCriterion("DISTRICTNAME <>", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameGreaterThan(String value) {
            addCriterion("DISTRICTNAME >", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICTNAME >=", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameLessThan(String value) {
            addCriterion("DISTRICTNAME <", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameLessThanOrEqualTo(String value) {
            addCriterion("DISTRICTNAME <=", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameLike(String value) {
            addCriterion("DISTRICTNAME like", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameNotLike(String value) {
            addCriterion("DISTRICTNAME not like", value, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameIn(List<String> values) {
            addCriterion("DISTRICTNAME in", values, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameNotIn(List<String> values) {
            addCriterion("DISTRICTNAME not in", values, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameBetween(String value1, String value2) {
            addCriterion("DISTRICTNAME between", value1, value2, "districtname");
            return (Criteria) this;
        }

        public Criteria andDistrictnameNotBetween(String value1, String value2) {
            addCriterion("DISTRICTNAME not between", value1, value2, "districtname");
            return (Criteria) this;
        }

        public Criteria andStreetidIsNull() {
            addCriterion("STREETID is null");
            return (Criteria) this;
        }

        public Criteria andStreetidIsNotNull() {
            addCriterion("STREETID is not null");
            return (Criteria) this;
        }

        public Criteria andStreetidEqualTo(String value) {
            addCriterion("STREETID =", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidNotEqualTo(String value) {
            addCriterion("STREETID <>", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidGreaterThan(String value) {
            addCriterion("STREETID >", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidGreaterThanOrEqualTo(String value) {
            addCriterion("STREETID >=", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidLessThan(String value) {
            addCriterion("STREETID <", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidLessThanOrEqualTo(String value) {
            addCriterion("STREETID <=", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidLike(String value) {
            addCriterion("STREETID like", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidNotLike(String value) {
            addCriterion("STREETID not like", value, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidIn(List<String> values) {
            addCriterion("STREETID in", values, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidNotIn(List<String> values) {
            addCriterion("STREETID not in", values, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidBetween(String value1, String value2) {
            addCriterion("STREETID between", value1, value2, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetidNotBetween(String value1, String value2) {
            addCriterion("STREETID not between", value1, value2, "streetid");
            return (Criteria) this;
        }

        public Criteria andStreetnameIsNull() {
            addCriterion("STREETNAME is null");
            return (Criteria) this;
        }

        public Criteria andStreetnameIsNotNull() {
            addCriterion("STREETNAME is not null");
            return (Criteria) this;
        }

        public Criteria andStreetnameEqualTo(String value) {
            addCriterion("STREETNAME =", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameNotEqualTo(String value) {
            addCriterion("STREETNAME <>", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameGreaterThan(String value) {
            addCriterion("STREETNAME >", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameGreaterThanOrEqualTo(String value) {
            addCriterion("STREETNAME >=", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameLessThan(String value) {
            addCriterion("STREETNAME <", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameLessThanOrEqualTo(String value) {
            addCriterion("STREETNAME <=", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameLike(String value) {
            addCriterion("STREETNAME like", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameNotLike(String value) {
            addCriterion("STREETNAME not like", value, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameIn(List<String> values) {
            addCriterion("STREETNAME in", values, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameNotIn(List<String> values) {
            addCriterion("STREETNAME not in", values, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameBetween(String value1, String value2) {
            addCriterion("STREETNAME between", value1, value2, "streetname");
            return (Criteria) this;
        }

        public Criteria andStreetnameNotBetween(String value1, String value2) {
            addCriterion("STREETNAME not between", value1, value2, "streetname");
            return (Criteria) this;
        }

        public Criteria andVillageidIsNull() {
            addCriterion("VILLAGEID is null");
            return (Criteria) this;
        }

        public Criteria andVillageidIsNotNull() {
            addCriterion("VILLAGEID is not null");
            return (Criteria) this;
        }

        public Criteria andVillageidEqualTo(String value) {
            addCriterion("VILLAGEID =", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotEqualTo(String value) {
            addCriterion("VILLAGEID <>", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidGreaterThan(String value) {
            addCriterion("VILLAGEID >", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidGreaterThanOrEqualTo(String value) {
            addCriterion("VILLAGEID >=", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidLessThan(String value) {
            addCriterion("VILLAGEID <", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidLessThanOrEqualTo(String value) {
            addCriterion("VILLAGEID <=", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidLike(String value) {
            addCriterion("VILLAGEID like", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotLike(String value) {
            addCriterion("VILLAGEID not like", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidIn(List<String> values) {
            addCriterion("VILLAGEID in", values, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotIn(List<String> values) {
            addCriterion("VILLAGEID not in", values, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidBetween(String value1, String value2) {
            addCriterion("VILLAGEID between", value1, value2, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotBetween(String value1, String value2) {
            addCriterion("VILLAGEID not between", value1, value2, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillagenameIsNull() {
            addCriterion("VILLAGENAME is null");
            return (Criteria) this;
        }

        public Criteria andVillagenameIsNotNull() {
            addCriterion("VILLAGENAME is not null");
            return (Criteria) this;
        }

        public Criteria andVillagenameEqualTo(String value) {
            addCriterion("VILLAGENAME =", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameNotEqualTo(String value) {
            addCriterion("VILLAGENAME <>", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameGreaterThan(String value) {
            addCriterion("VILLAGENAME >", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameGreaterThanOrEqualTo(String value) {
            addCriterion("VILLAGENAME >=", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameLessThan(String value) {
            addCriterion("VILLAGENAME <", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameLessThanOrEqualTo(String value) {
            addCriterion("VILLAGENAME <=", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameLike(String value) {
            addCriterion("VILLAGENAME like", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameNotLike(String value) {
            addCriterion("VILLAGENAME not like", value, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameIn(List<String> values) {
            addCriterion("VILLAGENAME in", values, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameNotIn(List<String> values) {
            addCriterion("VILLAGENAME not in", values, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameBetween(String value1, String value2) {
            addCriterion("VILLAGENAME between", value1, value2, "villagename");
            return (Criteria) this;
        }

        public Criteria andVillagenameNotBetween(String value1, String value2) {
            addCriterion("VILLAGENAME not between", value1, value2, "villagename");
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

        public Criteria andInfoIsNull() {
            addCriterion("INFO is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("INFO is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("INFO =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("INFO <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("INFO >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("INFO >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("INFO <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("INFO <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("INFO like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("INFO not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("INFO in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("INFO not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("INFO between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("INFO not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andSpellIsNull() {
            addCriterion("SPELL is null");
            return (Criteria) this;
        }

        public Criteria andSpellIsNotNull() {
            addCriterion("SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andSpellEqualTo(String value) {
            addCriterion("SPELL =", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotEqualTo(String value) {
            addCriterion("SPELL <>", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellGreaterThan(String value) {
            addCriterion("SPELL >", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellGreaterThanOrEqualTo(String value) {
            addCriterion("SPELL >=", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLessThan(String value) {
            addCriterion("SPELL <", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLessThanOrEqualTo(String value) {
            addCriterion("SPELL <=", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLike(String value) {
            addCriterion("SPELL like", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotLike(String value) {
            addCriterion("SPELL not like", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellIn(List<String> values) {
            addCriterion("SPELL in", values, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotIn(List<String> values) {
            addCriterion("SPELL not in", values, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellBetween(String value1, String value2) {
            addCriterion("SPELL between", value1, value2, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotBetween(String value1, String value2) {
            addCriterion("SPELL not between", value1, value2, "spell");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNull() {
            addCriterion("WEBSITE is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNotNull() {
            addCriterion("WEBSITE is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteEqualTo(String value) {
            addCriterion("WEBSITE =", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotEqualTo(String value) {
            addCriterion("WEBSITE <>", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThan(String value) {
            addCriterion("WEBSITE >", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("WEBSITE >=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThan(String value) {
            addCriterion("WEBSITE <", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThanOrEqualTo(String value) {
            addCriterion("WEBSITE <=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLike(String value) {
            addCriterion("WEBSITE like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLike(String value) {
            addCriterion("WEBSITE not like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteIn(List<String> values) {
            addCriterion("WEBSITE in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotIn(List<String> values) {
            addCriterion("WEBSITE not in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteBetween(String value1, String value2) {
            addCriterion("WEBSITE between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotBetween(String value1, String value2) {
            addCriterion("WEBSITE not between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andServertimeIsNull() {
            addCriterion("SERVERTIME is null");
            return (Criteria) this;
        }

        public Criteria andServertimeIsNotNull() {
            addCriterion("SERVERTIME is not null");
            return (Criteria) this;
        }

        public Criteria andServertimeEqualTo(String value) {
            addCriterion("SERVERTIME =", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeNotEqualTo(String value) {
            addCriterion("SERVERTIME <>", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeGreaterThan(String value) {
            addCriterion("SERVERTIME >", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeGreaterThanOrEqualTo(String value) {
            addCriterion("SERVERTIME >=", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeLessThan(String value) {
            addCriterion("SERVERTIME <", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeLessThanOrEqualTo(String value) {
            addCriterion("SERVERTIME <=", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeLike(String value) {
            addCriterion("SERVERTIME like", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeNotLike(String value) {
            addCriterion("SERVERTIME not like", value, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeIn(List<String> values) {
            addCriterion("SERVERTIME in", values, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeNotIn(List<String> values) {
            addCriterion("SERVERTIME not in", values, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeBetween(String value1, String value2) {
            addCriterion("SERVERTIME between", value1, value2, "servertime");
            return (Criteria) this;
        }

        public Criteria andServertimeNotBetween(String value1, String value2) {
            addCriterion("SERVERTIME not between", value1, value2, "servertime");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("SCORE not between", value1, value2, "score");
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