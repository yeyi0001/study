package com.yeyi.study.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NoteExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStudyDateIsNull() {
            addCriterion("study_date is null");
            return (Criteria) this;
        }

        public Criteria andStudyDateIsNotNull() {
            addCriterion("study_date is not null");
            return (Criteria) this;
        }

        public Criteria andStudyDateEqualTo(Date value) {
            addCriterionForJDBCDate("study_date =", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("study_date <>", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("study_date >", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("study_date >=", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateLessThan(Date value) {
            addCriterionForJDBCDate("study_date <", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("study_date <=", value, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateIn(List<Date> values) {
            addCriterionForJDBCDate("study_date in", values, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("study_date not in", values, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("study_date between", value1, value2, "studyDate");
            return (Criteria) this;
        }

        public Criteria andStudyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("study_date not between", value1, value2, "studyDate");
            return (Criteria) this;
        }

        public Criteria andLessonNumberIsNull() {
            addCriterion("lesson_number is null");
            return (Criteria) this;
        }

        public Criteria andLessonNumberIsNotNull() {
            addCriterion("lesson_number is not null");
            return (Criteria) this;
        }

        public Criteria andLessonNumberEqualTo(String value) {
            addCriterion("lesson_number =", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberNotEqualTo(String value) {
            addCriterion("lesson_number <>", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberGreaterThan(String value) {
            addCriterion("lesson_number >", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberGreaterThanOrEqualTo(String value) {
            addCriterion("lesson_number >=", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberLessThan(String value) {
            addCriterion("lesson_number <", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberLessThanOrEqualTo(String value) {
            addCriterion("lesson_number <=", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberLike(String value) {
            addCriterion("lesson_number like", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberNotLike(String value) {
            addCriterion("lesson_number not like", value, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberIn(List<String> values) {
            addCriterion("lesson_number in", values, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberNotIn(List<String> values) {
            addCriterion("lesson_number not in", values, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberBetween(String value1, String value2) {
            addCriterion("lesson_number between", value1, value2, "lessonNumber");
            return (Criteria) this;
        }

        public Criteria andLessonNumberNotBetween(String value1, String value2) {
            addCriterion("lesson_number not between", value1, value2, "lessonNumber");
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