package com.tfs.irp.bug.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpBug  extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.BUGID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long bugid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.SERIANUM
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long serianum;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.TITLE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String title;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.FOUNDERID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long founderid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.OPERATORID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long operatorid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.PROJECTID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long projectid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.PRIORITY
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String priority;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.STATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String state;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.CREATETIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Date createtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.REQUIREDTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Date requiredtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.FINISHTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Date finishtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.PRINTSCREEN
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String printscreen;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.FRESHNESS
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long freshness;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.ISDISPOSE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long isdispose;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.NEWESTSTATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String neweststate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.VERSIONID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long versionid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.MODULEID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private Long moduleid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.FILENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String filename;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_BUG.FILETRUENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    private String filetruename;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.BUGID
     *
     * @return the value of IRP_BUG.BUGID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getBugid() {
        return bugid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.BUGID
     *
     * @param bugid the value for IRP_BUG.BUGID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setBugid(Long bugid) {
        this.bugid = bugid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.SERIANUM
     *
     * @return the value of IRP_BUG.SERIANUM
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getSerianum() {
        return serianum;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.SERIANUM
     *
     * @param serianum the value for IRP_BUG.SERIANUM
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setSerianum(Long serianum) {
        this.serianum = serianum;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.TITLE
     *
     * @return the value of IRP_BUG.TITLE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.TITLE
     *
     * @param title the value for IRP_BUG.TITLE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.FOUNDERID
     *
     * @return the value of IRP_BUG.FOUNDERID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getFounderid() {
        return founderid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.FOUNDERID
     *
     * @param founderid the value for IRP_BUG.FOUNDERID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setFounderid(Long founderid) {
        this.founderid = founderid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.OPERATORID
     *
     * @return the value of IRP_BUG.OPERATORID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getOperatorid() {
        return operatorid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.OPERATORID
     *
     * @param operatorid the value for IRP_BUG.OPERATORID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.PROJECTID
     *
     * @return the value of IRP_BUG.PROJECTID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.PROJECTID
     *
     * @param projectid the value for IRP_BUG.PROJECTID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.PRIORITY
     *
     * @return the value of IRP_BUG.PRIORITY
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.PRIORITY
     *
     * @param priority the value for IRP_BUG.PRIORITY
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.STATE
     *
     * @return the value of IRP_BUG.STATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.STATE
     *
     * @param state the value for IRP_BUG.STATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.CREATETIME
     *
     * @return the value of IRP_BUG.CREATETIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.CREATETIME
     *
     * @param createtime the value for IRP_BUG.CREATETIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.REQUIREDTIME
     *
     * @return the value of IRP_BUG.REQUIREDTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Date getRequiredtime() {
        return requiredtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.REQUIREDTIME
     *
     * @param requiredtime the value for IRP_BUG.REQUIREDTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setRequiredtime(Date requiredtime) {
        this.requiredtime = requiredtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.FINISHTIME
     *
     * @return the value of IRP_BUG.FINISHTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Date getFinishtime() {
        return finishtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.FINISHTIME
     *
     * @param finishtime the value for IRP_BUG.FINISHTIME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.PRINTSCREEN
     *
     * @return the value of IRP_BUG.PRINTSCREEN
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getPrintscreen() {
        return printscreen;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.PRINTSCREEN
     *
     * @param printscreen the value for IRP_BUG.PRINTSCREEN
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setPrintscreen(String printscreen) {
        this.printscreen = printscreen;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.FRESHNESS
     *
     * @return the value of IRP_BUG.FRESHNESS
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getFreshness() {
        return freshness;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.FRESHNESS
     *
     * @param freshness the value for IRP_BUG.FRESHNESS
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setFreshness(Long freshness) {
        this.freshness = freshness;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.ISDISPOSE
     *
     * @return the value of IRP_BUG.ISDISPOSE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getIsdispose() {
        return isdispose;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.ISDISPOSE
     *
     * @param isdispose the value for IRP_BUG.ISDISPOSE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setIsdispose(Long isdispose) {
        this.isdispose = isdispose;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.NEWESTSTATE
     *
     * @return the value of IRP_BUG.NEWESTSTATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getNeweststate() {
        return neweststate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.NEWESTSTATE
     *
     * @param neweststate the value for IRP_BUG.NEWESTSTATE
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setNeweststate(String neweststate) {
        this.neweststate = neweststate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.VERSIONID
     *
     * @return the value of IRP_BUG.VERSIONID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getVersionid() {
        return versionid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.VERSIONID
     *
     * @param versionid the value for IRP_BUG.VERSIONID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setVersionid(Long versionid) {
        this.versionid = versionid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.MODULEID
     *
     * @return the value of IRP_BUG.MODULEID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public Long getModuleid() {
        return moduleid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.MODULEID
     *
     * @param moduleid the value for IRP_BUG.MODULEID
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setModuleid(Long moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.FILENAME
     *
     * @return the value of IRP_BUG.FILENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getFilename() {
        return filename;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.FILENAME
     *
     * @param filename the value for IRP_BUG.FILENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_BUG.FILETRUENAME
     *
     * @return the value of IRP_BUG.FILETRUENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public String getFiletruename() {
        return filetruename;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_BUG.FILETRUENAME
     *
     * @param filetruename the value for IRP_BUG.FILETRUENAME
     *
     * @ibatorgenerated Thu Feb 18 15:30:20 CST 2016
     */
    public void setFiletruename(String filetruename) {
        this.filetruename = filetruename;
    }
    /** 对象表名 **/
    public final static String TABLE_NAME = "IRP_BUG";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "BUGID";

	@Override
	public Long getId() {
		return this.bugid;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getTableName() {
		return this.TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return this.ID_FIELD_NAME;
	}
	
	public static final String STATE_CREAT ="已创建";
	
	public static final String STATE_WEIWAN ="未完成";
	
	public static final String STATE_SHENHE ="待审核";
	
	public static final String STATE_FORWARD="已转发";
	
	public static final String STATE_WANCHENG ="已完成";
	
	public static final String FINISHTIME_ANSHI="按时完成";
	
	public static final String FINISHTIME_WEIWAN="未完成";
	/**
	 * 第一次创建
	 */
	public static final Long FRESHNESS_FIRST=-1L;
	/**
	 * 过程
	 */
	public static final Long FRESHNESS_FLOW=0L;
	/**
	 * 最新
	 */
	public static final Long FRESHNESS_NEW=1L;
	
	/**
	 * 预处理
	 */
	public static final Long FRESHNESS_WILL=2L;
	
	/**
	 * 没被处理过
	 */
	public static final Long ISDISPORE_NO=-1L;
	/**
	 * 被处理过
	 */
	public static final Long ISDISPORE_YES=1L;
	/**
	 * 处理完成
	 */
	public static final Long ISDISPORE_OK=2L;
	
	/**
	 * 严重
	 */
	public static final String PRIORITY_YAN = "5";
	
	/**
	 * 紧急
	 */
	public static final String PRIORITY_JIN = "4";
	
	/**
	 * 高
	 */
	public static final String PRIORITY_GAO = "3";
	
	/**
	 * 中
	 */
	public static final String PRIORITY_ZHONG = "2";
	
	/**
	 * 低
	 */
	public static final String PRIORITY_DI = "1";
	
	/**
	 * 页面bug显示配置(两部分)
	 */
	public static final String PAGE_SIZE_TOW ="BUG_SHOW_TOW_COUNT";
	
	/**
	 * 页面bug显示配置(一部分)
	 */
	public static final String PAGE_SIZE_ONE ="BUG_SHOW_ONE_COUNT";
}