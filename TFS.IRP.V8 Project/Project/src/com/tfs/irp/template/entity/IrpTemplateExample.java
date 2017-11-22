package com.tfs.irp.template.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class IrpTemplateExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public IrpTemplateExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	protected IrpTemplateExample(IrpTemplateExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table irp_template
	 * @ibatorgenerated  Wed Jul 02 10:43:37 CST 2014
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

		public Criteria andTidIsNull() {
			addCriterion("tid is null");
			return this;
		}

		public Criteria andTidIsNotNull() {
			addCriterion("tid is not null");
			return this;
		}

		public Criteria andTidEqualTo(Long value) {
			addCriterion("tid =", value, "tid");
			return this;
		}

		public Criteria andTidNotEqualTo(Long value) {
			addCriterion("tid <>", value, "tid");
			return this;
		}

		public Criteria andTidGreaterThan(Long value) {
			addCriterion("tid >", value, "tid");
			return this;
		}

		public Criteria andTidGreaterThanOrEqualTo(Long value) {
			addCriterion("tid >=", value, "tid");
			return this;
		}

		public Criteria andTidLessThan(Long value) {
			addCriterion("tid <", value, "tid");
			return this;
		}

		public Criteria andTidLessThanOrEqualTo(Long value) {
			addCriterion("tid <=", value, "tid");
			return this;
		}

		public Criteria andTidIn(List<Long> values) {
			addCriterion("tid in", values, "tid");
			return this;
		}

		public Criteria andTidNotIn(List<Long> values) {
			addCriterion("tid not in", values, "tid");
			return this;
		}

		public Criteria andTidBetween(Long value1, Long value2) {
			addCriterion("tid between", value1, value2, "tid");
			return this;
		}

		public Criteria andTidNotBetween(Long value1, Long value2) {
			addCriterion("tid not between", value1, value2, "tid");
			return this;
		}

		public Criteria andTcateIsNull() {
			addCriterion("tcate is null");
			return this;
		}

		public Criteria andTcateIsNotNull() {
			addCriterion("tcate is not null");
			return this;
		}

		public Criteria andTcateEqualTo(Long value) {
			addCriterion("tcate =", value, "tcate");
			return this;
		}

		public Criteria andTcateNotEqualTo(Long value) {
			addCriterion("tcate <>", value, "tcate");
			return this;
		}

		public Criteria andTcateGreaterThan(Long value) {
			addCriterion("tcate >", value, "tcate");
			return this;
		}

		public Criteria andTcateGreaterThanOrEqualTo(Long value) {
			addCriterion("tcate >=", value, "tcate");
			return this;
		}

		public Criteria andTcateLessThan(Long value) {
			addCriterion("tcate <", value, "tcate");
			return this;
		}

		public Criteria andTcateLessThanOrEqualTo(Long value) {
			addCriterion("tcate <=", value, "tcate");
			return this;
		}

		public Criteria andTcateIn(List<Long> values) {
			addCriterion("tcate in", values, "tcate");
			return this;
		}

		public Criteria andTcateNotIn(List<Long> values) {
			addCriterion("tcate not in", values, "tcate");
			return this;
		}

		public Criteria andTcateBetween(Long value1, Long value2) {
			addCriterion("tcate between", value1, value2, "tcate");
			return this;
		}

		public Criteria andTcateNotBetween(Long value1, Long value2) {
			addCriterion("tcate not between", value1, value2, "tcate");
			return this;
		}

		public Criteria andTqoteidIsNull() {
			addCriterion("tqoteid is null");
			return this;
		}

		public Criteria andTqoteidIsNotNull() {
			addCriterion("tqoteid is not null");
			return this;
		}

		public Criteria andTqoteidEqualTo(Long value) {
			addCriterion("tqoteid =", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidNotEqualTo(Long value) {
			addCriterion("tqoteid <>", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidGreaterThan(Long value) {
			addCriterion("tqoteid >", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidGreaterThanOrEqualTo(Long value) {
			addCriterion("tqoteid >=", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidLessThan(Long value) {
			addCriterion("tqoteid <", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidLessThanOrEqualTo(Long value) {
			addCriterion("tqoteid <=", value, "tqoteid");
			return this;
		}

		public Criteria andTqoteidIn(List<Long> values) {
			addCriterion("tqoteid in", values, "tqoteid");
			return this;
		}

		public Criteria andTqoteidNotIn(List<Long> values) {
			addCriterion("tqoteid not in", values, "tqoteid");
			return this;
		}

		public Criteria andTqoteidBetween(Long value1, Long value2) {
			addCriterion("tqoteid between", value1, value2, "tqoteid");
			return this;
		}

		public Criteria andTqoteidNotBetween(Long value1, Long value2) {
			addCriterion("tqoteid not between", value1, value2, "tqoteid");
			return this;
		}

		public Criteria andTstatusIsNull() {
			addCriterion("tstatus is null");
			return this;
		}

		public Criteria andTstatusIsNotNull() {
			addCriterion("tstatus is not null");
			return this;
		}

		public Criteria andTstatusEqualTo(Integer value) {
			addCriterion("tstatus =", value, "tstatus");
			return this;
		}

		public Criteria andTstatusNotEqualTo(Integer value) {
			addCriterion("tstatus <>", value, "tstatus");
			return this;
		}

		public Criteria andTstatusGreaterThan(Integer value) {
			addCriterion("tstatus >", value, "tstatus");
			return this;
		}

		public Criteria andTstatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("tstatus >=", value, "tstatus");
			return this;
		}

		public Criteria andTstatusLessThan(Integer value) {
			addCriterion("tstatus <", value, "tstatus");
			return this;
		}

		public Criteria andTstatusLessThanOrEqualTo(Integer value) {
			addCriterion("tstatus <=", value, "tstatus");
			return this;
		}

		public Criteria andTstatusIn(List<Integer> values) {
			addCriterion("tstatus in", values, "tstatus");
			return this;
		}

		public Criteria andTstatusNotIn(List<Integer> values) {
			addCriterion("tstatus not in", values, "tstatus");
			return this;
		}

		public Criteria andTstatusBetween(Integer value1, Integer value2) {
			addCriterion("tstatus between", value1, value2, "tstatus");
			return this;
		}

		public Criteria andTstatusNotBetween(Integer value1, Integer value2) {
			addCriterion("tstatus not between", value1, value2, "tstatus");
			return this;
		}

		public Criteria andTisdelIsNull() {
			addCriterion("tisdel is null");
			return this;
		}

		public Criteria andTisdelIsNotNull() {
			addCriterion("tisdel is not null");
			return this;
		}

		public Criteria andTisdelEqualTo(Integer value) {
			addCriterion("tisdel =", value, "tisdel");
			return this;
		}

		public Criteria andTisdelNotEqualTo(Integer value) {
			addCriterion("tisdel <>", value, "tisdel");
			return this;
		}

		public Criteria andTisdelGreaterThan(Integer value) {
			addCriterion("tisdel >", value, "tisdel");
			return this;
		}

		public Criteria andTisdelGreaterThanOrEqualTo(Integer value) {
			addCriterion("tisdel >=", value, "tisdel");
			return this;
		}

		public Criteria andTisdelLessThan(Integer value) {
			addCriterion("tisdel <", value, "tisdel");
			return this;
		}

		public Criteria andTisdelLessThanOrEqualTo(Integer value) {
			addCriterion("tisdel <=", value, "tisdel");
			return this;
		}

		public Criteria andTisdelIn(List<Integer> values) {
			addCriterion("tisdel in", values, "tisdel");
			return this;
		}

		public Criteria andTisdelNotIn(List<Integer> values) {
			addCriterion("tisdel not in", values, "tisdel");
			return this;
		}

		public Criteria andTisdelBetween(Integer value1, Integer value2) {
			addCriterion("tisdel between", value1, value2, "tisdel");
			return this;
		}

		public Criteria andTisdelNotBetween(Integer value1, Integer value2) {
			addCriterion("tisdel not between", value1, value2, "tisdel");
			return this;
		}

		public Criteria andCrtimeIsNull() {
			addCriterion("crtime is null");
			return this;
		}

		public Criteria andCrtimeIsNotNull() {
			addCriterion("crtime is not null");
			return this;
		}

		public Criteria andCrtimeEqualTo(Date value) {
			addCriterion("crtime =", value, "crtime");
			return this;
		}

		public Criteria andCrtimeNotEqualTo(Date value) {
			addCriterion("crtime <>", value, "crtime");
			return this;
		}

		public Criteria andCrtimeGreaterThan(Date value) {
			addCriterion("crtime >", value, "crtime");
			return this;
		}

		public Criteria andCrtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("crtime >=", value, "crtime");
			return this;
		}

		public Criteria andCrtimeLessThan(Date value) {
			addCriterion("crtime <", value, "crtime");
			return this;
		}

		public Criteria andCrtimeLessThanOrEqualTo(Date value) {
			addCriterion("crtime <=", value, "crtime");
			return this;
		}

		public Criteria andCrtimeIn(List<Date> values) {
			addCriterion("crtime in", values, "crtime");
			return this;
		}

		public Criteria andCrtimeNotIn(List<Date> values) {
			addCriterion("crtime not in", values, "crtime");
			return this;
		}

		public Criteria andCrtimeBetween(Date value1, Date value2) {
			addCriterion("crtime between", value1, value2, "crtime");
			return this;
		}

		public Criteria andCrtimeNotBetween(Date value1, Date value2) {
			addCriterion("crtime not between", value1, value2, "crtime");
			return this;
		}

		public Criteria andCruseridIsNull() {
			addCriterion("cruserid is null");
			return this;
		}

		public Criteria andCruseridIsNotNull() {
			addCriterion("cruserid is not null");
			return this;
		}

		public Criteria andCruseridEqualTo(Long value) {
			addCriterion("cruserid =", value, "cruserid");
			return this;
		}

		public Criteria andCruseridNotEqualTo(Long value) {
			addCriterion("cruserid <>", value, "cruserid");
			return this;
		}

		public Criteria andCruseridGreaterThan(Long value) {
			addCriterion("cruserid >", value, "cruserid");
			return this;
		}

		public Criteria andCruseridGreaterThanOrEqualTo(Long value) {
			addCriterion("cruserid >=", value, "cruserid");
			return this;
		}

		public Criteria andCruseridLessThan(Long value) {
			addCriterion("cruserid <", value, "cruserid");
			return this;
		}

		public Criteria andCruseridLessThanOrEqualTo(Long value) {
			addCriterion("cruserid <=", value, "cruserid");
			return this;
		}

		public Criteria andCruseridIn(List<Long> values) {
			addCriterion("cruserid in", values, "cruserid");
			return this;
		}

		public Criteria andCruseridNotIn(List<Long> values) {
			addCriterion("cruserid not in", values, "cruserid");
			return this;
		}

		public Criteria andCruseridBetween(Long value1, Long value2) {
			addCriterion("cruserid between", value1, value2, "cruserid");
			return this;
		}

		public Criteria andCruseridNotBetween(Long value1, Long value2) {
			addCriterion("cruserid not between", value1, value2, "cruserid");
			return this;
		}

		public Criteria andTvaluedescIsNull() {
			addCriterion("tvaluedesc is null");
			return this;
		}

		public Criteria andTvaluedescIsNotNull() {
			addCriterion("tvaluedesc is not null");
			return this;
		}

		public Criteria andTvaluedescEqualTo(String value) {
			addCriterion("tvaluedesc =", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescNotEqualTo(String value) {
			addCriterion("tvaluedesc <>", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescGreaterThan(String value) {
			addCriterion("tvaluedesc >", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescGreaterThanOrEqualTo(String value) {
			addCriterion("tvaluedesc >=", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescLessThan(String value) {
			addCriterion("tvaluedesc <", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescLessThanOrEqualTo(String value) {
			addCriterion("tvaluedesc <=", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescLike(String value) {
			addCriterion("tvaluedesc like", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescNotLike(String value) {
			addCriterion("tvaluedesc not like", value, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescIn(List<String> values) {
			addCriterion("tvaluedesc in", values, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescNotIn(List<String> values) {
			addCriterion("tvaluedesc not in", values, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescBetween(String value1, String value2) {
			addCriterion("tvaluedesc between", value1, value2, "tvaluedesc");
			return this;
		}

		public Criteria andTvaluedescNotBetween(String value1, String value2) {
			addCriterion("tvaluedesc not between", value1, value2, "tvaluedesc");
			return this;
		}
	}
}