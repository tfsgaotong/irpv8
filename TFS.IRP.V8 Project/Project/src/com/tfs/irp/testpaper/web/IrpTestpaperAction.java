package com.tfs.irp.testpaper.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.service.IrpExamService;
import com.tfs.irp.examanswer.entity.IrpExamAnswer;
import com.tfs.irp.examanswer.service.IrpExamAnswerService;
import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.examrecord.service.IrpExamRecordService;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaper;
import com.tfs.irp.grouptestpaper.service.IrpGroupTestpaperService;
import com.tfs.irp.questionbank.entity.IrpQuestionBank;
import com.tfs.irp.questionbank.service.IrpQuestionBankService;
import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.testpaper.service.IrpTestpaperService;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTestpaperAction extends ActionSupport {
	
	private IrpTestpaperService irpTestpaperService;
	
	private IrpQuestionBankService irpQuestionBankService;
	
	private String checkstrids;
	
	private IrpTestpaper irpTestPaper;

	private List<IrpTestpaper> irpTestpaperlist;
	
	private IrpUserService irpUserService;
	
	private Integer pagenumpaper = 1;
	
	private String paperpagehtml;
	
	private Long paper; 
	
	private IrpTestpaper irpTestpaper;
	
	private String[] qbankbids;
	
	private Integer pageadminnum = 1;
	
	private Long paperid;
	
	private String qtestpidstr;
	
	private Long updatepid;
	
	private String grouptids;
	
	private IrpExamService irpExamService;
	
	private IrpExam irpExam;
	
	private IrpExamAnswerService irpExamAnswerService;
	
	private IrpExamRecordService irpExamRecordService;
	
	private Long recordid;
	
	private IrpExamRecord irpExamRecord;
	
	private int choicenum;
	
	private int boolquesnum;
	
	private int fullquesnum;
	
	
	
    public int getChoicenum() {
		return choicenum;
	}

	public void setChoicenum(int choicenum) {
		this.choicenum = choicenum;
	}

	public int getBoolquesnum() {
		return boolquesnum;
	}

	public void setBoolquesnum(int boolquesnum) {
		this.boolquesnum = boolquesnum;
	}

	public int getFullquesnum() {
		return fullquesnum;
	}

	public void setFullquesnum(int fullquesnum) {
		this.fullquesnum = fullquesnum;
	}

	public IrpExamRecord getIrpExamRecord() {
		return irpExamRecord;
	}

	public void setIrpExamRecord(IrpExamRecord irpExamRecord) {
		this.irpExamRecord = irpExamRecord;
	}

	public Long getRecordid() {
		return recordid;
	}

	public void setRecordid(Long recordid) {
		this.recordid = recordid;
	}

	public IrpExamAnswerService getIrpExamAnswerService() {
		return irpExamAnswerService;
	}

	public void setIrpExamAnswerService(IrpExamAnswerService irpExamAnswerService) {
		this.irpExamAnswerService = irpExamAnswerService;
	}

	public IrpExamRecordService getIrpExamRecordService() {
		return irpExamRecordService;
	}

	public void setIrpExamRecordService(IrpExamRecordService irpExamRecordService) {
		this.irpExamRecordService = irpExamRecordService;
	}

	public IrpExam getIrpExam() {
		return irpExam;
	}

	public void setIrpExam(IrpExam irpExam) {
		this.irpExam = irpExam;
	}

	public IrpExamService getIrpExamService() {
		return irpExamService;
	}

	public void setIrpExamService(IrpExamService irpExamService) {
		this.irpExamService = irpExamService;
	}

	public String getGrouptids() {
		return grouptids;
	}

	public void setGrouptids(String grouptids) {
		this.grouptids = grouptids;
	}
	private IrpGroupTestpaperService irpGroupTestpaperService;
    
    public IrpGroupTestpaperService getIrpGroupTestpaperService() {
		return irpGroupTestpaperService;
	}

	public void setIrpGroupTestpaperService(
			IrpGroupTestpaperService irpGroupTestpaperService) {
		this.irpGroupTestpaperService = irpGroupTestpaperService;
	}
	public Long getUpdatepid() {
		return updatepid;
	}

	public void setUpdatepid(Long updatepid) {
		this.updatepid = updatepid;
	}

	public String getQtestpidstr() {
		return qtestpidstr;
	}

	public void setQtestpidstr(String qtestpidstr) {
		this.qtestpidstr = qtestpidstr;
	}

	public Long getPaperid() {
		return paperid;
	}

	public void setPaperid(Long paperid) {
		this.paperid = paperid;
	}

	public Integer getPageadminnum() {
		return pageadminnum;
	}

	public void setPageadminnum(Integer pageadminnum) {
		this.pageadminnum = pageadminnum;
	}
	private String orderField = "";

	private String orderBy = "";
	
	private String pageHTML = "";
	
	private String searchWord  = "";
	
	private String searchType = "";
	
	private Long cateid;

	public Long getCateid() {
		return cateid;
	}

	public void setCateid(Long cateid) {
		this.cateid = cateid;
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

	public String[] getQbankbids() {
		return qbankbids;
	}

	public void setQbankbids(String[] qbankbids) {
		this.qbankbids = qbankbids;
	}

	public IrpTestpaper getIrpTestpaper() {
		return irpTestpaper;
	}

	public void setIrpTestpaper(IrpTestpaper irpTestpaper) {
		this.irpTestpaper = irpTestpaper;
	}

	public Long getPaper() {
		return paper;
	}

	public void setPaper(Long paper) {
		this.paper = paper;
	}

	public String getPaperpagehtml() {
		return paperpagehtml;
	}

	public void setPaperpagehtml(String paperpagehtml) {
		this.paperpagehtml = paperpagehtml;
	}

	public Integer getPagenumpaper() {
		return pagenumpaper;
	}

	public void setPagenumpaper(Integer pagenumpaper) {
		this.pagenumpaper = pagenumpaper;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpTestpaper> getIrpTestpaperlist() {
		return irpTestpaperlist;
	}

	public void setIrpTestpaperlist(List<IrpTestpaper> irpTestpaperlist) {
		this.irpTestpaperlist = irpTestpaperlist;
	}

	public IrpTestpaper getIrpTestPaper() {
		return irpTestPaper;
	}

	public void setIrpTestPaper(IrpTestpaper irpTestPaper) {
		this.irpTestPaper = irpTestPaper;
	}

	public String getCheckstrids() {
		return checkstrids;
	}

	public void setCheckstrids(String checkstrids) {
		this.checkstrids = checkstrids;
	}

	public IrpTestpaperService getIrpTestpaperService() {
		return irpTestpaperService;
	}

	public IrpQuestionBankService getIrpQuestionBankService() {
		return irpQuestionBankService;
	}

	public void setIrpQuestionBankService(
			IrpQuestionBankService irpQuestionBankService) {
		this.irpQuestionBankService = irpQuestionBankService;
	}

	public void setIrpTestpaperService(IrpTestpaperService irpTestpaperService) {
		this.irpTestpaperService = irpTestpaperService;
	}
	
	/**
	 * 链接到创建试卷表单
	 * @return
	 */
	public String linkCreateTPaper(){
		
		
		return SUCCESS;
	}
	/**
	 * 链接到选择题题库
	 * @return
	 */
	public String linkClientChoice(){
		
		
		return SUCCESS;
	}
	/**
	 * 根据id查询题库对象
	 * @param _qbankid
	 * @return
	 */
	public IrpQuestionBank getIrpQuestionBankById(Long _qbankid){
		IrpQuestionBank irpQuestionBank = null;
		if (_qbankid!=null) {
			irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(_qbankid);
		}
		return irpQuestionBank;
	}
	/**
	 * 根据id查询题库对象
	 * @param _qbankid
	 * @return
	 */
	public IrpQuestionBank getIrpQuestionBankByIdStr(String _qbankid){
		IrpQuestionBank irpQuestionBank = null;
		if (_qbankid!=null) {
			irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(Long.parseLong(_qbankid));
		}
		return irpQuestionBank;
	}
	/**
	 * 根据id集合返回对象
	 * @return
	 */
	public void findQBankByIds(){
		if (checkstrids.length()>0) {
			String[] ckids = checkstrids.split(",");
			List list = new ArrayList();
			for (int i = 0; i < ckids.length; i++) {
				IrpQuestionBank irpQuestionBank = getIrpQuestionBankById(Long.parseLong(ckids[i]));
				list.add("{\"id\":\""+irpQuestionBank.getQbankid().toString().trim()+"\"");
				list.add("\"score\":\""+irpQuestionBank.getQbscore().toString().trim()+"\"");
				list.add("\"type\":\""+irpQuestionBank.getQbtype().toString().trim()+"\"");
				String content = irpQuestionBank.getQuestiontext().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\r", "").replaceAll("\\s", "");
				list.add("\"text\":\""+content.trim()+"\"}"); 
			}
			ActionUtil.writer(list.toString());
		}
	}
	private int multiselectnum;
	public int getMultiselectnum() {
		return multiselectnum;
	}

	public void setMultiselectnum(int multiselectnum) {
		this.multiselectnum = multiselectnum;
	}

	/**
	 * 随机组卷 生成指定数量的题型
	 * 
	 * @author   npz
	 * @date 2017年8月3日
	 */
	public void randomtestpaper(){
		List<IrpQuestionBank> questionlist = this.irpQuestionBankService.randomquestion(multiselectnum,choicenum, boolquesnum, fullquesnum);
		List list = new ArrayList();
		for(IrpQuestionBank question:questionlist){
			list.add("{\"id\":\""+question.getQbankid().toString().trim()+"\"");
			list.add("\"score\":\""+question.getQbscore().toString().trim()+"\"");
			list.add("\"type\":\""+question.getQbtype().toString().trim()+"\"");
			String content = question.getQuestiontext().replace("\"", "\\\"").replace("\\\\\"","\\\"");
			list.add("\"text\":\""+content.trim()+"\"}");
		}
		ActionUtil.writer(list.toString());
		
		
	}
	/**
	 * 增加试卷
	 * @return
	 */
	public String addTestPaper(){
		IrpTestpaper irpTestPapers = new IrpTestpaper();
		irpTestPapers.setPaperid(TableIdUtil.getNextId(irpTestPapers));
		irpTestPapers.setCrtime(Calendar.getInstance().getTime());
		irpTestPapers.setCruserid(LoginUtil.getLoginUserId());

		irpTestPapers.setExtendsone(irpTestPaper.getExtendsone());
		irpTestPapers.setPaperstatus(irpTestPaper.getPaperstatus());
		irpTestPapers.setPapercontent(irpTestPaper.getPapercontent());
		irpTestPapers.setPapertime(irpTestPaper.getPapertime());
		irpTestPapers.setPapertitle(irpTestPaper.getPapertitle());
		irpTestPapers.setPaperdesc(irpTestPaper.getPaperdesc());
		irpTestPapers.setPapercate(irpTestPaper.getPapercate());
		
		int msg = this.irpTestpaperService.addTestPaper(irpTestPapers);
		
		return SUCCESS;
	}

	/**
	 * 链接到考试主页面
	 * @return
	 */
	public String linkExamMenu(){
    	if (cateid==null) {
    		cateid = 16l;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "paperid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int num = this.irpTestpaperService.getIrpTestpaperListNum(cateid,IrpTestpaper.YESFABU,searchWord,searchType);
		
		PageUtil pageUtil = new PageUtil(pagenumpaper, 10, num);
		
		irpTestpaperlist = this.irpTestpaperService.getIrpTestpaperList(cateid, IrpTestpaper.YESFABU, pageUtil, _oOrderby, searchWord, searchType);
		
		this.paperpagehtml = pageUtil.getClientPageHtml("pagePaper(#PageNum#)");
		
		return SUCCESS;
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
     * 链接到考试页面
     * @return
     */
    private Long exam;
    public Long getExam() {
		return exam;
	}

	public void setExam(Long exam) {
		this.exam = exam;
	}
	private long moretime;
	
	public long getMoretime() {
		return moretime;
	}

	public void setMoretime(long moretime) {
		this.moretime = moretime;
	}
	private String num;
	private String value;
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 防止用户刷新重新填写试卷，刷新后取出已做答案
	 * 
	 * @author   npz
	 * @date 2017年11月10日
	 */
	public void getresult(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		List<Map<String,Object>> obj = (List) session.getAttribute(String.valueOf(exam)+"content");
		ActionUtil.writer(JSON.toJSONString(obj));
	}
	/**
	 * session存储考试开始时间
	 * @param irpexam
	 * @param session
	 * @return
	 * @author   npz
	 * @date 2017年9月26日
	 */
	public boolean settime(IrpExam irpexam,HttpSession session){
		if(session.getAttribute(String.valueOf(exam))!=null){
		    	Date date = new Date();
		    	long time = Long.valueOf(String.valueOf(session.getAttribute(String.valueOf(exam))));
		    	long hastime = (date.getTime()-time)/1000;
		    	if(irpexam.getAnswertime()!=null){
		    	moretime = irpexam.getAnswertime()*60l-hastime;
		    	}
		    	return true;
		    }
		return false;
	}
	private Long userid;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * 进入考试详情
	 * @return
	 * @author   npz
	 * @date 2017年9月26日
	 */
	public String linkSingleQues(){
		irpExam = irpExamService.getIrpExamById(exam);
		irpTestpaper = this.irpTestpaperService.getIrpTestpaper(paper);
    	if (irpTestpaper!=null) {
    		qbankbids = irpTestpaper.getPapercontent().toString().split(",");
		}
    	userid = LoginUtil.getLoginUserId();
    	irpExamRecord = this.irpExamRecordService.findExamRecordbyexamidanduserid(exam, LoginUtil.getLoginUserId());
    	if(irpExamRecord!=null){
    		if(irpExamRecord.getExtendone()==30){
    			Date date = new Date();
    			long time = Long.valueOf(irpExamRecord.getExtendtwo()) ;
    			long hastime = (date.getTime()-time)/1000;
		    	if(irpExam.getAnswertime()!=null){
		    		moretime = irpExam.getAnswertime()*60l-hastime;
		    	}
    			return SUCCESS;
    		}
    		recordid = irpExamRecord.getRecordid();
    		return ERROR;
    	}else{
    		Long userid = LoginUtil.getLoginUserId();
    		IrpExamRecord record = new IrpExamRecord();
    		record.setRecordid(TableIdUtil.getNextId(record));
    		record.setCrtime(irpExam.getCrtime());
    		record.setCruserid(userid);
    		record.setExamid(irpExam.getExamid());
    		record.setExtendone(30l);
    		Date date = new Date();
    		record.setExtendtwo(String.valueOf(date.getTime()));;
    		irpExamRecordService.addIrpExamRecord(record);
			if(irpExam.getAnswertime()!=null){
				moretime = irpExam.getAnswertime()*60l;
			}
			
    	}
    	return SUCCESS;
    }
	
	/**
	 * 查看历史 已经考完的试卷  历史详情
	 * @return
	 */
	public String linkSingleQuesDetail(){
		irpExam = irpExamService.getIrpExamById(exam);
    	irpTestpaper = this.irpTestpaperService.getIrpTestpaper(paper);
    	if (irpTestpaper!=null) {
    		qbankbids = irpTestpaper.getPapercontent().toString().split(",");
		}
    	irpExamRecord = this.irpExamRecordService.getIrpExamRecordById(recordid);
    	return SUCCESS;
	}
	/**
	 * 根据条件获得考试细览记录
	 * @param _examid
	 * @param _paperid
	 * @param _qbankid
	 * @return
	 */
	public IrpExamAnswer getIrpExamAnswer(Long _examid,Long _paperid,Long _qbankid,String _examtiemss){
		
		IrpExamAnswer irpExamAnswer = null;
		irpExamAnswer = this.irpExamAnswerService.getIrpExamAnswer(_examid, _paperid, _qbankid,LoginUtil.getLoginUserId(),_examtiemss);
		return irpExamAnswer;
		
	}
	
	public IrpExamAnswer getIrpExamAnswerAdmin(Long _examid,Long _paperid,Long _qbankid,String _examtiemss,long _userid){
		IrpExamAnswer irpExamAnswer = null;
		irpExamAnswer = this.irpExamAnswerService.getIrpExamAnswer(_examid, _paperid, _qbankid, _userid, _examtiemss);
		return irpExamAnswer;
	}
	public IrpExamAnswer getIrpExamAnswera(Long _examid,Long _paperid,Long _qbankid,long _userid){
		IrpExamAnswer irpExamAnswer = null;
		irpExamAnswer = this.irpExamAnswerService.getIrpExamAnswer(_examid, _paperid, _qbankid, _userid);
		return irpExamAnswer;
	}
	/**
	 * 处理填空字符串
	 * @param _fullstr
	 * @return
	 */
	public String disposeFullContent(String _fullstr){
		int fullnum = 1;
		if (_fullstr.length()>0) {
			while (_fullstr.indexOf("[")>0 && _fullstr.indexOf("]")>0) {
				String str = _fullstr.substring(_fullstr.indexOf("["),_fullstr.indexOf("]")+1);
				if (!(str.equals(""))) {
					String restr = str.replace("[","\\[").replace("]","\\]").replace("|","\\|");
					_fullstr = _fullstr.replaceFirst(restr,"__"+fullnum+"__");
					
				}
				fullnum++;
			}
		}
		return _fullstr;
	}
	/**
	 * 返回填空处长度
	 * @param _fullstr
	 * @return
	 */
	public int[] disposeFCLength(String _fullstr){
		
		int fullnum = 1;
		if (_fullstr.length()>0) {
			while (_fullstr.indexOf("[")>0 && _fullstr.indexOf("]")>0) {
				String str = _fullstr.substring(_fullstr.indexOf("["),_fullstr.indexOf("]")+1);
				if (!(str.equals(""))) {
					String restr = str.replace("[","\\[").replace("]","\\]").replace("|","\\|");
					_fullstr = _fullstr.replaceFirst(restr,"__"+fullnum+"__");
					
				}
				fullnum++;
			}
		}
		int[] strarray = new int[fullnum-1];
		
		return strarray;
	}
	
    /**
     * 试卷管理菜单
     * @return
     */
    public String paperMenu(){
    	if (cateid==null) {
    		cateid = 0l;
		}
    	
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "paperid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int num = this.irpTestpaperService.getIrpTestpaperListNum(cateid,null,searchWord,searchType);
		
		PageUtil pageUtil = new PageUtil(pageadminnum, 10, num);
		
		irpTestpaperlist = this.irpTestpaperService.getIrpTestpaperList(cateid,null, pageUtil, _oOrderby, searchWord, searchType);
	
		this.paperpagehtml = pageUtil.getPageHtml("pagePaper(#PageNum#)");
    	
    	return SUCCESS;
    }
    /**
     * 链接到组卷页面
     * @return
     */
    public String linkAddTestPaper(){
    	
    	irpTestPaper = new IrpTestpaper();
    	
    	return SUCCESS;
    }
    /**
     * 根据id删除试卷
     * @return
     */
    public void deleteTPaperid(){
    	int msg = this.irpTestpaperService.deleteTestPaper(paperid);
    	ActionUtil.writer(msg+"");
    	
    }
    /**
     * 多选删除试卷
     * @return
     */
    public void deletetesTPaperids(){
    	  int msg = 0;
    		if (qtestpidstr!=null && qtestpidstr.length()>0) {
    			String dtarray[] = qtestpidstr.split(",");
    			if (dtarray.length>0) {
    				for (int i = 0; i < dtarray.length; i++)
    					msg = this.irpTestpaperService.deleteTestPaper(Long.parseLong(dtarray[i]));
    				}
    			}

    		ActionUtil.writer(msg+"");
    }
    /**
     * 修改试卷
     * @return
     */
    public String updateTTPaper(){
    	irpTestPaper = this.irpTestpaperService.getIrpTestpaper(updatepid);
    	if (irpTestPaper!=null) {
    		qbankbids = irpTestPaper.getPapercontent().toString().split(",");
		}
    	return SUCCESS;
    }
    /**
     * 修改试卷
     * @return
     */
    public void updateTestPaper(){
    	int msg = 0;
    	msg = this.irpTestpaperService.updatePaper(irpTestPaper);
    	ActionUtil.writer(msg+"");
    }
    /**
     * 编辑权限
     * @return
     */
 

	public String jurisdictionPaper(){
       List<IrpGroupTestpaper> list = irpGroupTestpaperService.getGTPaperByTPId(paperid, IrpGroupTestpaper.ISDELNORMAL);
       StringBuffer sb = new StringBuffer();
       
       for (int i = 0; i < list.size(); i++) {
    	   IrpGroupTestpaper grouptestpaper = list.get(i);
    	   sb.append(grouptestpaper.getGroupid()+",");
       }
      
       grouptids = sb.toString();
        	
       return SUCCESS;
    }
	
	/**
	 * 选择试卷
	 * @return
	 */
	public String selectTestPaper(){
		
		
		return SUCCESS;
	}
	/**
	 * 根据试题内容返回试题数量
	 * @param _papercontent
	 * @return
	 */
	public int getNumBySContent(String _papercontent){
		int num = 0;
		if (!_papercontent.trim().equals("")) {
			String pcarray[] = _papercontent.split(",");
			num = pcarray.length;
		}
		return num;
	}
}
