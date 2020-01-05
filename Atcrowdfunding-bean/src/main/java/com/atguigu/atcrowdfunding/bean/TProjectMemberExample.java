package com.atguigu.atcrowdfunding.bean;

import java.util.ArrayList;
import java.util.List;

public class TProjectMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TProjectMemberExample() {
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

        public Criteria andProidIsNull() {
            addCriterion("proId is null");
            return (Criteria) this;
        }

        public Criteria andProidIsNotNull() {
            addCriterion("proId is not null");
            return (Criteria) this;
        }

        public Criteria andProidEqualTo(Integer value) {
            addCriterion("proId =", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotEqualTo(Integer value) {
            addCriterion("proId <>", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThan(Integer value) {
            addCriterion("proId >", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThanOrEqualTo(Integer value) {
            addCriterion("proId >=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThan(Integer value) {
            addCriterion("proId <", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThanOrEqualTo(Integer value) {
            addCriterion("proId <=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidIn(List<Integer> values) {
            addCriterion("proId in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotIn(List<Integer> values) {
            addCriterion("proId not in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidBetween(Integer value1, Integer value2) {
            addCriterion("proId between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotBetween(Integer value1, Integer value2) {
            addCriterion("proId not between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andBasicdescIsNull() {
            addCriterion("basicDesc is null");
            return (Criteria) this;
        }

        public Criteria andBasicdescIsNotNull() {
            addCriterion("basicDesc is not null");
            return (Criteria) this;
        }

        public Criteria andBasicdescEqualTo(String value) {
            addCriterion("basicDesc =", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescNotEqualTo(String value) {
            addCriterion("basicDesc <>", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescGreaterThan(String value) {
            addCriterion("basicDesc >", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescGreaterThanOrEqualTo(String value) {
            addCriterion("basicDesc >=", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescLessThan(String value) {
            addCriterion("basicDesc <", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescLessThanOrEqualTo(String value) {
            addCriterion("basicDesc <=", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescLike(String value) {
            addCriterion("basicDesc like", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescNotLike(String value) {
            addCriterion("basicDesc not like", value, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescIn(List<String> values) {
            addCriterion("basicDesc in", values, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescNotIn(List<String> values) {
            addCriterion("basicDesc not in", values, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescBetween(String value1, String value2) {
            addCriterion("basicDesc between", value1, value2, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andBasicdescNotBetween(String value1, String value2) {
            addCriterion("basicDesc not between", value1, value2, "basicdesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescIsNull() {
            addCriterion("detailDesc is null");
            return (Criteria) this;
        }

        public Criteria andDetaildescIsNotNull() {
            addCriterion("detailDesc is not null");
            return (Criteria) this;
        }

        public Criteria andDetaildescEqualTo(String value) {
            addCriterion("detailDesc =", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotEqualTo(String value) {
            addCriterion("detailDesc <>", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescGreaterThan(String value) {
            addCriterion("detailDesc >", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescGreaterThanOrEqualTo(String value) {
            addCriterion("detailDesc >=", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLessThan(String value) {
            addCriterion("detailDesc <", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLessThanOrEqualTo(String value) {
            addCriterion("detailDesc <=", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLike(String value) {
            addCriterion("detailDesc like", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotLike(String value) {
            addCriterion("detailDesc not like", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescIn(List<String> values) {
            addCriterion("detailDesc in", values, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotIn(List<String> values) {
            addCriterion("detailDesc not in", values, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescBetween(String value1, String value2) {
            addCriterion("detailDesc between", value1, value2, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotBetween(String value1, String value2) {
            addCriterion("detailDesc not between", value1, value2, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
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