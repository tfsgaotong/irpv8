package com.tfs.irp.config.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpConfigExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public IrpConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    protected IrpConfigExample(IrpConfigExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
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
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
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

        public Criteria andConfigidIsNull() {
            addCriterion("CONFIGID is null");
            return this;
        }

        public Criteria andConfigidIsNotNull() {
            addCriterion("CONFIGID is not null");
            return this;
        }

        public Criteria andConfigidEqualTo(Long value) {
            addCriterion("CONFIGID =", value, "configid");
            return this;
        }

        public Criteria andConfigidNotEqualTo(Long value) {
            addCriterion("CONFIGID <>", value, "configid");
            return this;
        }

        public Criteria andConfigidGreaterThan(Long value) {
            addCriterion("CONFIGID >", value, "configid");
            return this;
        }

        public Criteria andConfigidGreaterThanOrEqualTo(Long value) {
            addCriterion("CONFIGID >=", value, "configid");
            return this;
        }

        public Criteria andConfigidLessThan(Long value) {
            addCriterion("CONFIGID <", value, "configid");
            return this;
        }

        public Criteria andConfigidLessThanOrEqualTo(Long value) {
            addCriterion("CONFIGID <=", value, "configid");
            return this;
        }

        public Criteria andConfigidIn(List<Long> values) {
            addCriterion("CONFIGID in", values, "configid");
            return this;
        }

        public Criteria andConfigidNotIn(List<Long> values) {
            addCriterion("CONFIGID not in", values, "configid");
            return this;
        }

        public Criteria andConfigidBetween(Long value1, Long value2) {
            addCriterion("CONFIGID between", value1, value2, "configid");
            return this;
        }

        public Criteria andConfigidNotBetween(Long value1, Long value2) {
            addCriterion("CONFIGID not between", value1, value2, "configid");
            return this;
        }

        public Criteria andCtypeIsNull() {
            addCriterion("CTYPE is null");
            return this;
        }

        public Criteria andCtypeIsNotNull() {
            addCriterion("CTYPE is not null");
            return this;
        }

        public Criteria andCtypeEqualTo(Integer value) {
            addCriterion("CTYPE =", value, "ctype");
            return this;
        }

        public Criteria andCtypeNotEqualTo(Integer value) {
            addCriterion("CTYPE <>", value, "ctype");
            return this;
        }

        public Criteria andCtypeGreaterThan(Integer value) {
            addCriterion("CTYPE >", value, "ctype");
            return this;
        }

        public Criteria andCtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CTYPE >=", value, "ctype");
            return this;
        }

        public Criteria andCtypeLessThan(Integer value) {
            addCriterion("CTYPE <", value, "ctype");
            return this;
        }

        public Criteria andCtypeLessThanOrEqualTo(Integer value) {
            addCriterion("CTYPE <=", value, "ctype");
            return this;
        }

        public Criteria andCtypeIn(List<Integer> values) {
            addCriterion("CTYPE in", values, "ctype");
            return this;
        }

        public Criteria andCtypeNotIn(List<Integer> values) {
            addCriterion("CTYPE not in", values, "ctype");
            return this;
        }

        public Criteria andCtypeBetween(Integer value1, Integer value2) {
            addCriterion("CTYPE between", value1, value2, "ctype");
            return this;
        }

        public Criteria andCtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("CTYPE not between", value1, value2, "ctype");
            return this;
        }

        public Criteria andCkeyIsNull() {
            addCriterion("CKEY is null");
            return this;
        }

        public Criteria andCkeyIsNotNull() {
            addCriterion("CKEY is not null");
            return this;
        }

        public Criteria andCkeyEqualTo(String value) {
            addCriterion("CKEY =", value, "ckey");
            return this;
        }

        public Criteria andCkeyNotEqualTo(String value) {
            addCriterion("CKEY <>", value, "ckey");
            return this;
        }

        public Criteria andCkeyGreaterThan(String value) {
            addCriterion("CKEY >", value, "ckey");
            return this;
        }

        public Criteria andCkeyGreaterThanOrEqualTo(String value) {
            addCriterion("CKEY >=", value, "ckey");
            return this;
        }

        public Criteria andCkeyLessThan(String value) {
            addCriterion("CKEY <", value, "ckey");
            return this;
        }

        public Criteria andCkeyLessThanOrEqualTo(String value) {
            addCriterion("CKEY <=", value, "ckey");
            return this;
        }

        public Criteria andCkeyLike(String value) {
            addCriterion("CKEY like", value, "ckey");
            return this;
        }

        public Criteria andCkeyNotLike(String value) {
            addCriterion("CKEY not like", value, "ckey");
            return this;
        }

        public Criteria andCkeyIn(List<String> values) {
            addCriterion("CKEY in", values, "ckey");
            return this;
        }

        public Criteria andCkeyNotIn(List<String> values) {
            addCriterion("CKEY not in", values, "ckey");
            return this;
        }

        public Criteria andCkeyBetween(String value1, String value2) {
            addCriterion("CKEY between", value1, value2, "ckey");
            return this;
        }

        public Criteria andCkeyNotBetween(String value1, String value2) {
            addCriterion("CKEY not between", value1, value2, "ckey");
            return this;
        }

        public Criteria andCvalueIsNull() {
            addCriterion("CVALUE is null");
            return this;
        }

        public Criteria andCvalueIsNotNull() {
            addCriterion("CVALUE is not null");
            return this;
        }

        public Criteria andCvalueEqualTo(String value) {
            addCriterion("CVALUE =", value, "cvalue");
            return this;
        }

        public Criteria andCvalueNotEqualTo(String value) {
            addCriterion("CVALUE <>", value, "cvalue");
            return this;
        }

        public Criteria andCvalueGreaterThan(String value) {
            addCriterion("CVALUE >", value, "cvalue");
            return this;
        }

        public Criteria andCvalueGreaterThanOrEqualTo(String value) {
            addCriterion("CVALUE >=", value, "cvalue");
            return this;
        }

        public Criteria andCvalueLessThan(String value) {
            addCriterion("CVALUE <", value, "cvalue");
            return this;
        }

        public Criteria andCvalueLessThanOrEqualTo(String value) {
            addCriterion("CVALUE <=", value, "cvalue");
            return this;
        }

        public Criteria andCvalueLike(String value) {
            addCriterion("CVALUE like", value, "cvalue");
            return this;
        }

        public Criteria andCvalueNotLike(String value) {
            addCriterion("CVALUE not like", value, "cvalue");
            return this;
        }

        public Criteria andCvalueIn(List<String> values) {
            addCriterion("CVALUE in", values, "cvalue");
            return this;
        }

        public Criteria andCvalueNotIn(List<String> values) {
            addCriterion("CVALUE not in", values, "cvalue");
            return this;
        }

        public Criteria andCvalueBetween(String value1, String value2) {
            addCriterion("CVALUE between", value1, value2, "cvalue");
            return this;
        }

        public Criteria andCvalueNotBetween(String value1, String value2) {
            addCriterion("CVALUE not between", value1, value2, "cvalue");
            return this;
        }

        public Criteria andCdescIsNull() {
            addCriterion("CDESC is null");
            return this;
        }

        public Criteria andCdescIsNotNull() {
            addCriterion("CDESC is not null");
            return this;
        }

        public Criteria andCdescEqualTo(String value) {
            addCriterion("CDESC =", value, "cdesc");
            return this;
        }

        public Criteria andCdescNotEqualTo(String value) {
            addCriterion("CDESC <>", value, "cdesc");
            return this;
        }

        public Criteria andCdescGreaterThan(String value) {
            addCriterion("CDESC >", value, "cdesc");
            return this;
        }

        public Criteria andCdescGreaterThanOrEqualTo(String value) {
            addCriterion("CDESC >=", value, "cdesc");
            return this;
        }

        public Criteria andCdescLessThan(String value) {
            addCriterion("CDESC <", value, "cdesc");
            return this;
        }

        public Criteria andCdescLessThanOrEqualTo(String value) {
            addCriterion("CDESC <=", value, "cdesc");
            return this;
        }

        public Criteria andCdescLike(String value) {
            addCriterion("CDESC like", value, "cdesc");
            return this;
        }

        public Criteria andCdescNotLike(String value) {
            addCriterion("CDESC not like", value, "cdesc");
            return this;
        }

        public Criteria andCdescIn(List<String> values) {
            addCriterion("CDESC in", values, "cdesc");
            return this;
        }

        public Criteria andCdescNotIn(List<String> values) {
            addCriterion("CDESC not in", values, "cdesc");
            return this;
        }

        public Criteria andCdescBetween(String value1, String value2) {
            addCriterion("CDESC between", value1, value2, "cdesc");
            return this;
        }

        public Criteria andCdescNotBetween(String value1, String value2) {
            addCriterion("CDESC not between", value1, value2, "cdesc");
            return this;
        }

        public Criteria andEncryptedIsNull() {
            addCriterion("ENCRYPTED is null");
            return this;
        }

        public Criteria andEncryptedIsNotNull() {
            addCriterion("ENCRYPTED is not null");
            return this;
        }

        public Criteria andEncryptedEqualTo(Integer value) {
            addCriterion("ENCRYPTED =", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedNotEqualTo(Integer value) {
            addCriterion("ENCRYPTED <>", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedGreaterThan(Integer value) {
            addCriterion("ENCRYPTED >", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedGreaterThanOrEqualTo(Integer value) {
            addCriterion("ENCRYPTED >=", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedLessThan(Integer value) {
            addCriterion("ENCRYPTED <", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedLessThanOrEqualTo(Integer value) {
            addCriterion("ENCRYPTED <=", value, "encrypted");
            return this;
        }

        public Criteria andEncryptedIn(List<Integer> values) {
            addCriterion("ENCRYPTED in", values, "encrypted");
            return this;
        }

        public Criteria andEncryptedNotIn(List<Integer> values) {
            addCriterion("ENCRYPTED not in", values, "encrypted");
            return this;
        }

        public Criteria andEncryptedBetween(Integer value1, Integer value2) {
            addCriterion("ENCRYPTED between", value1, value2, "encrypted");
            return this;
        }

        public Criteria andEncryptedNotBetween(Integer value1, Integer value2) {
            addCriterion("ENCRYPTED not between", value1, value2, "encrypted");
            return this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("MODIFIED is null");
            return this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("MODIFIED is not null");
            return this;
        }

        public Criteria andModifiedEqualTo(Integer value) {
            addCriterion("MODIFIED =", value, "modified");
            return this;
        }

        public Criteria andModifiedNotEqualTo(Integer value) {
            addCriterion("MODIFIED <>", value, "modified");
            return this;
        }

        public Criteria andModifiedGreaterThan(Integer value) {
            addCriterion("MODIFIED >", value, "modified");
            return this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Integer value) {
            addCriterion("MODIFIED >=", value, "modified");
            return this;
        }

        public Criteria andModifiedLessThan(Integer value) {
            addCriterion("MODIFIED <", value, "modified");
            return this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Integer value) {
            addCriterion("MODIFIED <=", value, "modified");
            return this;
        }

        public Criteria andModifiedIn(List<Integer> values) {
            addCriterion("MODIFIED in", values, "modified");
            return this;
        }

        public Criteria andModifiedNotIn(List<Integer> values) {
            addCriterion("MODIFIED not in", values, "modified");
            return this;
        }

        public Criteria andModifiedBetween(Integer value1, Integer value2) {
            addCriterion("MODIFIED between", value1, value2, "modified");
            return this;
        }

        public Criteria andModifiedNotBetween(Integer value1, Integer value2) {
            addCriterion("MODIFIED not between", value1, value2, "modified");
            return this;
        }

        public Criteria andSiteidIsNull() {
            addCriterion("SITEID is null");
            return this;
        }

        public Criteria andSiteidIsNotNull() {
            addCriterion("SITEID is not null");
            return this;
        }

        public Criteria andSiteidEqualTo(Long value) {
            addCriterion("SITEID =", value, "siteid");
            return this;
        }

        public Criteria andSiteidNotEqualTo(Long value) {
            addCriterion("SITEID <>", value, "siteid");
            return this;
        }

        public Criteria andSiteidGreaterThan(Long value) {
            addCriterion("SITEID >", value, "siteid");
            return this;
        }

        public Criteria andSiteidGreaterThanOrEqualTo(Long value) {
            addCriterion("SITEID >=", value, "siteid");
            return this;
        }

        public Criteria andSiteidLessThan(Long value) {
            addCriterion("SITEID <", value, "siteid");
            return this;
        }

        public Criteria andSiteidLessThanOrEqualTo(Long value) {
            addCriterion("SITEID <=", value, "siteid");
            return this;
        }

        public Criteria andSiteidIn(List<Long> values) {
            addCriterion("SITEID in", values, "siteid");
            return this;
        }

        public Criteria andSiteidNotIn(List<Long> values) {
            addCriterion("SITEID not in", values, "siteid");
            return this;
        }

        public Criteria andSiteidBetween(Long value1, Long value2) {
            addCriterion("SITEID between", value1, value2, "siteid");
            return this;
        }

        public Criteria andSiteidNotBetween(Long value1, Long value2) {
            addCriterion("SITEID not between", value1, value2, "siteid");
            return this;
        }
    }
}