package com.tfs.irp.chnl_doc_link.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.docscore.service.DocScoreService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.selectkey.entity.IrpSelectKey;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;

public class DocHighChartAction  extends ActionSupport {
	
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	
	private DocScoreService irpDocumentScoreService;
	
	private IrpDocumentService irpDocumentService;
	
	private IrpOpertypeService irpOpertypeService;
	
	private IrpUserService irpUserService;
	
	private IrpLogsService irpLogsService;
	
	private IrpUserValueLinkService irpUserValueLinkService;
	
	private IrpTagService irpTagService;
	
	private String  timeLimit;//thismonth本月 thisweek本周 thisquarter本季度 otherdate任意时间
	
	private Date startTime;
	
	private Date endTime;
	
	private String xaxisJasonString;
	
	private String amountJsonString; 
	
	private String privateAmountJsonString;
	
	private String orderField;
	
	private Integer keyAmount;
	
	private Long userId;
	
	private String userName;
	
	private String scoreJsonString;
	
	private String experienceJsonString;
	
	private Double sumScore;
	
	private Double sumExperence;

	private IrpMicroblogService irpMicroBlogService;
	
	
	/**
	 * 比较日期
	 * @param date 时间
	 * @param startDate 开始时间
	 * @param endDate  结束时间
	 * @return
	 */
	public boolean between(Date date,Date startDate,Date endDate){
		return date.getTime()>startDate.getTime() && date.getTime()<endDate.getTime();
	} 
	
	/**
	 * 设置横坐标的值
	 * @param limit
	 */
	public int setTimeBar(String limit){
		int amount =0;
		if(limit!=null){
			StringBuffer xAxis=new StringBuffer();
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			xAxis.append("[");
			LogTimeUtil logTimeUtil=new LogTimeUtil();
			if(limit.trim().equals("thismonth")){//本月  结束日期是到明天早上即包含今天
				startTime=logTimeUtil.getMonth();
				endTime=new Date();
				Long limitamount=endTime.getTime()-startTime.getTime();
				amount = (int)(limitamount/1000/60/60/24) ;
				calendar.setTime(startTime);
				xAxis.append("'"+format.format(calendar.getTime())+"'");
				for (int i = 0; i <amount; i++){
					calendar.add(Calendar.DAY_OF_MONTH,1);
					xAxis.append(","+"'"+format.format(calendar.getTime())+"'");
				}
			}else if (limit.trim().equals("thisweek")) {//本周 结束日期是到明天早上即包含今天
				startTime=logTimeUtil.getWeek();
				endTime=new Date();
				Long limitamount=endTime.getTime()-startTime.getTime();
				amount = (int)(limitamount/1000/60/60/24) ;
				calendar.setTime(startTime);
				xAxis.append(calendar.get(Calendar.DAY_OF_WEEK)-1);
				for (int i = 0; i <amount; i++){
					calendar.add(Calendar.DAY_OF_MONTH,1);
					xAxis.append(","+(calendar.get(Calendar.DAY_OF_WEEK)-1));
				}
			}else if(limit.trim().equals("thisquarter")){//本季度 结束日期是到明天早上即包含今天
				startTime=logTimeUtil.getQuarter();
				endTime=new Date();
				Long limitamount=endTime.getTime()-startTime.getTime();
				amount = (int)(limitamount/1000/60/60/24) ;
				calendar.setTime(startTime);
				xAxis.append("'"+format.format(calendar.getTime())+"'");
				for (int i = 0; i <amount; i++){
					calendar.add(Calendar.DAY_OF_MONTH,1);
					String nextDate=format.format(calendar.getTime());
					xAxis.append(",'"+nextDate+"'"); 
				}
			}else{ 
				Long limitamount=endTime.getTime()-startTime.getTime();
				amount = (int)(limitamount/1000/60/60/24) ;
				calendar.setTime(startTime);
				xAxis.append("'"+format.format(startTime)+"'");
				for (int i = 0; i <amount; i++){
					calendar.add(Calendar.DAY_OF_MONTH,1);
					String nextDate=format.format(calendar.getTime());
					xAxis.append(",'"+nextDate+"'");
				}
			}
			xAxis.append("]");
			this.setXaxisJasonString(xAxis.toString());
		}
 		return amount;
	}
 
