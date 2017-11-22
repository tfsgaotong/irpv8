package com.tfs.irp.docrecommend.entity;

import java.util.Date;
import java.util.List;

import com.tfs.irp.base.IrpBaseObj;

public class IrpDocrecommend extends IrpBaseObj {
    /**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.RECOMMENDID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Long recommendid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.DOCID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Long docid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.RECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private String recommend;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.CRUSER
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private String cruser;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.CRTIME
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Date crtime;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.ISDEL
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Integer isdel;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.CRUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Long cruserid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_DOCRECOMMEND.REPLAYUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	private Long replayuserid;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.RECOMMENDID
	 * @return  the value of IRP_DOCRECOMMEND.RECOMMENDID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Long getRecommendid() {
		return recommendid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.RECOMMENDID
	 * @param recommendid  the value for IRP_DOCRECOMMEND.RECOMMENDID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setRecommendid(Long recommendid) {
		this.recommendid = recommendid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.DOCID
	 * @return  the value of IRP_DOCRECOMMEND.DOCID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Long getDocid() {
		return docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.DOCID
	 * @param docid  the value for IRP_DOCRECOMMEND.DOCID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setDocid(Long docid) {
		this.docid = docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.RECOMMEND
	 * @return  the value of IRP_DOCRECOMMEND.RECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public String getRecommend() {
		return recommend;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.RECOMMEND
	 * @param recommend  the value for IRP_DOCRECOMMEND.RECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.CRUSER
	 * @return  the value of IRP_DOCRECOMMEND.CRUSER
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public String getCruser() {
		return cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.CRUSER
	 * @param cruser  the value for IRP_DOCRECOMMEND.CRUSER
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.CRTIME
	 * @return  the value of IRP_DOCRECOMMEND.CRTIME
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.CRTIME
	 * @param crtime  the value for IRP_DOCRECOMMEND.CRTIME
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.ISDEL
	 * @return  the value of IRP_DOCRECOMMEND.ISDEL
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Integer getIsdel() {
		return isdel;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.ISDEL
	 * @param isdel  the value for IRP_DOCRECOMMEND.ISDEL
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.CRUSERID
	 * @return  the value of IRP_DOCRECOMMEND.CRUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.CRUSERID
	 * @param cruserid  the value for IRP_DOCRECOMMEND.CRUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_DOCRECOMMEND.REPLAYUSERID
	 * @return  the value of IRP_DOCRECOMMEND.REPLAYUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public Long getReplayuserid() {
		return replayuserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_DOCRECOMMEND.REPLAYUSERID
	 * @param replayuserid  the value for IRP_DOCRECOMMEND.REPLAYUSERID
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	public void setReplayuserid(Long replayuserid) {
		this.replayuserid = replayuserid;
	}
	/**
     *  对象表名
     */
	public final static String TABLE_NAME = "IRP_DOCRECOMMEND";
	
	/**
     * 主键字段名称
     */
	public final static String ID_FIELD_NAME = "RECOMMENDID";
	
	@Override
	public Long getId() {
		return this.recommendid;
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
	
	public IrpDocrecommend parentDocRecommend=null;
	/**
	 * 正常
	 */
	public static final Integer ISDEL_PRO=1;
	public IrpDocrecommend getParentDocRecommend() {
		return parentDocRecommend;
	}

	public void setParentDocRecommend(IrpDocrecommend parentDocRecommend) {
		this.parentDocRecommend = parentDocRecommend;
	}
	/**
	 * 删除
	 */
	public static final Integer ISDEL_DELETE=2;
	 
	/**
	 * 用户头像路径
	 */
	public String userPicUrl;
 

	public String getUserPicUrl() {
		return userPicUrl;
	}

	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}
}