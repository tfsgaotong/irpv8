package com.tfs.irp.userapp.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpUserappExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public IrpUserappExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	protected IrpUserappExample(IrpUserappExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_USER_APP
	 * @ibatorgenerated  Fri Oct 11 11:05:19 CST 2013
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

		public Criteria andApplistidIsNull() {
			addCriterion("APPLISTID is null");
			return this;
		}

		public Criteria andApplistidIsNotNull() {
			addCriterion("APPLISTID is not null");
			return this;
		}

		public Criteria andApplistidEqualTo(Long value) {
			addCriterion("APPLISTID =", value, "applistid");
			return this;
		}

		public Criteria andApplistidNotEqualTo(Long value) {
			addCriterion("APPLISTID <>", value, "applistid");
			return this;
		}

		public Criteria andApplistidGreaterThan(Long value) {
			addCriterion("APPLISTID >", value, "applistid");
			return this;
		}

		public Criteria andApplistidGreaterThanOrEqualTo(Long value) {
			addCriterion("APPLISTID >=", value, "applistid");
			return this;
		}

		public Criteria andApplistidLessThan(Long value) {
			addCriterion("APPLISTID <", value, "applistid");
			return this;
		}

		public Criteria andApplistidLessThanOrEqualTo(Long value) {
			addCriterion("APPLISTID <=", value, "applistid");
			return this;
		}

		public Criteria andApplistidIn(List<Long> values) {
			addCriterion("APPLISTID in", values, "applistid");
			return this;
		}

		public Criteria andApplistidNotIn(List<Long> values) {
			addCriterion("APPLISTID not in", values, "applistid");
			return this;
		}

		public Criteria andApplistidBetween(Long value1, Long value2) {
			addCriterion("APPLISTID between", value1, value2, "applistid");
			return this;
		}

		public Criteria andApplistidNotBetween(Long value1, Long value2) {
			addCriterion("APPLISTID not between", value1, value2, "applistid");
			return this;
		}

		public Criteria andIsdisplayIsNull() {
			addCriterion("ISDISPLAY is null");
			return this;
		}

		public Criteria andIsdisplayIsNotNull() {
			addCriterion("ISDISPLAY is not null");
			return this;
		}

		public Criteria andIsdisplayEqualTo(Integer value) {
			addCriterion("ISDISPLAY =", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayNotEqualTo(Integer value) {
			addCriterion("ISDISPLAY <>", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayGreaterThan(Integer value) {
			addCriterion("ISDISPLAY >", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayGreaterThanOrEqualTo(Integer value) {
			addCriterion("ISDISPLAY >=", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayLessThan(Integer value) {
			addCriterion("ISDISPLAY <", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayLessThanOrEqualTo(Integer value) {
			addCriterion("ISDISPLAY <=", value, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayIn(List<Integer> values) {
			addCriterion("ISDISPLAY in", values, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayNotIn(List<Integer> values) {
			addCriterion("ISDISPLAY not in", values, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayBetween(Integer value1, Integer value2) {
			addCriterion("ISDISPLAY between", value1, value2, "isdisplay");
			return this;
		}

		public Criteria andIsdisplayNotBetween(Integer value1, Integer value2) {
			addCriterion("ISDISPLAY not between", value1, value2, "isdisplay");
			return this;
		}
	}
}