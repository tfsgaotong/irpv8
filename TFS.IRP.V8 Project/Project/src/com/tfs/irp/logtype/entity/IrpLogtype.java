package com.tfs.irp.logtype.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpLogtype extends IrpBaseObj{

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_LOGTYPE.LOGTYPEID
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	private Integer logtypeid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_LOGTYPE.TYPENAME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	private String typename;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_LOGTYPE.TYPEDESC
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	private String typedesc;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_LOGTYPE.CRUSER
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	private String cruser;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_LOGTYPE.CRTIME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	private Date crtime;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_LOGTYPE.LOGTYPEID
	 * @return  the value of IRP_LOGTYPE.LOGTYPEID
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public Integer getLogtypeid() {
		return logtypeid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_LOGTYPE.LOGTYPEID
	 * @param logtypeid  the value for IRP_LOGTYPE.LOGTYPEID
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setLogtypeid(Integer logtypeid) {
		this.logtypeid = logtypeid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_LOGTYPE.TYPENAME
	 * @return  the value of IRP_LOGTYPE.TYPENAME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_LOGTYPE.TYPENAME
	 * @param typename  the value for IRP_LOGTYPE.TYPENAME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_LOGTYPE.TYPEDESC
	 * @return  the value of IRP_LOGTYPE.TYPEDESC
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public String getTypedesc() {
		return typedesc;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_LOGTYPE.TYPEDESC
	 * @param typedesc  the value for IRP_LOGTYPE.TYPEDESC
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_LOGTYPE.CRUSER
	 * @return  the value of IRP_LOGTYPE.CRUSER
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public String getCruser() {
		return cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_LOGTYPE.CRUSER
	 * @param cruser  the value for IRP_LOGTYPE.CRUSER
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_LOGTYPE.CRTIME
	 * @return  the value of IRP_LOGTYPE.CRTIME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_LOGTYPE.CRTIME
	 * @param crtime  the value for IRP_LOGTYPE.CRTIME
	 * @ibatorgenerated  Fri Mar 08 12:50:39 CST 2013
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}


	/** 对象表名 **/
    public final static String TABLE_NAME = "IRP_LOGTYPE";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "LOGTYPEID";
    
    @Override
	public Long getId(){
	  return (long)this.logtypeid;
	}
    
    @Override
	public String getName(){
	  return this.typename;	
	}
	
    @Override
	public String getTableName(){
	  return TABLE_NAME;	
	}

	@Override
	public String getIdFieldName() {
		return ID_FIELD_NAME;
	}
	
}