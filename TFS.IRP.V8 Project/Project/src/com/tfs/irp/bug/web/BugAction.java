package com.tfs.irp.bug.web;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.bind.v2.TODO;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.bug.entity.IrpBug;
import com.tfs.irp.bug.entity.IrpBugWithBLOBs;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.bug.entity.IrpBugExample.Criteria;
import com.tfs.irp.bug.service.BugService;
import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import com.tfs.irp.bug_config.service.BugConfigService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class BugAction extends ActionSupport {

	/**
	 * 是否是项目的创建者  1代表是    0代表不是
	 */
	private int isprojectcre=0;
	
	private Long loginuserid;
	
	private Long tabPid=0l;

	public Long getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}

	public BugAction() {
		loginuserid = LoginUtil.getLoginUserId();

	}
	
	private Map<Long,String> versionNameMap=new HashMap<Long, String>();
	
	private Map<Long,String> modulNameMap=new HashMap<Long, String>();

	/**
	 * 分配给我的未解决Bug
	 */
	private List<IrpBugWithBLOBs> tomeBugsWei;

	/**
	 * 分配给我的已完成Bug
	 */
	private List<IrpBugWithBLOBs> tomeBugsWan;

	private String fileName;

	private String fileTrueName;

	private int isall = 1;

	private int seltype;

	/**
	 * 是否发送私信
	 */
	private int issendmessage;
	
	private IrpUserService userService;
	/**
	 * 分配人trueName Map
	 */
	private Map<Long, String> founderNameMap = new HashMap<Long, String>();
	/**
	 * 处理人trueName Map
	 */
	private Map<Long, String> operatorNameMap = new HashMap<Long, String>();
	/**
	 * 我完成的bug发起人trueName Map
	 */
	private Map<Long, String> meFounderNameMap = new HashMap<Long, String>();
	/**
	 * 我完成的bug处理人trueName Map
	 */
	private Map<Long, String> meOperatorNameMap = new HashMap<Long, String>();

	/**
	 * 优先级Map
	 */
	private Map<String, String> priorityMap = new HashMap<String, String>();

	private BugService bugService;

	private String stateType = "0";

	private String jsonFile;

	/**
	 * 项目成员
	 */
	private List<IrpUser> projectUsers;

	private IrpProjectPersonService projectpersonservice;

	private IrpBugWithBLOBs bug;

	private List<IrpAttached> attacheds;

	/**
	 * 所属项目
	 */
	private IrpProject project;

	private IrpProjectService projectService;

	private List<IrpProject> irpProjects;

	// do=1&disposeUserId=6&bugComment=&serianum=57 页面信息
	private int dowhat;

	/**
	 * 页面处理权限 2状态什么也不显示 1状态什么都显示 3状态不显示未完成按钮
	 */
	private int power = 0;

	/**
	 * 转发的下一个处理人
	 */
	private Long disposeUserId;

	private String bugComment;

	/**
	 * 分页器
	 */
	private String pageHTML;
	private int pageNum = 1;// 当前页
	int s = SysConfigUtil.getSysConfigNumValue(IrpBug.PAGE_SIZE_ONE);// 每页数量
	private int pageSize = s;
	private String queryType;
	
	/**
	 * 分页器上部分
	 */
	private String pageHTMLTop;
	private int pageNumTop = 1;// 当前页
	private int pageSizeTop = SysConfigUtil.getSysConfigNumValue(IrpBug.PAGE_SIZE_TOW);// 每页数量
	private String queryTypeTop;
	private Long projectIdTop;
	
	/**
	 * 分页器下部分
	 */
	private String pageHTMLBottom;
	private int pageNumBottom = 1;// 当前页
	private int pageSizeBottom = SysConfigUtil.getSysConfigNumValue(IrpBug.PAGE_SIZE_TOW);;// 每页数量
	private String queryTypeBottom;
	private Long projectIdBottom;
	
	
	private String headType;// 页面状态 ?所有/我创建/我处理/我待办
	private String founderTrueName;
	private String operatorTrueName;
	
	/**
	 * 排序条件
	 */
	private String order="priority desc,createtime desc";
	
	/**
	 * 排序类型
	 */
	private String ordertype;
	
	/**
	 * 排序条件top
	 */
	private String ordertop="priority desc,createtime desc";
	
	/**
	 * 排序类型bottom
	 */
	private String ordertoptype;
	
	/**
	 * 排序条件top
	 */
	private String orderbottom="priority desc,createtime desc";
	
	/**
	 * 排序类型bottom
	 */
	private String orderbottomtype;
	
	private Long bugId;
	private Long serianum;

	private List<IrpBugConfig> bugversions;

	private List<IrpBugConfig> bugmoduls=new ArrayList<IrpBugConfig>();

	private BugConfigService bugconfigservice;

	private Long versionid;

	private Long modulid;
	
	private String attachedids;

	/**
	 * bug标题
	 */
	private String bugTitle;
	/**
	 * 最新状态
	 */
	private String lastBugState;

	/**
	 * 创始人
	 */
	private Long founderId;
	/**
	 * 处理人
	 */
	private Long operatorId;
	/**
	 * 所属项目ID
	 */
	private Long projectId;
	/**
	 * 优先级
	 */
	private String priority;
	/**
	 * 描述
	 */
	private String describe;
	/**
	 * 状态 :已创建/未处理/待审核/已完成
	 */
	private String state;
	
	/**
	 * 统计跳转所有Bug页面的状态
	 */
	private String nstate;
	
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 要求完成时间
	 */
	private java.util.Date requiredTime;
	/**
	 * 实际完成时间
	 */
	private java.util.Date finishTime;
	/**
	 * 截图 暂时不用
	 */
	private String printScreen;
	/**
	 * 是否是最后一条 暂时不用
	 */
	private Long freshness;
	/**
	 * 是否处理过
	 */
	private Long isdispose;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * Bug集合
	 */
	private List<IrpBugWithBLOBs> irpBugs;

	/**
	 * 私信接口
	 * 
	 * @return
	 */
	private IrpMessageContentService messageService;

	/**
	 * 紧急待处理的Bug
	 */
	private List<IrpBugWithBLOBs> urgentBugs;

	/**
	 * 最新Bug
	 */
	private List<IrpBugWithBLOBs> newestBugs;
	
	/****************数据统计*****************/
	private String projectPersonsJson;
	
	private String bugsForModulJson;
	
	private String bugsForPersonJson;
	
	//reyuan
	private String weiBugsJson;
	//renyuan
	private String shenBugsJson;
	//renyuan
	private String wanBugsJson;
	
	//modul
	private String mweiBugsJson;
	private String mshenBugsJson;
	private String mwanBugsJson; 
	
	//fenbu
	//private Map<String,Integer> fenbu1 =new HashMap<String, Integer>();
	private List<Map> fenbu1=new ArrayList<Map>();
	
	private List<Map> fenbu2=new ArrayList<Map>();
	//private String fenbu2;
	
	private String zoushiDate;
	
	private String zoushiCount;
	
	private java.util.Date startDate;
	
	private java.util.Date endDate;
	
	/*********************************/
	public String bugInit() {
		return "SUCCESS";
	}

	public String bugManager() {
		// System.out.println(this.projectid);
		return SUCCESS;
	}

	/**
	 * 添加Bug
	 * 
	 * @return
	 */
	public String addBug() {
		try {
			// System.out.println("负责人Id是"+this.operatorId);
			/** 基础记录begin **/
			IrpBugWithBLOBs bug = new IrpBugWithBLOBs();
			Long key = TableIdUtil.getNextId(bug);
			this.serianum = key;
			bug.setBugid(key);
			bug.setSerianum(key);
			// bug.setBugid(33L);
		//	bugTitle=new  String(bugTitle.getBytes("ISO8859-1"), "utf-8");
			//describe=new  String(describe.getBytes("ISO8859-1"), "utf-8");
			bug.setTitle(bugTitle);
			this.founderId = LoginUtil.getLoginUserId();
			bug.setFounderid(founderId);
			bug.setOperatorid(operatorId);
			bug.setProjectid(projectId);
			bug.setPriority(this.priority);
			bug.setDescribe(describe);
			bug.setState(IrpBugWithBLOBs.STATE_CREAT);
			this.createTime = new java.util.Date();
			bug.setCreatetime(createTime);
			bug.setRequiredtime(requiredTime);
			bug.setFreshness(IrpBugWithBLOBs.FRESHNESS_FIRST);
			bug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_NO);
			bug.setVersionid(versionid);
			bug.setModuleid(modulid);
			bug.setFilename(fileName);
			bug.setFiletruename(fileTrueName);
			bugService.addBug(bug);
			state = IrpBugWithBLOBs.STATE_WEIWAN;
			/** 基础记录end **/
			this.isdispose = IrpBugWithBLOBs.ISDISPORE_NO;
			addWillBug();
			this.updateNewestState(IrpBugWithBLOBs.STATE_WEIWAN);
			if(issendmessage ==1){
				this.sendAddMessage(operatorId);
			}
			if (jsonFile != null && jsonFile != "") {
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
							typeid, key, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				}
			}
			this.tabPid=projectId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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
					IrpAttached.BUGDOCTYPEID, newFilePath,
					Integer.parseInt(_sEditversions), TypeId, _attflag);
			_attachedids.add(_attachedid);
		}
		return _attachedids;

	}

	/**
	 * 添加预处理Bug
	 */
	private void addWillBug() {

		try {
			/** 最新记录begin **/
			IrpBugWithBLOBs bugnew = new IrpBugWithBLOBs();
			bugnew.setTitle(bugTitle);
			bugnew.setBugid(TableIdUtil.getNextId("irp_bug"));
			bugnew.setSerianum(serianum);
			bugnew.setFounderid(operatorId);
			bugnew.setOperatorid(founderId);
			bugnew.setProjectid(projectId);
			bugnew.setRequiredtime(requiredTime);
			bugnew.setState(state);
			bugnew.setPriority(this.priority);
			bugnew.setIsdispose(this.isdispose);
			bugnew.setFreshness(IrpBugWithBLOBs.FRESHNESS_WILL);
			bugService.addBug(bugnew);
			/** 最新记录end **/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据状态改变显示Bug
	 * 
	 * @param criteria
	 */
	private void findByState(Criteria criteria) {
		if ("0".equals(stateType)) {
			return;
		} else if ("1".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
		} else if ("2".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
		} else if ("3".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		}
	}

	/**
	 * 初始化Bug信息 所有/我创建/我处理
	 * 
	 * @return
	 */
	public String findBugForProject() {
		String result = "success";
		try {
			IrpBugExample bugExample = new IrpBugExample();
			Criteria criteria = bugExample.createCriteria();
			if (projectId != null && !projectId.equals("")) {
				criteria.andProjectidEqualTo(projectId);
			}
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			if ("1".equals(headType)) {
				result = "page";
			} else if ("2".equals(headType)) {
				criteria.andFounderidEqualTo(LoginUtil.getLoginUserId());
				result = "page";
			} else if ("3".equals(headType)) {
				criteria.andOperatoridEqualTo(LoginUtil.getLoginUserId());
				result = "page";
			} else if ("4".equals(headType)) {
				List<Long> ss = getSerianums(LoginUtil.getLoginUserId());
				if (ss.size() == 0) {
					irpBugs = null;
					return "page";
				}
				criteria.andIsdisposeNotEqualTo(IrpBugWithBLOBs.ISDISPORE_OK);
				criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				criteria.andSerianumIn(ss);
				result = "page";
			}
			if(nstate !=null  && !"".equals(nstate) ){
				if(nstate.equals("1")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
				}
				else if(nstate.equals("2")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);		
							
				}else if(nstate.equals("3")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				}
			}else{
				findByState(criteria);
			}
			
			if (founderId != null && !founderId.equals("")) {
				addFounderIdIterm(criteria);
			}
			if (operatorId != null && !operatorId.equals("")) {
				addOperatorIdIterm(criteria);
			}
			if (priority != null && !priority.equals("")) {
				addPriorityIterm(criteria);
			}
			if (keyword != null && !keyword.equals("")) {
				criteria.andTitleLike("%" + keyword + "%");
			}
			if (versionid != null && !versionid.equals("")) {
				criteria.andVersionidEqualTo(versionid);
			}
			if (modulid != null && !modulid.equals("")) {
				criteria.andModuleidEqualTo(modulid);
			}
			bugExample.setOrderByClause(order);
			int dataCount = bugService.getDataCount(bugExample);
			PageUtil page = new PageUtil(pageNum, pageSize, dataCount);
			irpBugs = bugService.queryBugForPage(bugExample, page);
			this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
			/*List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());*/
			if ("1".equals(queryType)) {
				result = "page";
			}
			if (irpBugs.size() != 0) {
				/*for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
				for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getOperatorid() != null) {
						for (IrpUser element : userList) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}*/
				for (IrpBugWithBLOBs ele : irpBugs) {
					IrpUser user=userService.findUserByUserId(ele.getFounderid());
					founderNameMap.put(ele.getFounderid(),
							user.getTruename());
				}
				for (IrpBugWithBLOBs ele : irpBugs) {
					IrpUser user=userService.findUserByUserId(ele.getOperatorid());
					operatorNameMap.put(ele.getOperatorid(),
							user.getTruename());
				}
				this.vmNameMapInit();
			} else {
				irpBugs = null;
			}
			if (projectId != null && !projectId.equals("")) {
				getProjectUser(projectId, true);
			} else {
				this.projectUsers = userService.findAllRegUsers();
			}
			this.findMyProjects();
			this.findVersionByProid();
			this.findModulByProid();
			if (seltype == 1) {
				result = SUCCESS;
			}
			initPriority();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加分配人条件
	 * 
	 * @param criteria
	 */
	private void addFounderIdIterm(Criteria criteria) {
		criteria.andFounderidEqualTo(founderId);
	}

	/**
	 * 添加处理人条件
	 * 
	 * @param criteria
	 */
	private void addOperatorIdIterm(Criteria criteria) {
		criteria.andOperatoridEqualTo(operatorId);
	}

	/**
	 * 添加优先级条件
	 * 
	 * @param criteria
	 */
	private void addPriorityIterm(Criteria criteria) {
		criteria.andPriorityEqualTo(priority);
	}

	/**
	 * 跳转到添加bug页面
	 * 
	 * @return
	 */
	public String toAddBugPage() {
		try {
			if (projectId != null && !projectId.equals("")) {
				findAddProject();
				getProjectUser(projectId, true);
				this.findVersionByProid();
				this.findModulByProid();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否在该项目中
	 * @return
	 */
	private int isInProject(){
		IrpProjectPersonExample example = null;
		try {
			this.projectId=this.bugService.selectByPrimaryKey(serianum).getProjectid();
			example = new IrpProjectPersonExample();
			com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria=example.createCriteria();
			criteria.andPridEqualTo(projectId);
			criteria.andPersonidEqualTo(LoginUtil.getLoginUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.projectpersonservice.findPersonByExample(example).size();
	}

	/**
	 * 跳转到Bug处理页面 显示Bug处理信息
	 * 
	 * @return
	 */
	public String simplebuginfo() {
		String result=null;
		try {
			this.loginuserid=LoginUtil.getLoginUserId();
			IrpBugExample example = new IrpBugExample();
			example.setOrderByClause("BUGID");
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			irpBugs = bugService.selectByExample(example);
			if(irpBugs.size()>0){
				if(isInProject()>0){
				this.lastBugState = irpBugs.get(irpBugs.size() - 1).getState();
				List<IrpUser> userList = userService
						.findUserByExample(new IrpUserExample());
				for (IrpBugWithBLOBs ele : irpBugs) {
					for (IrpUser element : userList) {
						if (ele.getFounderid() == Integer.parseInt(element
								.getUserid() + "")) {
							founderNameMap.put(ele.getFounderid(),
									element.getTruename());
							break;
						}
					}
				}
				for (IrpBugWithBLOBs ele : irpBugs) {
					for (IrpUser element : userList) {
						if (ele.getOperatorid() != null
								&& !ele.getOperatorid().equals("")) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				project = projectService.findProjectById(irpBugs.get(0)
						.getProjectid());
				//System.out.println(1);
				attacheds = this.irpAttachedService.getAttachedByAttDocId(serianum,
						IrpAttached.BUGDOCTYPEID);
				this.projectId=project.getId();
				isProjectCre(project.getId());
				this.vmNameMapInit();
				// 获取项目成员,不包含自己
				getProjectUser(irpBugs.get(0).getProjectid(), false);
				powerInit();
				initPriority();
				result= SUCCESS;
				}else{
					ActionUtil.writer("0");
				}
				// operatorTrueName=userService.findUserByUserId(bug.getOperatorid()).getTruename();
			}else{
				ActionUtil.writer("1");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询当前登录的用户是否是这个项目的创始人   projectId
	 */
	private void isProjectCre(Long pid){
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria=example.createCriteria();
		criteria.andPridEqualTo(pid).andCruseridEqualTo(LoginUtil.getLoginUserId());
		List<IrpProjectPerson> list = projectpersonservice.findPersonByExample(example);
		if(list.size()>0){
			this.isprojectcre=1;
		}
	}

	/**
	 * 处理Bug
	 * 
	 * @return
	 */
	public String bugDispose() {

		return SUCCESS;
	}

	/**
	 * 获取项目成员 true 所有项目成员,false 不包括登录用户
	 * 
	 * @param projectid
	 * @param bl
	 */
	private void getProjectUser(Long projectid, boolean bl) {
		IrpProjectPersonExample example = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = example
				.createCriteria();
		if (!bl) {
			Long loginid = LoginUtil.getLoginUserId();
			criteria.andPersonidNotEqualTo(loginid);
		}
		criteria.andPridEqualTo(projectid);
		List<IrpProjectPerson> projectPersons = this.projectpersonservice
				.findPersonByExample(example);
		List<Long> presonIds = new ArrayList<Long>();
		for (IrpProjectPerson ele : projectPersons) {
			presonIds.add(ele.getPersonid());
		}
		IrpUserExample userExample = new IrpUserExample();
		userExample.setOrderByClause("USERID");
		com.tfs.irp.user.entity.IrpUserExample.Criteria userCriteria = userExample
				.createCriteria();
		if (presonIds.size() > 0) {
			userCriteria.andUseridIn(presonIds);
			projectUsers = userService.findUserByExample(userExample);
		} else {
			projectUsers = null;
		}
	}

	/**
	 * 总处理
	 */
	public String dispose() {
		try {
			if (this.dowhat == 1) {
				resolved();
			} else if (this.dowhat == 2) {
				weiwan();
			} else if (this.dowhat == 3) {
				forward();
			}
			simplebuginfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "page";
	}

	/**
	 * 处理 已解决
	 * 
	 * @return
	 */
	private void resolved() {
		try {
			Long loginUserId = LoginUtil.getLoginUserId();
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			List<IrpBugWithBLOBs> firstBug = bugService.selectByExample(example);
			/** 查询预处理数据begin **/
			example.clear();
			Criteria criteria3 = example.createCriteria();
			criteria3.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			criteria3.andSerianumEqualTo(serianum);
			IrpBugWithBLOBs bugnew = bugService.selectByExample(example).get(0);
			/** 查询预处理数据end **/
			if (firstBug.get(0).getFounderid().equals(loginUserId)) {
				/** 更新预处理数据 begin **/
				bugnew.setState(IrpBugWithBLOBs.STATE_WANCHENG);
				bugnew.setCreatetime(new java.util.Date());
				bugnew.setFreshness(IrpBugWithBLOBs.ISDISPORE_OK);
				bugnew.setBugcomment(bugComment);
				bugnew.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
				example.clear();
				Criteria criteria2 = example.createCriteria();
				criteria2.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
				criteria2.andSerianumEqualTo(serianum);
				bugService.updateByExample(bugnew, example);
				updateNewestState(IrpBugWithBLOBs.STATE_WANCHENG);
				this.updateIsdispose(IrpBugWithBLOBs.ISDISPORE_OK);
				/** 更新预处理数据 end **/
			} else {
				bugnew.setState(IrpBugWithBLOBs.STATE_SHENHE);
				bugnew.setCreatetime(new java.util.Date());
				bugnew.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
				bugnew.setBugcomment(bugComment);
				bugnew.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
				example.clear();
				Criteria criteria2 = example.createCriteria();
				criteria2.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
				criteria2.andSerianumEqualTo(serianum);
				bugService.updateByExample(bugnew, example);
				this.founderId = bugnew.getFounderid();
				this.operatorId = bugnew.getOperatorid();
				this.projectId = bugnew.getProjectid();
				this.priority = bugnew.getPriority();
				this.bugTitle = bugnew.getTitle();
				this.requiredTime = bugnew.getRequiredtime();
				this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
				state = IrpBugWithBLOBs.STATE_SHENHE;
				this.addWillBug();
				this.updateNewestState(state);
				this.updateIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
			}
			this.bugTitle = bugnew.getTitle();
			this.sendOtherMessage(bugnew.getOperatorid());
			setBugDispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bug处理 未完成
	 */
	private void weiwan() throws Exception {
		IrpBugWithBLOBs willBug = this.findWillBug();
		willBug.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
		willBug.setBugcomment(bugComment);
		willBug.setCreatetime(new java.util.Date());
		willBug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
		willBug.setState(IrpBugWithBLOBs.STATE_WEIWAN);
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andSerianumEqualTo(serianum);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		bugService.updateByExampleSelective(willBug, example);
		this.founderId = willBug.getFounderid();
		this.operatorId = willBug.getOperatorid();
		this.projectId = willBug.getProjectid();
		this.priority = willBug.getPriority();
		this.bugTitle = willBug.getTitle();
		state = IrpBugWithBLOBs.STATE_WEIWAN;
		this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
		this.addWillBug();
		this.updateNewestState(state);
		this.sendOtherMessage(operatorId);
	}

	/**
	 * 转发
	 */
	private void forward() throws Exception {
		IrpBugWithBLOBs bg = findWillBug();
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andSerianumEqualTo(serianum);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		bg.setState(IrpBugWithBLOBs.STATE_FORWARD);
		bg.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
		bg.setCreatetime(new java.util.Date());
		bg.setOperatorid(disposeUserId);
		bg.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
		bg.setBugcomment(bugComment);
		bugService.updateByExampleSelective(bg, example);
		this.bugTitle = bg.getTitle();
		this.operatorId = this.disposeUserId;
		this.founderId = this.findCreateUserId();
		this.projectId = bg.getProjectid();
		this.requiredTime = bg.getRequiredtime();
		this.state = IrpBugWithBLOBs.STATE_WEIWAN;
		this.priority = bg.getPriority();
		this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
		this.addWillBug();
		setBugDispose();
		updateNewestState(IrpBugWithBLOBs.STATE_WEIWAN);
		this.sendForwardMessage(disposeUserId);
	}

	/**
	 * 获得预处理Bug
	 * 
	 * @return 预处理IrpBug
	 */
	private IrpBugWithBLOBs findWillBug() throws Exception {
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		criteria.andSerianumEqualTo(serianum);
		return bugService.selectByExample(example).get(0);
	}

	/**
	 * 初始化页面权限
	 * 
	 * @return
	 */
	private void powerInit() {
		try {
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			IrpBugWithBLOBs pbug = bugService.selectByExample(example).get(0);
			if (IrpBugWithBLOBs.STATE_SHENHE.equals(pbug.getState())) {
				if (pbug.getFounderid().equals(LoginUtil.getLoginUserId())) {
					power = 1;
				} else if (pbug.getOperatorid().equals(
						LoginUtil.getLoginUserId())) {
					power = 2;
				}
			} else if (IrpBugWithBLOBs.STATE_WEIWAN.equals(pbug.getState())) {
				if (pbug.getFounderid().equals(LoginUtil.getLoginUserId())) {
					power = 3;
				} else if (pbug.getOperatorid().equals(
						LoginUtil.getLoginUserId())) {
					power = 2;
				}
			} else {
				power = 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置Bug为已处理
	 * 
	 * @return
	 */
	private void setBugDispose() {
		try {
			IrpBugWithBLOBs diposeBug = new IrpBugWithBLOBs();
			diposeBug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(diposeBug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到Bug管理页面
	 * @return
	 */
	public String toBugManage() {
		try {
			if(projectId!=null && !"".equals(projectId)){
				tabPid=projectId;
			}
			findMyProjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 初始化左侧页面
	 * @return
	 */
	public String addLeft() {
		try {
			findMyProjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 更新Bug最新状态
	 * 
	 * @return
	 */
	private void updateNewestState(String neweststate) {
		try {
			IrpBugWithBLOBs newbug = new IrpBugWithBLOBs();
			newbug.setNeweststate(neweststate);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(newbug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取创始人Id
	 * 
	 * @return
	 * @throws Exception
	 */
	private Long findCreateUserId() throws Exception {
		return bugService.selectByPrimaryKey(serianum).getFounderid();
	}

	/**
	 * 获得指定人员的待处理Bug序列号集合
	 * 
	 * @param userId
	 * @return
	 */
	private List<Long> getSerianums(Long userId) throws Exception {
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsdisposeNotEqualTo(IrpBugWithBLOBs.ISDISPORE_OK);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		criteria.andFounderidEqualTo(LoginUtil.getLoginUserId());
		List<IrpBugWithBLOBs> bgs = bugService.selectByExample(example);
		List<Long> serianums = new ArrayList<Long>();
		for (IrpBugWithBLOBs bg : bgs) {
			serianums.add(bg.getSerianum());
		}
		return serianums;
	}

	/**
	 * 更新Bug dispose
	 * 
	 * @param
	 */
	private void updateIsdispose(Long disposeState) {
		try {
			IrpBugWithBLOBs newbug = new IrpBugWithBLOBs();
			newbug.setIsdispose(disposeState);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(newbug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 我创建的Bug
	 * 
	 * @return
	 */
	public String findMyfounderBug() {
		this.headType = "2";
		findBugForProject();
		return "page";
	}

	/**
	 * 我处理的Bug
	 * 
	 * @return
	 */
	public String findMyoperatorBug() {
		this.headType = "3";
		findBugForProject();
		return "page";
	}

	/**
	 * 我待办的Bug
	 * 
	 * @return
	 */
	public String findMywillBug() {
		this.headType = "4";
		findBugForProject();
		return "page";
	}

	/**
	 * 发送私信 创建
	 * 
	 * @return
	 */
	private void sendAddMessage(Long _cruserid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("在");
		sb.append(DateUtils.getDateByFormat(createTime, "yyyy-MM-dd HH:mm:ss"));
		sb.append("为您创建了新Bug");
		sb.append("<a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(createTime);
		mc.setMessagetype(1);
		messageService.addMessageContent(mc);
	}

	/**
	 * 发送私信 其他
	 * 
	 * @return
	 */
	private void sendOtherMessage(Long _cruserid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append("您有新的Bug动态");
		sb.append("<a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(createTime);
		mc.setMessagetype(1);
		messageService.addMessageContent(mc);
	}

	/**
	 * 发送私信 转发
	 * 
	 * @return
	 */
	private void sendForwardMessage(Long _cruserid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("将Bug");
		sb.append("<a <a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
		sb.append("转发给了您.");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(createTime);
		mc.setMessagetype(1);
		messageService.addMessageContent(mc);
	}

	/**
	 * 初始化我所在的项目
	 */
	private void findMyProjects() throws Exception {
		IrpProjectPerson pp = new IrpProjectPerson();
		IrpProjectPersonExample ppe = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = ppe
				.createCriteria();
		criteria.andPersonidEqualTo(LoginUtil.getLoginUserId());
		List<IrpProjectPerson> pplist = projectpersonservice
				.findPersonByExample(ppe);
		if (pplist.size() > 0) {
			List<Long> projectids = new ArrayList<Long>();
			for (IrpProjectPerson ele : pplist) {
				projectids.add(ele.getPrid());
			}
			IrpProjectExample pe = new IrpProjectExample();
			com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
					.createCriteria();
			criteria2.andProjectidIn(projectids);
			criteria2.andProjecttypeEqualTo(9l);
			irpProjects = projectService.selectByExample(pe);
		}
	}

	/**
	 * 查询添加bug的项目
	 * @throws Exception
	 */
	private void findAddProject() throws Exception {
		IrpProjectExample pe = new IrpProjectExample();
		com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
				.createCriteria();
		criteria2.andProjectidEqualTo(projectId);
		criteria2.andProjecttypeEqualTo(9l);
		irpProjects = projectService.selectByExample(pe);
	}
	
	/**
	 * 查询项目下所有Bug的数量
	 * 
	 * @return
	 */
	public static Integer bugCountForProject(Long proid) {
		Integer count = 0;
		try {
			ApplicationContext ac = ApplicationContextHelper.getContext();
			BugService service = (BugService) ac.getBean("bugService");
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andProjectidEqualTo(proid);
			criteria.andFreshnessEqualTo(-1l);
			count = service.countByExample(example);
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询项目下所有未修复的BUG
	 * 
	 * @return
	 */
	public static Integer bugCountForNotRepair(Long proid) {
		Integer count = 0;
		try {
			ApplicationContext ac = ApplicationContextHelper.getContext();
			BugService service = (BugService) ac.getBean("bugService");
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andProjectidEqualTo(proid);
			criteria.andFreshnessEqualTo(-1l);
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
			count = service.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询项目下所有待审核的BUG
	 * 
	 * @param proid
	 * @return
	 */
	public static Integer bugCountForNotAudit(Long proid) {
		Integer count = 0;
		try {
			ApplicationContext ac = ApplicationContextHelper.getContext();
			BugService service = (BugService) ac.getBean("bugService");
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andProjectidEqualTo(proid);
			criteria.andFreshnessEqualTo(-1l);
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
			count = service.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static Integer bugCountForResolved(Long proid) {
		Integer count = 0;
		try {
			ApplicationContext ac = ApplicationContextHelper.getContext();
			BugService service = (BugService) ac.getBean("bugService");
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andProjectidEqualTo(proid);
			criteria.andFreshnessEqualTo(-1l);
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
			count = service.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 获得该项目下所有Bug信息
	 */
	public String allbughome() {
		try {
			IrpBugExample bugExample = new IrpBugExample();
			Criteria criteria = bugExample.createCriteria();
			criteria.andProjectidEqualTo(projectId);
			criteria.andFreshnessEqualTo(-1l);
			bugExample.setOrderByClause(" PRIORITY DESC,BUGID  DESC");
			int dataCount = bugService.getDataCount(bugExample);
			PageUtil page = new PageUtil(pageNum, pageSize, dataCount);
			irpBugs = bugService.queryBugForPage(bugExample, page);
			this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
			List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());
			if (irpBugs.size() != 0) {
				for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 查询项目下所有版本
	 */
	private void findVersionByProid() {
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(this.projectId);
				criteria.andConfigtypeEqualTo(0l);
				example.setOrderByClause(" BUGCONFIGID");
				this.bugversions = bugconfigservice.selectByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询项目下所有模块
	 */
	private void findModulByProid() {
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(projectId);
				criteria.andConfigtypeEqualTo(1l);
				example.setOrderByClause(" BUGCONFIGID");
				this.bugmoduls = bugconfigservice.selectByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到总览页面
	 * 
	 * @return
	 */
	public String toPandectPage() {
		try {
			findUrgentBugsByProjectId();
			findNewestBugsByProjectId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 查询紧急bugs
	 */
	private void findUrgentBugsByProjectId() throws Exception {
		List<String> prilist = new ArrayList<String>();
		prilist.add("3");
		prilist.add("4");
		prilist.add("5");
		IrpBugExample example = new IrpBugExample();
		example.setOrderByClause("priority desc,createtime desc");
		Criteria criteria = example.createCriteria();
		criteria.andPriorityIn(prilist);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		if(!"".equals(projectId) && projectId!=null){
			criteria.andProjectidEqualTo(projectId);
			projectIdTop=projectId;
			projectIdBottom=projectId;
		}else if(!"".equals(projectIdTop) && projectIdTop!=null){
			criteria.andProjectidEqualTo(projectIdTop);
			projectId=projectIdTop;
			projectIdBottom=projectIdTop;
		}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
			criteria.andProjectidEqualTo(projectIdBottom);
			projectIdTop=projectIdBottom;
			projectId=projectIdBottom;
		}
		/**************************************************************/
		int dataCount = bugService.getDataCount(example);
		PageUtil page = new PageUtil(pageNumTop, pageSizeTop, dataCount);
		urgentBugs = bugService.queryBugForPage(example, page);
		if(urgentBugs.size()>0){
			this.pageHTMLTop = page.getClientPageHtml("pageNavigainTop(#PageNum#)");
		}
		
		/**************************************************************/
		for (IrpBugWithBLOBs ele : urgentBugs) {
			IrpUser user=userService.findUserByUserId(ele.getOperatorid());
			operatorNameMap.put(ele.getOperatorid(),
					user.getTruename());
		}
		/*List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpBugWithBLOBs ele : urgentBugs) {
			for (IrpUser element : userList) {
				if (ele.getOperatorid() != null
						&& !ele.getOperatorid().equals("")) {
					if (ele.getOperatorid() == Integer.parseInt(element
							.getUserid() + "")) {
						operatorNameMap.put(ele.getOperatorid(),
								element.getTruename());
						break;
					}
				}
			}
		}*/
		initPriority();
	}

	/**
	 * 查询最新bugs
	 */
	private void findNewestBugsByProjectId() throws Exception {
		IrpBugExample example = new IrpBugExample();
		example.setOrderByClause("priority desc,createtime desc");
		Criteria criteria = example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
	//	this.newestBugs = this.bugService.selectByExample(example);
		if(!"".equals(projectId) && projectId!=null){
			criteria.andProjectidEqualTo(projectId);
			projectIdTop=projectId;
			projectIdBottom=projectId;
		}else if(!"".equals(projectIdTop) && projectIdTop!=null){
			criteria.andProjectidEqualTo(projectIdTop);
			projectId=projectIdTop;
			projectIdBottom=projectIdTop;
		}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
			criteria.andProjectidEqualTo(projectIdBottom);
			projectIdTop=projectIdBottom;
			projectId=projectIdBottom;
		}
		example.setOrderByClause("priority desc,createtime desc");
		/**************************************************************/
		int dataCount = bugService.getDataCount(example);
		PageUtil page = new PageUtil(pageNumBottom, pageSizeBottom, dataCount);
		newestBugs = bugService.queryBugForPage(example, page);
		if(newestBugs.size()>0){
			this.pageHTMLBottom = page.getClientPageHtml("pageNavigainBottom(#PageNum#)");
		}
		
		/**************************************************************/
		/*List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpBugWithBLOBs ele : newestBugs) {
			for (IrpUser element : userList) {
				if (ele.getFounderid() == Integer.parseInt(element.getUserid()
						+ "")) {
					founderNameMap.put(ele.getFounderid(),
							element.getTruename());
					break;
				}
			}
		}*/
		for (IrpBugWithBLOBs ele : newestBugs) {
			IrpUser user=userService.findUserByUserId(ele.getFounderid());
			founderNameMap.put(ele.getFounderid(),
					user.getTruename());
		}
	}

	/**
	 * 初始化优先级
	 */
	private void initPriority() {
		this.priorityMap.put("1", "低");
		this.priorityMap.put("2", "中");
		this.priorityMap.put("3", "高");
		this.priorityMap.put("4", "紧急");
		this.priorityMap.put("5", "严重");
	}

	/**
	 * 跳转到分配给我的Bug
	 * 
	 * @return
	 */
	public String findBugForMe() {
		try {
			findMyBugsWei();
			findMyBugsWan();
			initPriority();
			this.vmNameMapInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 查询分配给我待处理的Bug
	 * 
	 * @throws Exception
	 */
	private void findMyBugsWei() throws Exception {
		List<Long> mebs = this.getSerianums(LoginUtil.getLoginUserId());
		if (mebs.size() > 0) {
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andBugidIn(mebs);
			if(!"".equals(projectId) && projectId!=null){
				criteria.andProjectidEqualTo(projectId);
				projectIdTop=projectId;
				projectIdBottom=projectId;
			}else if(!"".equals(projectIdTop) && projectIdTop!=null){
				criteria.andProjectidEqualTo(projectIdTop);
				projectId=projectIdTop;
				projectIdBottom=projectIdTop;
			}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
				criteria.andProjectidEqualTo(projectIdBottom);
				projectIdTop=projectIdBottom;
				projectId=projectIdBottom;
			}
			if(keyword !=null && !"".equals(keyword)){
				criteria.andTitleLike("%"+keyword+"%");
			}
			if(priority !=null && !"".equals(priority)){
				criteria.andPriorityEqualTo(priority);
			}
			criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
			example.setOrderByClause(ordertop);
			/**************************************************************/
			int dataCount = bugService.getDataCount(example);
			PageUtil page = new PageUtil(pageNumTop, pageSizeTop, dataCount);
			tomeBugsWei = bugService.queryBugForPage(example, page);
			if(tomeBugsWei.size()>0){
			this.pageHTMLTop = page.getClientPageHtml("pageNavigainTop(#PageNum#)");
			}
			/**************************************************************/
			//this.tomeBugsWei = bugService.selectByExample(example);
			/*List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());*/
			if (tomeBugsWei.size() != 0) {
				/*for (IrpBugWithBLOBs ele : tomeBugsWei) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
				for (IrpBugWithBLOBs ele : tomeBugsWei) {
					if (ele.getOperatorid() != null) {
						for (IrpUser element : userList) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}*/
				for (IrpBugWithBLOBs ele : tomeBugsWei) {
					IrpUser user=userService.findUserByUserId(ele.getFounderid());
					founderNameMap.put(ele.getFounderid(),
							user.getTruename());
				}
				for (IrpBugWithBLOBs ele : tomeBugsWei) {
					IrpUser user=userService.findUserByUserId(ele.getOperatorid());
					operatorNameMap.put(ele.getOperatorid(),
							user.getTruename());
				}
			}
		} else {
			tomeBugsWei = null;
		}
	}

	/**
	 * 查询分配给我完成的bug
	 * 
	 * @throws Exception
	 */
	private void findMyBugsWan() throws Exception {
		List<Long> mebs = this.findSerianumsMyWan();
		if (mebs.size() > 0) {
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andBugidIn(mebs);
			if(!"".equals(projectId) && projectId!=null){
				criteria.andProjectidEqualTo(projectId);
				projectIdTop=projectId;
				projectIdBottom=projectId;
			}else if(!"".equals(projectIdTop) && projectIdTop!=null){
				criteria.andProjectidEqualTo(projectIdTop);
				projectId=projectIdTop;
				projectIdBottom=projectIdTop;
			}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
				criteria.andProjectidEqualTo(projectIdBottom);
				projectIdTop=projectIdBottom;
				projectId=projectIdBottom;
			}
			if(keyword !=null && !"".equals(keyword)){
				criteria.andTitleLike("%"+keyword+"%");
			}
			if(priority !=null && !"".equals(priority)){
				criteria.andPriorityEqualTo(priority);
			}
			example.setOrderByClause(orderbottom);
			/**************************************************************/
			int dataCount = bugService.getDataCount(example);
			PageUtil page = new PageUtil(pageNumBottom, pageSizeBottom, dataCount);
			tomeBugsWan = bugService.queryBugForPage(example, page);
			if(tomeBugsWan.size()>0){
				this.pageHTMLBottom = page.getClientPageHtml("pageNavigainBottom(#PageNum#)");
			}
			/**************************************************************/
			/*List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());*/
			if (tomeBugsWan.size() != 0) {
				/*for (IrpBugWithBLOBs ele : tomeBugsWan) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								meFounderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				for (IrpBugWithBLOBs ele : tomeBugsWan) {
					if (ele.getOperatorid() != null) {
						for (IrpUser element : userList) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}*/
				for (IrpBugWithBLOBs ele : tomeBugsWan) {
					IrpUser user=userService.findUserByUserId(ele.getFounderid());
					meFounderNameMap.put(ele.getFounderid(),
							user.getTruename());
				}
				for (IrpBugWithBLOBs ele : tomeBugsWan) {
					IrpUser user=userService.findUserByUserId(ele.getOperatorid());
					operatorNameMap.put(ele.getOperatorid(),
							user.getTruename());
				}
			}
		} else {
			tomeBugsWan = null;
		}
	}

	/**
	 * 获得我已经完成bug的序号集合
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<Long> findSerianumsMyWan() throws Exception {
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andOperatoridEqualTo(LoginUtil.getLoginUserId());
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		example.setOrderByClause("priority desc,createtime desc");
		List<IrpBugWithBLOBs> bgs = bugService.selectByExample(example);
		List<Long> serianums = new ArrayList<Long>();
		for (IrpBugWithBLOBs bg : bgs) {
			serianums.add(bg.getSerianum());
		}
		return serianums;
	}

	/**
	 * 跳转到我创建的Bug
	 * 
	 * @return
	 */
	public String meToBugPage() {
		
			try {
				IrpBugExample example = new IrpBugExample();
				Criteria criteria = example.createCriteria();
				criteria.andProjectidEqualTo(projectId);
				criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
				criteria.andFounderidEqualTo(LoginUtil.getLoginUserId());
				example.setOrderByClause("createtime");
				if(keyword !=null && !"".equals(keyword)){
					criteria.andTitleLike("%"+keyword+"%");
				}
				if(priority !=null && !"".equals(priority)){
					criteria.andPriorityEqualTo(priority);
				}
				if(state !=null && !"".equals(state)){
					if("1".equals(state)){
						criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
					}else if("2".equals(state)){
						criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
					}else if("3".equals(state)){
						criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
					}
				}
				if(!"".equals(order)){
					example.setOrderByClause(order);
				}
				/**************************************************************/
				int dataCount = bugService.getDataCount(example);
				PageUtil page = new PageUtil(pageNum, pageSize, dataCount);
				irpBugs = bugService.queryBugForPage(example, page);
				if(irpBugs.size()>0){
					this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
				}
				/**************************************************************/
				/*List<IrpUser> userList = userService
						.findUserByExample(new IrpUserExample());*/
				if (irpBugs.size() != 0) {
					/*for (IrpBugWithBLOBs ele : irpBugs) {
						if (ele.getFounderid() != null) {
							for (IrpUser element : userList) {
								if (ele.getFounderid() == Integer.parseInt(element
										.getUserid() + "")) {
									founderNameMap.put(ele.getFounderid(),
											element.getTruename());
									break;
								}
							}
						}

					}
					for (IrpBugWithBLOBs ele : irpBugs) {
						if (ele.getOperatorid() != null) {
							for (IrpUser element : userList) {
								if (ele.getOperatorid() == Integer.parseInt(element
										.getUserid() + "")) {
									operatorNameMap.put(ele.getOperatorid(),
											element.getTruename());
									break;
								}
							}
						}
					}*/
					for (IrpBugWithBLOBs ele : irpBugs) {
						IrpUser user=userService.findUserByUserId(ele.getFounderid());
						founderNameMap.put(ele.getFounderid(),
								user.getTruename());
					}
					for (IrpBugWithBLOBs ele : irpBugs) {
						IrpUser user=userService.findUserByUserId(ele.getOperatorid());
						operatorNameMap.put(ele.getOperatorid(),
								user.getTruename());
					}
				}
				this.initPriority();
				this.vmNameMapInit();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return SUCCESS;
	}
	
	/**
	 * 初始化版本模块名字信息
	 */
	private void vmNameMapInit(){
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(this.projectId);
				List<IrpBugConfig> bclist=this.bugconfigservice.selectByExample(example);
				if(bclist.size()>0){
					for(IrpBugConfig ele :bclist){
						if(ele.getConfigtype().equals(IrpBugConfig.TYPE_VERSION)){
							this.versionNameMap.put(ele.getBugconfigid(), ele.getVersionname());
						}else if(ele.getConfigtype().equals(IrpBugConfig.TYPE_MODUL)){
							this.modulNameMap.put(ele.getBugconfigid(), ele.getModulname());
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String token;
	private String tokens;
	public String getTokens() {
		return token;
	}

	public void setTokens(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * 跳转到Bug修改页面
	 * @return
	 */
	public String loadToEditorApp(){
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String token = request.getParameter("token");
				tokens=token;
				IrpUser irpuser = mobileAction.getlogin(token);
				IrpBugExample example = new IrpBugExample();
				example.setOrderByClause("BUGID");
				Criteria criteria = example.createCriteria();
				criteria.andSerianumEqualTo(serianum);
				irpBugs = bugService.selectByExample(example);
				this.lastBugState = irpBugs.get(irpBugs.size() - 1).getState();
				// bug=bugService.selectByPrimaryKey(bugId);
				// founderTrueName=userService.findUserByUserId(bug.getFounderid()).getTruename();
				getProjectUser(irpBugs.get(0).getProjectid(), true);
				List<IrpUser> userList = userService
						.findUserByExample(new IrpUserExample());
				for (IrpUser ele : projectUsers) {
					for (IrpUser element : userList) {
						if (ele.getUserid()!= null
								&& !ele.getUserid().equals("")) {
							if (ele.getUserid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getUserid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				project = projectService.findProjectById(irpBugs.get(0)
						.getProjectid());

				attacheds = this.irpAttachedService.getAttachedByAttDocId(serianum,
						IrpAttached.BUGDOCTYPEID);

				// 获取项目成员,不包含自己
				
				this.projectId=irpBugs.get(0).getProjectid();
				this.findVersionByProid();
				this.findModulByProid();
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return "loadtoeditor";
	}
	/**
	 * 
	 * 跳转到Bug修改页面
	 * @return
	 */
	public String loadToEditor(){
			try {
				IrpBugExample example = new IrpBugExample();
				example.setOrderByClause("BUGID");
				Criteria criteria = example.createCriteria();
				criteria.andSerianumEqualTo(serianum);
				irpBugs = bugService.selectByExample(example);
				this.lastBugState = irpBugs.get(irpBugs.size() - 1).getState();
				// bug=bugService.selectByPrimaryKey(bugId);
				// founderTrueName=userService.findUserByUserId(bug.getFounderid()).getTruename();
				getProjectUser(irpBugs.get(0).getProjectid(), true);
				List<IrpUser> userList = userService
						.findUserByExample(new IrpUserExample());
				for (IrpUser ele : projectUsers) {
					for (IrpUser element : userList) {
						if (ele.getUserid()!= null
								&& !ele.getUserid().equals("")) {
							if (ele.getUserid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getUserid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				project = projectService.findProjectById(irpBugs.get(0)
						.getProjectid());

				attacheds = this.irpAttachedService.getAttachedByAttDocId(serianum,
						IrpAttached.BUGDOCTYPEID);

				// 获取项目成员,不包含自己
				
				this.projectId=irpBugs.get(0).getProjectid();
				this.findVersionByProid();
				this.findModulByProid();
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return "loadtoeditor";
	}
	
	/**
	 * 修改保存bug
	 * @return
	 */
	public void saveBug(){
			try {
				IrpBugWithBLOBs ib = bugService.selectByPrimaryKey(serianum);
				ib.setTitle(bugTitle);
				ib.setPriority(this.priority);
				ib.setDescribe(describe);
				ib.setVersionid(versionid);
				ib.setModuleid(modulid);
				IrpBugExample example = new IrpBugExample();
				Criteria criteria = example.createCriteria();
				criteria.andBugidEqualTo(serianum);
				bugService.updateByExampleSelective(ib, example);
				saveBugWill();
				//删除附件
				if(attachedids !=null && !"".equals(attachedids)){
					List<Long> attaIdList=new ArrayList<Long>();
					String[] attaStr=attachedids.split(",");
					for(int i=0;i<attaStr.length;i++){
						attaIdList.add(Long.parseLong((attaStr[i]).trim()));
					}
					irpAttachedService.deleteFileInList(attaIdList, serianum, IrpAttached.BUGDOCTYPEID);
				}
				
				if (jsonFile != null && jsonFile != "") {
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
								typeid, this.serianum, sattdesc, sattlinkalt, seditversions,
								false, null, false);

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 修改处理人
	 * @throws Exception
	 */
	private void saveBugWill()throws Exception{
		IrpBugWithBLOBs ib=findWillBug();
		Long oldOID=ib.getFounderid();
		ib.setFounderid(operatorId);
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andSerianumEqualTo(serianum);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		bugService.updateByExampleSelective(ib, example);
		if(!operatorId.equals(oldOID)){
			sendForwardMessage(operatorId);
		}
		
	}
	
	/**
	 * 根据serianum删除Bug
	 */
	public void delBugBySu(){
		try {
			IrpBugExample example=new IrpBugExample();
			Criteria criteria=example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*************************************数据统计*************************************/
	
	/**
	 * 跳转到Bug统计页面
	 * @return
	 */
	public String toStatisticsPage(){
		return SUCCESS;
	}
	
	/**
	 * 查询统计数据
	 * @return
	 */
	public String initBugStatistics(){
		try {
			initBugRenyuanStatistics();
			countForState();
			countForModul();
			fountForfenbu();
			countForZoushi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 统计人员分布情况
	 */
	private void initBugRenyuanStatistics(){
		this.getProjectUser(projectId,true);
		List<String> projectPersonStr=new ArrayList<String>();
		for(IrpUser ele: projectUsers){
			projectPersonStr.add(ele.getTruename());
		}
		this.projectPersonsJson=JsonUtil.list2json(projectPersonStr);
	}
	
	/**
	 * 获得指定时间范围内创建的BugID集合
	 * @return
	 * @throws Exception
	 */
	private List<Long> getIdsByDate()throws Exception{
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		//criteria.andCreatetimeBetween(this.startDate, this.endDate);
		criteria.andProjectidEqualTo(projectId);
		List<IrpBugWithBLOBs> bs=bugService.selectByExample(example);
		List<Long> ids =new ArrayList<Long>();
		for(IrpBugWithBLOBs ib:bs){
			ids.add(ib.getBugid());
		}
		return ids;
	}
	
	/**
	 * 统计项目内每个成员未完/完成/待审核Bug的数量
	 * @throws Exception
	 */
	private void countForState() throws Exception{
		List<Integer> weiBugs=new ArrayList<Integer>();
		List<Integer> wanBugs=new ArrayList<Integer>();
		List<Integer> shenBugs=new ArrayList<Integer>();
		IrpBugExample example=new IrpBugExample();
		List<Long> ss=this.getIdsByDate();
		for(IrpUser ele:projectUsers){
			example.clear();
			int wei=0;
			int wan=0;
			int shen=0;
			Criteria criteria=example.createCriteria();
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			criteria.andFounderidEqualTo(ele.getId());
			criteria.andProjectidEqualTo(projectId);
			if(ss.size()>0){
				criteria.andSerianumIn(ss);
			}else{
				criteria.andBugidIsNull();
			}
			
			List<IrpBugWithBLOBs> bugs=this.bugService.selectByExample(example);
			for(IrpBugWithBLOBs bug:bugs){
				if(IrpBugWithBLOBs.STATE_WEIWAN.equals(bug.getNeweststate())){
					wei++;
				}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(bug.getNeweststate())){
					wan++;
				}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(bug.getNeweststate())){
					shen++;
				}
			}
			weiBugs.add(wei);
			wanBugs.add(wan);
			shenBugs.add(shen);
		}
		this.weiBugsJson=JsonUtil.list2json(weiBugs);
		this.wanBugsJson=JsonUtil.list2json(wanBugs);
		this.shenBugsJson=JsonUtil.list2json(shenBugs);
	}
	
	/**
	 * 统计不同模块bug数量     已解决/待审核/未解决
	 */
	private void countForModul()throws Exception{
		List<Integer> mweiBugs=new ArrayList<Integer>();
		List<Integer> mwanBugs=new ArrayList<Integer>();
		List<Integer> mshenBugs=new ArrayList<Integer>();
		List<String>  bugsForModul=new ArrayList<String>();
		findModulByProid();
		bugsForModul.add("默认模块");
		for(IrpBugConfig ibc:bugmoduls){
			bugsForModul.add(ibc.getModulname());
		}
		this.bugsForModulJson=JsonUtil.list2json(bugsForModul);
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andModuleidIsNull();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		List<IrpBugWithBLOBs> mobugs=bugService.selectByExample(example);
		int mwei=0;
		int mwan=0;
		int mshen=0;
		for(IrpBugWithBLOBs el:mobugs){
			if(IrpBugWithBLOBs.STATE_WEIWAN.equals(el.getNeweststate())){
				mwei++;
			}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(el.getNeweststate())){
				mwan++;
			}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(el.getNeweststate())){
				mshen++;
			}
		}
		mweiBugs.add(mwei);
		mwanBugs.add(mwan);
		mshenBugs.add(mshen);
		for(IrpBugConfig ele :bugmoduls){
			int wei=0;
			int wan=0;
			int shen=0;
			example.clear();
			criteria=example.createCriteria();
			criteria.andModuleidEqualTo(ele.getBugconfigid());
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			List<IrpBugWithBLOBs> bgs=bugService.selectByExample(example);
			for(IrpBugWithBLOBs el:bgs){
				if(IrpBugWithBLOBs.STATE_WEIWAN.equals(el.getNeweststate())){
					wei++;
				}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(el.getNeweststate())){
					wan++;
				}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(el.getNeweststate())){
					shen++;
				}
			}
			mweiBugs.add(wei);
			mwanBugs.add(wan);
			mshenBugs.add(shen);
		}
		
		this.mweiBugsJson=JsonUtil.list2json(mweiBugs);
		this.mwanBugsJson=JsonUtil.list2json(mwanBugs);
		this.mshenBugsJson=JsonUtil.list2json(mshenBugs);
		
	}
	
	/**
	 * 分布区域统计
	 * @throws Exception
	 */
	private void fountForfenbu() throws Exception{
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andProjectidEqualTo(projectId);
		criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		List<IrpBugWithBLOBs> fbgs=bugService.selectByExample(example);
		List<String> fenbu1List =new ArrayList<String>();
		Map yan = new HashMap();
		Map jin = new HashMap();
		Map gao = new HashMap();
		Map zhong = new HashMap();
		Map di = new HashMap();
		Map wan = new HashMap();
		Map wei = new HashMap();
		Map shen = new HashMap();
		int nyan = 0;
		int njin = 0;
		int ngao = 0;
		int nzhong = 0;
		int ndi = 0;
		int nwei =0;
		int nwan =0;
		int nshen = 0;
		for(IrpBugWithBLOBs bg:fbgs){
			if(IrpBugWithBLOBs.PRIORITY_YAN.equals(bg.getPriority())){
				nyan++;
			}else if(IrpBugWithBLOBs.PRIORITY_JIN.equals(bg.getPriority())){
				njin++;
			}else if(IrpBugWithBLOBs.PRIORITY_GAO.equals(bg.getPriority())){
				ngao++;
			}else if(IrpBugWithBLOBs.PRIORITY_ZHONG.equals(bg.getPriority())){
				nzhong++;
			}else if(IrpBugWithBLOBs.PRIORITY_DI.equals(bg.getPriority())){
				ndi++;
			}
		}
		for(IrpBugWithBLOBs bg:fbgs){
			if(IrpBugWithBLOBs.STATE_WEIWAN.equals(bg.getNeweststate())){
				nwei++;
			}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(bg.getNeweststate())){
				nshen++;
			}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(bg.getNeweststate())){
				nwan++;
			}
		}
		yan.put("严重", nyan);
		jin.put("紧急", njin);
		gao.put("高", ngao);
		zhong.put("中", nzhong);
		di.put("低", ndi);
		wei.put("未修复", nwei);
		shen.put("待审核", nshen);
		wan.put("已解决", nwan);
		this.fenbu1.add(yan);
		this.fenbu1.add(jin);
		this.fenbu1.add(gao);
		this.fenbu1.add(zhong);
		this.fenbu1.add(di);
		this.fenbu2.add(wei);
		this.fenbu2.add(shen);
		this.fenbu2.add(wan);
	}
	
	/**
	 * 查询Bug总量走势
	 * @throws Exception
	 */
	private void countForZoushi()throws Exception{
		List<java.util.Date> zoushiDateList=DateUtils.getDatesBetweenTwoDate(this.startDate, this.endDate);
		List<String>zoushiD=new ArrayList<String>();
		for(Date d:zoushiDateList){
			zoushiD.add(DateUtils.getDateByFormat(d, DateUtils.YMD_FORMAT));
		}
		List<Integer> zoushiCountList=new ArrayList<Integer>();
		for(java.util.Date dat:zoushiDateList){
			IrpBugExample example=new IrpBugExample();
			Criteria criteria=example.createCriteria();
			//criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(dat, Calendar.DATE, 1));
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			criteria.andProjectidEqualTo(projectId);
			//criteria.andCreatetimeBetween(this.startDate, this.endDate);
			zoushiCountList.add(bugService.countByExample(example));
		}
		this.zoushiDate=JsonUtil.list2json(zoushiD);
		this.zoushiCount=JsonUtil.list2json(zoushiCountList);
	}
	
	/*************************************数据统计*************************************/
	
	

	public String getIspublish() {
		return ispublish;
	}

	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}

	public IrpProject getIrpProject() {
		return irpProject;
	}

	public void setIrpProject(IrpProject irpProject) {
		this.irpProject = irpProject;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public List<IrpBugWithBLOBs> getIrpBugs() {
		return irpBugs;
	}

	public void setIrpBugs(List<IrpBugWithBLOBs> irpBugs) {
		this.irpBugs = irpBugs;
	}

	public IrpBugWithBLOBs getBug() {
		return bug;
	}

	public void setBug(IrpBugWithBLOBs bug) {
		this.bug = bug;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public Map<Long, String> getFounderNameMap() {
		return founderNameMap;
	}

	public void setFounderNameMap(Map<Long, String> founderNameMap) {
		this.founderNameMap = founderNameMap;
	}

	public Map<Long, String> getOperatorNameMap() {
		return operatorNameMap;
	}

	public void setOperatorNameMap(Map<Long, String> operatorNameMap) {
		this.operatorNameMap = operatorNameMap;
	}

	/**
	 * 配合页面属性
	 * 
	 * @return
	 */

	private String ispublish;

	private IrpProject irpProject;

	public Long getBugId() {
		return bugId;
	}

	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public Long getFounderId() {
		return founderId;
	}

	public void setFounderId(Long founderId) {
		this.founderId = founderId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getRequiredTime() {
		return requiredTime;
	}

	public void setRequiredTime(java.util.Date requiredTime) {
		this.requiredTime = requiredTime;
	}

	public java.util.Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getPrintScreen() {
		return printScreen;
	}

	public void setPrintScreen(String printScreen) {
		this.printScreen = printScreen;
	}

	public Long getFreshness() {
		return freshness;
	}

	public void setFreshness(Long freshness) {
		this.freshness = freshness;
	}

	public Long getIsdispose() {
		return isdispose;
	}

	public void setIsdispose(Long isdispose) {
		this.isdispose = isdispose;
	}

	public BugService getBugService() {
		return bugService;
	}

	public void setBugService(BugService bugService) {
		this.bugService = bugService;
	}

	public IrpUserService getUserService() {
		return userService;
	}

	public void setUserService(IrpUserService userService) {
		this.userService = userService;
	}

	public String getFounderTrueName() {
		return founderTrueName;
	}

	public void setFounderTrueName(String founderTrueName) {
		this.founderTrueName = founderTrueName;
	}

	public String getOperatorTrueName() {
		return operatorTrueName;
	}

	public void setOperatorTrueName(String operatorTrueName) {
		this.operatorTrueName = operatorTrueName;
	}

	public IrpProject getProject() {
		return project;
	}

	public void setProject(IrpProject project) {
		this.project = project;
	}

	public Long getSerianum() {
		return serianum;
	}

	public void setSerianum(Long serianum) {
		this.serianum = serianum;
	}

	public IrpProjectService getProjectService() {
		return projectService;
	}

	public List<IrpUser> getProjectUsers() {
		return projectUsers;
	}

	public void setProjectUsers(List<IrpUser> projectUsers) {
		this.projectUsers = projectUsers;
	}

	public void setProjectService(IrpProjectService projectService) {
		this.projectService = projectService;
	}

	public IrpProjectPersonService getProjectpersonservice() {
		return projectpersonservice;
	}

	public void setProjectpersonservice(
			IrpProjectPersonService projectpersonservice) {
		this.projectpersonservice = projectpersonservice;
	}

	public int getDowhat() {
		return dowhat;
	}

	public void setDowhat(int dowhat) {
		this.dowhat = dowhat;
	}

	public Long getDisposeUserId() {
		return disposeUserId;
	}

	public void setDisposeUserId(Long disposeUserId) {
		this.disposeUserId = disposeUserId;
	}

	public String getBugComment() {
		return bugComment;
	}

	public void setBugComment(String bugComment) {
		this.bugComment = bugComment;
	}

	public String getLastBugState() {
		return lastBugState;
	}

	public void setLastBugState(String lastBugState) {
		this.lastBugState = lastBugState;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getStateType() {
		return stateType;
	}

	public void setIrpProjects(List<IrpProject> irpProjects) {
		this.irpProjects = irpProjects;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IrpMessageContentService getMessageService() {
		return messageService;
	}

	public void setMessageService(IrpMessageContentService messageService) {
		this.messageService = messageService;
	}

	public List<IrpProject> getIrpProjects() {
		return irpProjects;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public int getIsall() {
		return isall;
	}

	public void setIsall(int isall) {
		this.isall = isall;
	}

	public List<IrpBugConfig> getBugversions() {
		return bugversions;
	}

	public void setBugversions(List<IrpBugConfig> bugversions) {
		this.bugversions = bugversions;
	}

	public List<IrpBugConfig> getBugmoduls() {
		return bugmoduls;
	}

	public void setBugmoduls(List<IrpBugConfig> bugmoduls) {
		this.bugmoduls = bugmoduls;
	}

	public BugConfigService getBugconfigservice() {
		return bugconfigservice;
	}

	public void setBugconfigservice(BugConfigService bugconfigservice) {
		this.bugconfigservice = bugconfigservice;
	}

	public int getSeltype() {
		return seltype;
	}

	public void setSeltype(int seltype) {
		this.seltype = seltype;
	}

	public Long getVersionid() {
		return versionid;
	}

	public void setVersionid(Long versionid) {
		this.versionid = versionid;
	}

	public Long getModulid() {
		return modulid;
	}

	public void setModulid(Long modulid) {
		this.modulid = modulid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileTrueName() {
		return fileTrueName;
	}

	public void setFileTrueName(String fileTrueName) {
		this.fileTrueName = fileTrueName;
	}

	public List<IrpBugWithBLOBs> getUrgentBugs() {
		return urgentBugs;
	}

	public void setUrgentBugs(List<IrpBugWithBLOBs> urgentBugs) {
		this.urgentBugs = urgentBugs;
	}

	public List<IrpBugWithBLOBs> getNewestBugs() {
		return newestBugs;
	}

	public void setNewestBugs(List<IrpBugWithBLOBs> newestBugs) {
		this.newestBugs = newestBugs;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	private IrpAttachedService irpAttachedService;

	private IrpAttachedTypeService irpAttachedTypeService;

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public Map<String, String> getPriorityMap() {
		return priorityMap;
	}

	public void setPriorityMap(Map<String, String> priorityMap) {
		this.priorityMap = priorityMap;
	}

	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}

	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}

	public List<IrpBugWithBLOBs> getTomeBugsWei() {
		return tomeBugsWei;
	}

	public void setTomeBugsWei(List<IrpBugWithBLOBs> tomeBugsWei) {
		this.tomeBugsWei = tomeBugsWei;
	}

	public List<IrpBugWithBLOBs> getTomeBugsWan() {
		return tomeBugsWan;
	}

	public void setTomeBugsWan(List<IrpBugWithBLOBs> tomeBugsWan) {
		this.tomeBugsWan = tomeBugsWan;
	}

	public Map<Long, String> getMeFounderNameMap() {
		return meFounderNameMap;
	}

	public void setMeFounderNameMap(Map<Long, String> meFounderNameMap) {
		this.meFounderNameMap = meFounderNameMap;
	}

	public Map<Long, String> getMeOperatorNameMap() {
		return meOperatorNameMap;
	}

	public void setMeOperatorNameMap(Map<Long, String> meOperatorNameMap) {
		this.meOperatorNameMap = meOperatorNameMap;
	}

	public String getPageHTMLTop() {
		return pageHTMLTop;
	}

	public void setPageHTMLTop(String pageHTMLTop) {
		this.pageHTMLTop = pageHTMLTop;
	}

	public int getPageNumTop() {
		return pageNumTop;
	}

	public void setPageNumTop(int pageNumTop) {
		this.pageNumTop = pageNumTop;
	}

	public int getPageSizeTop() {
		return pageSizeTop;
	}

	public void setPageSizeTop(int pageSizeTop) {
		this.pageSizeTop = pageSizeTop;
	}

	public String getQueryTypeTop() {
		return queryTypeTop;
	}

	public void setQueryTypeTop(String queryTypeTop) {
		this.queryTypeTop = queryTypeTop;
	}

	public String getPageHTMLBottom() {
		return pageHTMLBottom;
	}

	public void setPageHTMLBottom(String pageHTMLBottom) {
		this.pageHTMLBottom = pageHTMLBottom;
	}

	public int getPageNumBottom() {
		return pageNumBottom;
	}

	public void setPageNumBottom(int pageNumBottom) {
		this.pageNumBottom = pageNumBottom;
	}

	public int getPageSizeBottom() {
		return pageSizeBottom;
	}

	public void setPageSizeBottom(int pageSizeBottom) {
		this.pageSizeBottom = pageSizeBottom;
	}

	public String getQueryTypeBottom() {
		return queryTypeBottom;
	}

	public void setQueryTypeBottom(String queryTypeBottom) {
		this.queryTypeBottom = queryTypeBottom;
	}

	public Long getProjectIdTop() {
		return projectIdTop;
	}

	public void setProjectIdTop(Long projectIdTop) {
		this.projectIdTop = projectIdTop;
	}

	public Long getProjectIdBottom() {
		return projectIdBottom;
	}

	public void setProjectIdBottom(Long projectIdBottom) {
		this.projectIdBottom = projectIdBottom;
	}

	public String getNstate() {
		return nstate;
	}

	public void setNstate(String nstate) {
		this.nstate = nstate;
	}

	public Map<Long, String> getVersionNameMap() {
		return versionNameMap;
	}

	public void setVersionNameMap(Map<Long, String> versionNameMap) {
		this.versionNameMap = versionNameMap;
	}

	public Map<Long, String> getModulNameMap() {
		return modulNameMap;
	}

	public void setModulNameMap(Map<Long, String> modulNameMap) {
		this.modulNameMap = modulNameMap;
	}

	public int getIssendmessage() {
		return issendmessage;
	}

	public void setIssendmessage(int issendmessage) {
		this.issendmessage = issendmessage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrdertop() {
		return ordertop;
	}

	public void setOrdertop(String ordertop) {
		this.ordertop = ordertop;
	}

	public String getOrdertoptype() {
		return ordertoptype;
	}

	public void setOrdertoptype(String ordertoptype) {
		this.ordertoptype = ordertoptype;
	}

	public String getOrderbottom() {
		return orderbottom;
	}

	public void setOrderbottom(String orderbottom) {
		this.orderbottom = orderbottom;
	}

	public String getOrderbottomtype() {
		return orderbottomtype;
	}

	public void setOrderbottomtype(String orderbottomtype) {
		this.orderbottomtype = orderbottomtype;
	}

	public String getProjectPersonsJson() {
		return projectPersonsJson;
	}

	public void setProjectPersonsJson(String projectPersonsJson) {
		this.projectPersonsJson = projectPersonsJson;
	}

	public String getBugsForModulJson() {
		return bugsForModulJson;
	}

	public void setBugsForModulJson(String bugsForModulJson) {
		this.bugsForModulJson = bugsForModulJson;
	}

	public String getBugsForPersonJson() {
		return bugsForPersonJson;
	}

	public void setBugsForPersonJson(String bugsForPersonJson) {
		this.bugsForPersonJson = bugsForPersonJson;
	}

	public String getWeiBugsJson() {
		return weiBugsJson;
	}

	public void setWeiBugsJson(String weiBugsJson) {
		this.weiBugsJson = weiBugsJson;
	}

	public String getShenBugsJson() {
		return shenBugsJson;
	}

	public void setShenBugsJson(String shenBugsJson) {
		this.shenBugsJson = shenBugsJson;
	}

	public String getWanBugsJson() {
		return wanBugsJson;
	}

	public void setWanBugsJson(String wanBugsJson) {
		this.wanBugsJson = wanBugsJson;
	}

	public String getMweiBugsJson() {
		return mweiBugsJson;
	}

	public void setMweiBugsJson(String mweiBugsJson) {
		this.mweiBugsJson = mweiBugsJson;
	}

	public String getMshenBugsJson() {
		return mshenBugsJson;
	}

	public void setMshenBugsJson(String mshenBugsJson) {
		this.mshenBugsJson = mshenBugsJson;
	}

	public String getMwanBugsJson() {
		return mwanBugsJson;
	}

	public void setMwanBugsJson(String mwanBugsJson) {
		this.mwanBugsJson = mwanBugsJson;
	}

	public List<Map> getFenbu1() {
		return fenbu1;
	}

	public void setFenbu1(List<Map> fenbu1) {
		this.fenbu1 = fenbu1;
	}

	public List<Map> getFenbu2() {
		return fenbu2;
	}

	public void setFenbu2(List<Map> fenbu2) {
		this.fenbu2 = fenbu2;
	}

	public String getZoushiDate() {
		return zoushiDate;
	}

	public void setZoushiDate(String zoushiDate) {
		this.zoushiDate = zoushiDate;
	}

	public String getZoushiCount() {
		return zoushiCount;
	}

	public void setZoushiCount(String zoushiCount) {
		this.zoushiCount = zoushiCount;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public String getAttachedids() {
		return attachedids;
	}

	public void setAttachedids(String attachedids) {
		this.attachedids = attachedids;
	}

	public Long getTabPid() {
		return tabPid;
	}

	public void setTabPid(Long tabPid) {
		this.tabPid = tabPid;
	}

	public int getIsprojectcre() {
		return isprojectcre;
	}

	public void setIsprojectcre(int isprojectcre) {
		this.isprojectcre = isprojectcre;
	}

	
}
