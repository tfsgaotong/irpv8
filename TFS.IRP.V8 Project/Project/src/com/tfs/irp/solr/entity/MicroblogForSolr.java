package com.tfs.irp.solr.entity;

import java.util.Date;

public class MicroblogForSolr {
	
	private Long MICROBLOGID;
	
	private Long USERID;
	
	private Date CRTIME;
	
	private String FROMDATA;
	
	private String MICROBLOGCONTENT;
	
	public Long getMICROBLOGID() {
		return MICROBLOGID;
	}

	public void setMICROBLOGID(Long mICROBLOGID) {
		MICROBLOGID = mICROBLOGID;
	}

	public Long getUSERID() {
		return USERID;
	}

	public void setUSERID(Long uSERID) {
		USERID = uSERID;
	}

	public Date getCRTIME() {
		return CRTIME;
	}

	public void setCRTIME(Date cRTIME) {
		CRTIME = cRTIME;
	}

	public String getFROMDATA() {
		return FROMDATA;
	}

	public void setFROMDATA(String fROMDATA) {
		FROMDATA = fROMDATA;
	}

	public String getMICROBLOGCONTENT() {
		return MICROBLOGCONTENT;
	}

	public void setMICROBLOGCONTENT(String mICROBLOGCONTENT) {
		MICROBLOGCONTENT = mICROBLOGCONTENT;
	}


}
