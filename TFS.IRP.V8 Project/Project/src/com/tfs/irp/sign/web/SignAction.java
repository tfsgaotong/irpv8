package com.tfs.irp.sign.web;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.swing.plaf.TableUI;

import org.apache.log4j.Logger;
import org.apache.solr.common.util.DateUtil;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.compass.core.converter.extended.DataTimeConverter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.app.entity.IrpApp;
import com.tfs.irp.app.service.IrpAppService;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.sign.entity.IrpSignInfoExample;
import com.tfs.irp.sign.entity.IrpSignInfoExample.Criteria;
import com.tfs.irp.sign.service.SignService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SignUtil;
import com.tfs.irp.util.TableIdUtil;

public class SignAction extends ActionSupport {
	private String fileName;//导出的zip文件名
	private String queryString;
	private Long userId;// 用户id
	private int pageNum = 1;// 当前页
	private int pageSize = 10;// 每页数量
	private Date endTime;// 结束时间
	private IrpUser user;// 用户
	private Date startTime;// 起始时间
	private String searchName;//搜索名字
	private String pageHTML;// 页面
	private String latejson;// 迟到
	private String userName;// 用户名
	private String earlyjson;// 早退
	private String beginTime;
	private String finishTime;
	private String timeLimit;// 时间限制
	private String normaljson;// 正常
	private String usernamejson;// 名称
	private String signAgainjson;// 补签
	private IrpSignInfo irpSignInfo;// 签到用户对象
	private SignService signService;// 签到逻辑
	private String amountJsonString;
	private String xaxisJasonString;// 横坐标
	private String norJsonString;
	private String unorJsonString;
	private static final Logger logger = Logger.getLogger(SignAction.class);
	private List<IrpSignInfo> signList;// 签到用户集合
	private IrpUserService userService;// 用户逻辑
	private Map<Long, String> nameMap = new HashMap<Long, String>();
	private Map<Long, String> notSignOutRecord = new HashMap<Long, String>();

	/**
	 * 初始化未签退用户
	 * 
	 * @param list
	 */
	public void notSignOutRecord(List<IrpSignInfo> list) {
		if (list != null && list.size() != 0) {
			for (IrpSignInfo ele : list) {
				if (ele.getSignid() == LoginUtil.getLoginUserId()
						&& SignUtil.common(ele.getSigninstatus())
						&& ele.getSignintime().compareTo(
								SignUtil.getYesterday()) < 0
						&& !SignUtil.common(ele.getSignoutstatus())) {
					notSignOutRecord.put(ele.getSigninfoid(), "1");
				}
			}
		}
	}
	
	/**
	 * 测试path方法
	 */
	public void getPath(){
		 ActionContext ac = ActionContext.getContext();     
	        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
	       String WEB_ROOT_PATH = sc.getRealPath("/");  
	}
		
	
	/**
	 * 判断
	 * @param str
	 * @return
	 */
	public boolean common(String str){
		if(str!=null && !"".equals(str)){
			return true;
		}
		return false;
	}

