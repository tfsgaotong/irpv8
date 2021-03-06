package com.tfs.irp.assetype.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpAssetype extends IrpBaseObj{
    /**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_ASSETYPE.ASSEROOMAPPLYTYPEID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	private Long asseroomapplytypeid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_ASSETYPE.ASSETYPENAME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	private String assetypename;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_ASSETYPE.CRTIME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	private Date crtime;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_ASSETYPE.CRUSERID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	private Long cruserid;
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_ASSETYPE.ASSEROOMAPPLYTYPEID
	 * @return  the value of IRP_ASSETYPE.ASSEROOMAPPLYTYPEID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public Long getAsseroomapplytypeid() {
		return asseroomapplytypeid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_ASSETYPE.ASSEROOMAPPLYTYPEID
	 * @param asseroomapplytypeid  the value for IRP_ASSETYPE.ASSEROOMAPPLYTYPEID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void setAsseroomapplytypeid(Long asseroomapplytypeid) {
		this.asseroomapplytypeid = asseroomapplytypeid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_ASSETYPE.ASSETYPENAME
	 * @return  the value of IRP_ASSETYPE.ASSETYPENAME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public String getAssetypename() {
		return assetypename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_ASSETYPE.ASSETYPENAME
	 * @param assetypename  the value for IRP_ASSETYPE.ASSETYPENAME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void setAssetypename(String assetypename) {
		this.assetypename = assetypename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_ASSETYPE.CRTIME
	 * @return  the value of IRP_ASSETYPE.CRTIME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_ASSETYPE.CRTIME
	 * @param crtime  the value for IRP_ASSETYPE.CRTIME
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_ASSETYPE.CRUSERID
	 * @return  the value of IRP_ASSETYPE.CRUSERID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_ASSETYPE.CRUSERID
	 * @param cruserid  the value for IRP_ASSETYPE.CRUSERID
	 * @ibatorgenerated  Thu Aug 25 17:07:22 CST 2016
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/** 对象表名 **/
    public final static String TABLE_NAME = "IRP_ASSETYPE";
    
    /** 主键字段�?**/
    public final static String ID_FIELD_NAME = "ASSEROOMAPPLYTYPEID";
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return asseroomapplytypeid;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		// TODO Auto-generated method stub
		return ID_FIELD_NAME;
	}
}