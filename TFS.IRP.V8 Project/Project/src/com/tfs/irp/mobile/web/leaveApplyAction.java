package com.tfs.irp.mobile.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.service.IrpLeaveapplyService;
import com.tfs.irp.leaveapply.web.IrpLeaveapplyInfo;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.leaveconf.service.IrpLeaveconfigService;
import com.tfs.irp.leaveoper.entity.IrpLeaveoper;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class leaveApplyAction extends ActionSupport{
	private IrpLeaveoperService irpLeaveoperService;
	private IrpLeaveapplyService irpLeaveapplyService;
	private List<IrpLeaveapply>  irpLeaveapplyList;
	private List<IrpLeaveapplyInfo> irpLeaveapplyInfos;
	private IrpLeaveconfigService irpLeaveconfigService;
	private Integer type;
	private IrpUserDAO irpUserDAO;
	private int pageNum=1;	
	private int pageSize=10;
	private String pageHTML;
	
	public IrpLeaveoperService getIrpLeaveoperService() {
		return irpLeaveoperService;
	}
	public void setIrpLeaveoperService(IrpLeaveoperService irpLeaveoperService) {
		this.irpLeaveoperService = irpLeaveoperService;
	}
	public IrpLeaveapplyService getIrpLeaveapplyService() {
		return irpLeaveapplyService;
	}
	public void setIrpLeaveapplyService(IrpLeaveapplyService irpLeaveapplyService) {
		this.irpLeaveapplyService = irpLeaveapplyService;
	}
	public List<IrpLeaveapply> getIrpLeaveapplyList() {
		return irpLeaveapplyList;
	}
	public void setIrpLeaveapplyList(List<IrpLeaveapply> irpLeaveapplyList) {
		this.irpLeaveapplyList = irpLeaveapplyList;
	}
	public List<IrpLeaveapplyInfo> getIrpLeaveapplyInfos() {
		return irpLeaveapplyInfos;
	}
	public void setIrpLeaveapplyInfos(List<IrpLeaveapplyInfo> irpLeaveapplyInfos) {
		this.irpLeaveapplyInfos = irpLeaveapplyInfos;
	}
	public IrpLeaveconfigService getIrpLeaveconfigService() {
		return irpLeaveconfigService;
	}
	public void setIrpLeaveconfigService(IrpLeaveconfigService irpLeaveconfigService) {
		this.irpLeaveconfigService = irpLeaveconfigService;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}
	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
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
	
	/**
	    * 对list的某些字段进行转化
	    * @param list
	    * @return
	    */
   public List<IrpLeaveapplyInfo> coverListApply(List<IrpLeaveapply> list){
	   List<IrpLeaveapplyInfo> lists=new ArrayList<IrpLeaveapplyInfo>();
	   for(IrpLeaveapply s:list){
		   IrpLeaveapplyInfo sinfo=new IrpLeaveapplyInfo();
		   sinfo.setLeaveapplyid(s.getLeaveapplyid());
		   sinfo.setApplyreason(s.getApplyreason());
		   sinfo.setLeavedays(s.getLeavedays());
		   sinfo.setAuditinfo(s.getAuditinfo());
		   sinfo.setContent(s.getContent());
		   sinfo.setAddress(s.getAddress());
		   sinfo.setCruseridl(s.getCruserid());
		   //这些用户具有审批权限
		
		   List<IrpLeaveoper> list2 =this.irpLeaveoperService.getOperByapplyId(s.getLeaveapplyid(),LoginUtil.getLoginUserId());
			 
		   for(IrpLeaveoper se:list2){
			  
			   
			if(se.getOperstatus().equals(10)){
				   sinfo.setAdvise("已同意");
			   }else if(se.getOperstatus().equals(20)){
				   sinfo.setAdvise("未审核");
			   }else if(se.getOperstatus().equals(30)){
				   sinfo.setAdvise("已拒绝");
			   }
			   
		   }
		   if(s.getApplystatus().equals(10)){
			   sinfo.setApplystatus("已同意");
		   }else if(s.getApplystatus().equals(20)){
			   sinfo.setApplystatus("未审核");
		   }else if(s.getApplystatus().equals(30)){
			   sinfo.setApplystatus("已拒绝");
		   }
		   IrpUser irpUser=null;  
		   try {
			irpUser = irpUserDAO.selectByPrimaryKey(s.getCruserid());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		   sinfo.setCheckmore(s.getCheckmore());
		   sinfo.setCruserid(LoginUtil.getUserNameString(irpUser));
		   sinfo.setCrtime(s.getCrtime());
		   sinfo.setStarttime(s.getStarttime());
		   sinfo.setEndtime(s.getEndtime());
		   sinfo.setOvertimecontent(s.getOvertimecontent());
		   sinfo.setOvertimeadress(s.getOvertimeadress());
		   sinfo.setTitle(s.getTitle());
		   if(s.getEmergency().equals(10)){
			   
			   sinfo.setEmergency("正常");
		   }else if(s.getEmergency().equals(20)){
			   sinfo.setEmergency("重要");
		   }else if(s.getEmergency().equals(30)){
			   sinfo.setEmergency("紧急");
		   }
		  
		   //请假类型
		   IrpLeaveconfig irpLeaveconfig =this.irpLeaveconfigService.selLeaveConfigById(s.getApplytypeid());
		   sinfo.setApplytypeid(irpLeaveconfig.getLeaveconfigname());
		   //增加审核人
		 List<IrpLeaveoper> opers = this.irpLeaveoperService.getListByapplyId(s.getLeaveapplyid());
		
		 String checker2="";
		 if(opers.size()>0){
		 for(IrpLeaveoper o:opers){
			 IrpUser irpUsero=null; 
			 try {
				irpUsero = irpUserDAO.selectByPrimaryKey(o.getUserid());
				checker2+=","+irpUsero.getName();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			 
		 }
		 sinfo.setChecker(checker2.substring(1));
		 }else{
			 sinfo.setChecker(checker2);
			 
		 }		
		   lists.add(sinfo);
	   }
	return lists;
	   
   }
	/**
	 * 请假审核mobile
	 * @return
	 * @author   npz
	 * @date 2017年9月19日
	 */
	public String managerLeaveList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String nextPage=request.getParameter("nextPage");
		String _status = request.getParameter("status");
		String token = request.getParameter("token");
		IrpUser currIrpUser = mobileAction.getlogin(token);
		Integer status=0;
		if(_status!=null){
				status=Integer.valueOf(_status);
		}
		List<Long> list = null;
		if(status!=0){
			if(status.equals(20)){			
				 list =this.irpLeaveoperService.selLeaveapplyidByUserid(20,currIrpUser);
			}else if(status.equals(40)){
				List<Integer> list2=new ArrayList<Integer>();
				list2.add(10);list2.add(30);			
				 list =this.irpLeaveoperService.selLeaveapplyidByUseridList(list2,currIrpUser);
			}
		}	
	
		if(list==null||list.size()<=0){
			list.add(0L);
		}
		int nDataCount=this.irpLeaveapplyService.findListOverTimeNums(type,status,list);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		irpLeaveapplyList = this.irpLeaveapplyService.getAll(pageUtil,type,status,list);
		//irpCheckerLinkService.selAllByPageAndExample(_pageUtil, example);
		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
		this.pageHTML = pageUtil.getPageHtml("findFlowConn(#PageNum#)");	
		if(nextPage!=null&&nextPage.equals("1")){
			return "page";
		}else{
			return SUCCESS;
		}	
	}
}
