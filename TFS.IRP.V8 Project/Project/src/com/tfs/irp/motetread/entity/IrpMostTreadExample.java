package com.tfs.irp.motetread.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpMostTreadExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public IrpMostTreadExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	protected IrpMostTreadExample(IrpMostTreadExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_MOST_TREAD
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition,
				List<? extends Object> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List<Object> list = new ArrayList<Object>();
			list.add(value1);
			list.add(value2);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		protected void addCriterionForJDBCDate(String condition, Date value,
				String property) {
			addCriterion(condition, new java.sql.Date(value.getTime()),
					property);
		}

		protected void addCriterionForJDBCDate(String condition,
				List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1,
				Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()),
					new java.sql.Date(value2.getTime()), property);
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
			addCriterionForJDBCDate("CRTIME not between", value1, value2,
					"crtime");
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

		public Criteria andStatusEqualTo(BigDecimal value) {
			addCriterion("STATUS =", value, "status");
			return this;
		}

		public Criteria andStatusNotEqualTo(BigDecimal value) {
			addCriterion("STATUS <>", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThan(BigDecimal value) {
			addCriterion("STATUS >", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("STATUS >=", value, "status");
			return this;
		}

		public Criteria andStatusLessThan(BigDecimal value) {
			addCriterion("STATUS <", value, "status");
			return this;
		}

		public Criteria andStatusLessThanOrEqualTo(BigDecimal value) {
			addCriterion("STATUS <=", value, "status");
			return this;
		}

		public Criteria andStatusIn(List<BigDecimal> values) {
			addCriterion("STATUS in", values, "status");
			return this;
		}

		public Criteria andStatusNotIn(List<BigDecimal> values) {
			addCriterion("STATUS not in", values, "status");
			return this;
		}

		public Criteria andStatusBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("STATUS between", value1, value2, "status");
			return this;
		}

		public Criteria andStatusNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("STATUS not between", value1, value2, "status");
			return this;
		}

		public Criteria andMosttypeIsNull() {
			addCriterion("MOSTTYPE is null");
			return this;
		}

		public Criteria andMosttypeIsNotNull() {
			addCriterion("MOSTTYPE is not null");
			return this;
		}

		public Criteria andMosttypeEqualTo(BigDecimal value) {
			addCriterion("MOSTTYPE =", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeNotEqualTo(BigDecimal value) {
			addCriterion("MOSTTYPE <>", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeGreaterThan(BigDecimal value) {
			addCriterion("MOSTTYPE >", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("MOSTTYPE >=", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeLessThan(BigDecimal value) {
			addCriterion("MOSTTYPE <", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("MOSTTYPE <=", value, "mosttype");
			return this;
		}

		public Criteria andMosttypeIn(List<BigDecimal> values) {
			addCriterion("MOSTTYPE in", values, "mosttype");
			return this;
		}

		public Criteria andMosttypeNotIn(List<BigDecimal> values) {
			addCriterion("MOSTTYPE not in", values, "mosttype");
			return this;
		}

		public Criteria andMosttypeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("MOSTTYPE between", value1, value2, "mosttype");
			return this;
		}

		public Criteria andMosttypeNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("MOSTTYPE not between", value1, value2, "mosttype");
			return this;
		}
	}
}