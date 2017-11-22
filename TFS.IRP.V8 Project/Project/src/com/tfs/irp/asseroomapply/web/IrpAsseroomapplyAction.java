package com.tfs.irp.asseroomapply.web;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.TableUI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.net.aso.e;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.util.Function;
import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample;
import com.tfs.irp.asseroom.service.IrpAsseroomService;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample.Criteria;
import com.tfs.irp.asseroomapply.entity.ViewFrom;
import com.tfs.irp.asseroomapply.service.IrpAsseroomapplyService;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.asseroomsb.service.IrpAsseroomsbService;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblink;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblinkExample;
import com.tfs.irp.asseroomsblink.service.IrpAsseroomsblinkService;
import com.tfs.irp.assetype.entity.IrpAssetype;
import com.tfs.irp.assetype.entity.IrpAssetypeExample;
import com.tfs.irp.assetype.service.IrpAssetypeService;
import com.tfs.irp.asseuser.entity.IrpAsseuser;
import com.tfs.irp.asseuser.entity.IrpAsseuserExample;
import com.tfs.irp.asseuser.service.IrpAsseuserService;
import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;
import com.tfs.irp.assewarn.service.IrpAssewarnService;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userRandom.util.EmailUtils;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.sms.SmsUtil;

public class IrpAsseroomapplyAction extends ActionSupport {
	
	/**
	 * 关于私信的接口
	 */
	private IrpMessageContentService irpMessageContentService;
	private IrpAsseroomapplyService asseroomapplyService;
	private IrpAsseuserService asseuserService;
	private IrpAssetypeService assetypeService;
	private IrpAssewarnService assewarnService;
	private IrpAsseroomService asseroomService;
	private static final long serialVersionUID = 1L;
	private List<IrpAttached> attacheds;
	private Long asseroomid;
	private IrpAsseroomsblinkService asseroomsblinkService;
	private IrpAsseroomsbService asseroomsbService;
	private List<IrpAsseroomsb> asseroomsbList;
	private IrpGroupService irpGroupService;
	private List<IrpUser> irpUsers;
	private String searchType;
	
	private String userids;
	private String tokens;
	
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	/**
	 * 通知方式
	 */
	private String[] warnMenthod;
	public String[] getWarnMenthod() {
		return warnMenthod;
	}
	public void setWarnMenthod(String[] warnMenthod) {
		this.warnMenthod = warnMenthod;
	}
	private Long groupId;
	/**
	 * 会议日期json模式
	 */
	private String xaxisJasonString;
	/**
	 * 给摸一个人发送得ID
	 */
	private Long sendid;
	/**
	 * 姓名
	 */
	private String sendusername;
	/**
	 * 私信建议
	 */
	private String sendcontent;
	/**
	 * 附加私信内容
	 */
	private String attachsendcontent;
	public Long getSendid() {
		return sendid;
	}
	public void setSendid(Long sendid) {
		this.sendid = sendid;
	}
	public String getSendusername() {
		return sendusername;
	}
	public void setSendusername(String sendusername) {
		this.sendusername = sendusername;
	}
	public String getSendcontent() {
		return sendcontent;
	}
	public void setSendcontent(String sendcontent) {
		this.sendcontent = sendcontent;
	}

	public String getAttachsendcontent() {
		return attachsendcontent;
	}
	public void setAttachsendcontent(String attachsendcontent) {
		this.attachsendcontent = attachsendcontent;
	}

	/**
	 * 会议室名字统计
	 */
	private String roomnamejson;
	/**
	 * 会议室使用次数统计
	 */
	private String roomcountjson;
	
	public String getRoomnamejson() {
		return roomnamejson;
	}
	public void setRoomnamejson(String roomnamejson) {
		this.roomnamejson = roomnamejson;
	}
	public String getRoomcountjson() {
		return roomcountjson;
	}
	public void setRoomcountjson(String roomcountjson) {
		this.roomcountjson = roomcountjson;
	}
	/**
	 * 被选中的会议室设备
	 */
	private String sbids_1;
	/**
	 * 会议室设备回显
	 */
	List<Long> sbidList_1;
	/**
	 * 会议室人数
	 */
	private Long people;
	
	
	public Long getPeople() {
		return people;
	}
	public void setPeople(Long people) {
		this.people = people;
	}
	public String getSbids_1() {
		return sbids_1;
	}
	public void setSbids_1(String sbids_1) {
		this.sbids_1 = sbids_1;
	}
	public List<Long> getSbidList_1() {
		return sbidList_1;
	}
	public void setSbidList_1(List<Long> sbidList_1) {
		this.sbidList_1 = sbidList_1;
	}
	/**
	 * 参加会议的某个人员id
	 */
	private long asseroomuserid;
	
	public long getAsseroomuserid() {
		return asseroomuserid;
	}
	public void setAsseroomuserid(long asseroomuserid) {
		this.asseroomuserid = asseroomuserid;
	}
	/**
	 * 此次会议所用设备
	 */
	private String[] asseroomsbs;
	/**
	 * 统计时指定时间
	 */
	private String timeLimit;
	
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String[] getAsseroomsbs() {
		return asseroomsbs;
	}
	public void setAsseroomsbs(String[] asseroomsbs) {
		this.asseroomsbs = asseroomsbs;
	}
	public List<IrpAsseroomsb> getAsseroomsbList() {
		return asseroomsbList;
	}
	public void setAsseroomsbList(List<IrpAsseroomsb> asseroomsbList) {
		this.asseroomsbList = asseroomsbList;
	}
	public IrpAsseroomsblinkService getAsseroomsblinkService() {
		return asseroomsblinkService;
	}
	public void setAsseroomsblinkService(
			IrpAsseroomsblinkService asseroomsblinkService) {
		this.asseroomsblinkService = asseroomsblinkService;
	}
	public IrpAsseroomsbService getAsseroomsbService() {
		return asseroomsbService;
	}
	public void setAsseroomsbService(IrpAsseroomsbService asseroomsbService) {
		this.asseroomsbService = asseroomsbService;
	}
	public Long getAsseroomid() {
		return asseroomid;
	}
	public void setAsseroomid(Long asseroomid) {
		this.asseroomid = asseroomid;
	}
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public IrpAsseroomService getAsseroomService() {
		return asseroomService;
	}
	public void setAsseroomService(IrpAsseroomService asseroomService) {
		this.asseroomService = asseroomService;
	}
	/**
	 * 附件
	 */
	private String jsonFile;
	
	/**
	 * 附件接口
	 */
	private IrpAttachedService irpAttachedService;
	private IrpAttachedTypeService irpAttachedTypeService;
	
	
	private IrpAsseroomapply asseroomapply;
	/**
	 * 召集人姓名
	 */
	private String convenerusername;
	/**
	 * 参会人员姓名
	 */
	private String fromidAll;
	/**
	 * 联系人姓名
	 */
	private String linkmanusername;
	 /**
	 * 人员
	 */
	private IrpUserService irpUserService;
	public String getLinkmanusername() {
		return linkmanusername;
	}
	public void setLinkmanusername(String linkmanusername) {
		this.linkmanusername = linkmanusername;
	}
	public String getFromidAll() {
		return fromidAll;
	}
	public void setFromidAll(String fromidAll) {
		this.fromidAll = fromidAll;
	}
	public String getConvenerusername() {
		return convenerusername;
	}
	public void setConvenerusername(String convenerusername) {
		this.convenerusername = convenerusername;
	}
	private List<IrpAsseroomapply> asseroomapplyList;
	/*检索*/
	private String searchWord;
	private String start;
	private String end;
	private String warn;
	
	
	
	/*
	 * 分页排序
	 */

	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";
	
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
	 * 存放会议申请的id
	 */
	private Long asseroomapplyid;
	
	/**
	 * 开始时间
	 */
	private Date startTime ;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 选择的时间
	 */
	private Date sel_Date;
	/**
	 * 判断 是选择的时间还是指定的时间（0，1）
	 */
	private int flag;
	
