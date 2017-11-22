package com.tfs.irp.uservaluelink.web;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserValue;
import com.tfs.irp.user.service.IrpUserService;


import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;

import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.service.IrpValueConfigService;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;

public class IrpUserValueLinkAction extends ActionSupport {
	
	private IrpUserValueLinkService IrpUserValueLinkService;
	
	private IrpUserService irpUserService;
	
    private List<IrpUser> userlist;
    
    private List<IrpUserValue> irpUserlist;
    private IrpUser irpUser;
    
    private long userid;
    
    private String userids;
    
    private IrpValueSettingService irpValueSettingService;

    private String searchWord;
    
    public List<IrpLeaveapply> getIrpLeaveapplies() {
		return irpLeaveapplies;
	}

	public void setIrpLeaveapplies(List<IrpLeaveapply> irpLeaveapplies) {
		this.irpLeaveapplies = irpLeaveapplies;
	}
	private String searchType;
    
    private List<IrpUserValueLink> irpUserValueLink;
    private List<IrpLeaveapply> irpLeaveapplies;
    
    private IrpValueConfigService irpValueConfigService;
    
    private Integer datanumid;
    
    private Long useridsearch;
    
    private List<IrpUser> irpUsers;
    
    private String userIds;
    public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public Long getUseridsearch() {
		return useridsearch;
	}

	public void setUseridsearch(Long useridsearch) {
		this.useridsearch = useridsearch;
	}
	/**
     * 开始时间      and  结束时间
     * @return
     */

    
    private Date date_start_time;
    
    private Date date_end_time;
    

	

	

	public Date getDate_start_time() {
		return date_start_time;
	}

	public void setDate_start_time(Date date_start_time) {
		this.date_start_time = date_start_time;
	}

	public Date getDate_end_time() {
		return date_end_time;
	}

	public void setDate_end_time(Date date_end_time) {
		this.date_end_time = date_end_time;
	}

	public Integer getDatanumid() {
		return datanumid;
	}

	public void setDatanumid(Integer datanumid) {
		this.datanumid = datanumid;
	}

	public IrpValueConfigService getIrpValueConfigService() {
		return irpValueConfigService;
	}

	public void setIrpValueConfigService(IrpValueConfigService irpValueConfigService) {
		this.irpValueConfigService = irpValueConfigService;
	}

	public List<IrpUserValueLink> getIrpUserValueLink() {
		return irpUserValueLink;
	}

