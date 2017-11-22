package com.tfs.irp.category_file_link.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpCategoryFileLink extends IrpBaseObj {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_category_file_link.ID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    private Long categoryfileid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_category_file_link.CID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    private Long cid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_category_file_link.FID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    private Long fid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_category_file_link.CRUSERID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    private Long cruserid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_category_file_link.CRTIME
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    private Date crtime;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_category_file_link.ID
     *
     * @return the value of irp_category_file_link.ID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public Long getCategoryfileid() {
		return categoryfileid;
	}

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_category_file_link.ID
     *
     * @param id the value for irp_category_file_link.ID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public void setCategoryfileid(Long categoryfileid) {
		this.categoryfileid = categoryfileid;
	}

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_category_file_link.CID
     *
     * @return the value of irp_category_file_link.CID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public Long getCid() {
        return cid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_category_file_link.CID
     *
     * @param cid the value for irp_category_file_link.CID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_category_file_link.FID
     *
     * @return the value of irp_category_file_link.FID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public Long getFid() {
        return fid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_category_file_link.FID
     *
     * @param fid the value for irp_category_file_link.FID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public void setFid(Long fid) {
        this.fid = fid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_category_file_link.CRUSERID
     *
     * @return the value of irp_category_file_link.CRUSERID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public Long getCruserid() {
        return cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_category_file_link.CRUSERID
     *
     * @param cruserid the value for irp_category_file_link.CRUSERID
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public void setCruserid(Long cruserid) {
        this.cruserid = cruserid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_category_file_link.CRTIME
     *
     * @return the value of irp_category_file_link.CRTIME
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public Date getCrtime() {
        return crtime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_category_file_link.CRTIME
     *
     * @param crtime the value for irp_category_file_link.CRTIME
     *
     * @ibatorgenerated Mon Mar 17 10:23:17 CST 2014
     */
    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    /** 对象表名 **/
    public final static String TABLE_NAME = "IRP_CATEGORY_FILE_LINK";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "CATEGORYFILEID";

	@Override
	public Long getId() {
		return this.categoryfileid;
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