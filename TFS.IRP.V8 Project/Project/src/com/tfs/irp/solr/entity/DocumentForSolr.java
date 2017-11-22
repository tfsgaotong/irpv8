package com.tfs.irp.solr.entity;

import java.util.Date;

public class DocumentForSolr {

	private Long DOCID;
	
	private String DOCTITLE;
	
	private String DOCCONTENT;
	
	private String DOCKEYWORDS;
	
	private Date CRTIME;
	
	private String CRUSER;
	
	private Long CRUSERID;
	
	private Long SITEID;
	
	private Long CHANNELID;

	public Long getCHANNELID() {
		return CHANNELID;
	}

	public void setCHANNELID(Long cHANNELID) {
		CHANNELID = cHANNELID;
	}

	public Long getSITEID() {
		return SITEID;
	}

	public void setSITEID(Long sITEID) {
		SITEID = sITEID;
	}

	public Long getDOCID() {
		return DOCID;
	}

	public void setDOCID(Long dOCID) {
		DOCID = dOCID;
	}

	public String getDOCTITLE() {
		return DOCTITLE;
	}

	public void setDOCTITLE(String dOCTITLE) {
		DOCTITLE = dOCTITLE;
	}

	public String getDOCCONTENT() {
		return DOCCONTENT;
	}

	public void setDOCCONTENT(String dOCCONTENT) {
		DOCCONTENT = dOCCONTENT;
	}

	public String getDOCKEYWORDS() {
		return DOCKEYWORDS;
	}

	public void setDOCKEYWORDS(String dOCKEYWORDS) {
		DOCKEYWORDS = dOCKEYWORDS;
	}

	public Date getCRTIME() {
		return CRTIME;
	}

	public void setCRTIME(Date cRTIME) {
		CRTIME = cRTIME;
	}

	public String getCRUSER() {
		return CRUSER;
	}

	public void setCRUSER(String cRUSER) {
		CRUSER = cRUSER;
	}

	public Long getCRUSERID() {
		return CRUSERID;
	}

	public void setCRUSERID(Long cRUSERID) {
		CRUSERID = cRUSERID;
	}
	
	
}