	public Date getSel_Date() {
		return sel_Date;
	}
	public void setSel_Date(Date sel_Date) {
		this.sel_Date = sel_Date;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	/**
	 * 存放会议室的集合
	 */
	private List<IrpAsseroom> asseroomList;
	/**
	 * 存放会议类型的集合
	 */
	private List<IrpAssetype> assetypeList;
	/**
	 * 存放会议通知类型的集合
	 */
	private List<IrpAssewarn> assewarnList;
	/**
	 * 判断是前台删除还是后台删除
	 */
	private int preOrOld;
	private IrpAsseroom asseroom;
	private IrpAssetype assetype;
	private IrpAssewarn assewarn;
	
	public IrpAsseroom getAsseroom() {
		return asseroom;
	}
	public void setAsseroom(IrpAsseroom asseroom) {
		this.asseroom = asseroom;
	}
	public IrpAssetype getAssetype() {
		return assetype;
	}
	public void setAssetype(IrpAssetype assetype) {
		this.assetype = assetype;
	}
	public IrpAssewarn getAssewarn() {
		return assewarn;
	}
	public void setAssewarn(IrpAssewarn assewarn) {
		this.assewarn = assewarn;
	}
	public int getPreOrOld() {
		return preOrOld;
	}
	public void setPreOrOld(int preOrOld) {
		this.preOrOld = preOrOld;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<IrpAsseroom> getAsseroomList() {
		return asseroomList;
	}
	public void setAsseroomList(List<IrpAsseroom> asseroomList) {
		this.asseroomList = asseroomList;
	}
	public List<IrpAssetype> getAssetypeList() {
		return assetypeList;
	}
	public void setAssetypeList(List<IrpAssetype> assetypeList) {
		this.assetypeList = assetypeList;
	}
	public List<IrpAssewarn> getAssewarnList() {
		return assewarnList;
	}
	public void setAssewarnList(List<IrpAssewarn> assewarnList) {
		this.assewarnList = assewarnList;
	}
	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}
	
	public IrpAsseroomapplyService getAsseroomapplyService() {
		return asseroomapplyService;
	}
	public void setAsseroomapplyService(IrpAsseroomapplyService asseroomapplyService) {
		this.asseroomapplyService = asseroomapplyService;
	}
	public IrpAsseuserService getAsseuserService() {
		return asseuserService;
	}
	public void setAsseuserService(IrpAsseuserService asseuserService) {
		this.asseuserService = asseuserService;
	}
	public IrpAssetypeService getAssetypeService() {
		return assetypeService;
	}
	public void setAssetypeService(IrpAssetypeService assetypeService) {
		this.assetypeService = assetypeService;
	}
	public IrpAssewarnService getAssewarnService() {
		return assewarnService;
	}
	public void setAssewarnService(IrpAssewarnService assewarnService) {
		this.assewarnService = assewarnService;
	}
	public IrpAsseroomapply getAsseroomapply() {
		return asseroomapply;
	}
	public void setAsseroomapply(IrpAsseroomapply asseroomapply) {
		this.asseroomapply = asseroomapply;
	}
	public List<IrpAsseroomapply> getAsseroomapplyList() {
		return asseroomapplyList;
	}
	public void setAsseroomapplyList(List<IrpAsseroomapply> asseroomapplyList) {
		this.asseroomapplyList = asseroomapplyList;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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
	public Long getAsseroomapplyid() {
		return asseroomapplyid;
	}
	public void setAsseroomapplyid(Long asseroomapplyid) {
		this.asseroomapplyid = asseroomapplyid;
	}
	/*
	 * 多选会议室
	 */
	private String asseroomids;
	private Long conveneruserid;
	
	public String getAsseroomids() {
		return asseroomids;
	}
	public void setAsseroomids(String asseroomids) {
		this.asseroomids = asseroomids;
	}
	/**
	 * 查询所有预约会议室
	 * @return
	 * @throws Exception
	 */
	public void findAll() throws Exception {
		IrpAsseroomapplyExample example=new IrpAsseroomapplyExample();
		Criteria criteria=example.createCriteria();
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "starttime desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		//按会议室查询
		if(searchWord!=null&&!"".equals(searchWord)){
			if(!searchWord.equals("all")){
				Long asserromid=Long.parseLong(searchWord);
				criteria.andAsseroomidEqualTo(asserromid);
			}
		}
		//按时间查询,开始时间
		if(startTime!=null&&!"".equals(startTime))
			criteria.andStarttimeBetween(startTime, endTime);
		example.setOrderByClause("starttime asc");
		asseroomapplyList=asseroomapplyService.querySbForPage(example);
		
		
		
		
		ActionUtil.writer(getJsonFrom(asseroomapplyList)+"");
	}
	/**
	 * 统计会议室使用的频率
	 * @return
	 * @throws Exception 
	 */
	public String findForPageAndCount() throws Exception{
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "asseroomid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
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
		}else{
			startTime=DateUtils.getDateByYMDHM(start);
			endTime=DateUtils.getDateByYMDHM(end);
		} //否则开始结束时间都是有值的，即任意时间
		//按会议室查询
		StringBuffer asseroomname=new StringBuffer();
		StringBuffer asseroomcount=new StringBuffer();
		int count =0;
		if(asseroomids!=null&&!"".equals(asseroomids)){
			String[] idsStrings=asseroomids.split(",");
			count=idsStrings.length;
			for (int i = 0; i < idsStrings.length; i++) {
				IrpAsseroomapplyExample example=new IrpAsseroomapplyExample();
				Criteria criteria=example.createCriteria();
				criteria.andStarttimeBetween(startTime, endTime);
				if(!"".equals(idsStrings[i])){
					criteria.andAsseroomidEqualTo(Long.parseLong(idsStrings[i]));
					example.setOrderByClause(_oOrderby);
					asseroomapplyList=asseroomapplyService.querySbForPage(example);
					asseroomname.append("'"+asseroomService.selectByPrimaryKey(Long.parseLong(idsStrings[i].trim())).getAsseroomname()+"',");
					asseroomcount.append(asseroomapplyList.size()+",");
				}
			}
		}
		//排序
		String[] name=asseroomname.toString().substring(0,asseroomname.toString().length()-1).split(",");
		String[] num=asseroomcount.toString().substring(0,asseroomcount.toString().length()-1).split(",");
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if(Integer.parseInt(num[i])<Integer.parseInt(num[j])){
					String change=num[i];
					num[i]=num[j];
					num[j]=change;
					change=name[i];
					name[i]=name[j];
					name[j]=change;
				}
			}
		}
		 asseroomname=new StringBuffer();
		 asseroomcount=new StringBuffer();
		for (int i = 0; i < num.length; i++) {
			asseroomname.append(name[i]+",");
			asseroomcount.append(num[i]+",");
		}
		roomnamejson=asseroomname.toString().substring(0,asseroomname.toString().length()-1);
		roomcountjson=asseroomcount.toString().substring(0,asseroomcount.toString().length()-1);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				count);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 将预约会议室信息封装成日历控件对象
	 * @param _asseroomapplyList
	 * @return
	 * @throws SQLException
	 */
	public String getJsonFrom(List<IrpAsseroomapply> _asseroomapplyList) throws SQLException{
		List<ViewFrom> viewFromList=null;
		if(null!=_asseroomapplyList&&!_asseroomapplyList.isEmpty()){
			viewFromList=new ArrayList<ViewFrom>();
			for (IrpAsseroomapply a : _asseroomapplyList) {
				ViewFrom viewFrom=new ViewFrom();
				viewFrom.setId(a.getAsseroomapplyid());
				viewFrom.setSid(a.getAsseroomapplyid());
				viewFrom.setConfname(asseroomService.selectByPrimaryKey(a.getAsseroomid()).getAsseroomname());
				viewFrom.setDescription(a.getAsseroomcontent());
				viewFrom.setFullname(a.getAssename());
				viewFrom.setStart(a.getStarttime());
				viewFrom.setEnd(a.getEndtime());
				//判断是否全天
				boolean allDay=(a.getEndtime().getTime()-a.getStarttime().getTime())>=(24 * 60 * 60 * 1000)? true:false;
				viewFrom .setAllDay(allDay);
				//判断是否已经完成
				if((a.getStarttime().getTime()-new Date().getTime())<=0)
					viewFrom .setBackgroundColor("red");
				viewFromList.add(viewFrom);
			}
		}
		//将list转化成json格式
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(viewFromList);
		return jsonArray.toString();
	}
	public String calender() throws Exception {
		return super.execute();
	}
	/**
	 * 查询所有会议室返回页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findAsseroom() throws Exception {
		findAllAssewarn();//通知类型
		findAllAssetype();//会议类型
		findAllAsseroom();//会议室
		IrpUser loginUser = LoginUtil.getLoginUser();
		convenerusername=loginUser.getUsername();//召集人姓名
		if(searchWord!=null&&!"".equals(searchWord)){
			preOrOld=1;
		}else{
			preOrOld=0;
		}
		return super.execute();
	}
	/**
	 * 查询所有会议室返回页面 mobile
	 * @return
	 * @throws Exception
	 * @author   npz
	 * @date 2017年8月14日
	 */
	public String findAsseroom_app() throws Exception {
		findAllAssewarn();//通知类型
		findAllAssetype();//会议类型
		findAllAsseroom();//会议室
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser loginUser = mobileAction.getlogin(token);
		convenerusername=loginUser.getUsername();//召集人姓名
		if(searchWord!=null&&!"".equals(searchWord)){
			preOrOld=1;
		}else{
			preOrOld=0;
		}
		return super.execute();
	}
	
	public String toAdd() throws Exception{
		findAllAssewarn();//通知类型
		findAllAssetype();//会议类型
		findAllAsseroom();//会议室
		IrpUser loginUser = LoginUtil.getLoginUser();
		convenerusername=loginUser.getUsername();//召集人姓名
		conveneruserid=loginUser.getUserid();
		searchWord=searchWord;
		start=start;
		end=end;
		warn=warn;
		if(preOrOld==1)
			return "root";
		else
			return "noroot";
	}
	
	
	/**
	 * 申请会议室
	 * @throws Exception
	 */
	public void addApply(){
		 //存放所有参会人员id
        List<Long > useridList=new ArrayList<Long>();
		IrpUser loginUser = LoginUtil.getLoginUser();
		long id=loginUser.getId();//创建人id
		Long tableid=TableIdUtil.getNextId(IrpAsseroomapply.TABLE_NAME);
		asseroomapply.setWarnid(1L);
		asseroomapply.setAsseroomapplyid(tableid);
		asseroomapply.setCrtime(new Date());
		asseroomapply.setCruserid(id);
		asseroomapply.setApplystatus(1);
		asseroomapply.setAsseconveneruserid(id);
		List<IrpUser> irpUser=irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(linkmanusername));
		long asselinkmanuserid;
		if(irpUser.size()>0){
			asselinkmanuserid=irpUser.get(0).getId();//联系人id
			asseroomapply.setAsselinkmanuserid(asselinkmanuserid);
		}
		 irpUser=irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(convenerusername));
		long asseconveneruserid;
		if(irpUser.size()>0){
			asseconveneruserid=irpUser.get(0).getId();//召集人id
			asseroomapply.setAsseconveneruserid(asseconveneruserid);
		}
		if(null!=asseroomapply.getAsseroomsbids()&&!asseroomapply.getAsseroomsbids().equals("")){
			asseroomapply.setAsseroomsbids(asseroomapply.getAsseroomsbids().substring(0, asseroomapply.getAsseroomsbids().length()-1));
		}
		asseroomapply.setStarttime(DateUtils.getDateByYMDHM(asseroomapply.getStart()));
		asseroomapply.setEndtime(DateUtils.getDateByYMDHM(asseroomapply.getEnd()));
		String[] warndataString=asseroomapply.getWarnData();
		asseroomapply.setAttched("0");
		if(null!=warndataString&&warndataString.length>0){
			for (int i = 0; i <asseroomapply.getWarnData().length; i++) {
				Date date=DateUtils.getDateByYMDHM(warndataString[i]);
				if(date.compareTo(new Date())>=0)
					asseroomapply.setWarntime(date); 
				else {
					asseroomapply.setAttched("1");
				}
			}
		}
		//会议通知类型
		StringBuffer warnMenthodBuffer=new StringBuffer();
		if(null!=warnMenthod&&warnMenthod.length>0){
			for (int i = 0; i < warnMenthod.length; i++) {
				warnMenthodBuffer.append(warnMenthod[i]);
			}
			asseroomapply.setWarnid(Long.parseLong(warnMenthodBuffer.toString()));
		}
		if (jsonFile != null && jsonFile != "") { 
			try{
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
					addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, tableid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}
		
		Long _cataStatus=0L;
		try {
			_cataStatus = asseroomapplyService.addApply(asseroomapply);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String fromidAlls =  fromidAll.replace(","," ").replace(";"," ").replace(":"," ").replace("，"," ").replace("；"," ").replace("："," ");
        String[] fromidArray = fromidAlls.split(" ");
		for (int i = 0; i < fromidArray.length; i++) {
			List<IrpUser> irpUser1 = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(fromidArray[i]));
			if (irpUser1.size() >= 1) {
				long userids = irpUser1.get(0).getUserid();
					useridList.add(userids);
			}
		}
		if(null!=useridList&&!useridList.isEmpty()){
			try {
				//申请人员表中添加
				insertAsseUser(useridList, tableid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null!=asseroomapply.getWarntime()){
				if(null!=warnMenthodBuffer){
					String warn_1=warnMenthodBuffer.toString();
					if(warn_1.indexOf("1")!=-1){
						sendMessage(1, asseroomapply, useridList);//发送私信，立即通知
						send(4, asseroomapply.getAsselinkmanuserid(), asseroomapply);//联系人发私信
					}
					if(warn_1.indexOf("2")!=-1){
						smsMessage(1, asseroomapply, useridList);//发送短信
						sms(4,  asseroomapply.getAsselinkmanuserid(), asseroomapply);
					}
					if(warn_1.indexOf("3")!=-1){
						sendEmail(1, asseroomapply, useridList);//发送邮件
						sendMeeting(4,  asseroomapply.getAsselinkmanuserid(), asseroomapply);
					}
				}
			}else{
				if(Integer.parseInt(asseroomapply.getAttched())>=1){
					if(null!=warnMenthodBuffer){
						String warn_1=warnMenthodBuffer.toString();
						if(warn_1.indexOf("1")!=-1){
							sendMessage(1, asseroomapply, useridList);//发送私信，立即通知
							send(4, asseroomapply.getAsselinkmanuserid(), asseroomapply);//联系人发私信
						}
						if(warn_1.indexOf("2")!=-1){
							smsMessage(1, asseroomapply, useridList);//发送短信
							sms(4,  asseroomapply.getAsselinkmanuserid(), asseroomapply);
						}
						if(warn_1.indexOf("3")!=-1){
							sendEmail(1, asseroomapply, useridList);//发送邮件
							sendMeeting(4,  asseroomapply.getAsselinkmanuserid(), asseroomapply);
						}
					}
				}
			}
		}
     	ActionUtil.writer(_cataStatus + "");
	}
	public String getJsonFile() {
		return jsonFile;
	}
	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}
	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	/***
	 * 添加附件信息到数据库
	 * 
	 * @param _attflag
	 * @param _sAttFile
	 * @param TypeId
	 * @param document
	 * @param _sAttDesc
	 * @param _sAttLinkAlt
	 * @param _sEditversions
	 * @param isClient
	 * @param _touChannelid
	 * @param addUserFile
	 * @return
	 */
	private ArrayList<Long> addAttQuestionFile(Integer _attflag,
			String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
			String _sAttLinkAlt, String _sEditversions, boolean isClient,
			Long _touChannelid, Boolean addUserFile) {
		ArrayList<Long> _attachedids = new ArrayList<Long>();
		String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
		File newFile = new File(filePath);
		String newFileName = "";
		if (newFile.exists()) {
			if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
					TypeId.toString())) { // 这是创建文档之后的附件名字
				newFileName = SysFileUtil.saveFileDoc(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
			} else {
				newFileName = SysFileUtil.saveFileDoc(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
			}
			// 删除临时表中的文件
			String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

			Long _attachedid = irpAttachedService.addFile(_docid, 0L,
					newFileName, _sAttLinkAlt, _sAttDesc,
					IrpAttached.ASSEROOMAPPLY, newFilePath,
					Integer.parseInt(_sEditversions), TypeId, _attflag);
			_attachedids.add(_attachedid);
		}
		return _attachedids;

	}
	
	
	
	/**
	 * 根据申请删除
	 * @throws Exception
	 */
	public void deleteApply() throws Exception{
		ActionUtil.writer(deleteApplyByid(asseroomapplyid) + "");
	}
	/**根据申请单号删除,并发送通知
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public int deleteApplyByid(long id) throws Exception{
		IrpAsseroomapplyExample example=new IrpAsseroomapplyExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomapplyidEqualTo(id);
		IrpAsseroomapply asseapply=asseroomapplyService.selectByPrimaryKey(id);
		int _cataStatus=asseroomapplyService.deleteByExample(example);
		IrpAsseuserExample example1=new IrpAsseuserExample();
		com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria1=example1.createCriteria();
		criteria1.andAsseroomapplyidEqualTo(id);
		List<IrpAsseuser> userList=asseuserService.selectByApplyid(example1);
		asseuserService.deleteByApplyid(example1);
		List<Long> useridList=new ArrayList<Long>();
		if(null!=userList&&!userList.isEmpty()){
			for (IrpAsseuser as : userList) {
				useridList.add(as.getUserid());
			}
		}
		String warnMenthodBuffer=asseapply.getWarnid().toString();
		if(null!=asseapply.getWarntime()){
			String warnString=DateUtils.getDateByFormat(asseapply.getWarntime(), DateUtils.YMDHMS_FORMAT);
			Date warnDate=DateUtils.getDateByYMDHMS(warnString);
			if(warnDate.compareTo(new Date())<0){
				if(null!=warnMenthodBuffer){
					String warn_1=warnMenthodBuffer.toString();
					if(warn_1.indexOf("1")!=-1){
						sendMessage(2, asseapply, useridList);//发送删除通知
						send(5, asseapply.getAsselinkmanuserid(), asseapply);
					}
					if(warn_1.indexOf("2")!=-1){
						smsMessage(2, asseapply, useridList);//发送短信
						sms(5,asseapply.getAsselinkmanuserid(), asseapply);
					}
					if(warn_1.indexOf("3")!=-1){
						sendEmail(2, asseapply, useridList);//发送邮件
						sendMeeting(5,asseapply.getAsselinkmanuserid(), asseapply);
					}
				}
				
			}else{
					if(Integer.parseInt(asseapply.getAttched())>=1){
						if(null!=warnMenthodBuffer){
							String warn_1=warnMenthodBuffer.toString();
							if(warn_1.indexOf("1")!=-1){
								sendMessage(2, asseapply, useridList);//发送删除通知
								send(5, asseapply.getAsselinkmanuserid(), asseapply);
							}
							if(warn_1.indexOf("2")!=-1){
								smsMessage(2, asseapply, useridList);//发送删除通知
								sms(5, asseapply.getAsselinkmanuserid(), asseapply);
							}
							if(warn_1.indexOf("3")!=-1){
								sendEmail(2, asseapply, useridList);//发送邮件
								sendMeeting(5,asseapply.getAsselinkmanuserid(), asseapply);
							}
						}
					}
			}
		}else{
			if(null!=warnMenthodBuffer){
				String warn_1=warnMenthodBuffer.toString();
				if(warn_1.indexOf("1")!=-1){
					sendMessage(2, asseapply, useridList);//发送删除通知
					send(5, asseapply.getAsselinkmanuserid(), asseapply);
				}
				if(warn_1.indexOf("2")!=-1){
					smsMessage(2, asseapply, useridList);//发送删除通知
					sms(5, asseapply.getAsselinkmanuserid(), asseapply);
				}
				if(warn_1.indexOf("3")!=-1){
					sendEmail(2, asseapply, useridList);//发送邮件
					sendMeeting(5,asseapply.getAsselinkmanuserid(), asseapply);
				}
			}
		}
		return _cataStatus;
	}
	/**
	 * 判断是否删除
	 * @throws Exception
	 */
	public void isDelete() throws Exception{
		asseroomapply=getApplyInfoByid();
		ActionUtil.writer(asseroomapply.getFlag()+"");
	}
	/**
	 * 查询某一条预约会议室详情
	 * @throws Exception
	 */
	public String selectByApplyid() throws Exception{
		asseroomapply=getApplyInfoByid();
		//查询所申请的会议室
		asseroom=asseroomService.selectByPrimaryKey(asseroomapply.getAsseroomid());
		//查询会议类型
		assetype =assetypeService.selectByPrimaryKey(asseroomapply.getAsseroomapplytypeid());
		//查询会议通知类型
		String warnString=asseroomapply.getWarnid().toString();
		StringBuffer warnBuffer=new StringBuffer();
		if(warnString.indexOf("1")!=-1){
			warnBuffer.append("私信；");
		}
		if(warnString.indexOf("2")!=-1){
			warnBuffer.append("短信；");
		}
		if(warnString.indexOf("3")!=-1){
			warnBuffer.append("邮箱；");
		}
		asseroomapply.setWarnMenthodString(warnBuffer.toString());
		//assewarn=assewarnService.SelectByPrimaryKey(asseroomapply.getWarnid());
		//查询参会人员
		IrpAsseuserExample example=new IrpAsseuserExample();
		com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria=example.createCriteria();
		criteria.andAsseroomapplyidEqualTo(asseroomapply.getAsseroomapplyid());
		List<IrpAsseuser> userList=asseuserService.selectByuserid(example);
		if(null!=userList){
			StringBuffer sbBuffer=new StringBuffer();
			for (IrpAsseuser irpAsseuser : userList) {
				sbBuffer.append(irpUserService.findUserByUserId(irpAsseuser.getUserid()).getName()+",");
			}
			asseroomapply.setFromidAll(sbBuffer.toString());//参会人员姓名
		}
		//查询附件
		attacheds = this.irpAttachedService.getAttachedByAttDocId(asseroomapply.getAsseroomapplyid(),
				IrpAttached.ASSEROOMAPPLY);
		//查询会议室设备
		String sbsString=asseroomapply.getAsseroomsbids();
		if(null!=sbsString&&!"".equals(sbsString)){
			String[] sbs=sbsString.split(";");
			StringBuffer sBuffer=new StringBuffer();
			for (int i = 0; i < sbs.length; i++) {
				if(null==sbs[i]||"nul".equals(sbs[i])||"null".equals(sbs[i])||"nu".equals(sbs[i])||"n".equals(sbs[i])||"".equals(sbs[i])){
					break;
				}
				long sbid=Long.parseLong(sbs[i].trim());
				List<IrpAsseroomsblink> linkList=asseroomsblinkService.findbyAsseroomid(asseroomapply.getAsseroomid());
				if(null!=linkList&&linkList.size()>0){
					for (IrpAsseroomsblink irpAsseroomsblink : linkList) {
						if(irpAsseroomsblink.getAsseroomsbid()==sbid){
							sBuffer.append((asseroomsbService.selectByPrimaryKey(sbid).getAsseroomsbname())+";");
						}
					}
				}else{
					break;
				}
			}
			asseroomapply.setAsseroomapplysbnames(sBuffer.toString());
		}
		return "success";
	}
	
	private IrpUser irpUser;
	public IrpUser getIrpUser() {
		return irpUser;
	}
	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}
	/**
	 * 我的申请
	 */
	public void myApply()throws Exception{
		IrpUser loginUser = LoginUtil.getLoginUser();
		Long userid=loginUser.getId();
		IrpAsseroomapplyExample example1=new IrpAsseroomapplyExample();
		Criteria criteria1=example1.createCriteria();
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "asseroomapplyid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		//按会议室查询
		if(searchWord!=null&&!"".equals(searchWord)){
			if(!searchWord.equals("all")){
				Long asserromid=Long.parseLong(searchWord);
				criteria1.andAsseroomidEqualTo(asserromid);
			}
		}
		criteria1.andAsseconveneruseridEqualTo(userid);
		//按时间查询,开始时间
		if(startTime!=null&&!"".equals(startTime))
			criteria1.andStarttimeBetween(startTime, endTime);
		example1.setOrderByClause(_oOrderby);
		List<IrpAsseroomapply> apply_list_1=asseroomapplyService.querySbForPage(example1);
		
		IrpAsseroomapplyExample example2=new IrpAsseroomapplyExample();
		Criteria criteria2=example2.createCriteria();
		//按会议室查询
		if(searchWord!=null&&!"".equals(searchWord)){
			if(!searchWord.equals("all")){
				Long asserromid=Long.parseLong(searchWord);
				criteria2.andAsseroomidEqualTo(asserromid);
			}
		}
		criteria2.andCruseridEqualTo(userid);
		//按时间查询,开始时间
		if(startTime!=null&&!"".equals(startTime))
			criteria2.andStarttimeBetween(startTime, endTime);
		example2.setOrderByClause(_oOrderby);
		List<IrpAsseroomapply> apply_list_2=asseroomapplyService.querySbForPage(example2);
		if(null!=apply_list_1&&!apply_list_1.isEmpty()){
			if(null!=apply_list_2&&apply_list_2.size()>0){
				asseroomapplyList=apply_list_2;
				for (IrpAsseroomapply assroom_1 : apply_list_1) {
					int count=0;
					for (IrpAsseroomapply assroom_2 : apply_list_2) {
						if((assroom_1.getAsseroomapplyid()+"").equals(assroom_2.getAsseroomapplyid()+"")){
							count++;
						}
					}
					if(count==0){
						asseroomapplyList.add(assroom_1);
					}
				}
			}else{
				asseroomapplyList=apply_list_1;
			}
		}else{
			if(null!=apply_list_2&&!apply_list_2.isEmpty()){
				asseroomapplyList=apply_list_2;
			}
		}
		ActionUtil.writer(getJsonFrom(asseroomapplyList)+"");
	}
	
	/**
	 * 显示我的日程
	 * @throws Exception
	 */
	public void selectApplyByuserid() throws Exception{
		IrpUser loginUser = LoginUtil.getLoginUser();
		Long userid=loginUser.getId();
		IrpAsseuserExample example=new IrpAsseuserExample();
		com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<IrpAsseuser>  userList=asseuserService.selectByuserid(example);
		if(null!=userList&&!userList.isEmpty()){
			List<Long> applyidList=new ArrayList<Long>();
			if(userList.size()>0){
				for (IrpAsseuser irpAsseuser : userList) {
					applyidList.add(irpAsseuser.getAsseroomapplyid());
				}
			}
			IrpAsseroomapplyExample example1=new IrpAsseroomapplyExample();
			Criteria criteria1=example1.createCriteria();
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "asseroomapplyid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			if(null!=applyidList&&!applyidList.isEmpty()){
				criteria1.andAsseroomapplyidIn(applyidList);
			}
			//按会议室查询
			if(searchWord!=null&&!"".equals(searchWord)){
				if(!searchWord.equals("all")){
					Long asserromid=Long.parseLong(searchWord);
					criteria1.andAsseroomidEqualTo(asserromid);
				}
			}
			//按时间查询,开始时间
			if(startTime!=null&&!"".equals(startTime))
				criteria1.andStarttimeBetween(startTime, endTime);
			example1.setOrderByClause(_oOrderby);
			asseroomapplyList=asseroomapplyService.querySbForPage(example1);
			ActionUtil.writer(getJsonFrom(asseroomapplyList)+"");
		}
	}
	/**
	 * 查看会议室在某一段时间是否被占用
	 * @param id
	 * @throws Exception
	 */
	public void checkRoom() throws Exception{
		IrpAsseroomapplyExample example = new IrpAsseroomapplyExample();
		Criteria criteria = example.createCriteria();
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "asseroomapplyid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		// 按时间查询,开始时间
		startTime = DateUtils.getDateByYMDHM(start);
		endTime = DateUtils.getDateByYMDHM(end);
		criteria.andAsseroomidEqualTo(Long.parseLong(searchWord));
		example.setOrderByClause(_oOrderby);
		asseroomapplyList = asseroomapplyService.querySbForPage(example);
		String  _cataStatus = "0";
		if (null != asseroomapplyList && asseroomapplyList.size() > 0){

			for (IrpAsseroomapply apply : asseroomapplyList) {
				String startString =DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT);
				String endString=DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT);
				Date startDate=DateUtils.getDateByYMDHMS(startString);
				Date endDate=DateUtils.getDateByYMDHMS(endString);
				if(startDate.compareTo(startTime)>=0&&startDate.compareTo(endTime)<=0){
					_cataStatus = "该会议室已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段内被申请";
					break;
				}
				if(endDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
					_cataStatus = "该会议室已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段内被申请";
					break;
				}
				if(startDate.compareTo(startTime)<=0&&endDate.compareTo(endTime)>=0){
					_cataStatus = "该会议室已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段内被申请";
					break;
				}
				if(startDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
					_cataStatus = "该会议室已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段内被申请";
					break;
				}
			}
		}
			
		ActionUtil.writer(_cataStatus + "");
	}
	
	/**
	 * 选择请假的审核人
	 * @return
	 */
	public String findUser(){
		if(groupId==null){
			groupId= IrpGroup.GROUP_ROOTID_PUBLIC;
		}
		userids=userids;
		int nDataCount = irpGroupService.findUsersCountByGroupId(groupId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpGroupService.findUsersOfPageByGroupId(pageUtil, groupId, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("pagecheck(#PageNum#)");
		return SUCCESS;
	}
	
	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}
	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}
	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}
	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getConveneruserid() {
		return conveneruserid;
	}
	public void setConveneruserid(Long conveneruserid) {
		this.conveneruserid = conveneruserid;
	}
	/**
	 * 查看人员在某一段时间是否有空
	 * @param id
	 * @throws Exception
	 */
	public void  checkUser() throws Exception{
		String  _cataStatus = "0";
		String fromidAlls =  fromidAll.replace(","," ").replace(";"," ").replace(":"," ").replace("，"," ").replace("；"," ").replace("："," ");
        String[] fromidArray = fromidAlls.split(" ");
		for (int i = 0; i < fromidArray.length; i++) {
			List<IrpUser> irpUser1 = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(fromidArray[i]));
			if (irpUser1.size() >= 1) {
				long userid = irpUser1.get(0).getUserid();
				IrpAsseuserExample example=new IrpAsseuserExample();
				com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria=example.createCriteria();
				criteria.andUseridEqualTo(userid);
				List<IrpAsseuser>  userList=asseuserService.selectByuserid(example);
				List<Long> applyidList=new ArrayList<Long>();
				if(null!=userList&&userList.size()>0){
					for (IrpAsseuser irpAsseuser : userList) {
						applyidList.add(irpAsseuser.getAsseroomapplyid());
					}
				}else{
					break;
				}
				IrpAsseroomapplyExample example1=new IrpAsseroomapplyExample();
				Criteria criteria1=example1.createCriteria();
				IrpAsseroomapplyExample example2=new IrpAsseroomapplyExample();
				Criteria criteria2=example2.createCriteria();
				String _oOrderby = "";
				if (this.orderField == null || this.orderField.equals("")) {
					_oOrderby = "asseroomapplyid desc";
				} else {
					_oOrderby = this.orderField + " " + this.orderBy;
				}
				if(null!=applyidList&&!applyidList.isEmpty()){
					//按时间查询,开始时间
					startTime = DateUtils.getDateByYMDHM(start);
					endTime = DateUtils.getDateByYMDHM(end);
					criteria1.andAsseroomapplyidIn(applyidList);
					example1.setOrderByClause(_oOrderby);
					asseroomapplyList=asseroomapplyService.querySbForPage(example1);
					if (null != asseroomapplyList && asseroomapplyList.size() > 0){
						for (IrpAsseroomapply apply : asseroomapplyList) {
							String startString =DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT);
							String endString=DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT);
							Date startDate=DateUtils.getDateByYMDHMS(startString);
							Date endDate=DateUtils.getDateByYMDHMS(endString);
							if(startDate.compareTo(startTime)>=0&&startDate.compareTo(endTime)<=0){
								_cataStatus = fromidArray[i]+"已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段参加"+apply.getAssename();
								break;
							}
							if(endDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
								_cataStatus = fromidArray[i]+"已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段参加"+apply.getAssename();
								break;
							}
							if(startDate.compareTo(startTime)<=0&&endDate.compareTo(endTime)>=0){
								_cataStatus = fromidArray[i]+"已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段参加"+apply.getAssename();
								break;
							}
							if(startDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
								_cataStatus = fromidArray[i]+"已经在"+DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"~"+DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT)+"时间段参加"+apply.getAssename();
								break;
							}
						}
					}
				}
			}else{
				break;
			}
		}
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 参会人员表
	 * @param id
	 * @param applyid
	 * @throws Exception
	 */
	public void insertAsseUser(List<Long> userids,Long applyid) throws Exception{
		if(userids.size()>0){
			for (Long id : userids) {
				IrpAsseuser asseuser=new IrpAsseuser();
				asseuser.setAsseruserstatus(1);
				asseuser.setAsseuserid(TableIdUtil.getNextId(IrpAsseuser.TABLE_NAME));
				asseuser.setCrtime(new Date());
				IrpUser loginUser = LoginUtil.getLoginUser();
				asseuser.setCruserid(loginUser.getId());
				asseuser.setAsseroomapplyid(applyid);
				asseuser.setUserid(id);
				asseuserService.add(asseuser);
			}
		}
		
	}
	/**
	 * 发邮件
	 * @param flag
	 * @param _asseroomapply
	 * @param _useridList
	 */
	public void sendEmail(int flag,IrpAsseroomapply _asseroomapply,List<Long> _useridList){
		if(_useridList.size()>0){
			for (Long long1 : _useridList) {
				sendMeeting(flag,long1,_asseroomapply);
			}
		}
	}
	/**
	 * 发邮件的方法；
	 * @param flag
	 * @param id
	 * @param _asseroomapply
	 */
	public void sendMeeting (int flag,Long id,IrpAsseroomapply _asseroomapply){
		String content="";
		String demoString=_asseroomapply.getAsseroomapplydemo()==null? "无":_asseroomapply.getAsseroomapplydemo();
		IrpUser user=irpUserService.findUserByUserId(id);
		String name=LoginUtil.getUserNameString(LoginUtil.findUserById(id));
		IrpAsseroom asseroom=null;
		try {
			asseroom=asseroomService.selectByPrimaryKey(_asseroomapply.getAsseroomid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(null!=user.getEmail()&&!"".equals(user.getEmail())){
			if(flag==1){
				//String message="我对于将在"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的【"+_asseroomapply.getAssename()+"】会议的建议是：";
				content="会议消息通知:"+name+"，您好。“"+_asseroomapply.getAssename()+"”，将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";会议备注："+demoString;
			}
			if(flag==2)//会议取消
				content="会议消息通知:"+name+"，您好。您将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。";
			if(flag==4){//通知联系人参加会议
				content="会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";参会人员：【"+fromidAll+"】"+";会议备注："+demoString;
			}
			if(flag==5){//通知联系人取消会议
				content="会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议本将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+"。但是,由于某些原因，会议将被取消";
			}
			if(!"".equals(content)){
				EmailUtils.sendMeetingEmail(user, content,"会议通知");
			}
		}
		if(flag==3){//会议取消,通知联系人
			user=irpUserService.findUserByUserId(_asseroomapply.getAsselinkmanuserid());
			if(null!=user.getEmail()&&!"".equals(user.getEmail())){
				String name_1=LoginUtil.getUserNameString(LoginUtil.findUserById(_asseroomapply.getAsselinkmanuserid()));
				content="会议消息通知:"+name_1+"，您好。"+name+"将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。";
				EmailUtils.sendMeetingEmail(user, content,"会议通知");
			}
		}	
	}
	
	/**
	 * 发短信
	 * @param flag
	 * @param _asseroomapply
	 * @param _useridList
	 */
	public void smsMessage(int flag,IrpAsseroomapply _asseroomapply,List<Long> _useridList){
		if(_useridList.size()>0){
			for (Long long1 : _useridList) {
				sms(flag,long1,_asseroomapply);
				
			}
		}
	}
	
	public void sms(int flag,Long id,IrpAsseroomapply _asseroomapply){
		String content="";
		String demoString=_asseroomapply.getAsseroomapplydemo()==null? "无":_asseroomapply.getAsseroomapplydemo();
		IrpUser user=irpUserService.findUserByUserId(id);
		String name=LoginUtil.getUserNameString(LoginUtil.findUserById(id));
		IrpAsseroom asseroom=null;
		try {
			asseroom=asseroomService.selectByPrimaryKey(_asseroomapply.getAsseroomid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(null!=user.getMobile()&&!"".equals(user.getMobile())){
			if(flag==1){
				//String message="我对于将在"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的【"+_asseroomapply.getAssename()+"】会议的建议是：";
				content="会议消息通知:"+name+"，您好。“"+_asseroomapply.getAssename()+"”，将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";会议备注："+demoString;
			}
			if(flag==2)//会议取消
				content="会议消息通知:"+name+"，您好。您将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。";
			if(flag==4){//通知联系人参加会议
				content="会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";参会人员：【"+fromidAll+"】"+";会议备注："+demoString;
			}
			
			if(flag==5){//通知联系人取消会议
				content="会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议本将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+"。但是,由于某些原因，会议将被取消";
			}
			if(!"".equals(content)){
				SmsUtil.sendMsg_webchinese(content, user.getMobile());
			}
			
		}
		if(flag==3){//会议取消,通知联系人
			user=irpUserService.findUserByUserId(_asseroomapply.getAsselinkmanuserid());
			if(null!=user.getMobile()&&!"".equals(user.getMobile())){
				String name_1=LoginUtil.getUserNameString(LoginUtil.findUserById(_asseroomapply.getAsselinkmanuserid()));
				content="会议消息通知:"+name_1+"，您好。"+name+"将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。";
				SmsUtil.sendMsg_webchinese(content, user.getMobile());
			}
		}
	}
	
	/**
	 * 发送私信
	 * @param flag
	 * @param _asseroomapply
	 * @param _useridList
	 */
	public void sendMessage(int flag,IrpAsseroomapply _asseroomapply,List<Long> _useridList){
		if(_useridList.size()>0){
			for (Long long1 : _useridList) {
				send(flag,long1,_asseroomapply);
				
			}
		}
	}
	
	/**
	 * 发送私信方法
	 * @param flag
	 * @param id
	 * @param _asseroomapply
	 */
	public void send(int flag,Long id,IrpAsseroomapply _asseroomapply){
		IrpUser loginUser = LoginUtil.getLoginUser();
		String demoString=_asseroomapply.getAsseroomapplydemo()==null? "无":_asseroomapply.getAsseroomapplydemo();
		if(!id.toString().equals(loginUser.getId().toString())){
			
			IrpMessageContent irpMessageContent=new IrpMessageContent();
			irpMessageContent.setFromUid(id);
			String name=LoginUtil.getUserNameString(LoginUtil.findUserById(id));
			IrpAsseroom asseroom=null;
			try {
				asseroom=asseroomService.selectByPrimaryKey(_asseroomapply.getAsseroomid());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			StringBuffer content=new StringBuffer();
			if(flag==1){
				String message="我对于将在"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的【"+_asseroomapply.getAssename()+"】会议的建议是：";
				irpMessageContent.setContent("会议消息通知:"+name+"，您好。“"+_asseroomapply.getAssename()+"”，将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";会议备注："+demoString+";    <a href='javascript:void(0) ' onclick='send("+_asseroomapply.getAsselinkmanuserid()+",\""+message+"\")' >回执</a>");
			}
			if(flag==2)//会议取消
				irpMessageContent.setContent("会议消息通知:"+name+"，您好。您将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。");
			if(flag==3){//会议取消
				Long id_1=_asseroomapply.getAsselinkmanuserid();
				if(!id_1.toString().equals(loginUser.getId().toString())){
					irpMessageContent.setFromUid(id_1);
					String name_1=LoginUtil.getUserNameString(LoginUtil.findUserById(_asseroomapply.getAsselinkmanuserid()));
					irpMessageContent.setContent("会议消息通知:"+name_1+"，您好。"+name+"将不用参加"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的“"+_asseroomapply.getAssename()+"”会议。");
				}
			}
			if(flag==4){//通知联系人参加会议
				irpMessageContent.setContent("会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+";参会人员：【"+fromidAll+"】"+";会议备注："+demoString);
			}
			if(flag==5){//通知联系人取消会议
				irpMessageContent.setContent("会议消息通知:"+name+"，您好。您为“"+_asseroomapply.getAssename()+"”会议的联系人，会议本将于"+DateUtils.getDateByFormat(_asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_asseroomapply.getAsseroomcontent()+"。但是,由于某些原因，会议将被取消");
			}
			irpMessageContentService.addMessageContent(irpMessageContent);
		}
	}
	
	/**
	 * 回执所需要的参数
	 * @return
	 */
	public String toSend(){
		sendusername=LoginUtil.getUserNameString(LoginUtil.findUserById(sendid));
		attachsendcontent=attachsendcontent;
		sendid=sendid;
		return SUCCESS;
		
	}
	/**
	 * 私信回执
	 */
	public void huizhi(){
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpMessageContent irpMessageContent=new IrpMessageContent();
		irpMessageContent.setFromUid(sendid);
		sendusername=LoginUtil.getUserNameString(LoginUtil.findUserById(sendid));
		irpMessageContent.setContent(sendusername+",您好,我是"+loginUser.getUsername()+"。"+attachsendcontent+""+sendcontent);
		irpMessageContentService.addMessageContent(irpMessageContent);
	}
	/**
	 *根据id查询预约会议室信息
	 *未开始的会议只有会议管理员，召集人，创建人，可以删除；
	 * @return
	 * @throws SQLException
	 */
	public IrpAsseroomapply getApplyInfoByid() throws SQLException{
		IrpUser loginUser = LoginUtil.getLoginUser();
		long newUserId=loginUser.getId();
		asseroomapply=asseroomapplyService.selectByPrimaryKey(asseroomapplyid);
		long oldUserId=asseroomapply.getAsseconveneruserid();
		String username=irpUserService.findUserByUserId(oldUserId).getName();
		asseroomapply.setConvenerusername(username);//召集人姓名
		username=irpUserService.findUserByUserId(asseroomapply.getAsselinkmanuserid()).getName();
		asseroomapply.setLinkmanusername(username);//联系人姓名
		//处理时间
		String startString =DateUtils.getDateByFormat(asseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT);
		String endString=DateUtils.getDateByFormat(asseroomapply.getEndtime(), DateUtils.YMDHMS_FORMAT);
		String crtimeString=DateUtils.getDateByFormat(asseroomapply.getCrtime(), DateUtils.YMDHMS_FORMAT);
		Date date=asseroomapply.getWarntime();
		if(null!=date){
			String warnString=DateUtils.getDateByFormat(date, DateUtils.YMDHMS_FORMAT);
			asseroomapply.setWarnCalender(warnString);
		}else{
			asseroomapply.setCrtimes(crtimeString);
		}
		asseroomapply.getCruserid();
		Date startDate=DateUtils.getDateByYMDHMS(startString);
		Date endDate=DateUtils.getDateByYMDHMS(endString);
		asseroomapply.setEnd(endString);
		asseroomapply.setStart(startString);
		if(newUserId==oldUserId||asseroomapply.getCruserid()==newUserId){
			if(startDate.compareTo(new Date())>0&&endDate.compareTo(new Date())>0){
				asseroomapply.setFlag(1);
			}
		}else{
			if(preOrOld==1){
				if(startDate.compareTo(new Date())>0&&endDate.compareTo(new Date())>0){
					asseroomapply.setFlag(1);
				}
			}
		}
		return asseroomapply;
	}
	
	/**
	 * 查询所有通知类型
	 */
	public void findAllAssewarn(){
		IrpAssewarnExample example=new IrpAssewarnExample();
		example.setOrderByClause("warnid asc");
		try {
			assewarnList=assewarnService.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有会议类型
	 */
	public void findAllAssetype(){
		IrpAssetypeExample example=new IrpAssetypeExample();
		example.setOrderByClause("asseroomapplytypeid asc");
		try {
			assetypeList=assetypeService.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有会议室
	 */
	public void findAllAsseroom()
	{	
		IrpAsseroomExample example1=new IrpAsseroomExample();
		com.tfs.irp.asseroom.entity.IrpAsseroomExample.Criteria criteria1=example1.createCriteria();
		criteria1.andAsseroomstatusEqualTo(1);
		example1.setOrderByClause("asseroomid asc");
		try {
			asseroomList=asseroomService.selectByExample(example1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取会议室设备id
	 */
	public void getSbids(){
		StringBuffer sbidStringBuffer=new StringBuffer();
		if(null!=asseroomsbs&&asseroomsbs.length>0){
			for (int i = 0; i < asseroomsbs.length; i++) {
					sbidStringBuffer.append(asseroomsbs[i]+";");
			}
		}
		String []s_1=new String[2];
		s_1[0]=people.toString();
		s_1[1]=sbidStringBuffer.toString();
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s_1);
		ActionUtil .writer(jsonArray.toString());
	}
	/**
	 * 预约会议室设备
	 */
	public String findAsseroomByid(){
		sbidList_1=new ArrayList<Long>();
		if(null!=sbids_1&&!sbids_1.equals("")){
			String[] sbids_2=sbids_1.split(";");
			for (int i = 0; i < sbids_2.length; i++) {
				if(null==sbids_2[i]||"nul".equals(sbids_2[i])||"null".equals(sbids_2[i])||"nu".equals(sbids_2[i])||"n".equals(sbids_2[i])||"".equals(sbids_2[i])){
					break;
				}else{
					sbidList_1.add(Long.parseLong(sbids_2[i]));
					
				}
			}
		}
		findOneAsseroom();
		return SUCCESS;
	}
	/**
	 * 会议室信息，根据id查询
	 */
	public void findOneAsseroom(){
		IrpAsseroomExample example=new IrpAsseroomExample();
		com.tfs.irp.asseroom.entity.IrpAsseroomExample.Criteria criteria=example.createCriteria();
		criteria.andAsseroomidEqualTo(asseroomid);
		criteria.andAsseroomstatusEqualTo(1);
		example.setOrderByClause("asseroomid asc");
		try {
			asseroom=asseroomService.selectByExample(example).get(0);
			people=asseroom.getAsseroompeoples();
			List<IrpAsseroomsblink> asseroomsblinksList=asseroomsblinkService.findbyAsseroomid(asseroom.getAsseroomid());
			if(null!=asseroomsblinksList&&!asseroomsblinksList.isEmpty()){
				List<Long> sbids=new ArrayList<Long>();
				for (IrpAsseroomsblink irpAsseroomsblink : asseroomsblinksList) {
					sbids.add(irpAsseroomsblink.getAsseroomsbid());
				}
				if(sbids.size()>0){
					IrpAsseroomsbExample example2=new IrpAsseroomsbExample();
					com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample.Criteria criteria2=example2.createCriteria();
					criteria2.andAsseroomsbidIn(sbids);
					criteria2.andAsseroomsbstatusEqualTo(1);
					example2.setOrderByClause("asseroomsbid asc");
					asseroomsbList=asseroomsbService.selectByExample(example2);
					StringBuffer sbidStringBuffer=new StringBuffer();
					for (IrpAsseroomsb sb : asseroomsbList) {
						sbidStringBuffer.append(sb.getAsseroomsbid()+";");
						if(null!=sbidList_1&&!sbidList_1.isEmpty()){
							for (Long long1 : sbidList_1) {
								if(long1.toString().equals(sb.getAsseroomsbid().toString())){
									sb.setAsseroomsbstatus(3);
								}
								
							}
						}
					}
					String []s_1=new String[2];
					s_1[0]=people.toString();
					s_1[1]=sbidStringBuffer.toString();
					net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s_1);
					ActionUtil.writer(jsonArray.toString());
				}
			}else{
				String []s_1=new String[2];
				s_1[0]=people.toString();
				net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s_1);
				ActionUtil.writer(jsonArray.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据会议室一键预约
	 * @throws  
	 */
	public void oneApply(){
		start=asseroomapply.getStart();
		end=asseroomapply.getEnd();
		searchWord=asseroomapply.getAsseroomid().toString();
		try {
			deleteAsseroom();//删除占用会议室
			deleteUser();//删除占用人员
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加会议室预约
		addApply();
	}
	/**
	 * 若会议室被占用，删除占用，并通知
	 * @throws Exception 
	 */
	public void deleteAsseroom() throws Exception{
		IrpAsseroomapplyExample example = new IrpAsseroomapplyExample();
		Criteria criteria = example.createCriteria();
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "asseroomapplyid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		// 按时间查询,开始时间
		startTime = DateUtils.getDateByYMDHM(start);
		endTime = DateUtils.getDateByYMDHM(end);
		criteria.andAsseroomidEqualTo(Long.parseLong(searchWord));
		example.setOrderByClause(_oOrderby);
		asseroomapplyList = asseroomapplyService.querySbForPage(example);
		String  _cataStatus = "0";
		if (null != asseroomapplyList && asseroomapplyList.size() > 0){
			for (IrpAsseroomapply apply : asseroomapplyList) {
				String startString =DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT);
				String endString=DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT);
				Date startDate=DateUtils.getDateByYMDHMS(startString);
				Date endDate=DateUtils.getDateByYMDHMS(endString);
				long id=apply.getAsseroomapplyid();
				if(startDate.compareTo(startTime)>=0&&startDate.compareTo(endTime)<=0){
					deleteApplyByid(id);
					continue;
				}
				if(endDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
					deleteApplyByid(id);
					continue;
				}
				if(startDate.compareTo(startTime)<=0&&endDate.compareTo(endTime)>=0){
					deleteApplyByid(id);
					continue;
				}
				if(startDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
					deleteApplyByid(id);
					continue;
				}
			}
		}
	}
	/**
	 * 若人员被占用，删除人员占用并通知
	 * @throws Exception 
	 */
	public void deleteUser() throws Exception{
		String  _cataStatus = "0";
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "asseroomapplyid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		String fromidAlls =  fromidAll.replace(","," ").replace(";"," ").replace(":"," ").replace("，"," ").replace("；"," ").replace("："," ");
        String[] fromidArray = fromidAlls.split(" ");
		for (int i = 0; i < fromidArray.length; i++) {
			List<IrpUser> irpUser1 = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(fromidArray[i]));
			if (irpUser1.size() >= 1) {
				long userid = irpUser1.get(0).getUserid();
				IrpAsseuserExample example=new IrpAsseuserExample();
				com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria=example.createCriteria();
				criteria.andUseridEqualTo(userid);
				List<IrpAsseuser>  userList=asseuserService.selectByuserid(example);
				List<Long> applyidList=new ArrayList<Long>();
				if(null!=userList&&userList.size()>0){
					for (IrpAsseuser irpAsseuser : userList) {
						applyidList.add(irpAsseuser.getAsseroomapplyid());
					}
					if(null!=applyidList&&!applyidList.isEmpty()){
						IrpAsseroomapplyExample example1=new IrpAsseroomapplyExample();
						Criteria criteria1=example1.createCriteria();
						//按时间查询,开始时间
						startTime = DateUtils.getDateByYMDHM(start);
						endTime = DateUtils.getDateByYMDHM(end);
						criteria1.andAsseroomapplyidIn(applyidList);
						example1.setOrderByClause(_oOrderby);
						asseroomapplyList=asseroomapplyService.querySbForPage(example1);
						if (null != asseroomapplyList && asseroomapplyList.size() > 0){
							for (IrpAsseroomapply apply : asseroomapplyList) {
								String startString =DateUtils.getDateByFormat(apply.getStarttime(), DateUtils.YMDHMS_FORMAT);
								String endString=DateUtils.getDateByFormat(apply.getEndtime(), DateUtils.YMDHMS_FORMAT);
								Date startDate=DateUtils.getDateByYMDHMS(startString);
								Date endDate=DateUtils.getDateByYMDHMS(endString);
								if(startDate.compareTo(startTime)>=0&&startDate.compareTo(endTime)<=0){
									userwarn(userid,apply);
									continue;
								}
								if(endDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
									userwarn(userid,apply);
									continue;
								}
								if(startDate.compareTo(startTime)<=0&&endDate.compareTo(endTime)>=0){
									userwarn(userid,apply);
									continue;
								}
								if(startDate.compareTo(startTime)>=0&&endDate.compareTo(endTime)<=0){
									userwarn(userid,apply);
									continue;
								}
							}
						}
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
	}
	
	public void userwarn(long userid,IrpAsseroomapply apply) throws Exception{
		String warnMenthodBuffer=apply.getWarnid().toString();
		deleteuser(userid,apply.getAsseroomapplyid());
		if(null!=apply.getWarntime()){
			String warnString=DateUtils.getDateByFormat(apply.getWarntime(), DateUtils.YMDHMS_FORMAT);
			Date warnDate=DateUtils.getDateByYMDHMS(warnString);
			if(warnDate.compareTo(new Date())<0){
				if(null!=warnMenthodBuffer){
					String warn_1=warnMenthodBuffer.toString();
					if(warn_1.indexOf("1")!=-1){
						send(2, userid, apply);
						send(3, userid, apply);//通知联系人
					}
					if(warn_1.indexOf("2")!=-1){
						//发送邮件
						sms(2, userid, apply);
						sms(3, userid, apply);
					}
					if(warn_1.indexOf("3")!=-1){
						//发送邮件
						sendMeeting(2, userid, apply);
						sendMeeting(3, userid, apply);
					}
				}
				
			}else{
					if(Integer.parseInt(apply.getAttched())>=1){
						if(null!=warnMenthodBuffer){
							String warn_1=warnMenthodBuffer.toString();
							if(warn_1.indexOf("1")!=-1){
								send(2, userid, apply);
								send(3, userid, apply);//通知联系人
							}
							if(warn_1.indexOf("2")!=-1){
								//发送邮件
								sms(2, userid, apply);
								sms(3, userid, apply);
							}
							if(warn_1.indexOf("3")!=-1){
								//发送邮件
								sendMeeting(2, userid, apply);
								sendMeeting(3, userid, apply);
							}
						}
					}
			}
		}else{
			if(null!=warnMenthodBuffer){
				String warn_1=warnMenthodBuffer.toString();
				if(warn_1.indexOf("1")!=-1){
					send(2, userid, apply);
					send(3, userid, apply);//通知联系人
				}
				if(warn_1.indexOf("2")!=-1){
					//发送邮件
					sms(2, userid, apply);
					sms(3, userid, apply);
				}
				if(warn_1.indexOf("3")!=-1){
					//发送邮件
					sendMeeting(2, userid, apply);
					sendMeeting(3, userid, apply);
				}
			}
		}
	}
	/**
	 * 删除人员会议
	 * @throws Exception 
	 */
	public void deleteuser(long userid,long asseroomapplyid) throws Exception{
		IrpAsseuserExample example=new IrpAsseuserExample();
		com.tfs.irp.asseuser.entity.IrpAsseuserExample.Criteria criteria=example.createCriteria();
		criteria.andAsseroomapplyidEqualTo(asseroomapplyid);
		criteria.andUseridEqualTo(userid);
		asseuserService.deleteByApplyid(example);
	}
	//获取一段时间内会议室的使用统计
	public String roomDate() throws Exception{
		if(this.timeLimit!=null){
			IrpAsseroomapplyExample example=new IrpAsseroomapplyExample();
			Criteria criteria=example.createCriteria();
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "asseroomid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			//按会议室查询
			if(searchWord!=null&&!"".equals(searchWord)){
				if(!searchWord.equals("all")){
					Long asserromid=Long.parseLong(searchWord);
					criteria.andAsseroomidEqualTo(asserromid);
				}
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
			}else{
				startTime=DateUtils.getDateByYMD(start);
				endTime=DateUtils.getDateByYMD(end);
			}  //否则开始结束时间都是有值的，即任意时间
			
			criteria.andStarttimeBetween(startTime, endTime);
			example.setOrderByClause("starttime asc");
			asseroomapplyList=asseroomapplyService.querySbForPage(example);
			StringBuffer roomdate=new StringBuffer();
			StringBuffer roomcount=new StringBuffer();
			//获取横坐标可以不需要横坐标，横坐标自动每走一步
			int amount=setTimeBar(this.timeLimit);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startTime);//设置初始时间
			if(asseroomapplyList!=null){  
				for (int i = 0; i <=amount; i++) {
					int count=0;
					for (int j = 0; j <asseroomapplyList.size(); j++) {
						Date docPubTime=asseroomapplyList.get(j).getStarttime();
						String doc=dateFormat.format(docPubTime.getTime());
						String time=dateFormat.format(calendar.getTime());
						if(doc.equals(time)){//如果相同就加1
							count++;
						}
					}
					roomcount.append(count+",");
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
			roomcountjson=roomcount.toString().substring(0,roomcount.toString().length()-1);
			findAllAsseroom();//所有会议室
			//roomdatejson=roomdate.toString();
		}
		return SUCCESS;
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
	public String getXaxisJasonString() {
		return xaxisJasonString;
	}
	public void setXaxisJasonString(String xaxisJasonString) {
		this.xaxisJasonString = xaxisJasonString;
	}
 
}
