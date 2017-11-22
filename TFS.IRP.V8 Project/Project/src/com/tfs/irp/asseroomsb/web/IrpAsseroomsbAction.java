package com.tfs.irp.asseroomsb.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample.Criteria;
import com.tfs.irp.asseroomsb.service.IrpAsseroomsbService;
import com.tfs.irp.asseroomsblink.service.IrpAsseroomsblinkService;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.systimdask.service.systemDocToDocTime;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;


public class IrpAsseroomsbAction extends ActionSupport {
	private IrpAsseroomsb asseroomsb;
	private IrpAsseroomsbService asseroomsbService;
	private IrpAsseroomsblinkService asseroomsblinkService;
	/*检索*/
	private String searchWord;
	private String searchType;
	/*
	 * 多选删除
	 */
	private String asseroomsbids;
	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";
	/**
	 * 存放IrpAsseroomsb list集合
	 */
	private List<IrpAsseroomsb> asseroomsbList;
	/**
	 * 存放设备的id
	 */
	private Long asseroomsbid;
	/**
	 * 
	 * 更新检索验证
	 */
	private String ck_sbnametwo;
	/**
	 * 存放设备名称
	 */
	private String ck_sbname;
	
	
	public Long getAsseroomsbid() {
		return asseroomsbid;
	}
	public void setAsseroomsbid(Long asseroomsbid) {
		this.asseroomsbid = asseroomsbid;
	}
	public String getCk_sbnametwo() {
		return ck_sbnametwo;
	}
	public void setCk_sbnametwo(String ck_sbnametwo) {
		this.ck_sbnametwo = ck_sbnametwo;
	}
	public String getCk_sbname() {
		return ck_sbname;
	}
	public void setCk_sbname(String ck_sbname) {
		this.ck_sbname = ck_sbname;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getAsseroomsbids() {
		return asseroomsbids;
	}
	public void setAsseroomsbids(String asseroomsbids) {
		this.asseroomsbids = asseroomsbids;
	}
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public List<IrpAsseroomsb> getAsseroomsbList() {
		return asseroomsbList;
	}
	public void setAsseroomsbList(List<IrpAsseroomsb> asseroomsbList) {
		this.asseroomsbList = asseroomsbList;
	}
	public IrpAsseroomsblinkService getAsseroomsblinkService() {
		return asseroomsblinkService;
	}
	public void setAsseroomsblinkService(
			IrpAsseroomsblinkService asseroomsblinkService) {
		this.asseroomsblinkService = asseroomsblinkService;
	}
	public IrpAsseroomsb getAsseroomsb() {
		return asseroomsb;
	}
	public void setAsseroomsb(IrpAsseroomsb asseroomsb) {
		this.asseroomsb = asseroomsb;
	}
	public IrpAsseroomsbService getAsseroomsbService() {
		return asseroomsbService;
	}
	public void setAsseroomsbService(IrpAsseroomsbService asseroomsbService) {
		this.asseroomsbService = asseroomsbService;
	}
	public String findAll() throws Exception {
	IrpAsseroomsbExample example=new IrpAsseroomsbExample();
	Criteria criteria=example.createCriteria();
	String _oOrderby = "";
	if (this.orderField == null || this.orderField.equals("")) {
		_oOrderby = "asseroomsbid desc";
	} else {
		_oOrderby = this.orderField + " " + this.orderBy;
	}
	if(searchType!=null&&!"".equals(searchType)){
		if("all".equals(searchType)){
			example.or(criteria.andAsseroomsbnameLike("%"+searchWord+"%"));
			example.or(criteria.andAsseroomsbtypeLike("%"+searchWord+"%"));
		} else if("asseroomsbname".equals(searchType)){
			criteria.andAsseroomsbnameLike("%"+searchWord+"%");
		} else if("asseroomsbtype".equals(searchType)){
			criteria.andAsseroomsbtypeLike("%"+searchWord+"%");
		}
	}
	example.setOrderByClause(_oOrderby);
	//按条件查询数据的总记录条数
	int count =asseroomsbService.getDataCount(example);
	PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
			count);
	asseroomsbList=asseroomsbService.querySbForPage(example, pageUtil);
	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
	return SUCCESS;
	}
	/**
	 * 添加设备
	 * @throws Exception
	 */
	public void addSb() {
		Long _cataStatus = 0L;
		 LogUtil logUtil=new LogUtil("ASSEROOMSB_ADD",asseroomsb);
		try {
			_cataStatus = asseroomsbService.addSb(asseroomsb);
			logUtil.successLogs( "添加会议室设备["+logUtil.getLogUser()+"]成功");
		} catch (Exception e) {
			e.printStackTrace();
			logUtil.errorLogs( "添加会议室设备["+logUtil.getLogUser()+"]失败",e);
		}
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 更新设备
	 * @throws Exception
	 */
	public void updateSb() {
		IrpAsseroomsbExample example =new IrpAsseroomsbExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomsbidEqualTo(asseroomsbid);
		asseroomsb.setAsseroomsbid(asseroomsbid);
		 LogUtil logUtil=new LogUtil("ASSEROOMSB_UPDATE",asseroomsb);
		int _cataStatus = 0;
		try {
			_cataStatus = asseroomsbService.updateByExampleSelective(asseroomsb, example);
			logUtil.successLogs( "修改会议室设备["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "修改会议室设备["+logUtil.getLogUser()+"]失败",e);
		}
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 查询设备
	 * @throws Exception
	 */
	public String  sbFrom() throws Exception {
		asseroomsb=asseroomsbService.selectByPrimaryKey(asseroomsbid);
		return "success";
	}
	/**
	 * 删除设备
	 * @throws Exception
	 */
	public void deleteSb() {
		IrpAsseroomsbExample example =new IrpAsseroomsbExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomsbidEqualTo(asseroomsbid);
		int _cataStatus = 0;
		 LogUtil logUtil=null;
		try {
			asseroomsb=asseroomsbService.selectByPrimaryKey(asseroomsbid);
			logUtil=new LogUtil("ASSEROOMSB_DEL",asseroomsb);
			_cataStatus = asseroomsbService.deleteByExample(example);
			asseroomsblinkService.deletebyAsseroomsbid(asseroomsbid);
			logUtil.successLogs( "删除会议室设备["+logUtil.getLogUser()+"]成功");
		} catch (Exception e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除会议室设备["+logUtil.getLogUser()+"]失败",e);
		}
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 删除多个设备
	 * @throws Exception
	 */
	public void deleteAll() {
		List<Long > ids=new ArrayList<Long>();
		LogUtil logUtil=null;
		String[] idsStrings=asseroomsbids.split(",");
		for (int i = 0; i < idsStrings.length; i++) {
			if(!"".equals(idsStrings[i])){
				ids.add(Long.parseLong(idsStrings[i].trim()));
			}
		}
		if(ids.size()>0){
			try {
				asseroomsb=asseroomsbService.selectByPrimaryKey(ids.get(0));
				logUtil=new LogUtil("ASSEROOMSB_DEL",asseroomsb);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IrpAsseroomsbExample example =new IrpAsseroomsbExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomsbidIn(ids);
		int _cataStatus=0;
		try {
			 _cataStatus=asseroomsbService.deleteByExample(example);
			asseroomsblinkService.deletebyAsseroomsbidList(ids);
			if(logUtil!=null){
				logUtil.successLogs( "删除会议室设备"+ids.toString()+"["+logUtil.getLogUser()+"]成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(logUtil!=null){
				logUtil.errorLogs( "删除会议室设备"+ids.toString()+"["+logUtil.getLogUser()+"]失败",e);
			}
		}
		ActionUtil.writer(_cataStatus + "");
	}
	
}