	/**
	 * 初始化签到用户
	 * 
	 * @param signService
	 */
	public String signInit() {
		String result = "success";
		String type = ServletActionContext.getRequest().getParameter(
				"queryType");
		/**
		 * 查询出userid集合
		 */
		List<Long> userIds = null;
		if(common(searchName)){
			try {
//				this.searchName = new String(this.searchName.getBytes("iso-8859-1"),"UTF-8");
				if(!"用户姓名".equals(searchName)){
					IrpUserExample userExample = new IrpUserExample();
					com.tfs.irp.user.entity.IrpUserExample.Criteria criteria1 = userExample.createCriteria();
					com.tfs.irp.user.entity.IrpUserExample.Criteria criteria2 = userExample.createCriteria();
					criteria1.andTruenameLike("%"+this.searchName.trim()+"%");
					criteria2.andNicknameLike("%"+this.searchName.trim()+"%");
					userExample.or(criteria1);
					userExample.or(criteria2);
					List<IrpUser> userList = userService.findUserByExample(userExample);
					if(userList!=null && userList.size()>0){
						userIds = new ArrayList<Long>();
						for(IrpUser ele : userList){
							userIds.add(ele.getUserid());
						}
					}else{
						signList = null;
						ifSign();
						return "page";
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		if(userIds!=null){
			criteria.andSignidIn(userIds);
		}
		
		if(common(beginTime) && common(finishTime)){
			String finishTime1=finishTime+" 23:59:59";
			criteria.andSignintimeBetween(DateUtils.getDateByStrToYMD(beginTime), DateUtils.getDateByYMDHMS(finishTime1));
		}else if(common(beginTime)){
			criteria.andSignintimeGreaterThanOrEqualTo(DateUtils.getDateByStrToYMD(beginTime));
		}else if(common(finishTime)){
			criteria.andSignintimeLessThanOrEqualTo(DateUtils.getDateByStrToYMD(finishTime));
		}
		if ("1".equals(type)) {
			result = "page";
		}
		example.setOrderByClause("signinfoid desc");
		int datacount = signService.getDataCount(userIds,beginTime,finishTime);
		PageUtil page = new PageUtil(pageNum, pageSize, datacount);
		signList = signService.signInfo(page,example);
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		// 初始化未签退记录
		notSignOutRecord(signList);
		// 转换id为truename
		if (signList != null && signList.size() != 0) {
			for (IrpSignInfo ele : signList) {
				for (IrpUser element : userList) {
					if (ele.getSignid() == Integer.parseInt(element.getUserid()
							+ "")) {
						nameMap.put(ele.getSignid(), element.getTruename());
						break;
					}
				}
			}
		}else{
			signList = null;
		}

		ifSign();
		this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
		return result;
	}

	/**
	 * 签到对话框
	 * 
	 * @return
	 */
	public String signin_set() {
		return SUCCESS;
	}

	/**
	 * 签退对话框
	 * 
	 * @return
	 */
	public String signout_set() {
		return SUCCESS;
	}

	/**
	 * 判断是否签到
	 */
	public void ifSign() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(date) + " 00:00:00";
		String endTime = sdf.format(date) + " 23:59:59";
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andSignidEqualTo(LoginUtil.getLoginUserId());
		criteria.andSignintimeBetween(Timestamp.valueOf(beginTime),
				Timestamp.valueOf(endTime));
		List<IrpSignInfo> list = signService.selectByExample(example);
		if (list != null && list.size() > 0) {
			irpSignInfo = list.get(0);
		} else {
			irpSignInfo = null;
		}
	}

	/**
	 * 判断是否可以签退
	 */
	public void isSignOut(){
		Date signInDate = DateUtils.getNOWTime();
		Date endtime = SignUtil.getDownTime();
		if (signInDate.compareTo(endtime) >= 0) {
			ActionUtil.writer("ok");
		}else{				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActionUtil.writer(sdf.format(signInDate).toString());
		}
	}
	/**
	 * 签到
	 * 
	 * @param signService
	 */
	public void signIn() {
		if (SignUtil.common(irpSignInfo.getSignindirection())) {
			Date signInDate = DateUtils.getNOWTime();
			IrpSignInfo signInfo = new IrpSignInfo();
			signInfo.setSigninfoid(TableIdUtil.getNextId("irp_sign_info"));
			if(SignUtil.common(irpSignInfo.getSigncomment())){
				signInfo.setSigncomment("『签』:"+irpSignInfo.getSigncomment());
			}else{
				signInfo.setSigncomment("");
			}
			signInfo.setSignindirection(irpSignInfo.getSignindirection());
			signInfo.setSignintime(signInDate);
			signInfo.setSignid(LoginUtil.getLoginUserId());
			signInfo.setSigninip(SignUtil.getAddress());
			if (signInDate.compareTo(SignUtil.getUpTime()) <= 0) {
				signInfo.setSigninstatus(SignUtil.NORMAL);
			} else {
				signInfo.setSigninstatus(SignUtil.LATE);
			}
			ifSign();
			if (irpSignInfo == null) {
				signService.insertSelect(signInfo);
				ActionUtil.writer("1");
				irpSignInfo = null;
				return;
			}
			ActionUtil.writer("0");
		}
	}
	
	/**
	 * 签退
	 * 
	 * @return
	 */
	public void signOut() {
		String comment = irpSignInfo.getSigncomment();
		ifSign();
		if (irpSignInfo != null
				&& SignUtil.common(irpSignInfo.getSigninstatus())
				&& !SignUtil.common(irpSignInfo.getSignoutstatus())) {
			Date signOutDate = DateUtils.getNOWTime();
			IrpSignInfo signInfo = new IrpSignInfo();
			if(SignUtil.common(irpSignInfo.getSigncomment())){//签到有备注
				if (SignUtil.common(comment)) {
					comment = irpSignInfo.getSigncomment() + "『退』:" + comment;
				}else{
					comment = irpSignInfo.getSigncomment();
				}
			}else{
				if (SignUtil.common(comment)) {
					comment = "『退』:" + comment;
				}else{
					comment = "";
				}
			}
			signInfo.setSigninfoid(irpSignInfo.getSigninfoid());
			signInfo.setSigncomment(comment);
			// signInfo.setSignOutdirection();
			signInfo.setSignouttime(signOutDate);
			signInfo.setSignoutip(SignUtil.getAddress());
			if (signOutDate.compareTo(SignUtil.getDownTime()) >= 0) {
				signInfo.setSignoutstatus(SignUtil.NORMAL);
			} else {
				signInfo.setSignoutstatus(SignUtil.EARLY);
			}
			ifSign();
			if (irpSignInfo != null
					&& SignUtil.common(irpSignInfo.getSigninstatus())
					&& !SignUtil.common(irpSignInfo.getSignoutstatus())) {
				signService.updateByPrimaryKeySelective(signInfo);
				ActionUtil.writer("1");
				return;
			}
			ActionUtil.writer("0");
		}

	}
	
	/**
	 * 补签
	 */
	public void signAgain() {
		irpSignInfo.setSignouttime(DateUtils.getNOWTime());
		irpSignInfo.setSignoutstatus("补签");
		irpSignInfo.setSignoutip(SignUtil.getAddress());
		IrpSignInfoExample example = new IrpSignInfoExample();
		example.createCriteria().andSigninfoidEqualTo(irpSignInfo.getSigninfoid());
		List<IrpSignInfo> newSign = signService.selectByExample(example);
		IrpSignInfo old = newSign.get(0);
		if (!SignUtil.common(old.getSignoutstatus())) {
			if(SignUtil.common(irpSignInfo.getSigncomment())){
				if(SignUtil.common(old.getSigncomment())){
					irpSignInfo.setSigncomment(old.getSigncomment()+"『补』:"+irpSignInfo.getSigncomment());
				}else{
					irpSignInfo.setSigncomment("『补』:"+irpSignInfo.getSigncomment());
				}
			}else{
				if(SignUtil.common(old.getSigncomment())){
					irpSignInfo.setSigncomment(old.getSigncomment());
				}else{
					irpSignInfo.setSigncomment("");
				}
			}
			signService.updateByPrimaryKeySelective(irpSignInfo);
			ActionUtil.writer("1");
		}
	}

	/**
	 * 个人签到签退统计报表
	 * 
	 * @return
	 */
	public String getsignPersonStatement() {
		initTime();
		String _oOrderby = "sumscore desc";
		// 分页查出所有用户
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				userService.searchUserValueLinkSize(null, null));
		List<IrpUser> userlist = this.userService.findAllIrpUser(pageUtil,
				_oOrderby, null, null);
		IrpSignInfoExample example = new IrpSignInfoExample();
		if (startTime != null && endTime != null) {
			example.createCriteria().andSignintimeBetween(startTime, endTime);
		}
		signList = signService.selectByExample(example);
		// 用户,正常数量(正常/正常),迟到,早退,补签数量
		StringBuffer sbName = new StringBuffer();
		StringBuffer sbLateNum = new StringBuffer();
		StringBuffer sbEarlyNum = new StringBuffer();
		StringBuffer sbNormalNum = new StringBuffer();
		StringBuffer sbsignAgainNum = new StringBuffer();
		// 遍历用户,计算出对应的结果
		// 外层循环为用户
		for (int i = 0; i < userlist.size(); i++) {
			int late = 0;
			int early = 0;
			int again = 0;
			int normal = 0;
			IrpUser irpUser = userlist.get(i);
			sbName.append("'" + irpUser.getTruename() + "',");
			// 内层签到信息循环
			if (signList != null && signList.size() != 0) {
				for (int j = 0; j < signList.size(); j++) {
					if (Integer.parseInt(irpUser.getUserid() + "") == signList
							.get(j).getSignid()) {
						if ("迟到".equals(signList.get(j).getSigninstatus())) {
							late += 1;
						}
						if ("早退".equals(signList.get(j).getSignoutstatus())) {
							early += 1;
						}
						if ("补签".equals(signList.get(j).getSignoutstatus())) {
							again += 1;
						}
						if ("正常".equals(signList.get(j).getSigninstatus())
								&& "正常".equals(signList.get(j)
										.getSignoutstatus())) {
							normal += 1;
						}
					}
				}
			}
			sbLateNum.append(late + ",");
			sbEarlyNum.append(early + ",");
			sbsignAgainNum.append(again + ",");
			sbNormalNum.append(normal + ",");
		}

		usernamejson = sbName.toString().substring(0,
				sbName.toString().length() - 1);
		latejson = sbLateNum.toString().substring(0,
				sbLateNum.toString().length() - 1);
		earlyjson = sbEarlyNum.toString().substring(0,
				sbEarlyNum.toString().length() - 1);
		normaljson = sbNormalNum.toString().substring(0,
				sbNormalNum.toString().length() - 1);
		signAgainjson = sbsignAgainNum.toString().substring(0,
				sbsignAgainNum.toString().length() - 1);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}

	/**
	 * 初始化报表时间
	 * 
	 * @return
	 */
	public void initTime() {
		if (this.timeLimit != null) {
			LogTimeUtil logTimeUtil = new LogTimeUtil();
			if (this.timeLimit.trim().equals("thismonth")) {// 本月
				startTime = logTimeUtil.getMonth();
				endTime = logTimeUtil.getLastMonth();
			} else if (this.timeLimit.trim().equals("thisweek")) {// 本周
				startTime = logTimeUtil.getWeek();
				endTime = logTimeUtil.getLastWeek();
			} else if (this.timeLimit.trim().equals("thisquarter")) {// 本季度
				startTime = logTimeUtil.getQuarter();
				endTime = logTimeUtil.getLastQuarter();
			}
		}
	}

	/**
	 * 统计数量
	 * 
	 * @param msg
	 * @return
	 */
	public Integer judge(String msg) {
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		if (startTime != null && endTime != null) {
			criteria.andSignintimeBetween(startTime, endTime);
		}
		if (userId != null && userId != 0) {
			criteria.andSignidEqualTo(userId);
		}
		if ("迟到".equals(msg)) {
			criteria.andSigninstatusEqualTo("迟到");
		} else if ("早退".equals(msg)) {
			criteria.andSignoutstatusEqualTo("早退");
		} else if ("补签".equals(msg)) {
			criteria.andSignoutstatusEqualTo("补签");
		} else {
			criteria.andSigninstatusEqualTo("正常");
			criteria.andSignoutstatusEqualTo("正常");
		}
		List list = signService.selectByExample(example);
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	/**
	 * 整体概况
	 * 
	 * @return
	 */
	public String checkByBing() {
		initTime();
		StringBuffer buffer = new StringBuffer();
		int normal = judge("正常");
		int late = judge("迟到");
		int early = judge("早退");
		int again = judge("补签");
		buffer.append(normal + "," + late + "," + early + "," + again);
		amountJsonString = buffer.toString();
		return SUCCESS;
	}

	/**
	 * 设置横坐标的值
	 * 
	 * @param limit
	 */
	public int setTimeBar(String limit) {
		int amount = 0;
		if (limit != null) {
			StringBuffer xAxis = new StringBuffer();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			xAxis.append("[");
			LogTimeUtil logTimeUtil = new LogTimeUtil();
			if (limit.trim().equals("thismonth")) {// 本月 结束日期是到明天早上即包含今天
				startTime = logTimeUtil.getMonth();
				endTime = new Date();
				Long limitamount = endTime.getTime() - startTime.getTime();
				amount = (int) (limitamount / 1000 / 60 / 60 / 24);
				calendar.setTime(startTime);
				xAxis.append("'" + format.format(calendar.getTime()) + "'");
				for (int i = 0; i < amount; i++) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					xAxis.append("," + "'" + format.format(calendar.getTime())
							+ "'");
				}
			} else if (limit.trim().equals("thisweek")) {// 本周 结束日期是到明天早上即包含今天
				startTime = logTimeUtil.getWeek();
				endTime = new Date();
				Long limitamount = endTime.getTime() - startTime.getTime();
				amount = (int) (limitamount / 1000 / 60 / 60 / 24);
				calendar.setTime(startTime);
				xAxis.append(calendar.get(Calendar.DAY_OF_WEEK) - 1);
				for (int i = 0; i < amount; i++) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					xAxis.append("," + (calendar.get(Calendar.DAY_OF_WEEK) - 1));
				}
			} else if (limit.trim().equals("thisquarter")) {// 本季度
															// 结束日期是到明天早上即包含今天
				startTime = logTimeUtil.getQuarter();
				endTime = new Date();
				Long limitamount = endTime.getTime() - startTime.getTime();
				amount = (int) (limitamount / 1000 / 60 / 60 / 24);
				calendar.setTime(startTime);
				xAxis.append("'" + format.format(calendar.getTime()) + "'");
				for (int i = 0; i < amount; i++) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					String nextDate = format.format(calendar.getTime());
					xAxis.append(",'" + nextDate + "'");
				}
			} else {
				Long limitamount = endTime.getTime() - startTime.getTime();
				amount = (int) (limitamount / 1000 / 60 / 60 / 24);
				calendar.setTime(startTime);
				xAxis.append("'" + format.format(startTime) + "'");
				for (int i = 0; i < amount; i++) {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					String nextDate = format.format(calendar.getTime());
					xAxis.append(",'" + nextDate + "'");
				}
			}
			xAxis.append("]");
			xaxisJasonString = xAxis.toString();
		}
		return amount;
	}

