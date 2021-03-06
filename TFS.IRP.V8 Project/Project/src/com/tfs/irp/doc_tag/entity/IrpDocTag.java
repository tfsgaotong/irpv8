package com.tfs.irp.doc_tag.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpDocTag extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOC_TAG.DOCTAGID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    private Long doctagid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOC_TAG.DOCID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    private Long docid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOC_TAG.MARKUSERID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    private Long markuserid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOC_TAG.MARKTIME
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    private Date marktime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOC_TAG.TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    private String tag;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOC_TAG.DOCTAGID
     *
     * @return the value of IRP_DOC_TAG.DOCTAGID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public Long getDoctagid() {
        return doctagid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOC_TAG.DOCTAGID
     *
     * @param doctagid the value for IRP_DOC_TAG.DOCTAGID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public void setDoctagid(Long doctagid) {
        this.doctagid = doctagid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOC_TAG.DOCID
     *
     * @return the value of IRP_DOC_TAG.DOCID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public Long getDocid() {
        return docid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOC_TAG.DOCID
     *
     * @param docid the value for IRP_DOC_TAG.DOCID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public void setDocid(Long docid) {
        this.docid = docid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOC_TAG.MARKUSERID
     *
     * @return the value of IRP_DOC_TAG.MARKUSERID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public Long getMarkuserid() {
        return markuserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOC_TAG.MARKUSERID
     *
     * @param markuserid the value for IRP_DOC_TAG.MARKUSERID
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public void setMarkuserid(Long markuserid) {
        this.markuserid = markuserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOC_TAG.MARKTIME
     *
     * @return the value of IRP_DOC_TAG.MARKTIME
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public Date getMarktime() {
        return marktime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOC_TAG.MARKTIME
     *
     * @param marktime the value for IRP_DOC_TAG.MARKTIME
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public void setMarktime(Date marktime) {
        this.marktime = marktime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOC_TAG.TAG
     *
     * @return the value of IRP_DOC_TAG.TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOC_TAG.TAG
     *
     * @param tag the value for IRP_DOC_TAG.TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    /**对象表名*/
    public final static String TABLE_NAME = "IRP_DOC_TAG";
    
    /**主键字段名称*/
	public final static String ID_FIELD_NAME = "DOCTAGID";

	@Override
	public Long getId() {
		return this.doctagid;
	}

	@Override
	public String getName() {
		return this.tag;
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