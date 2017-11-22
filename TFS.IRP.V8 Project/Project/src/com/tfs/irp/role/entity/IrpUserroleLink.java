package com.tfs.irp.role.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpUserroleLink extends IrpBaseObj{

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.USERROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private Long userroleid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.ROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private Long roleid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.USERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private Long userid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.CRUSERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private Long cruserid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.CRTIME
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private Date crtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_USERROLE_LINK.CRUSER
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	private String cruser;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.USERROLEID
	 * @return  the value of IRP_USERROLE_LINK.USERROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public Long getUserroleid() {
		return userroleid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.USERROLEID
	 * @param userroleid  the value for IRP_USERROLE_LINK.USERROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.ROLEID
	 * @return  the value of IRP_USERROLE_LINK.ROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public Long getRoleid() {
		return roleid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.ROLEID
	 * @param roleid  the value for IRP_USERROLE_LINK.ROLEID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.USERID
	 * @return  the value of IRP_USERROLE_LINK.USERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.USERID
	 * @param userid  the value for IRP_USERROLE_LINK.USERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.CRUSERID
	 * @return  the value of IRP_USERROLE_LINK.CRUSERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.CRUSERID
	 * @param cruserid  the value for IRP_USERROLE_LINK.CRUSERID
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.CRTIME
	 * @return  the value of IRP_USERROLE_LINK.CRTIME
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.CRTIME
	 * @param crtime  the value for IRP_USERROLE_LINK.CRTIME
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_USERROLE_LINK.CRUSER
	 * @return  the value of IRP_USERROLE_LINK.CRUSER
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public String getCruser() {
		return cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_USERROLE_LINK.CRUSER
	 * @param cruser  the value for IRP_USERROLE_LINK.CRUSER
	 * @ibatorgenerated  Wed Mar 13 10:43:31 CST 2013
	 */
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}
	
	/** 对象表名 **/
    public final static String TABLE_NAME = "IRP_USERROLE_LINK";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "USERROLEID";
    
	@Override
	public Long getId() {
		return this.userroleid;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return ID_FIELD_NAME;
	}
}