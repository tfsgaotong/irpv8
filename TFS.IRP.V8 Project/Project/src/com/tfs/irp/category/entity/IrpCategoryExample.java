package com.tfs.irp.category.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpCategoryExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public IrpCategoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    protected IrpCategoryExample(IrpCategoryExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
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
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_category
     *
     * @ibatorgenerated Wed Mar 19 09:23:56 CST 2014
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

        public Criteria andCategoryidIsNull() {
            addCriterion("CATEGORYID is null");
            return this;
        }

        public Criteria andCategoryidIsNotNull() {
            addCriterion("CATEGORYID is not null");
            return this;
        }

        public Criteria andCategoryidEqualTo(Long value) {
            addCriterion("CATEGORYID =", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidNotEqualTo(Long value) {
            addCriterion("CATEGORYID <>", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidGreaterThan(Long value) {
            addCriterion("CATEGORYID >", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidGreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORYID >=", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidLessThan(Long value) {
            addCriterion("CATEGORYID <", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidLessThanOrEqualTo(Long value) {
            addCriterion("CATEGORYID <=", value, "categoryid");
            return this;
        }

        public Criteria andCategoryidIn(List<Long> values) {
            addCriterion("CATEGORYID in", values, "categoryid");
            return this;
        }

        public Criteria andCategoryidNotIn(List<Long> values) {
            addCriterion("CATEGORYID not in", values, "categoryid");
            return this;
        }

        public Criteria andCategoryidBetween(Long value1, Long value2) {
            addCriterion("CATEGORYID between", value1, value2, "categoryid");
            return this;
        }

        public Criteria andCategoryidNotBetween(Long value1, Long value2) {
            addCriterion("CATEGORYID not between", value1, value2, "categoryid");
            return this;
        }

        public Criteria andCparentidIsNull() {
            addCriterion("CPARENTID is null");
            return this;
        }

        public Criteria andCparentidIsNotNull() {
            addCriterion("CPARENTID is not null");
            return this;
        }

        public Criteria andCparentidEqualTo(Long value) {
            addCriterion("CPARENTID =", value, "cparentid");
            return this;
        }

        public Criteria andCparentidNotEqualTo(Long value) {
            addCriterion("CPARENTID <>", value, "cparentid");
            return this;
        }

        public Criteria andCparentidGreaterThan(Long value) {
            addCriterion("CPARENTID >", value, "cparentid");
            return this;
        }

        public Criteria andCparentidGreaterThanOrEqualTo(Long value) {
            addCriterion("CPARENTID >=", value, "cparentid");
            return this;
        }

        public Criteria andCparentidLessThan(Long value) {
            addCriterion("CPARENTID <", value, "cparentid");
            return this;
        }

        public Criteria andCparentidLessThanOrEqualTo(Long value) {
            addCriterion("CPARENTID <=", value, "cparentid");
            return this;
        }

        public Criteria andCparentidIn(List<Long> values) {
            addCriterion("CPARENTID in", values, "cparentid");
            return this;
        }

        public Criteria andCparentidNotIn(List<Long> values) {
            addCriterion("CPARENTID not in", values, "cparentid");
            return this;
        }

        public Criteria andCparentidBetween(Long value1, Long value2) {
            addCriterion("CPARENTID between", value1, value2, "cparentid");
            return this;
        }

        public Criteria andCparentidNotBetween(Long value1, Long value2) {
            addCriterion("CPARENTID not between", value1, value2, "cparentid");
            return this;
        }

        public Criteria andCnameIsNull() {
            addCriterion("CNAME is null");
            return this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("CNAME is not null");
            return this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("CNAME =", value, "cname");
            return this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("CNAME <>", value, "cname");
            return this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("CNAME >", value, "cname");
            return this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("CNAME >=", value, "cname");
            return this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("CNAME <", value, "cname");
            return this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("CNAME <=", value, "cname");
            return this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("CNAME like", value, "cname");
            return this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("CNAME not like", value, "cname");
            return this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("CNAME in", values, "cname");
            return this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("CNAME not in", values, "cname");
            return this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("CNAME between", value1, value2, "cname");
            return this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("CNAME not between", value1, value2, "cname");
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

        public Criteria andCategoryorderIsNull() {
            addCriterion("CATEGORYORDER is null");
            return this;
        }

        public Criteria andCategoryorderIsNotNull() {
            addCriterion("CATEGORYORDER is not null");
            return this;
        }

        public Criteria andCategoryorderEqualTo(Long value) {
            addCriterion("CATEGORYORDER =", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderNotEqualTo(Long value) {
            addCriterion("CATEGORYORDER <>", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderGreaterThan(Long value) {
            addCriterion("CATEGORYORDER >", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderGreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORYORDER >=", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderLessThan(Long value) {
            addCriterion("CATEGORYORDER <", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderLessThanOrEqualTo(Long value) {
            addCriterion("CATEGORYORDER <=", value, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderIn(List<Long> values) {
            addCriterion("CATEGORYORDER in", values, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderNotIn(List<Long> values) {
            addCriterion("CATEGORYORDER not in", values, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderBetween(Long value1, Long value2) {
            addCriterion("CATEGORYORDER between", value1, value2, "categoryorder");
            return this;
        }

        public Criteria andCategoryorderNotBetween(Long value1, Long value2) {
            addCriterion("CATEGORYORDER not between", value1, value2, "categoryorder");
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

        public Criteria andLinkurlIsNull() {
            addCriterion("LINKURL is null");
            return this;
        }

        public Criteria andLinkurlIsNotNull() {
            addCriterion("LINKURL is not null");
            return this;
        }

        public Criteria andLinkurlEqualTo(String value) {
            addCriterion("LINKURL =", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlNotEqualTo(String value) {
            addCriterion("LINKURL <>", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlGreaterThan(String value) {
            addCriterion("LINKURL >", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlGreaterThanOrEqualTo(String value) {
            addCriterion("LINKURL >=", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlLessThan(String value) {
            addCriterion("LINKURL <", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlLessThanOrEqualTo(String value) {
            addCriterion("LINKURL <=", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlLike(String value) {
            addCriterion("LINKURL like", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlNotLike(String value) {
            addCriterion("LINKURL not like", value, "linkurl");
            return this;
        }

        public Criteria andLinkurlIn(List<String> values) {
            addCriterion("LINKURL in", values, "linkurl");
            return this;
        }

        public Criteria andLinkurlNotIn(List<String> values) {
            addCriterion("LINKURL not in", values, "linkurl");
            return this;
        }

        public Criteria andLinkurlBetween(String value1, String value2) {
            addCriterion("LINKURL between", value1, value2, "linkurl");
            return this;
        }

        public Criteria andLinkurlNotBetween(String value1, String value2) {
            addCriterion("LINKURL not between", value1, value2, "linkurl");
            return this;
        }

        public Criteria andCategorypicIsNull() {
            addCriterion("CATEGORYPIC is null");
            return this;
        }

        public Criteria andCategorypicIsNotNull() {
            addCriterion("CATEGORYPIC is not null");
            return this;
        }

        public Criteria andCategorypicEqualTo(String value) {
            addCriterion("CATEGORYPIC =", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicNotEqualTo(String value) {
            addCriterion("CATEGORYPIC <>", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicGreaterThan(String value) {
            addCriterion("CATEGORYPIC >", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORYPIC >=", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicLessThan(String value) {
            addCriterion("CATEGORYPIC <", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicLessThanOrEqualTo(String value) {
            addCriterion("CATEGORYPIC <=", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicLike(String value) {
            addCriterion("CATEGORYPIC like", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicNotLike(String value) {
            addCriterion("CATEGORYPIC not like", value, "categorypic");
            return this;
        }

        public Criteria andCategorypicIn(List<String> values) {
            addCriterion("CATEGORYPIC in", values, "categorypic");
            return this;
        }

        public Criteria andCategorypicNotIn(List<String> values) {
            addCriterion("CATEGORYPIC not in", values, "categorypic");
            return this;
        }

        public Criteria andCategorypicBetween(String value1, String value2) {
            addCriterion("CATEGORYPIC between", value1, value2, "categorypic");
            return this;
        }

        public Criteria andCategorypicNotBetween(String value1, String value2) {
            addCriterion("CATEGORYPIC not between", value1, value2, "categorypic");
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

        public Criteria andCrtimeIsNull() {
            addCriterion("CRTIME is null");
            return this;
        }

        public Criteria andCrtimeIsNotNull() {
            addCriterion("CRTIME is not null");
            return this;
        }

        public Criteria andCrtimeEqualTo(Date value) {
            addCriterion("CRTIME =", value, "crtime");
            return this;
        }

        public Criteria andCrtimeNotEqualTo(Date value) {
            addCriterion("CRTIME <>", value, "crtime");
            return this;
        }

        public Criteria andCrtimeGreaterThan(Date value) {
            addCriterion("CRTIME >", value, "crtime");
            return this;
        }

        public Criteria andCrtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CRTIME >=", value, "crtime");
            return this;
        }

        public Criteria andCrtimeLessThan(Date value) {
            addCriterion("CRTIME <", value, "crtime");
            return this;
        }

        public Criteria andCrtimeLessThanOrEqualTo(Date value) {
            addCriterion("CRTIME <=", value, "crtime");
            return this;
        }

        public Criteria andCrtimeIn(List<Date> values) {
            addCriterion("CRTIME in", values, "crtime");
            return this;
        }

        public Criteria andCrtimeNotIn(List<Date> values) {
            addCriterion("CRTIME not in", values, "crtime");
            return this;
        }

        public Criteria andCrtimeBetween(Date value1, Date value2) {
            addCriterion("CRTIME between", value1, value2, "crtime");
            return this;
        }

        public Criteria andCrtimeNotBetween(Date value1, Date value2) {
            addCriterion("CRTIME not between", value1, value2, "crtime");
            return this;
        }
    }
}