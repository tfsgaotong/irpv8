package com.tfs.irp.asseroomsb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpAsseroomsbExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public IrpAsseroomsbExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	protected IrpAsseroomsbExample(IrpAsseroomsbExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
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

		public Criteria andAsseroomsbidIsNull() {
			addCriterion("ASSEROOMSBID is null");
			return this;
		}

		public Criteria andAsseroomsbidIsNotNull() {
			addCriterion("ASSEROOMSBID is not null");
			return this;
		}

		public Criteria andAsseroomsbidEqualTo(Long value) {
			addCriterion("ASSEROOMSBID =", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidNotEqualTo(Long value) {
			addCriterion("ASSEROOMSBID <>", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidGreaterThan(Long value) {
			addCriterion("ASSEROOMSBID >", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMSBID >=", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidLessThan(Long value) {
			addCriterion("ASSEROOMSBID <", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidLessThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMSBID <=", value, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidIn(List<Long> values) {
			addCriterion("ASSEROOMSBID in", values, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidNotIn(List<Long> values) {
			addCriterion("ASSEROOMSBID not in", values, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMSBID between", value1, value2, "asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbidNotBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMSBID not between", value1, value2,
					"asseroomsbid");
			return this;
		}

		public Criteria andAsseroomsbnameIsNull() {
			addCriterion("ASSEROOMSBNAME is null");
			return this;
		}

		public Criteria andAsseroomsbnameIsNotNull() {
			addCriterion("ASSEROOMSBNAME is not null");
			return this;
		}

		public Criteria andAsseroomsbnameEqualTo(String value) {
			addCriterion("ASSEROOMSBNAME =", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameNotEqualTo(String value) {
			addCriterion("ASSEROOMSBNAME <>", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameGreaterThan(String value) {
			addCriterion("ASSEROOMSBNAME >", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBNAME >=", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameLessThan(String value) {
			addCriterion("ASSEROOMSBNAME <", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBNAME <=", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameLike(String value) {
			addCriterion("ASSEROOMSBNAME like", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameNotLike(String value) {
			addCriterion("ASSEROOMSBNAME not like", value, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameIn(List<String> values) {
			addCriterion("ASSEROOMSBNAME in", values, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameNotIn(List<String> values) {
			addCriterion("ASSEROOMSBNAME not in", values, "asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBNAME between", value1, value2,
					"asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbnameNotBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBNAME not between", value1, value2,
					"asseroomsbname");
			return this;
		}

		public Criteria andAsseroomsbdescIsNull() {
			addCriterion("ASSEROOMSBDESC is null");
			return this;
		}

		public Criteria andAsseroomsbdescIsNotNull() {
			addCriterion("ASSEROOMSBDESC is not null");
			return this;
		}

		public Criteria andAsseroomsbdescEqualTo(String value) {
			addCriterion("ASSEROOMSBDESC =", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescNotEqualTo(String value) {
			addCriterion("ASSEROOMSBDESC <>", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescGreaterThan(String value) {
			addCriterion("ASSEROOMSBDESC >", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBDESC >=", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescLessThan(String value) {
			addCriterion("ASSEROOMSBDESC <", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBDESC <=", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescLike(String value) {
			addCriterion("ASSEROOMSBDESC like", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescNotLike(String value) {
			addCriterion("ASSEROOMSBDESC not like", value, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescIn(List<String> values) {
			addCriterion("ASSEROOMSBDESC in", values, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescNotIn(List<String> values) {
			addCriterion("ASSEROOMSBDESC not in", values, "asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBDESC between", value1, value2,
					"asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbdescNotBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBDESC not between", value1, value2,
					"asseroomsbdesc");
			return this;
		}

		public Criteria andAsseroomsbuseIsNull() {
			addCriterion("ASSEROOMSBUSE is null");
			return this;
		}

		public Criteria andAsseroomsbuseIsNotNull() {
			addCriterion("ASSEROOMSBUSE is not null");
			return this;
		}

		public Criteria andAsseroomsbuseEqualTo(String value) {
			addCriterion("ASSEROOMSBUSE =", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseNotEqualTo(String value) {
			addCriterion("ASSEROOMSBUSE <>", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseGreaterThan(String value) {
			addCriterion("ASSEROOMSBUSE >", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBUSE >=", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseLessThan(String value) {
			addCriterion("ASSEROOMSBUSE <", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBUSE <=", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseLike(String value) {
			addCriterion("ASSEROOMSBUSE like", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseNotLike(String value) {
			addCriterion("ASSEROOMSBUSE not like", value, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseIn(List<String> values) {
			addCriterion("ASSEROOMSBUSE in", values, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseNotIn(List<String> values) {
			addCriterion("ASSEROOMSBUSE not in", values, "asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBUSE between", value1, value2,
					"asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbuseNotBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBUSE not between", value1, value2,
					"asseroomsbuse");
			return this;
		}

		public Criteria andAsseroomsbtypeIsNull() {
			addCriterion("ASSEROOMSBTYPE is null");
			return this;
		}

		public Criteria andAsseroomsbtypeIsNotNull() {
			addCriterion("ASSEROOMSBTYPE is not null");
			return this;
		}

		public Criteria andAsseroomsbtypeEqualTo(String value) {
			addCriterion("ASSEROOMSBTYPE =", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeNotEqualTo(String value) {
			addCriterion("ASSEROOMSBTYPE <>", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeGreaterThan(String value) {
			addCriterion("ASSEROOMSBTYPE >", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBTYPE >=", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeLessThan(String value) {
			addCriterion("ASSEROOMSBTYPE <", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBTYPE <=", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeLike(String value) {
			addCriterion("ASSEROOMSBTYPE like", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeNotLike(String value) {
			addCriterion("ASSEROOMSBTYPE not like", value, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeIn(List<String> values) {
			addCriterion("ASSEROOMSBTYPE in", values, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeNotIn(List<String> values) {
			addCriterion("ASSEROOMSBTYPE not in", values, "asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBTYPE between", value1, value2,
					"asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbtypeNotBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBTYPE not between", value1, value2,
					"asseroomsbtype");
			return this;
		}

		public Criteria andAsseroomsbstatusIsNull() {
			addCriterion("ASSEROOMSBSTATUS is null");
			return this;
		}

		public Criteria andAsseroomsbstatusIsNotNull() {
			addCriterion("ASSEROOMSBSTATUS is not null");
			return this;
		}

		public Criteria andAsseroomsbstatusEqualTo(Integer value) {
			addCriterion("ASSEROOMSBSTATUS =", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusNotEqualTo(Integer value) {
			addCriterion("ASSEROOMSBSTATUS <>", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusGreaterThan(Integer value) {
			addCriterion("ASSEROOMSBSTATUS >", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("ASSEROOMSBSTATUS >=", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusLessThan(Integer value) {
			addCriterion("ASSEROOMSBSTATUS <", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusLessThanOrEqualTo(Integer value) {
			addCriterion("ASSEROOMSBSTATUS <=", value, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusIn(List<Integer> values) {
			addCriterion("ASSEROOMSBSTATUS in", values, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusNotIn(List<Integer> values) {
			addCriterion("ASSEROOMSBSTATUS not in", values, "asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusBetween(Integer value1,
				Integer value2) {
			addCriterion("ASSEROOMSBSTATUS between", value1, value2,
					"asseroomsbstatus");
			return this;
		}

		public Criteria andAsseroomsbstatusNotBetween(Integer value1,
				Integer value2) {
			addCriterion("ASSEROOMSBSTATUS not between", value1, value2,
					"asseroomsbstatus");
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
	}

	@Override
	public String toString() {
		return "IrpAsseroomsbExample [orderByClause=" + orderByClause
				+ ", oredCriteria=" + oredCriteria + "]";
	}
	
}