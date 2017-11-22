package com.tfs.irp.docscore.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpDocumentScoreExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public IrpDocumentScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    protected IrpDocumentScoreExample(IrpDocumentScoreExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
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
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
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

        public Criteria andDocidIsNull() {
            addCriterion("DOCID is null");
            return this;
        }

        public Criteria andDocidIsNotNull() {
            addCriterion("DOCID is not null");
            return this;
        }

        public Criteria andDocidEqualTo(Long value) {
            addCriterion("DOCID =", value, "docid");
            return this;
        }

        public Criteria andDocidNotEqualTo(Long value) {
            addCriterion("DOCID <>", value, "docid");
            return this;
        }

        public Criteria andDocidGreaterThan(Long value) {
            addCriterion("DOCID >", value, "docid");
            return this;
        }

        public Criteria andDocidGreaterThanOrEqualTo(Long value) {
            addCriterion("DOCID >=", value, "docid");
            return this;
        }

        public Criteria andDocidLessThan(Long value) {
            addCriterion("DOCID <", value, "docid");
            return this;
        }

        public Criteria andDocidLessThanOrEqualTo(Long value) {
            addCriterion("DOCID <=", value, "docid");
            return this;
        }

        public Criteria andDocidIn(List<Long> values) {
            addCriterion("DOCID in", values, "docid");
            return this;
        }

        public Criteria andDocidNotIn(List<Long> values) {
            addCriterion("DOCID not in", values, "docid");
            return this;
        }

        public Criteria andDocidBetween(Long value1, Long value2) {
            addCriterion("DOCID between", value1, value2, "docid");
            return this;
        }

        public Criteria andDocidNotBetween(Long value1, Long value2) {
            addCriterion("DOCID not between", value1, value2, "docid");
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

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("USERID =", value, "userid");
            return this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("USERID <>", value, "userid");
            return this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("USERID >", value, "userid");
            return this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("USERID >=", value, "userid");
            return this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("USERID <", value, "userid");
            return this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("USERID <=", value, "userid");
            return this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("USERID in", values, "userid");
            return this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("USERID not in", values, "userid");
            return this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return this;
        }

        public Criteria andScoreEqualTo(Long value) {
            addCriterion("SCORE =", value, "score");
            return this;
        }

        public Criteria andScoreNotEqualTo(Long value) {
            addCriterion("SCORE <>", value, "score");
            return this;
        }

        public Criteria andScoreGreaterThan(Long value) {
            addCriterion("SCORE >", value, "score");
            return this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("SCORE >=", value, "score");
            return this;
        }

        public Criteria andScoreLessThan(Long value) {
            addCriterion("SCORE <", value, "score");
            return this;
        }

        public Criteria andScoreLessThanOrEqualTo(Long value) {
            addCriterion("SCORE <=", value, "score");
            return this;
        }

        public Criteria andScoreIn(List<Long> values) {
            addCriterion("SCORE in", values, "score");
            return this;
        }

        public Criteria andScoreNotIn(List<Long> values) {
            addCriterion("SCORE not in", values, "score");
            return this;
        }

        public Criteria andScoreBetween(Long value1, Long value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return this;
        }

        public Criteria andScoreNotBetween(Long value1, Long value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return this;
        }
    }
}