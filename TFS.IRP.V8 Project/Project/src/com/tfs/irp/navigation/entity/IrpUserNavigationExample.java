package com.tfs.irp.navigation.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpUserNavigationExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public IrpUserNavigationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    protected IrpUserNavigationExample(IrpUserNavigationExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
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
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
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

        public Criteria andNavigationidIsNull() {
            addCriterion("NAVIGATIONID is null");
            return this;
        }

        public Criteria andNavigationidIsNotNull() {
            addCriterion("NAVIGATIONID is not null");
            return this;
        }

        public Criteria andNavigationidEqualTo(Long value) {
            addCriterion("NAVIGATIONID =", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidNotEqualTo(Long value) {
            addCriterion("NAVIGATIONID <>", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidGreaterThan(Long value) {
            addCriterion("NAVIGATIONID >", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidGreaterThanOrEqualTo(Long value) {
            addCriterion("NAVIGATIONID >=", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidLessThan(Long value) {
            addCriterion("NAVIGATIONID <", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidLessThanOrEqualTo(Long value) {
            addCriterion("NAVIGATIONID <=", value, "navigationid");
            return this;
        }

        public Criteria andNavigationidIn(List<Long> values) {
            addCriterion("NAVIGATIONID in", values, "navigationid");
            return this;
        }

        public Criteria andNavigationidNotIn(List<Long> values) {
            addCriterion("NAVIGATIONID not in", values, "navigationid");
            return this;
        }

        public Criteria andNavigationidBetween(Long value1, Long value2) {
            addCriterion("NAVIGATIONID between", value1, value2, "navigationid");
            return this;
        }

        public Criteria andNavigationidNotBetween(Long value1, Long value2) {
            addCriterion("NAVIGATIONID not between", value1, value2, "navigationid");
            return this;
        }

        public Criteria andNavigationnameIsNull() {
            addCriterion("NAVIGATIONNAME is null");
            return this;
        }

        public Criteria andNavigationnameIsNotNull() {
            addCriterion("NAVIGATIONNAME is not null");
            return this;
        }

        public Criteria andNavigationnameEqualTo(String value) {
            addCriterion("NAVIGATIONNAME =", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameNotEqualTo(String value) {
            addCriterion("NAVIGATIONNAME <>", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameGreaterThan(String value) {
            addCriterion("NAVIGATIONNAME >", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameGreaterThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONNAME >=", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameLessThan(String value) {
            addCriterion("NAVIGATIONNAME <", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameLessThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONNAME <=", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameLike(String value) {
            addCriterion("NAVIGATIONNAME like", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameNotLike(String value) {
            addCriterion("NAVIGATIONNAME not like", value, "navigationname");
            return this;
        }

        public Criteria andNavigationnameIn(List<String> values) {
            addCriterion("NAVIGATIONNAME in", values, "navigationname");
            return this;
        }

        public Criteria andNavigationnameNotIn(List<String> values) {
            addCriterion("NAVIGATIONNAME not in", values, "navigationname");
            return this;
        }

        public Criteria andNavigationnameBetween(String value1, String value2) {
            addCriterion("NAVIGATIONNAME between", value1, value2, "navigationname");
            return this;
        }

        public Criteria andNavigationnameNotBetween(String value1, String value2) {
            addCriterion("NAVIGATIONNAME not between", value1, value2, "navigationname");
            return this;
        }

        public Criteria andNavigationvalueIsNull() {
            addCriterion("NAVIGATIONVALUE is null");
            return this;
        }

        public Criteria andNavigationvalueIsNotNull() {
            addCriterion("NAVIGATIONVALUE is not null");
            return this;
        }

        public Criteria andNavigationvalueEqualTo(String value) {
            addCriterion("NAVIGATIONVALUE =", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueNotEqualTo(String value) {
            addCriterion("NAVIGATIONVALUE <>", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueGreaterThan(String value) {
            addCriterion("NAVIGATIONVALUE >", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueGreaterThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONVALUE >=", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueLessThan(String value) {
            addCriterion("NAVIGATIONVALUE <", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueLessThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONVALUE <=", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueLike(String value) {
            addCriterion("NAVIGATIONVALUE like", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueNotLike(String value) {
            addCriterion("NAVIGATIONVALUE not like", value, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueIn(List<String> values) {
            addCriterion("NAVIGATIONVALUE in", values, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueNotIn(List<String> values) {
            addCriterion("NAVIGATIONVALUE not in", values, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueBetween(String value1, String value2) {
            addCriterion("NAVIGATIONVALUE between", value1, value2, "navigationvalue");
            return this;
        }

        public Criteria andNavigationvalueNotBetween(String value1, String value2) {
            addCriterion("NAVIGATIONVALUE not between", value1, value2, "navigationvalue");
            return this;
        }

        public Criteria andNavigationdescIsNull() {
            addCriterion("NAVIGATIONDESC is null");
            return this;
        }

        public Criteria andNavigationdescIsNotNull() {
            addCriterion("NAVIGATIONDESC is not null");
            return this;
        }

        public Criteria andNavigationdescEqualTo(String value) {
            addCriterion("NAVIGATIONDESC =", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescNotEqualTo(String value) {
            addCriterion("NAVIGATIONDESC <>", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescGreaterThan(String value) {
            addCriterion("NAVIGATIONDESC >", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescGreaterThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONDESC >=", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescLessThan(String value) {
            addCriterion("NAVIGATIONDESC <", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescLessThanOrEqualTo(String value) {
            addCriterion("NAVIGATIONDESC <=", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescLike(String value) {
            addCriterion("NAVIGATIONDESC like", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescNotLike(String value) {
            addCriterion("NAVIGATIONDESC not like", value, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescIn(List<String> values) {
            addCriterion("NAVIGATIONDESC in", values, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescNotIn(List<String> values) {
            addCriterion("NAVIGATIONDESC not in", values, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescBetween(String value1, String value2) {
            addCriterion("NAVIGATIONDESC between", value1, value2, "navigationdesc");
            return this;
        }

        public Criteria andNavigationdescNotBetween(String value1, String value2) {
            addCriterion("NAVIGATIONDESC not between", value1, value2, "navigationdesc");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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

        public Criteria andClassifyidIsNull() {
            addCriterion("CLASSIFYID is null");
            return this;
        }

        public Criteria andClassifyidIsNotNull() {
            addCriterion("CLASSIFYID is not null");
            return this;
        }

        public Criteria andClassifyidEqualTo(Long value) {
            addCriterion("CLASSIFYID =", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidNotEqualTo(Long value) {
            addCriterion("CLASSIFYID <>", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidGreaterThan(Long value) {
            addCriterion("CLASSIFYID >", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidGreaterThanOrEqualTo(Long value) {
            addCriterion("CLASSIFYID >=", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidLessThan(Long value) {
            addCriterion("CLASSIFYID <", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidLessThanOrEqualTo(Long value) {
            addCriterion("CLASSIFYID <=", value, "classifyid");
            return this;
        }

        public Criteria andClassifyidIn(List<Long> values) {
            addCriterion("CLASSIFYID in", values, "classifyid");
            return this;
        }

        public Criteria andClassifyidNotIn(List<Long> values) {
            addCriterion("CLASSIFYID not in", values, "classifyid");
            return this;
        }

        public Criteria andClassifyidBetween(Long value1, Long value2) {
            addCriterion("CLASSIFYID between", value1, value2, "classifyid");
            return this;
        }

        public Criteria andClassifyidNotBetween(Long value1, Long value2) {
            addCriterion("CLASSIFYID not between", value1, value2, "classifyid");
            return this;
        }
    }
}