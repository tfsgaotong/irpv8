package com.tfs.irp.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.vote.entity.IrpVote;
import com.tfs.irp.vote.service.IrpVoteService;
import com.tfs.irp.voteoptions.entity.IrpVoteOptions;
import com.tfs.irp.voteoptions.service.IrpVoteOptionsService;

public class voteAction extends ActionSupport{
	private IrpVoteService irpVoteService;
	private IrpVoteOptionsService irpVoteOptionsService;
	private IrpUserService irpUserService;
    private IrpMicroblogService irpMicroBlogService;
	private IrpVote irpvotetitle;
	private IrpVote irptitle;
	private IrpVoteOptions voteOptions;
	private String jsonoptionimg;
	private String soptionstr;
	private String message;
	private List<IrpVote> irpVotestitle;
	private Long votedid;
	private int typeindex = 1;
    private String urloption;
	private Long votetype;
	private List<IrpMicroblog> irpMicroblogr;
	private List<IrpMicroblog> irpMicroblogb;
	private List<IrpMicroblog> irpMicroblogs;
	
	
	public List<IrpMicroblog> getIrpMicroblogs() {
		return irpMicroblogs;
	}

	public void setIrpMicroblogs(List<IrpMicroblog> irpMicroblogs) {
		this.irpMicroblogs = irpMicroblogs;
	}

	public List<IrpMicroblog> getIrpMicroblogr() {
		return irpMicroblogr;
	}

	public void setIrpMicroblogr(List<IrpMicroblog> irpMicroblogr) {
		this.irpMicroblogr = irpMicroblogr;
	}

	public List<IrpMicroblog> getIrpMicroblogb() {
		return irpMicroblogb;
	}

	public void setIrpMicroblogb(List<IrpMicroblog> irpMicroblogb) {
		this.irpMicroblogb = irpMicroblogb;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public Long getVotetype() {
		return votetype;
	}
	
	public void setVotetype(Long votetype) {
		this.votetype = votetype;
	}

	public String getUrloption() {
		return urloption;
	}

	public void setUrloption(String urloption) {
		this.urloption = urloption;
	}

	public int getTypeindex() {
		return typeindex;
	}

	public void setTypeindex(int typeindex) {
		this.typeindex = typeindex;
	}

	private Long voteid;

	public Long getVoteid() {
		return voteid;
	}

	public void setVoteid(Long voteid) {
		this.voteid = voteid;
	}

	private Map<IrpVote, List<IrpVoteOptions>> maplist;

	public Map<IrpVote, List<IrpVoteOptions>> getMaplist() {
		return maplist;
	}

	public void setMaplist(Map<IrpVote, List<IrpVoteOptions>> maplist) {
		this.maplist = maplist;
	}

	private String friendlyshow;

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}

	private List<IrpVoteOptions> irpVoteOptions;

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpVoteOptionsService getIrpVoteOptionsService() {
		return irpVoteOptionsService;
	}

	public void setIrpVoteOptionsService(
			IrpVoteOptionsService irpVoteOptionsService) {
		this.irpVoteOptionsService = irpVoteOptionsService;
	}

	public List<IrpVoteOptions> getIrpVoteOptions() {
		return irpVoteOptions;
	}

	public void setIrpVoteOptions(List<IrpVoteOptions> irpVoteOptions) {
		this.irpVoteOptions = irpVoteOptions;
	}

	public Long getVotedid() {
		return votedid;
	}

	public void setVotedid(Long votedid) {
		this.votedid = votedid;
	}

	/**
	 * 分页排序检索
	 */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private String orderField = "";
	private String orderBy = "";
	private String searchWord;
	private String searchType;

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

	public String getJsonoptionimg() {
		return jsonoptionimg;
	}

	public List<IrpVote> getIrpVotestitle() {
		return irpVotestitle;
	}

	public void setIrpVotestitle(List<IrpVote> irpVotestitle) {
		this.irpVotestitle = irpVotestitle;
	}

	public void setJsonoptionimg(String jsonoptionimg) {
		this.jsonoptionimg = jsonoptionimg;
	}

	public IrpVote getIrptitle() {
		return irptitle;
	}

