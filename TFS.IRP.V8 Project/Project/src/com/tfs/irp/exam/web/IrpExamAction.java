package com.tfs.irp.exam.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.service.IrpExamService;
import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.examrecord.service.IrpExamRecordService;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaper;
import com.tfs.irp.grouptestpaper.service.IrpGroupTestpaperService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.testpaper.service.IrpTestpaperService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

public class IrpExamAction extends ActionSupport {
	
	private IrpExamService irpExamService;
	
	private Long cateid;
	
	private String orderField = "";

	private String orderBy = "";
	
	private String pageHTML = "";
	
	private String searchWord  = "";
	
	private String searchType = "";
	
	private int pageexamnum = 1;
	
	private List<IrpExam> exammanagerlist;
	
	private IrpExam irpexam;
	
	private IrpGroupService irpGroupService;
	
	private IrpGroupTestpaperService irpGroupTestpaperService;
	
	private List<IrpExam> examlist;
	
	private Long examid;
	
	private String examids;
	
	private IrpTestpaperService irpTestpaperService;
	
	private String begintimestr;
	
	private String endtimestr;
	
	private IrpExamRecordService irpExamRecordService;
	
	private List<IrpExamRecord> examrecordlist;
	
	private Long exam;

	private List<IrpChnlDocLink> chnlDocLinks15;
	
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	

	private IrpAttachedService irpAttachedService;
	
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public List<IrpChnlDocLink> getChnlDocLinks15() {
		return chnlDocLinks15;
	}

	public void setChnlDocLinks15(List<IrpChnlDocLink> chnlDocLinks15) {
		this.chnlDocLinks15 = chnlDocLinks15;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

	public Long getExam() {
		return exam;
	}

	public void setExam(Long exam) {
		this.exam = exam;
	}

	public List<IrpExamRecord> getExamrecordlist() {
		return examrecordlist;
	}

	public void setExamrecordlist(List<IrpExamRecord> examrecordlist) {
		this.examrecordlist = examrecordlist;
	}

	public IrpExamRecordService getIrpExamRecordService() {
		return irpExamRecordService;
	}

	public void setIrpExamRecordService(IrpExamRecordService irpExamRecordService) {
		this.irpExamRecordService = irpExamRecordService;
	}

	public String getBegintimestr() {
		return begintimestr;
	}

	public void setBegintimestr(String begintimestr) {
		this.begintimestr = begintimestr;
	}

	public String getEndtimestr() {
		return endtimestr;
	}

	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}

	public IrpTestpaperService getIrpTestpaperService() {
		return irpTestpaperService;
	}

	public void setIrpTestpaperService(IrpTestpaperService irpTestpaperService) {
		this.irpTestpaperService = irpTestpaperService;
	}

	public String getExamids() {
		return examids;
	}

	public void setExamids(String examids) {
		this.examids = examids;
	}

	public Long getExamid() {
		return examid;
	}

	public void setExamid(Long examid) {
		this.examid = examid;
	}

	public List<IrpExam> getExamlist() {
		return examlist;
	}

	public void setExamlist(List<IrpExam> examlist) {
		this.examlist = examlist;
	}

	public IrpGroupTestpaperService getIrpGroupTestpaperService() {
		return irpGroupTestpaperService;
	}

