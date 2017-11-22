package com.tfs.irp.right.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpRightExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public IrpRightExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	protected IrpRightExample(IrpRightExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_RIGHT
	 * @ibatorgenerated  Thu Mar 28 15:33:26 CST 2013
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

		public Criteria andRightidIsNull() {
			addCriterion("RIGHTID is null");
			return this;
		}

		public Criteria andRightidIsNotNull() {
			addCriterion("RIGHTID is not null");
			return this;
		}

		public Criteria andRightidEqualTo(Long value) {
			addCriterion("RIGHTID =", value, "rightid");
			return this;
		}

		public Criteria andRightidNotEqualTo(Long value) {
			addCriterion("RIGHTID <>", value, "rightid");
			return this;
		}

		public Criteria andRightidGreaterThan(Long value) {
			addCriterion("RIGHTID >", value, "rightid");
			return this;
		}

		public Criteria andRightidGreaterThanOrEqualTo(Long value) {
			addCriterion("RIGHTID >=", value, "rightid");
			return this;
		}

		public Criteria andRightidLessThan(Long value) {
			addCriterion("RIGHTID <", value, "rightid");
			return this;
		}

		public Criteria andRightidLessThanOrEqualTo(Long value) {
			addCriterion("RIGHTID <=", value, "rightid");
			return this;
		}

		public Criteria andRightidIn(List<Long> values) {
			addCriterion("RIGHTID in", values, "rightid");
			return this;
		}

		public Criteria andRightidNotIn(List<Long> values) {
			addCriterion("RIGHTID not in", values, "rightid");
			return this;
		}

		public Criteria andRightidBetween(Long value1, Long value2) {
			addCriterion("RIGHTID between", value1, value2, "rightid");
			return this;
		}

		public Criteria andRightidNotBetween(Long value1, Long value2) {
			addCriterion("RIGHTID not between", value1, value2, "rightid");
			return this;
		}

		public Criteria andObjidIsNull() {
			addCriterion("OBJID is null");
			return this;
		}

		public Criteria andObjidIsNotNull() {
			addCriterion("OBJID is not null");
			return this;
		}

		public Criteria andObjidEqualTo(Long value) {
			addCriterion("OBJID =", value, "objid");
			return this;
		}

		public Criteria andObjidNotEqualTo(Long value) {
			addCriterion("OBJID <>", value, "objid");
			return this;
		}

		public Criteria andObjidGreaterThan(Long value) {
			addCriterion("OBJID >", value, "objid");
			return this;
		}

		public Criteria andObjidGreaterThanOrEqualTo(Long value) {
			addCriterion("OBJID >=", value, "objid");
			return this;
		}

		public Criteria andObjidLessThan(Long value) {
			addCriterion("OBJID <", value, "objid");
			return this;
		}

		public Criteria andObjidLessThanOrEqualTo(Long value) {
			addCriterion("OBJID <=", value, "objid");
			return this;
		}

		public Criteria andObjidIn(List<Long> values) {
			addCriterion("OBJID in", values, "objid");
			return this;
		}

		public Criteria andObjidNotIn(List<Long> values) {
			addCriterion("OBJID not in", values, "objid");
			return this;
		}

		public Criteria andObjidBetween(Long value1, Long value2) {
			addCriterion("OBJID between", value1, value2, "objid");
			return this;
		}

		public Criteria andObjidNotBetween(Long value1, Long value2) {
			addCriterion("OBJID not between", value1, value2, "objid");
			return this;
		}

		public Criteria andObjtypeIsNull() {
			addCriterion("OBJTYPE is null");
			return this;
		}

		public Criteria andObjtypeIsNotNull() {
			addCriterion("OBJTYPE is not null");
			return this;
		}

		public Criteria andObjtypeEqualTo(String value) {
			addCriterion("OBJTYPE =", value, "objtype");
			return this;
		}

		public Criteria andObjtypeNotEqualTo(String value) {
			addCriterion("OBJTYPE <>", value, "objtype");
			return this;
		}

		public Criteria andObjtypeGreaterThan(String value) {
			addCriterion("OBJTYPE >", value, "objtype");
			return this;
		}

		public Criteria andObjtypeGreaterThanOrEqualTo(String value) {
			addCriterion("OBJTYPE >=", value, "objtype");
			return this;
		}

		public Criteria andObjtypeLessThan(String value) {
			addCriterion("OBJTYPE <", value, "objtype");
			return this;
		}

		public Criteria andObjtypeLessThanOrEqualTo(String value) {
			addCriterion("OBJTYPE <=", value, "objtype");
			return this;
		}

		public Criteria andObjtypeLike(String value) {
			addCriterion("OBJTYPE like", value, "objtype");
			return this;
		}

		public Criteria andObjtypeNotLike(String value) {
			addCriterion("OBJTYPE not like", value, "objtype");
			return this;
		}

		public Criteria andObjtypeIn(List<String> values) {
			addCriterion("OBJTYPE in", values, "objtype");
			return this;
		}

		public Criteria andObjtypeNotIn(List<String> values) {
			addCriterion("OBJTYPE not in", values, "objtype");
			return this;
		}

		public Criteria andObjtypeBetween(String value1, String value2) {
			addCriterion("OBJTYPE between", value1, value2, "objtype");
			return this;
		}

		public Criteria andObjtypeNotBetween(String value1, String value2) {
			addCriterion("OBJTYPE not between", value1, value2, "objtype");
			return this;
		}

		public Criteria andOperidIsNull() {
			addCriterion("OPERID is null");
			return this;
		}

		public Criteria andOperidIsNotNull() {
			addCriterion("OPERID is not null");
			return this;
		}

		public Criteria andOperidEqualTo(Long value) {
			addCriterion("OPERID =", value, "operid");
			return this;
		}

		public Criteria andOperidNotEqualTo(Long value) {
			addCriterion("OPERID <>", value, "operid");
			return this;
		}

		public Criteria andOperidGreaterThan(Long value) {
			addCriterion("OPERID >", value, "operid");
			return this;
		}

		public Criteria andOperidGreaterThanOrEqualTo(Long value) {
			addCriterion("OPERID >=", value, "operid");
			return this;
		}

		public Criteria andOperidLessThan(Long value) {
			addCriterion("OPERID <", value, "operid");
			return this;
		}

		public Criteria andOperidLessThanOrEqualTo(Long value) {
			addCriterion("OPERID <=", value, "operid");
			return this;
		}

		public Criteria andOperidIn(List<Long> values) {
			addCriterion("OPERID in", values, "operid");
			return this;
		}

		public Criteria andOperidNotIn(List<Long> values) {
			addCriterion("OPERID not in", values, "operid");
			return this;
		}

		public Criteria andOperidBetween(Long value1, Long value2) {
			addCriterion("OPERID between", value1, value2, "operid");
			return this;
		}

		public Criteria andOperidNotBetween(Long value1, Long value2) {
			addCriterion("OPERID not between", value1, value2, "operid");
			return this;
		}

		public Criteria andOpertypeIsNull() {
			addCriterion("OPERTYPE is null");
			return this;
		}

		public Criteria andOpertypeIsNotNull() {
			addCriterion("OPERTYPE is not null");
			return this;
		}

		public Criteria andOpertypeEqualTo(String value) {
			addCriterion("OPERTYPE =", value, "opertype");
			return this;
		}

		public Criteria andOpertypeNotEqualTo(String value) {
			addCriterion("OPERTYPE <>", value, "opertype");
			return this;
		}

		public Criteria andOpertypeGreaterThan(String value) {
			addCriterion("OPERTYPE >", value, "opertype");
			return this;
		}

		public Criteria andOpertypeGreaterThanOrEqualTo(String value) {
			addCriterion("OPERTYPE >=", value, "opertype");
			return this;
		}

		public Criteria andOpertypeLessThan(String value) {
			addCriterion("OPERTYPE <", value, "opertype");
			return this;
		}

		public Criteria andOpertypeLessThanOrEqualTo(String value) {
			addCriterion("OPERTYPE <=", value, "opertype");
			return this;
		}

		public Criteria andOpertypeLike(String value) {
			addCriterion("OPERTYPE like", value, "opertype");
			return this;
		}

		public Criteria andOpertypeNotLike(String value) {
			addCriterion("OPERTYPE not like", value, "opertype");
			return this;
		}

		public Criteria andOpertypeIn(List<String> values) {
			addCriterion("OPERTYPE in", values, "opertype");
			return this;
		}

		public Criteria andOpertypeNotIn(List<String> values) {
			addCriterion("OPERTYPE not in", values, "opertype");
			return this;
		}

		public Criteria andOpertypeBetween(String value1, String value2) {
			addCriterion("OPERTYPE between", value1, value2, "opertype");
			return this;
		}

		public Criteria andOpertypeNotBetween(String value1, String value2) {
			addCriterion("OPERTYPE not between", value1, value2, "opertype");
			return this;
		}

		public Criteria andOpertypeidIsNull() {
			addCriterion("OPERTYPEID is null");
			return this;
		}

		public Criteria andOpertypeidIsNotNull() {
			addCriterion("OPERTYPEID is not null");
			return this;
		}

		public Criteria andOpertypeidEqualTo(Long value) {
			addCriterion("OPERTYPEID =", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidNotEqualTo(Long value) {
			addCriterion("OPERTYPEID <>", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidGreaterThan(Long value) {
			addCriterion("OPERTYPEID >", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidGreaterThanOrEqualTo(Long value) {
			addCriterion("OPERTYPEID >=", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidLessThan(Long value) {
			addCriterion("OPERTYPEID <", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidLessThanOrEqualTo(Long value) {
			addCriterion("OPERTYPEID <=", value, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidIn(List<Long> values) {
			addCriterion("OPERTYPEID in", values, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidNotIn(List<Long> values) {
			addCriterion("OPERTYPEID not in", values, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidBetween(Long value1, Long value2) {
			addCriterion("OPERTYPEID between", value1, value2, "opertypeid");
			return this;
		}

		public Criteria andOpertypeidNotBetween(Long value1, Long value2) {
			addCriterion("OPERTYPEID not between", value1, value2, "opertypeid");
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
	}
}