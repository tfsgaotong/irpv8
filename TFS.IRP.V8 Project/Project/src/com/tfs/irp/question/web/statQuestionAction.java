package com.tfs.irp.question.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.util.LogTimeUtil;

public class statQuestionAction extends ActionSupport {

	private IrpQuestionService irpQuestionService;
	
	private Long sumquestion;
	
	private Long sumanswer;
	
	private Long sumsalovequestion;
	
	private String startTime;
	
	private String endTime;
	
	private String timeLimit;
	
	private String xvalue;
	
	private String lineofquestion;
	
	private String lineofanswer;
	
	private String lineofsolve;
	
	
	public String getLineofquestion() {
		return lineofquestion;
	}

	public void setLineofquestion(String lineofquestion) {
		this.lineofquestion = lineofquestion;
	}

	public String getLineofanswer() {
		return lineofanswer;
	}

	public void setLineofanswer(String lineofanswer) {
		this.lineofanswer = lineofanswer;
	}

	public String getLineofsolve() {
		return lineofsolve;
	}

	public void setLineofsolve(String lineofsolve) {
		this.lineofsolve = lineofsolve;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getXvalue() {
		return xvalue;
	}

	public void setXvalue(String xvalue) {
		this.xvalue = xvalue;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
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

	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}

	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}
	/**
	 * 问答
	 * @return
	 */
	public String getStatQuestionTrend(){
		xvalue = "1,2,3,4,5";
		lineofquestion = "1,2,3,4,5";
		lineofanswer = "1,2,3,4,5";
		lineofsolve = "1,2,3,4,5";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbofquestion = new StringBuffer();
        StringBuffer sbofanswer = new StringBuffer();
        StringBuffer sbofsolve= new StringBuffer();
		if(timeLimit!=null){
			if(timeLimit.equals("thisweek")){
				//本周
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				System.out.println(ltu.getWeek());
				System.out.println(ltu.getLastWeek());
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getWeek(),ltu.getLastWeek());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getWeek(),ltu.getLastWeek());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getWeek(),ltu.getLastWeek());
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getWeek());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	for (int i = 0; i <7; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	xvalue = disposeViewX(sb.toString().substring(1)).substring(1);
		    	lineofquestion = sbofquestion.toString().substring(1);
		    	lineofanswer = sbofanswer.toString().substring(1);
		    	lineofsolve = sbofsolve.toString().substring(1);
				
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
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getMonth());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        for (int i = 0; i <maxdate; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	xvalue =disposeViewX(sb.toString().substring(1)).substring(1);
		    	lineofquestion = sbofquestion.toString().substring(1);
		    	lineofanswer = sbofanswer.toString().substring(1);
		    	lineofsolve = sbofsolve.toString().substring(1);
			}else if(timeLimit.equals("thisquarter")){
				//本季度
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				 //当前月份
				 long  datanums = 0;
				 datanums =  (ltu.getLastQuarter().getTime()-ltu.getQuarter().getTime())/(24*3600*1000);
					Calendar calender = Calendar.getInstance();
			        calender.setTime(ltu.getQuarter());
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        for (int i = 0; i <=datanums; i++){
			    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
			    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
			    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
			    		sb.append(",'"+sdf.format(calender.getTime())+"'");
			    		calender.add(Calendar.DAY_OF_MONTH,1);
					}
			    	xvalue = disposeViewX(sb.toString().substring(1)).substring(1);
			    	lineofquestion = sbofquestion.toString().substring(1);
			    	lineofanswer = sbofanswer.toString().substring(1);
			    	lineofsolve = sbofsolve.toString().substring(1); 
			}else if(timeLimit.equals("otherdate")){
				//指定
				Date stattimes = null;
				Date endtimes = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 try {
					 stattimes = sdf.parse(startTime);
					 endtimes = sdf.parse(endTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//总问题数
					sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
					//总回答数
					sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
					//总解决问题数
					sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,stattimes,endtimes);
				 //计算一个时间段 有多少天       
				Calendar calender = Calendar.getInstance();
				calender.setTime(stattimes);
				long datenum = (endtimes.getTime()-stattimes.getTime())/(24*3600*1000);
				for (int i = 0; i <=datenum; i++) {
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
					calender.add(Calendar.DAY_OF_MONTH,1);
				}
				xvalue = disposeViewX(sb.toString().substring(1)).substring(1);
		    	lineofquestion = sbofquestion.toString().substring(1);
		    	lineofanswer = sbofanswer.toString().substring(1);
		    	lineofsolve = sbofsolve.toString().substring(1);
		    	startTime = stattimes.toString();
		    	endTime = endtimes.toString();
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
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getMonth());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        for (int i = 0; i <maxdate; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	xvalue = disposeViewX(sb.toString().substring(1)).substring(1);
		    	lineofquestion = sbofquestion.toString().substring(1);
		    	lineofanswer = sbofanswer.toString().substring(1);
		    	lineofsolve = sbofsolve.toString().substring(1);
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
			Calendar calender = Calendar.getInstance();
	        calender.setTime(ltu.getMonth());
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        for (int i = 0; i <maxdate; i++){
	    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
	    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
	    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
	    		sb.append(",'"+sdf.format(calender.getTime())+"'");
	    		calender.add(Calendar.DAY_OF_MONTH,1);
			}
	    	xvalue = disposeViewX(sb.toString().substring(1)).substring(1);
	    	lineofquestion = sbofquestion.toString().substring(1);
	    	lineofanswer = sbofanswer.toString().substring(1);
	    	lineofsolve = sbofsolve.toString().substring(1);
		}
		
		return SUCCESS;
	}
	
	public Map<String,Object> getStatQuestion(){
		xvalue = "1,2,3,4,5";
		lineofquestion = "1,2,3,4,5";
		lineofanswer = "1,2,3,4,5";
		lineofsolve = "1,2,3,4,5";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbofquestion = new StringBuffer();
        StringBuffer sbofanswer = new StringBuffer();
        StringBuffer sbofsolve= new StringBuffer();
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
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getWeek());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	for (int i = 0; i <7; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	
				
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
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getMonth());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        for (int i = 0; i <maxdate; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	
			}else if(timeLimit.equals("thisquarter")){
				//本季度
				LogTimeUtil ltu = new LogTimeUtil();
				//总问题数
				sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总回答数
				sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				//总解决问题数
				sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,ltu.getQuarter(),ltu.getLastQuarter());
				 //当前月份
				 long  datanums = 0;
				 datanums =  (ltu.getLastQuarter().getTime()-ltu.getQuarter().getTime())/(24*3600*1000);
					Calendar calender = Calendar.getInstance();
			        calender.setTime(ltu.getQuarter());
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        for (int i = 0; i <=datanums; i++){
			    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
			    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
			    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
			    		sb.append(",'"+sdf.format(calender.getTime())+"'");
			    		calender.add(Calendar.DAY_OF_MONTH,1);
					}
			    	
			}else if(timeLimit.equals("otherdate")){
				//指定
				Date stattimes = null;
				Date endtimes = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 try {
					 stattimes = sdf.parse(startTime);
					 endtimes = sdf.parse(endTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//总问题数
					sumquestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
					//总回答数
					sumanswer = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION,stattimes,endtimes);
					//总解决问题数
					sumsalovequestion = irpQuestionService.getQuqestionTotalByCondition(IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION,stattimes,endtimes);
				 //计算一个时间段 有多少天       
				Calendar calender = Calendar.getInstance();
				calender.setTime(stattimes);
				long datenum = (endtimes.getTime()-stattimes.getTime())/(24*3600*1000);
				for (int i = 0; i <=datenum; i++) {
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
					calender.add(Calendar.DAY_OF_MONTH,1);
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
				Calendar calender = Calendar.getInstance();
		        calender.setTime(ltu.getMonth());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        for (int i = 0; i <maxdate; i++){
		    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
		    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
		    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
		    		sb.append(",'"+sdf.format(calender.getTime())+"'");
		    		calender.add(Calendar.DAY_OF_MONTH,1);
				}
		    	
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
			Calendar calender = Calendar.getInstance();
	        calender.setTime(ltu.getMonth());
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        for (int i = 0; i <maxdate; i++){
	    		sbofquestion.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISOTHERQUESTION));
	    		sbofanswer.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISANSWER,IrpQuestion.ISOTHERQUESTION));
	    		sbofsolve.append(","+this.irpQuestionService.getQuestionNumOfDate(calender.getTime(),IrpQuestion.ISQUESTION,IrpQuestion.ISBESTQUESTION));
	    		sb.append(",'"+sdf.format(calender.getTime())+"'");
	    		calender.add(Calendar.DAY_OF_MONTH,1);
			}
	    	
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sumquestion", sumquestion);
		map.put("sumanswer", sumanswer);
		map.put("sumsalovequestion", sumsalovequestion);
		return map;
	}
	/**
	* 得到指定月的天数
	* @return
	*/
	public  int getMonthDatas(int month)
	{
	Calendar a = Calendar.getInstance();
	a.set(Calendar.MONTH, month - 1);
	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	int maxDate = a.get(Calendar.DATE);
	return maxDate;
	}
	/**
	* 得到指定月的天数
	* @return
	*/
	public  int getMonthDatas(int month,int year)
	{
	Calendar a = Calendar.getInstance();
	a.set(Calendar.YEAR, year);
	a.set(Calendar.MONTH, month - 1);
	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	int maxDate = a.get(Calendar.DATE);
	return maxDate;
	}
	/**
	 * 处理x轴显示的值
	 * @param _value
	 * @return
	 */
    public String disposeViewX(String _value){
    	StringBuffer sbxvalue = new StringBuffer();
    	String xvaluearray[] = _value.split(",");
    	int findnum = xvaluearray.length/IrpQuestion.QUESTIONTOTALVIEW;
    	if(findnum==1){
    		findnum = 2;
    	}
    	if(findnum>0){
    		for (int i = 0; i < xvaluearray.length; i++) {
    			if(i%findnum==0){
    				sbxvalue.append(","+xvaluearray[i]);
    			}else{
    				sbxvalue.append(",''");
    			}
    		}
    	}else{
    		sbxvalue.append(","+_value);
    	}
    	return sbxvalue.toString();
	}
}
