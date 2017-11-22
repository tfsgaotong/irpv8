package com.tfs.irp.chnl_doc_link.entity;

import java.util.Date;
import java.util.List;

import com.tfs.irp.base.IrpBaseObj; 
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.user.entity.IrpUser;

import java.math.BigDecimal;
public class IrpChnlDocLink extends IrpBaseObj{
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CHNLDOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long chnldocid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CHANNELID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long channelid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCTITLE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private String doctitle;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCORDER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docorder;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCSTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docstatus;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private String cruser;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CRTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Date crtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCPUBTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Date docpubtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCPUBURL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private String docpuburl;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCORDERPRI
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docorderpri;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.INVALIDTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Date invalidtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DELUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private String deluser;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Date deltime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.MODAL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Integer modal;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCRELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Date docreltime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCCHANNEL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docchannel;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCFLAG
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Integer docflag;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCKIND
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long dockind;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.SITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long siteid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.SRCSITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long srcsiteid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.LIFESTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long lifestatus;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCCRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private String doccruser;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.HITSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long hitscount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.COMMENTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long commentcount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.COLLECTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long collectcount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CRUSERID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long cruserid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.COMPLETESCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long completescore;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.CRYSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long cryscore;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.ALLSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long allscore;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.TRANSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long transcount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.INFORMTYPE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long informtype;
	
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_CHNL_DOC_LINK.DOCSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	private Long docscore;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CHNLDOCID
	 * @return  the value of IRP_CHNL_DOC_LINK.CHNLDOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getChnldocid() {
		return chnldocid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CHNLDOCID
	 * @param chnldocid  the value for IRP_CHNL_DOC_LINK.CHNLDOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setChnldocid(Long chnldocid) {
		this.chnldocid = chnldocid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CHANNELID
	 * @return  the value of IRP_CHNL_DOC_LINK.CHANNELID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getChannelid() {
		return channelid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CHANNELID
	 * @param channelid  the value for IRP_CHNL_DOC_LINK.CHANNELID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCID
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocid() {
		return docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCID
	 * @param docid  the value for IRP_CHNL_DOC_LINK.DOCID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocid(Long docid) {
		this.docid = docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCTITLE
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCTITLE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public String getDoctitle() {
		return doctitle;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCTITLE
	 * @param doctitle  the value for IRP_CHNL_DOC_LINK.DOCTITLE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCORDER
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCORDER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocorder() {
		return docorder;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCORDER
	 * @param docorder  the value for IRP_CHNL_DOC_LINK.DOCORDER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocorder(Long docorder) {
		this.docorder = docorder;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCSTATUS
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCSTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocstatus() {
		return docstatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCSTATUS
	 * @param docstatus  the value for IRP_CHNL_DOC_LINK.DOCSTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocstatus(Long docstatus) {
		this.docstatus = docstatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CRUSER
	 * @return  the value of IRP_CHNL_DOC_LINK.CRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public String getCruser() {
		return cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CRUSER
	 * @param cruser  the value for IRP_CHNL_DOC_LINK.CRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CRTIME
	 * @return  the value of IRP_CHNL_DOC_LINK.CRTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CRTIME
	 * @param crtime  the value for IRP_CHNL_DOC_LINK.CRTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCPUBTIME
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCPUBTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Date getDocpubtime() {
		return docpubtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCPUBTIME
	 * @param docpubtime  the value for IRP_CHNL_DOC_LINK.DOCPUBTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocpubtime(Date docpubtime) {
		this.docpubtime = docpubtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCPUBURL
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCPUBURL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public String getDocpuburl() {
		return docpuburl;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCPUBURL
	 * @param docpuburl  the value for IRP_CHNL_DOC_LINK.DOCPUBURL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocpuburl(String docpuburl) {
		this.docpuburl = docpuburl;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCORDERPRI
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCORDERPRI
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocorderpri() {
		return docorderpri;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCORDERPRI
	 * @param docorderpri  the value for IRP_CHNL_DOC_LINK.DOCORDERPRI
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocorderpri(Long docorderpri) {
		this.docorderpri = docorderpri;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.INVALIDTIME
	 * @return  the value of IRP_CHNL_DOC_LINK.INVALIDTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Date getInvalidtime() {
		return invalidtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.INVALIDTIME
	 * @param invalidtime  the value for IRP_CHNL_DOC_LINK.INVALIDTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setInvalidtime(Date invalidtime) {
		this.invalidtime = invalidtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DELUSER
	 * @return  the value of IRP_CHNL_DOC_LINK.DELUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public String getDeluser() {
		return deluser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DELUSER
	 * @param deluser  the value for IRP_CHNL_DOC_LINK.DELUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDeluser(String deluser) {
		this.deluser = deluser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DELTIME
	 * @return  the value of IRP_CHNL_DOC_LINK.DELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Date getDeltime() {
		return deltime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DELTIME
	 * @param deltime  the value for IRP_CHNL_DOC_LINK.DELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDeltime(Date deltime) {
		this.deltime = deltime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.MODAL
	 * @return  the value of IRP_CHNL_DOC_LINK.MODAL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Integer getModal() {
		return modal;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.MODAL
	 * @param modal  the value for IRP_CHNL_DOC_LINK.MODAL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setModal(Integer modal) {
		this.modal = modal;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCRELTIME
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCRELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Date getDocreltime() {
		return docreltime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCRELTIME
	 * @param docreltime  the value for IRP_CHNL_DOC_LINK.DOCRELTIME
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocreltime(Date docreltime) {
		this.docreltime = docreltime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCCHANNEL
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCCHANNEL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocchannel() {
		return docchannel;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCCHANNEL
	 * @param docchannel  the value for IRP_CHNL_DOC_LINK.DOCCHANNEL
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocchannel(Long docchannel) {
		this.docchannel = docchannel;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCFLAG
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCFLAG
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Integer getDocflag() {
		return docflag;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCFLAG
	 * @param docflag  the value for IRP_CHNL_DOC_LINK.DOCFLAG
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocflag(Integer docflag) {
		this.docflag = docflag;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCKIND
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCKIND
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDockind() {
		return dockind;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCKIND
	 * @param dockind  the value for IRP_CHNL_DOC_LINK.DOCKIND
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDockind(Long dockind) {
		this.dockind = dockind;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.SITEID
	 * @return  the value of IRP_CHNL_DOC_LINK.SITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getSiteid() {
		return siteid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.SITEID
	 * @param siteid  the value for IRP_CHNL_DOC_LINK.SITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setSiteid(Long siteid) {
		this.siteid = siteid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.SRCSITEID
	 * @return  the value of IRP_CHNL_DOC_LINK.SRCSITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getSrcsiteid() {
		return srcsiteid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.SRCSITEID
	 * @param srcsiteid  the value for IRP_CHNL_DOC_LINK.SRCSITEID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setSrcsiteid(Long srcsiteid) {
		this.srcsiteid = srcsiteid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.LIFESTATUS
	 * @return  the value of IRP_CHNL_DOC_LINK.LIFESTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getLifestatus() {
		return lifestatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.LIFESTATUS
	 * @param lifestatus  the value for IRP_CHNL_DOC_LINK.LIFESTATUS
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setLifestatus(Long lifestatus) {
		this.lifestatus = lifestatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCCRUSER
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCCRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public String getDoccruser() {
		return doccruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCCRUSER
	 * @param doccruser  the value for IRP_CHNL_DOC_LINK.DOCCRUSER
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDoccruser(String doccruser) {
		this.doccruser = doccruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.HITSCOUNT
	 * @return  the value of IRP_CHNL_DOC_LINK.HITSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getHitscount() {
		return hitscount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.HITSCOUNT
	 * @param hitscount  the value for IRP_CHNL_DOC_LINK.HITSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setHitscount(Long hitscount) {
		this.hitscount = hitscount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.COMMENTCOUNT
	 * @return  the value of IRP_CHNL_DOC_LINK.COMMENTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getCommentcount() {
		return commentcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.COMMENTCOUNT
	 * @param commentcount  the value for IRP_CHNL_DOC_LINK.COMMENTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCommentcount(Long commentcount) {
		this.commentcount = commentcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.COLLECTCOUNT
	 * @return  the value of IRP_CHNL_DOC_LINK.COLLECTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getCollectcount() {
		return collectcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.COLLECTCOUNT
	 * @param collectcount  the value for IRP_CHNL_DOC_LINK.COLLECTCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCollectcount(Long collectcount) {
		this.collectcount = collectcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CRUSERID
	 * @return  the value of IRP_CHNL_DOC_LINK.CRUSERID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CRUSERID
	 * @param cruserid  the value for IRP_CHNL_DOC_LINK.CRUSERID
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.COMPLETESCORE
	 * @return  the value of IRP_CHNL_DOC_LINK.COMPLETESCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getCompletescore() {
		return completescore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.COMPLETESCORE
	 * @param completescore  the value for IRP_CHNL_DOC_LINK.COMPLETESCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCompletescore(Long completescore) {
		this.completescore = completescore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.CRYSCORE
	 * @return  the value of IRP_CHNL_DOC_LINK.CRYSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getCryscore() {
		return cryscore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.CRYSCORE
	 * @param cryscore  the value for IRP_CHNL_DOC_LINK.CRYSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setCryscore(Long cryscore) {
		this.cryscore = cryscore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.ALLSCORE
	 * @return  the value of IRP_CHNL_DOC_LINK.ALLSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getAllscore() {
		return allscore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.ALLSCORE
	 * @param allscore  the value for IRP_CHNL_DOC_LINK.ALLSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setAllscore(Long allscore) {
		this.allscore = allscore;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.TRANSCOUNT
	 * @return  the value of IRP_CHNL_DOC_LINK.TRANSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getTranscount() {
		return transcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.TRANSCOUNT
	 * @param transcount  the value for IRP_CHNL_DOC_LINK.TRANSCOUNT
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setTranscount(Long transcount) {
		this.transcount = transcount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.INFORMTYPE
	 * @return  the value of IRP_CHNL_DOC_LINK.INFORMTYPE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getInformtype() {
		return informtype;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.INFORMTYPE
	 * @param informtype  the value for IRP_CHNL_DOC_LINK.INFORMTYPE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setInformtype(Long informtype) {
		this.informtype = informtype;
	}
	
	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_CHNL_DOC_LINK.DOCSCORE
	 * @return  the value of IRP_CHNL_DOC_LINK.DOCSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public Long getDocscore() {
		return docscore;
	}
	
	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_CHNL_DOC_LINK.DOCSCORE
	 * @param informtype  the value for IRP_CHNL_DOC_LINK.DOCSCORE
	 * @ibatorgenerated  Fri Sep 27 15:45:21 CST 2013
	 */
	public void setDocscore(Long docscore) {
		this.docscore = docscore;
	}
	
	/**
     *  对象表名
     */
	public final static String TABLE_NAME = "IRP_CHNL_DOC_LINK";
	/**
	 * 发布的状态�?�?0
	 */
	public final static Long DOC_STATUS_PUBLISH=10L;	
	/**
	 * 草稿状�?�?�?
	 */
	public final static Long DOC_STATUS_CAOGAO=1L;
	/**
     * 主键字段名称
     */
	public final static String ID_FIELD_NAME = "CHNLDOCID";
	/**
	 * 附件名称（当前文档的封面�?
	 */
	private String documentFileName;
	
    public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	@Override
	public Long getId() {
		return this.chnldocid;
	}

	@Override
	public String getName() {
		return this.doctitle+"["+this.channelid+","+this.docid+","+this.siteid+"]";
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String getIdFieldName() {
		return ID_FIELD_NAME;
	}
    /**
     * 添加�?��栏目显示名称
     */
	private String chnldesc;
	public String getChnldesc() {
		return chnldesc;
	}

	public void setChnldesc(String chnldesc) {
		this.chnldesc = chnldesc;
	}
	/**
	 * 文档对象
	 */
	private IrpDocument document;
	public IrpDocument getDocument() {
		return document;
	}

	public void setDocument(IrpDocument document) {
		this.document = document;
	}
	 /**
	  * 收藏表对�?
	  */
	private IrpDocumentCollection irpDocumentCollection;

	public IrpDocumentCollection getIrpDocumentCollection() {
		return irpDocumentCollection;
	}

	public void setIrpDocumentCollection(IrpDocumentCollection irpDocumentCollection) {
		this.irpDocumentCollection = irpDocumentCollection;
	} 
	 /**
	  * 推荐专家集合
	  */
	private List<IrpUser> irpRoles;

	public List<IrpUser> getIrpRoles() {
		return irpRoles;
	}

	public void setIrpRoles(List<IrpUser> irpRoles) {
		this.irpRoles = irpRoles;
	}
}