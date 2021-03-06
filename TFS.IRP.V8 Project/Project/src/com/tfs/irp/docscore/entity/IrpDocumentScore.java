package com.tfs.irp.docscore.entity;

import java.util.Date;

public class IrpDocumentScore {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOCUMENT_SCORE.DOCID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    private Long docid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOCUMENT_SCORE.CRTIME
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    private Date crtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOCUMENT_SCORE.USERID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    private Long userid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_DOCUMENT_SCORE.SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    private Long score;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOCUMENT_SCORE.DOCID
     *
     * @return the value of IRP_DOCUMENT_SCORE.DOCID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public Long getDocid() {
        return docid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOCUMENT_SCORE.DOCID
     *
     * @param docid the value for IRP_DOCUMENT_SCORE.DOCID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void setDocid(Long docid) {
        this.docid = docid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOCUMENT_SCORE.CRTIME
     *
     * @return the value of IRP_DOCUMENT_SCORE.CRTIME
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public Date getCrtime() {
        return crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOCUMENT_SCORE.CRTIME
     *
     * @param crtime the value for IRP_DOCUMENT_SCORE.CRTIME
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOCUMENT_SCORE.USERID
     *
     * @return the value of IRP_DOCUMENT_SCORE.USERID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOCUMENT_SCORE.USERID
     *
     * @param userid the value for IRP_DOCUMENT_SCORE.USERID
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_DOCUMENT_SCORE.SCORE
     *
     * @return the value of IRP_DOCUMENT_SCORE.SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public Long getScore() {
        return score;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_DOCUMENT_SCORE.SCORE
     *
     * @param score the value for IRP_DOCUMENT_SCORE.SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void setScore(Long score) {
        this.score = score;
    }
}