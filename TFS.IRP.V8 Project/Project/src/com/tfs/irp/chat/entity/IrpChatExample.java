package com.tfs.irp.chat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrpChatExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public IrpChatExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    protected IrpChatExample(IrpChatExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_chat
     *
     * @ibatorgenerated Fri Aug 01 11:06:43 CST 2014
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andChatidIsNull() {
            addCriterion("CHATID is null");
            return this;
        }

        public Criteria andChatidIsNotNull() {
            addCriterion("CHATID is not null");
            return this;
        }

        public Criteria andChatidEqualTo(Long value) {
            addCriterion("CHATID =", value, "chatid");
            return this;
        }

        public Criteria andChatidNotEqualTo(Long value) {
            addCriterion("CHATID <>", value, "chatid");
            return this;
        }

        public Criteria andChatidGreaterThan(Long value) {
            addCriterion("CHATID >", value, "chatid");
            return this;
        }

        public Criteria andChatidGreaterThanOrEqualTo(Long value) {
            addCriterion("CHATID >=", value, "chatid");
            return this;
        }

        public Criteria andChatidLessThan(Long value) {
            addCriterion("CHATID <", value, "chatid");
            return this;
        }

        public Criteria andChatidLessThanOrEqualTo(Long value) {
            addCriterion("CHATID <=", value, "chatid");
            return this;
        }

        public Criteria andChatidIn(List<Long> values) {
            addCriterion("CHATID in", values, "chatid");
            return this;
        }

        public Criteria andChatidNotIn(List<Long> values) {
            addCriterion("CHATID not in", values, "chatid");
            return this;
        }

        public Criteria andChatidBetween(Long value1, Long value2) {
            addCriterion("CHATID between", value1, value2, "chatid");
            return this;
        }

        public Criteria andChatidNotBetween(Long value1, Long value2) {
            addCriterion("CHATID not between", value1, value2, "chatid");
            return this;
        }

        public Criteria andReceiveruseridIsNull() {
            addCriterion("RECEIVERUSERID is null");
            return this;
        }

        public Criteria andReceiveruseridIsNotNull() {
            addCriterion("RECEIVERUSERID is not null");
            return this;
        }

        public Criteria andReceiveruseridEqualTo(Long value) {
            addCriterion("RECEIVERUSERID =", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridNotEqualTo(Long value) {
            addCriterion("RECEIVERUSERID <>", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridGreaterThan(Long value) {
            addCriterion("RECEIVERUSERID >", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridGreaterThanOrEqualTo(Long value) {
            addCriterion("RECEIVERUSERID >=", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridLessThan(Long value) {
            addCriterion("RECEIVERUSERID <", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridLessThanOrEqualTo(Long value) {
            addCriterion("RECEIVERUSERID <=", value, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridIn(List<Long> values) {
            addCriterion("RECEIVERUSERID in", values, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridNotIn(List<Long> values) {
            addCriterion("RECEIVERUSERID not in", values, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridBetween(Long value1, Long value2) {
            addCriterion("RECEIVERUSERID between", value1, value2, "receiveruserid");
            return this;
        }

        public Criteria andReceiveruseridNotBetween(Long value1, Long value2) {
            addCriterion("RECEIVERUSERID not between", value1, value2, "receiveruserid");
            return this;
        }

        public Criteria andSenderuseridIsNull() {
            addCriterion("SENDERUSERID is null");
            return this;
        }

        public Criteria andSenderuseridIsNotNull() {
            addCriterion("SENDERUSERID is not null");
            return this;
        }

        public Criteria andSenderuseridEqualTo(Long value) {
            addCriterion("SENDERUSERID =", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridNotEqualTo(Long value) {
            addCriterion("SENDERUSERID <>", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridGreaterThan(Long value) {
            addCriterion("SENDERUSERID >", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("SENDERUSERID >=", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridLessThan(Long value) {
            addCriterion("SENDERUSERID <", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridLessThanOrEqualTo(Long value) {
            addCriterion("SENDERUSERID <=", value, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridIn(List<Long> values) {
            addCriterion("SENDERUSERID in", values, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridNotIn(List<Long> values) {
            addCriterion("SENDERUSERID not in", values, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridBetween(Long value1, Long value2) {
            addCriterion("SENDERUSERID between", value1, value2, "senderuserid");
            return this;
        }

        public Criteria andSenderuseridNotBetween(Long value1, Long value2) {
            addCriterion("SENDERUSERID not between", value1, value2, "senderuserid");
            return this;
        }

        public Criteria andSenddateIsNull() {
            addCriterion("SENDDATE is null");
            return this;
        }

        public Criteria andSenddateIsNotNull() {
            addCriterion("SENDDATE is not null");
            return this;
        }

        public Criteria andSenddateEqualTo(Date value) {
            addCriterion("SENDDATE =", value, "senddate");
            return this;
        }

        public Criteria andSenddateNotEqualTo(Date value) {
            addCriterion("SENDDATE <>", value, "senddate");
            return this;
        }

        public Criteria andSenddateGreaterThan(Date value) {
            addCriterion("SENDDATE >", value, "senddate");
            return this;
        }

        public Criteria andSenddateGreaterThanOrEqualTo(Date value) {
            addCriterion("SENDDATE >=", value, "senddate");
            return this;
        }

        public Criteria andSenddateLessThan(Date value) {
            addCriterion("SENDDATE <", value, "senddate");
            return this;
        }

        public Criteria andSenddateLessThanOrEqualTo(Date value) {
            addCriterion("SENDDATE <=", value, "senddate");
            return this;
        }

        public Criteria andSenddateIn(List<Date> values) {
            addCriterion("SENDDATE in", values, "senddate");
            return this;
        }

        public Criteria andSenddateNotIn(List<Date> values) {
            addCriterion("SENDDATE not in", values, "senddate");
            return this;
        }

        public Criteria andSenddateBetween(Date value1, Date value2) {
            addCriterion("SENDDATE between", value1, value2, "senddate");
            return this;
        }

        public Criteria andSenddateNotBetween(Date value1, Date value2) {
            addCriterion("SENDDATE not between", value1, value2, "senddate");
            return this;
        }

        public Criteria andChatpointtypeIsNull() {
            addCriterion("CHATPOINTTYPE is null");
            return this;
        }

        public Criteria andChatpointtypeIsNotNull() {
            addCriterion("CHATPOINTTYPE is not null");
            return this;
        }

        public Criteria andChatpointtypeEqualTo(Long value) {
            addCriterion("CHATPOINTTYPE =", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeNotEqualTo(Long value) {
            addCriterion("CHATPOINTTYPE <>", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeGreaterThan(Long value) {
            addCriterion("CHATPOINTTYPE >", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("CHATPOINTTYPE >=", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeLessThan(Long value) {
            addCriterion("CHATPOINTTYPE <", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeLessThanOrEqualTo(Long value) {
            addCriterion("CHATPOINTTYPE <=", value, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeIn(List<Long> values) {
            addCriterion("CHATPOINTTYPE in", values, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeNotIn(List<Long> values) {
            addCriterion("CHATPOINTTYPE not in", values, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeBetween(Long value1, Long value2) {
            addCriterion("CHATPOINTTYPE between", value1, value2, "chatpointtype");
            return this;
        }

        public Criteria andChatpointtypeNotBetween(Long value1, Long value2) {
            addCriterion("CHATPOINTTYPE not between", value1, value2, "chatpointtype");
            return this;
        }

        public Criteria andChatcontentIsNull() {
            addCriterion("CHATCONTENT is null");
            return this;
        }

        public Criteria andChatcontentIsNotNull() {
            addCriterion("CHATCONTENT is not null");
            return this;
        }

        public Criteria andChatcontentEqualTo(String value) {
            addCriterion("CHATCONTENT =", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentNotEqualTo(String value) {
            addCriterion("CHATCONTENT <>", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentGreaterThan(String value) {
            addCriterion("CHATCONTENT >", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentGreaterThanOrEqualTo(String value) {
            addCriterion("CHATCONTENT >=", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentLessThan(String value) {
            addCriterion("CHATCONTENT <", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentLessThanOrEqualTo(String value) {
            addCriterion("CHATCONTENT <=", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentLike(String value) {
            addCriterion("CHATCONTENT like", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentNotLike(String value) {
            addCriterion("CHATCONTENT not like", value, "chatcontent");
            return this;
        }

        public Criteria andChatcontentIn(List<String> values) {
            addCriterion("CHATCONTENT in", values, "chatcontent");
            return this;
        }

        public Criteria andChatcontentNotIn(List<String> values) {
            addCriterion("CHATCONTENT not in", values, "chatcontent");
            return this;
        }

        public Criteria andChatcontentBetween(String value1, String value2) {
            addCriterion("CHATCONTENT between", value1, value2, "chatcontent");
            return this;
        }

        public Criteria andChatcontentNotBetween(String value1, String value2) {
            addCriterion("CHATCONTENT not between", value1, value2, "chatcontent");
            return this;
        }

        public Criteria andChatstatusIsNull() {
            addCriterion("CHATSTATUS is null");
            return this;
        }

        public Criteria andChatstatusIsNotNull() {
            addCriterion("CHATSTATUS is not null");
            return this;
        }

        public Criteria andChatstatusEqualTo(Integer value) {
            addCriterion("CHATSTATUS =", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusNotEqualTo(Integer value) {
            addCriterion("CHATSTATUS <>", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusGreaterThan(Integer value) {
            addCriterion("CHATSTATUS >", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHATSTATUS >=", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusLessThan(Integer value) {
            addCriterion("CHATSTATUS <", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusLessThanOrEqualTo(Integer value) {
            addCriterion("CHATSTATUS <=", value, "chatstatus");
            return this;
        }

        public Criteria andChatstatusIn(List<Integer> values) {
            addCriterion("CHATSTATUS in", values, "chatstatus");
            return this;
        }

        public Criteria andChatstatusNotIn(List<Integer> values) {
            addCriterion("CHATSTATUS not in", values, "chatstatus");
            return this;
        }

        public Criteria andChatstatusBetween(Integer value1, Integer value2) {
            addCriterion("CHATSTATUS between", value1, value2, "chatstatus");
            return this;
        }

        public Criteria andChatstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CHATSTATUS not between", value1, value2, "chatstatus");
            return this;
        }

        public Criteria andCharreadstatusIsNull() {
            addCriterion("CHARREADSTATUS is null");
            return this;
        }

        public Criteria andCharreadstatusIsNotNull() {
            addCriterion("CHARREADSTATUS is not null");
            return this;
        }

        public Criteria andCharreadstatusEqualTo(Integer value) {
            addCriterion("CHARREADSTATUS =", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusNotEqualTo(Integer value) {
            addCriterion("CHARREADSTATUS <>", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusGreaterThan(Integer value) {
            addCriterion("CHARREADSTATUS >", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHARREADSTATUS >=", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusLessThan(Integer value) {
            addCriterion("CHARREADSTATUS <", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusLessThanOrEqualTo(Integer value) {
            addCriterion("CHARREADSTATUS <=", value, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusIn(List<Integer> values) {
            addCriterion("CHARREADSTATUS in", values, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusNotIn(List<Integer> values) {
            addCriterion("CHARREADSTATUS not in", values, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusBetween(Integer value1, Integer value2) {
            addCriterion("CHARREADSTATUS between", value1, value2, "charreadstatus");
            return this;
        }

        public Criteria andCharreadstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CHARREADSTATUS not between", value1, value2, "charreadstatus");
            return this;
        }

        public Criteria andUnreadsearchdateIsNull() {
            addCriterion("UNREADSEARCHDATE is null");
            return this;
        }

        public Criteria andUnreadsearchdateIsNotNull() {
            addCriterion("UNREADSEARCHDATE is not null");
            return this;
        }

        public Criteria andUnreadsearchdateEqualTo(Date value) {
            addCriterion("UNREADSEARCHDATE =", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateNotEqualTo(Date value) {
            addCriterion("UNREADSEARCHDATE <>", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateGreaterThan(Date value) {
            addCriterion("UNREADSEARCHDATE >", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateGreaterThanOrEqualTo(Date value) {
            addCriterion("UNREADSEARCHDATE >=", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateLessThan(Date value) {
            addCriterion("UNREADSEARCHDATE <", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateLessThanOrEqualTo(Date value) {
            addCriterion("UNREADSEARCHDATE <=", value, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateIn(List<Date> values) {
            addCriterion("UNREADSEARCHDATE in", values, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateNotIn(List<Date> values) {
            addCriterion("UNREADSEARCHDATE not in", values, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateBetween(Date value1, Date value2) {
            addCriterion("UNREADSEARCHDATE between", value1, value2, "unreadsearchdate");
            return this;
        }

        public Criteria andUnreadsearchdateNotBetween(Date value1, Date value2) {
            addCriterion("UNREADSEARCHDATE not between", value1, value2, "unreadsearchdate");
            return this;
        }

        public Criteria andSenderboolrecordIsNull() {
            addCriterion("SENDERBOOLRECORD is null");
            return this;
        }

        public Criteria andSenderboolrecordIsNotNull() {
            addCriterion("SENDERBOOLRECORD is not null");
            return this;
        }

        public Criteria andSenderboolrecordEqualTo(Integer value) {
            addCriterion("SENDERBOOLRECORD =", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordNotEqualTo(Integer value) {
            addCriterion("SENDERBOOLRECORD <>", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordGreaterThan(Integer value) {
            addCriterion("SENDERBOOLRECORD >", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("SENDERBOOLRECORD >=", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordLessThan(Integer value) {
            addCriterion("SENDERBOOLRECORD <", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordLessThanOrEqualTo(Integer value) {
            addCriterion("SENDERBOOLRECORD <=", value, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordIn(List<Integer> values) {
            addCriterion("SENDERBOOLRECORD in", values, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordNotIn(List<Integer> values) {
            addCriterion("SENDERBOOLRECORD not in", values, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordBetween(Integer value1, Integer value2) {
            addCriterion("SENDERBOOLRECORD between", value1, value2, "senderboolrecord");
            return this;
        }

        public Criteria andSenderboolrecordNotBetween(Integer value1, Integer value2) {
            addCriterion("SENDERBOOLRECORD not between", value1, value2, "senderboolrecord");
            return this;
        }

        public Criteria andChataccessoryIsNull() {
            addCriterion("CHATACCESSORY is null");
            return this;
        }

        public Criteria andChataccessoryIsNotNull() {
            addCriterion("CHATACCESSORY is not null");
            return this;
        }

        public Criteria andChataccessoryEqualTo(Integer value) {
            addCriterion("CHATACCESSORY =", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryNotEqualTo(Integer value) {
            addCriterion("CHATACCESSORY <>", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryGreaterThan(Integer value) {
            addCriterion("CHATACCESSORY >", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHATACCESSORY >=", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryLessThan(Integer value) {
            addCriterion("CHATACCESSORY <", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryLessThanOrEqualTo(Integer value) {
            addCriterion("CHATACCESSORY <=", value, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryIn(List<Integer> values) {
            addCriterion("CHATACCESSORY in", values, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryNotIn(List<Integer> values) {
            addCriterion("CHATACCESSORY not in", values, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryBetween(Integer value1, Integer value2) {
            addCriterion("CHATACCESSORY between", value1, value2, "chataccessory");
            return this;
        }

        public Criteria andChataccessoryNotBetween(Integer value1, Integer value2) {
            addCriterion("CHATACCESSORY not between", value1, value2, "chataccessory");
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

        public Criteria andExtendtwoEqualTo(Long value) {
            addCriterion("EXTENDTWO =", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoNotEqualTo(Long value) {
            addCriterion("EXTENDTWO <>", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoGreaterThan(Long value) {
            addCriterion("EXTENDTWO >", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoGreaterThanOrEqualTo(Long value) {
            addCriterion("EXTENDTWO >=", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoLessThan(Long value) {
            addCriterion("EXTENDTWO <", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoLessThanOrEqualTo(Long value) {
            addCriterion("EXTENDTWO <=", value, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoIn(List<Long> values) {
            addCriterion("EXTENDTWO in", values, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoNotIn(List<Long> values) {
            addCriterion("EXTENDTWO not in", values, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoBetween(Long value1, Long value2) {
            addCriterion("EXTENDTWO between", value1, value2, "extendtwo");
            return this;
        }

        public Criteria andExtendtwoNotBetween(Long value1, Long value2) {
            addCriterion("EXTENDTWO not between", value1, value2, "extendtwo");
            return this;
        }
    }
}