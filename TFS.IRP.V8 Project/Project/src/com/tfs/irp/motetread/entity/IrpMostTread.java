package com.tfs.irp.motetread.entity;

import java.math.BigDecimal;
import java.util.Date;

public class IrpMostTread {
    /**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_MOST_TREAD.DOCID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	private Long docid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_MOST_TREAD.CRUSERID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	private Long cruserid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_MOST_TREAD.CRTIME
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	private Date crtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_MOST_TREAD.STATUS
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	private BigDecimal status;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_MOST_TREAD.MOSTTYPE
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	private BigDecimal mosttype;
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_MOST_TREAD.DOCID
	 * @return  the value of IRP_MOST_TREAD.DOCID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public Long getDocid() {
		return docid;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_MOST_TREAD.DOCID
	 * @param docid  the value for IRP_MOST_TREAD.DOCID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_MOST_TREAD.CRUSERID
	 * @return  the value of IRP_MOST_TREAD.CRUSERID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public Long getCruserid() {
		return cruserid;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_MOST_TREAD.CRUSERID
	 * @param cruserid  the value for IRP_MOST_TREAD.CRUSERID
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_MOST_TREAD.CRTIME
	 * @return  the value of IRP_MOST_TREAD.CRTIME
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public Date getCrtime() {
		return crtime;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_MOST_TREAD.CRTIME
	 * @param crtime  the value for IRP_MOST_TREAD.CRTIME
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_MOST_TREAD.STATUS
	 * @return  the value of IRP_MOST_TREAD.STATUS
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public BigDecimal getStatus() {
		return status;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_MOST_TREAD.STATUS
	 * @param status  the value for IRP_MOST_TREAD.STATUS
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_MOST_TREAD.MOSTTYPE
	 * @return  the value of IRP_MOST_TREAD.MOSTTYPE
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public BigDecimal getMosttype() {
		return mosttype;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_MOST_TREAD.MOSTTYPE
	 * @param mosttype  the value for IRP_MOST_TREAD.MOSTTYPE
	 * @ibatorgenerated  Tue Sep 24 09:40:23 CST 2013
	 */
	public void setMosttype(BigDecimal mosttype) {
		this.mosttype = mosttype;
	}
	/**
     * 顶
     */
    public static final Integer MOTE_TREAD_STATUS_DING=2;
    /**
     * 踩
     */
    public static final Integer MOTE_TREAD_STATUS_CAI=1;
    /**
     * 知识的顶采类型
     */
    public  static  final  Integer MOTE_DOCUMENT_TYPE=10;
    /**
     * 问答的顶采类型
     */
    public static  final  Integer  MOTE_QUESTION_TYPE=20;
}