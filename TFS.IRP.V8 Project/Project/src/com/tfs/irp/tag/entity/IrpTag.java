package com.tfs.irp.tag.entity;

import com.tfs.irp.base.IrpBaseObj;

public class IrpTag extends IrpBaseObj{
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_tag.TAGID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    private Long tagid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_tag.TAGNAME
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    private String tagname;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_tag.NCOUNT
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    private Long ncount;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column irp_tag.TAGTYPEID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    private Long tagtypeid;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_tag.TAGID
     *
     * @return the value of irp_tag.TAGID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public Long getTagid() {
        return tagid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_tag.TAGID
     *
     * @param tagid the value for irp_tag.TAGID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void setTagid(Long tagid) {
        this.tagid = tagid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_tag.TAGNAME
     *
     * @return the value of irp_tag.TAGNAME
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public String getTagname() {
        return tagname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_tag.TAGNAME
     *
     * @param tagname the value for irp_tag.TAGNAME
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_tag.NCOUNT
     *
     * @return the value of irp_tag.NCOUNT
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public Long getNcount() {
        return ncount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_tag.NCOUNT
     *
     * @param ncount the value for irp_tag.NCOUNT
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void setNcount(Long ncount) {
        this.ncount = ncount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column irp_tag.TAGTYPEID
     *
     * @return the value of irp_tag.TAGTYPEID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public Long getTagtypeid() {
        return tagtypeid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column irp_tag.TAGTYPEID
     *
     * @param tagtypeid the value for irp_tag.TAGTYPEID
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void setTagtypeid(Long tagtypeid) {
        this.tagtypeid = tagtypeid;
    }

    /** 对象表名 **/
	public final static String TABLE_NAME = "IRP_TAG";
	
	/** 主键字段名 **/
    public final static String ID_FIELD_NAME = "TAGID";

	@Override
	public Long getId() {
		return tagid;
	}

	@Override
	public String getName() {
		return tagname;
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