	public void setIrptitle(IrpVote irptitle) {
		this.irptitle = irptitle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSoptionstr() {
		return soptionstr;
	}

	public void setSoptionstr(String soptionstr) {
		this.soptionstr = soptionstr;
	}

	public IrpVoteOptions getVoteOptions() {
		return voteOptions;
	}

	public void setVoteOptions(IrpVoteOptions voteOptions) {
		this.voteOptions = voteOptions;
	}

	public IrpVote getIrpvotetitle() {
		return irpvotetitle;
	}

	public void setIrpvotetitle(IrpVote irpvotetitle) {
		this.irpvotetitle = irpvotetitle;
	}

	public IrpVoteService getIrpVoteService() {
		return irpVoteService;
	}

	public void setIrpVoteService(IrpVoteService irpVoteService) {
		this.irpVoteService = irpVoteService;
	}

	private String iso2utf() {
		String option = this.soptionstr;
		/*try {
			irptitle.setTitle(new String(irptitle.getTitle().getBytes(
					"ISO-8859-1"), "UTF-8"));
			irpvotetitle.setVotetitle(new String(irpvotetitle.getVotetitle()
					.getBytes("ISO-8859-1"), "UTF-8"));
			if (irptitle.getDescription() != null
					&& irptitle.getDescription().length() > 0) {
				irptitle.setDescription(new String(irptitle.getDescription()
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		return option;
	}

	/**
	 * 保存投票
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveVotefirst() throws Exception {
		String option = iso2utf();
		// 标题 分组标题 多个选项
		Long voteid = irpVoteService.addVoteandOption(irptitle, irpvotetitle,option,urloption,this.getVotetype());
		this.setVotedid(voteid);
		return SUCCESS;
	}
	
	public String saveopinVotefirst() throws Exception {
		String option = iso2utf();
		// 标题 分组标题 多个选项
		Long voteid = irpVoteService.addVoteandOption(irptitle, irpvotetitle,option,urloption,3L);
		this.setVotedid(voteid);
		return SUCCESS;
	}

	/**
	 * 保存投票分组和选项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveVotesecond() throws Exception {
		/*irpvotetitle.setVotetitle(new String(irpvotetitle.getVotetitle().getBytes("ISO-8859-1"), "UTF-8"));*/
		// 标题 分组标题 多个选项
		irpVoteService.addTitleandOption(irpvotetitle, this.getSoptionstr(),this.urloption,voteid);
		return SUCCESS;
	}

	/**
	 * 图片投票
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePicVotefirst() throws Exception {
		String option = iso2utf();
		// 标题 分组标题 多个选项
		Long voteid = irpVoteService.addPicVoteandOption(irptitle,irpvotetitle, option,urloption, jsonoptionimg);
		this.setVotedid(voteid);
		return SUCCESS;
	}

	/**
	 * 投票列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String voteList() throws Exception {
		this.setTypeindex(1);
		try {
			int result = irpVoteService.findAllVotecount();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					result);
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "CRTIME desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			irpVotestitle = irpVoteService.findAllVote(pageUtil, _oOrderby);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
			for (int i = 0; i < irpVotestitle.size(); i++) {
				List<IrpVoteOptions> optionlist = null;
				IrpVote iv = irpVotestitle.get(i);
				IrpVote irptitlev = irpVoteService.findVotetitleBypid(
						iv.getVoteid()).get(0);
				optionlist = irpVoteOptionsService.findOptionBypid(irptitlev.getVoteid(),"optionid");
				maplist.put(iv, optionlist);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	
	private int pagestart=1;
	private int pageend=2;

	public int getPagestart() {
		return pagestart;
	}

	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}

	public int getPageend() {
		return pageend;
	}

	public void setPageend(int pageend) {
		this.pageend = pageend;
	}
	String tokens;
	
	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	/**
	 * 详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String voteDetil() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		typeindex = 4;
		IrpVote irptitler = irpVoteService.findbyvoteid(voteid);
		boolean fag=false;
		boolean fags = irpVoteService.isuservote(voteid);
		if (fags) {
			fag=false;
		} else {
			// 投过票
			fag = true;
		}
		if(fag){
			irptitle=irptitler;
			irpVotestitle = irpVoteService.findVotetitleBypid(voteid);
			maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
			List<IrpVoteOptions> optionlist = null;
			for (int i = 0; i < irpVotestitle.size(); i++) {
				optionlist = irpVoteOptionsService
						.findOptionBypid(irpVotestitle.get(i).getVoteid(),"optionid");
				maplist.put(irpVotestitle.get(i), optionlist);
			}
			if(irptitler.getVotetype()==IrpVote.VOTETYPE_WORD){
				return "wordresult";
			}else if(irptitler.getVotetype()==IrpVote.VOTETYPE_PIC){
				return "picresult";
			}else if(irptitler.getVotetype()==IrpVote.VOTETYPE_URL){
				
				//查询评论
				if(optionlist!=null){
					toolTFatti(optionlist);
				}
				toolDifferent();
				return "poinresult";
			}else{
				return ERROR;
			}
		}else{
			return detilandupdate(voteid);
		}
	}
	private void toolDifferent(){
		//查询个性评论
		int result = irpMicroBlogService.findMicBytypeandvoteidcount(voteid,IrpMicroblog.TJNGMICROBLOG);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),result);
		irpMicroblogs=irpMicroBlogService.findMicBytypeandvoteid(voteid,IrpMicroblog.TJNGMICROBLOG,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
	}
	
	private int datacountr;
	private int datacountb;
	
	public int getDatacountr() {
		return datacountr;
	}

	public void setDatacountr(int datacountr) {
		this.datacountr = datacountr;
	}

	public int getDatacountb() {
		return datacountb;
	}

	public void setDatacountb(int datacountb) {
		this.datacountb = datacountb;
	}

	public void toolTFatti(List<IrpVoteOptions> optionlist){
		for(int i=0;i<optionlist.size();i++){
			IrpVoteOptions ios=optionlist.get(i);
			if(i==0){
				datacountr=irpMicroBlogService.findMicBytypeandvoteidcount(ios.getOptionid(), IrpMicroblog.PINGMICROBLOG);
				if(datacountr>=pagestart){
					irpMicroblogr=irpMicroBlogService.findMicBytype(ios.getOptionid(),IrpMicroblog.PINGMICROBLOG,(pagestart-1)*pageend,pageend);
				}else{
					irpMicroblogr=new ArrayList<IrpMicroblog>();
				}
			}else{
				datacountb=irpMicroBlogService.findMicBytypeandvoteidcount(ios.getOptionid(), IrpMicroblog.PINGMICROBLOG);
				if(datacountb>=pagestart){
					irpMicroblogb=irpMicroBlogService.findMicBytype(ios.getOptionid(),IrpMicroblog.PINGMICROBLOG,(pagestart-1)*pageend,pageend);
				}else{
					irpMicroblogb=new ArrayList<IrpMicroblog>();
				}
				
			}
		}
	}
	
	
	public String findTFatti(){
		irptitle=irpVoteService.findbyvoteid(voteid);;
		irpVotestitle = irpVoteService.findVotetitleBypid(voteid);
		maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
		List<IrpVoteOptions> optionlist = null;
		for (int i = 0; i < irpVotestitle.size(); i++) {
			optionlist = irpVoteOptionsService
					.findOptionBypid(irpVotestitle.get(i).getVoteid(),"optionid");
			maplist.put(irpVotestitle.get(i), optionlist);
		}
		if(irptitle.getVotetype()==IrpVote.VOTETYPE_URL){
			//查询评论
			if(optionlist!=null){
				IrpVoteOptions ios=optionlist.get(Integer.parseInt(myagree)-1);
				irpMicroblogs=irpMicroBlogService.findMicBytype(ios.getOptionid(),IrpMicroblog.PINGMICROBLOG,(pagestart-1)*pageend,pageend);
			}
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String findDifferent(){
		toolDifferent();
		return SUCCESS;
	}
	
	// 取名
	public String findUsername(Long _userid) {
		String name = irpUserService.findShowNameByUserid(_userid);
		return name;
	}

	/**
	 * 投票
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendVoterecode() {
		if (voteid == null || soptionstr == null) {
			this.setFriendlyshow("糟糕,请联系管理员");
			return ERROR;
		} else {
			// 判断
			boolean fag = irpVoteService.isuservote(voteid);
			if (fag) {
				// 投票
				irpVoteService.addVoterecords(voteid, soptionstr);
				this.setMessage("投票成功");
				return SUCCESS;
			} else {
				this.setMessage("你已经投过票了");
				return SUCCESS;
			}
		}
	}

	
	/**
	 * 投票结果判断
	 * 
	 * @return
	 * @throws Exception
	 */
	public String votecheckDetilResult() {
		// 判断是否有查看权限
		boolean fag = false;
		try {
			// 0 投票后可见 1自己可见 2所有人可见
			irptitle = irpVoteService.findbyvoteid(voteid);
			if (irptitle.getResultshow() == 0) {
				// 判断是否投过票 voteid
				boolean fags = irpVoteService.isuservote(voteid);
				if (fags) {
					if (irptitle.getCruserid() == LoginUtil.getLoginUserId()) {
						fag = true;
					} else {
						fag = false;
					}
				} else {
					// 投过票
					fag = true;
				}
			} else {
				fag = true;
			}
			this.setMessage(fag+"");
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	public String votecheckDetilResultMore() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		// 判断是否有查看权限
		Long fags = irpVoteService.isuservoteid(voteid);
		if(fags>0){
			this.setMessage(fags+"");
		}else{
			irptitle = irpVoteService.findbyvoteid(voteid);
			if (irptitle.getCruserid() == irpuser.getUserid()) {
				this.setMessage("2");
			} else {
				if(irptitle.getResultshow() == 2){
					this.setMessage("3");
				}else{
					this.setMessage("4");
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 投票结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String voteDetilResult() {
		// 判断是否有查看权限
		boolean fag = false;
		try {
			// 0 投票后可见 1自己可见 2所有人可见
			irptitle = irpVoteService.findbyvoteid(voteid);
			if(irptitle!=null){
				if (irptitle.getResultshow() == 0) {
					// 判断是否投过票 voteid
					boolean fags = irpVoteService.isuservote(voteid);
					if (fags) {
						if (irptitle.getCruserid() == LoginUtil.getLoginUserId()) {
							fag = true;
						} else {
							fag = false;
						}
					} else {
						// 投过票
						fag = true;
					}
				} else {
					fag = true;
				}
				if (fag) {
					irpVotestitle = irpVoteService.findVotetitleBypid(voteid);
					maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
					List<IrpVoteOptions> optionlist = null;
					for (int i = 0; i < irpVotestitle.size(); i++) {
						optionlist = irpVoteOptionsService
								.findOptionBypid(irpVotestitle.get(i).getVoteid(),"optionid");
						maplist.put(irpVotestitle.get(i), optionlist);
					}
					if(irptitle.getVotetype()==IrpVote.VOTETYPE_WORD){
						return SUCCESS;
					}else if(irptitle.getVotetype()==IrpVote.VOTETYPE_PIC){
						return "pic";
					}else if(irptitle.getVotetype()==IrpVote.VOTETYPE_URL){
						//查询评论
						if(optionlist!=null){
							toolTFatti(optionlist);
						}
						toolDifferent();
						return "url";
					}else{
						return SUCCESS;
					}
				} else {
					this.setMessage("您没有权限");
					return "error";
				}
			}else{
				this.setMessage("投票还未发布");
				return "error";
			}
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	

	/**
	 * 我发起的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String voteMyvote() throws Exception {
		try {
			this.setTypeindex(2);
			int result = irpVoteService.findAllMyvotecount();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					result);
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "CRTIME desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			irpVotestitle = irpVoteService.findAllMyvote(pageUtil, _oOrderby);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
			for (int i = 0; i < irpVotestitle.size(); i++) {
				List<IrpVoteOptions> optionlist = null;
				IrpVote iv = irpVotestitle.get(i);
				IrpVote irptitlev = irpVoteService.findVotetitleBypid(
						iv.getVoteid()).get(0);
				optionlist = irpVoteOptionsService.findOptionBypid(irptitlev.getVoteid(),"optionid");
				maplist.put(iv, optionlist);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 我参与的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String votePartmevote() throws Exception {
		try {
			this.setTypeindex(3);
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "CRTIME desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cruserid", LoginUtil.getLoginUserId());
			map.put("orderby", _oOrderby);
			int result = irpVoteService.findPartmevotecount(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					result);
			irpVotestitle = irpVoteService.findPartmevote(map, pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
			for (int i = 0; i < irpVotestitle.size(); i++) {
				List<IrpVoteOptions> optionlist = null;
				IrpVote iv = irpVotestitle.get(i);
				IrpVote irptitlev = irpVoteService.findVotetitleBypid(
						iv.getVoteid()).get(0);
				optionlist = irpVoteOptionsService.findOptionBypid(irptitlev.getVoteid(),"optionid");
				maplist.put(iv, optionlist);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String voteUpdatevote() throws Exception {
		typeindex = 5;
		try {
			irptitle = irpVoteService.findbyvoteid(voteid);
			if (irptitle.getIspublish() == IrpVote.ISPUBLISH_NO&&irptitle.getCruserid() == LoginUtil.getLoginUserId()) {
				irpVotestitle = irpVoteService.findVotetitleBypid(voteid);
				maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
				List<IrpVoteOptions> optionlist = null;
				for (int i = 0; i < irpVotestitle.size(); i++) {
					optionlist = irpVoteOptionsService
							.findOptionBypid(irpVotestitle.get(i).getVoteid(),"optionid");
					maplist.put(irpVotestitle.get(i), optionlist);
				}
				int vtype = irptitle.getVotetype();
				if (vtype == 1) {
					return SUCCESS;
				} else if (vtype == 2) {
					return "pic";
				} else if (vtype == 3) {
					return "url";
				} else {
					return ERROR;
				}
			} else {
				this.setFriendlyshow("sorry,投票不能修改");
				return ERROR;
			}
		} catch (Exception e) {
			this.setFriendlyshow("sorry,投票不存在");
			return ERROR;
		}
	}
//细缆
	private String detilandupdate(Long vid) {
		try {
			irptitle = irpVoteService.findbyvoteid(vid);
			if (irptitle.getIspublish() == IrpVote.ISPUBLISH_YES
					|| irptitle.getCruserid() == LoginUtil.getLoginUserId()) {
				irpVotestitle = irpVoteService.findVotetitleBypid(vid);
				maplist = new LinkedHashMap<IrpVote, List<IrpVoteOptions>>();
				List<IrpVoteOptions> optionlist = null;
				int vtype = irptitle.getVotetype();
				for (int i = 0; i < irpVotestitle.size(); i++) {
					optionlist = irpVoteOptionsService.findOptionBypid(irpVotestitle.get(i).getVoteid(),"optionid");
					maplist.put(irpVotestitle.get(i), optionlist);
				}
				if (vtype == 1) {
					return SUCCESS;
				} else if (vtype == 2) {
					return "pic";
				} else if (vtype == 3) {
					//查询评论
					if(optionlist!=null){
						toolTFatti(optionlist);
					}
					toolDifferent();
					return "url";
				} else {
					return ERROR;
				}
			} else {
				this.setFriendlyshow("sorry,投票还没发布");
				return ERROR;
			}
		} catch (Exception e) {
			this.setFriendlyshow("sorry,投票还没发布");
			return ERROR;
		}
	}

	
	
	
	private String myagree;
	private String title;
	private String option;
	private Long optionid;
	private Long titleid;
	
	public Long getTitleid() {
		return titleid;
	}

	public void setTitleid(Long titleid) {
		this.titleid = titleid;
	}

	public Long getOptionid() {
		return optionid;
	}

	public void setOptionid(Long optionid) {
		this.optionid = optionid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getMyagree() {
		return myagree;
	}

	public void setMyagree(String myagree) {
		this.myagree = myagree;
	}

	//跳转
	public String jumpVotetf() {
		return SUCCESS;
	}
	//投票  并跳转到发表观点
	public String addVotetf() {
		//加一票
		if(Integer.parseInt(this.getMyagree())>0){
			irpVoteService.addVoterecords(titleid,optionid.toString());
		}
		if(title==null||title.length()<1){
			return ERROR;
		}else{
			/*try {
				this.setTitle(new String(title.getBytes("ISO-8859-1"), "UTF-8"));
				this.setOption(new String(option.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
			/*try {
				title=URLDecoder.decode(title, "utf-8");
				option=URLDecoder.decode(option, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//编码解码 ​​​​ 
*/			this.setTitle(title);
			this.setOption(option);
			return SUCCESS;
		}
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
	 * 通过id找出相对应的个人信息
	 * @return
	 */
    public IrpUser findUserByUserId(long _userid){
  	  IrpUser irpUser = null;
  	  irpUser =  this.irpUserService.findUserByUserId(_userid);
  	  return irpUser;
  	}
}
