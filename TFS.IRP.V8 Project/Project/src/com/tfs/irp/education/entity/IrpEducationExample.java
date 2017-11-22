package com.tfs.irp.education.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpEducationExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public IrpEducationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    protected IrpEducationExample(IrpEducationExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
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
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_EDUCATION
     *
     * @ibatorgenerated Mon Apr 22 10:24:42 CST 2013
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

        public Criteria andEducationidIsNull() {
            addCriterion("EDUCATIONID is null");
            return this;
        }

        public Criteria andEducationidIsNotNull() {
            addCriterion("EDUCATIONID is not null");
            return this;
        }

        public Criteria andEducationidEqualTo(Long value) {
            addCriterion("EDUCATIONID =", value, "educationid");
            return this;
        }

        public Criteria andEducationidNotEqualTo(Long value) {
            addCriterion("EDUCATIONID <>", value, "educationid");
            return this;
        }

        public Criteria andEducationidGreaterThan(Long value) {
            addCriterion("EDUCATIONID >", value, "educationid");
            return this;
        }

        public Criteria andEducationidGreaterThanOrEqualTo(Long value) {
            addCriterion("EDUCATIONID >=", value, "educationid");
            return this;
        }

        public Criteria andEducationidLessThan(Long value) {
            addCriterion("EDUCATIONID <", value, "educationid");
            return this;
        }

        public Criteria andEducationidLessThanOrEqualTo(Long value) {
            addCriterion("EDUCATIONID <=", value, "educationid");
            return this;
        }

        public Criteria andEducationidIn(List<Long> values) {
            addCriterion("EDUCATIONID in", values, "educationid");
            return this;
        }

        public Criteria andEducationidNotIn(List<Long> values) {
            addCriterion("EDUCATIONID not in", values, "educationid");
            return this;
        }

        public Criteria andEducationidBetween(Long value1, Long value2) {
            addCriterion("EDUCATIONID between", value1, value2, "educationid");
            return this;
        }

        public Criteria andEducationidNotBetween(Long value1, Long value2) {
            addCriterion("EDUCATIONID not between", value1, value2, "educationid");
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

        public Criteria andSchooltypeIsNull() {
            addCriterion("SCHOOLTYPE is null");
            return this;
        }

        public Criteria andSchooltypeIsNotNull() {
            addCriterion("SCHOOLTYPE is not null");
            return this;
        }

        public Criteria andSchooltypeEqualTo(Short value) {
            addCriterion("SCHOOLTYPE =", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeNotEqualTo(Short value) {
            addCriterion("SCHOOLTYPE <>", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeGreaterThan(Short value) {
            addCriterion("SCHOOLTYPE >", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeGreaterThanOrEqualTo(Short value) {
            addCriterion("SCHOOLTYPE >=", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeLessThan(Short value) {
            addCriterion("SCHOOLTYPE <", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeLessThanOrEqualTo(Short value) {
            addCriterion("SCHOOLTYPE <=", value, "schooltype");
            return this;
        }

        public Criteria andSchooltypeIn(List<Short> values) {
            addCriterion("SCHOOLTYPE in", values, "schooltype");
            return this;
        }

        public Criteria andSchooltypeNotIn(List<Short> values) {
            addCriterion("SCHOOLTYPE not in", values, "schooltype");
            return this;
        }

        public Criteria andSchooltypeBetween(Short value1, Short value2) {
            addCriterion("SCHOOLTYPE between", value1, value2, "schooltype");
            return this;
        }

        public Criteria andSchooltypeNotBetween(Short value1, Short value2) {
            addCriterion("SCHOOLTYPE not between", value1, value2, "schooltype");
            return this;
        }

        public Criteria andSchoolnameIsNull() {
            addCriterion("SCHOOLNAME is null");
            return this;
        }

        public Criteria andSchoolnameIsNotNull() {
            addCriterion("SCHOOLNAME is not null");
            return this;
        }

        public Criteria andSchoolnameEqualTo(String value) {
            addCriterion("SCHOOLNAME =", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameNotEqualTo(String value) {
            addCriterion("SCHOOLNAME <>", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameGreaterThan(String value) {
            addCriterion("SCHOOLNAME >", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOLNAME >=", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameLessThan(String value) {
            addCriterion("SCHOOLNAME <", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOLNAME <=", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameLike(String value) {
            addCriterion("SCHOOLNAME like", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameNotLike(String value) {
            addCriterion("SCHOOLNAME not like", value, "schoolname");
            return this;
        }

        public Criteria andSchoolnameIn(List<String> values) {
            addCriterion("SCHOOLNAME in", values, "schoolname");
            return this;
        }

        public Criteria andSchoolnameNotIn(List<String> values) {
            addCriterion("SCHOOLNAME not in", values, "schoolname");
            return this;
        }

        public Criteria andSchoolnameBetween(String value1, String value2) {
            addCriterion("SCHOOLNAME between", value1, value2, "schoolname");
            return this;
        }

        public Criteria andSchoolnameNotBetween(String value1, String value2) {
            addCriterion("SCHOOLNAME not between", value1, value2, "schoolname");
            return this;
        }

        public Criteria andSchoolclassIsNull() {
            addCriterion("SCHOOLCLASS is null");
            return this;
        }

        public Criteria andSchoolclassIsNotNull() {
            addCriterion("SCHOOLCLASS is not null");
            return this;
        }

        public Criteria andSchoolclassEqualTo(String value) {
            addCriterion("SCHOOLCLASS =", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassNotEqualTo(String value) {
            addCriterion("SCHOOLCLASS <>", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassGreaterThan(String value) {
            addCriterion("SCHOOLCLASS >", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOLCLASS >=", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassLessThan(String value) {
            addCriterion("SCHOOLCLASS <", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassLessThanOrEqualTo(String value) {
            addCriterion("SCHOOLCLASS <=", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassLike(String value) {
            addCriterion("SCHOOLCLASS like", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassNotLike(String value) {
            addCriterion("SCHOOLCLASS not like", value, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassIn(List<String> values) {
            addCriterion("SCHOOLCLASS in", values, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassNotIn(List<String> values) {
            addCriterion("SCHOOLCLASS not in", values, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassBetween(String value1, String value2) {
            addCriterion("SCHOOLCLASS between", value1, value2, "schoolclass");
            return this;
        }

        public Criteria andSchoolclassNotBetween(String value1, String value2) {
            addCriterion("SCHOOLCLASS not between", value1, value2, "schoolclass");
            return this;
        }

        public Criteria andStartyearIsNull() {
            addCriterion("STARTYEAR is null");
            return this;
        }

        public Criteria andStartyearIsNotNull() {
            addCriterion("STARTYEAR is not null");
            return this;
        }

        public Criteria andStartyearEqualTo(Short value) {
            addCriterion("STARTYEAR =", value, "startyear");
            return this;
        }

        public Criteria andStartyearNotEqualTo(Short value) {
            addCriterion("STARTYEAR <>", value, "startyear");
            return this;
        }

        public Criteria andStartyearGreaterThan(Short value) {
            addCriterion("STARTYEAR >", value, "startyear");
            return this;
        }

        public Criteria andStartyearGreaterThanOrEqualTo(Short value) {
            addCriterion("STARTYEAR >=", value, "startyear");
            return this;
        }

        public Criteria andStartyearLessThan(Short value) {
            addCriterion("STARTYEAR <", value, "startyear");
            return this;
        }

        public Criteria andStartyearLessThanOrEqualTo(Short value) {
            addCriterion("STARTYEAR <=", value, "startyear");
            return this;
        }

        public Criteria andStartyearIn(List<Short> values) {
            addCriterion("STARTYEAR in", values, "startyear");
            return this;
        }

        public Criteria andStartyearNotIn(List<Short> values) {
            addCriterion("STARTYEAR not in", values, "startyear");
            return this;
        }

        public Criteria andStartyearBetween(Short value1, Short value2) {
            addCriterion("STARTYEAR between", value1, value2, "startyear");
            return this;
        }

        public Criteria andStartyearNotBetween(Short value1, Short value2) {
            addCriterion("STARTYEAR not between", value1, value2, "startyear");
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
    }
}