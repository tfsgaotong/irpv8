package com.tfs.irp.selectkey.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpSelectKeyExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public IrpSelectKeyExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	protected IrpSelectKeyExample(IrpSelectKeyExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_SELECT_KEY
	 * @ibatorgenerated  Thu Aug 15 17:44:44 CST 2013
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

		public Criteria andSidIsNull() {
			addCriterion("SID is null");
			return this;
		}

		public Criteria andSidIsNotNull() {
			addCriterion("SID is not null");
			return this;
		}

		public Criteria andSidEqualTo(Long value) {
			addCriterion("SID =", value, "sid");
			return this;
		}

		public Criteria andSidNotEqualTo(Long value) {
			addCriterion("SID <>", value, "sid");
			return this;
		}

		public Criteria andSidGreaterThan(Long value) {
			addCriterion("SID >", value, "sid");
			return this;
		}

		public Criteria andSidGreaterThanOrEqualTo(Long value) {
			addCriterion("SID >=", value, "sid");
			return this;
		}

		public Criteria andSidLessThan(Long value) {
			addCriterion("SID <", value, "sid");
			return this;
		}

		public Criteria andSidLessThanOrEqualTo(Long value) {
			addCriterion("SID <=", value, "sid");
			return this;
		}

		public Criteria andSidIn(List<Long> values) {
			addCriterion("SID in", values, "sid");
			return this;
		}

		public Criteria andSidNotIn(List<Long> values) {
			addCriterion("SID not in", values, "sid");
			return this;
		}

		public Criteria andSidBetween(Long value1, Long value2) {
			addCriterion("SID between", value1, value2, "sid");
			return this;
		}

		public Criteria andSidNotBetween(Long value1, Long value2) {
			addCriterion("SID not between", value1, value2, "sid");
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

		public Criteria andSkeyIsNull() {
			addCriterion("SKEY is null");
			return this;
		}

		public Criteria andSkeyIsNotNull() {
			addCriterion("SKEY is not null");
			return this;
		}

		public Criteria andSkeyEqualTo(String value) {
			addCriterion("SKEY =", value, "skey");
			return this;
		}

		public Criteria andSkeyNotEqualTo(String value) {
			addCriterion("SKEY <>", value, "skey");
			return this;
		}

		public Criteria andSkeyGreaterThan(String value) {
			addCriterion("SKEY >", value, "skey");
			return this;
		}

		public Criteria andSkeyGreaterThanOrEqualTo(String value) {
			addCriterion("SKEY >=", value, "skey");
			return this;
		}

		public Criteria andSkeyLessThan(String value) {
			addCriterion("SKEY <", value, "skey");
			return this;
		}

		public Criteria andSkeyLessThanOrEqualTo(String value) {
			addCriterion("SKEY <=", value, "skey");
			return this;
		}

		public Criteria andSkeyLike(String value) {
			addCriterion("SKEY like", value, "skey");
			return this;
		}

		public Criteria andSkeyNotLike(String value) {
			addCriterion("SKEY not like", value, "skey");
			return this;
		}

		public Criteria andSkeyIn(List<String> values) {
			addCriterion("SKEY in", values, "skey");
			return this;
		}

		public Criteria andSkeyNotIn(List<String> values) {
			addCriterion("SKEY not in", values, "skey");
			return this;
		}

		public Criteria andSkeyBetween(String value1, String value2) {
			addCriterion("SKEY between", value1, value2, "skey");
			return this;
		}

		public Criteria andSkeyNotBetween(String value1, String value2) {
			addCriterion("SKEY not between", value1, value2, "skey");
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

		public Criteria andUrlIsNull() {
			addCriterion("URL is null");
			return this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("URL is not null");
			return this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("URL =", value, "url");
			return this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("URL <>", value, "url");
			return this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("URL >", value, "url");
			return this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("URL >=", value, "url");
			return this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("URL <", value, "url");
			return this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("URL <=", value, "url");
			return this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("URL like", value, "url");
			return this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("URL not like", value, "url");
			return this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("URL in", values, "url");
			return this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("URL not in", values, "url");
			return this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("URL between", value1, value2, "url");
			return this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("URL not between", value1, value2, "url");
			return this;
		}

		public Criteria andUseripIsNull() {
			addCriterion("USERIP is null");
			return this;
		}

		public Criteria andUseripIsNotNull() {
			addCriterion("USERIP is not null");
			return this;
		}

		public Criteria andUseripEqualTo(String value) {
			addCriterion("USERIP =", value, "userip");
			return this;
		}

		public Criteria andUseripNotEqualTo(String value) {
			addCriterion("USERIP <>", value, "userip");
			return this;
		}

		public Criteria andUseripGreaterThan(String value) {
			addCriterion("USERIP >", value, "userip");
			return this;
		}

		public Criteria andUseripGreaterThanOrEqualTo(String value) {
			addCriterion("USERIP >=", value, "userip");
			return this;
		}

		public Criteria andUseripLessThan(String value) {
			addCriterion("USERIP <", value, "userip");
			return this;
		}

		public Criteria andUseripLessThanOrEqualTo(String value) {
			addCriterion("USERIP <=", value, "userip");
			return this;
		}

		public Criteria andUseripLike(String value) {
			addCriterion("USERIP like", value, "userip");
			return this;
		}

		public Criteria andUseripNotLike(String value) {
			addCriterion("USERIP not like", value, "userip");
			return this;
		}

		public Criteria andUseripIn(List<String> values) {
			addCriterion("USERIP in", values, "userip");
			return this;
		}

		public Criteria andUseripNotIn(List<String> values) {
			addCriterion("USERIP not in", values, "userip");
			return this;
		}

		public Criteria andUseripBetween(String value1, String value2) {
			addCriterion("USERIP between", value1, value2, "userip");
			return this;
		}

		public Criteria andUseripNotBetween(String value1, String value2) {
			addCriterion("USERIP not between", value1, value2, "userip");
			return this;
		}
	}
}