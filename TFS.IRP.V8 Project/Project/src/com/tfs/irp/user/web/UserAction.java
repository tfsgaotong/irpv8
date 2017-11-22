package com.tfs.irp.user.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.struts2.ServletActionContext;

import weibo4j.Account;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.util.WeiboConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.expert.service.IrpExpertClassifyLinkService;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.EncryptUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class UserAction extends ActionSupport {

	private List<IrpUser> irpUsers;
	
	private List<IrpRole> irpSysRoles;
	
	private List<IrpRole> irpSpeRoles;
	
	private IrpUserService irpUserService;
	
	private IrpGroupService irpGroupService;
	
	private IrpRoleService irpRoleService;
	
	private IrpUserPrivacyService irpUserPrivacyService;
	
	private IrpCategoryService irpCategoryService;
	
	private IrpExpertClassifyLinkService irpExpertClassifyLinkService;
	
	private IrpMicroblogFocusService irpMicroblogFocusService;
	
    private Map<Long, String> groupsInfo;
	
	private Map<Long, String> categorysInfo;
	
	private long userId;
	
	private String userIds;
	
	private String groupIds;
	
	private String roleIds;
	
	private IrpUser irpUser;
	
	private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private String passWord;
	
	private String userName;
	
	private String searchWord;
	
	private String searchType;
	
	private String userPicUrl;
	
	private int pageSizeclient=15;
    private String sname;
    private String showway;
    
    private String oauthLoginUrl;
    
	private String type;
	
	private String code;
	
	private String classifyIds;
	
	private String experttype;
	
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
        return irpMicroblogFocusService;
    }

    public void setIrpMicroblogFocusService(IrpMicroblogFocusService irpMicroblogFocusService) {
        this.irpMicroblogFocusService = irpMicroblogFocusService;
    }
	
	public String getExperttype() {
		return experttype;
	}

	public void setExperttype(String experttype) {
		this.experttype = experttype;
	}

	public IrpExpertClassifyLinkService getIrpExpertClassifyLinkService() {
		return irpExpertClassifyLinkService;
	}

	public void setIrpExpertClassifyLinkService(
			IrpExpertClassifyLinkService irpExpertClassifyLinkService) {
		this.irpExpertClassifyLinkService = irpExpertClassifyLinkService;
	}

	public String getClassifyIds() {
		return classifyIds;
	}

	public void setClassifyIds(String classifyIds) {
		this.classifyIds = classifyIds;
	}

	public Map<Long, String> getCategorysInfo() {
		return categorysInfo;
	}

	public void setCategorysInfo(Map<Long, String> categorysInfo) {
		this.categorysInfo = categorysInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}

	public String getOauthLoginUrl() {
		return oauthLoginUrl;
	}

	public void setOauthLoginUrl(String oauthLoginUrl) {
		this.oauthLoginUrl = oauthLoginUrl;
	}

	public String getShowway() {
		return showway;
	}

	public void setShowway(String showway) {
		this.showway = showway;
	}
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getPageSizeclient() {
		return pageSizeclient;
	}

	public void setPageSizeclient(int pageSizeclient) {
		this.pageSizeclient = pageSizeclient;
	}

	public String getUserPicUrl() {
		return userPicUrl;
	}

	  
	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord.trim();
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Map<Long, String> getGroupsInfo() {
		return groupsInfo;
	}

	public void setGroupsInfo(Map<Long, String> groupsInfo) {
		this.groupsInfo = groupsInfo;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<IrpRole> getIrpSysRoles() {
		return irpSysRoles;
	}

	public void setIrpSysRoles(List<IrpRole> irpSysRoles) {
		this.irpSysRoles = irpSysRoles;
	}

	public List<IrpRole> getIrpSpeRoles() {
		return irpSpeRoles;
	}

	public void setIrpSpeRoles(List<IrpRole> irpSpeRoles) {
		this.irpSpeRoles = irpSpeRoles;
	}

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}

	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
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

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
	
	private String pageHTML;

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}
	
	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	/**
	 * 已开通用户列表
	 * @return
	 */
	public String useUserList() {
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	private int countuser;

	private HashMap<Long, String> denseMap;

	private String oldPassWord;
	
	
	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public int getCountuser() {
		return countuser;
	}

	public HashMap<Long, String> getDenseMap() {
		return denseMap;
	}

	public void setDenseMap(HashMap<Long, String> denseMap) {
		this.denseMap = denseMap;
	}

	public void setCountuser(int countuser) {
		this.countuser = countuser;
	}

	/**
	 * 随机用户列表
	 * @return
	 */
	public String getUserRandom() {
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG, searchWord, searchType);
		if(countuser<this.pageSize&&countuser!=0){
			this.pageSize=countuser;
		}
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, countuser);
		List<Long> listuserid=irpUserService.findAllSystemUserIds();
		List<IrpUser> listuser= new ArrayList<IrpUser>();
		int userid;
		for(;listuser.size()<countuser;){
			userid = (int)(Math.random()*listuserid.size());
			IrpUser user=irpUserService.findUserByUserId((long) listuserid.get(userid));
			if(!listuser.contains(user)){
				listuser.add(user);
			}
		}
		irpUsers=listuser;
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 防止手机session失效
	 * mobile
	 * @return
	 */
	public void sessionPMobile(){
	}
	
	/**
	 * 未开通用户列表
	 * @return
	 */
	public String applyUserList() {
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_APPLY, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_APPLY, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 已停用用户列表
	 * @return
	 */
	public String forbidUserList() {
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_FORBID, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_FORBID, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 编辑用户
	 * @return
	 */
	public String userEdit() {
		irpUser = irpUserService.findUserByUserId(userId);
		//获得角色相关信息
		irpSysRoles = irpRoleService.findRolesByRoleType(1);
		irpSpeRoles = irpRoleService.findRolesByRoleType(2);
		roleIds = irpRoleService.findRoleIdsByUserId(userId);
		//判断当前用户是否是专家
		long expertRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
		for (String sRoleId : roleIds.split(",")) {
			if(sRoleId==null || sRoleId.equals(""))
				continue;
			if(Long.parseLong(sRoleId)==expertRoleId){
				irpUser.setExpert(true);
				break;
			}
		}
		//密级
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		denseMap=new HashMap<Long, String>();
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				for (String string : denseArray) {
					String[] denseArrayTwo=string.split(":");
					if(denseArrayTwo!=null&&denseArrayTwo.length>0){
						denseMap.put(Long.parseLong(denseArrayTwo[0]), denseArrayTwo[1]);
					}
				}
			}
		}
		//获得组织相关信息
		groupsInfo = irpGroupService.findGroupIdsByUserId(userId);
		//获得专家分类
		if(irpUser.isExpert()){
			categorysInfo = irpCategoryService.findCategoryByExpertId(userId);
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑用户处理
	 */
	public void userEditDowith(){
		int nResult = 0;
		int nNum = 0;
		//添加用户
		//添加生母
		String sm=getEnameFirst(irpUser.getTruename());
		if(sm.length()>=2){
			irpUser.setUsersm("");
		}else{
			String smfirest=sm.substring(0, 1).toUpperCase();
			irpUser.setUsersm(smfirest);
		}
		Long nUserId = irpUserService.userEdit(irpUser);
		//添加用户组织关系
		if(groupIds!=null && groupIds.split(",").length>0 && nUserId>0L){
			nResult += irpGroupService.importGroupUserByUserId(nUserId, groupIds);
			nNum += 1;
		}
		//添加用户角色关系
		if(roleIds!=null && roleIds.split(",").length>0 && nUserId>0L){
			nResult += irpRoleService.importRoleUserByUserId(nUserId, roleIds);
			nNum += 1;
		}
		//添加专家分类
		if(classifyIds!=null && classifyIds.split(",").length>0 && nUserId>0L){
			nResult += irpExpertClassifyLinkService.importExpertClassifyByUserId(nUserId, classifyIds);
			nNum += 1;
		}
		//添加专家推荐
		irpUserService.setExpertRecommend(nUserId, experttype);
		ActionUtil.writer(nResult==nNum?"1":"0");
	}
	
	/**
	 * 编辑用户处理
	 */
	public String userUs(){
		irpUser = new IrpUser();
		irpUser.setUserid(new Long(0));
		irpSysRoles = irpRoleService.findRolesByRoleType(1);
		irpSpeRoles = irpRoleService.findRolesByRoleType(2);
		return SUCCESS;
	}
	/**
	 * 编辑用户处理
	 */
	public String userByleave(){
		irpUser = new IrpUser();
		irpUser.setUserid(new Long(0));
		irpSysRoles = irpRoleService.findRolesByRoleType(1);
		return SUCCESS;
	}
	/**
	 * 开通用户
	 */
	public void openUser(){
		if(userIds==null || "".equals(userIds)){
			return;
		}
		int nRows = irpUserService.updateStatusByUserids(userIds, IrpUser.USER_STATUS_REG);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 停用用户
	 */
	public void forbidUser(){
		if(userIds==null || "".equals(userIds)){
			return;
		}
		int nRows = irpUserService.updateStatusByUserids(userIds, IrpUser.USER_STATUS_FORBID);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 跳转到修改用户密码页面
	 * @return
	 */
	public String userPwdEdit() {
		return SUCCESS;
	}
	
	/**
	 * 更新用户密码
	 */
	public void userPwdEditDowith() {
		if(userIds==null || passWord==null || "".equals(userIds) || "".equals(passWord)){
			return;
		}
		int nRows = irpUserService.updatePassWordByUserids(userIds, passWord);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 管理员更新用户密码
	 */
	public void clientUserPwdEditDowith() {
		if(passWord==null || "".equals(passWord)){
			return;
		}
		int nRows = irpUserService.updatePassWordByUserids(LoginUtil.getLoginUserId()+"", passWord);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	
	/**
	 * 用户修改密码
	 */
	public void userPwdUpdate() {
		int nRows =0;
		if(oldPassWord==null || "".equals(oldPassWord)){
			return;
		}else{
			String string=LoginUtil.getLoginUser().getPassword();
			oldPassWord=EncryptUtil.encryptMD5(oldPassWord);
			if(oldPassWord.equals(string)){
				if(passWord==null || "".equals(passWord)){
					return;
				}
				nRows = irpUserService.updatePassWordByUserids(LoginUtil.getLoginUserId()+"", passWord);
			}
		}
		ActionUtil.writer(String.valueOf(nRows));
	}
	/**
	 * 用户密码验证
	 */
	public void userPwdValidate() {
		int nRows =0;
		if(oldPassWord==null || "".equals(oldPassWord)){
		}else{
			String string=LoginUtil.getLoginUser().getPassword();
			oldPassWord=EncryptUtil.encryptMD5(oldPassWord);
			if(oldPassWord.equals(string)){
				nRows=1;
			}
		}
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 初始化创建用户页面
	 * @return
	 */
	public String createUser() {
		irpUser = new IrpUser();
		irpUser.setUserid(new Long(0));
		irpSysRoles = irpRoleService.findRolesByRoleType(1);
		irpSpeRoles = irpRoleService.findRolesByRoleType(2);
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		denseMap=new HashMap<Long, String>();
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				for (String string : denseArray) {
					String[] denseArrayTwo=string.split(":");
					if(denseArrayTwo!=null&&denseArrayTwo.length>0){
						denseMap.put(Long.parseLong(denseArrayTwo[0]), denseArrayTwo[1]);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 验证用户名是否存在
	 */
	public void checkUserName() {
		boolean nResult = irpUserService.checkUserName(userName, userId);
		ActionUtil.writer(String.valueOf(nResult));
	}
	
	/**
	 * 前台用户设置
	 * @return
	 */
	public String userSet() {
		irpUser = LoginUtil.getLoginUser();
		return SUCCESS;
	}
	
	/**
	 * 前台管理中心
	 * @return
	 */
	public String userManage(){
		irpUser = LoginUtil.getLoginUser();
		return SUCCESS;
	}
	
	public void userSetDowith(){
		//添加用户
		String sm=getEnameFirst(irpUser.getTruename());
		if(sm.length()>=2){
			irpUser.setUsersm("");
		}else{
			String smfirest=sm.substring(0, 1).toUpperCase();
			irpUser.setUsersm(smfirest);
		}
		long nUserId = irpUserService.userEdit(irpUser);
		if(nUserId>0){
			if(irpUser.getEmail()==null || "".equals(irpUser.getEmail())){
				irpUserPrivacyService.judgePrivacyIfEx(LoginUtil.getLoginUserId(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
				irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL, IrpUserPrivacy.MICROBLOGVALUE);
				
			}
		}
		ActionUtil.writer(nUserId>0?"1":"0");
	}
	
	/**
	 * 用户头像
	 * @return
	 */
	public String userPic() {
		//初始化用户图片地址
		IrpUser user = irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		this.userPicUrl =  user.getDefaultUserPic();
		return SUCCESS;
	}
    
	/**
	 * 根据用户输入 提示用户
	 * @return
	 */
    public String selectUserlike() {
    	irpUsers=irpUserService.likeUser(sname);
		return SUCCESS;
	}
    private Long groupId;
    
    private List<Map<String,Object>> dataList;
    
    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
     * 根据用户生母查询用户
     * @return
     */
    public String selectUserbySM() {
        // 创建一个用于前端遍历的数据列表
        dataList = new ArrayList<Map<String, Object>>();
        
        // 获取登录人的Id，获取关注人的id集合
        Long loginUserId = LoginUtil.getLoginUser().getUserid();
        List<IrpMicroblogFocus> focusList = this.irpMicroblogFocusService.findMicroblogFocusUserId(loginUserId, null);
        
        // 查询分组用户
        if (groupId != null) {
            int nDataCount = irpGroupService.findUsersCountByGroupId(groupId, searchWord, searchType);
            PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
            irpUsers = irpGroupService.findUsersOfPageByGroupId(pageUtil, groupId, "crtime desc", searchWord,
                    searchType, sname);

            // 遍历判断通讯录中哪个用户的Id与关注人的Id相等
            for (IrpUser irpUser : irpUsers) {
                Map<String, Object> dataMap = new HashMap<String, Object>();

                // 遍历取出关注人的Id
                for (IrpMicroblogFocus irpMicroblogFocus : focusList) {
                    Long focusUserId = irpMicroblogFocus.getUserid();

                    // 相等的话将关注状态focusStatus置为true，跳出循环
                    if (focusUserId.equals(irpUser.getUserid())) {
                        dataMap.put("focusStatus", "true");
                        break;
                    } else {
                        dataMap.put("focusStatus", "false");
                    }
                }

                // 构造用户数据 
                dataMap.put("userid", irpUser.getUserid());
                dataMap.put("userpic", irpUser.getUserpic());
                dataMap.put("sex", irpUser.getSex());
                dataMap.put("truename", irpUser.getTruename());
                dataMap.put("mobile", irpUser.getMobile());
                dataMap.put("email", irpUser.getEmail());
                dataMap.put("location", irpUser.getLocation());

                dataList.add(dataMap);
                dataMap = new HashMap<String, Object>();
            }

            if (showway.equals("0")) {
                return SUCCESS;
            } else {
                return "list";
            }
        }

        //判断检索词是否为空
        if (this.getSearchWord() == null || this.getSearchWord().trim().length() == 0) {
            this.searchWord = null;
        } else {
            //去掉搜索词中的空格(包括首尾和中间)
            this.setSearchWord(this.searchWord.replaceAll("\\s*", ""));
        }

        int result = irpUserService.findUserbySMcount(this.getSname(), searchWord);
        PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, result);
        irpUsers = irpUserService.findUserbySM(this.getSname(), searchWord, pageUtil);


        // 遍历判断通讯录中哪个用户的Id与关注人的Id相等
        for (IrpUser irpUser : irpUsers) {
            Map<String, Object> dataMap = new HashMap<String, Object>();

            // 遍历取出关注人的Id
            for (IrpMicroblogFocus irpMicroblogFocus : focusList) {
                Long focusUserId = irpMicroblogFocus.getUserid();

                // 相等的话将关注状态focusStatus置为true，跳出循环
                if (focusUserId.equals(irpUser.getUserid())) {
                    dataMap.put("focusStatus", "true");
                    break;
                } else {
                    dataMap.put("focusStatus", "false");
                }
            }

            // 构造用户数据 
            dataMap.put("userid", irpUser.getUserid());
            dataMap.put("userpic", irpUser.getUserpic());
            dataMap.put("sex", irpUser.getSex());
            dataMap.put("truename", irpUser.getTruename());
            dataMap.put("mobile", irpUser.getMobile());
            dataMap.put("email", irpUser.getEmail());
            dataMap.put("location", irpUser.getLocation());

            dataList.add(dataMap);
            dataMap = new HashMap<String, Object>();
        }

        this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
        if (showway.equals("0")) {
            return SUCCESS;
        } else {
            return "list";
        }
    }
    
    /**
     * 将中文转换为拼音
     * @param name
     * @return
     */
    private String getEnameFirst(String name) {
    	 String str="";
         HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
         pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置样式
         pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
         pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
         pyFormat.getToneType();
         try {
        	 str= PinyinHelper.toHanyuPinyinString(name.toLowerCase(), pyFormat, "");
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
         char csm=str.charAt(0);
         int ism=csm;
         if(ism>=97&&ism<=122){
         	return csm+"";
         }else{
        	return "other";
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
     * 根据username获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserName(String _username){
    	return this.irpUserService.findShowNameByUsername(_username);
    }
    
    /**
     * 选择用户
     * @return
     */
    public String selectUserList() {
    	int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG , searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("select_page(#PageNum#)");
		return SUCCESS;
	}
    
    public String userBinding() {
    	irpUser = LoginUtil.getLoginUser();
		return SUCCESS;
	}
    
    public void userUnbindingEdit() {
    	irpUser.setUserid(LoginUtil.getLoginUserId());
    	if("qq".equals(type)){
    		if(irpUser.getQqtoken()==null){
        		irpUser.setQqtoken("");
        	}
        	if(irpUser.getQquserid()==null){
        		irpUser.setQquserid("");
        	}
    	}else if ("weibo".equals(type)) {
    		if(irpUser.getWeibotoken()==null){
        		irpUser.setWeibotoken("");
        	}
        	if(irpUser.getWeibouserid()==null){
        		irpUser.setWeibouserid("");
        	}
		}
    	int nCount = irpUserService.userBindingEdit(irpUser);
    	ActionUtil.writer(""+nCount);
	}
    
    public String userBindingOauth() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	if("qq".equals(type)){
    		String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/user/user_bindingqq_dowith.action";
        	QQConnectConfig.updateProperties("redirect_URI", redirectURI);
        	QQConnectConfig.updateProperties("scope", "get_user_info");
        	try {
        		oauthLoginUrl = new Oauth().getAuthorizeURL(ServletActionContext.getRequest());
    		} catch (QQConnectException e) {
    			e.printStackTrace();
    		}
    	}else if("weibo".equals(type)){
    		try {
    			String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/user/user_bindingweibo_dowith.action";
    			WeiboConfig.updateProperties("redirect_URI", redirectURI);
				oauthLoginUrl = new weibo4j.Oauth().authorize("code", "all");
			} catch (WeiboException e) {
				e.printStackTrace();
			}
    	}
    	if(oauthLoginUrl!=null && oauthLoginUrl.length()>0){
    		return SUCCESS;
    	}else {
    		return ERROR;
		}
	}
    
    public String userBindingQqDowith() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = null,openID = null;
			if (accessTokenObj.getAccessToken().equals("")) {
            	// 我们的网站被CSRF攻击了或者用户取消了授权
				//做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
            	accessToken = accessTokenObj.getAccessToken();
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                irpUser = new IrpUser();
                irpUser.setUserid(LoginUtil.getLoginUserId());
                irpUser.setQqtoken(accessToken);
                irpUser.setQquserid(openID);
                irpUserService.userBindingEdit(irpUser);
                return SUCCESS;
            }
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
    	return ERROR;
    }
    
    public String userBindingWeiboDowith() {
    	try {
    		weibo4j.http.AccessToken accessTokenObj = new weibo4j.Oauth().getAccessTokenByCode(code);
    		String accessToken = null,openID = null;
    		if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
            	accessToken = accessTokenObj.getAccessToken();
            	Account am = new Account();
            	am.client.setToken(accessToken);
            	openID = am.getUid().getString("uid");
                irpUser = new IrpUser();
                irpUser.setUserid(LoginUtil.getLoginUserId());
                irpUser.setWeibotoken(accessToken);
                irpUser.setWeibouserid(openID);
                irpUserService.userBindingEdit(irpUser);
                return SUCCESS;
            }
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return ERROR;
    }
    public void getJsonUser(){
    	HttpServletRequest request =ServletActionContext.getRequest();
    	String term = request.getParameter("term");
    	IrpUserExample example = new IrpUserExample();
    	example.createCriteria().andUsernameLike(term);
    	List<IrpUser> users = irpUserService.findUserByExample(example);
    	StringBuffer data=new StringBuffer();   	
    	if(users.size()>0){
    		data.append("[");
    		String desc ="";
    		for(int i=0;i<users.size();i++){
    			Map<Long,String> userGroups = irpGroupService.findGroupIdsByUserId(users.get(i).getUserid());
    			desc=userGroups.get(users.get(i).getUserid());
    			if(desc!=null&&!desc.equals("")){
    				desc=desc+"-";
    			}else{
    				desc="";
    			}
    			data.append("{\"id\":");
    			data.append(users.get(i).getUserid());
    			data.append(",");
    			data.append("\"userinfo\":");
    			data.append('"'+desc+users.get(i).getUsername()+'"');
    			data.append("}");
    			if(i+1<users.size()){
    				data.append(",");
    			}    	    	
    		}
    		data.append("]");
        }
        ActionUtil.writer(data.toString());
    }
}