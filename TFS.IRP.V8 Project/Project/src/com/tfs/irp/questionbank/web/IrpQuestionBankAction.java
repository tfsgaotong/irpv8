package com.tfs.irp.questionbank.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.examanswer.entity.IrpExamAnswer;
import com.tfs.irp.examanswer.service.IrpExamAnswerService;
import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.examrecord.service.IrpExamRecordService;
import com.tfs.irp.questionbank.entity.IrpQuestionBank;
import com.tfs.irp.questionbank.service.IrpQuestionBankService;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpQuestionBankAction extends ActionSupport {
	
	private IrpQuestionBankService irpQuestionBankService;
	
	private IrpQuestionBank irpQuestionBank;
	
	public Long getCateid() {
		return cateid;
	}

	public void setCateid(Long cateid) {
		this.cateid = cateid;
	}
	private List<IrpQuestionBank> questionbanklist;
	
	private Long cateid;
	
	private Integer pagenum = 1;
	private String orderField = "";

	private String orderBy = "";
	
	private String pageHTML = "";
	
	private Long qbankid;
	
	private String qbankidstr;
	
	private IrpUserService irpUserService;
	
	private String searchWord  = "";
	
	private String searchType = "";
	
	private Integer qtype  = 10;
	
	private String singfullcontent;
	
	private Integer singfullcontentindex;
	
	private Long examid;
	
	private Long testpaperid;
	
	private IrpExamAnswerService irpExamAnswerService;
	
	private Integer resulttype;
	
	private Integer examtimes;
	
	private Integer jigestatus;
	
	private Integer examtotalscore;
	
	private IrpExamRecordService irpExamRecordService;
	
	public IrpExamRecordService getIrpExamRecordService() {
		return irpExamRecordService;
	}

	public void setIrpExamRecordService(IrpExamRecordService irpExamRecordService) {
		this.irpExamRecordService = irpExamRecordService;
	}

	public Integer getResulttype() {
		return resulttype;
	}

	public void setResulttype(Integer resulttype) {
		this.resulttype = resulttype;
	}

	public IrpExamAnswerService getIrpExamAnswerService() {
		return irpExamAnswerService;
	}

	public void setIrpExamAnswerService(IrpExamAnswerService irpExamAnswerService) {
		this.irpExamAnswerService = irpExamAnswerService;
	}

	public Long getExamid() {
		return examid;
	}

	public void setExamid(Long examid) {
		this.examid = examid;
	}

	public Long getTestpaperid() {
		return testpaperid;
	}

	public void setTestpaperid(Long testpaperid) {
		this.testpaperid = testpaperid;
	}

	public String getSingfullcontent() {
		return singfullcontent;
	}

	public void setSingfullcontent(String singfullcontent) {
		this.singfullcontent = singfullcontent;
	}

	public Integer getSingfullcontentindex() {
		return singfullcontentindex;
	}

	public void setSingfullcontentindex(Integer singfullcontentindex) {
		this.singfullcontentindex = singfullcontentindex;
	}

	public Integer getQtype() {
		return qtype;
	}

	public void setQtype(Integer qtype) {
		this.qtype = qtype;
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

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getQbankidstr() {
		return qbankidstr;
	}

	public void setQbankidstr(String qbankidstr) {
		this.qbankidstr = qbankidstr;
	}

	public Long getQbankid() {
		return qbankid;
	}

	public void setQbankid(Long qbankid) {
		this.qbankid = qbankid;
	}
	private IrpCategoryService irpCategoryService;

	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
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

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public List<IrpQuestionBank> getQuestionbanklist() {
		return questionbanklist;
	}

	public void setQuestionbanklist(List<IrpQuestionBank> questionbanklist) {
		this.questionbanklist = questionbanklist;
	}

	public IrpQuestionBank getIrpQuestionBank() {
		return irpQuestionBank;
	}

	public void setIrpQuestionBank(IrpQuestionBank irpQuestionBank) {
		this.irpQuestionBank = irpQuestionBank;
	}

	public IrpQuestionBankService getIrpQuestionBankService() {
		return irpQuestionBankService;
	}

	public void setIrpQuestionBankService(
			IrpQuestionBankService irpQuestionBankService) {
		this.irpQuestionBankService = irpQuestionBankService;
	}
	/**
	 * 后台  题库管理  左侧 手风琴标签
	 * @return
	 */
	public String qBankMenu(){
		
		
		return SUCCESS;
	}
	/**
	 * 后台题库管理     右侧数据列表
	 * @return
	 */
	public String qBankList(){
		if (cateid<=0) {
			cateid = null;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "qbankid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int ncount = this.irpQuestionBankService.questionBankListNum(cateid, IrpQuestionBank.NORMALSTATUS,searchWord,searchType);
		
		PageUtil pageutil = new PageUtil(pagenum, 10, ncount);

		questionbanklist = this.irpQuestionBankService.questionBankList(cateid, IrpQuestionBank.NORMALSTATUS, pageutil,_oOrderby,searchWord,searchType);
		
		this.pageHTML = pageutil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 后台题库管理  链接到增加选择题页面
	 * @return
	 */
	public String findChoiceQPage(){
		
		irpQuestionBank = new IrpQuestionBank();
		
		
		return SUCCESS;
	}
	/**
	 * 后台题库管理  链接到增加判断题页面
	 * 
	 * @return
	 */
	public String findBooleanQPage(){
		
		irpQuestionBank = new IrpQuestionBank();
		
		return SUCCESS;
	}
	/**
	 * 后台题库管理  链接到增加填空题页面
	 * 
	 * @return
	 */
	public String findfullQPage(){
		
		irpQuestionBank = new IrpQuestionBank();
		
		return SUCCESS;
	}
	
	
	/**
	 * 增加选择题
	 * @return
	 */
	public void addChoiceQuestion(){
		boolean flag = false;
		if (irpQuestionBank!=null) {
			IrpQuestionBank disposeiqb = new IrpQuestionBank();
			disposeiqb.setQbankid(TableIdUtil.getNextId(disposeiqb));
			disposeiqb.setQbtype(irpQuestionBank.getQbtype());
			disposeiqb.setQbstatus(irpQuestionBank.NORMALSTATUS);
			if (cateid!=null) {
				disposeiqb.setQbcate(cateid);
			}
			disposeiqb.setAnswera(irpQuestionBank.getAnswera());
			disposeiqb.setAnswerb(irpQuestionBank.getAnswerb());
			disposeiqb.setAnswerc(irpQuestionBank.getAnswerc());
			disposeiqb.setAnswerd(irpQuestionBank.getAnswerd());
			disposeiqb.setAnswere(irpQuestionBank.getAnswere());
			
			if (irpQuestionBank.getQbtype().equals(IrpQuestionBank.FILLINGTYPE)) {
				//填空
				String qtext = irpQuestionBank.getQuestiontext();
				if (qtext.length()>0) {
					int fullnum = 1;
					StringBuffer sbstr = new StringBuffer();
					while (qtext.indexOf("[")>0 && qtext.indexOf("]")>0) {
						String str = qtext.substring(qtext.indexOf("["),qtext.indexOf("]")+1);
						if (!(str.equals(""))) {
							sbstr.append(str);
							String disposestr = str.replace("[","\\[").replace("]","\\]").replace("|","\\|");
							qtext = qtext.replaceFirst(disposestr,"(&nbsp;"+fullnum+"&nbsp;)");
						}
						fullnum++;
					}
					disposeiqb.setQuestiontext(irpQuestionBank.getQuestiontext());
					disposeiqb.setFinalanswer(sbstr.toString());
				}
			}else{
				disposeiqb.setQuestiontext(irpQuestionBank.getQuestiontext());
				disposeiqb.setFinalanswer(irpQuestionBank.getFinalanswer());
			}
			disposeiqb.setCruser(LoginUtil.getLoginUserId());
			disposeiqb.setCrtime(Calendar.getInstance().getTime());
			disposeiqb.setQbscore(irpQuestionBank.getQbscore());
			disposeiqb.setQexplain(irpQuestionBank.getQexplain());
			disposeiqb.setQblevel(irpQuestionBank.getQblevel());
			flag = this.irpQuestionBankService.addQBank(disposeiqb);
		}
		ActionUtil.writer(flag+"");
		
	}
	/**
	 * 根据主键id找出分类的名称
	 * @param _cateid
	 * @return
	 */
	public String getCateNameById(Long _cateid){
		
		IrpCategory irpCategory = this.irpCategoryService.findCategoryByPrimaryKey(_cateid);
		
		return irpCategory.getCname();
	}
	/**
	 * 通过状态验证级别
	 * @param _status
	 * @return
	 */
	public String getLevleByStatus(int _status){
		String levle = "";
		switch (_status) {
		case IrpQuestionBank.RANKSIMPLE:
			levle = "简单";
			break;
		case IrpQuestionBank.RANKNORMAL:
			levle = "一般";
			break;
		case IrpQuestionBank.RANKHARD:
			levle = "困难";
			break;
		}	
		return levle;
	}
	/**
	 * 根据类型获得相应的中文名称
	 * @param _type
	 * @return
	 */
	public String getTypeStrByTypes(int _type){
		String typestr = "";
		
		switch (_type) {
		case IrpQuestionBank.CHOICEMANYTYPE:
			typestr = "多选题";
			break;
		case IrpQuestionBank.CHOICESINGLETYPE:
			typestr = "单选题";
			break;
		case IrpQuestionBank.FILLINGTYPE:
			typestr = "填空题";
			break;
		case IrpQuestionBank.BOOLEANTYPE:
			typestr = "判断题";
			break;
		}
		
		return typestr;
		
	}
	/**
	 * 根据主键id删除某个题目
	 * @return
	 */
	public void deleteQBankById(){
		int msg = 0;
		msg = this.irpQuestionBankService.deleteQBankById(qbankid);
		ActionUtil.writer(msg+"");
	}
	public void deleteQBankByIdStr(){
		int msg = 0;
		if (qbankidstr!=null && qbankidstr.length()>0) {
			String dtarray[] = qbankidstr.split(",");
			if (dtarray.length>0) {
				for (int i = 0; i < dtarray.length; i++)
					msg = this.irpQuestionBankService.deleteQBankById(Long.parseLong(dtarray[i]));
				}
			}
	
		ActionUtil.writer(msg+"");
	
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
     * 修改题目
     * @return
     */
    public String updateQBank(){
    	if (qbankid!=null) {
    		irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(qbankid);    		
    		if (irpQuestionBank!=null) {
    			if (irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICESINGLETYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICEMANYTYPE) {
					//选择题
    				return "CHOICEQUESTION";
				}else if(irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE){
					//判断题
					return "BOOLEANQUESTION";
				}else if(irpQuestionBank.getQbtype()==IrpQuestionBank.FILLINGTYPE){
					//填空题
					return "FULLQUESTION";
				}
			}
			
		}    	
    	return SUCCESS;
    }
    /**
     * 修改选择题
     * @return
     */
    public void updateChoiceQuestion(){
    	
    	int msg = 0;
    	IrpQuestionBank disposeiqb = new IrpQuestionBank();
    	disposeiqb.setQbankid(irpQuestionBank.getQbankid());
    	disposeiqb.setQbtype(irpQuestionBank.getQbtype());
		disposeiqb.setAnswera(irpQuestionBank.getAnswera());
		disposeiqb.setAnswerb(irpQuestionBank.getAnswerb());
		disposeiqb.setAnswerc(irpQuestionBank.getAnswerc());
		disposeiqb.setAnswerd(irpQuestionBank.getAnswerd());
		disposeiqb.setAnswere(irpQuestionBank.getAnswere());
		
		if (irpQuestionBank.getQbtype().equals(IrpQuestionBank.FILLINGTYPE)) {
			//填空
			String qtext = irpQuestionBank.getQuestiontext();
			if (qtext.length()>0) {
				int fullnum = 1;
				StringBuffer sbstr = new StringBuffer();
				while (qtext.indexOf("[")>0 && qtext.indexOf("]")>0) {
					String str = qtext.substring(qtext.indexOf("["),qtext.indexOf("]")+1);
					if (!(str.equals(""))) {
						sbstr.append(str);
						String disposestr = str.replace("[","\\[").replace("]","\\]").replace("|","\\|");
						qtext = qtext.replaceFirst(disposestr,"(&nbsp;"+fullnum+"&nbsp;)");
					}
					fullnum++;
				}
				disposeiqb.setQuestiontext(irpQuestionBank.getQuestiontext());
				disposeiqb.setFinalanswer(sbstr.toString());
			}
		}else{
			disposeiqb.setQuestiontext(irpQuestionBank.getQuestiontext());
			disposeiqb.setFinalanswer(irpQuestionBank.getFinalanswer());
		}
		disposeiqb.setQbscore(irpQuestionBank.getQbscore());
		disposeiqb.setQexplain(irpQuestionBank.getQexplain());
		disposeiqb.setQblevel(irpQuestionBank.getQblevel());
    	msg = this.irpQuestionBankService.updateQBank(disposeiqb);
    	
    	ActionUtil.writer(msg+"");
    }
    /**
     * 链接到后台添加题库内容页面
     * @return
     */
    public String linkQuoteBank(){
    	
    	
    	return SUCCESS;
    }
    

    private Long questionid;
    private String answercontent;
    public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public String getAnswercontent() {
		return answercontent;
	}

	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}
    /**
     * 验证答案
     * @return
     */
	private String mysysdate;
	public String getMysysdate() {
		return mysysdate;
	}

	public void setMysysdate(String mysysdate) {
		this.mysysdate = mysysdate;
	}
	private String myanswer;
	
	public String getMyanswer() {
		return myanswer;
	}

	public void setMyanswer(String myanswer) {
		this.myanswer = myanswer;
	}

	/**
	 * 保存用户选择
	 * 
	 * @author   npz
	 * @date 2017年11月10日
	 */
	public void saveresult(){
		IrpExamAnswer irpExamAnswer = irpExamAnswerService.getIrpExamAnswer(examid, testpaperid, questionid, LoginUtil.getLoginUserId());
		if(irpExamAnswer==null){
			irpExamAnswer = new IrpExamAnswer();
			irpExamAnswer.setAnswerid(TableIdUtil.getNextId(irpExamAnswer));
			irpExamAnswer.setExamid(examid);
			irpExamAnswer.setPaperid(testpaperid);
			irpExamAnswer.setQuestionbankid(questionid);
			irpExamAnswer.setCrtime(Calendar.getInstance().getTime());
			irpExamAnswer.setCruserid(LoginUtil.getLoginUserId());
			if(myanswer!=null){
				irpExamAnswer.setAnswer(myanswer);
			}else{
				irpExamAnswer.setAnswer(singfullcontent);
			}
			//增加每个考生对每个考题的具体答案
			this.irpExamAnswerService.addExamAnswer(irpExamAnswer);
		}else{
			irpExamAnswer.setCrtime(Calendar.getInstance().getTime());
			if(myanswer!=null && myanswer!=""){
				irpExamAnswer.setAnswer(myanswer);
			}else{
				irpExamAnswer.setAnswer(singfullcontent);
			}
			//更新每个考生对考题的具体答案
			this.irpExamAnswerService.updateExamAnswer(irpExamAnswer);
		}
		
	}
	/**
	 * 获取用户答案对比正确答案算分
	 * 
	 * @author   npz
	 * @date 2017年11月13日
	 */
	public void newbooleanAnswer(){
		
			if (questionid!=null) {
	    		IrpExamAnswer irpExamAnswer = irpExamAnswerService.getIrpExamAnswer(examid, testpaperid, questionid, LoginUtil.getLoginUserId());
	    		if(irpExamAnswer==null){
	    			booleanAnswer();
	    			return;
	    		}
	    		irpExamAnswer.setExamidtimes(mysysdate);
	    		
	    		IrpQuestionBank irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(questionid);
	    		List list = new ArrayList();
	    		if(irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICESINGLETYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICEMANYTYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE){
	    			irpExamAnswer.setAnswer(answercontent);
	    			
					//单选题
	    			if (answercontent!=null) {
	        			if (answercontent.equals(irpQuestionBank.getFinalanswer().replace(" ",""))) {
	    					//答对了
	        				list.add("{\"status\":\""+1+"\"");
	        				list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
	    				}else{
	        				list.add("{\"status\":\""+0+"\"");
	        				list.add("\"score\":\""+0+"\"");
	    				}
					}else{
						list.add("{\"status\":\""+0+"\"");
						list.add("\"score\":\""+0+"\"");
					}
	    			
	    			if (irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE) {
	    				String boolstr = "";
	    				if (irpQuestionBank.getFinalanswer().trim().equals("A")) {
	    					boolstr = "正确";
						}else{
							boolstr = "错误";
						}
	    				list.add("\"answer\":\""+boolstr+"\"");
					}else{
						list.add("\"answer\":\""+irpQuestionBank.getFinalanswer().trim()+"\"");
					}
					
					String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
					
					list.add("\"explain\":\""+explainstr+"\"}");
					
					
					
	    			
	    		}else if(irpQuestionBank.getQbtype()==IrpQuestionBank.FILLINGTYPE){
	    			irpExamAnswer.setAnswer(singfullcontent);
	    			
	    			
	    			
	    			if(singfullcontent.length()>0){
	    				String c_one[] = singfullcontent.split("]");
	    				String c_two[] = irpQuestionBank.getFinalanswer().trim().split("]");
	    				if (c_one.length==c_two.length) {
	    					int status_d = 1;
	    					for (int i = 0; i < c_two.length; i++) {
	    						String d_one = c_one[i].toString().replaceFirst("\\[","").trim();
	    						String d_two = c_two[i].toString().replaceFirst("\\[","").trim();
	    						
	    						if (d_two.indexOf("|")>0) {
	    						    String []d_two_array = d_two.split("\\|");
	    						    int d_msg = 0;
	    						    for (int j = 0; j < d_two_array.length; j++) {
	    						    	if (d_one.equals(d_two_array[j])) {
	    						    		d_msg = 1;
	    						    		break;
										}
									}
	    						    if (d_msg==1) {
	    						    	status_d =1;
									}else{
										status_d =0;
										break;
									}
								}else{
									if (!d_one.equals(d_two)) {
										status_d = 0;
										break;
									}
								}
							}
	    					list.add("{\"status\":\""+status_d+"\"");
	    					if (status_d==1) {
	    						list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
							}else{
								list.add("\"score\":\""+0+"\"");
							}
						}else{
							
							list.add("{\"status\":\""+0+"\"");
							list.add("\"score\":\""+0+"\"");
						}
	    				
	    			}else{
	    				list.add("{\"status\":\""+0+"\"");
	    				list.add("\"score\":\""+0+"\"");
	    			}
	    			String contentarray[] =	irpQuestionBank.getFinalanswer().trim().split("\\[");
	    			StringBuffer sban = new StringBuffer();
	    			
	    			for (int i = 1; i < contentarray.length; i++) {
	    				sban.append("("+i+")&nbsp;:&nbsp;"+contentarray[i].toString().replace("|","&nbsp;<font style=\\\"color:#0080FF;\\\">或</font>&nbsp;").replace("]","").trim()+"&nbsp;&nbsp;");
					}
	    			
	    			list.add("\"answer\":\""+sban+"\"");
					String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
					
					list.add("\"explain\":\""+explainstr+"\"}");
	    			
	    			
	    		}
	    		this.irpExamAnswerService.updateExamAnswer(irpExamAnswer);
	    		ActionUtil.writer(list.toString());
			}
	}
	public void booleanAnswer(){
		
    	if (questionid!=null) {
    		
    		IrpExamAnswer irpExamAnswer = new IrpExamAnswer();
    		irpExamAnswer.setAnswerid(TableIdUtil.getNextId(irpExamAnswer));
    		irpExamAnswer.setExamid(examid);
    		irpExamAnswer.setPaperid(testpaperid);
    		irpExamAnswer.setQuestionbankid(questionid);
    		irpExamAnswer.setCrtime(Calendar.getInstance().getTime());
    		irpExamAnswer.setCruserid(LoginUtil.getLoginUserId());
    		irpExamAnswer.setExamidtimes(mysysdate);
    		
    		IrpQuestionBank irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(questionid);
    		List list = new ArrayList();
    		if(irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICESINGLETYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICEMANYTYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE){
    			irpExamAnswer.setAnswer(answercontent);
    			
				//单选题
    			if (answercontent!=null) {
        			if (answercontent.equals(irpQuestionBank.getFinalanswer().replace(" ",""))) {
    					//答对了
        				list.add("{\"status\":\""+1+"\"");
        				list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
    				}else{
        				list.add("{\"status\":\""+0+"\"");
        				list.add("\"score\":\""+0+"\"");
    				}
				}else{
					list.add("{\"status\":\""+0+"\"");
					list.add("\"score\":\""+0+"\"");
				}
    			
    			if (irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE) {
    				String boolstr = "";
    				if (irpQuestionBank.getFinalanswer().trim().equals("A")) {
    					boolstr = "正确";
					}else{
						boolstr = "错误";
					}
    				list.add("\"answer\":\""+boolstr+"\"");
				}else{
					list.add("\"answer\":\""+irpQuestionBank.getFinalanswer().trim()+"\"");
				}
				
				String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
				
				list.add("\"explain\":\""+explainstr+"\"}");
				
				
				
    			
    		}else if(irpQuestionBank.getQbtype()==IrpQuestionBank.FILLINGTYPE){
    			irpExamAnswer.setAnswer(singfullcontent);
    			
    			
    			
    			if(singfullcontent.length()>0){
    				String c_one[] = singfullcontent.split("]");
    				String c_two[] = irpQuestionBank.getFinalanswer().trim().split("]");
    				if (c_one.length==c_two.length) {
    					int status_d = 1;
    					for (int i = 0; i < c_two.length; i++) {
    						String d_one = c_one[i].toString().replaceFirst("\\[","").trim();
    						String d_two = c_two[i].toString().replaceFirst("\\[","").trim();
    						
    						if (d_two.indexOf("|")>0) {
    						    String []d_two_array = d_two.split("\\|");
    						    int d_msg = 0;
    						    for (int j = 0; j < d_two_array.length; j++) {
    						    	if (d_one.equals(d_two_array[j])) {
    						    		d_msg = 1;
    						    		break;
									}
								}
    						    if (d_msg==1) {
    						    	status_d =1;
								}else{
									status_d =0;
									break;
								}
							}else{
								if (!d_one.equals(d_two)) {
									status_d = 0;
									break;
								}
							}
						}
    					list.add("{\"status\":\""+status_d+"\"");
    					if (status_d==1) {
    						list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
						}else{
							list.add("\"score\":\""+0+"\"");
						}
					}else{
						
						list.add("{\"status\":\""+0+"\"");
						list.add("\"score\":\""+0+"\"");
					}
    				
    			}else{
    				list.add("{\"status\":\""+0+"\"");
    				list.add("\"score\":\""+0+"\"");
    			}
    			String contentarray[] =	irpQuestionBank.getFinalanswer().trim().split("\\[");
    			StringBuffer sban = new StringBuffer();
    			
    			for (int i = 1; i < contentarray.length; i++) {
    				sban.append("("+i+")&nbsp;:&nbsp;"+contentarray[i].toString().replace("|","&nbsp;<font style=\\\"color:#0080FF;\\\">或</font>&nbsp;").replace("]","").trim()+"&nbsp;&nbsp;");
				}
    			
    			list.add("\"answer\":\""+sban+"\"");
				String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
				
				list.add("\"explain\":\""+explainstr+"\"}");
    			
    			
    			
    			
    		}
    		
    		//增加每个考生对每个考题的具体答案
    		this.irpExamAnswerService.addExamAnswer(irpExamAnswer);
    		
    		
    		
    		ActionUtil.writer(list.toString());
		}
    	
    }
	
	public void booleanAnswerAV(){
		
		

    	if (questionid!=null) {
    		
    		IrpQuestionBank irpQuestionBank = this.irpQuestionBankService.getQuestionBankById(questionid);
    		List list = new ArrayList();
    		if(irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICESINGLETYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.CHOICEMANYTYPE || irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE){
    	
    			//单选题
    			if (answercontent!=null) {
        			if (answercontent.equals(irpQuestionBank.getFinalanswer().replace(" ",""))) {
    					//答对了
        				list.add("{\"status\":\""+1+"\"");
        				list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
    				}else{
        				list.add("{\"status\":\""+0+"\"");
        				list.add("\"score\":\""+0+"\"");
    				}
				}else{
					list.add("{\"status\":\""+0+"\"");
					list.add("\"score\":\""+0+"\"");
				}
    			
    			if (irpQuestionBank.getQbtype()==IrpQuestionBank.BOOLEANTYPE) {
    				String boolstr = "";
    				if (irpQuestionBank.getFinalanswer().trim().equals("A")) {
    					boolstr = "正确";
					}else{
						boolstr = "错误";
					}
    				list.add("\"answer\":\""+boolstr+"\"");
				}else{
					list.add("\"answer\":\""+irpQuestionBank.getFinalanswer().trim()+"\"");
				}
				
				String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
				
				list.add("\"explain\":\""+explainstr+"\"}");
				
				
				
    			
    		}else if(irpQuestionBank.getQbtype()==IrpQuestionBank.FILLINGTYPE){
    			
    			
    			if(singfullcontent.length()>0){
    				String c_one[] = singfullcontent.split("]");
    				String c_two[] = irpQuestionBank.getFinalanswer().trim().split("]");
    				if (c_one.length==c_two.length) {
    					int status_d = 1;
    					for (int i = 0; i < c_two.length; i++) {
    						String d_one = c_one[i].toString().replaceFirst("\\[","").trim();
    						String d_two = c_two[i].toString().replaceFirst("\\[","").trim();
    						
    						if (d_two.indexOf("|")>0) {
    						    String []d_two_array = d_two.split("\\|");
    						    int d_msg = 0;
    						    for (int j = 0; j < d_two_array.length; j++) {
    						    	if (d_one.equals(d_two_array[j])) {
    						    		d_msg = 1;
    						    		break;
									}
								}
    						    if (d_msg==1) {
    						    	status_d =1;
								}else{
									status_d =0;
									break;
								}
							}else{
								if (!d_one.equals(d_two)) {
									status_d = 0;
									break;
								}
							}
						}
    					list.add("{\"status\":\""+status_d+"\"");
    					if (status_d==1) {
    						list.add("\"score\":\""+irpQuestionBank.getQbscore()+"\"");
						}else{
							list.add("\"score\":\""+0+"\"");
						}
					}else{
						
						list.add("{\"status\":\""+0+"\"");
						list.add("\"score\":\""+0+"\"");
					}
    				
    			}else{
    				list.add("{\"status\":\""+0+"\"");
    				list.add("\"score\":\""+0+"\"");
    			}
    			String contentarray[] =	irpQuestionBank.getFinalanswer().trim().split("\\[");
    			StringBuffer sban = new StringBuffer();
    			
    			for (int i = 1; i < contentarray.length; i++) {
    				sban.append("("+i+")&nbsp;:&nbsp;"+contentarray[i].toString().replace("|","&nbsp;<font style=\\\"color:#0080FF;\\\">或</font>&nbsp;").replace("]","").trim()+"&nbsp;&nbsp;");
				}
    			
    			list.add("\"answer\":\""+sban+"\"");
				String explainstr = irpQuestionBank.getQexplain().trim().replace("\"", "\\\"").replace("\\\\\"","\\\"").replaceAll("\n|\r","");
				
				list.add("\"explain\":\""+explainstr+"\"}");
    			
    			
    			
    			
    		}
    		
    		ActionUtil.writer(list.toString());
		
    	}
		
	}
	private Long examstatus;
	public Long getExamstatus() {
		return examstatus;
	}

	public void setExamstatus(Long examstatus) {
		this.examstatus = examstatus;
	}

	/**
	 * 更新考试结果记录
	 * @return
	 */
	public void addExamRecord(){
		int msg = 0;
		IrpExamRecord examrecord = irpExamRecordService.findExamRecordbyexamidanduserid(examid, LoginUtil.getLoginUserId());
		examrecord.setExamid(examid);
		examrecord.setExamtime(examtimes);
		examrecord.setExamstatus(jigestatus);
		examrecord.setExamgrade(examtotalscore);
		examrecord.setCrtime(Calendar.getInstance().getTime());
		examrecord.setCruserid(LoginUtil.getLoginUserId());
		
		examrecord.setExtendone(examstatus);
		
		examrecord.setExamidtimes(mysysdate);
		msg = irpExamRecordService.updateIrpExamRecord(examrecord);
		ActionUtil.writer(msg+"");
		
	}

	public Integer getExamtimes() {
		return examtimes;
	}

	public void setExamtimes(Integer examtimes) {
		this.examtimes = examtimes;
	}

	public Integer getJigestatus() {
		return jigestatus;
	}

	public void setJigestatus(Integer jigestatus) {
		this.jigestatus = jigestatus;
	}

	public Integer getExamtotalscore() {
		return examtotalscore;
	}

	public void setExamtotalscore(Integer examtotalscore) {
		this.examtotalscore = examtotalscore;
	}
	
	
    
}
