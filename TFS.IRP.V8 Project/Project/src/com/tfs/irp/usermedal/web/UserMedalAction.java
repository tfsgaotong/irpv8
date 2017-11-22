package com.tfs.irp.usermedal.web;


import java.util.Date;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.medal.web.MedalAction;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class UserMedalAction extends ActionSupport {
	private IrpUserMedalService irpUserMedalService;
	private IrpUserService irpUserService;
	private IrpMedalService irpMedalService;
	private IrpGoodsService irpGoodsService;
	private IrpMessageContentService irpMessageContentService;
	

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpGoodsService getIrpGoodsService() {
		return irpGoodsService;
	}

	public void setIrpGoodsService(IrpGoodsService irpGoodsService) {
		this.irpGoodsService = irpGoodsService;
	}

	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}

	
	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	private Long usermedalid;
	private String usermedalids;
	private int pageNum=1; 
	private int pageSize=10; 
	private String pageHTML;
	private IrpUserMedal irpUserMedal;
	private String orderField;
	private String orderBy;
	
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

	public Long getUsermedalid() {
		return usermedalid;
	}

	public void setUsermedalid(Long usermedalid) {
		this.usermedalid = usermedalid;
	}

	public String getUsermedalids() {
		return usermedalids;
	}

	public void setUsermedalids(String usermedalids) {
		this.usermedalids = usermedalids;
	}

	
	public IrpUserMedal getIrpUserMedal() {
		return irpUserMedal;
	}

	public void setIrpUserMedal(IrpUserMedal irpUserMedal) {
		this.irpUserMedal = irpUserMedal;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	private List<IrpUser> irpUserList;
	private List<IrpMedal> irpMedalList;
	
	public List<IrpMedal> getIrpMedalList() {
		return irpMedalList;
	}

	public void setIrpMedalList(List<IrpMedal> irpMedalList) {
		this.irpMedalList = irpMedalList;
	}

	public List<IrpUser> getIrpUserList() {
		return irpUserList;
	}

	public void setIrpUserList(List<IrpUser> irpUserList) {
		this.irpUserList = irpUserList;
	}

	/**
	 * 添加页面跳转
	 * @return
	 */
	public String toaddusermedal(){
		irpUserList=irpUserService.findAllIrpUser(null, null, null, null);
		irpMedalList=irpMedalService.listMedal();
		return SUCCESS;
		
	}
	public void addusermedal(){
		int msg = 0;
		Date date = new Date();
		irpUserMedal.setUsermedalid(TableIdUtil.getNextId(IrpUserMedal.TABLE_NAME));
		irpUserMedal.setCoverttime(date);
		IrpUser irpUser=irpUserService.findUserByUserId(irpUserMedal.getUserid());
		irpUserMedal.setUsername(irpUser.getUsername());
		IrpMedal irpMedal=irpMedalService.findMedalByMedalid(irpUserMedal.getMedalid());
		irpUserMedal.setMedalname(irpMedal.getMedalname());
		irpUserMedal.setMedalstate(0l);
		if(irpMedal.getMedalnum()>0){
			msg = irpUserMedalService.addUserMedal(irpUserMedal);
			long medalnum=irpMedal.getMedalnum()-1;
			irpMedal.setMedalnum(medalnum);
			irpMedalService.updateMedalByMedalid(irpMedal);
			IrpMessageContent irpMessageContent=new IrpMessageContent();
			irpMessageContent.setFromUid(irpUserMedal.getUserid());
			irpMessageContent.setContent("恭喜您！"+irpUser.getUsername()+"，获得了一枚【"+irpMedal.getMedalname()+"】勋章。请继续加油哦！");
			irpMessageContent.setCruserid(LoginUtil.getLoginUserId());
			irpMessageContent.setMessagetype(0);
			irpMessageContentService.addMessageContent(irpMessageContent);
		}
		ActionUtil.writer(""+msg);
	}
	private List<IrpUserMedal> listusermedal;
	
	public List<IrpUserMedal> getListusermedal() {
		return listusermedal;
	}

	public void setListusermedal(List<IrpUserMedal> listusermedal) {
		this.listusermedal = listusermedal;
	}
	private String username;
	private String medalname;
	private String c_start_end;//操作时间段
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMedalname() {
		return medalname;
	}

	public void setMedalname(String medalname) {
		this.medalname = medalname;
	}

	public String getC_start_end() {
		return c_start_end;
	}

	public void setC_start_end(String c_start_end) {
		this.c_start_end = c_start_end;
	}

	public String listUserMedal(){
		Date starttimes=null;
		Date endtimes=null;
		int aDataCount=irpUserMedalService.countUserMedal();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		//listusermedal = irpUserMedalService.showAllUserMedal(pageUtil,orderField,orderBy);
		//this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		// 处理排序
					String _oOrderby = "";
					if (this.orderField == null || this.orderField.equals("")) {
						_oOrderby = "coverttime desc";
					} else {
						_oOrderby = this.orderField + " " + this.orderBy;
					}
					IrpUserMedal usermedal=new IrpUserMedal();
					//兑换用户
					if (username!=null) {
						usermedal.setUsername(username.trim());
					}
					//兑换商品	
					if (medalname!=null) {
						usermedal.setMedalname(medalname.trim());
					}
						
					//时间段
					if (this.c_start_end.equals("issue_week")) {
						starttimes=new LogTimeUtil().getWeek();
						endtimes=new LogTimeUtil().getLastWeek();
					}else if(this.c_start_end.equals("issue_month")){
						starttimes=new LogTimeUtil().getMonth();
						endtimes=new LogTimeUtil().getLastMonth();
					}else if(this.c_start_end.equals("issue_quarter")){
						starttimes=new LogTimeUtil().getQuarter();
						endtimes=new LogTimeUtil().getLastQuarter();
					}else if(this.c_start_end.equals("issue_appoint_date")){
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
					int covertsize= this.irpUserMedalService.findUserMedalOfPageSize(pageUtil, _oOrderby,usermedal,starttimes,endtimes).size();
					PageUtil pageUtilCheck = new PageUtil(this.pageNum, this.getPageSize(),
							covertsize);
					listusermedal=this.irpUserMedalService.findUserMedalOfPage(pageUtilCheck, _oOrderby,usermedal,starttimes,endtimes);
				    this.pageHTML = pageUtilCheck.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	private Long goodsid;
	
	
	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public void getUserMedal(){
		int count = irpUserMedalService.countUserMedal(LoginUtil.getLoginUserId(), goodsid, 0l);
		ActionUtil.writer(""+count);
		
	}
	
	/*public void plDeleteCovert(){
		int msg = 0;
		if(covertids!=""&&covertids!=null){
    		String[] array =covertids.split(",");
    		for(int j=0;j<array.length;j++){
    			irpUserCovertGoodsService.deleteMoreCovert(Long.parseLong(array[j]));
    			msg++;
    		}
		}
		ActionUtil.writer(""+msg);
	}
	public String findCovertInfoById(){
		covert=irpUserCovertGoodsService.findCovertByCovertid(covertid);
		username=irpUserService.findShowNameByUserid(covert.getUserid());
		IrpGoods irpGoods=irpGoodsService.findGoodsByGoodsid(covert.getGoodsid());
		goodsname=irpGoods.getGoodsname();
		return SUCCESS;
	}*/
	
	/*public void findcovertnum(){
		HttpServletRequest _request =ServletActionContext.getRequest();
		Long goodsid=Long.parseLong(_request.getParameter("goodsid"));
		Long covertnum=Long.parseLong(_request.getParameter("covertnum"));
		System.out.println("aaaa"+covertnum);
	    ActionUtil.writer(covertnum+"");
	 }*/

}