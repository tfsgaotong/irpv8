package com.tfs.irp.vote.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpVote extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTEID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Long voteid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.TITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String title;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.DESCRIPTION
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String description;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTETITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String votetitle;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.CHECKTYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Long checktype;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTEPARENTID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Long voteparentid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.CRUSERID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Long cruserid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.COUNT
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Long count;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.CRTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Date crtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.ENDTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Date endtime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.ATTIMG
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String attimg;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.RESULTSHOW
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Short resultshow;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTEUSER
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String voteuser;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTEGROUP
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private String votegroup;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.STARTTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Date starttime;

   
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.VOTETYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Integer votetype;

    //1  未发布  2 已发布
    private Integer ispublish;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column IRP_VOTE.LESSCHECK
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    private Integer lesscheck;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTEID
     *
     * @return the value of IRP_VOTE.VOTEID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Long getVoteid() {
        return voteid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTEID
     *
     * @param voteid the value for IRP_VOTE.VOTEID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVoteid(Long voteid) {
        this.voteid = voteid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.TITLE
     *
     * @return the value of IRP_VOTE.TITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.TITLE
     *
     * @param title the value for IRP_VOTE.TITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.DESCRIPTION
     *
     * @return the value of IRP_VOTE.DESCRIPTION
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.DESCRIPTION
     *
     * @param description the value for IRP_VOTE.DESCRIPTION
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTETITLE
     *
     * @return the value of IRP_VOTE.VOTETITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getVotetitle() {
        return votetitle;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTETITLE
     *
     * @param votetitle the value for IRP_VOTE.VOTETITLE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVotetitle(String votetitle) {
        this.votetitle = votetitle;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.CHECKTYPE
     *
     * @return the value of IRP_VOTE.CHECKTYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Long getChecktype() {
        return checktype;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.CHECKTYPE
     *
     * @param checktype the value for IRP_VOTE.CHECKTYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setChecktype(Long checktype) {
        this.checktype = checktype;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTEPARENTID
     *
     * @return the value of IRP_VOTE.VOTEPARENTID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Long getVoteparentid() {
        return voteparentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTEPARENTID
     *
     * @param voteparentid the value for IRP_VOTE.VOTEPARENTID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVoteparentid(Long voteparentid) {
        this.voteparentid = voteparentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.CRUSERID
     *
     * @return the value of IRP_VOTE.CRUSERID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Long getCruserid() {
        return cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.CRUSERID
     *
     * @param cruserid the value for IRP_VOTE.CRUSERID
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setCruserid(Long cruserid) {
        this.cruserid = cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.COUNT
     *
     * @return the value of IRP_VOTE.COUNT
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Long getCount() {
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.COUNT
     *
     * @param count the value for IRP_VOTE.COUNT
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.CRTIME
     *
     * @return the value of IRP_VOTE.CRTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Date getCrtime() {
        return crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.CRTIME
     *
     * @param crtime the value for IRP_VOTE.CRTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.ENDTIME
     *
     * @return the value of IRP_VOTE.ENDTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.ENDTIME
     *
     * @param endtime the value for IRP_VOTE.ENDTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.ATTIMG
     *
     * @return the value of IRP_VOTE.ATTIMG
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getAttimg() {
        return attimg;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.ATTIMG
     *
     * @param attimg the value for IRP_VOTE.ATTIMG
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setAttimg(String attimg) {
        this.attimg = attimg;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.RESULTSHOW
     *
     * @return the value of IRP_VOTE.RESULTSHOW
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Short getResultshow() {
        return resultshow;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.RESULTSHOW
     *
     * @param resultshow the value for IRP_VOTE.RESULTSHOW
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setResultshow(Short resultshow) {
        this.resultshow = resultshow;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTEUSER
     *
     * @return the value of IRP_VOTE.VOTEUSER
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getVoteuser() {
        return voteuser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTEUSER
     *
     * @param voteuser the value for IRP_VOTE.VOTEUSER
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVoteuser(String voteuser) {
        this.voteuser = voteuser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTEGROUP
     *
     * @return the value of IRP_VOTE.VOTEGROUP
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public String getVotegroup() {
        return votegroup;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTEGROUP
     *
     * @param votegroup the value for IRP_VOTE.VOTEGROUP
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVotegroup(String votegroup) {
        this.votegroup = votegroup;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.STARTTIME
     *
     * @return the value of IRP_VOTE.STARTTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.STARTTIME
     *
     * @param starttime the value for IRP_VOTE.STARTTIME
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.VOTETYPE
     *
     * @return the value of IRP_VOTE.VOTETYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Integer getVotetype() {
        return votetype;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.VOTETYPE
     *
     * @param votetype the value for IRP_VOTE.VOTETYPE
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setVotetype(Integer votetype) {
        this.votetype = votetype;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.ISPUBLISH
     *
     * @return the value of IRP_VOTE.ISPUBLISH
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Integer getIspublish() {
        return ispublish;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.ISPUBLISH
     *
     * @param ispublish the value for IRP_VOTE.ISPUBLISH
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setIspublish(Integer ispublish) {
        this.ispublish = ispublish;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column IRP_VOTE.LESSCHECK
     *
     * @return the value of IRP_VOTE.LESSCHECK
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public Integer getLesscheck() {
        return lesscheck;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column IRP_VOTE.LESSCHECK
     *
     * @param lesscheck the value for IRP_VOTE.LESSCHECK
     *
     * @ibatorgenerated Thu Nov 28 09:43:05 CST 2013
     */
    public void setLesscheck(Integer lesscheck) {
        this.lesscheck = lesscheck;
    }
    
    private Integer morecheck;
    
    
    
    public Integer getMorecheck() {
		return morecheck;
	}

	public void setMorecheck(Integer morecheck) {
		this.morecheck = morecheck;
	}

	/** 对象表名 **/
    public final static String TABLE_NAME = "IRP_VOTE";
    
    /** 主键字段�?**/
    public final static String ID_FIELD_NAME = "VOTEID";
    
    public final static int VOTETYPE_WORD=1;
    public final static int VOTETYPE_PIC=2;
    public final static int VOTETYPE_URL=3;
    /** 未发布**/
    public final static int ISPUBLISH_NO=1;
    /** 已发布**/
    public final static int ISPUBLISH_YES=2;
    /** 已删除**/
    public final static int ISPUBLISH_DEL=3;

	@Override
	public Long getId() {
		return this.voteid;
	}

	@Override
	public String getName() {
		return this.title;
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