package com.tfs.irp.logs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpLogsExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public IrpLogsExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	protected IrpLogsExample(IrpLogsExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_LOGS
	 * @ibatorgenerated  Mon Mar 25 09:27:48 CST 2013
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

		public Criteria andLogidIsNull() {
			addCriterion("LOGID is null");
			return this;
		}

		public Criteria andLogidIsNotNull() {
			addCriterion("LOGID is not null");
			return this;
		}

		public Criteria andLogidEqualTo(Long value) {
			addCriterion("LOGID =", value, "logid");
			return this;
		}

		public Criteria andLogidNotEqualTo(Long value) {
			addCriterion("LOGID <>", value, "logid");
			return this;
		}

		public Criteria andLogidGreaterThan(Long value) {
			addCriterion("LOGID >", value, "logid");
			return this;
		}

		public Criteria andLogidGreaterThanOrEqualTo(Long value) {
			addCriterion("LOGID >=", value, "logid");
			return this;
		}

		public Criteria andLogidLessThan(Long value) {
			addCriterion("LOGID <", value, "logid");
			return this;
		}

		public Criteria andLogidLessThanOrEqualTo(Long value) {
			addCriterion("LOGID <=", value, "logid");
			return this;
		}

		public Criteria andLogidIn(List<Long> values) {
			addCriterion("LOGID in", values, "logid");
			return this;
		}

		public Criteria andLogidNotIn(List<Long> values) {
			addCriterion("LOGID not in", values, "logid");
			return this;
		}

		public Criteria andLogidBetween(Long value1, Long value2) {
			addCriterion("LOGID between", value1, value2, "logid");
			return this;
		}

		public Criteria andLogidNotBetween(Long value1, Long value2) {
			addCriterion("LOGID not between", value1, value2, "logid");
			return this;
		}

		public Criteria andLogtypeIsNull() {
			addCriterion("LOGTYPE is null");
			return this;
		}

		public Criteria andLogtypeIsNotNull() {
			addCriterion("LOGTYPE is not null");
			return this;
		}

		public Criteria andLogtypeEqualTo(Long value) {
			addCriterion("LOGTYPE =", value, "logtype");
			return this;
		}

		public Criteria andLogtypeNotEqualTo(Long value) {
			addCriterion("LOGTYPE <>", value, "logtype");
			return this;
		}

		public Criteria andLogtypeGreaterThan(Long value) {
			addCriterion("LOGTYPE >", value, "logtype");
			return this;
		}

		public Criteria andLogtypeGreaterThanOrEqualTo(Long value) {
			addCriterion("LOGTYPE >=", value, "logtype");
			return this;
		}

		public Criteria andLogtypeLessThan(Long value) {
			addCriterion("LOGTYPE <", value, "logtype");
			return this;
		}

		public Criteria andLogtypeLessThanOrEqualTo(Long value) {
			addCriterion("LOGTYPE <=", value, "logtype");
			return this;
		}

		public Criteria andLogtypeIn(List<Long> values) {
			addCriterion("LOGTYPE in", values, "logtype");
			return this;
		}

		public Criteria andLogtypeNotIn(List<Long> values) {
			addCriterion("LOGTYPE not in", values, "logtype");
			return this;
		}

		public Criteria andLogtypeBetween(Long value1, Long value2) {
			addCriterion("LOGTYPE between", value1, value2, "logtype");
			return this;
		}

		public Criteria andLogtypeNotBetween(Long value1, Long value2) {
			addCriterion("LOGTYPE not between", value1, value2, "logtype");
			return this;
		}

		public Criteria andLoginfoIsNull() {
			addCriterion("LOGINFO is null");
			return this;
		}

		public Criteria andLoginfoIsNotNull() {
			addCriterion("LOGINFO is not null");
			return this;
		}

		public Criteria andLoginfoEqualTo(String value) {
			addCriterion("LOGINFO =", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoNotEqualTo(String value) {
			addCriterion("LOGINFO <>", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoGreaterThan(String value) {
			addCriterion("LOGINFO >", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoGreaterThanOrEqualTo(String value) {
			addCriterion("LOGINFO >=", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoLessThan(String value) {
			addCriterion("LOGINFO <", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoLessThanOrEqualTo(String value) {
			addCriterion("LOGINFO <=", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoLike(String value) {
			addCriterion("LOGINFO like", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoNotLike(String value) {
			addCriterion("LOGINFO not like", value, "loginfo");
			return this;
		}

		public Criteria andLoginfoIn(List<String> values) {
			addCriterion("LOGINFO in", values, "loginfo");
			return this;
		}

		public Criteria andLoginfoNotIn(List<String> values) {
			addCriterion("LOGINFO not in", values, "loginfo");
			return this;
		}

		public Criteria andLoginfoBetween(String value1, String value2) {
			addCriterion("LOGINFO between", value1, value2, "loginfo");
			return this;
		}

		public Criteria andLoginfoNotBetween(String value1, String value2) {
			addCriterion("LOGINFO not between", value1, value2, "loginfo");
			return this;
		}

		public Criteria andLogobjnameIsNull() {
			addCriterion("LOGOBJNAME is null");
			return this;
		}

		public Criteria andLogobjnameIsNotNull() {
			addCriterion("LOGOBJNAME is not null");
			return this;
		}

		public Criteria andLogobjnameEqualTo(String value) {
			addCriterion("LOGOBJNAME =", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameNotEqualTo(String value) {
			addCriterion("LOGOBJNAME <>", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameGreaterThan(String value) {
			addCriterion("LOGOBJNAME >", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameGreaterThanOrEqualTo(String value) {
			addCriterion("LOGOBJNAME >=", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameLessThan(String value) {
			addCriterion("LOGOBJNAME <", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameLessThanOrEqualTo(String value) {
			addCriterion("LOGOBJNAME <=", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameLike(String value) {
			addCriterion("LOGOBJNAME like", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameNotLike(String value) {
			addCriterion("LOGOBJNAME not like", value, "logobjname");
			return this;
		}

		public Criteria andLogobjnameIn(List<String> values) {
			addCriterion("LOGOBJNAME in", values, "logobjname");
			return this;
		}

		public Criteria andLogobjnameNotIn(List<String> values) {
			addCriterion("LOGOBJNAME not in", values, "logobjname");
			return this;
		}

		public Criteria andLogobjnameBetween(String value1, String value2) {
			addCriterion("LOGOBJNAME between", value1, value2, "logobjname");
			return this;
		}

		public Criteria andLogobjnameNotBetween(String value1, String value2) {
			addCriterion("LOGOBJNAME not between", value1, value2, "logobjname");
			return this;
		}

		public Criteria andLogobjtypeIsNull() {
			addCriterion("LOGOBJTYPE is null");
			return this;
		}

		public Criteria andLogobjtypeIsNotNull() {
			addCriterion("LOGOBJTYPE is not null");
			return this;
		}

		public Criteria andLogobjtypeEqualTo(String value) {
			addCriterion("LOGOBJTYPE =", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeNotEqualTo(String value) {
			addCriterion("LOGOBJTYPE <>", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeGreaterThan(String value) {
			addCriterion("LOGOBJTYPE >", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeGreaterThanOrEqualTo(String value) {
			addCriterion("LOGOBJTYPE >=", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeLessThan(String value) {
			addCriterion("LOGOBJTYPE <", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeLessThanOrEqualTo(String value) {
			addCriterion("LOGOBJTYPE <=", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeLike(String value) {
			addCriterion("LOGOBJTYPE like", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeNotLike(String value) {
			addCriterion("LOGOBJTYPE not like", value, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeIn(List<String> values) {
			addCriterion("LOGOBJTYPE in", values, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeNotIn(List<String> values) {
			addCriterion("LOGOBJTYPE not in", values, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeBetween(String value1, String value2) {
			addCriterion("LOGOBJTYPE between", value1, value2, "logobjtype");
			return this;
		}

		public Criteria andLogobjtypeNotBetween(String value1, String value2) {
			addCriterion("LOGOBJTYPE not between", value1, value2, "logobjtype");
			return this;
		}

		public Criteria andLogobjidIsNull() {
			addCriterion("LOGOBJID is null");
			return this;
		}

		public Criteria andLogobjidIsNotNull() {
			addCriterion("LOGOBJID is not null");
			return this;
		}

		public Criteria andLogobjidEqualTo(Long value) {
			addCriterion("LOGOBJID =", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidNotEqualTo(Long value) {
			addCriterion("LOGOBJID <>", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidGreaterThan(Long value) {
			addCriterion("LOGOBJID >", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidGreaterThanOrEqualTo(Long value) {
			addCriterion("LOGOBJID >=", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidLessThan(Long value) {
			addCriterion("LOGOBJID <", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidLessThanOrEqualTo(Long value) {
			addCriterion("LOGOBJID <=", value, "logobjid");
			return this;
		}

		public Criteria andLogobjidIn(List<Long> values) {
			addCriterion("LOGOBJID in", values, "logobjid");
			return this;
		}

		public Criteria andLogobjidNotIn(List<Long> values) {
			addCriterion("LOGOBJID not in", values, "logobjid");
			return this;
		}

		public Criteria andLogobjidBetween(Long value1, Long value2) {
			addCriterion("LOGOBJID between", value1, value2, "logobjid");
			return this;
		}

		public Criteria andLogobjidNotBetween(Long value1, Long value2) {
			addCriterion("LOGOBJID not between", value1, value2, "logobjid");
			return this;
		}

		public Criteria andLogoptypeIsNull() {
			addCriterion("LOGOPTYPE is null");
			return this;
		}

		public Criteria andLogoptypeIsNotNull() {
			addCriterion("LOGOPTYPE is not null");
			return this;
		}

		public Criteria andLogoptypeEqualTo(String value) {
			addCriterion("LOGOPTYPE =", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeNotEqualTo(String value) {
			addCriterion("LOGOPTYPE <>", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeGreaterThan(String value) {
			addCriterion("LOGOPTYPE >", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeGreaterThanOrEqualTo(String value) {
			addCriterion("LOGOPTYPE >=", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeLessThan(String value) {
			addCriterion("LOGOPTYPE <", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeLessThanOrEqualTo(String value) {
			addCriterion("LOGOPTYPE <=", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeLike(String value) {
			addCriterion("LOGOPTYPE like", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeNotLike(String value) {
			addCriterion("LOGOPTYPE not like", value, "logoptype");
			return this;
		}

		public Criteria andLogoptypeIn(List<String> values) {
			addCriterion("LOGOPTYPE in", values, "logoptype");
			return this;
		}

		public Criteria andLogoptypeNotIn(List<String> values) {
			addCriterion("LOGOPTYPE not in", values, "logoptype");
			return this;
		}

		public Criteria andLogoptypeBetween(String value1, String value2) {
			addCriterion("LOGOPTYPE between", value1, value2, "logoptype");
			return this;
		}

		public Criteria andLogoptypeNotBetween(String value1, String value2) {
			addCriterion("LOGOPTYPE not between", value1, value2, "logoptype");
			return this;
		}

		public Criteria andLogresultIsNull() {
			addCriterion("LOGRESULT is null");
			return this;
		}

		public Criteria andLogresultIsNotNull() {
			addCriterion("LOGRESULT is not null");
			return this;
		}

		public Criteria andLogresultEqualTo(Integer value) {
			addCriterion("LOGRESULT =", value, "logresult");
			return this;
		}

		public Criteria andLogresultNotEqualTo(Integer value) {
			addCriterion("LOGRESULT <>", value, "logresult");
			return this;
		}

		public Criteria andLogresultGreaterThan(Integer value) {
			addCriterion("LOGRESULT >", value, "logresult");
			return this;
		}

		public Criteria andLogresultGreaterThanOrEqualTo(Integer value) {
			addCriterion("LOGRESULT >=", value, "logresult");
			return this;
		}

		public Criteria andLogresultLessThan(Integer value) {
			addCriterion("LOGRESULT <", value, "logresult");
			return this;
		}

		public Criteria andLogresultLessThanOrEqualTo(Integer value) {
			addCriterion("LOGRESULT <=", value, "logresult");
			return this;
		}

		public Criteria andLogresultIn(List<Integer> values) {
			addCriterion("LOGRESULT in", values, "logresult");
			return this;
		}

		public Criteria andLogresultNotIn(List<Integer> values) {
			addCriterion("LOGRESULT not in", values, "logresult");
			return this;
		}

		public Criteria andLogresultBetween(Integer value1, Integer value2) {
			addCriterion("LOGRESULT between", value1, value2, "logresult");
			return this;
		}

		public Criteria andLogresultNotBetween(Integer value1, Integer value2) {
			addCriterion("LOGRESULT not between", value1, value2, "logresult");
			return this;
		}

		public Criteria andLoguserIsNull() {
			addCriterion("LOGUSER is null");
			return this;
		}

		public Criteria andLoguserIsNotNull() {
			addCriterion("LOGUSER is not null");
			return this;
		}

		public Criteria andLoguserEqualTo(String value) {
			addCriterion("LOGUSER =", value, "loguser");
			return this;
		}

		public Criteria andLoguserNotEqualTo(String value) {
			addCriterion("LOGUSER <>", value, "loguser");
			return this;
		}

		public Criteria andLoguserGreaterThan(String value) {
			addCriterion("LOGUSER >", value, "loguser");
			return this;
		}

		public Criteria andLoguserGreaterThanOrEqualTo(String value) {
			addCriterion("LOGUSER >=", value, "loguser");
			return this;
		}

		public Criteria andLoguserLessThan(String value) {
			addCriterion("LOGUSER <", value, "loguser");
			return this;
		}

		public Criteria andLoguserLessThanOrEqualTo(String value) {
			addCriterion("LOGUSER <=", value, "loguser");
			return this;
		}

		public Criteria andLoguserLike(String value) {
			addCriterion("LOGUSER like", value, "loguser");
			return this;
		}

		public Criteria andLoguserNotLike(String value) {
			addCriterion("LOGUSER not like", value, "loguser");
			return this;
		}

		public Criteria andLoguserIn(List<String> values) {
			addCriterion("LOGUSER in", values, "loguser");
			return this;
		}

		public Criteria andLoguserNotIn(List<String> values) {
			addCriterion("LOGUSER not in", values, "loguser");
			return this;
		}

		public Criteria andLoguserBetween(String value1, String value2) {
			addCriterion("LOGUSER between", value1, value2, "loguser");
			return this;
		}

		public Criteria andLoguserNotBetween(String value1, String value2) {
			addCriterion("LOGUSER not between", value1, value2, "loguser");
			return this;
		}

		public Criteria andLogoptimeIsNull() {
			addCriterion("LOGOPTIME is null");
			return this;
		}

		public Criteria andLogoptimeIsNotNull() {
			addCriterion("LOGOPTIME is not null");
			return this;
		}

		public Criteria andLogoptimeEqualTo(Date value) {
			addCriterionForJDBCDate("LOGOPTIME =", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeNotEqualTo(Date value) {
			addCriterionForJDBCDate("LOGOPTIME <>", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeGreaterThan(Date value) {
			addCriterionForJDBCDate("LOGOPTIME >", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("LOGOPTIME >=", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeLessThan(Date value) {
			addCriterionForJDBCDate("LOGOPTIME <", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("LOGOPTIME <=", value, "logoptime");
			return this;
		}

		public Criteria andLogoptimeIn(List<Date> values) {
			addCriterionForJDBCDate("LOGOPTIME in", values, "logoptime");
			return this;
		}

		public Criteria andLogoptimeNotIn(List<Date> values) {
			addCriterionForJDBCDate("LOGOPTIME not in", values, "logoptime");
			return this;
		}

		public Criteria andLogoptimeBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("LOGOPTIME between", value1, value2,
					"logoptime");
			return this;
		}

		public Criteria andLogoptimeNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("LOGOPTIME not between", value1, value2,
					"logoptime");
			return this;
		}

		public Criteria andStimemillisIsNull() {
			addCriterion("STIMEMILLIS is null");
			return this;
		}

		public Criteria andStimemillisIsNotNull() {
			addCriterion("STIMEMILLIS is not null");
			return this;
		}

		public Criteria andStimemillisEqualTo(BigDecimal value) {
			addCriterion("STIMEMILLIS =", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisNotEqualTo(BigDecimal value) {
			addCriterion("STIMEMILLIS <>", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisGreaterThan(BigDecimal value) {
			addCriterion("STIMEMILLIS >", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("STIMEMILLIS >=", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisLessThan(BigDecimal value) {
			addCriterion("STIMEMILLIS <", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisLessThanOrEqualTo(BigDecimal value) {
			addCriterion("STIMEMILLIS <=", value, "stimemillis");
			return this;
		}

		public Criteria andStimemillisIn(List<BigDecimal> values) {
			addCriterion("STIMEMILLIS in", values, "stimemillis");
			return this;
		}

		public Criteria andStimemillisNotIn(List<BigDecimal> values) {
			addCriterion("STIMEMILLIS not in", values, "stimemillis");
			return this;
		}

		public Criteria andStimemillisBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("STIMEMILLIS between", value1, value2, "stimemillis");
			return this;
		}

		public Criteria andStimemillisNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("STIMEMILLIS not between", value1, value2,
					"stimemillis");
			return this;
		}

		public Criteria andEtimemillisIsNull() {
			addCriterion("ETIMEMILLIS is null");
			return this;
		}

		public Criteria andEtimemillisIsNotNull() {
			addCriterion("ETIMEMILLIS is not null");
			return this;
		}

		public Criteria andEtimemillisEqualTo(BigDecimal value) {
			addCriterion("ETIMEMILLIS =", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisNotEqualTo(BigDecimal value) {
			addCriterion("ETIMEMILLIS <>", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisGreaterThan(BigDecimal value) {
			addCriterion("ETIMEMILLIS >", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("ETIMEMILLIS >=", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisLessThan(BigDecimal value) {
			addCriterion("ETIMEMILLIS <", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisLessThanOrEqualTo(BigDecimal value) {
			addCriterion("ETIMEMILLIS <=", value, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisIn(List<BigDecimal> values) {
			addCriterion("ETIMEMILLIS in", values, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisNotIn(List<BigDecimal> values) {
			addCriterion("ETIMEMILLIS not in", values, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("ETIMEMILLIS between", value1, value2, "etimemillis");
			return this;
		}

		public Criteria andEtimemillisNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("ETIMEMILLIS not between", value1, value2,
					"etimemillis");
			return this;
		}

		public Criteria andExectimeIsNull() {
			addCriterion("EXECTIME is null");
			return this;
		}

		public Criteria andExectimeIsNotNull() {
			addCriterion("EXECTIME is not null");
			return this;
		}

		public Criteria andExectimeEqualTo(BigDecimal value) {
			addCriterion("EXECTIME =", value, "exectime");
			return this;
		}

		public Criteria andExectimeNotEqualTo(BigDecimal value) {
			addCriterion("EXECTIME <>", value, "exectime");
			return this;
		}

		public Criteria andExectimeGreaterThan(BigDecimal value) {
			addCriterion("EXECTIME >", value, "exectime");
			return this;
		}

		public Criteria andExectimeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("EXECTIME >=", value, "exectime");
			return this;
		}

		public Criteria andExectimeLessThan(BigDecimal value) {
			addCriterion("EXECTIME <", value, "exectime");
			return this;
		}

		public Criteria andExectimeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("EXECTIME <=", value, "exectime");
			return this;
		}

		public Criteria andExectimeIn(List<BigDecimal> values) {
			addCriterion("EXECTIME in", values, "exectime");
			return this;
		}

		public Criteria andExectimeNotIn(List<BigDecimal> values) {
			addCriterion("EXECTIME not in", values, "exectime");
			return this;
		}

		public Criteria andExectimeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("EXECTIME between", value1, value2, "exectime");
			return this;
		}

		public Criteria andExectimeNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("EXECTIME not between", value1, value2, "exectime");
			return this;
		}

		public Criteria andLoguseripIsNull() {
			addCriterion("LOGUSERIP is null");
			return this;
		}

		public Criteria andLoguseripIsNotNull() {
			addCriterion("LOGUSERIP is not null");
			return this;
		}

		public Criteria andLoguseripEqualTo(String value) {
			addCriterion("LOGUSERIP =", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripNotEqualTo(String value) {
			addCriterion("LOGUSERIP <>", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripGreaterThan(String value) {
			addCriterion("LOGUSERIP >", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripGreaterThanOrEqualTo(String value) {
			addCriterion("LOGUSERIP >=", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripLessThan(String value) {
			addCriterion("LOGUSERIP <", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripLessThanOrEqualTo(String value) {
			addCriterion("LOGUSERIP <=", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripLike(String value) {
			addCriterion("LOGUSERIP like", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripNotLike(String value) {
			addCriterion("LOGUSERIP not like", value, "loguserip");
			return this;
		}

		public Criteria andLoguseripIn(List<String> values) {
			addCriterion("LOGUSERIP in", values, "loguserip");
			return this;
		}

		public Criteria andLoguseripNotIn(List<String> values) {
			addCriterion("LOGUSERIP not in", values, "loguserip");
			return this;
		}

		public Criteria andLoguseripBetween(String value1, String value2) {
			addCriterion("LOGUSERIP between", value1, value2, "loguserip");
			return this;
		}

		public Criteria andLoguseripNotBetween(String value1, String value2) {
			addCriterion("LOGUSERIP not between", value1, value2, "loguserip");
			return this;
		}

		public Criteria andSigninfoIsNull() {
			addCriterion("SIGNINFO is null");
			return this;
		}

		public Criteria andSigninfoIsNotNull() {
			addCriterion("SIGNINFO is not null");
			return this;
		}

		public Criteria andSigninfoEqualTo(String value) {
			addCriterion("SIGNINFO =", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoNotEqualTo(String value) {
			addCriterion("SIGNINFO <>", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoGreaterThan(String value) {
			addCriterion("SIGNINFO >", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoGreaterThanOrEqualTo(String value) {
			addCriterion("SIGNINFO >=", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoLessThan(String value) {
			addCriterion("SIGNINFO <", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoLessThanOrEqualTo(String value) {
			addCriterion("SIGNINFO <=", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoLike(String value) {
			addCriterion("SIGNINFO like", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoNotLike(String value) {
			addCriterion("SIGNINFO not like", value, "signinfo");
			return this;
		}

		public Criteria andSigninfoIn(List<String> values) {
			addCriterion("SIGNINFO in", values, "signinfo");
			return this;
		}

		public Criteria andSigninfoNotIn(List<String> values) {
			addCriterion("SIGNINFO not in", values, "signinfo");
			return this;
		}

		public Criteria andSigninfoBetween(String value1, String value2) {
			addCriterion("SIGNINFO between", value1, value2, "signinfo");
			return this;
		}

		public Criteria andSigninfoNotBetween(String value1, String value2) {
			addCriterion("SIGNINFO not between", value1, value2, "signinfo");
			return this;
		}
	}
}