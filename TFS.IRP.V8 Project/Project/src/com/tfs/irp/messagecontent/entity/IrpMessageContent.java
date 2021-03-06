package com.tfs.irp.messagecontent.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpMessageContent extends IrpBaseObj {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.MESSAGEID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Long messageid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.FROM_UID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Long fromUid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.CONTENT
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private String content;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.ISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Integer isdel;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.CRTIME
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Date crtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.CRUSERID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Long cruserid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.CRISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Long crisdel;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_MESSAGE_CONTENT.BROWSESTATUS
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    private Integer browsestatus;
    
    private Integer messagetype;
    
    public Integer getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(Integer messagetype) {
		this.messagetype = messagetype;
	}

	/**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.MESSAGEID
     *
     * @return the value of IRP_MESSAGE_CONTENT.MESSAGEID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Long getMessageid() {
        return messageid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.MESSAGEID
     *
     * @param messageid the value for IRP_MESSAGE_CONTENT.MESSAGEID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.FROM_UID
     *
     * @return the value of IRP_MESSAGE_CONTENT.FROM_UID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Long getFromUid() {
        return fromUid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.FROM_UID
     *
     * @param fromUid the value for IRP_MESSAGE_CONTENT.FROM_UID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.CONTENT
     *
     * @return the value of IRP_MESSAGE_CONTENT.CONTENT
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.CONTENT
     *
     * @param content the value for IRP_MESSAGE_CONTENT.CONTENT
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.ISDEL
     *
     * @return the value of IRP_MESSAGE_CONTENT.ISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.ISDEL
     *
     * @param isdel the value for IRP_MESSAGE_CONTENT.ISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.CRTIME
     *
     * @return the value of IRP_MESSAGE_CONTENT.CRTIME
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Date getCrtime() {
        return crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.CRTIME
     *
     * @param crtime the value for IRP_MESSAGE_CONTENT.CRTIME
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.CRUSERID
     *
     * @return the value of IRP_MESSAGE_CONTENT.CRUSERID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Long getCruserid() {
        return cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.CRUSERID
     *
     * @param cruserid the value for IRP_MESSAGE_CONTENT.CRUSERID
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setCruserid(Long cruserid) {
        this.cruserid = cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.CRISDEL
     *
     * @return the value of IRP_MESSAGE_CONTENT.CRISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Long getCrisdel() {
        return crisdel;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.CRISDEL
     *
     * @param crisdel the value for IRP_MESSAGE_CONTENT.CRISDEL
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setCrisdel(Long crisdel) {
        this.crisdel = crisdel;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_MESSAGE_CONTENT.BROWSESTATUS
     *
     * @return the value of IRP_MESSAGE_CONTENT.BROWSESTATUS
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public Integer getBrowsestatus() {
        return browsestatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_MESSAGE_CONTENT.BROWSESTATUS
     *
     * @param browsestatus the value for IRP_MESSAGE_CONTENT.BROWSESTATUS
     *
     * @ibatorgenerated Sun Jun 09 16:47:42 CST 2013
     */
    public void setBrowsestatus(Integer browsestatus) {
        this.browsestatus = browsestatus;
    }
    
    /**
     * 正常状态
     * @return
     */
    public static final Integer NORMALSTATUS = 0;
    /**
     * 发送者删除    1
     * @return
     */
    public static final Integer CRUSERSTATUS = 1;
    /**
     * 接收者删除   2
     * @return
     */
    public static final Integer FROMSTATUS = 2;
    /**
     * 两者都删除   3
     * @return
     */
    public static final Integer BOTHSTATUS = 3;
    /**
     * 配置发送私信的字数限制
     * @return
     */
    public static final String MESSAGEFONTCOUNT="MESSAGE_FONT_NUMBER";
    /**
     * 配置私信详细信息字数
     * @return
     */
    public static final String MESSAGEDETAILNUMBER="MESSAGE_DETAIL_NUMBER";
    /**
     * 未读私信状态
     * @return
     */
    public static final Integer MESSAGEUNREADSTATUS = 0;
    /**
     * 已读私信状态
     * @return
     */
    public static final Integer MESSAGEREADSTATUS = 1;
    /**
     * 已读私信状态（会话时）
     * @return
     */
    public static final Integer MESSAGEREADSTATUSDETAIL = 2;
    
    /**
     * 多长时间加载一次未读私信
     * @return
     */
    public static final String MESSAGELOADUNREADTIME ="MESSAGE_UNREAD_TIME";
    
    /**
     * 数据库表名    
     * @return
     */
    public static final String TABLE_NAME="IRP_MESSAGE_CONTENT";
    /**
     * 主键字段名称
     */
	public final static String ID_FIELD_NAME = "MESSAGEID";

	@Override
	public Long getId() {
		return this.messageid;
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