	public void setIrpGroupTestpaperService(
			IrpGroupTestpaperService irpGroupTestpaperService) {
		this.irpGroupTestpaperService = irpGroupTestpaperService;
	}

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}

	public IrpExam getIrpexam() {
		return irpexam;
	}

	public void setIrpexam(IrpExam irpexam) {
		this.irpexam = irpexam;
	}

	public List<IrpExam> getExammanagerlist() {
		return exammanagerlist;
	}

	public void setExammanagerlist(List<IrpExam> exammanagerlist) {
		this.exammanagerlist = exammanagerlist;
	}

	public int getPageexamnum() {
		return pageexamnum;
	}

	public void setPageexamnum(int pageexamnum) {
		this.pageexamnum = pageexamnum;
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

	public Long getCateid() {
		return cateid;
	}

	public void setCateid(Long cateid) {
		this.cateid = cateid;
	}

	public IrpExamService getIrpExamService() {
		return irpExamService;
	}

	public void setIrpExamService(IrpExamService irpExamService) {
		this.irpExamService = irpExamService;
	}
	/**
	 * 链接到考试菜单
	 * @return
	 */
	public String examMenu(){
		
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "examid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int num = this.irpExamService.getExamByCateIdNum(cateid, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(pageexamnum, 10, num);
		
		exammanagerlist = this.irpExamService.getExamByCateId(cateid, searchWord, searchType, _oOrderby, pageUtil);
		
		pageHTML = pageUtil.getPageHtml("pageExam(#PageNum#)");
		
		return SUCCESS;
	}
	/**
	 * 链接到添加试卷的div
	 * @return
	 */
	public String addExamDiv(){
		
		
		irpexam = new IrpExam();
		
		return SUCCESS;
	}
	/**
	 * 增加试卷
	 * @return
	 */
	public void addExamContent(){
		int msg = 0;
		IrpExam irpexams = new IrpExam();
		irpexams.setExamid(TableIdUtil.getNextId(irpexams));
		irpexams.setExamname(irpexam.getExamname());
		irpexams.setExamcontent(irpexam.getExamcontent());
		irpexams.setExamcate(irpexam.getExamcate());
		irpexams.setExamstatus(irpexam.getExamstatus());
		
		Date begindate = null;
		if (begintimestr.length()>0) {
			
			String d_one[] = begintimestr.split(" ");
			String d_one_s[] = d_one[0].split("-");
			String d_two_s[] = d_one[1].split(":");
			begindate = new Date(Integer.parseInt(d_one_s[0])-1900, Integer.parseInt(d_one_s[1])-1, Integer.parseInt(d_one_s[2]), Integer.parseInt(d_two_s[0]), Integer.parseInt(d_two_s[1]), 0);
		}
		irpexams.setBegintime(begindate);
		Date endtime = new Date();
		
		if (endtimestr.length()>0) {
			String d_one[] = endtimestr.split(" ");
			String d_one_s[] = d_one[0].split("-");
			String d_two_s[] = d_one[1].split(":");
			endtime.setYear(Integer.parseInt(d_one_s[0])-1900);
			endtime.setMonth(Integer.parseInt(d_one_s[1])-1);
			endtime.setDate(Integer.parseInt(d_one_s[2]));
			endtime.setHours(Integer.parseInt(d_two_s[0]));
			endtime.setMinutes(Integer.parseInt(d_two_s[1]));
			endtime.setSeconds(0);
		}
		irpexams.setEndtime(endtime);
		
		
		irpexams.setAnswertime(irpexam.getAnswertime());
		irpexams.setQualifiedscore(irpexam.getQualifiedscore());
		irpexams.setCruser(LoginUtil.getLoginUserId());
		irpexams.setCrtime(Calendar.getInstance().getTime());
		irpexams.setStartv(irpexam.getStartv());
		irpexams.setEndv(irpexam.getEndv());
		irpexams.setResultputlic(irpexam.getResultputlic());
		msg = this.irpExamService.addExam(irpexams);
		ActionUtil.writer(msg+"");
	}
	
	/**
	 * 链接到前台考试页面
	 * @return
	 */
	private Integer exammenunum = 1;
	private String pagemenuhtml = "";
	private String pagemenuhtmlexam = "";
	
	
	public String getPagemenuhtmlexam() {
		return pagemenuhtmlexam;
	}

	public void setPagemenuhtmlexam(String pagemenuhtmlexam) {
		this.pagemenuhtmlexam = pagemenuhtmlexam;
	}

	public String getPagemenuhtml() {
		return pagemenuhtml;
	}

	public void setPagemenuhtml(String pagemenuhtml) {
		this.pagemenuhtml = pagemenuhtml;
	}

	public Integer getExammenunum() {
		return exammenunum;
	}

	public void setExammenunum(Integer exammenunum) {
		this.exammenunum = exammenunum;
	}

	/**
	 * 根据id删除相应的考试
	 * @return
	 */
	public void dropExamInId(){
		int msg = this.irpExamService.deleteExamById(examid);
		int num = this.irpGroupTestpaperService.deleteGPByTPId(examid);
		ActionUtil.writer(msg+num+"");
	}
	/**
	 * 多选删除
	 * @return
	 */
	public void dropExamInIds(){
  	  int msg = 0;
  		if (examids!=null && examids.length()>0) {
  			String dtarray[] = examids.split(",");
  			if (dtarray.length>0) {
  				for (int i = 0; i < dtarray.length; i++)
  					msg = this.irpExamService.deleteExamById(Long.parseLong(dtarray[i]));
  				}
  			}

  		ActionUtil.writer(msg+"");
	}
	/**
	 * 
	 * 修改考试
	 * @return
	 */
	public String updateExamDiv(){
		if (examid!=null) {
			irpexam =  this.irpExamService.getIrpExamById(examid);
		}
		return SUCCESS;
	}
	/**
	 * 获得试卷id对象
	 * @param _paperid
	 * @return
	 */
	public IrpTestpaper getTPObjById(Long _paperid){
		IrpTestpaper irptestpaper  = null;
		if (_paperid!=null) {
			irptestpaper = this.irpTestpaperService.getIrpTestpaper(_paperid);
		}
		return irptestpaper;
	}
	
	/**
	 * 修改内容
	 * @return
	 */
	public void updateExamContent(){
		int msg = 0;
		IrpExam irpexams = new IrpExam();
		irpexams.setExamid(irpexam.getExamid());
		irpexams.setExamname(irpexam.getExamname());
		irpexams.setExamcontent(irpexam.getExamcontent());
		irpexams.setExamcate(irpexam.getExamcate());
		irpexams.setExamstatus(irpexam.getExamstatus());

		Date begindate = null;
		if (begintimestr.length()>0) {
			
			String d_one[] = begintimestr.split(" ");
			String d_one_s[] = d_one[0].split("-");
			String d_two_s[] = d_one[1].split(":");
			begindate = new Date(Integer.parseInt(d_one_s[0])-1900, Integer.parseInt(d_one_s[1])-1, Integer.parseInt(d_one_s[2]), Integer.parseInt(d_two_s[0]), Integer.parseInt(d_two_s[1]), 0);
		}
		irpexams.setBegintime(begindate);
		Date endtime = new Date();
		
		if (endtimestr.length()>0) {
			String d_one[] = endtimestr.split(" ");
			String d_one_s[] = d_one[0].split("-");
			String d_two_s[] = d_one[1].split(":");
			endtime.setYear(Integer.parseInt(d_one_s[0])-1900);
			endtime.setMonth(Integer.parseInt(d_one_s[1])-1);
			endtime.setDate(Integer.parseInt(d_one_s[2]));
			endtime.setHours(Integer.parseInt(d_two_s[0]));
			endtime.setMinutes(Integer.parseInt(d_two_s[1]));
			endtime.setSeconds(0);
		}
		irpexams.setEndtime(endtime);
		
		irpexams.setAnswertime(irpexam.getAnswertime());
		irpexams.setQualifiedscore(irpexam.getQualifiedscore());
		irpexams.setStartv(irpexam.getStartv());
		irpexams.setEndv(irpexam.getEndv());
		irpexams.setResultputlic(irpexam.getResultputlic());
		msg = this.irpExamService.updateExamById(irpexams);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(irpexams.getExamid().toString());
		ActionUtil.writer(msg+"");
	}
	/**
	 * 需要参加的考试
	 * @return
	 */
	public String linkExamMenu(){
		//获取当前用户所在的组织,判断
		//List<IrpExam> listt = new ArrayList();
		Long userid = LoginUtil.getLoginUserId();
		Map<Long,String> usermap = this.irpGroupService.findGroupIdsByUserId(userid);
		Set<Long> set = usermap.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Long groupid = (Long) iterator.next();
			
			//根据组织id查找对应的考试id的数量
			int num = this.irpExamService.findAllList(userid, groupid, IrpGroupTestpaper.ISDELNORMAL);
			PageUtil pageutil = new PageUtil(exammenunum, 4, num);
			examlist = irpExamService.findAllList(userid, groupid, IrpGroupTestpaper.ISDELNORMAL,pageutil);
			if(examlist.size()>0){
				this.pagemenuhtmlexam = pageutil.getClientPageHtml("pageEMenu(#PageNum#)");
			}
			//根据组织id查找对应的考试id的list
//			List<Long> lists = this.irpGroupTestpaperService.getGroupTestpapeByGroup(groupid, IrpGroupTestpaper.ISDELNORMAL,pageutil);
//			if (lists.size()>0) {
//				//所对应的试卷id集合
//				for (int i = 0; i < lists.size(); i++) {
//					listt.add(this.irpExamService.getIrpExamById(lists.get(i)));
//				}
//			}
		}
		//过滤考试时间
//		List<IrpExam> disposelistt = new ArrayList();
//		if (listt.size()>0) {
//			for (int i = 0; i < listt.size(); i++) {
//				IrpExam irpexam = listt.get(i);
//				Date nowtime = new Date();
//				if (irpexam.getExamstatus().equals(IrpExam.EXAMNORMAL)) {
//				
//				if (irpexam.getBegintime()==null && irpexam.getEndtime()==null) {
//					disposelistt.add(irpexam);
//				}else if(irpexam.getBegintime()==null && irpexam.getEndtime()!=null){
//					if (nowtime.getTime()<irpexam.getEndtime().getTime()) {
//						disposelistt.add(irpexam);
//					}
//				}else if(irpexam.getBegintime()!=null && irpexam.getEndtime()==null){
//					if (nowtime.getTime()>irpexam.getBegintime().getTime()) {
//						disposelistt.add(irpexam);
//					}
//				}else if(irpexam.getBegintime()!=null && irpexam.getEndtime()!=null){
//					if (nowtime.getTime()<irpexam.getEndtime().getTime()) {
//						disposelistt.add(irpexam);
//					}
//				}
//				}
//			}
//			
//		}
		//过滤考试时间没有过期但是已经提交的考试，防止重复提交
//		List<IrpExam> uncommitedlistt = new ArrayList();
//		for(IrpExam irpexam:disposelistt){
//			int irpexamrecord = irpExamRecordService.findExamRecordByexamidanduserid(irpexam.getExamid(), userid);
//			if(irpexamrecord==0){
//				uncommitedlistt.add(irpexam);
//			}
//		}
//		examlist = uncommitedlistt;
		
		//已参加的考试
//		int num = this.irpExamRecordService.getExamRecordListnum(IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
//		PageUtil pageutil = new PageUtil(exammenunum, 5, num);
//		examrecordlist = this.irpExamRecordService.getExamRecordList(pageutil,IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
//		if(examrecordlist.size()>0){
//			this.pagemenuhtml = pageutil.getClientPageHtml("pageAlreadyeMenu(#PageNum#)");
//		}
		//考试首页大图相关知识
		String s="";
		List<Long> _channelIds5 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("EXAM_BIG_IMAGE");
		_channelIds5.add(Long.parseLong(s));
		PageUtil pageUtil2 = new PageUtil(1, 3, 0);
		String sOrderByClause = "docpubtime desc";
		chnlDocLinks15=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds5, sOrderByClause, pageUtil2);
		return SUCCESS;
	}
	/**
	 * 待参加考试分页
	 * @return
	 * @author   npz
	 * @date 2017年10月17日
	 */
	public String pagexam(){
		if(exammenunum==null){
			exammenunum= 1;
		}
		Long userid = LoginUtil.getLoginUserId();
		Map<Long,String> usermap = this.irpGroupService.findGroupIdsByUserId(userid);
		Set<Long> set = usermap.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Long groupid = (Long) iterator.next();
			
			//根据组织id查找对应的考试id的数量
			int num = this.irpExamService.findAllList(userid, groupid, IrpGroupTestpaper.ISDELNORMAL);
			PageUtil pageutil = new PageUtil(exammenunum, 4, num);
			examlist = irpExamService.findAllList(userid, groupid, IrpGroupTestpaper.ISDELNORMAL,pageutil);
			if(examlist.size()>0){
				this.pagemenuhtmlexam = pageutil.getClientPageHtml("pageEMenu(#PageNum#)");
			}
		}
		return SUCCESS;
	}
	/**
	 * 已参加考试分页
	 * @return
	 * @author   npz
	 * @date 2017年10月18日
	 */
	public String alreadyexampage(){
		int num = this.irpExamRecordService.getExamRecordListnum(IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
		PageUtil pageutil = new PageUtil(exammenunum, 6, num);
		examrecordlist = this.irpExamRecordService.getExamRecordList(pageutil,IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
		if(examrecordlist.size()>0){
			this.pagemenuhtmlexam = pageutil.getClientPageHtml("pageAlreadyeMenu(#PageNum#)");
		}
		return SUCCESS;
	}
	/**
	 * 正在考试分页
	 * @return
	 * @author   npz
	 * @date 2017年11月13日
	 */
	public String nowexampage(){
		int num = this.irpExamRecordService.getExamRecordListnum(IrpExamRecord.EXAMING,LoginUtil.getLoginUserId());
		PageUtil pageutil = new PageUtil(exammenunum, 6, num);
		examrecordlist = this.irpExamRecordService.getExamRecordList(pageutil,IrpExamRecord.EXAMING,LoginUtil.getLoginUserId());
		if(examrecordlist.size()>0){
			this.pagemenuhtmlexam = pageutil.getClientPageHtml("pageAlreadyeMenu(#PageNum#)");
		}
		return SUCCESS;
	}
	/**
	 * 原图
	 * @param _docid
	 * @return
	 */
	public String docCoverPathSource(Long _docid, Integer docflag){
		String filePath="";
		IrpAttached attached=irpAttachedService.getIrpATttachedByDocIDFIle(_docid); 
		if(attached!=null){
			String myFileName=attached.getAttfile(); 
			//获得文件路径 
			filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "");
		}else if(docflag!=null && docflag>0){
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand"+docflag+".jpg";
		}else{
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
		} 
		return filePath;
	}
	/**
	 * 判断是否可以考试
	 * 
	 * @author   npz
	 * @date 2017年8月7日
	 */
	public void joinexam(){
		irpexam = irpExamService.getIrpExamById(exam);
		Map<String,IrpExam> map = new HashMap<String,IrpExam>();
		List<Map<String,IrpExam>> list = new ArrayList<Map<String,IrpExam>>();
		map.put("exam", irpexam);
		list.add(map);
		String jsonString_new = JSON.toJSONString(list);
		ActionUtil.writer(jsonString_new);
	}
	/**
	 * 已经参加过的考试 
	 * @return
	 */
	public String linkExamMenuAlready(){
		int num = this.irpExamRecordService.getExamRecordListnum(IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
		PageUtil pageutil = new PageUtil(exammenunum, 9, num);
		examrecordlist = this.irpExamRecordService.getExamRecordList(pageutil,IrpExamRecord.NORMALSTATUS,LoginUtil.getLoginUserId());
		if(examrecordlist.size()>0){
		this.pagemenuhtml = pageutil.getClientPageHtml("pageEMenu(#PageNum#)");
		}
		return SUCCESS;
	}
	/**
	 * 正在进行的考试
	 * @return
	 * @author   npz
	 * @date 2017年11月13日
	 */
	public String nowExaming(){
		int num = this.irpExamRecordService.getExamRecordListnum(IrpExamRecord.EXAMING,LoginUtil.getLoginUserId());
		PageUtil pageutil = new PageUtil(exammenunum, 9, num);
		examrecordlist = this.irpExamRecordService.getExamRecordList(pageutil,IrpExamRecord.EXAMING,LoginUtil.getLoginUserId());
		if(examrecordlist.size()>0){
		this.pagemenuhtml = pageutil.getClientPageHtml("pageEMenu(#PageNum#)");
		}
		return SUCCESS;
	}
	/**
	 * 根据id获得考试对象
	 * @param _examid
	 * @return
	 */
	public IrpExam getIrpExamByExamId(Long _examid){
		IrpExam irpExam = null;
		if (_examid!=null) {
			irpExam = this.irpExamService.getIrpExamById(_examid);
		}
		return irpExam;
		
	}
}
