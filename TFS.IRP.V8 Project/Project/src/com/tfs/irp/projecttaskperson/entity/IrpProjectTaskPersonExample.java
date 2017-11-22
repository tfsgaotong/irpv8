package com.tfs.irp.projecttaskperson.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpProjectTaskPersonExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public IrpProjectTaskPersonExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    protected IrpProjectTaskPersonExample(IrpProjectTaskPersonExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
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
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_PROJECT_TASK_PERSON
     *
     * @ibatorgenerated Wed Jun 19 14:52:02 CST 2013
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

        public Criteria andTaskpersonidIsNull() {
            addCriterion("TASKPERSONID is null");
            return this;
        }

        public Criteria andTaskpersonidIsNotNull() {
            addCriterion("TASKPERSONID is not null");
            return this;
        }

        public Criteria andTaskpersonidEqualTo(Long value) {
            addCriterion("TASKPERSONID =", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidNotEqualTo(Long value) {
            addCriterion("TASKPERSONID <>", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidGreaterThan(Long value) {
            addCriterion("TASKPERSONID >", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidGreaterThanOrEqualTo(Long value) {
            addCriterion("TASKPERSONID >=", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidLessThan(Long value) {
            addCriterion("TASKPERSONID <", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidLessThanOrEqualTo(Long value) {
            addCriterion("TASKPERSONID <=", value, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidIn(List<Long> values) {
            addCriterion("TASKPERSONID in", values, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidNotIn(List<Long> values) {
            addCriterion("TASKPERSONID not in", values, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidBetween(Long value1, Long value2) {
            addCriterion("TASKPERSONID between", value1, value2, "taskpersonid");
            return this;
        }

        public Criteria andTaskpersonidNotBetween(Long value1, Long value2) {
            addCriterion("TASKPERSONID not between", value1, value2, "taskpersonid");
            return this;
        }

        public Criteria andProjectidIsNull() {
            addCriterion("PROJECTID is null");
            return this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("PROJECTID is not null");
            return this;
        }

        public Criteria andProjectidEqualTo(Long value) {
            addCriterion("PROJECTID =", value, "projectid");
            return this;
        }

        public Criteria andProjectidNotEqualTo(Long value) {
            addCriterion("PROJECTID <>", value, "projectid");
            return this;
        }

        public Criteria andProjectidGreaterThan(Long value) {
            addCriterion("PROJECTID >", value, "projectid");
            return this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Long value) {
            addCriterion("PROJECTID >=", value, "projectid");
            return this;
        }

        public Criteria andProjectidLessThan(Long value) {
            addCriterion("PROJECTID <", value, "projectid");
            return this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Long value) {
            addCriterion("PROJECTID <=", value, "projectid");
            return this;
        }

        public Criteria andProjectidIn(List<Long> values) {
            addCriterion("PROJECTID in", values, "projectid");
            return this;
        }

        public Criteria andProjectidNotIn(List<Long> values) {
            addCriterion("PROJECTID not in", values, "projectid");
            return this;
        }

        public Criteria andProjectidBetween(Long value1, Long value2) {
            addCriterion("PROJECTID between", value1, value2, "projectid");
            return this;
        }

        public Criteria andProjectidNotBetween(Long value1, Long value2) {
            addCriterion("PROJECTID not between", value1, value2, "projectid");
            return this;
        }

        public Criteria andSharetaskidIsNull() {
            addCriterion("SHARETASKID is null");
            return this;
        }

        public Criteria andSharetaskidIsNotNull() {
            addCriterion("SHARETASKID is not null");
            return this;
        }

        public Criteria andSharetaskidEqualTo(Long value) {
            addCriterion("SHARETASKID =", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidNotEqualTo(Long value) {
            addCriterion("SHARETASKID <>", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidGreaterThan(Long value) {
            addCriterion("SHARETASKID >", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidGreaterThanOrEqualTo(Long value) {
            addCriterion("SHARETASKID >=", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidLessThan(Long value) {
            addCriterion("SHARETASKID <", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidLessThanOrEqualTo(Long value) {
            addCriterion("SHARETASKID <=", value, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidIn(List<Long> values) {
            addCriterion("SHARETASKID in", values, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidNotIn(List<Long> values) {
            addCriterion("SHARETASKID not in", values, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidBetween(Long value1, Long value2) {
            addCriterion("SHARETASKID between", value1, value2, "sharetaskid");
            return this;
        }

        public Criteria andSharetaskidNotBetween(Long value1, Long value2) {
            addCriterion("SHARETASKID not between", value1, value2, "sharetaskid");
            return this;
        }

        public Criteria andPersonidIsNull() {
            addCriterion("PERSONID is null");
            return this;
        }

        public Criteria andPersonidIsNotNull() {
            addCriterion("PERSONID is not null");
            return this;
        }

        public Criteria andPersonidEqualTo(Long value) {
            addCriterion("PERSONID =", value, "personid");
            return this;
        }

        public Criteria andPersonidNotEqualTo(Long value) {
            addCriterion("PERSONID <>", value, "personid");
            return this;
        }

        public Criteria andPersonidGreaterThan(Long value) {
            addCriterion("PERSONID >", value, "personid");
            return this;
        }

        public Criteria andPersonidGreaterThanOrEqualTo(Long value) {
            addCriterion("PERSONID >=", value, "personid");
            return this;
        }

        public Criteria andPersonidLessThan(Long value) {
            addCriterion("PERSONID <", value, "personid");
            return this;
        }

        public Criteria andPersonidLessThanOrEqualTo(Long value) {
            addCriterion("PERSONID <=", value, "personid");
            return this;
        }

        public Criteria andPersonidIn(List<Long> values) {
            addCriterion("PERSONID in", values, "personid");
            return this;
        }

        public Criteria andPersonidNotIn(List<Long> values) {
            addCriterion("PERSONID not in", values, "personid");
            return this;
        }

        public Criteria andPersonidBetween(Long value1, Long value2) {
            addCriterion("PERSONID between", value1, value2, "personid");
            return this;
        }

        public Criteria andPersonidNotBetween(Long value1, Long value2) {
            addCriterion("PERSONID not between", value1, value2, "personid");
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
    }
}