	public void setIrpUserValueLink(List<IrpUserValueLink> irpUserValueLink) {
		this.irpUserValueLink = irpUserValueLink;
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

	public IrpValueSettingService getIrpValueSettingService() {
		return irpValueSettingService;
	}

	public void setIrpValueSettingService(
			IrpValueSettingService irpValueSettingService) {
		this.irpValueSettingService = irpValueSettingService;
	}

	public String getUserids() {
		return userids;
	}

	public void setUserids(String userids) {
		this.userids = userids;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
	private String c_date_start_time;
	private String c_date_end_time;
	public String getC_date_start_time() {
		return c_date_start_time;
	}

	public void setC_date_start_time(String c_date_start_time) {
		this.c_date_start_time = c_date_start_time;
	}

	public String getC_date_end_time() {
		return c_date_end_time;
	}

	public void setC_date_end_time(String c_date_end_time) {
		this.c_date_end_time = c_date_end_time;
	}
	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";
	
	private String orderFieldtwo = "";
	
	private String graphicType;
	
	

	public String getGraphicType() {
		return graphicType;
	}

	public void setGraphicType(String graphicType) {
		this.graphicType = graphicType;
	}

	public String getOrderFieldtwo() {
		return orderFieldtwo;
	}

	public void setOrderFieldtwo(String orderFieldtwo) {
		this.orderFieldtwo = orderFieldtwo;
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

	public List<IrpUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<IrpUser> userlist) {
		this.userlist = userlist;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return IrpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		IrpUserValueLinkService = irpUserValueLinkService;
	}
	private String c_start_end;//操作时间段
	
	public String getC_start_end() {
		return c_start_end;
	}

	public void setC_start_end(String c_start_end) {
		this.c_start_end = c_start_end;
	}
	
	public List<IrpUserValue> getIrpUserlist() {
		return irpUserlist;
	}

	public void setIrpUserlist(List<IrpUserValue> irpUserlist) {
		this.irpUserlist = irpUserlist;
	}

	/**
	 * 所有用户的列表(积分统计)
	 * @return
	 */
	public String searchUserRankings(){
		Date starttimes=null;
		Date endtimes=null;
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "sumscore desc";
			} else {
				System.out.println(orderBy);
				if(orderBy.equals("asc")){
					_oOrderby = this.orderField + " " + this.orderBy+" nulls first";
				}else{
					_oOrderby = this.orderField + " " + this.orderBy+" nulls last";
				}
			}
			//时间段
			if (this.c_start_end.equals("covert_week")) {
				starttimes=new LogTimeUtil().getWeek();
				endtimes=new LogTimeUtil().getLastWeek();
			}else if(this.c_start_end.equals("covert_moren")){
				starttimes=null;
				endtimes=null;
			}else if(this.c_start_end.equals("covert_month")){
				starttimes=new LogTimeUtil().getMonth();
				endtimes=new LogTimeUtil().getLastMonth();
			}else if(this.c_start_end.equals("covert_quarter")){
				starttimes=new LogTimeUtil().getQuarter();
				endtimes=new LogTimeUtil().getLastQuarter();
			}else if(this.c_start_end.equals("covert_appoint_date")){
				String starttimeL[]=this.c_date_start_time.split("-");
				Date dateS=new Date(new LogTimeUtil().getYearOfYear(starttimeL[0]),Integer.parseInt(starttimeL[1])-1,Integer.parseInt(starttimeL[2]));
				dateS.setHours(0);
				dateS.setMinutes(0);
				dateS.setSeconds(0);
				starttimes=dateS;
				String endtimeL[]=this.c_date_end_time.split("-");
				Date dateE=new Date(new LogTimeUtil().getYearOfYear(endtimeL[0]),Integer.parseInt(endtimeL[1])-1,Integer.parseInt(endtimeL[2])+1);
				dateE.setHours(24);
				dateE.setMinutes(59);
				dateE.setSeconds(59);
				endtimes=dateE;
			}
			PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
					irpUserService.searchUserValueLinkSize(searchWord, searchType));
			
		  irpUserlist =  this.irpUserService.findAllIrpUser(pageUtil,_oOrderby,searchWord,searchType,starttimes,endtimes);
		  this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			return SUCCESS;
			
		}
	/**
	 * 所有用户的列表(年假统计)
	 * @return
	 */
	public String searchUserLeaveYearDays(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "sumscore desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				irpUserService.searchYearDays(searchWord, searchType));
		
		userlist =  this.irpUserService.findAllIrpUserHoloday(pageUtil,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	/**
	 * 特殊用户
	 * @return
	 */
	public String searchSpecialUser(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "userid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				irpUserService.searchSpecialUserValueLinkSize(searchWord, searchType));
		
	  userlist =  this.irpUserService.findSpecialIrpUser(pageUtil,_oOrderby,searchWord,searchType);
	  
	  this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return 	SUCCESS;
	}
	/**
	 * 选择特殊 用户 
	 * @return
	 */
	public String selectUserAll(){
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("import_page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 改变用户为特殊用户
	 * @return
	 */
	public void changeUserSpecialType(){		
		String userid[] = userIds.split(",");
		int data =0;
		for (int i = 0; i < userid.length; i++) {
			 data = this.irpUserService.changeUserSpecialType(Long.valueOf(userid[i]),IrpUser.SPECIAL_USER);
		}
		ActionUtil.writer(data+"");
	}
	/**
	 * 改变用户为特殊用户
	 * @return
	 */
	public void changeUserNormalType(){
		String userid[] = userIds.split(",");
		int data =0;
		for (int i = 0; i < userid.length; i++) {
			 data += this.irpUserService.changeUserSpecialType(Long.valueOf(userid[i]),IrpUser.NORMAL_USER);
		}
		ActionUtil.writer(data+"");
	}
	/**
	 * 设置统计图表数据 
	 */
	public void searchUserRankingsjson(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "sumscore desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				irpUserService.searchUserValueLinkSize(searchWord, searchType));
		
	  userlist =  this.irpUserService.findAllIrpUser(pageUtil,_oOrderby,searchWord,searchType);
	  
	  StringBuffer sbName = new StringBuffer();
	  StringBuffer sbScore = new StringBuffer();
	  StringBuffer sbExpe = new StringBuffer();
	  
		for(int i = 0;i<userlist.size();i++){	
			IrpUser irpUser = userlist.get(i);
			sbName.append(irpUser.getUsername()+",");
		}
		
		for (int i = 0;i<userlist.size();i++) {
			IrpUser irpUser = userlist.get(i);
			sbScore.append(irpUser.getSumscore()+",");
		}
		for (int i = 0;i<userlist.size();i++) {
			IrpUser irpUser = userlist.get(i);
			sbExpe.append(irpUser.getSumexperience() +",");
		}
		sbName.append("."+sbScore+"."+sbExpe);
		
		
     ActionUtil.writer(sbName.toString());
		
		
	}
	
	/**
	 * 加载用户贡献排行框
	 * @return
	 */
	public String updateLoadRanking(){
		
		irpUser = this.irpUserService.findUserByUserId(userid);
		
		return SUCCESS;
	}
	/**
	 * 加载用户剩余年加修改框
	 * @return
	 */
	public String toUpdateLeaveYearDays(){
		
		irpUser = this.irpUserService.findUserByUserId(userid);
		
		return SUCCESS;
	}
	/**
	 * 跳转
	 * @return
	 */
	public String toLeaveYearDays(){
		if(userids!=null){
			userids=userids;
		}
		
		return SUCCESS;
	}
	/**
	 * 修改贡献排行数据
	 */
	public void updateRankingByUserid(){
    int _nStatus =  this.irpUserService.updateSumScoreExperience(irpUser);
    ActionUtil.writer(_nStatus+"");
	}
	/**
	 * 修改剩余年假
	 */
	public void updateLeaveYearDays(){
		int _nStatus =  this.irpUserService.updateLeaveYearDays(irpUser);
		ActionUtil.writer(_nStatus+"");
	}
	/**
	 * 一键修改剩余年假天数
	 */
	public void updateALLLeaveYearDays(){
		
		int _nStatus =  this.irpUserService.updateAllLeaveYearDays(irpUser);
		ActionUtil.writer(_nStatus+"");
	}
	/**
	 * 选择修改剩余年假天数
	 */
	public void updateWithChooseLeaveYearDays(){
		
		String useridsa=irpUser.getUsername();
		String userid[] = useridsa.split(",");
		int _nStatus =0;
		for (int i = 0; i < userid.length; i++) {
			_nStatus += this.irpUserService.updateUserWithChoose(Long.valueOf(userid[i]),irpUser.getHoliday());
		}
		
		ActionUtil.writer(_nStatus+"");
	}

	/**
	 * 清空用户总积分和总经验
	 */
	public void userInfoEmpty(){
		int totalscore =this.IrpUserValueLinkService.sumScoreByUserid(userid);
		int totalexperience =  this.IrpUserValueLinkService.sumExperienceByUserid(userid);
		
		int _nStatus = 0;
		_nStatus  =  this.irpUserService.emptyUserScoreExperInfo(userid,totalscore,totalexperience);
		ActionUtil.writer(_nStatus+"");
		
	}
	/**
	 * 清空选中的用户
	 * @return
	 */
	public void userInfoEmptyAll(){
		int _nStatus = 0;

		String useridstrue = userids.substring(9);
		String useridarray[] = useridstrue.split(",");
		for(int i = 0; i <useridarray.length;i++){
			
			int totalscore =this.IrpUserValueLinkService.sumScoreByUserid(Long.parseLong(useridarray[i]));
			int totalexperience =  this.IrpUserValueLinkService.sumExperienceByUserid(Long.parseLong(useridarray[i]));
			_nStatus =	this.irpUserService.emptyUserScoreExperInfo(Long.parseLong(useridarray[i]),totalscore,totalexperience);
	
		}
		ActionUtil.writer(_nStatus+"");
	}
	
	/**
	 * 根据相应的积分获得相应的称号
	 * @param _score
	 * @return
	 */
	public String findRankNameByScore(long _score){
		String rankname = "";
		rankname = irpValueSettingService.findRanknameBySumScore(_score);
		return rankname;
	}
	/**
	 * 根据相应的积分获得相应的称号描述
	 * @param _score
	 * @return
	 */
	public String findRankdescBySumScore(long _score){
		String rankdesc = "";
		rankdesc = irpValueSettingService.findRankdescBySumScore(_score);
		return rankdesc;
	}
	/**
	 * 查看某个用户的详细信息
	 * @return
	 */
	public String userLoadContributeDetail(){
		Date starttime = null;
		Date endtime = null;
		LogTimeUtil ltu = new LogTimeUtil();
		if(datanumid!=null){
			 if(datanumid==1){
				 //本周
	        	  starttime = ltu.getWeek();
	        	  endtime  = ltu.getLastWeek();
	          }else if(datanumid==2){
	        	  //本月
	        	  starttime = ltu.getMonth();
	        	  endtime = ltu.getLastMonth();
	          }else if(datanumid==3){
	        	  //本季
	        	  starttime = ltu.getQuarter();
	        	  endtime = ltu.getLastQuarter();
	          }else{
	        	  //指定
	        	 
                  Date date = date_end_time;
                  date.setHours(24);
       	          starttime=date_start_time;
       	          endtime=date;
	          }
		}else{
        	  starttime = ltu.getMonth();
        	  endtime = ltu.getLastMonth();
          }
		String _oOrderby = "";

		
	
		if (this.orderFieldtwo == null || this.orderFieldtwo.equals("")) {
			_oOrderby = "crtime desc";
		} else {
			_oOrderby = this.orderFieldtwo + " " + this.orderBy;
		}
	
		
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				this.IrpUserValueLinkService.getIrpUserValueLinkListByTimeCount(starttime,endtime, useridsearch));
		irpUserValueLink = this.IrpUserValueLinkService.getIrpUserValueLinkListByTime(starttime,endtime, useridsearch,pageUtil,_oOrderby);
		
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c_date_start_time = sdf.format(starttime);
		Date dateEnd = endtime;
		dateEnd.setDate(endtime.getDate()-1);
		c_date_end_time =sdf.format(dateEnd);
		
		
		return SUCCESS;
	}
	/**
	 * 查看某个用户的详细休年假信息
	 * @return
	 */
	public String loadUserleavedaysDetail(){
		Date starttime = null;
		Date endtime = null;
		LogTimeUtil ltu = new LogTimeUtil();
		if(datanumid!=null){
			if(datanumid==1){
				//本周
				starttime = ltu.getWeek();
				endtime  = ltu.getLastWeek();
			}else if(datanumid==2){
				//本月
				starttime = ltu.getMonth();
				endtime = ltu.getLastMonth();
			}else if(datanumid==3){
				//本季
				starttime = ltu.getQuarter();
				endtime = ltu.getLastQuarter();
			}else{
				//指定
				
				Date date = date_end_time;
				date.setHours(24);
				starttime=date_start_time;
				endtime=date;
			}
		}else{
			starttime = ltu.getMonth();
			endtime = ltu.getLastMonth();
		}
		String _oOrderby = "";
		
		
		
		if (this.orderFieldtwo == null || this.orderFieldtwo.equals("")) {
			_oOrderby = "crtime desc";
		} else {
			_oOrderby = this.orderFieldtwo + " " + this.orderBy;
		}
		
		
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				this.IrpUserValueLinkService.getLeaveDaysByTimeCount(starttime,endtime, useridsearch));
		irpLeaveapplies = this.IrpUserValueLinkService.getLeaveDaysListByTime(starttime,endtime, useridsearch,pageUtil,_oOrderby);
		
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c_date_start_time = sdf.format(starttime);
		Date dateEnd = endtime;
		dateEnd.setDate(endtime.getDate()-1);
		c_date_end_time =sdf.format(dateEnd);
		useridsearch=useridsearch;
		return SUCCESS;
	}
	public IrpValueConfig getIrpValueConfig(String _valuekey){
		IrpValueConfig irpValueConfig = null;
		irpValueConfig = irpValueConfigService.irpValueConfigByValueKey(_valuekey);	
	return irpValueConfig;	
	}
	
}
