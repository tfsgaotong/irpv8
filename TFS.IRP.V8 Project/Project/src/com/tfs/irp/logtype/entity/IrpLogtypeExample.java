package com.tfs.irp.logtype.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpLogtypeExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public IrpLogtypeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	protected IrpLogtypeExample(IrpLogtypeExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_LOGTYPE
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
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

		public Criteria andLogtypeidIsNull() {
			addCriterion("LOGTYPEID is null");
			return this;
		}

		public Criteria andLogtypeidIsNotNull() {
			addCriterion("LOGTYPEID is not null");
			return this;
		}

		public Criteria andLogtypeidEqualTo(Integer value) {
			addCriterion("LOGTYPEID =", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidNotEqualTo(Integer value) {
			addCriterion("LOGTYPEID <>", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidGreaterThan(Integer value) {
			addCriterion("LOGTYPEID >", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidGreaterThanOrEqualTo(Integer value) {
			addCriterion("LOGTYPEID >=", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidLessThan(Integer value) {
			addCriterion("LOGTYPEID <", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidLessThanOrEqualTo(Integer value) {
			addCriterion("LOGTYPEID <=", value, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidIn(List<Integer> values) {
			addCriterion("LOGTYPEID in", values, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidNotIn(List<Integer> values) {
			addCriterion("LOGTYPEID not in", values, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidBetween(Integer value1, Integer value2) {
			addCriterion("LOGTYPEID between", value1, value2, "logtypeid");
			return this;
		}

		public Criteria andLogtypeidNotBetween(Integer value1, Integer value2) {
			addCriterion("LOGTYPEID not between", value1, value2, "logtypeid");
			return this;
		}

		public Criteria andTypenameIsNull() {
			addCriterion("TYPENAME is null");
			return this;
		}

		public Criteria andTypenameIsNotNull() {
			addCriterion("TYPENAME is not null");
			return this;
		}

		public Criteria andTypenameEqualTo(String value) {
			addCriterion("TYPENAME =", value, "typename");
			return this;
		}

		public Criteria andTypenameNotEqualTo(String value) {
			addCriterion("TYPENAME <>", value, "typename");
			return this;
		}

		public Criteria andTypenameGreaterThan(String value) {
			addCriterion("TYPENAME >", value, "typename");
			return this;
		}

		public Criteria andTypenameGreaterThanOrEqualTo(String value) {
			addCriterion("TYPENAME >=", value, "typename");
			return this;
		}

		public Criteria andTypenameLessThan(String value) {
			addCriterion("TYPENAME <", value, "typename");
			return this;
		}

		public Criteria andTypenameLessThanOrEqualTo(String value) {
			addCriterion("TYPENAME <=", value, "typename");
			return this;
		}

		public Criteria andTypenameLike(String value) {
			addCriterion("TYPENAME like", value, "typename");
			return this;
		}

		public Criteria andTypenameNotLike(String value) {
			addCriterion("TYPENAME not like", value, "typename");
			return this;
		}

		public Criteria andTypenameIn(List<String> values) {
			addCriterion("TYPENAME in", values, "typename");
			return this;
		}

		public Criteria andTypenameNotIn(List<String> values) {
			addCriterion("TYPENAME not in", values, "typename");
			return this;
		}

		public Criteria andTypenameBetween(String value1, String value2) {
			addCriterion("TYPENAME between", value1, value2, "typename");
			return this;
		}

		public Criteria andTypenameNotBetween(String value1, String value2) {
			addCriterion("TYPENAME not between", value1, value2, "typename");
			return this;
		}

		public Criteria andTypedescIsNull() {
			addCriterion("TYPEDESC is null");
			return this;
		}

		public Criteria andTypedescIsNotNull() {
			addCriterion("TYPEDESC is not null");
			return this;
		}

		public Criteria andTypedescEqualTo(String value) {
			addCriterion("TYPEDESC =", value, "typedesc");
			return this;
		}

		public Criteria andTypedescNotEqualTo(String value) {
			addCriterion("TYPEDESC <>", value, "typedesc");
			return this;
		}

		public Criteria andTypedescGreaterThan(String value) {
			addCriterion("TYPEDESC >", value, "typedesc");
			return this;
		}

		public Criteria andTypedescGreaterThanOrEqualTo(String value) {
			addCriterion("TYPEDESC >=", value, "typedesc");
			return this;
		}

		public Criteria andTypedescLessThan(String value) {
			addCriterion("TYPEDESC <", value, "typedesc");
			return this;
		}

		public Criteria andTypedescLessThanOrEqualTo(String value) {
			addCriterion("TYPEDESC <=", value, "typedesc");
			return this;
		}

		public Criteria andTypedescLike(String value) {
			addCriterion("TYPEDESC like", value, "typedesc");
			return this;
		}

		public Criteria andTypedescNotLike(String value) {
			addCriterion("TYPEDESC not like", value, "typedesc");
			return this;
		}

		public Criteria andTypedescIn(List<String> values) {
			addCriterion("TYPEDESC in", values, "typedesc");
			return this;
		}

		public Criteria andTypedescNotIn(List<String> values) {
			addCriterion("TYPEDESC not in", values, "typedesc");
			return this;
		}

		public Criteria andTypedescBetween(String value1, String value2) {
			addCriterion("TYPEDESC between", value1, value2, "typedesc");
			return this;
		}

		public Criteria andTypedescNotBetween(String value1, String value2) {
			addCriterion("TYPEDESC not between", value1, value2, "typedesc");
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
			addCriterionForJDBCDate("CRTIME not between", value1, value2,
					"crtime");
			return this;
		}
	}
}