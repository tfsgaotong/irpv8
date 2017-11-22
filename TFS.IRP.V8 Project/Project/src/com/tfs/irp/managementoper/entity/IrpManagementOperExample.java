package com.tfs.irp.managementoper.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample.Criteria;

public class IrpManagementOperExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public IrpManagementOperExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected IrpManagementOperExample(IrpManagementOperExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected abstract static class GeneratedCriteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected GeneratedCriteria() {
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

        public Criteria andManagementoperidIsNull() {
            addCriterion("MANAGEMENTOPERID is null");
            return (Criteria) this;
        }

        public Criteria andManagementoperidIsNotNull() {
            addCriterion("MANAGEMENTOPERID is not null");
            return (Criteria) this;
        }

        public Criteria andManagementoperidEqualTo(Long value) {
            addCriterion("MANAGEMENTOPERID =", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidNotEqualTo(Long value) {
            addCriterion("MANAGEMENTOPERID <>", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidGreaterThan(Long value) {
            addCriterion("MANAGEMENTOPERID >", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidGreaterThanOrEqualTo(Long value) {
            addCriterion("MANAGEMENTOPERID >=", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidLessThan(Long value) {
            addCriterion("MANAGEMENTOPERID <", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidLessThanOrEqualTo(Long value) {
            addCriterion("MANAGEMENTOPERID <=", value, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidIn(List<Long> values) {
            addCriterion("MANAGEMENTOPERID in", values, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidNotIn(List<Long> values) {
            addCriterion("MANAGEMENTOPERID not in", values, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidBetween(Long value1, Long value2) {
            addCriterion("MANAGEMENTOPERID between", value1, value2, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andManagementoperidNotBetween(Long value1, Long value2) {
            addCriterion("MANAGEMENTOPERID not between", value1, value2, "managementoperid");
            return (Criteria) this;
        }

        public Criteria andOpernameIsNull() {
            addCriterion("OPERNAME is null");
            return (Criteria) this;
        }

        public Criteria andOpernameIsNotNull() {
            addCriterion("OPERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOpernameEqualTo(String value) {
            addCriterion("OPERNAME =", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameNotEqualTo(String value) {
            addCriterion("OPERNAME <>", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameGreaterThan(String value) {
            addCriterion("OPERNAME >", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameGreaterThanOrEqualTo(String value) {
            addCriterion("OPERNAME >=", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameLessThan(String value) {
            addCriterion("OPERNAME <", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameLessThanOrEqualTo(String value) {
            addCriterion("OPERNAME <=", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameLike(String value) {
            addCriterion("OPERNAME like", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameNotLike(String value) {
            addCriterion("OPERNAME not like", value, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameIn(List<String> values) {
            addCriterion("OPERNAME in", values, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameNotIn(List<String> values) {
            addCriterion("OPERNAME not in", values, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameBetween(String value1, String value2) {
            addCriterion("OPERNAME between", value1, value2, "opername");
            return (Criteria) this;
        }

        public Criteria andOpernameNotBetween(String value1, String value2) {
            addCriterion("OPERNAME not between", value1, value2, "opername");
            return (Criteria) this;
        }

        public Criteria andOperdescIsNull() {
            addCriterion("OPERDESC is null");
            return (Criteria) this;
        }

        public Criteria andOperdescIsNotNull() {
            addCriterion("OPERDESC is not null");
            return (Criteria) this;
        }

        public Criteria andOperdescEqualTo(String value) {
            addCriterion("OPERDESC =", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescNotEqualTo(String value) {
            addCriterion("OPERDESC <>", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescGreaterThan(String value) {
            addCriterion("OPERDESC >", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescGreaterThanOrEqualTo(String value) {
            addCriterion("OPERDESC >=", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescLessThan(String value) {
            addCriterion("OPERDESC <", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescLessThanOrEqualTo(String value) {
            addCriterion("OPERDESC <=", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescLike(String value) {
            addCriterion("OPERDESC like", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescNotLike(String value) {
            addCriterion("OPERDESC not like", value, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescIn(List<String> values) {
            addCriterion("OPERDESC in", values, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescNotIn(List<String> values) {
            addCriterion("OPERDESC not in", values, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescBetween(String value1, String value2) {
            addCriterion("OPERDESC between", value1, value2, "operdesc");
            return (Criteria) this;
        }

        public Criteria andOperdescNotBetween(String value1, String value2) {
            addCriterion("OPERDESC not between", value1, value2, "operdesc");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Long value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Long value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Long value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Long value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Long value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Long> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Long> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Long value1, Long value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Long value1, Long value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andOperurisIsNull() {
            addCriterion("OPERURIS is null");
            return (Criteria) this;
        }

        public Criteria andOperurisIsNotNull() {
            addCriterion("OPERURIS is not null");
            return (Criteria) this;
        }

        public Criteria andOperurisEqualTo(String value) {
            addCriterion("OPERURIS =", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisNotEqualTo(String value) {
            addCriterion("OPERURIS <>", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisGreaterThan(String value) {
            addCriterion("OPERURIS >", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisGreaterThanOrEqualTo(String value) {
            addCriterion("OPERURIS >=", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisLessThan(String value) {
            addCriterion("OPERURIS <", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisLessThanOrEqualTo(String value) {
            addCriterion("OPERURIS <=", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisLike(String value) {
            addCriterion("OPERURIS like", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisNotLike(String value) {
            addCriterion("OPERURIS not like", value, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisIn(List<String> values) {
            addCriterion("OPERURIS in", values, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisNotIn(List<String> values) {
            addCriterion("OPERURIS not in", values, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisBetween(String value1, String value2) {
            addCriterion("OPERURIS between", value1, value2, "operuris");
            return (Criteria) this;
        }

        public Criteria andOperurisNotBetween(String value1, String value2) {
            addCriterion("OPERURIS not between", value1, value2, "operuris");
            return (Criteria) this;
        }
        public Criteria andRoletypeIsNull() {
            addCriterion("ROLETYPE is null");
            return (Criteria) this;
        }

        public Criteria andRoletypeIsNotNull() {
            addCriterion("ROLETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRoletypeEqualTo(Long value) {
            addCriterion("ROLETYPE =", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeNotEqualTo(Long value) {
            addCriterion("ROLETYPE <>", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeGreaterThan(Long value) {
            addCriterion("ROLETYPE >", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeGreaterThanOrEqualTo(Long value) {
            addCriterion("ROLETYPE >=", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeLessThan(Long value) {
            addCriterion("ROLETYPE <", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeLessThanOrEqualTo(Long value) {
            addCriterion("ROLETYPE <=", value, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeIn(List<Long> values) {
            addCriterion("ROLETYPE in", values, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeNotIn(List<Long> values) {
            addCriterion("ROLETYPE not in", values, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeBetween(Long value1, Long value2) {
            addCriterion("ROLETYPE between", value1, value2, "roletype");
            return (Criteria) this;
        }

        public Criteria andRoletypeNotBetween(Long value1, Long value2) {
            addCriterion("ROLETYPE not between", value1, value2, "roletype");
            return (Criteria) this;
        }
        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Long value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Long value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Long value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Long value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Long value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Long> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Long> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Long value1, Long value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Long value1, Long value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }
        public Criteria extSQL(String _sSql) {
			addCriterion(_sSql);
			return (Criteria) this;
		}
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 07 13:57:02 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}