package com.atguigu.atcrowdfunding.bean;

import java.util.ArrayList;
import java.util.List;

public class TProjectCompExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TProjectCompExample() {
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

        public Criteria andCompnaIsNull() {
            addCriterion("compna is null");
            return (Criteria) this;
        }

        public Criteria andCompnaIsNotNull() {
            addCriterion("compna is not null");
            return (Criteria) this;
        }

        public Criteria andCompnaEqualTo(String value) {
            addCriterion("compna =", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaNotEqualTo(String value) {
            addCriterion("compna <>", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaGreaterThan(String value) {
            addCriterion("compna >", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaGreaterThanOrEqualTo(String value) {
            addCriterion("compna >=", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaLessThan(String value) {
            addCriterion("compna <", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaLessThanOrEqualTo(String value) {
            addCriterion("compna <=", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaLike(String value) {
            addCriterion("compna like", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaNotLike(String value) {
            addCriterion("compna not like", value, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaIn(List<String> values) {
            addCriterion("compna in", values, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaNotIn(List<String> values) {
            addCriterion("compna not in", values, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaBetween(String value1, String value2) {
            addCriterion("compna between", value1, value2, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnaNotBetween(String value1, String value2) {
            addCriterion("compna not between", value1, value2, "compna");
            return (Criteria) this;
        }

        public Criteria andCompnoIsNull() {
            addCriterion("compno is null");
            return (Criteria) this;
        }

        public Criteria andCompnoIsNotNull() {
            addCriterion("compno is not null");
            return (Criteria) this;
        }

        public Criteria andCompnoEqualTo(String value) {
            addCriterion("compno =", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoNotEqualTo(String value) {
            addCriterion("compno <>", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoGreaterThan(String value) {
            addCriterion("compno >", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoGreaterThanOrEqualTo(String value) {
            addCriterion("compno >=", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoLessThan(String value) {
            addCriterion("compno <", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoLessThanOrEqualTo(String value) {
            addCriterion("compno <=", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoLike(String value) {
            addCriterion("compno like", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoNotLike(String value) {
            addCriterion("compno not like", value, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoIn(List<String> values) {
            addCriterion("compno in", values, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoNotIn(List<String> values) {
            addCriterion("compno not in", values, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoBetween(String value1, String value2) {
            addCriterion("compno between", value1, value2, "compno");
            return (Criteria) this;
        }

        public Criteria andCompnoNotBetween(String value1, String value2) {
            addCriterion("compno not between", value1, value2, "compno");
            return (Criteria) this;
        }

        public Criteria andCompcdIsNull() {
            addCriterion("compcd is null");
            return (Criteria) this;
        }

        public Criteria andCompcdIsNotNull() {
            addCriterion("compcd is not null");
            return (Criteria) this;
        }

        public Criteria andCompcdEqualTo(String value) {
            addCriterion("compcd =", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdNotEqualTo(String value) {
            addCriterion("compcd <>", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdGreaterThan(String value) {
            addCriterion("compcd >", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdGreaterThanOrEqualTo(String value) {
            addCriterion("compcd >=", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdLessThan(String value) {
            addCriterion("compcd <", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdLessThanOrEqualTo(String value) {
            addCriterion("compcd <=", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdLike(String value) {
            addCriterion("compcd like", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdNotLike(String value) {
            addCriterion("compcd not like", value, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdIn(List<String> values) {
            addCriterion("compcd in", values, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdNotIn(List<String> values) {
            addCriterion("compcd not in", values, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdBetween(String value1, String value2) {
            addCriterion("compcd between", value1, value2, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompcdNotBetween(String value1, String value2) {
            addCriterion("compcd not between", value1, value2, "compcd");
            return (Criteria) this;
        }

        public Criteria andCompacctIsNull() {
            addCriterion("compacct is null");
            return (Criteria) this;
        }

        public Criteria andCompacctIsNotNull() {
            addCriterion("compacct is not null");
            return (Criteria) this;
        }

        public Criteria andCompacctEqualTo(String value) {
            addCriterion("compacct =", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctNotEqualTo(String value) {
            addCriterion("compacct <>", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctGreaterThan(String value) {
            addCriterion("compacct >", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctGreaterThanOrEqualTo(String value) {
            addCriterion("compacct >=", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctLessThan(String value) {
            addCriterion("compacct <", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctLessThanOrEqualTo(String value) {
            addCriterion("compacct <=", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctLike(String value) {
            addCriterion("compacct like", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctNotLike(String value) {
            addCriterion("compacct not like", value, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctIn(List<String> values) {
            addCriterion("compacct in", values, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctNotIn(List<String> values) {
            addCriterion("compacct not in", values, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctBetween(String value1, String value2) {
            addCriterion("compacct between", value1, value2, "compacct");
            return (Criteria) this;
        }

        public Criteria andCompacctNotBetween(String value1, String value2) {
            addCriterion("compacct not between", value1, value2, "compacct");
            return (Criteria) this;
        }

        public Criteria andServicetelIsNull() {
            addCriterion("servicetel is null");
            return (Criteria) this;
        }

        public Criteria andServicetelIsNotNull() {
            addCriterion("servicetel is not null");
            return (Criteria) this;
        }

        public Criteria andServicetelEqualTo(String value) {
            addCriterion("servicetel =", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelNotEqualTo(String value) {
            addCriterion("servicetel <>", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelGreaterThan(String value) {
            addCriterion("servicetel >", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelGreaterThanOrEqualTo(String value) {
            addCriterion("servicetel >=", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelLessThan(String value) {
            addCriterion("servicetel <", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelLessThanOrEqualTo(String value) {
            addCriterion("servicetel <=", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelLike(String value) {
            addCriterion("servicetel like", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelNotLike(String value) {
            addCriterion("servicetel not like", value, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelIn(List<String> values) {
            addCriterion("servicetel in", values, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelNotIn(List<String> values) {
            addCriterion("servicetel not in", values, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelBetween(String value1, String value2) {
            addCriterion("servicetel between", value1, value2, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetelNotBetween(String value1, String value2) {
            addCriterion("servicetel not between", value1, value2, "servicetel");
            return (Criteria) this;
        }

        public Criteria andServicetimeIsNull() {
            addCriterion("servicetime is null");
            return (Criteria) this;
        }

        public Criteria andServicetimeIsNotNull() {
            addCriterion("servicetime is not null");
            return (Criteria) this;
        }

        public Criteria andServicetimeEqualTo(String value) {
            addCriterion("servicetime =", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeNotEqualTo(String value) {
            addCriterion("servicetime <>", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeGreaterThan(String value) {
            addCriterion("servicetime >", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeGreaterThanOrEqualTo(String value) {
            addCriterion("servicetime >=", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeLessThan(String value) {
            addCriterion("servicetime <", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeLessThanOrEqualTo(String value) {
            addCriterion("servicetime <=", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeLike(String value) {
            addCriterion("servicetime like", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeNotLike(String value) {
            addCriterion("servicetime not like", value, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeIn(List<String> values) {
            addCriterion("servicetime in", values, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeNotIn(List<String> values) {
            addCriterion("servicetime not in", values, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeBetween(String value1, String value2) {
            addCriterion("servicetime between", value1, value2, "servicetime");
            return (Criteria) this;
        }

        public Criteria andServicetimeNotBetween(String value1, String value2) {
            addCriterion("servicetime not between", value1, value2, "servicetime");
            return (Criteria) this;
        }

        public Criteria andUserexistIsNull() {
            addCriterion("userexist is null");
            return (Criteria) this;
        }

        public Criteria andUserexistIsNotNull() {
            addCriterion("userexist is not null");
            return (Criteria) this;
        }

        public Criteria andUserexistEqualTo(String value) {
            addCriterion("userexist =", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistNotEqualTo(String value) {
            addCriterion("userexist <>", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistGreaterThan(String value) {
            addCriterion("userexist >", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistGreaterThanOrEqualTo(String value) {
            addCriterion("userexist >=", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistLessThan(String value) {
            addCriterion("userexist <", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistLessThanOrEqualTo(String value) {
            addCriterion("userexist <=", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistLike(String value) {
            addCriterion("userexist like", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistNotLike(String value) {
            addCriterion("userexist not like", value, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistIn(List<String> values) {
            addCriterion("userexist in", values, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistNotIn(List<String> values) {
            addCriterion("userexist not in", values, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistBetween(String value1, String value2) {
            addCriterion("userexist between", value1, value2, "userexist");
            return (Criteria) this;
        }

        public Criteria andUserexistNotBetween(String value1, String value2) {
            addCriterion("userexist not between", value1, value2, "userexist");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNull() {
            addCriterion("template is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNotNull() {
            addCriterion("template is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEqualTo(String value) {
            addCriterion("template =", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotEqualTo(String value) {
            addCriterion("template <>", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThan(String value) {
            addCriterion("template >", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("template >=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThan(String value) {
            addCriterion("template <", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThanOrEqualTo(String value) {
            addCriterion("template <=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLike(String value) {
            addCriterion("template like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotLike(String value) {
            addCriterion("template not like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateIn(List<String> values) {
            addCriterion("template in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotIn(List<String> values) {
            addCriterion("template not in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateBetween(String value1, String value2) {
            addCriterion("template between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotBetween(String value1, String value2) {
            addCriterion("template not between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andProidIsNull() {
            addCriterion("proid is null");
            return (Criteria) this;
        }

        public Criteria andProidIsNotNull() {
            addCriterion("proid is not null");
            return (Criteria) this;
        }

        public Criteria andProidEqualTo(Integer value) {
            addCriterion("proid =", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotEqualTo(Integer value) {
            addCriterion("proid <>", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThan(Integer value) {
            addCriterion("proid >", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThanOrEqualTo(Integer value) {
            addCriterion("proid >=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThan(Integer value) {
            addCriterion("proid <", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThanOrEqualTo(Integer value) {
            addCriterion("proid <=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidIn(List<Integer> values) {
            addCriterion("proid in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotIn(List<Integer> values) {
            addCriterion("proid not in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidBetween(Integer value1, Integer value2) {
            addCriterion("proid between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotBetween(Integer value1, Integer value2) {
            addCriterion("proid not between", value1, value2, "proid");
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