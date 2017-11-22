package com.tfs.irp.examrecord.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.service.IrpExamService;
import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.examrecord.service.IrpExamRecordService;
import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.testpaper.service.IrpTestpaperService;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;

public class IrpExamRecordAction extends ActionSupport {
	
	private IrpExamRecordService irpExamRecordService;
	
	private String orderField = "";

	private String orderBy = "";
	
	private String pageHTML = "";
	
	private String searchWord  = "";
	
	private String searchType = "";
	
	private Integer pagenum = 1;
	
	private Long recordid;
	
	private String qtestpidstr;
	
	private IrpExamService irpExamService;
	
	private IrpUserService irpUserService;
	
	private IrpTestpaperService irpTestpaperService;
	
	private IrpCategoryService irpCategoryService;
	
	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	public IrpTestpaperService getIrpTestpaperService() {
		return irpTestpaperService;
	}

	public void setIrpTestpaperService(IrpTestpaperService irpTestpaperService) {
		this.irpTestpaperService = irpTestpaperService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpExamService getIrpExamService() {
		return irpExamService;
	}

	public void setIrpExamService(IrpExamService irpExamService) {
		this.irpExamService = irpExamService;
	}

	public String getQtestpidstr() {
		return qtestpidstr;
	}

	public void setQtestpidstr(String qtestpidstr) {
		this.qtestpidstr = qtestpidstr;
	}

	public Long getRecordid() {
		return recordid;
	}

	public void setRecordid(Long recordid) {
		this.recordid = recordid;
	}
	private List<IrpExamRecord> recordlist;

	public List<IrpExamRecord> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(List<IrpExamRecord> recordlist) {
		this.recordlist = recordlist;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
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

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public IrpExamRecordService getIrpExamRecordService() {
		return irpExamRecordService;
	}

	public void setIrpExamRecordService(IrpExamRecordService irpExamRecordService) {
		this.irpExamRecordService = irpExamRecordService;
	}
	
	/**
	 * 链接到成绩页面
	 * @return
	 */
	public String examMenuDiv(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "recordid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int num = this.irpExamRecordService.getExamRecordListnumMeun(null, null);
		
		PageUtil pageutil = new PageUtil(pagenum, 10, num);
		
		recordlist = this.irpExamRecordService.getExamRecordListMenu(pageutil, null,null, _oOrderby);
		
		this.pageHTML = pageutil.getPageHtml("pageExam(#PageNum#)");
		
		return SUCCESS;
	}
	/**
	 * 根据id删除记录
	 * @return
	 */
	public void deleteExamRecord(){
		int msg = 0;
		msg = this.irpExamRecordService.deleteExamRecord(recordid);
		ActionUtil.writer(msg+"");
	}
	/**
	 * 多选删除
	 * @return
	 */
	public void deleteManyERds(){
  	  int msg = 0;
  		if (qtestpidstr!=null && qtestpidstr.length()>0) {
  			String dtarray[] = qtestpidstr.split(",");
  			if (dtarray.length>0) {
  				for (int i = 0; i < dtarray.length; i++)
  					msg = this.irpExamRecordService.deleteExamRecord(Long.parseLong(dtarray[i]));
  				}
  			}

  		ActionUtil.writer(msg+"");		
		
		
	}
	/**
	 * 把结果发布到前台
	 * @return
	 */
	public void updateFaBuStatus(){
		int msg = 0;
		
		msg = this.irpExamRecordService.updateExamRecordStatus(recordid, IrpExamRecord.NORMALSTATUS);
		
		ActionUtil.writer(msg+"");
	}
	/**
	 * 多选发布
	 * @return
	 */
	public void fabuManysRecords(){
	  	  int msg = 0;
	  		if (qtestpidstr!=null && qtestpidstr.length()>0) {
	  			String dtarray[] = qtestpidstr.split(",");
	  			if (dtarray.length>0) {
	  				for (int i = 0; i < dtarray.length; i++)
	  					msg = this.irpExamRecordService.updateExamRecordStatus(Long.parseLong(dtarray[i]), IrpExamRecord.NORMALSTATUS);
	  				}
	  			}

	  		ActionUtil.writer(msg+"");	
		
	}
	/**
	 * 根据id获得考试对象
	 * @param _examid
	 * @return
	 */
	public IrpExam getIrpExamById(long _examid){
		IrpExam irpexam = null;
		
		irpexam = this.irpExamService.getIrpExamById(_examid);
		
		return irpexam;
	}
	 /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
    /**
     * 根据id获得卷子对象
     * @param _id
     * @return
     */
    public IrpTestpaper getIrpTestpaper(String _id){
    	IrpTestpaper itt = null;
    	
    	itt =  this.irpTestpaperService.getIrpTestpaper(Long.parseLong(_id));
    	
    	return itt;
    }
    /**
     * 
     * @param _cateid
     * @return
     */
    public IrpCategory getIrpCategoryService(Long _cateid){
    	
    	IrpCategory cate = null;
    	
    	cate = this.irpCategoryService.findCategoryByPrimaryKey(_cateid);
    	
    	return cate;
    }
    
    
}
