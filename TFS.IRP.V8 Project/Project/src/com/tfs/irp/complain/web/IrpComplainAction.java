package com.tfs.irp.complain.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.complain.entity.IrpComplain;
import com.tfs.irp.complain.service.IrpComplainService;
import com.tfs.irp.util.ExcelUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

@SuppressWarnings("serial")
public class IrpComplainAction extends ActionSupport {

	private IrpComplainService irpComplainService;

	private IrpComplain irpComplain;
	
	private String message;
	
	private List<IrpComplain> irpComplains;
	
	private IrpComplain complain;
	
	private String complainid;
	
	private String informtypeidall;
	/**
	 * 分页排序检索
	 */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private String orderField = "";
	private String orderBy = "";
	private String searchWord;
	private String searchType;
    private List<Map> listmap;
	
	public List<Map> getListmap() {
		return listmap;
	}
	public void setListmap(List<Map> listmap) {
		this.listmap = listmap;
	}
	public String getInformtypeidall() {
		return informtypeidall;
	}
	public void setInformtypeidall(String informtypeidall) {
		this.informtypeidall = informtypeidall;
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
	
	
	public List<IrpComplain> getIrpComplains() {
		return irpComplains;
	}
	public void setIrpComplains(List<IrpComplain> irpComplains) {
		this.irpComplains = irpComplains;
	}
	public IrpComplain getComplain() {
		return complain;
	}
	public void setComplain(IrpComplain complain) {
		this.complain = complain;
	}
	public String getComplainid() {
		return complainid;
	}
	public void setComplainid(String complainid) {
		this.complainid = complainid;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IrpComplain getIrpComplain() {
		return irpComplain;
	}

	public void setIrpComplain(IrpComplain irpComplain) {
		this.irpComplain = irpComplain;
	}

	public IrpComplainService getIrpComplainService() {
		return irpComplainService;
	}

	public void setIrpComplainService(IrpComplainService irpComplainService) {
		this.irpComplainService = irpComplainService;
	}

	/**
	 * 添加意见反馈到数据库
	 * @return
	 * @throws Exception
	 */
	public String addComplain() throws Exception {
		// 添加意见反馈
		this.irpComplain.setComplaindesc(new String(this.irpComplain.getComplaindesc().getBytes("ISO-8859-1"), "UTF-8"));
		this.irpComplain.setAnsques((short)IrpComplain.COMPLAIN_STATUS_SUGGESTION);
		this.irpComplain.setCreatuser(LoginUtil.getLoginUser().getUsername());
		this.irpComplain.setParentid(0L);
		this.irpComplain.setCreattime(new Date());
		this.setMessage("添加成功,谢谢您的支持");
		irpComplainService.saveComplain(irpComplain);
		return SUCCESS;
	};
	
	/**
	 * 查询所有意见反馈
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findAllComplain() throws Exception {
		//分页显示所有反馈类型
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "complaintypeid asc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (this.searchWord == null) {
			searchWord = "";
		}
		List<Short> sids=new ArrayList<Short>();
		//意见和答复都查
		sids.add(IrpComplain.COMPLAIN_STATUS_SUGGESTION);
		sids.add(IrpComplain.COMPLAIN_STATUS_REPLY);
		int typesize=irpComplainService.findAllComplainBystatuscount(searchWord, sids);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.getPageSize(),typesize);
		listmap=(List<Map>) irpComplainService.findAllComplainBystatus(pageUtil, _oOrderby, searchWord, sids);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 意见反馈导出成excel
	 * @return
	 * @throws Exception
	 */
	public String excelComplain() throws Exception {
		String path = "D:"; // 产生的Excel文件的名称
		//List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "complaintypeid asc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (this.searchWord == null) {
			searchWord = "";
		}
		List<Short> sids=new ArrayList<Short>();
		//意见和答复都查
		sids.add(IrpComplain.COMPLAIN_STATUS_SUGGESTION);
		sids.add(IrpComplain.COMPLAIN_STATUS_REPLY);
		int typesize=irpComplainService.findAllComplainBystatuscount(searchWord, sids);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.getPageSize(),typesize);
		listmap=(List<Map>) irpComplainService.findAllComplainBystatus(null, _oOrderby, searchWord, sids);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		ExcelUtil.createxlsUtil(listmap);
		this.setMessage(listmap.size()+"");
		return SUCCESS;
	}
}
