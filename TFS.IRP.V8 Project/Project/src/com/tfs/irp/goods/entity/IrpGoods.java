package com.tfs.irp.goods.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;


public class IrpGoods extends IrpBaseObj {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSID
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long goodsid;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSNO
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private String goodsno;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSNAME
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private String goodsname;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSDESC
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private String goodsdesc;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSIMAGE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private String goodsimage;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.NEEDSCORE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long needscore;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.PRICE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long price;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.STOCKNUM
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long stocknum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.COVERSTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long coverstate;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.SALESTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Long salestate;
    
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_USER_COVERT_GOODS.COVERTTIME
     *
     * @abatorgenerated Wed Apr 12 17:15:45 CST 2017
     */
    private Date crtime;
    
    private Integer reorder;
    
    private Long medalid;
    private String medalname;
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSID
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private Integer salesvolume;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column IRP_GOODS.GOODSNO
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private String contentdetial;
    
	public Integer getSalesvolume() {
		return salesvolume;
	}

	public void setSalesvolume(Integer salesvolume) {
		this.salesvolume = salesvolume;
	}

	public String getContentdetial() {
		return contentdetial;
	}

	public void setContentdetial(String contentdetial) {
		this.contentdetial = contentdetial;
	}

	public Long getMedalid() {
		return medalid;
	}

	public void setMedalid(Long medalid) {
		this.medalid = medalid;
	}

	public String getMedalname() {
		return medalname;
	}

	public void setMedalname(String medalname) {
		this.medalname = medalname;
	}

	public Integer getReorder() {
		return reorder;
	}

	public void setReorder(Integer reorder) {
		this.reorder = reorder;
	}

	public Date getCrtime() {
		return crtime;
	}

	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.GOODSID
     *
     * @return the value of IRP_GOODS.GOODSID
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getGoodsid() {
        return goodsid;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.GOODSID
     *
     * @param goodsid the value for IRP_GOODS.GOODSID
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.GOODSNO
     *
     * @return the value of IRP_GOODS.GOODSNO
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public String getGoodsno() {
        return goodsno;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.GOODSNO
     *
     * @param goodsno the value for IRP_GOODS.GOODSNO
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.GOODSNAME
     *
     * @return the value of IRP_GOODS.GOODSNAME
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public String getGoodsname() {
        return goodsname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.GOODSNAME
     *
     * @param goodsname the value for IRP_GOODS.GOODSNAME
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.GOODSDESC
     *
     * @return the value of IRP_GOODS.GOODSDESC
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public String getGoodsdesc() {
        return goodsdesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.GOODSDESC
     *
     * @param goodsdesc the value for IRP_GOODS.GOODSDESC
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setGoodsdesc(String goodsdesc) {
        this.goodsdesc = goodsdesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.GOODSIMAGE
     *
     * @return the value of IRP_GOODS.GOODSIMAGE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public String getGoodsimage() {
        return goodsimage;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.GOODSIMAGE
     *
     * @param goodsimage the value for IRP_GOODS.GOODSIMAGE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setGoodsimage(String goodsimage) {
        this.goodsimage = goodsimage;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.NEEDSCORE
     *
     * @return the value of IRP_GOODS.NEEDSCORE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getNeedscore() {
        return needscore;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.NEEDSCORE
     *
     * @param needscore the value for IRP_GOODS.NEEDSCORE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setNeedscore(Long needscore) {
        this.needscore = needscore;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.PRICE
     *
     * @return the value of IRP_GOODS.PRICE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getPrice() {
        return price;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.PRICE
     *
     * @param price the value for IRP_GOODS.PRICE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.STOCKNUM
     *
     * @return the value of IRP_GOODS.STOCKNUM
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getStocknum() {
        return stocknum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.STOCKNUM
     *
     * @param stocknum the value for IRP_GOODS.STOCKNUM
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setStocknum(Long stocknum) {
        this.stocknum = stocknum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.COVERSTATE
     *
     * @return the value of IRP_GOODS.COVERSTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getCoverstate() {
        return coverstate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.COVERSTATE
     *
     * @param coverstate the value for IRP_GOODS.COVERSTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setCoverstate(Long coverstate) {
        this.coverstate = coverstate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column IRP_GOODS.SALESTATE
     *
     * @return the value of IRP_GOODS.SALESTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public Long getSalestate() {
        return salestate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column IRP_GOODS.SALESTATE
     *
     * @param salestate the value for IRP_GOODS.SALESTATE
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void setSalestate(Long salestate) {
        this.salestate = salestate;
    }
    /**
     * 已上架
     */
    public  static final Long SALE=20l;
    /**
     * 未上架
     */
    public  static final Long NOSALE=10l;
    /**
     * 积分兑换
     */
    public  static final Long COVERT=20l;
    /**
     * 非积分兑换
     */
    public  static final Long NOCOVERT=10l;
    /**
     *  对象表名
     */
	public final static String TABLE_NAME = "IRP_GOODS";
	
	/**
     * 主键字段名称
     */
	public final static String ID_FIELD_NAME = "GOODSID";
	public final static Long SALE_STATE=20l;
	
	
	public static Long getSaleState() {
		return SALE_STATE;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	@Override
	public String getIdFieldName() {
		return ID_FIELD_NAME;
	}

	@Override
	public Long getId() {
		return goodsid;
	}

	@Override
	public String getName() {
		return goodsname;
	}

	public static Long getSale() {
		return SALE;
	}

	public static Long getNosale() {
		return NOSALE;
	}

	public static Long getCovert() {
		return COVERT;
	}

	public static Long getNocovert() {
		return NOCOVERT;
	}
	
	
}