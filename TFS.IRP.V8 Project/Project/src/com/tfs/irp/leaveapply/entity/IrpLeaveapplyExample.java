package com.tfs.irp.leaveapply.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpLeaveapplyExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public IrpLeaveapplyExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	protected IrpLeaveapplyExample(IrpLeaveapplyExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_LEAVEAPPLY
	 * @ibatorgenerated  Tue Sep 27 14:29:49 CST 2016
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

		public Criteria andLeaveapplyidIsNull() {
			addCriterion("LEAVEAPPLYID is null");
			return this;
		}

		public Criteria andLeaveapplyidIsNotNull() {
			addCriterion("LEAVEAPPLYID is not null");
			return this;
		}

		public Criteria andLeaveapplyidEqualTo(Long value) {
			addCriterion("LEAVEAPPLYID =", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidNotEqualTo(Long value) {
			addCriterion("LEAVEAPPLYID <>", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidGreaterThan(Long value) {
			addCriterion("LEAVEAPPLYID >", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidGreaterThanOrEqualTo(Long value) {
			addCriterion("LEAVEAPPLYID >=", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidLessThan(Long value) {
			addCriterion("LEAVEAPPLYID <", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidLessThanOrEqualTo(Long value) {
			addCriterion("LEAVEAPPLYID <=", value, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidIn(List<Long> values) {
			addCriterion("LEAVEAPPLYID in", values, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidNotIn(List<Long> values) {
			addCriterion("LEAVEAPPLYID not in", values, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidBetween(Long value1, Long value2) {
			addCriterion("LEAVEAPPLYID between", value1, value2, "leaveapplyid");
			return this;
		}

		public Criteria andLeaveapplyidNotBetween(Long value1, Long value2) {
			addCriterion("LEAVEAPPLYID not between", value1, value2,
					"leaveapplyid");
			return this;
		}

		public Criteria andApplyreasonIsNull() {
			addCriterion("APPLYREASON is null");
			return this;
		}

		public Criteria andApplyreasonIsNotNull() {
			addCriterion("APPLYREASON is not null");
			return this;
		}

		public Criteria andApplyreasonEqualTo(String value) {
			addCriterion("APPLYREASON =", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonNotEqualTo(String value) {
			addCriterion("APPLYREASON <>", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonGreaterThan(String value) {
			addCriterion("APPLYREASON >", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonGreaterThanOrEqualTo(String value) {
			addCriterion("APPLYREASON >=", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonLessThan(String value) {
			addCriterion("APPLYREASON <", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonLessThanOrEqualTo(String value) {
			addCriterion("APPLYREASON <=", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonLike(String value) {
			addCriterion("APPLYREASON like", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonNotLike(String value) {
			addCriterion("APPLYREASON not like", value, "applyreason");
			return this;
		}

		public Criteria andApplyreasonIn(List<String> values) {
			addCriterion("APPLYREASON in", values, "applyreason");
			return this;
		}

		public Criteria andApplyreasonNotIn(List<String> values) {
			addCriterion("APPLYREASON not in", values, "applyreason");
			return this;
		}

		public Criteria andApplyreasonBetween(String value1, String value2) {
			addCriterion("APPLYREASON between", value1, value2, "applyreason");
			return this;
		}

		public Criteria andApplyreasonNotBetween(String value1, String value2) {
			addCriterion("APPLYREASON not between", value1, value2,
					"applyreason");
			return this;
		}

		public Criteria andApplytypeidIsNull() {
			addCriterion("APPLYTYPEID is null");
			return this;
		}

		public Criteria andApplytypeidIsNotNull() {
			addCriterion("APPLYTYPEID is not null");
			return this;
		}

		public Criteria andApplytypeidEqualTo(Long value) {
			addCriterion("APPLYTYPEID =", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidNotEqualTo(Long value) {
			addCriterion("APPLYTYPEID <>", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidGreaterThan(Long value) {
			addCriterion("APPLYTYPEID >", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidGreaterThanOrEqualTo(Long value) {
			addCriterion("APPLYTYPEID >=", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidLessThan(Long value) {
			addCriterion("APPLYTYPEID <", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidLessThanOrEqualTo(Long value) {
			addCriterion("APPLYTYPEID <=", value, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidIn(List<Long> values) {
			addCriterion("APPLYTYPEID in", values, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidNotIn(List<Long> values) {
			addCriterion("APPLYTYPEID not in", values, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidBetween(Long value1, Long value2) {
			addCriterion("APPLYTYPEID between", value1, value2, "applytypeid");
			return this;
		}

		public Criteria andApplytypeidNotBetween(Long value1, Long value2) {
			addCriterion("APPLYTYPEID not between", value1, value2,
					"applytypeid");
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

		public Criteria andEmergencyIsNull() {
			addCriterion("EMERGENCY is null");
			return this;
		}

		public Criteria andEmergencyIsNotNull() {
			addCriterion("EMERGENCY is not null");
			return this;
		}

		public Criteria andEmergencyEqualTo(Integer value) {
			addCriterion("EMERGENCY =", value, "emergency");
			return this;
		}

		public Criteria andEmergencyNotEqualTo(Integer value) {
			addCriterion("EMERGENCY <>", value, "emergency");
			return this;
		}

		public Criteria andEmergencyGreaterThan(Integer value) {
			addCriterion("EMERGENCY >", value, "emergency");
			return this;
		}

		public Criteria andEmergencyGreaterThanOrEqualTo(Integer value) {
			addCriterion("EMERGENCY >=", value, "emergency");
			return this;
		}

		public Criteria andEmergencyLessThan(Integer value) {
			addCriterion("EMERGENCY <", value, "emergency");
			return this;
		}

		public Criteria andEmergencyLessThanOrEqualTo(Integer value) {
			addCriterion("EMERGENCY <=", value, "emergency");
			return this;
		}

		public Criteria andEmergencyIn(List<Integer> values) {
			addCriterion("EMERGENCY in", values, "emergency");
			return this;
		}

		public Criteria andEmergencyNotIn(List<Integer> values) {
			addCriterion("EMERGENCY not in", values, "emergency");
			return this;
		}

		public Criteria andEmergencyBetween(Integer value1, Integer value2) {
			addCriterion("EMERGENCY between", value1, value2, "emergency");
			return this;
		}

		public Criteria andEmergencyNotBetween(Integer value1, Integer value2) {
			addCriterion("EMERGENCY not between", value1, value2, "emergency");
			return this;
		}

		public Criteria andOvertimecontentIsNull() {
			addCriterion("OVERTIMECONTENT is null");
			return this;
		}

		public Criteria andOvertimecontentIsNotNull() {
			addCriterion("OVERTIMECONTENT is not null");
			return this;
		}

		public Criteria andOvertimecontentEqualTo(String value) {
			addCriterion("OVERTIMECONTENT =", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentNotEqualTo(String value) {
			addCriterion("OVERTIMECONTENT <>", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentGreaterThan(String value) {
			addCriterion("OVERTIMECONTENT >", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentGreaterThanOrEqualTo(String value) {
			addCriterion("OVERTIMECONTENT >=", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentLessThan(String value) {
			addCriterion("OVERTIMECONTENT <", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentLessThanOrEqualTo(String value) {
			addCriterion("OVERTIMECONTENT <=", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentLike(String value) {
			addCriterion("OVERTIMECONTENT like", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentNotLike(String value) {
			addCriterion("OVERTIMECONTENT not like", value, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentIn(List<String> values) {
			addCriterion("OVERTIMECONTENT in", values, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentNotIn(List<String> values) {
			addCriterion("OVERTIMECONTENT not in", values, "overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentBetween(String value1, String value2) {
			addCriterion("OVERTIMECONTENT between", value1, value2,
					"overtimecontent");
			return this;
		}

		public Criteria andOvertimecontentNotBetween(String value1,
				String value2) {
			addCriterion("OVERTIMECONTENT not between", value1, value2,
					"overtimecontent");
			return this;
		}

		public Criteria andOvertimeadressIsNull() {
			addCriterion("OVERTIMEADRESS is null");
			return this;
		}

		public Criteria andOvertimeadressIsNotNull() {
			addCriterion("OVERTIMEADRESS is not null");
			return this;
		}

		public Criteria andOvertimeadressEqualTo(String value) {
			addCriterion("OVERTIMEADRESS =", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressNotEqualTo(String value) {
			addCriterion("OVERTIMEADRESS <>", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressGreaterThan(String value) {
			addCriterion("OVERTIMEADRESS >", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressGreaterThanOrEqualTo(String value) {
			addCriterion("OVERTIMEADRESS >=", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressLessThan(String value) {
			addCriterion("OVERTIMEADRESS <", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressLessThanOrEqualTo(String value) {
			addCriterion("OVERTIMEADRESS <=", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressLike(String value) {
			addCriterion("OVERTIMEADRESS like", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressNotLike(String value) {
			addCriterion("OVERTIMEADRESS not like", value, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressIn(List<String> values) {
			addCriterion("OVERTIMEADRESS in", values, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressNotIn(List<String> values) {
			addCriterion("OVERTIMEADRESS not in", values, "overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressBetween(String value1, String value2) {
			addCriterion("OVERTIMEADRESS between", value1, value2,
					"overtimeadress");
			return this;
		}

		public Criteria andOvertimeadressNotBetween(String value1, String value2) {
			addCriterion("OVERTIMEADRESS not between", value1, value2,
					"overtimeadress");
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

		public Criteria andLeavemarkingIsNull() {
			addCriterion("LEAVEMARKING is null");
			return this;
		}

		public Criteria andLeavemarkingIsNotNull() {
			addCriterion("LEAVEMARKING is not null");
			return this;
		}

		public Criteria andLeavemarkingEqualTo(Integer value) {
			addCriterion("LEAVEMARKING =", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingNotEqualTo(Integer value) {
			addCriterion("LEAVEMARKING <>", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingGreaterThan(Integer value) {
			addCriterion("LEAVEMARKING >", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingGreaterThanOrEqualTo(Integer value) {
			addCriterion("LEAVEMARKING >=", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingLessThan(Integer value) {
			addCriterion("LEAVEMARKING <", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingLessThanOrEqualTo(Integer value) {
			addCriterion("LEAVEMARKING <=", value, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingIn(List<Integer> values) {
			addCriterion("LEAVEMARKING in", values, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingNotIn(List<Integer> values) {
			addCriterion("LEAVEMARKING not in", values, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingBetween(Integer value1, Integer value2) {
			addCriterion("LEAVEMARKING between", value1, value2, "leavemarking");
			return this;
		}

		public Criteria andLeavemarkingNotBetween(Integer value1, Integer value2) {
			addCriterion("LEAVEMARKING not between", value1, value2,
					"leavemarking");
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

		public Criteria andAuditinfoIsNull() {
			addCriterion("AUDITINFO is null");
			return this;
		}

		public Criteria andAuditinfoIsNotNull() {
			addCriterion("AUDITINFO is not null");
			return this;
		}

		public Criteria andAuditinfoEqualTo(String value) {
			addCriterion("AUDITINFO =", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoNotEqualTo(String value) {
			addCriterion("AUDITINFO <>", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoGreaterThan(String value) {
			addCriterion("AUDITINFO >", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoGreaterThanOrEqualTo(String value) {
			addCriterion("AUDITINFO >=", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoLessThan(String value) {
			addCriterion("AUDITINFO <", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoLessThanOrEqualTo(String value) {
			addCriterion("AUDITINFO <=", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoLike(String value) {
			addCriterion("AUDITINFO like", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoNotLike(String value) {
			addCriterion("AUDITINFO not like", value, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoIn(List<String> values) {
			addCriterion("AUDITINFO in", values, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoNotIn(List<String> values) {
			addCriterion("AUDITINFO not in", values, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoBetween(String value1, String value2) {
			addCriterion("AUDITINFO between", value1, value2, "auditinfo");
			return this;
		}

		public Criteria andAuditinfoNotBetween(String value1, String value2) {
			addCriterion("AUDITINFO not between", value1, value2, "auditinfo");
			return this;
		}

		public Criteria andLeavedaysIsNull() {
			addCriterion("LEAVEDAYS is null");
			return this;
		}

		public Criteria andLeavedaysIsNotNull() {
			addCriterion("LEAVEDAYS is not null");
			return this;
		}

		public Criteria andLeavedaysEqualTo(Double value) {
			addCriterion("LEAVEDAYS =", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysNotEqualTo(Double value) {
			addCriterion("LEAVEDAYS <>", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysGreaterThan(Double value) {
			addCriterion("LEAVEDAYS >", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysGreaterThanOrEqualTo(Double value) {
			addCriterion("LEAVEDAYS >=", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysLessThan(Double value) {
			addCriterion("LEAVEDAYS <", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysLessThanOrEqualTo(Double value) {
			addCriterion("LEAVEDAYS <=", value, "leavedays");
			return this;
		}

		public Criteria andLeavedaysIn(List<Double> values) {
			addCriterion("LEAVEDAYS in", values, "leavedays");
			return this;
		}

		public Criteria andLeavedaysNotIn(List<Double> values) {
			addCriterion("LEAVEDAYS not in", values, "leavedays");
			return this;
		}

		public Criteria andLeavedaysBetween(Double value1, Double value2) {
			addCriterion("LEAVEDAYS between", value1, value2, "leavedays");
			return this;
		}

		public Criteria andLeavedaysNotBetween(Double value1, Double value2) {
			addCriterion("LEAVEDAYS not between", value1, value2, "leavedays");
			return this;
		}

		public Criteria andCheckmoreIsNull() {
			addCriterion("CHECKMORE is null");
			return this;
		}

		public Criteria andCheckmoreIsNotNull() {
			addCriterion("CHECKMORE is not null");
			return this;
		}

		public Criteria andCheckmoreEqualTo(Integer value) {
			addCriterion("CHECKMORE =", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreNotEqualTo(Integer value) {
			addCriterion("CHECKMORE <>", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreGreaterThan(Integer value) {
			addCriterion("CHECKMORE >", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("CHECKMORE >=", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreLessThan(Integer value) {
			addCriterion("CHECKMORE <", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreLessThanOrEqualTo(Integer value) {
			addCriterion("CHECKMORE <=", value, "checkmore");
			return this;
		}

		public Criteria andCheckmoreIn(List<Integer> values) {
			addCriterion("CHECKMORE in", values, "checkmore");
			return this;
		}

		public Criteria andCheckmoreNotIn(List<Integer> values) {
			addCriterion("CHECKMORE not in", values, "checkmore");
			return this;
		}

		public Criteria andCheckmoreBetween(Integer value1, Integer value2) {
			addCriterion("CHECKMORE between", value1, value2, "checkmore");
			return this;
		}

		public Criteria andCheckmoreNotBetween(Integer value1, Integer value2) {
			addCriterion("CHECKMORE not between", value1, value2, "checkmore");
			return this;
		}

		public Criteria andAdviseIsNull() {
			addCriterion("ADVISE is null");
			return this;
		}

		public Criteria andAdviseIsNotNull() {
			addCriterion("ADVISE is not null");
			return this;
		}

		public Criteria andAdviseEqualTo(String value) {
			addCriterion("ADVISE =", value, "advise");
			return this;
		}

		public Criteria andAdviseNotEqualTo(String value) {
			addCriterion("ADVISE <>", value, "advise");
			return this;
		}

		public Criteria andAdviseGreaterThan(String value) {
			addCriterion("ADVISE >", value, "advise");
			return this;
		}

		public Criteria andAdviseGreaterThanOrEqualTo(String value) {
			addCriterion("ADVISE >=", value, "advise");
			return this;
		}

		public Criteria andAdviseLessThan(String value) {
			addCriterion("ADVISE <", value, "advise");
			return this;
		}

		public Criteria andAdviseLessThanOrEqualTo(String value) {
			addCriterion("ADVISE <=", value, "advise");
			return this;
		}

		public Criteria andAdviseLike(String value) {
			addCriterion("ADVISE like", value, "advise");
			return this;
		}

		public Criteria andAdviseNotLike(String value) {
			addCriterion("ADVISE not like", value, "advise");
			return this;
		}

		public Criteria andAdviseIn(List<String> values) {
			addCriterion("ADVISE in", values, "advise");
			return this;
		}

		public Criteria andAdviseNotIn(List<String> values) {
			addCriterion("ADVISE not in", values, "advise");
			return this;
		}

		public Criteria andAdviseBetween(String value1, String value2) {
			addCriterion("ADVISE between", value1, value2, "advise");
			return this;
		}

		public Criteria andAdviseNotBetween(String value1, String value2) {
			addCriterion("ADVISE not between", value1, value2, "advise");
			return this;
		}

		public Criteria andContentIsNull() {
			addCriterion("CONTENT is null");
			return this;
		}

		public Criteria andContentIsNotNull() {
			addCriterion("CONTENT is not null");
			return this;
		}

		public Criteria andContentEqualTo(String value) {
			addCriterion("CONTENT =", value, "content");
			return this;
		}

		public Criteria andContentNotEqualTo(String value) {
			addCriterion("CONTENT <>", value, "content");
			return this;
		}

		public Criteria andContentGreaterThan(String value) {
			addCriterion("CONTENT >", value, "content");
			return this;
		}

		public Criteria andContentGreaterThanOrEqualTo(String value) {
			addCriterion("CONTENT >=", value, "content");
			return this;
		}

		public Criteria andContentLessThan(String value) {
			addCriterion("CONTENT <", value, "content");
			return this;
		}

		public Criteria andContentLessThanOrEqualTo(String value) {
			addCriterion("CONTENT <=", value, "content");
			return this;
		}

		public Criteria andContentLike(String value) {
			addCriterion("CONTENT like", value, "content");
			return this;
		}

		public Criteria andContentNotLike(String value) {
			addCriterion("CONTENT not like", value, "content");
			return this;
		}

		public Criteria andContentIn(List<String> values) {
			addCriterion("CONTENT in", values, "content");
			return this;
		}

		public Criteria andContentNotIn(List<String> values) {
			addCriterion("CONTENT not in", values, "content");
			return this;
		}

		public Criteria andContentBetween(String value1, String value2) {
			addCriterion("CONTENT between", value1, value2, "content");
			return this;
		}

		public Criteria andContentNotBetween(String value1, String value2) {
			addCriterion("CONTENT not between", value1, value2, "content");
			return this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("ADDRESS is null");
			return this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("ADDRESS is not null");
			return this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("ADDRESS =", value, "address");
			return this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("ADDRESS <>", value, "address");
			return this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("ADDRESS >", value, "address");
			return this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("ADDRESS >=", value, "address");
			return this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("ADDRESS <", value, "address");
			return this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("ADDRESS <=", value, "address");
			return this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("ADDRESS like", value, "address");
			return this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("ADDRESS not like", value, "address");
			return this;
		}

		public Criteria andAddressIn(List<String> values) {
			addCriterion("ADDRESS in", values, "address");
			return this;
		}

		public Criteria andAddressNotIn(List<String> values) {
			addCriterion("ADDRESS not in", values, "address");
			return this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("ADDRESS between", value1, value2, "address");
			return this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("ADDRESS not between", value1, value2, "address");
			return this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("TITLE is null");
			return this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("TITLE is not null");
			return this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("TITLE =", value, "title");
			return this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("TITLE <>", value, "title");
			return this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("TITLE >", value, "title");
			return this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("TITLE >=", value, "title");
			return this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("TITLE <", value, "title");
			return this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("TITLE <=", value, "title");
			return this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("TITLE like", value, "title");
			return this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("TITLE not like", value, "title");
			return this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("TITLE in", values, "title");
			return this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("TITLE not in", values, "title");
			return this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("TITLE between", value1, value2, "title");
			return this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("TITLE not between", value1, value2, "title");
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

		public Criteria andWarnidEqualTo(Integer value) {
			addCriterion("WARNID =", value, "warnid");
			return this;
		}

		public Criteria andWarnidNotEqualTo(Integer value) {
			addCriterion("WARNID <>", value, "warnid");
			return this;
		}

		public Criteria andWarnidGreaterThan(Integer value) {
			addCriterion("WARNID >", value, "warnid");
			return this;
		}

		public Criteria andWarnidGreaterThanOrEqualTo(Integer value) {
			addCriterion("WARNID >=", value, "warnid");
			return this;
		}

		public Criteria andWarnidLessThan(Integer value) {
			addCriterion("WARNID <", value, "warnid");
			return this;
		}

		public Criteria andWarnidLessThanOrEqualTo(Integer value) {
			addCriterion("WARNID <=", value, "warnid");
			return this;
		}

		public Criteria andWarnidIn(List<Integer> values) {
			addCriterion("WARNID in", values, "warnid");
			return this;
		}

		public Criteria andWarnidNotIn(List<Integer> values) {
			addCriterion("WARNID not in", values, "warnid");
			return this;
		}

		public Criteria andWarnidBetween(Integer value1, Integer value2) {
			addCriterion("WARNID between", value1, value2, "warnid");
			return this;
		}

		public Criteria andWarnidNotBetween(Integer value1, Integer value2) {
			addCriterion("WARNID not between", value1, value2, "warnid");
			return this;
		}
	}
}