package com.tfs.irp.solr.entity;

import java.util.Date;

public class QuestionForSolr {

	private Long QUESTIONID;
	
	private Long CRUSERID;
	
	private String TITLE;
	
	private String TEXTCONTENT;
	
	private Long PARENTID;
	
	private String CRUSER;
	
	private Date CRTIME;

	public Long getQUESTIONID() {
		return QUESTIONID;
	}

	public void setQUESTIONID(Long qUESTIONID) {
		QUESTIONID = qUESTIONID;
	}

	public Long getCRUSERID() {
		return CRUSERID;
	}

	public void setCRUSERID(Long cRUSERID) {
		CRUSERID = cRUSERID;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getTEXTCONTENT() {
		return TEXTCONTENT;
	}

	public void setTEXTCONTENT(String tEXTCONTENT) {
		TEXTCONTENT = tEXTCONTENT;
	}

	public Long getPARENTID() {
		return PARENTID;
	}

	public void setPARENTID(Long pARENTID) {
		PARENTID = pARENTID;
	}

	public String getCRUSER() {
		return CRUSER;
	}

	public void setCRUSER(String cRUSER) {
		CRUSER = cRUSER;
	}

	public Date getCRTIME() {
		return CRTIME;
	}

	public void setCRTIME(Date cRTIME) {
		CRTIME = cRTIME;
	}
	
	
}
