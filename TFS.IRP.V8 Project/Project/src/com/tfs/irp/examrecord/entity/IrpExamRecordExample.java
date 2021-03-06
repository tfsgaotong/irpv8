package com.tfs.irp.examrecord.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpExamRecordExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public IrpExamRecordExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	protected IrpExamRecordExample(IrpExamRecordExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table irp_exam_record
	 * @ibatorgenerated  Thu Oct 30 09:59:08 CST 2014
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

		public Criteria andRecordidIsNull() {
			addCriterion("RECORDID is null");
			return this;
		}

		public Criteria andRecordidIsNotNull() {
			addCriterion("RECORDID is not null");
			return this;
		}

		public Criteria andRecordidEqualTo(Long value) {
			addCriterion("RECORDID =", value, "recordid");
			return this;
		}

		public Criteria andRecordidNotEqualTo(Long value) {
			addCriterion("RECORDID <>", value, "recordid");
			return this;
		}

		public Criteria andRecordidGreaterThan(Long value) {
			addCriterion("RECORDID >", value, "recordid");
			return this;
		}

		public Criteria andRecordidGreaterThanOrEqualTo(Long value) {
			addCriterion("RECORDID >=", value, "recordid");
			return this;
		}

		public Criteria andRecordidLessThan(Long value) {
			addCriterion("RECORDID <", value, "recordid");
			return this;
		}

		public Criteria andRecordidLessThanOrEqualTo(Long value) {
			addCriterion("RECORDID <=", value, "recordid");
			return this;
		}

		public Criteria andRecordidIn(List<Long> values) {
			addCriterion("RECORDID in", values, "recordid");
			return this;
		}

		public Criteria andRecordidNotIn(List<Long> values) {
			addCriterion("RECORDID not in", values, "recordid");
			return this;
		}

		public Criteria andRecordidBetween(Long value1, Long value2) {
			addCriterion("RECORDID between", value1, value2, "recordid");
			return this;
		}

		public Criteria andRecordidNotBetween(Long value1, Long value2) {
			addCriterion("RECORDID not between", value1, value2, "recordid");
			return this;
		}

		public Criteria andExamidIsNull() {
			addCriterion("EXAMID is null");
			return this;
		}

		public Criteria andExamidIsNotNull() {
			addCriterion("EXAMID is not null");
			return this;
		}

		public Criteria andExamidEqualTo(Long value) {
			addCriterion("EXAMID =", value, "examid");
			return this;
		}

		public Criteria andExamidNotEqualTo(Long value) {
			addCriterion("EXAMID <>", value, "examid");
			return this;
		}

		public Criteria andExamidGreaterThan(Long value) {
			addCriterion("EXAMID >", value, "examid");
			return this;
		}

		public Criteria andExamidGreaterThanOrEqualTo(Long value) {
			addCriterion("EXAMID >=", value, "examid");
			return this;
		}

		public Criteria andExamidLessThan(Long value) {
			addCriterion("EXAMID <", value, "examid");
			return this;
		}

		public Criteria andExamidLessThanOrEqualTo(Long value) {
			addCriterion("EXAMID <=", value, "examid");
			return this;
		}

		public Criteria andExamidIn(List<Long> values) {
			addCriterion("EXAMID in", values, "examid");
			return this;
		}

		public Criteria andExamidNotIn(List<Long> values) {
			addCriterion("EXAMID not in", values, "examid");
			return this;
		}

		public Criteria andExamidBetween(Long value1, Long value2) {
			addCriterion("EXAMID between", value1, value2, "examid");
			return this;
		}

		public Criteria andExamidNotBetween(Long value1, Long value2) {
			addCriterion("EXAMID not between", value1, value2, "examid");
			return this;
		}

		public Criteria andExamtimeIsNull() {
			addCriterion("EXAMTIME is null");
			return this;
		}

		public Criteria andExamtimeIsNotNull() {
			addCriterion("EXAMTIME is not null");
			return this;
		}

		public Criteria andExamtimeEqualTo(Integer value) {
			addCriterion("EXAMTIME =", value, "examtime");
			return this;
		}

		public Criteria andExamtimeNotEqualTo(Integer value) {
			addCriterion("EXAMTIME <>", value, "examtime");
			return this;
		}

		public Criteria andExamtimeGreaterThan(Integer value) {
			addCriterion("EXAMTIME >", value, "examtime");
			return this;
		}

		public Criteria andExamtimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("EXAMTIME >=", value, "examtime");
			return this;
		}

		public Criteria andExamtimeLessThan(Integer value) {
			addCriterion("EXAMTIME <", value, "examtime");
			return this;
		}

		public Criteria andExamtimeLessThanOrEqualTo(Integer value) {
			addCriterion("EXAMTIME <=", value, "examtime");
			return this;
		}

		public Criteria andExamtimeIn(List<Integer> values) {
			addCriterion("EXAMTIME in", values, "examtime");
			return this;
		}

		public Criteria andExamtimeNotIn(List<Integer> values) {
			addCriterion("EXAMTIME not in", values, "examtime");
			return this;
		}

		public Criteria andExamtimeBetween(Integer value1, Integer value2) {
			addCriterion("EXAMTIME between", value1, value2, "examtime");
			return this;
		}

		public Criteria andExamtimeNotBetween(Integer value1, Integer value2) {
			addCriterion("EXAMTIME not between", value1, value2, "examtime");
			return this;
		}

		public Criteria andExamstatusIsNull() {
			addCriterion("EXAMSTATUS is null");
			return this;
		}

		public Criteria andExamstatusIsNotNull() {
			addCriterion("EXAMSTATUS is not null");
			return this;
		}

		public Criteria andExamstatusEqualTo(Integer value) {
			addCriterion("EXAMSTATUS =", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusNotEqualTo(Integer value) {
			addCriterion("EXAMSTATUS <>", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusGreaterThan(Integer value) {
			addCriterion("EXAMSTATUS >", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("EXAMSTATUS >=", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusLessThan(Integer value) {
			addCriterion("EXAMSTATUS <", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusLessThanOrEqualTo(Integer value) {
			addCriterion("EXAMSTATUS <=", value, "examstatus");
			return this;
		}

		public Criteria andExamstatusIn(List<Integer> values) {
			addCriterion("EXAMSTATUS in", values, "examstatus");
			return this;
		}

		public Criteria andExamstatusNotIn(List<Integer> values) {
			addCriterion("EXAMSTATUS not in", values, "examstatus");
			return this;
		}

		public Criteria andExamstatusBetween(Integer value1, Integer value2) {
			addCriterion("EXAMSTATUS between", value1, value2, "examstatus");
			return this;
		}

		public Criteria andExamstatusNotBetween(Integer value1, Integer value2) {
			addCriterion("EXAMSTATUS not between", value1, value2, "examstatus");
			return this;
		}

		public Criteria andExamgradeIsNull() {
			addCriterion("EXAMGRADE is null");
			return this;
		}

		public Criteria andExamgradeIsNotNull() {
			addCriterion("EXAMGRADE is not null");
			return this;
		}

		public Criteria andExamgradeEqualTo(Integer value) {
			addCriterion("EXAMGRADE =", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeNotEqualTo(Integer value) {
			addCriterion("EXAMGRADE <>", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeGreaterThan(Integer value) {
			addCriterion("EXAMGRADE >", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeGreaterThanOrEqualTo(Integer value) {
			addCriterion("EXAMGRADE >=", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeLessThan(Integer value) {
			addCriterion("EXAMGRADE <", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeLessThanOrEqualTo(Integer value) {
			addCriterion("EXAMGRADE <=", value, "examgrade");
			return this;
		}

		public Criteria andExamgradeIn(List<Integer> values) {
			addCriterion("EXAMGRADE in", values, "examgrade");
			return this;
		}

		public Criteria andExamgradeNotIn(List<Integer> values) {
			addCriterion("EXAMGRADE not in", values, "examgrade");
			return this;
		}

		public Criteria andExamgradeBetween(Integer value1, Integer value2) {
			addCriterion("EXAMGRADE between", value1, value2, "examgrade");
			return this;
		}

		public Criteria andExamgradeNotBetween(Integer value1, Integer value2) {
			addCriterion("EXAMGRADE not between", value1, value2, "examgrade");
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

		public Criteria andExtendoneIsNull() {
			addCriterion("EXTENDONE is null");
			return this;
		}

		public Criteria andExtendoneIsNotNull() {
			addCriterion("EXTENDONE is not null");
			return this;
		}

		public Criteria andExtendoneEqualTo(Long value) {
			addCriterion("EXTENDONE =", value, "extendone");
			return this;
		}

		public Criteria andExtendoneNotEqualTo(Long value) {
			addCriterion("EXTENDONE <>", value, "extendone");
			return this;
		}

		public Criteria andExtendoneGreaterThan(Long value) {
			addCriterion("EXTENDONE >", value, "extendone");
			return this;
		}

		public Criteria andExtendoneGreaterThanOrEqualTo(Long value) {
			addCriterion("EXTENDONE >=", value, "extendone");
			return this;
		}

		public Criteria andExtendoneLessThan(Long value) {
			addCriterion("EXTENDONE <", value, "extendone");
			return this;
		}

		public Criteria andExtendoneLessThanOrEqualTo(Long value) {
			addCriterion("EXTENDONE <=", value, "extendone");
			return this;
		}

		public Criteria andExtendoneIn(List<Long> values) {
			addCriterion("EXTENDONE in", values, "extendone");
			return this;
		}

		public Criteria andExtendoneNotIn(List<Long> values) {
			addCriterion("EXTENDONE not in", values, "extendone");
			return this;
		}

		public Criteria andExtendoneBetween(Long value1, Long value2) {
			addCriterion("EXTENDONE between", value1, value2, "extendone");
			return this;
		}

		public Criteria andExtendoneNotBetween(Long value1, Long value2) {
			addCriterion("EXTENDONE not between", value1, value2, "extendone");
			return this;
		}

		public Criteria andExtendtwoIsNull() {
			addCriterion("EXTENDTWO is null");
			return this;
		}

		public Criteria andExtendtwoIsNotNull() {
			addCriterion("EXTENDTWO is not null");
			return this;
		}

		public Criteria andExtendtwoEqualTo(String value) {
			addCriterion("EXTENDTWO =", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoNotEqualTo(String value) {
			addCriterion("EXTENDTWO <>", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoGreaterThan(String value) {
			addCriterion("EXTENDTWO >", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoGreaterThanOrEqualTo(String value) {
			addCriterion("EXTENDTWO >=", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoLessThan(String value) {
			addCriterion("EXTENDTWO <", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoLessThanOrEqualTo(String value) {
			addCriterion("EXTENDTWO <=", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoLike(String value) {
			addCriterion("EXTENDTWO like", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoNotLike(String value) {
			addCriterion("EXTENDTWO not like", value, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoIn(List<String> values) {
			addCriterion("EXTENDTWO in", values, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoNotIn(List<String> values) {
			addCriterion("EXTENDTWO not in", values, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoBetween(String value1, String value2) {
			addCriterion("EXTENDTWO between", value1, value2, "extendtwo");
			return this;
		}

		public Criteria andExtendtwoNotBetween(String value1, String value2) {
			addCriterion("EXTENDTWO not between", value1, value2, "extendtwo");
			return this;
		}

		public Criteria andExamidtimesIsNull() {
			addCriterion("EXAMIDTIMES is null");
			return this;
		}

		public Criteria andExamidtimesIsNotNull() {
			addCriterion("EXAMIDTIMES is not null");
			return this;
		}

		public Criteria andExamidtimesEqualTo(String value) {
			addCriterion("EXAMIDTIMES =", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesNotEqualTo(String value) {
			addCriterion("EXAMIDTIMES <>", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesGreaterThan(String value) {
			addCriterion("EXAMIDTIMES >", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesGreaterThanOrEqualTo(String value) {
			addCriterion("EXAMIDTIMES >=", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesLessThan(String value) {
			addCriterion("EXAMIDTIMES <", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesLessThanOrEqualTo(String value) {
			addCriterion("EXAMIDTIMES <=", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesLike(String value) {
			addCriterion("EXAMIDTIMES like", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesNotLike(String value) {
			addCriterion("EXAMIDTIMES not like", value, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesIn(List<String> values) {
			addCriterion("EXAMIDTIMES in", values, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesNotIn(List<String> values) {
			addCriterion("EXAMIDTIMES not in", values, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesBetween(String value1, String value2) {
			addCriterion("EXAMIDTIMES between", value1, value2, "examidtimes");
			return this;
		}

		public Criteria andExamidtimesNotBetween(String value1, String value2) {
			addCriterion("EXAMIDTIMES not between", value1, value2,
					"examidtimes");
			return this;
		}
	}
}