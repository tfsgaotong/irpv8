package com.tfs.irp.apptype.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpApptypeExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public IrpApptypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    protected IrpApptypeExample(IrpApptypeExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
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
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
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

        public Criteria andAppypeidIsNull() {
            addCriterion("APPYPEID is null");
            return this;
        }

        public Criteria andAppypeidIsNotNull() {
            addCriterion("APPYPEID is not null");
            return this;
        }

        public Criteria andAppypeidEqualTo(Long value) {
            addCriterion("APPYPEID =", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidNotEqualTo(Long value) {
            addCriterion("APPYPEID <>", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidGreaterThan(Long value) {
            addCriterion("APPYPEID >", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidGreaterThanOrEqualTo(Long value) {
            addCriterion("APPYPEID >=", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidLessThan(Long value) {
            addCriterion("APPYPEID <", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidLessThanOrEqualTo(Long value) {
            addCriterion("APPYPEID <=", value, "appypeid");
            return this;
        }

        public Criteria andAppypeidIn(List<Long> values) {
            addCriterion("APPYPEID in", values, "appypeid");
            return this;
        }

        public Criteria andAppypeidNotIn(List<Long> values) {
            addCriterion("APPYPEID not in", values, "appypeid");
            return this;
        }

        public Criteria andAppypeidBetween(Long value1, Long value2) {
            addCriterion("APPYPEID between", value1, value2, "appypeid");
            return this;
        }

        public Criteria andAppypeidNotBetween(Long value1, Long value2) {
            addCriterion("APPYPEID not between", value1, value2, "appypeid");
            return this;
        }

        public Criteria andAppnameIsNull() {
            addCriterion("APPNAME is null");
            return this;
        }

        public Criteria andAppnameIsNotNull() {
            addCriterion("APPNAME is not null");
            return this;
        }

        public Criteria andAppnameEqualTo(String value) {
            addCriterion("APPNAME =", value, "appname");
            return this;
        }

        public Criteria andAppnameNotEqualTo(String value) {
            addCriterion("APPNAME <>", value, "appname");
            return this;
        }

        public Criteria andAppnameGreaterThan(String value) {
            addCriterion("APPNAME >", value, "appname");
            return this;
        }

        public Criteria andAppnameGreaterThanOrEqualTo(String value) {
            addCriterion("APPNAME >=", value, "appname");
            return this;
        }

        public Criteria andAppnameLessThan(String value) {
            addCriterion("APPNAME <", value, "appname");
            return this;
        }

        public Criteria andAppnameLessThanOrEqualTo(String value) {
            addCriterion("APPNAME <=", value, "appname");
            return this;
        }

        public Criteria andAppnameLike(String value) {
            addCriterion("APPNAME like", value, "appname");
            return this;
        }

        public Criteria andAppnameNotLike(String value) {
            addCriterion("APPNAME not like", value, "appname");
            return this;
        }

        public Criteria andAppnameIn(List<String> values) {
            addCriterion("APPNAME in", values, "appname");
            return this;
        }

        public Criteria andAppnameNotIn(List<String> values) {
            addCriterion("APPNAME not in", values, "appname");
            return this;
        }

        public Criteria andAppnameBetween(String value1, String value2) {
            addCriterion("APPNAME between", value1, value2, "appname");
            return this;
        }

        public Criteria andAppnameNotBetween(String value1, String value2) {
            addCriterion("APPNAME not between", value1, value2, "appname");
            return this;
        }

        public Criteria andAppdescIsNull() {
            addCriterion("APPDESC is null");
            return this;
        }

        public Criteria andAppdescIsNotNull() {
            addCriterion("APPDESC is not null");
            return this;
        }

        public Criteria andAppdescEqualTo(String value) {
            addCriterion("APPDESC =", value, "appdesc");
            return this;
        }

        public Criteria andAppdescNotEqualTo(String value) {
            addCriterion("APPDESC <>", value, "appdesc");
            return this;
        }

        public Criteria andAppdescGreaterThan(String value) {
            addCriterion("APPDESC >", value, "appdesc");
            return this;
        }

        public Criteria andAppdescGreaterThanOrEqualTo(String value) {
            addCriterion("APPDESC >=", value, "appdesc");
            return this;
        }

        public Criteria andAppdescLessThan(String value) {
            addCriterion("APPDESC <", value, "appdesc");
            return this;
        }

        public Criteria andAppdescLessThanOrEqualTo(String value) {
            addCriterion("APPDESC <=", value, "appdesc");
            return this;
        }

        public Criteria andAppdescLike(String value) {
            addCriterion("APPDESC like", value, "appdesc");
            return this;
        }

        public Criteria andAppdescNotLike(String value) {
            addCriterion("APPDESC not like", value, "appdesc");
            return this;
        }

        public Criteria andAppdescIn(List<String> values) {
            addCriterion("APPDESC in", values, "appdesc");
            return this;
        }

        public Criteria andAppdescNotIn(List<String> values) {
            addCriterion("APPDESC not in", values, "appdesc");
            return this;
        }

        public Criteria andAppdescBetween(String value1, String value2) {
            addCriterion("APPDESC between", value1, value2, "appdesc");
            return this;
        }

        public Criteria andAppdescNotBetween(String value1, String value2) {
            addCriterion("APPDESC not between", value1, value2, "appdesc");
            return this;
        }
    }
}