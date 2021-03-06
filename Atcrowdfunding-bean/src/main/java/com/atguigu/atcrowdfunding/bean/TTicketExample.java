package com.atguigu.atcrowdfunding.bean;

import java.util.ArrayList;
import java.util.List;

public class TTicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TTicketExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNull() {
            addCriterion("memberid is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberid is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(Integer value) {
            addCriterion("memberid =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(Integer value) {
            addCriterion("memberid <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(Integer value) {
            addCriterion("memberid >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberid >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(Integer value) {
            addCriterion("memberid <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(Integer value) {
            addCriterion("memberid <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<Integer> values) {
            addCriterion("memberid in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<Integer> values) {
            addCriterion("memberid not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(Integer value1, Integer value2) {
            addCriterion("memberid between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(Integer value1, Integer value2) {
            addCriterion("memberid not between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andPiidIsNull() {
            addCriterion("piid is null");
            return (Criteria) this;
        }

        public Criteria andPiidIsNotNull() {
            addCriterion("piid is not null");
            return (Criteria) this;
        }

        public Criteria andPiidEqualTo(String value) {
            addCriterion("piid =", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidNotEqualTo(String value) {
            addCriterion("piid <>", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidGreaterThan(String value) {
            addCriterion("piid >", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidGreaterThanOrEqualTo(String value) {
            addCriterion("piid >=", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidLessThan(String value) {
            addCriterion("piid <", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidLessThanOrEqualTo(String value) {
            addCriterion("piid <=", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidLike(String value) {
            addCriterion("piid like", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidNotLike(String value) {
            addCriterion("piid not like", value, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidIn(List<String> values) {
            addCriterion("piid in", values, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidNotIn(List<String> values) {
            addCriterion("piid not in", values, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidBetween(String value1, String value2) {
            addCriterion("piid between", value1, value2, "piid");
            return (Criteria) this;
        }

        public Criteria andPiidNotBetween(String value1, String value2) {
            addCriterion("piid not between", value1, value2, "piid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIsNull() {
            addCriterion("authcode is null");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIsNotNull() {
            addCriterion("authcode is not null");
            return (Criteria) this;
        }

        public Criteria andAuthcodeEqualTo(String value) {
            addCriterion("authcode =", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotEqualTo(String value) {
            addCriterion("authcode <>", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeGreaterThan(String value) {
            addCriterion("authcode >", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeGreaterThanOrEqualTo(String value) {
            addCriterion("authcode >=", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLessThan(String value) {
            addCriterion("authcode <", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLessThanOrEqualTo(String value) {
            addCriterion("authcode <=", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLike(String value) {
            addCriterion("authcode like", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotLike(String value) {
            addCriterion("authcode not like", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIn(List<String> values) {
            addCriterion("authcode in", values, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotIn(List<String> values) {
            addCriterion("authcode not in", values, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeBetween(String value1, String value2) {
            addCriterion("authcode between", value1, value2, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotBetween(String value1, String value2) {
            addCriterion("authcode not between", value1, value2, "authcode");
            return (Criteria) this;
        }

        public Criteria andPstepIsNull() {
            addCriterion("pstep is null");
            return (Criteria) this;
        }

        public Criteria andPstepIsNotNull() {
            addCriterion("pstep is not null");
            return (Criteria) this;
        }

        public Criteria andPstepEqualTo(String value) {
            addCriterion("pstep =", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepNotEqualTo(String value) {
            addCriterion("pstep <>", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepGreaterThan(String value) {
            addCriterion("pstep >", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepGreaterThanOrEqualTo(String value) {
            addCriterion("pstep >=", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepLessThan(String value) {
            addCriterion("pstep <", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepLessThanOrEqualTo(String value) {
            addCriterion("pstep <=", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepLike(String value) {
            addCriterion("pstep like", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepNotLike(String value) {
            addCriterion("pstep not like", value, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepIn(List<String> values) {
            addCriterion("pstep in", values, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepNotIn(List<String> values) {
            addCriterion("pstep not in", values, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepBetween(String value1, String value2) {
            addCriterion("pstep between", value1, value2, "pstep");
            return (Criteria) this;
        }

        public Criteria andPstepNotBetween(String value1, String value2) {
            addCriterion("pstep not between", value1, value2, "pstep");
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