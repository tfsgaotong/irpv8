package com.tfs.irp.complain.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpComplain extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.COMPLAINID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private Long complainid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.COMPLAINTYPEID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private String complaintypeid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.COMPLAINDESC
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private String complaindesc;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.ANSQUES
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private Short ansques;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.PARENTID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private Long parentid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.CREATUSER
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private String creatuser;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_COMPLAIN.CREATTIME
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    private Date creattime;


    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.COMPLAINID
     *
     * @return the value of IRP_COMPLAIN.COMPLAINID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public Long getComplainid() {
        return complainid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.COMPLAINID
     *
     * @param complainid the value for IRP_COMPLAIN.COMPLAINID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setComplainid(Long complainid) {
        this.complainid = complainid;
    }


    public String getComplaintypeid() {
		return complaintypeid;
	}

	public void setComplaintypeid(String complaintypeid) {
		this.complaintypeid = complaintypeid;
	}

	/**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.COMPLAINDESC
     *
     * @return the value of IRP_COMPLAIN.COMPLAINDESC
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public String getComplaindesc() {
        return complaindesc;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.COMPLAINDESC
     *
     * @param complaindesc the value for IRP_COMPLAIN.COMPLAINDESC
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setComplaindesc(String complaindesc) {
        this.complaindesc = complaindesc;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.ANSQUES
     *
     * @return the value of IRP_COMPLAIN.ANSQUES
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public Short getAnsques() {
        return ansques;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.ANSQUES
     *
     * @param ansques the value for IRP_COMPLAIN.ANSQUES
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setAnsques(Short ansques) {
        this.ansques = ansques;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.PARENTID
     *
     * @return the value of IRP_COMPLAIN.PARENTID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.PARENTID
     *
     * @param parentid the value for IRP_COMPLAIN.PARENTID
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.CREATUSER
     *
     * @return the value of IRP_COMPLAIN.CREATUSER
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public String getCreatuser() {
        return creatuser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.CREATUSER
     *
     * @param creatuser the value for IRP_COMPLAIN.CREATUSER
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setCreatuser(String creatuser) {
        this.creatuser = creatuser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_COMPLAIN.CREATTIME
     *
     * @return the value of IRP_COMPLAIN.CREATTIME
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_COMPLAIN.CREATTIME
     *
     * @param creattime the value for IRP_COMPLAIN.CREATTIME
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /** 反馈意见**/
    public final static short COMPLAIN_STATUS_SUGGESTION = 1;

    /** 反馈答复**/
    public final static short COMPLAIN_STATUS_REPLY = 2;
    
    /** 对象表名 **/
    public final static String TABLE_NAME = "IRP_COMPLAIN";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "COMPLAINID";


	@Override
	public Long getId() {
		return this.complainid;
	}

	@Override
	public String getName() {
		return this.complaindesc;
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