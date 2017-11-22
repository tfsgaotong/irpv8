package com.tfs.irp.topic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IrpTopicExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public IrpTopicExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	protected IrpTopicExample(IrpTopicExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
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

		public Criteria andTopicidIsNull() {
			addCriterion("TOPICID is null");
			return this;
		}

		public Criteria andTopicidIsNotNull() {
			addCriterion("TOPICID is not null");
			return this;
		}

		public Criteria andTopicidEqualTo(Long value) {
			addCriterion("TOPICID =", value, "topicid");
			return this;
		}

		public Criteria andTopicidNotEqualTo(Long value) {
			addCriterion("TOPICID <>", value, "topicid");
			return this;
		}

		public Criteria andTopicidGreaterThan(Long value) {
			addCriterion("TOPICID >", value, "topicid");
			return this;
		}

		public Criteria andTopicidGreaterThanOrEqualTo(Long value) {
			addCriterion("TOPICID >=", value, "topicid");
			return this;
		}

		public Criteria andTopicidLessThan(Long value) {
			addCriterion("TOPICID <", value, "topicid");
			return this;
		}

		public Criteria andTopicidLessThanOrEqualTo(Long value) {
			addCriterion("TOPICID <=", value, "topicid");
			return this;
		}

		public Criteria andTopicidIn(List<Long> values) {
			addCriterion("TOPICID in", values, "topicid");
			return this;
		}

		public Criteria andTopicidNotIn(List<Long> values) {
			addCriterion("TOPICID not in", values, "topicid");
			return this;
		}

		public Criteria andTopicidBetween(Long value1, Long value2) {
			addCriterion("TOPICID between", value1, value2, "topicid");
			return this;
		}

		public Criteria andTopicidNotBetween(Long value1, Long value2) {
			addCriterion("TOPICID not between", value1, value2, "topicid");
			return this;
		}

		public Criteria andTopicnameIsNull() {
			addCriterion("TOPICNAME is null");
			return this;
		}

		public Criteria andTopicnameIsNotNull() {
			addCriterion("TOPICNAME is not null");
			return this;
		}

		public Criteria andTopicnameEqualTo(String value) {
			addCriterion("TOPICNAME =", value, "topicname");
			return this;
		}

		public Criteria andTopicnameNotEqualTo(String value) {
			addCriterion("TOPICNAME <>", value, "topicname");
			return this;
		}

		public Criteria andTopicnameGreaterThan(String value) {
			addCriterion("TOPICNAME >", value, "topicname");
			return this;
		}

		public Criteria andTopicnameGreaterThanOrEqualTo(String value) {
			addCriterion("TOPICNAME >=", value, "topicname");
			return this;
		}

		public Criteria andTopicnameLessThan(String value) {
			addCriterion("TOPICNAME <", value, "topicname");
			return this;
		}

		public Criteria andTopicnameLessThanOrEqualTo(String value) {
			addCriterion("TOPICNAME <=", value, "topicname");
			return this;
		}

		public Criteria andTopicnameLike(String value) {
			addCriterion("TOPICNAME like", value, "topicname");
			return this;
		}

		public Criteria andTopicnameNotLike(String value) {
			addCriterion("TOPICNAME not like", value, "topicname");
			return this;
		}

		public Criteria andTopicnameIn(List<String> values) {
			addCriterion("TOPICNAME in", values, "topicname");
			return this;
		}

		public Criteria andTopicnameNotIn(List<String> values) {
			addCriterion("TOPICNAME not in", values, "topicname");
			return this;
		}

		public Criteria andTopicnameBetween(String value1, String value2) {
			addCriterion("TOPICNAME between", value1, value2, "topicname");
			return this;
		}

		public Criteria andTopicnameNotBetween(String value1, String value2) {
			addCriterion("TOPICNAME not between", value1, value2, "topicname");
			return this;
		}

		public Criteria andTopicdescIsNull() {
			addCriterion("TOPICDESC is null");
			return this;
		}

		public Criteria andTopicdescIsNotNull() {
			addCriterion("TOPICDESC is not null");
			return this;
		}

		public Criteria andTopicdescEqualTo(String value) {
			addCriterion("TOPICDESC =", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescNotEqualTo(String value) {
			addCriterion("TOPICDESC <>", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescGreaterThan(String value) {
			addCriterion("TOPICDESC >", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescGreaterThanOrEqualTo(String value) {
			addCriterion("TOPICDESC >=", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescLessThan(String value) {
			addCriterion("TOPICDESC <", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescLessThanOrEqualTo(String value) {
			addCriterion("TOPICDESC <=", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescLike(String value) {
			addCriterion("TOPICDESC like", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescNotLike(String value) {
			addCriterion("TOPICDESC not like", value, "topicdesc");
			return this;
		}

		public Criteria andTopicdescIn(List<String> values) {
			addCriterion("TOPICDESC in", values, "topicdesc");
			return this;
		}

		public Criteria andTopicdescNotIn(List<String> values) {
			addCriterion("TOPICDESC not in", values, "topicdesc");
			return this;
		}

		public Criteria andTopicdescBetween(String value1, String value2) {
			addCriterion("TOPICDESC between", value1, value2, "topicdesc");
			return this;
		}

		public Criteria andTopicdescNotBetween(String value1, String value2) {
			addCriterion("TOPICDESC not between", value1, value2, "topicdesc");
			return this;
		}

		public Criteria andTopicclicknumIsNull() {
			addCriterion("TOPICCLICKNUM is null");
			return this;
		}

		public Criteria andTopicclicknumIsNotNull() {
			addCriterion("TOPICCLICKNUM is not null");
			return this;
		}

		public Criteria andTopicclicknumEqualTo(Long value) {
			addCriterion("TOPICCLICKNUM =", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumNotEqualTo(Long value) {
			addCriterion("TOPICCLICKNUM <>", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumGreaterThan(Long value) {
			addCriterion("TOPICCLICKNUM >", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumGreaterThanOrEqualTo(Long value) {
			addCriterion("TOPICCLICKNUM >=", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumLessThan(Long value) {
			addCriterion("TOPICCLICKNUM <", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumLessThanOrEqualTo(Long value) {
			addCriterion("TOPICCLICKNUM <=", value, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumIn(List<Long> values) {
			addCriterion("TOPICCLICKNUM in", values, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumNotIn(List<Long> values) {
			addCriterion("TOPICCLICKNUM not in", values, "topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumBetween(Long value1, Long value2) {
			addCriterion("TOPICCLICKNUM between", value1, value2,
					"topicclicknum");
			return this;
		}

		public Criteria andTopicclicknumNotBetween(Long value1, Long value2) {
			addCriterion("TOPICCLICKNUM not between", value1, value2,
					"topicclicknum");
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

		public Criteria andExpandfieldIsNull() {
			addCriterion("EXPANDFIELD is null");
			return this;
		}

		public Criteria andExpandfieldIsNotNull() {
			addCriterion("EXPANDFIELD is not null");
			return this;
		}

		public Criteria andExpandfieldEqualTo(String value) {
			addCriterion("EXPANDFIELD =", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldNotEqualTo(String value) {
			addCriterion("EXPANDFIELD <>", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldGreaterThan(String value) {
			addCriterion("EXPANDFIELD >", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldGreaterThanOrEqualTo(String value) {
			addCriterion("EXPANDFIELD >=", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldLessThan(String value) {
			addCriterion("EXPANDFIELD <", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldLessThanOrEqualTo(String value) {
			addCriterion("EXPANDFIELD <=", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldLike(String value) {
			addCriterion("EXPANDFIELD like", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldNotLike(String value) {
			addCriterion("EXPANDFIELD not like", value, "expandfield");
			return this;
		}

		public Criteria andExpandfieldIn(List<String> values) {
			addCriterion("EXPANDFIELD in", values, "expandfield");
			return this;
		}

		public Criteria andExpandfieldNotIn(List<String> values) {
			addCriterion("EXPANDFIELD not in", values, "expandfield");
			return this;
		}

		public Criteria andExpandfieldBetween(String value1, String value2) {
			addCriterion("EXPANDFIELD between", value1, value2, "expandfield");
			return this;
		}

		public Criteria andExpandfieldNotBetween(String value1, String value2) {
			addCriterion("EXPANDFIELD not between", value1, value2,
					"expandfield");
			return this;
		}

		public Criteria andTopictypeIsNull() {
			addCriterion("TOPICTYPE is null");
			return this;
		}

		public Criteria andTopictypeIsNotNull() {
			addCriterion("TOPICTYPE is not null");
			return this;
		}

		public Criteria andTopictypeEqualTo(Integer value) {
			addCriterion("TOPICTYPE =", value, "topictype");
			return this;
		}

		public Criteria andTopictypeNotEqualTo(Integer value) {
			addCriterion("TOPICTYPE <>", value, "topictype");
			return this;
		}

		public Criteria andTopictypeGreaterThan(Integer value) {
			addCriterion("TOPICTYPE >", value, "topictype");
			return this;
		}

		public Criteria andTopictypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("TOPICTYPE >=", value, "topictype");
			return this;
		}

		public Criteria andTopictypeLessThan(Integer value) {
			addCriterion("TOPICTYPE <", value, "topictype");
			return this;
		}

		public Criteria andTopictypeLessThanOrEqualTo(Integer value) {
			addCriterion("TOPICTYPE <=", value, "topictype");
			return this;
		}

		public Criteria andTopictypeIn(List<Integer> values) {
			addCriterion("TOPICTYPE in", values, "topictype");
			return this;
		}

		public Criteria andTopictypeNotIn(List<Integer> values) {
			addCriterion("TOPICTYPE not in", values, "topictype");
			return this;
		}

		public Criteria andTopictypeBetween(Integer value1, Integer value2) {
			addCriterion("TOPICTYPE between", value1, value2, "topictype");
			return this;
		}

		public Criteria andTopictypeNotBetween(Integer value1, Integer value2) {
			addCriterion("TOPICTYPE not between", value1, value2, "topictype");
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
	}
}