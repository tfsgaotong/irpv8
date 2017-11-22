package com.tfs.irp.asseroomapply.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpAsseroomapplyExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public IrpAsseroomapplyExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	protected IrpAsseroomapplyExample(IrpAsseroomapplyExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
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

		public Criteria andAsseroomapplyidIsNull() {
			addCriterion("ASSEROOMAPPLYID is null");
			return this;
		}

		public Criteria andAsseroomapplyidIsNotNull() {
			addCriterion("ASSEROOMAPPLYID is not null");
			return this;
		}

		public Criteria andAsseroomapplyidEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYID =", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidNotEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYID <>", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidGreaterThan(Long value) {
			addCriterion("ASSEROOMAPPLYID >", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYID >=", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidLessThan(Long value) {
			addCriterion("ASSEROOMAPPLYID <", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidLessThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMAPPLYID <=", value, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidIn(List<Long> values) {
			addCriterion("ASSEROOMAPPLYID in", values, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidNotIn(List<Long> values) {
			addCriterion("ASSEROOMAPPLYID not in", values, "asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMAPPLYID between", value1, value2,
					"asseroomapplyid");
			return this;
		}

		public Criteria andAsseroomapplyidNotBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMAPPLYID not between", value1, value2,
					"asseroomapplyid");
			return this;
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

		public Criteria andAssenameIsNull() {
			addCriterion("ASSENAME is null");
			return this;
		}

		public Criteria andAssenameIsNotNull() {
			addCriterion("ASSENAME is not null");
			return this;
		}

		public Criteria andAssenameEqualTo(String value) {
			addCriterion("ASSENAME =", value, "assename");
			return this;
		}

		public Criteria andAssenameNotEqualTo(String value) {
			addCriterion("ASSENAME <>", value, "assename");
			return this;
		}

		public Criteria andAssenameGreaterThan(String value) {
			addCriterion("ASSENAME >", value, "assename");
			return this;
		}

		public Criteria andAssenameGreaterThanOrEqualTo(String value) {
			addCriterion("ASSENAME >=", value, "assename");
			return this;
		}

		public Criteria andAssenameLessThan(String value) {
			addCriterion("ASSENAME <", value, "assename");
			return this;
		}

		public Criteria andAssenameLessThanOrEqualTo(String value) {
			addCriterion("ASSENAME <=", value, "assename");
			return this;
		}

		public Criteria andAssenameLike(String value) {
			addCriterion("ASSENAME like", value, "assename");
			return this;
		}

		public Criteria andAssenameNotLike(String value) {
			addCriterion("ASSENAME not like", value, "assename");
			return this;
		}

		public Criteria andAssenameIn(List<String> values) {
			addCriterion("ASSENAME in", values, "assename");
			return this;
		}

		public Criteria andAssenameNotIn(List<String> values) {
			addCriterion("ASSENAME not in", values, "assename");
			return this;
		}

		public Criteria andAssenameBetween(String value1, String value2) {
			addCriterion("ASSENAME between", value1, value2, "assename");
			return this;
		}

		public Criteria andAssenameNotBetween(String value1, String value2) {
			addCriterion("ASSENAME not between", value1, value2, "assename");
			return this;
		}

		public Criteria andAsseconveneruseridIsNull() {
			addCriterion("ASSECONVENERUSERID is null");
			return this;
		}

		public Criteria andAsseconveneruseridIsNotNull() {
			addCriterion("ASSECONVENERUSERID is not null");
			return this;
		}

		public Criteria andAsseconveneruseridEqualTo(Long value) {
			addCriterion("ASSECONVENERUSERID =", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridNotEqualTo(Long value) {
			addCriterion("ASSECONVENERUSERID <>", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridGreaterThan(Long value) {
			addCriterion("ASSECONVENERUSERID >", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSECONVENERUSERID >=", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridLessThan(Long value) {
			addCriterion("ASSECONVENERUSERID <", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridLessThanOrEqualTo(Long value) {
			addCriterion("ASSECONVENERUSERID <=", value, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridIn(List<Long> values) {
			addCriterion("ASSECONVENERUSERID in", values, "asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridNotIn(List<Long> values) {
			addCriterion("ASSECONVENERUSERID not in", values,
					"asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridBetween(Long value1, Long value2) {
			addCriterion("ASSECONVENERUSERID between", value1, value2,
					"asseconveneruserid");
			return this;
		}

		public Criteria andAsseconveneruseridNotBetween(Long value1, Long value2) {
			addCriterion("ASSECONVENERUSERID not between", value1, value2,
					"asseconveneruserid");
			return this;
		}

		public Criteria andAsselinkmanuseridIsNull() {
			addCriterion("ASSELINKMANUSERID is null");
			return this;
		}

		public Criteria andAsselinkmanuseridIsNotNull() {
			addCriterion("ASSELINKMANUSERID is not null");
			return this;
		}

		public Criteria andAsselinkmanuseridEqualTo(Long value) {
			addCriterion("ASSELINKMANUSERID =", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridNotEqualTo(Long value) {
			addCriterion("ASSELINKMANUSERID <>", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridGreaterThan(Long value) {
			addCriterion("ASSELINKMANUSERID >", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSELINKMANUSERID >=", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridLessThan(Long value) {
			addCriterion("ASSELINKMANUSERID <", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridLessThanOrEqualTo(Long value) {
			addCriterion("ASSELINKMANUSERID <=", value, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridIn(List<Long> values) {
			addCriterion("ASSELINKMANUSERID in", values, "asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridNotIn(List<Long> values) {
			addCriterion("ASSELINKMANUSERID not in", values,
					"asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridBetween(Long value1, Long value2) {
			addCriterion("ASSELINKMANUSERID between", value1, value2,
					"asselinkmanuserid");
			return this;
		}

		public Criteria andAsselinkmanuseridNotBetween(Long value1, Long value2) {
			addCriterion("ASSELINKMANUSERID not between", value1, value2,
					"asselinkmanuserid");
			return this;
		}

		public Criteria andAsseroomidIsNull() {
			addCriterion("ASSEROOMID is null");
			return this;
		}

		public Criteria andAsseroomidIsNotNull() {
			addCriterion("ASSEROOMID is not null");
			return this;
		}

		public Criteria andAsseroomidEqualTo(Long value) {
			addCriterion("ASSEROOMID =", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidNotEqualTo(Long value) {
			addCriterion("ASSEROOMID <>", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidGreaterThan(Long value) {
			addCriterion("ASSEROOMID >", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidGreaterThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMID >=", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidLessThan(Long value) {
			addCriterion("ASSEROOMID <", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidLessThanOrEqualTo(Long value) {
			addCriterion("ASSEROOMID <=", value, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidIn(List<Long> values) {
			addCriterion("ASSEROOMID in", values, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidNotIn(List<Long> values) {
			addCriterion("ASSEROOMID not in", values, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMID between", value1, value2, "asseroomid");
			return this;
		}

		public Criteria andAsseroomidNotBetween(Long value1, Long value2) {
			addCriterion("ASSEROOMID not between", value1, value2, "asseroomid");
			return this;
		}

		public Criteria andAsseroomcontentIsNull() {
			addCriterion("ASSEROOMCONTENT is null");
			return this;
		}

		public Criteria andAsseroomcontentIsNotNull() {
			addCriterion("ASSEROOMCONTENT is not null");
			return this;
		}

		public Criteria andAsseroomcontentEqualTo(String value) {
			addCriterion("ASSEROOMCONTENT =", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentNotEqualTo(String value) {
			addCriterion("ASSEROOMCONTENT <>", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentGreaterThan(String value) {
			addCriterion("ASSEROOMCONTENT >", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMCONTENT >=", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentLessThan(String value) {
			addCriterion("ASSEROOMCONTENT <", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMCONTENT <=", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentLike(String value) {
			addCriterion("ASSEROOMCONTENT like", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentNotLike(String value) {
			addCriterion("ASSEROOMCONTENT not like", value, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentIn(List<String> values) {
			addCriterion("ASSEROOMCONTENT in", values, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentNotIn(List<String> values) {
			addCriterion("ASSEROOMCONTENT not in", values, "asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentBetween(String value1, String value2) {
			addCriterion("ASSEROOMCONTENT between", value1, value2,
					"asseroomcontent");
			return this;
		}

		public Criteria andAsseroomcontentNotBetween(String value1,
				String value2) {
			addCriterion("ASSEROOMCONTENT not between", value1, value2,
					"asseroomcontent");
			return this;
		}

		public Criteria andStarttimeIsNull() {
			addCriterion("STARTTIME is null");
			return this;
		}

		public Criteria andStarttimeIsNotNull() {
			addCriterion("STARTTIME is not null");
			return this;
		}

		public Criteria andStarttimeEqualTo(Date value) {
			addCriterion("STARTTIME =", value, "starttime");
			return this;
		}

		public Criteria andStarttimeNotEqualTo(Date value) {
			addCriterion("STARTTIME <>", value, "starttime");
			return this;
		}

		public Criteria andStarttimeGreaterThan(Date value) {
			addCriterion("STARTTIME >", value, "starttime");
			return this;
		}

		public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
			addCriterion("STARTTIME >=", value, "starttime");
			return this;
		}

		public Criteria andStarttimeLessThan(Date value) {
			addCriterion("STARTTIME <", value, "starttime");
			return this;
		}

		public Criteria andStarttimeLessThanOrEqualTo(Date value) {
			addCriterion("STARTTIME <=", value, "starttime");
			return this;
		}

		public Criteria andStarttimeIn(List<Date> values) {
			addCriterion("STARTTIME in", values, "starttime");
			return this;
		}

		public Criteria andStarttimeNotIn(List<Date> values) {
			addCriterion("STARTTIME not in", values, "starttime");
			return this;
		}

		public Criteria andStarttimeBetween(Date value1, Date value2) {
			addCriterion("STARTTIME between", value1, value2, "starttime");
			return this;
		}

		public Criteria andStarttimeNotBetween(Date value1, Date value2) {
			addCriterion("STARTTIME not between", value1, value2, "starttime");
			return this;
		}

		public Criteria andEndtimeIsNull() {
			addCriterion("ENDTIME is null");
			return this;
		}

		public Criteria andEndtimeIsNotNull() {
			addCriterion("ENDTIME is not null");
			return this;
		}

		public Criteria andEndtimeEqualTo(Date value) {
			addCriterion("ENDTIME =", value, "endtime");
			return this;
		}

		public Criteria andEndtimeNotEqualTo(Date value) {
			addCriterion("ENDTIME <>", value, "endtime");
			return this;
		}

		public Criteria andEndtimeGreaterThan(Date value) {
			addCriterion("ENDTIME >", value, "endtime");
			return this;
		}

		public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("ENDTIME >=", value, "endtime");
			return this;
		}

		public Criteria andEndtimeLessThan(Date value) {
			addCriterion("ENDTIME <", value, "endtime");
			return this;
		}

		public Criteria andEndtimeLessThanOrEqualTo(Date value) {
			addCriterion("ENDTIME <=", value, "endtime");
			return this;
		}

		public Criteria andEndtimeIn(List<Date> values) {
			addCriterion("ENDTIME in", values, "endtime");
			return this;
		}

		public Criteria andEndtimeNotIn(List<Date> values) {
			addCriterion("ENDTIME not in", values, "endtime");
			return this;
		}

		public Criteria andEndtimeBetween(Date value1, Date value2) {
			addCriterion("ENDTIME between", value1, value2, "endtime");
			return this;
		}

		public Criteria andEndtimeNotBetween(Date value1, Date value2) {
			addCriterion("ENDTIME not between", value1, value2, "endtime");
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

		public Criteria andWarnidIsNull() {
			addCriterion("WARNID is null");
			return this;
		}

		public Criteria andWarnidIsNotNull() {
			addCriterion("WARNID is not null");
			return this;
		}

		public Criteria andWarnidEqualTo(Long value) {
			addCriterion("WARNID =", value, "warnid");
			return this;
		}

		public Criteria andWarnidNotEqualTo(Long value) {
			addCriterion("WARNID <>", value, "warnid");
			return this;
		}

		public Criteria andWarnidGreaterThan(Long value) {
			addCriterion("WARNID >", value, "warnid");
			return this;
		}

		public Criteria andWarnidGreaterThanOrEqualTo(Long value) {
			addCriterion("WARNID >=", value, "warnid");
			return this;
		}

		public Criteria andWarnidLessThan(Long value) {
			addCriterion("WARNID <", value, "warnid");
			return this;
		}

		public Criteria andWarnidLessThanOrEqualTo(Long value) {
			addCriterion("WARNID <=", value, "warnid");
			return this;
		}

		public Criteria andWarnidIn(List<Long> values) {
			addCriterion("WARNID in", values, "warnid");
			return this;
		}

		public Criteria andWarnidNotIn(List<Long> values) {
			addCriterion("WARNID not in", values, "warnid");
			return this;
		}

		public Criteria andWarnidBetween(Long value1, Long value2) {
			addCriterion("WARNID between", value1, value2, "warnid");
			return this;
		}

		public Criteria andWarnidNotBetween(Long value1, Long value2) {
			addCriterion("WARNID not between", value1, value2, "warnid");
			return this;
		}

		public Criteria andWarntimeIsNull() {
			addCriterion("WARNTIME is null");
			return this;
		}

		public Criteria andWarntimeIsNotNull() {
			addCriterion("WARNTIME is not null");
			return this;
		}

		public Criteria andWarntimeEqualTo(Date value) {
			addCriterion("WARNTIME =", value, "warntime");
			return this;
		}

		public Criteria andWarntimeNotEqualTo(Date value) {
			addCriterion("WARNTIME <>", value, "warntime");
			return this;
		}

		public Criteria andWarntimeGreaterThan(Date value) {
			addCriterion("WARNTIME >", value, "warntime");
			return this;
		}

		public Criteria andWarntimeGreaterThanOrEqualTo(Date value) {
			addCriterion("WARNTIME >=", value, "warntime");
			return this;
		}
		 
		public Criteria andWarntimeLessThan(Date value) {
			addCriterion("WARNTIME <", value, "warntime");
			return this;
		}

		public Criteria andWarntimeLessThanOrEqualTo(Date value) {
			addCriterion("WARNTIME <=", value, "warntime");
			return this;
		}

		public Criteria andWarntimeIn(List<Date> values) {
			addCriterion("WARNTIME in", values, "warntime");
			return this;
		}

		public Criteria andWarntimeNotIn(List<Date> values) {
			addCriterion("WARNTIME not in", values, "warntime");
			return this;
		}

		public Criteria andWarntimeBetween(Date value1, Date value2) {
			addCriterion("WARNTIME between", value1, value2, "warntime");
			return this;
		}

		public Criteria andWarntimeNotBetween(Date value1, Date value2) {
			addCriterion("WARNTIME not between", value1, value2, "warntime");
			return this;
		}

		public Criteria andShouldmanIsNull() {
			addCriterion("SHOULDMAN is null");
			return this;
		}

		public Criteria andShouldmanIsNotNull() {
			addCriterion("SHOULDMAN is not null");
			return this;
		}

		public Criteria andShouldmanEqualTo(Long value) {
			addCriterion("SHOULDMAN =", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanNotEqualTo(Long value) {
			addCriterion("SHOULDMAN <>", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanGreaterThan(Long value) {
			addCriterion("SHOULDMAN >", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanGreaterThanOrEqualTo(Long value) {
			addCriterion("SHOULDMAN >=", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanLessThan(Long value) {
			addCriterion("SHOULDMAN <", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanLessThanOrEqualTo(Long value) {
			addCriterion("SHOULDMAN <=", value, "shouldman");
			return this;
		}

		public Criteria andShouldmanIn(List<Long> values) {
			addCriterion("SHOULDMAN in", values, "shouldman");
			return this;
		}

		public Criteria andShouldmanNotIn(List<Long> values) {
			addCriterion("SHOULDMAN not in", values, "shouldman");
			return this;
		}

		public Criteria andShouldmanBetween(Long value1, Long value2) {
			addCriterion("SHOULDMAN between", value1, value2, "shouldman");
			return this;
		}

		public Criteria andShouldmanNotBetween(Long value1, Long value2) {
			addCriterion("SHOULDMAN not between", value1, value2, "shouldman");
			return this;
		}

		public Criteria andAttchedIsNull() {
			addCriterion("ATTCHED is null");
			return this;
		}

		public Criteria andAttchedIsNotNull() {
			addCriterion("ATTCHED is not null");
			return this;
		}

		public Criteria andAttchedEqualTo(String value) {
			addCriterion("ATTCHED =", value, "attched");
			return this;
		}

		public Criteria andAttchedNotEqualTo(String value) {
			addCriterion("ATTCHED <>", value, "attched");
			return this;
		}

		public Criteria andAttchedGreaterThan(String value) {
			addCriterion("ATTCHED >", value, "attched");
			return this;
		}

		public Criteria andAttchedGreaterThanOrEqualTo(String value) {
			addCriterion("ATTCHED >=", value, "attched");
			return this;
		}

		public Criteria andAttchedLessThan(String value) {
			addCriterion("ATTCHED <", value, "attched");
			return this;
		}

		public Criteria andAttchedLessThanOrEqualTo(String value) {
			addCriterion("ATTCHED <=", value, "attched");
			return this;
		}

		public Criteria andAttchedLike(String value) {
			addCriterion("ATTCHED like", value, "attched");
			return this;
		}

		public Criteria andAttchedNotLike(String value) {
			addCriterion("ATTCHED not like", value, "attched");
			return this;
		}

		public Criteria andAttchedIn(List<String> values) {
			addCriterion("ATTCHED in", values, "attched");
			return this;
		}

		public Criteria andAttchedNotIn(List<String> values) {
			addCriterion("ATTCHED not in", values, "attched");
			return this;
		}

		public Criteria andAttchedBetween(String value1, String value2) {
			addCriterion("ATTCHED between", value1, value2, "attched");
			return this;
		}

		public Criteria andAttchedNotBetween(String value1, String value2) {
			addCriterion("ATTCHED not between", value1, value2, "attched");
			return this;
		}

		public Criteria andApplystatusIsNull() {
			addCriterion("APPLYSTATUS is null");
			return this;
		}

		public Criteria andApplystatusIsNotNull() {
			addCriterion("APPLYSTATUS is not null");
			return this;
		}

		public Criteria andApplystatusEqualTo(Integer value) {
			addCriterion("APPLYSTATUS =", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusNotEqualTo(Integer value) {
			addCriterion("APPLYSTATUS <>", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusGreaterThan(Integer value) {
			addCriterion("APPLYSTATUS >", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("APPLYSTATUS >=", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusLessThan(Integer value) {
			addCriterion("APPLYSTATUS <", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusLessThanOrEqualTo(Integer value) {
			addCriterion("APPLYSTATUS <=", value, "applystatus");
			return this;
		}

		public Criteria andApplystatusIn(List<Integer> values) {
			addCriterion("APPLYSTATUS in", values, "applystatus");
			return this;
		}

		public Criteria andApplystatusNotIn(List<Integer> values) {
			addCriterion("APPLYSTATUS not in", values, "applystatus");
			return this;
		}

		public Criteria andApplystatusBetween(Integer value1, Integer value2) {
			addCriterion("APPLYSTATUS between", value1, value2, "applystatus");
			return this;
		}

		public Criteria andApplystatusNotBetween(Integer value1, Integer value2) {
			addCriterion("APPLYSTATUS not between", value1, value2,
					"applystatus");
			return this;
		}

		public Criteria andAsseroomsbidsIsNull() {
			addCriterion("ASSEROOMSBIDS is null");
			return this;
		}

		public Criteria andAsseroomsbidsIsNotNull() {
			addCriterion("ASSEROOMSBIDS is not null");
			return this;
		}

		public Criteria andAsseroomsbidsEqualTo(String value) {
			addCriterion("ASSEROOMSBIDS =", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsNotEqualTo(String value) {
			addCriterion("ASSEROOMSBIDS <>", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsGreaterThan(String value) {
			addCriterion("ASSEROOMSBIDS >", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBIDS >=", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsLessThan(String value) {
			addCriterion("ASSEROOMSBIDS <", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMSBIDS <=", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsLike(String value) {
			addCriterion("ASSEROOMSBIDS like", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsNotLike(String value) {
			addCriterion("ASSEROOMSBIDS not like", value, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsIn(List<String> values) {
			addCriterion("ASSEROOMSBIDS in", values, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsNotIn(List<String> values) {
			addCriterion("ASSEROOMSBIDS not in", values, "asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBIDS between", value1, value2,
					"asseroomsbids");
			return this;
		}

		public Criteria andAsseroomsbidsNotBetween(String value1, String value2) {
			addCriterion("ASSEROOMSBIDS not between", value1, value2,
					"asseroomsbids");
			return this;
		}

		public Criteria andAsseroomapplydemoIsNull() {
			addCriterion("ASSEROOMAPPLYDEMO is null");
			return this;
		}

		public Criteria andAsseroomapplydemoIsNotNull() {
			addCriterion("ASSEROOMAPPLYDEMO is not null");
			return this;
		}

		public Criteria andAsseroomapplydemoEqualTo(String value) {
			addCriterion("ASSEROOMAPPLYDEMO =", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoNotEqualTo(String value) {
			addCriterion("ASSEROOMAPPLYDEMO <>", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoGreaterThan(String value) {
			addCriterion("ASSEROOMAPPLYDEMO >", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoGreaterThanOrEqualTo(String value) {
			addCriterion("ASSEROOMAPPLYDEMO >=", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoLessThan(String value) {
			addCriterion("ASSEROOMAPPLYDEMO <", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoLessThanOrEqualTo(String value) {
			addCriterion("ASSEROOMAPPLYDEMO <=", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoLike(String value) {
			addCriterion("ASSEROOMAPPLYDEMO like", value, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoNotLike(String value) {
			addCriterion("ASSEROOMAPPLYDEMO not like", value,
					"asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoIn(List<String> values) {
			addCriterion("ASSEROOMAPPLYDEMO in", values, "asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoNotIn(List<String> values) {
			addCriterion("ASSEROOMAPPLYDEMO not in", values,
					"asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoBetween(String value1, String value2) {
			addCriterion("ASSEROOMAPPLYDEMO between", value1, value2,
					"asseroomapplydemo");
			return this;
		}

		public Criteria andAsseroomapplydemoNotBetween(String value1,
				String value2) {
			addCriterion("ASSEROOMAPPLYDEMO not between", value1, value2,
					"asseroomapplydemo");
			return this;
		}
	}
}