	//获取知识发布量统计图
	public String everyDayPublishDocumentAmount(){
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
			List<IrpChnlDocLink> docList=irpChnl_Doc_LinkService.findChnlDocByTime(startTime, endTime,_oOrderby);
			StringBuffer amountStr=new StringBuffer();
			StringBuffer privateAmountStr=new StringBuffer();
			//获取横坐标可以不需要横坐标，横坐标自动每走一步
			int amount=setTimeBar(this.timeLimit);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			if(docList!=null){  
				amountStr.append("[");
				privateAmountStr.append("[");
				boolean b=false;
				for (int i = 0; i <=amount; i++) {
					int pubAmount=0;//企业
					int pubPrivateAmount=0;//个人
					for (int j = 0; j <docList.size(); j++) {
						Date docPubTime=docList.get(j).getDocpubtime();
						String doc=dateFormat.format(docPubTime);
						String time=dateFormat.format(calendar.getTime());
						if(doc.equals(time)){//如果相同就加1
							if(docList.get(j).getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID.longValue()){//私人站点
								pubPrivateAmount++;
							}else{
								pubAmount++;
							}
						}
					}
					if(b){
						amountStr.append(","+pubAmount);
						privateAmountStr.append(","+pubPrivateAmount);
					}
					else {
						amountStr.append(pubAmount);
						privateAmountStr.append(pubPrivateAmount);
					}
					 b=true;
					 pubAmount=0;
					 calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				amountStr.append("]");
				privateAmountStr.append("]");
			}
			amountJsonString=amountStr.toString();
			privateAmountJsonString=privateAmountStr.toString();
		}
		return SUCCESS;
	}
	//获取知识质量图
	public String documentQuality(){
		StringBuffer buffer=new StringBuffer();
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
			int one=irpDocumentService.countDocument(new BigDecimal(0.5),new BigDecimal(1.5),startTime,endTime);
			int two=irpDocumentService.countDocument(new BigDecimal(1.5),new BigDecimal(2.5),startTime,endTime);
			int three=irpDocumentService.countDocument(new BigDecimal(2.5),new BigDecimal(3.5),startTime,endTime);
			int four=irpDocumentService.countDocument(new BigDecimal(3.5),new BigDecimal(4.5),startTime,endTime);
			int five=irpDocumentService.countDocument(new BigDecimal(4.5),new BigDecimal(5.5) ,startTime,endTime);
			int other=irpDocumentService.countDocument(new BigDecimal(0), new BigDecimal(0.1),startTime,endTime);
			buffer.append(one+","+two+","+three+","+four+","+five+","+other);
			amountJsonString=buffer.toString();
		}
		return  SUCCESS;
	}
	//知识分数统计图
	public String documentSctter(){
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
			List<IrpDocument> documents=irpDocumentService.sctterDocument(startTime, endTime,IrpSite.PUBLIC_SITE_ID);
			if(documents!=null){
				StringBuffer buffer=new StringBuffer();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				
				for (int i = 0; i < documents.size(); i++) {
					IrpDocument document=documents.get(i);
					Date pubTime=document.getDocpubtime();
					String dateStr=dateFormat.format(pubTime);
					try {
						pubTime=dateFormat.parse(dateStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}  
					float score=0;
					Calendar cal = Calendar.getInstance(TimeZone.getDefault());//TimeZone.getDefault()获取主机的默认 TimeZone，即时区偏移量。
					Long trueSecond=pubTime.getTime()+cal.getTimeZone().getRawOffset();
					if(document.getAvgscore()!=null){
						 score=document.getAvgscore().floatValue();
					}	
					buffer.append("["+trueSecond+","+score+"],");
				}
				if(buffer!=null &&buffer.length()>0){
					amountJsonString=buffer.substring(0, buffer.length()-1);
				}
			}
		}
		return SUCCESS;
	}
	//获取知识访问量 统计图
	public void documentVisitCount(){
		if(this.timeLimit!=null){
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
			}//否则开始结束时间都是有值的，即任意时间
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = " docpubtime desc";
			} else {
				_oOrderby = this.orderField;
			}
		}
	}
	
	//获取发布微知数量
	public String everyDayPublishMicroblogAmount(){
		if(this.timeLimit!=null){
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = " crtime desc";
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
			List<IrpMicroblog> microblogList=irpMicroBlogService.findMicroblogByTime(startTime, endTime, _oOrderby);
			StringBuffer privateAmountStr=new StringBuffer();
			//获取横坐标可以不需要横坐标，横坐标自动每走一步
			int amount=setTimeBar(this.timeLimit);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			if(microblogList!=null){
				privateAmountStr.append("[");
				boolean b=false;
				for (int i = 0; i <=amount; i++) {
					int pubPrivateAmount=0;//个人
					for (int j = 0; j <microblogList.size(); j++) {
						Date docPubTime=microblogList.get(j).getCrtime();
						String doc=dateFormat.format(docPubTime);
						String time=dateFormat.format(calendar.getTime());
						if(doc.equals(time)){//如果相同就加1
							pubPrivateAmount++;
						}
					}
					if(b){
						privateAmountStr.append(","+pubPrivateAmount);
					}
					else {
						privateAmountStr.append(pubPrivateAmount);
					}
					 b=true;
					 calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				privateAmountStr.append("]");
			}
			privateAmountJsonString=privateAmountStr.toString();
		}
		return SUCCESS;
	}
	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	/**
	 * 获取今天开始时间
	 */
	public Date getThisDayStart(){
		 Calendar c = Calendar.getInstance();
		 Date date=c.getTime();
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		 return date;
	}
	/**
	 * 获取今天后的日期
	 */
	public Date getThisDayEnd(int day){
		 Calendar c = Calendar.getInstance();
		 c.add(Calendar.DATE,day);
		 Date date=c.getTime();
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		 return date;
	}
	//系统活跃度
	public String systemVitality(){
		if(this.timeLimit!=null){
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
			}else if(this.timeLimit.trim().equals("thisday")){//今天
				startTime=this.getThisDayStart();
				endTime=this.getThisDayEnd(1);
			}else if(this.timeLimit.trim().equals("yesterday")){//昨天
				startTime=this.getThisDayEnd(-1);
				endTime=this.getThisDayStart();
			}
			//否则开始结束时间都是有值的，即任意时间
			//获取某段时间人登陆的次数
//			int loginCount=irpLogsService.findCountByExpert(startTime, endTime, irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
//			//获取系统正常的用户总数
			int userSum=irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG);
//			amountJsonString=String.valueOf((int)((loginCount*1.0/userSum)*100));
			/***********************/
			IrpLogs checkirplog=new IrpLogs();
			checkirplog.setLogtype(0L);
			checkirplog.setLogobjtype("0");
			checkirplog.setLogobjid(-1L);
			checkirplog.setLoguser("");
			checkirplog.setLogresult(0);
			Long limitamount=endTime.getTime()-startTime.getTime();
			//操作类型	
			checkirplog.setLogoptype(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
			List<IrpLogs> logs=this.irpLogsService.findIrpLogsOfPage(null, null,checkirplog,startTime,endTime);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			int sumPerson=0;//某个时间差之内的每天登陆人数求和
			int amount = (int)(limitamount/1000/60/60/24) ;///时间差
			for (int i = 0; i <=amount; i++) {
				List<Long> userIds=new ArrayList<Long>();
				for (int j = 0; j < logs.size(); j++) {
					IrpLogs log=logs.get(j);
					Date pubTime=log.getLogoptime();
					String dateStr=dateFormat.format(pubTime);
					String time=dateFormat.format(calendar.getTime());
					Long userId=log.getLogobjid();
					if(dateStr.equals(time) &&!userIds.contains(userId.longValue())){//如果日期和人不相同，加1,并且记录一下这个人的ID
						userIds.add(userId.longValue());
						sumPerson++;
					}
				}
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			double data=0;
		if(this.timeLimit.equals("thisday")||this.timeLimit.equals("yesterday") ||amount<2){
			data=(sumPerson*1.0d/userSum)*100;//每天登陆人数求和/时间差/总人数*100%==系统活跃度
		}else{
			data=(sumPerson*1.0)/(amount*userSum)*100;//每天登陆人数求和/时间差/总人数*100%==系统活跃度
		}
		BigDecimal bigDecimal= new BigDecimal(data);
		amountJsonString=String.valueOf(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		return SUCCESS;
	} 
	//系统访问量
	public String systemVisitedAmount(){
		if(this.timeLimit!=null){
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
			IrpLogs checkirplog=new IrpLogs();
			checkirplog.setLogtype(0L);
			checkirplog.setLogobjtype("0");
			checkirplog.setLogobjid(-1L);
			checkirplog.setLoguser("");
			checkirplog.setLogresult(0);
			
			
			//操作类型	
			checkirplog.setLogoptype(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
			List<IrpLogs> logs=this.irpLogsService.findIrpLogsOfPage(null, null,checkirplog,startTime,endTime);
			
			StringBuffer buffer=new StringBuffer();
			StringBuffer privateBuffer=new StringBuffer();
			int amount=setTimeBar(this.timeLimit);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			for (int j = 0; j <=amount; j++) {
				int sum=0;
				int personSum=0;
				List<Long> userIds=new ArrayList<Long>();
				for (int i = 0; i <logs.size(); i++) {
					IrpLogs irpLogs=logs.get(i);
					Date pubTime=irpLogs.getLogoptime();
					String dateStr=dateFormat.format(pubTime);
					Long personId=irpLogs.getLogobjid();
					String time=dateFormat.format(calendar.getTime());
					if(dateStr.equals(time)){//如果相同就加1
						 sum++;
						 //每天访问人数统计
						 if(userIds!=null &&userIds.size()>0 && userIds.contains(personId.longValue())){
							 personSum++;
						 }else{
							 userIds.add(personId.longValue());
							 personSum=1;
						 }
					} 
				}
				 buffer.append(sum+",");
				 privateBuffer.append(personSum+",");
				 calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			if(buffer!=null &&buffer.length()>0){
				amountJsonString="["+buffer.substring(0, buffer.length()-1)+"]";
			}
			if(privateBuffer!=null && privateBuffer.length()>0){
				privateAmountJsonString="["+privateBuffer.substring(0, privateBuffer.length()-1)+"]";
			}
		}  
			return SUCCESS;
	}
	///获取知识类型积分集合
	public List<String> getDocumentType(){
		List<String> docs=new ArrayList<String>();//知识类型集合
		docs.add("REPLY_KNOWLEDGE_AUTCHER");
		docs.add("REPLY_KNOWLEDGE");
		docs.add("KNOWLEDGE_DELETE");
		docs.add("CAI_QUESTION_WITHME");
		docs.add("KNOW_DIGEST");
		docs.add("KNOWLEDGE_CANCEL_COLLECT");
		docs.add("KNOWLEDGE_INFORM_AUTCHER");
		docs.add("KNOWLEDGE_INFORM");
		docs.add("CAI_QUESTION");
		docs.add("DING_KNOWLEDGE_AUTCHER");
		docs.add("DING_KNOWLEDGE");
		docs.add("COLLECTION_KNOWLEDGE");
		docs.add("KNOWLEDGE_RECOMMEND");
		docs.add("COLLECTION_KNOWLEDGE_AUTCHER");
		docs.add("BROWSE_KNOWLEDGE_TOMEEXPER");
		docs.add("BROWSE_KNOWLEDGE_TOAUTCHER");
		docs.add("RECOMMEND_KNOWLEDGE_ME");
		docs.add("DOCUMENT_ADD");
		docs.add("KNOWLEDGE_ADD");
		docs.add("USER_LOGIN");
		docs.add("KNOWLEDGE_SCORE_FIVE_TOAUTCHER");
		docs.add("KNOWLEDGE_SCORE_ONE_TOAUTCHER");
		docs.add("KNOWLEDGE_SCORE");
		docs.add("KNOWLEDGE_SUBSCRIBE");
		docs.add("KNOWLEDGE_CANCLE_SUBSCRIBE");
		docs.add("MICROBLOG_FOCUS");
		docs.add("MICROBLOG_CANCLE_FOCUS");
		return docs;
	}
	//获取问答类型积分集合
	public List<String> getQuestionType(){
		List<String> questions=new ArrayList<String>();
		questions.add("QUESTION_CAI");
		questions.add("QUESTION_ANSWER");
		questions.add("BEST_ANSWER");
		questions.add("QUESTION_DNG");
		questions.add("QUESTION_CAI_AUTCHER");
		questions.add("QUESTION_DING_AUTCHER");
		questions.add("QUESTION_ASK");
		questions.add("QUESTION_BROWSE");
		questions.add("QUESTION_CHANGE_KNOWLEDGE");
		return questions;
	}
		//获取微知类型积分集合
	public List<String> getMicroType(){
		List<String> micos=new ArrayList<String>();
		micos.add("MICROBLOG_TRAN_TOME");
		micos.add("REPORT_PASS");
		micos.add("SHARE_MICROBLOG");
		micos.add("MICROBLOG_TRAN_AUTCHER");
		micos.add("COLLECT_MICROBLOG_TO");
		micos.add("COLLECT_MICROBLOG");
		return  micos;
	}
	
	//获取bug类型积分集合
	public List<String> getBugType(){
		List<String> bugs=new ArrayList<String>();
		bugs.add("PROJECT_BUG_NEW");
		bugs.add("BUG_NEW");
		bugs.add("BUG_DEL");
		bugs.add("BUG_DATILWITH");
		
		return  bugs;
	}
	//获取投票类型积分集合
		public List<String> getVoteType(){
			List<String> votes=new ArrayList<String>();
			votes.add("VOTE_NEW");
			votes.add("VOTE_TOKEN");
			votes.add("VOTE_NEW_PIC");
			votes.add("VOTE_NEW_OPIN");
			votes.add("VOTE_TOKEN_OPIN");
			return  votes;
		}
	//获取系统积分统计
	public String systemValueLink(){
		if(this.timeLimit!=null){
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
			List<IrpUserValueLink> links=irpUserValueLinkService.findDataByExpert(startTime, endTime,null);
			List<String> docs=getDocumentType();
			List<String> micros=getMicroType();
			List<String> ques=getQuestionType();
			int amount=setTimeBar(this.timeLimit);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			StringBuffer amountStr=new StringBuffer();
			StringBuffer privateAmountStr=new StringBuffer();
			if(links!=null){  
				boolean b=false;
				for (int i = 0; i <=amount; i++) {
					Long docAmount=0L;//积分
					Long docPrivateAmount=0L;//经验 
					for (int j = 0; j < links.size(); j++) {
						IrpUserValueLink link=links.get(j);
						String valueKey=link.getValueckey();
						Long score=link.getScore();
						Long experience=link.getExperience();
						Date crtime= link.getCrtime();
						String dateStr=dateFormat.format(crtime);
						String time=dateFormat.format(calendar.getTime());
						if(dateStr.equals(time)){//如果相同就加1
							if(docs.contains(valueKey) ||micros.contains(valueKey) ||ques.contains(valueKey)){
								docAmount=docAmount+score;
								docPrivateAmount=docPrivateAmount+experience;
							} 
						}
					}
					amountStr.append(docAmount+",");
					privateAmountStr.append(docPrivateAmount+",");
					calendar.add(Calendar.DAY_OF_MONTH, 1);
			}	
			}
			if(amountStr!=null && amountStr.length()>0){
				amountJsonString="["+amountStr.substring(0,amountStr.length()-1)+"]";
			}
			if(privateAmountStr!=null && privateAmountStr.length()>0){
				privateAmountJsonString="["+privateAmountStr.substring(0,privateAmountStr.length()-1)+"]";
			}
		}
		//查询系统总积分
		IrpUser irpUser=irpUserService.findSumScoreAndSumExperence();
		sumScore=Double.valueOf(irpUser.getSumscore());
		sumExperence=Double.valueOf(irpUser.getSumexperience());
		return SUCCESS;
	}
	//热门检索词
	public String hotSelectKey(){
		if(keyAmount!=null && keyAmount!=0){
			List<IrpTag> irpTags=irpTagService.findAllTag(keyAmount);
			StringBuffer buffer=new StringBuffer();
			if(irpTags!=null && irpTags.size()>0){
				for (int i = 0; i < irpTags.size(); i++) {
					IrpTag irpTag=irpTags.get(i);
					try {
						buffer.append(URLEncoder.encode(irpTag.getTagname(), "UTF-8")+","+irpTag.getNcount()+"-");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} 
				if(buffer!=null && buffer.length()>0){
					amountJsonString=""+buffer.substring(0,buffer.length()-1)+"";
				}
			}
		}
		return SUCCESS;
	}
	//某个人的积分统计
	public String personValueExperience(){
		if(this.timeLimit!=null){
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
				SimpleDateFormat aa=new SimpleDateFormat("yyyy-MM-dd");
			} //否则开始结束时间都是有值的，即任意时间
			if(userId==null || userId.longValue()==0L){
				userId=LoginUtil.getLoginUserId();
				userName=LoginUtil.getUserNameString(LoginUtil.getLoginUser());
			}
			List<IrpUserValueLink> links=irpUserValueLinkService.findDataByExpert(startTime, endTime,userId);
			List<String> docs=getDocumentType();
			List<String> micros=getMicroType();
			List<String> ques=getQuestionType();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			int amount=setTimeBar(this.timeLimit);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			StringBuffer amountStr=new StringBuffer();
			StringBuffer privateAmountStr=new StringBuffer();
			if(links!=null){  
				Long allDocScore=0L;
				Long allDocExperience=0L;
				Long allMicroScore=0L;
				Long allMicroExperience=0L;
				Long allQuesScore=0L;
				Long allQuesExperience=0L;
				for (int i = 0; i <=amount; i++) {
					Long docAmount=0L;//积分
					Long docPrivateAmount=0L;//经验
					Long quesAmount=0L;//积分
					Long quesPrivateAmount=0L;//经验
					Long micoAmount=0L;//积分
					Long micoPrivateAmount=0L;//经验
					for (int j = 0; j < links.size(); j++) {
						IrpUserValueLink link=links.get(j);
						String valueKey=link.getValueckey();
						Long score=link.getScore();
						Long experience=link.getExperience();
						Date crtime= link.getCrtime();
						String dateStr=dateFormat.format(crtime);
						String time=dateFormat.format(calendar.getTime());
						if(dateStr.equals(time)){//如果相同就加1
							if(docs.contains(valueKey)){
								docAmount=docAmount+score;
								docPrivateAmount=docPrivateAmount+experience;
							}else if(micros.contains(valueKey)){
								micoAmount=micoAmount+score;
								micoPrivateAmount=micoPrivateAmount+experience;
							}else if(ques.contains(valueKey)){
								quesAmount=quesAmount+score;
								quesPrivateAmount=quesPrivateAmount+experience;
							}
						}
					}
					allDocScore=allDocScore+docAmount;
					allDocExperience = allDocExperience +docPrivateAmount;
					
					allMicroScore=allMicroScore+micoAmount;
					allMicroExperience = allMicroExperience +micoPrivateAmount;

					allQuesScore=allQuesScore+quesAmount;
					allQuesExperience = allQuesExperience +quesPrivateAmount;
					
					amountStr.append(docAmount+micoAmount+quesAmount+",");//总积分
					privateAmountStr.append(docPrivateAmount+micoPrivateAmount+quesPrivateAmount+",");//总经验
					calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
				this.scoreJsonString=allDocScore+","+allMicroScore+","+allQuesScore;
				this.experienceJsonString=allDocExperience+","+allMicroExperience+","+allQuesExperience;
			}
			if(amountStr!=null && amountStr.length()>0){
				amountJsonString="["+amountStr.substring(0,amountStr.length()-1)+"]";
			}
			if(privateAmountStr!=null && privateAmountStr.length()>0){
				privateAmountJsonString="["+privateAmountStr.substring(0,privateAmountStr.length()-1)+"]";
			}
		}
		return  SUCCESS;
	}
	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getAmountJsonString() {
		return amountJsonString;
	}
	public void setAmountJsonString(String amountJsonString) {
		this.amountJsonString = amountJsonString;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
 
	public String getOrderField() {
		return orderField;
	}
	public String getXaxisJasonString() {
		return xaxisJasonString;
	}
	public void setXaxisJasonString(String xaxisJasonString) {
		this.xaxisJasonString = xaxisJasonString;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public DocScoreService getIrpDocumentScoreService() {
		return irpDocumentScoreService;
	}

	public void setIrpDocumentScoreService(DocScoreService irpDocumentScoreService) {
		this.irpDocumentScoreService = irpDocumentScoreService;
	}

	public String getPrivateAmountJsonString() {
		return privateAmountJsonString;
	}

	public void setPrivateAmountJsonString(String privateAmountJsonString) {
		this.privateAmountJsonString = privateAmountJsonString;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpLogsService getIrpLogsService() {
		return irpLogsService;
	}

	public void setIrpLogsService(IrpLogsService irpLogsService) {
		this.irpLogsService = irpLogsService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public Double getSumScore() {
		return sumScore;
	}

	public Double getSumExperence() {
		return sumExperence;
	}

	public void setSumScore(Double sumScore) {
		this.sumScore = sumScore;
	}

	public void setSumExperence(Double sumExperence) {
		this.sumExperence = sumExperence;
	}

	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}

	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}

	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}

	public IrpTagService getIrpTagService() {
		return irpTagService;
	}

	public String getScoreJsonString() {
		return scoreJsonString;
	}

	public String getExperienceJsonString() {
		return experienceJsonString;
	}

	public void setScoreJsonString(String scoreJsonString) {
		this.scoreJsonString = scoreJsonString;
	}

	public void setExperienceJsonString(String experienceJsonString) {
		this.experienceJsonString = experienceJsonString;
	}

	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}

	public Integer getKeyAmount() {
		return keyAmount;
	}

	public void setKeyAmount(Integer keyAmount) {
		this.keyAmount = keyAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	
}
