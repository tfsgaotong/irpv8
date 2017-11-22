package com.tfs.irp.assetype.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpAssetypeExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public IrpAssetypeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	protected IrpAssetypeExample(IrpAssetypeExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSETYPE
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
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

		public Criteria andAsseroomapplytypeidIsNull() {
			addCriterion("ASSEROOMAPPLYTYPEID is null");
			return this;
		}

		public Criteria andAsseroomapplytypeidIsNotNull() {
			addCriterion("ASSEROOMAPPLYTYPEID is not null");
			return this;
		}

		public Criteria andAsseroomapplytypeidEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID =", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidNotEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID <>", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidGreaterThan(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID >", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID >=", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidLessThan(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID <", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidLessThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYTYPEID <=", value, "asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidIn(List<Long> values) {
			addCriterion("ASSEROOMAPPLYTYPEID in", values,
					"asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidNotIn(List<Long> values) {
			addCriterion("ASSEROOMAPPLYTYPEID not in", values,
					"asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMAPPLYTYPEID between", value1, value2,
					"asseroomapplytypeid");
			return this;
		}

		public Criteria andAsseroomapplytypeidNotBetween(Long value1,
				Long value2) {
			addCriterion("ASSEROOMAPPLYTYPEID not between", value1, value2,
					"asseroomapplytypeid");
			return this;
		}

		public Criteria andAssetypenameIsNull() {
			addCriterion("ASSETYPENAME is null");
			return this;
		}

		public Criteria andAssetypenameIsNotNull() {
			addCriterion("ASSETYPENAME is not null");
			return this;
		}

		public Criteria andAssetypenameEqualTo(String value) {
			addCriterion("ASSETYPENAME =", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameNotEqualTo(String value) {
			addCriterion("ASSETYPENAME <>", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameGreaterThan(String value) {
			addCriterion("ASSETYPENAME >", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameGreaterThanOrEqualTo(String value) {
			addCriterion("ASSETYPENAME >=", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameLessThan(String value) {
			addCriterion("ASSETYPENAME <", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameLessThanOrEqualTo(String value) {
			addCriterion("ASSETYPENAME <=", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameLike(String value) {
			addCriterion("ASSETYPENAME like", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameNotLike(String value) {
			addCriterion("ASSETYPENAME not like", value, "assetypename");
			return this;
		}

		public Criteria andAssetypenameIn(List<String> values) {
			addCriterion("ASSETYPENAME in", values, "assetypename");
			return this;
		}

		public Criteria andAssetypenameNotIn(List<String> values) {
			addCriterion("ASSETYPENAME not in", values, "assetypename");
			return this;
		}

		public Criteria andAssetypenameBetween(String value1, String value2) {
			addCriterion("ASSETYPENAME between", value1, value2, "assetypename");
			return this;
		}

		public Criteria andAssetypenameNotBetween(String value1, String value2) {
			addCriterion("ASSETYPENAME not between", value1, value2,
					"assetypename");
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
}