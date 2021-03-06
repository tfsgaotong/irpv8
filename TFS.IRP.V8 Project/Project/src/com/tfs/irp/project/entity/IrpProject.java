package com.tfs.irp.project.entity;

import java.util.Date;
import java.util.List;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.user.entity.IrpUser;
import java.math.BigDecimal;

public class IrpProject extends IrpBaseObj{

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PROJECTID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long projectid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PRNAME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private String prname;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PRDESC
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private String prdesc;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.CRTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Date crtime;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.STARTTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Date starttime;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.ENDTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Date endtime;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.CRUSERID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long cruserid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.OFFPERSONID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long offpersonid;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.ISCLOSED
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long isclosed;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.ISPUBLISH
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long ispublish;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PROKEY
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private String prokey;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PROSTATUS
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long prostatus;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PROJECTFILE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private String projectfile;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_PROJECT.PROJECTTYPE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	private Long projecttype;
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PROJECTID
	 * @return  the value of IRP_PROJECT.PROJECTID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getProjectid() {
		return projectid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PROJECTID
	 * @param projectid  the value for IRP_PROJECT.PROJECTID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PRNAME
	 * @return  the value of IRP_PROJECT.PRNAME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public String getPrname() {
		return prname;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PRNAME
	 * @param prname  the value for IRP_PROJECT.PRNAME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setPrname(String prname) {
		this.prname = prname;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PRDESC
	 * @return  the value of IRP_PROJECT.PRDESC
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public String getPrdesc() {
		return prdesc;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PRDESC
	 * @param prdesc  the value for IRP_PROJECT.PRDESC
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setPrdesc(String prdesc) {
		this.prdesc = prdesc;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.CRTIME
	 * @return  the value of IRP_PROJECT.CRTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.CRTIME
	 * @param crtime  the value for IRP_PROJECT.CRTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.STARTTIME
	 * @return  the value of IRP_PROJECT.STARTTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.STARTTIME
	 * @param starttime  the value for IRP_PROJECT.STARTTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.ENDTIME
	 * @return  the value of IRP_PROJECT.ENDTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.ENDTIME
	 * @param endtime  the value for IRP_PROJECT.ENDTIME
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.CRUSERID
	 * @return  the value of IRP_PROJECT.CRUSERID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.CRUSERID
	 * @param cruserid  the value for IRP_PROJECT.CRUSERID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.OFFPERSONID
	 * @return  the value of IRP_PROJECT.OFFPERSONID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getOffpersonid() {
		return offpersonid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.OFFPERSONID
	 * @param offpersonid  the value for IRP_PROJECT.OFFPERSONID
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setOffpersonid(Long offpersonid) {
		this.offpersonid = offpersonid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.ISCLOSED
	 * @return  the value of IRP_PROJECT.ISCLOSED
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getIsclosed() {
		return isclosed;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.ISCLOSED
	 * @param isclosed  the value for IRP_PROJECT.ISCLOSED
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setIsclosed(Long isclosed) {
		this.isclosed = isclosed;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.ISPUBLISH
	 * @return  the value of IRP_PROJECT.ISPUBLISH
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getIspublish() {
		return ispublish;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.ISPUBLISH
	 * @param ispublish  the value for IRP_PROJECT.ISPUBLISH
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setIspublish(Long ispublish) {
		this.ispublish = ispublish;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PROKEY
	 * @return  the value of IRP_PROJECT.PROKEY
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public String getProkey() {
		return prokey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PROKEY
	 * @param prokey  the value for IRP_PROJECT.PROKEY
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setProkey(String prokey) {
		this.prokey = prokey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PROSTATUS
	 * @return  the value of IRP_PROJECT.PROSTATUS
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getProstatus() {
		return prostatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PROSTATUS
	 * @param prostatus  the value for IRP_PROJECT.PROSTATUS
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setProstatus(Long prostatus) {
		this.prostatus = prostatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PROJECTFILE
	 * @return  the value of IRP_PROJECT.PROJECTFILE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public String getProjectfile() {
		return projectfile;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PROJECTFILE
	 * @param projectfile  the value for IRP_PROJECT.PROJECTFILE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setProjectfile(String projectfile) {
		this.projectfile = projectfile;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_PROJECT.PROJECTTYPE
	 * @return  the value of IRP_PROJECT.PROJECTTYPE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public Long getProjecttype() {
		return projecttype;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_PROJECT.PROJECTTYPE
	 * @param projecttype  the value for IRP_PROJECT.PROJECTTYPE
	 * @ibatorgenerated  Tue Aug 26 16:41:09 CST 2014
	 */
	public void setProjecttype(Long projecttype) {
		this.projecttype = projecttype;
	}
	/**
     *  对象表名
     */
	public final static String TABLE_NAME = "IRP_PROJECT";
	
	/**
     * 主键字段名称
     */
	public final static String ID_FIELD_NAME = "PROJECTID";
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return projectid;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return prname;
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
	/**
	 * 项目已经完成
	*/
	public  static final Long IS_CLOSED=2L;
	/**
	 * 项目未完�?
	 */
	public  static final Long NOT_CLOSED=1L;
	/**
	 * 项目发布
	 */
	public  static final Long IS_PUBLISH=2L;
	/**
	 * 项目隐私
	 */
	public  static final Long NOT_PUBLISH=1L;
	/**
	 * 项目负责�?
	 */
	public IrpUser offPerson;
	/**
	 * 项目负责人的头像
	 */
	public String userPicUrl;
	
	public String getUserPicUrl() {
		return userPicUrl;
	}

	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}

	public IrpUser getOffPerson() {
		return offPerson;
	}

	public void setOffPerson(IrpUser offPerson) {
		this.offPerson = offPerson;
	}
	/**
	 * 项目被删�?
	 */
	public static String PROJECT_IS_DELETE="项目不存在，可能被删�?";
	/**
	 * 不是负责人，没有操作权限
	 */
	public static String PROJECT_IS_UPDATE_RIGHT="对不起，只有负责人可以修改项目内�?";
	
}