	/**
	 * 获得分类集合
	 * 
	 * @param msg
	 * @return
	 */
	public List<String> getType(String msg) {
		List<String> stringList = new ArrayList<String>();
		if ("normal".equals(msg)) {
			stringList.add("正常");
		} else {
			stringList.add("迟到");
			stringList.add("早退");
			stringList.add("补签");
		}
		return stringList;
	}

	/**
	 * 个人签到具体统计趋势图
	 * 
	 * @return
	 */
	public String signPersonSpecific() {
		if (this.timeLimit != null) {
			initTime();
			String iid = ServletActionContext.getRequest().getParameter("iid");
			if (SignUtil.common(iid)) {
				userId = Long.valueOf(iid);
			}
			if (userId == null || userId.longValue() == 0L) {
				userId = LoginUtil.getLoginUserId();
				userName = LoginUtil
						.getUserNameString(LoginUtil.getLoginUser());
			}
			IrpSignInfoExample example = new IrpSignInfoExample();
			Criteria c = example.createCriteria();
			if (startTime != null && endTime != null) {
				c.andSignintimeBetween(startTime, endTime);
			}
			c.andSignidEqualTo(userId);
			List<IrpSignInfo> infos = signService.selectByExample(example);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			int amount = setTimeBar(this.timeLimit);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);// 设置初始时间
			StringBuffer amountStr = new StringBuffer();// 正常
			StringBuffer lateStr = new StringBuffer();// 迟到
			StringBuffer earlyStr = new StringBuffer();// 早退
			StringBuffer signAgainStr = new StringBuffer();// 补签
			if (infos != null) {
				Long allNormal = 0L;
				Long allLate = 0L;
				Long allEarly = 0L;
				Long allSignAgain = 0L;
				for (int i = 0; i <= amount; i++) {
					Long normalAmount = 0L;// 正常
					Long lateAmount = 0L;// 迟到
					Long earlyAmount = 0L;// 早退
					Long signAgainAmount = 0L;// 补签
					for (int j = 0; j < infos.size(); j++) {
						IrpSignInfo info = infos.get(j);
						Date signInTime = info.getSignintime();
						String dateStr = dateFormat.format(signInTime);
						String time = dateFormat.format(calendar.getTime());
						String statusIn = info.getSigninstatus();
						String statusOut = info.getSignoutstatus();
						if (dateStr.equals(time)) {
							if ("正常".equals(statusIn) && "正常".equals(statusOut)) {
								normalAmount = 1L;
							} else {
								if ("迟到".equals(statusIn)) {
									lateAmount = 1L;
								}
								if ("早退".equals(statusOut)) {
									earlyAmount = 1L;
								} else if ("补签".equals(statusOut)) {
									signAgainAmount = 1L;
								}
							}
							break;
						}
					}
					allNormal = allNormal + normalAmount;
					allLate = allLate + lateAmount;
					allEarly = allEarly + earlyAmount;
					allSignAgain = allSignAgain + signAgainAmount;

					amountStr.append(normalAmount + ",");
					lateStr.append(lateAmount + ",");
					earlyStr.append(earlyAmount + ",");
					signAgainStr.append(signAgainAmount + ",");
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				this.norJsonString = allNormal + "";
				this.unorJsonString = allLate + "," + allEarly + ","
						+ allSignAgain;
			}
			if (amountStr != null && amountStr.length() > 0) {
				amountJsonString = "["
						+ amountStr.substring(0, amountStr.length() - 1) + "]";
			}
			if (lateStr != null && lateStr.length() > 0) {
				latejson = "[" + lateStr.substring(0, lateStr.length() - 1)
						+ "]";
			}
			if (lateStr != null && lateStr.length() > 0) {
				earlyjson = "[" + earlyStr.substring(0, earlyStr.length() - 1)
						+ "]";
			}
			if (lateStr != null && lateStr.length() > 0) {
				signAgainjson = "["
						+ signAgainStr.substring(0, signAgainStr.length() - 1)
						+ "]";
			}
		}
		return SUCCESS;
	}
	
	
	public void exportToZip(){
		/**
		 * 查询出userid集合
		 */
		ActionContext ac = ActionContext.getContext();     
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
        String WEB_ROOT_PATH = sc.getRealPath("/"); 
        String app_path=WEB_ROOT_PATH.replace("\\", "/");
        String path=app_path+"sign";
		List<Long> userIds = null;
		if(common(searchName)){
			try {
				if(!"用户姓名".equals(searchName)){
					IrpUserExample userExample = new IrpUserExample();
					com.tfs.irp.user.entity.IrpUserExample.Criteria criteria1 = userExample.createCriteria();
					com.tfs.irp.user.entity.IrpUserExample.Criteria criteria2 = userExample.createCriteria();
					criteria1.andTruenameLike("%"+this.searchName.trim()+"%");
					criteria2.andNicknameLike("%"+this.searchName.trim()+"%");
					userExample.or(criteria1);
					userExample.or(criteria2);
					List<IrpUser> userList = userService.findUserByExample(userExample);
					if(userList!=null && userList.size()>0){
						userIds = new ArrayList<Long>();
						for(IrpUser ele : userList){
							userIds.add(ele.getUserid());
						}
					}else{
						signList = null;
						ifSign();
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		if(userIds!=null){
			criteria.andSignidIn(userIds);
		}
		if(common(beginTime) && common(finishTime)){
			String finishTime1=finishTime+" 23:59:59";
			criteria.andSignintimeBetween(DateUtils.getDateByStrToYMD(beginTime), DateUtils.getDateByYMDHMS(finishTime1));		}else if(common(beginTime)){
			criteria.andSignintimeGreaterThanOrEqualTo(DateUtils.getDateByStrToYMD(beginTime));
		}else if(common(finishTime)){
			criteria.andSignintimeLessThanOrEqualTo(DateUtils.getDateByStrToYMD(finishTime));
		}
		
		example.setOrderByClause("signid ,signintime ");
		int datacount = signService.getDataCount(userIds,beginTime,finishTime);
		signList = signService.signInfo(example);
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		// 初始化未签退记录
		notSignOutRecord(signList);
		// 转换id为truename
		signService.exportAllSignInfoToZip(signList,path,fileName);
	}
	

	public void setSignService(SignService signService) {
		this.signService = signService;
	}

	public List<IrpSignInfo> getSignList() {
		return signList;
	}

	public void setSignList(List<IrpSignInfo> signList) {
		this.signList = signList;
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

	public IrpSignInfo getIrpSignInfo() {
		return irpSignInfo;
	}

	public void setIrpSignInfo(IrpSignInfo irpSignInfo) {
		this.irpSignInfo = irpSignInfo;
	}

	public static Logger getLogger() {
		return logger;
	}

	public IrpUser getUser() {
		return user;
	}

	public void setUser(IrpUser user) {
		this.user = user;
	}

	public void setUserService(IrpUserService userService) {
		this.userService = userService;
	}

	public String getUsernamejson() {
		return usernamejson;
	}

	public void setUsernamejson(String usernamejson) {
		this.usernamejson = usernamejson;
	}

	public String getLatejson() {
		return latejson;
	}

	public void setLatejson(String latejson) {
		this.latejson = latejson;
	}

	public String getEarlyjson() {
		return earlyjson;
	}

	public void setEarlyjson(String earlyjson) {
		this.earlyjson = earlyjson;
	}

	public String getNormaljson() {
		return normaljson;
	}

	public void setNormaljson(String normaljson) {
		this.normaljson = normaljson;
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

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getAmountJsonString() {
		return amountJsonString;
	}

	public void setAmountJsonString(String amountJsonString) {
		this.amountJsonString = amountJsonString;
	}

	public String getSignAgainjson() {
		return signAgainjson;
	}

	public void setSignAgainjson(String signAgainjson) {
		this.signAgainjson = signAgainjson;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getXaxisJasonString() {
		return xaxisJasonString;
	}

	public void setXaxisJasonString(String xaxisJasonString) {
		this.xaxisJasonString = xaxisJasonString;
	}

	public String getNorJsonString() {
		return norJsonString;
	}

	public void setNorJsonString(String norJsonString) {
		this.norJsonString = norJsonString;
	}

	public String getUnorJsonString() {
		return unorJsonString;
	}

	public void setUnorJsonString(String unorJsonString) {
		this.unorJsonString = unorJsonString;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Map<Long, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<Long, String> nameMap) {
		this.nameMap = nameMap;
	}

	public Map<Long, String> getNotSignOutRecord() {
		return notSignOutRecord;
	}

	public void setNotSignOutRecord(Map<Long, String> notSignOutRecord) {
		this.notSignOutRecord = notSignOutRecord;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getBeginTime() {
		return beginTime;
	}
	
	
	
	

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}
