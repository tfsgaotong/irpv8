package com.tfs.irp.expert.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpExpertQuestionExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public IrpExpertQuestionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    protected IrpExpertQuestionExample(IrpExpertQuestionExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_EXPERT_QUESTION
     *
     * @ibatorgenerated Mon May 13 14:38:31 CST 2013
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
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

        public Criteria andExpertquestionidIsNull() {
            addCriterion("EXPERTQUESTIONID is null");
            return this;
        }

        public Criteria andExpertquestionidIsNotNull() {
            addCriterion("EXPERTQUESTIONID is not null");
            return this;
        }

        public Criteria andExpertquestionidEqualTo(Long value) {
            addCriterion("EXPERTQUESTIONID =", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidNotEqualTo(Long value) {
            addCriterion("EXPERTQUESTIONID <>", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidGreaterThan(Long value) {
            addCriterion("EXPERTQUESTIONID >", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPERTQUESTIONID >=", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidLessThan(Long value) {
            addCriterion("EXPERTQUESTIONID <", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidLessThanOrEqualTo(Long value) {
            addCriterion("EXPERTQUESTIONID <=", value, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidIn(List<Long> values) {
            addCriterion("EXPERTQUESTIONID in", values, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidNotIn(List<Long> values) {
            addCriterion("EXPERTQUESTIONID not in", values, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidBetween(Long value1, Long value2) {
            addCriterion("EXPERTQUESTIONID between", value1, value2, "expertquestionid");
            return this;
        }

        public Criteria andExpertquestionidNotBetween(Long value1, Long value2) {
            addCriterion("EXPERTQUESTIONID not between", value1, value2, "expertquestionid");
            return this;
        }

        public Criteria andExpertidIsNull() {
            addCriterion("EXPERTID is null");
            return this;
        }

        public Criteria andExpertidIsNotNull() {
            addCriterion("EXPERTID is not null");
            return this;
        }

        public Criteria andExpertidEqualTo(Long value) {
            addCriterion("EXPERTID =", value, "expertid");
            return this;
        }

        public Criteria andExpertidNotEqualTo(Long value) {
            addCriterion("EXPERTID <>", value, "expertid");
            return this;
        }

        public Criteria andExpertidGreaterThan(Long value) {
            addCriterion("EXPERTID >", value, "expertid");
            return this;
        }

        public Criteria andExpertidGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPERTID >=", value, "expertid");
            return this;
        }

        public Criteria andExpertidLessThan(Long value) {
            addCriterion("EXPERTID <", value, "expertid");
            return this;
        }

        public Criteria andExpertidLessThanOrEqualTo(Long value) {
            addCriterion("EXPERTID <=", value, "expertid");
            return this;
        }

        public Criteria andExpertidIn(List<Long> values) {
            addCriterion("EXPERTID in", values, "expertid");
            return this;
        }

        public Criteria andExpertidNotIn(List<Long> values) {
            addCriterion("EXPERTID not in", values, "expertid");
            return this;
        }

        public Criteria andExpertidBetween(Long value1, Long value2) {
            addCriterion("EXPERTID between", value1, value2, "expertid");
            return this;
        }

        public Criteria andExpertidNotBetween(Long value1, Long value2) {
            addCriterion("EXPERTID not between", value1, value2, "expertid");
            return this;
        }

        public Criteria andQuestioneridIsNull() {
            addCriterion("QUESTIONERID is null");
            return this;
        }

        public Criteria andQuestioneridIsNotNull() {
            addCriterion("QUESTIONERID is not null");
            return this;
        }

        public Criteria andQuestioneridEqualTo(Long value) {
            addCriterion("QUESTIONERID =", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridNotEqualTo(Long value) {
            addCriterion("QUESTIONERID <>", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridGreaterThan(Long value) {
            addCriterion("QUESTIONERID >", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridGreaterThanOrEqualTo(Long value) {
            addCriterion("QUESTIONERID >=", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridLessThan(Long value) {
            addCriterion("QUESTIONERID <", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridLessThanOrEqualTo(Long value) {
            addCriterion("QUESTIONERID <=", value, "questionerid");
            return this;
        }

        public Criteria andQuestioneridIn(List<Long> values) {
            addCriterion("QUESTIONERID in", values, "questionerid");
            return this;
        }

        public Criteria andQuestioneridNotIn(List<Long> values) {
            addCriterion("QUESTIONERID not in", values, "questionerid");
            return this;
        }

        public Criteria andQuestioneridBetween(Long value1, Long value2) {
            addCriterion("QUESTIONERID between", value1, value2, "questionerid");
            return this;
        }

        public Criteria andQuestioneridNotBetween(Long value1, Long value2) {
            addCriterion("QUESTIONERID not between", value1, value2, "questionerid");
            return this;
        }

        public Criteria andQuestionidIsNull() {
            addCriterion("QUESTIONID is null");
            return this;
        }

        public Criteria andQuestionidIsNotNull() {
            addCriterion("QUESTIONID is not null");
            return this;
        }

        public Criteria andQuestionidEqualTo(Long value) {
            addCriterion("QUESTIONID =", value, "questionid");
            return this;
        }

        public Criteria andQuestionidNotEqualTo(Long value) {
            addCriterion("QUESTIONID <>", value, "questionid");
            return this;
        }

        public Criteria andQuestionidGreaterThan(Long value) {
            addCriterion("QUESTIONID >", value, "questionid");
            return this;
        }

        public Criteria andQuestionidGreaterThanOrEqualTo(Long value) {
            addCriterion("QUESTIONID >=", value, "questionid");
            return this;
        }

        public Criteria andQuestionidLessThan(Long value) {
            addCriterion("QUESTIONID <", value, "questionid");
            return this;
        }

        public Criteria andQuestionidLessThanOrEqualTo(Long value) {
            addCriterion("QUESTIONID <=", value, "questionid");
            return this;
        }

        public Criteria andQuestionidIn(List<Long> values) {
            addCriterion("QUESTIONID in", values, "questionid");
            return this;
        }

        public Criteria andQuestionidNotIn(List<Long> values) {
            addCriterion("QUESTIONID not in", values, "questionid");
            return this;
        }

        public Criteria andQuestionidBetween(Long value1, Long value2) {
            addCriterion("QUESTIONID between", value1, value2, "questionid");
            return this;
        }

        public Criteria andQuestionidNotBetween(Long value1, Long value2) {
            addCriterion("QUESTIONID not between", value1, value2, "questionid");
            return this;
        }

        public Criteria andCruseridIsNull() {
            addCriterion("CRUSERID is null");
            return this;
        }

        public Criteria andCruseridIsNotNull() {
            addCriterion("CRUSERID is not null");
            return this;
        }

        public Criteria andCruseridEqualTo(Long value) {
            addCriterion("CRUSERID =", value, "cruserid");
            return this;
        }

        public Criteria andCruseridNotEqualTo(Long value) {
            addCriterion("CRUSERID <>", value, "cruserid");
            return this;
        }

        public Criteria andCruseridGreaterThan(Long value) {
            addCriterion("CRUSERID >", value, "cruserid");
            return this;
        }

        public Criteria andCruseridGreaterThanOrEqualTo(Long value) {
            addCriterion("CRUSERID >=", value, "cruserid");
            return this;
        }

        public Criteria andCruseridLessThan(Long value) {
            addCriterion("CRUSERID <", value, "cruserid");
            return this;
        }

        public Criteria andCruseridLessThanOrEqualTo(Long value) {
            addCriterion("CRUSERID <=", value, "cruserid");
            return this;
        }

        public Criteria andCruseridIn(List<Long> values) {
            addCriterion("CRUSERID in", values, "cruserid");
            return this;
        }

        public Criteria andCruseridNotIn(List<Long> values) {
            addCriterion("CRUSERID not in", values, "cruserid");
            return this;
        }

        public Criteria andCruseridBetween(Long value1, Long value2) {
            addCriterion("CRUSERID between", value1, value2, "cruserid");
            return this;
        }

        public Criteria andCruseridNotBetween(Long value1, Long value2) {
            addCriterion("CRUSERID not between", value1, value2, "cruserid");
            return this;
        }

        public Criteria andCruserIsNull() {
            addCriterion("CRUSER is null");
            return this;
        }

        public Criteria andCruserIsNotNull() {
            addCriterion("CRUSER is not null");
            return this;
        }

        public Criteria andCruserEqualTo(String value) {
            addCriterion("CRUSER =", value, "cruser");
            return this;
        }

        public Criteria andCruserNotEqualTo(String value) {
            addCriterion("CRUSER <>", value, "cruser");
            return this;
        }

        public Criteria andCruserGreaterThan(String value) {
            addCriterion("CRUSER >", value, "cruser");
            return this;
        }

        public Criteria andCruserGreaterThanOrEqualTo(String value) {
            addCriterion("CRUSER >=", value, "cruser");
            return this;
        }

        public Criteria andCruserLessThan(String value) {
            addCriterion("CRUSER <", value, "cruser");
            return this;
        }

        public Criteria andCruserLessThanOrEqualTo(String value) {
            addCriterion("CRUSER <=", value, "cruser");
            return this;
        }

        public Criteria andCruserLike(String value) {
            addCriterion("CRUSER like", value, "cruser");
            return this;
        }

        public Criteria andCruserNotLike(String value) {
            addCriterion("CRUSER not like", value, "cruser");
            return this;
        }

        public Criteria andCruserIn(List<String> values) {
            addCriterion("CRUSER in", values, "cruser");
            return this;
        }

        public Criteria andCruserNotIn(List<String> values) {
            addCriterion("CRUSER not in", values, "cruser");
            return this;
        }

        public Criteria andCruserBetween(String value1, String value2) {
            addCriterion("CRUSER between", value1, value2, "cruser");
            return this;
        }

        public Criteria andCruserNotBetween(String value1, String value2) {
            addCriterion("CRUSER not between", value1, value2, "cruser");
            return this;
        }

        public Criteria andCrtimeIsNull() {
            addCriterion("CRTIME is null");
            return this;
        }

        public Criteria andCrtimeIsNotNull() {
            addCriterion("CRTIME is not null");
            return this;
        }

        public Criteria andCrtimeEqualTo(Date value) {
            addCriterionForJDBCDate("CRTIME =", value, "crtime");
            return this;
        }

        public Criteria andCrtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CRTIME <>", value, "crtime");
            return this;
        }

        public Criteria andCrtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CRTIME >", value, "crtime");
            return this;
        }

        public Criteria andCrtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRTIME >=", value, "crtime");
            return this;
        }

        public Criteria andCrtimeLessThan(Date value) {
            addCriterionForJDBCDate("CRTIME <", value, "crtime");
            return this;
        }

        public Criteria andCrtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRTIME <=", value, "crtime");
            return this;
        }

        public Criteria andCrtimeIn(List<Date> values) {
            addCriterionForJDBCDate("CRTIME in", values, "crtime");
            return this;
        }

        public Criteria andCrtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CRTIME not in", values, "crtime");
            return this;
        }

        public Criteria andCrtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRTIME between", value1, value2, "crtime");
            return this;
        }

        public Criteria andCrtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRTIME not between", value1, value2, "crtime");
            return this;
        }

        public Criteria andExpertNameIsNull() {
            addCriterion("EXPERT_NAME is null");
            return this;
        }

        public Criteria andExpertNameIsNotNull() {
            addCriterion("EXPERT_NAME is not null");
            return this;
        }

        public Criteria andExpertNameEqualTo(String value) {
            addCriterion("EXPERT_NAME =", value, "expertName");
            return this;
        }

        public Criteria andExpertNameNotEqualTo(String value) {
            addCriterion("EXPERT_NAME <>", value, "expertName");
            return this;
        }

        public Criteria andExpertNameGreaterThan(String value) {
            addCriterion("EXPERT_NAME >", value, "expertName");
            return this;
        }

        public Criteria andExpertNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_NAME >=", value, "expertName");
            return this;
        }

        public Criteria andExpertNameLessThan(String value) {
            addCriterion("EXPERT_NAME <", value, "expertName");
            return this;
        }

        public Criteria andExpertNameLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_NAME <=", value, "expertName");
            return this;
        }

        public Criteria andExpertNameLike(String value) {
            addCriterion("EXPERT_NAME like", value, "expertName");
            return this;
        }

        public Criteria andExpertNameNotLike(String value) {
            addCriterion("EXPERT_NAME not like", value, "expertName");
            return this;
        }

        public Criteria andExpertNameIn(List<String> values) {
            addCriterion("EXPERT_NAME in", values, "expertName");
            return this;
        }

        public Criteria andExpertNameNotIn(List<String> values) {
            addCriterion("EXPERT_NAME not in", values, "expertName");
            return this;
        }

        public Criteria andExpertNameBetween(String value1, String value2) {
            addCriterion("EXPERT_NAME between", value1, value2, "expertName");
            return this;
        }

        public Criteria andExpertNameNotBetween(String value1, String value2) {
            addCriterion("EXPERT_NAME not between", value1, value2, "expertName");
            return this;
        }
    }
}