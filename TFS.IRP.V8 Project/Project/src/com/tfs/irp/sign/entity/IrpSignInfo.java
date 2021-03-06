package com.tfs.irp.sign.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpSignInfo extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.ID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private Long signinfoid;

	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private Long signid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNINTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private Date signintime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNOUTTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private Date signouttime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNINIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signinip;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNOUTIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signoutip;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNINSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signinstatus;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNOUTSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signoutstatus;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNINDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signindirection;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNOUTDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signoutdirection;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_SIGN_INFO.SIGNCOMMENT
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    private String signcomment;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.ID
     *
     * @return the value of IRP_SIGN_INFO.ID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public Long getSigninfoid() {
		return signinfoid;
	}

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.ID
     *
     * @param id the value for IRP_SIGN_INFO.ID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSigninfoid(Long signinfoid) {
		this.signinfoid = signinfoid;
	}

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNID
     *
     * @return the value of IRP_SIGN_INFO.SIGNID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public Long getSignid() {
        return signid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNID
     *
     * @param signid the value for IRP_SIGN_INFO.SIGNID
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignid(Long signid) {
        this.signid = signid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNINTIME
     *
     * @return the value of IRP_SIGN_INFO.SIGNINTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public Date getSignintime() {
        return signintime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNINTIME
     *
     * @param signintime the value for IRP_SIGN_INFO.SIGNINTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignintime(Date signintime) {
        this.signintime = signintime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNOUTTIME
     *
     * @return the value of IRP_SIGN_INFO.SIGNOUTTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public Date getSignouttime() {
        return signouttime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNOUTTIME
     *
     * @param signouttime the value for IRP_SIGN_INFO.SIGNOUTTIME
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignouttime(Date signouttime) {
        this.signouttime = signouttime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNINIP
     *
     * @return the value of IRP_SIGN_INFO.SIGNINIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSigninip() {
        return signinip;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNINIP
     *
     * @param signinip the value for IRP_SIGN_INFO.SIGNINIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSigninip(String signinip) {
        this.signinip = signinip;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNOUTIP
     *
     * @return the value of IRP_SIGN_INFO.SIGNOUTIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSignoutip() {
        return signoutip;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNOUTIP
     *
     * @param signoutip the value for IRP_SIGN_INFO.SIGNOUTIP
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignoutip(String signoutip) {
        this.signoutip = signoutip;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNINSTATUS
     *
     * @return the value of IRP_SIGN_INFO.SIGNINSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSigninstatus() {
        return signinstatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNINSTATUS
     *
     * @param signinstatus the value for IRP_SIGN_INFO.SIGNINSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSigninstatus(String signinstatus) {
        this.signinstatus = signinstatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNOUTSTATUS
     *
     * @return the value of IRP_SIGN_INFO.SIGNOUTSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSignoutstatus() {
        return signoutstatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNOUTSTATUS
     *
     * @param signoutstatus the value for IRP_SIGN_INFO.SIGNOUTSTATUS
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignoutstatus(String signoutstatus) {
        this.signoutstatus = signoutstatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNINDIRECTION
     *
     * @return the value of IRP_SIGN_INFO.SIGNINDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSignindirection() {
        return signindirection;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNINDIRECTION
     *
     * @param signindirection the value for IRP_SIGN_INFO.SIGNINDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignindirection(String signindirection) {
        this.signindirection = signindirection;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNOUTDIRECTION
     *
     * @return the value of IRP_SIGN_INFO.SIGNOUTDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSignoutdirection() {
        return signoutdirection;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNOUTDIRECTION
     *
     * @param signoutdirection the value for IRP_SIGN_INFO.SIGNOUTDIRECTION
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSignoutdirection(String signoutdirection) {
        this.signoutdirection = signoutdirection;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_SIGN_INFO.SIGNCOMMENT
     *
     * @return the value of IRP_SIGN_INFO.SIGNCOMMENT
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public String getSigncomment() {
        return signcomment;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_SIGN_INFO.SIGNCOMMENT
     *
     * @param signcomment the value for IRP_SIGN_INFO.SIGNCOMMENT
     *
     * @ibatorgenerated Thu Jan 16 13:46:03 CST 2014
     */
    public void setSigncomment(String signcomment) {
        this.signcomment = signcomment;
    }
    
    /** 对象表名 **/
    public final static String TABLE_NAME = "IRP_SIGN_INFO";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "SIGNINFOID";
    
	@Override
	public Long getId() {
		return this.signinfoid;
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