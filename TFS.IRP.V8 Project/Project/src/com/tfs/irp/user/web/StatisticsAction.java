package com.tfs.irp.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.chnl_doc_link.web.DocHighChartAction;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.question.web.statQuestionAction;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.DocExport;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.PageUtil;

public class StatisticsAction extends ActionSupport {

	
	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	
	private int pageNum = 1;

	private int pageSize = 10;
	
	private IrpUserService irpUserService;
	
	
	private String usernamejson;

	private String scorejson;
	
	private String experjson;
	
	private String orderField;
	
	private IrpLogsService irpLogsService;
	
	private Date startTime;
	
	private Date endTime;
	
	private String timeLimit;
	
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	
	private Long sumquestion;
	
	private Long sumanswer;
	
	private Long sumsalovequestion;
	
	private IrpQuestionService irpQuestionService;
	
	private IrpOpertypeService irpOpertypeService;
	
	private IrpMicroblogService irpMicroBlogService;
	
	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}



	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}



	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}



	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}



	public IrpTagService getIrpTagService() {
		return irpTagService;
	}



	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}
	private IrpTagService irpTagService;
	
	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}



	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}



	public Date getStartTime() {
		return startTime;
	}



	public String getTimeLimit() {
		return timeLimit;
	}



	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public IrpLogsService getIrpLogsService() {
		return irpLogsService;
	}



	public void setIrpLogsService(IrpLogsService irpLogsService) {
		this.irpLogsService = irpLogsService;
	}



	public String getOrderField() {
		return orderField;
	}



	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}



	public String getUsernamejson() {
		return usernamejson;
	}



	public void setUsernamejson(String usernamejson) {
		this.usernamejson = usernamejson;
	}



	public String getScorejson() {
		return scorejson;
	}



	public void setScorejson(String scorejson) {
		this.scorejson = scorejson;
	}



	public String getExperjson() {
		return experjson;
	}



	public void setExperjson(String experjson) {
		this.experjson = experjson;
	}

	
	





	public IrpUserService getIrpUserService() {
		return irpUserService;
	}



	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
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

    /**
     * 用户积分经验统计
     * @return
     */
	public String getUserScoreExperienceChart(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "sumscore desc";
		} else {
			_oOrderby = this.orderField;
		}
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				irpUserService.searchUserValueLinkSize(null,null));
		
	  List<IrpUser> userlist =  this.irpUserService.findAllIrpUser(pageUtil,_oOrderby,null,null);
	  
	  StringBuffer sbName = new StringBuffer();
	  StringBuffer sbScore = new StringBuffer();
	  StringBuffer sbExpe = new StringBuffer();
	  
		for(int i = 0;i<userlist.size();i++){	
			IrpUser irpUser = userlist.get(i);
			sbName.append("'"+irpUser.getUsername()+"',");
		}
		
		for (int i = 0;i<userlist.size();i++) {
			IrpUser irpUser = userlist.get(i);
			sbScore.append(irpUser.getSumscore()+",");
		}
		for (int i = 0;i<userlist.size();i++) {
			IrpUser irpUser = userlist.get(i);
			sbExpe.append(irpUser.getSumexperience() +",");
		}
		usernamejson =sbName.toString().substring(0,sbName.toString().length()-1);

		scorejson = sbScore.toString().substring(0,sbScore.toString().length()-1);

		experjson = sbExpe.toString().substring(0,sbExpe.toString().length()-1);

	   this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 访问人数/次数统计
	 * @return
	 */
	private String usernameofpeonum;
	
	private String usernameofcount;
	
	  private Integer datanumid;

	    private Date date_start_time;
	    
	    private Date date_end_time;
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



	public Integer getDatanumid() {
			return datanumid;
		}



		public void setDatanumid(Integer datanumid) {
			this.datanumid = datanumid;
		}



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



	public String getUsernameofcount() {
		return usernameofcount;
	}



	public void setUsernameofcount(String usernameofcount) {
		this.usernameofcount = usernameofcount;
	}



	public String getUsernameofpeonum() {
		return usernameofpeonum;
	}



	public void setUsernameofpeonum(String usernameofpeonum) {
		this.usernameofpeonum = usernameofpeonum;
	}



	public String getSystemVisitByPeople(){
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
	//	PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),irpLogsService.findIrpLogsByDateCount(starttime,endtime));	
    List<IrpLogs> list =  irpLogsService.findIrpLogsByDate(starttime, endtime);
    Set<String> unameSet = new HashSet<String>();
      for(int i=0;i<list.size();i++){  	    
    	  unameSet.add("'"+list.get(i)+"'");
	  }
      
      usernameofpeonum = unameSet.toString();
      
      StringBuffer countuname = new StringBuffer();
		Iterator<String> iterator=unameSet.iterator();
		while(iterator.hasNext()){
		String uname = iterator.next();
		countuname.append(this.irpLogsService.findCountOfUserByDate(uname.substring(1, uname.length()-1),starttime,endtime)+",");
		}
		usernameofcount=countuname.toString().substring(0,countuname.toString().length()-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c_date_start_time = sdf.format(starttime);
		Date dateEnd = endtime;
		dateEnd.setDate(endtime.getDate()-1);
		c_date_end_time =sdf.format(dateEnd);
		  
    return SUCCESS;
	}
	/**
	 * 访问时间段统计
	 * @return
	 */
	private String hoursarray;
	
	private String daytimes;
	public String getDaytimes() {
		return daytimes;
	}



	public void setDaytimes(String daytimes) {
		this.daytimes = daytimes;
	}



	public String getHoursarray() {
		return hoursarray;
	}



	public void setHoursarray(String hoursarray) {
		this.hoursarray = hoursarray;
	}



	public String getSystemTheQuanTum(){
		if(this.timeLimit!=null){
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = " docpubtime desc";
			} else {
				_oOrderby = this.orderField;
			}
			LogTimeUtil logTimeUtil=new LogTimeUtil();
			if(this.timeLimit.trim().equals("thismonth")){//本月
				startTime=logTimeUtil.getMonth();
				endTime=logTimeUtil.getLastMonth();
			}else if (this.timeLimit.trim().equals("thisweek")) {//本周
				startTime=logTimeUtil.getWeek();
				endTime=logTimeUtil.getLastWeek();
			}else if(this.timeLimit.trim().equals("thisquarter")){//本季度
				startTime=logTimeUtil.getQuarter();
				endTime=logTimeUtil.getLastQuarter();
			} //否则开始结束时间都是有值的，即任意时间
			List<IrpLogs> list = this.irpLogsService.getAllDateByLogin(startTime,endTime);
			
			StringBuffer hoursStr = new StringBuffer();
			
			int hours1 = 0;
			int hours2 = 0;
			int hours3 = 0;
			int hours4 = 0;
			int hours5 = 0;
			int hours6 = 0;
			int hours7 = 0;
			int hours8 = 0;
			for(int i =0;i<list.size();i++){
				IrpLogs irpLogs = list.get(i);
				int hours = irpLogs.getLogoptime().getHours();
				if(hours>=3 && hours<6){
					hours1++;
				}else if(hours>=6 && hours<9){
					hours2++;			
				}else if(hours>=9 && hours<12){
					hours3++;
				}else if(hours>=12 && hours<15){	
					hours4++;
				}else if(hours>=15 && hours<18){
					hours5++;		
				}else if(hours>=18 && hours<21){	
					hours6++;
				}else if(hours>=21 && hours<24){
					hours7++;	
				}else{
					hours8++;
				}
				
			}
			hoursStr.append(hours8+","+hours1+","+hours2+","+hours3+","+hours4+","+hours5+","+hours6+","+hours7);
			
			hoursarray = hoursStr.toString();
			
			
			StringBuffer datytimesstr = new StringBuffer();
			datytimesstr.append((hours8+hours1)+","+(hours2+hours3)+","+(hours4+hours5)+","+(hours6+hours7));
			daytimes = datytimesstr.toString();
		}
		return SUCCESS;
	}
	/**
	 * 统计报表模块
	 * @return
	 * @author   npz
	 * @date 2017年9月6日
	 */
	public String getStatreport(){
		return SUCCESS;
	}
	/**
	 * 生成统计报表文档
	 * 
	 * @author   npz
	 * @return 
	 * @date 2017年9月6日
	 */
	public InputStream getExportstatreport(){
		InputStream is = null;
		SimpleDateFormat Format=new SimpleDateFormat("yyyy/MM/dd");
		Map<String,Object> statisticsmap = new HashMap<String,Object>();
		//日期
		if(this.timeLimit!=null){
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = " docpubtime desc";
			} else {
				_oOrderby = this.orderField;
			}
			LogTimeUtil logTimeUtil=new LogTimeUtil();
			if(this.timeLimit.trim().equals("thismonth")){//本月
				startTime=logTimeUtil.getMonth();
				endTime=logTimeUtil.getLastMonth();
			}else if (this.timeLimit.trim().equals("thisweek")) {//本周
				startTime=logTimeUtil.getWeek();
				endTime=logTimeUtil.getLastWeek();
			}else if(this.timeLimit.trim().equals("thisquarter")){//本季度
				startTime=logTimeUtil.getQuarter();
				endTime=logTimeUtil.getLastQuarter();
			} //否则开始结束时间都是有值的，即任意时间
			
			
		//知识统计
		List<IrpChnlDocLink> docList=irpChnl_Doc_LinkService.findChnlDocByTime(startTime, endTime,_oOrderby);
		statisticsmap.put("knowleagenum", docList.size());
		statisticsmap.put("starttime", Format.format(startTime));
		statisticsmap.put("endtime", Format.format(endTime));
		}

		//问答统计
		if(timeLimit!=null){
			if(timeLimit.equals("thisweek")){
				//本周
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getWeek(),ltu.getLastWeek());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getWeek(),ltu.getLastWeek());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getWeek(),ltu.getLastWeek());
				
		    }else if(timeLimit.equals("thismonth")){
				//本月
				//得到当月的天数
				Calendar  cale = Calendar.getInstance();
				cale.set(Calendar.DATE, 1);
				cale.roll(Calendar.DATE,-1);
				int maxdate = cale.get(Calendar.DATE);
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getMonth(),ltu.getLastMonth());
				
		    	
			}else if(timeLimit.equals("thisquarter")){
				//本季度
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				
			    	
			}else if(timeLimit.equals("otherdate")){
				//指定
				Date stattimes = null;
				Date endtimes = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				stattimes = startTime;
				endtimes = endTime;
				
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,stattimes,endtimes);
				
				
			}else{
				//本月
				//得到当月的天数
				Calendar  cale = Calendar.getInstance();
				cale.set(Calendar.DATE, 1);
				cale.roll(Calendar.DATE,-1);
				int maxdate = cale.get(Calendar.DATE);
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getMonth(),ltu.getLastMonth());
				}
			}else{
				//本月
				//得到当月的天数
				Calendar  cale = Calendar.getInstance();
				cale.set(Calendar.DATE, 1);
				cale.roll(Calendar.DATE,-1);
				int maxdate = cale.get(Calendar.DATE);
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getMonth(),ltu.getLastMonth());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getMonth(),ltu.getLastMonth());
			}
		    
		    
			statisticsmap.put("sumquestion", sumquestion);
			statisticsmap.put("sumanswer", sumanswer);
			statisticsmap.put("sumsalovequestion", sumsalovequestion);
				
			//系统访问统计
			IrpLogs checkirplog=new IrpLogs();
			checkirplog.setLogtype(0L);
			checkirplog.setLogobjtype("0");
			checkirplog.setLogobjid(-1L);
			checkirplog.setLoguser("");
			checkirplog.setLogresult(0);
			//操作类型	
			checkirplog.setLogoptype(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
			List<IrpLogs> logs=this.irpLogsService.findIrpLogsOfPage(null, null,checkirplog,startTime,endTime);
			statisticsmap.put("visitorcount", logs.size());
			//热门检索词统计
			List<IrpTag> irpTags=irpTagService.findAllTag(10);
			statisticsmap.put("allhotword", irpTags);
			//微知统计
			int micronum = this.irpMicroBlogService.getMicroblogcount(startTime,endTime);
			statisticsmap.put("micronum", micronum);
			//统计报表文件
			try {
				is= DocExport.exportMillCertificateWord(statisticsmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return is;
}



	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}



	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}



	public Long getSumquestion() {
		return sumquestion;
	}



	public void setSumquestion(Long sumquestion) {
		this.sumquestion = sumquestion;
	}



	public Long getSumanswer() {
		return sumanswer;
	}



	public void setSumanswer(Long sumanswer) {
		this.sumanswer = sumanswer;
	}



	public Long getSumsalovequestion() {
		return sumsalovequestion;
	}



	public void setSumsalovequestion(Long sumsalovequestion) {
		this.sumsalovequestion = sumsalovequestion;
	}
	}
