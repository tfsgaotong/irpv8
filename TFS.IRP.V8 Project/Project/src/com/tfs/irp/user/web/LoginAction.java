package com.tfs.irp.user.web;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import weibo4j.Account;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;
import com.swetake.util.Qrcode;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.util.erWM;
import com.tfs.irp.util.sms.SmsUtil;

public class LoginAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	
	private final String IRPUSERNAME = "irpusername"; //cookie用户名称
	private final String IRPPASSWORD = "irppassword"; //cookie用户密码
	private final int MAXAGE = 60*60*24*365; //cookie生命周期
	
	private IrpUser irpUser;
	private IrpUserService irpUserService;
	private IrpUserPrivacyService irpUserPrivacyService;
	private IrpCategoryService irpCategoryService;
	
	private List<IrpCategory> listCategory;
	
	private String requesturl;
	
	private String type;
	
	private String oauthLoginUrl;
	
	private String code;
	
	private String mobileusername;
	
	private String mobilepwd;
	
	private String checkalphabet;
	
	private String phonenumber;
	
	private IrpAttachedService irpAttachedService;
	
	
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCheckalphabet() {
		return checkalphabet;
	}

	public void setCheckalphabet(String checkalphabet) {
		this.checkalphabet = checkalphabet;
	}

	public String getMobileusername() {
		return mobileusername;
	}

	public void setMobileusername(String mobileusername) {
		this.mobileusername = mobileusername;
	}

	public String getMobilepwd() {
		return mobilepwd;
	}

	public void setMobilepwd(String mobilepwd) {
		this.mobilepwd = mobilepwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOauthLoginUrl() {
		return oauthLoginUrl;
	}

	public void setOauthLoginUrl(String oauthLoginUrl) {
		this.oauthLoginUrl = oauthLoginUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}

	private String saveLogin;

	public String getSaveLogin() {
		return saveLogin;
	}

	public void setSaveLogin(String saveLogin) {
		this.saveLogin = saveLogin;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	
	public List<IrpCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<IrpCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}
	
	public String reg() {
		return SUCCESS;
	}

	public void regDowith() {
		String  Multilevel_Verification = SysConfigUtil.getSysConfigValue("Multilevel_Verification");
		if(Multilevel_Verification.equals("1") || Multilevel_Verification.equals("2")){
			// 验证用户名密码+验证码
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取session验证码
			String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			int nLoginStatus = 0;
			if(checkalphabet !=null && !checkalphabet.equals("")&&kaptchaExpected!=null&&!kaptchaExpected.equals("")){
				if(kaptchaExpected.trim().equalsIgnoreCase(checkalphabet.trim())){
					nLoginStatus=regUser();
					request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "");
				}else{
					nLoginStatus = 4;
				}
			}else{
				nLoginStatus = 4;
			}
			ActionUtil.writer("" + nLoginStatus);
		}else{
			ActionUtil.writer("" + regUser());
		}
	}
	
	public int regUser() {
		String sm=getEnameFirst(irpUser.getTruename());
		if(sm.length()>=2){
			irpUser.setUsersm("");
		}else{
			String smfirest=sm.substring(0, 1).toUpperCase();
			irpUser.setUsersm(smfirest);
		}
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				String[] denseArrayTwo=denseArray[0].split(":");
				if(denseArrayTwo!=null&&denseArrayTwo.length>0){
					irpUser.setDense(Long.parseLong(denseArrayTwo[0]));
				}
			}
		}
		int nRegStatus = irpUserService.regUser(irpUser);
		return nRegStatus;
	}
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validateUser(){
		int msg=0;
		List<IrpUser> irpUser= irpUserService.findUserByUsername(username);
		if(irpUser.size()>0){
			msg=1;
		}
		ActionUtil.writer(msg+"");
	}
	public void validatePwd(){
		int msg=0;
		List<IrpUser> irpUser= irpUserService.findUserByUsername(username);
		if(irpUser.size()>0){
			if(irpUser.get(0).getPassword().equals(password)){
				msg=1;
			}
		}
		ActionUtil.writer(msg+"");
	}
	public String login() {
		irpUser = LoginUtil.getLoginUser();
		if(irpUser!=null){
			IrpUserPrivacy irpUserPrivacy = irpUserPrivacyService.irpUserPrivacy(irpUser.getUserid(), IrpUserPrivacy.USERLOGINLOCATION);
			if(irpUserPrivacy!=null){
				if(irpUserPrivacy.getPrivacyvalue().equals(IrpUserPrivacy.ENTERPRICEPAGE)){
					return "enterprise";
				}else{
					return "personal";
				}
			}	
		}
		String sUserName = getCookieByName(IRPUSERNAME);
		String sPassWord = getCookieByName(IRPPASSWORD);
		if(sUserName!=null && sUserName.length()>0 && sPassWord!=null && sPassWord.length()>0){
			irpUser = new IrpUser();
			irpUser.setUsername(sUserName);
			irpUser.setPassword(sPassWord);
			int nLoginStatus = irpUserService.login(irpUser);
			if(nLoginStatus>0){
				if(nLoginStatus==3){
					return "enterprise";
				}else if(nLoginStatus==2){
					return "personal";
				}else{
					return "personal";
				}
			}else{
				clearCookieByName(IRPUSERNAME);
				clearCookieByName(IRPPASSWORD);
				String  Multilevel_Verification = SysConfigUtil.getSysConfigValue("Multilevel_Verification");
				if(Multilevel_Verification.equals("1")){
					return "success_one";
				}else if(Multilevel_Verification.equals("2")){
					return "success_two";
				}else{
					return SUCCESS;
				}
			}
		}else{
			String  Multilevel_Verification = SysConfigUtil.getSysConfigValue("Multilevel_Verification");
			if(Multilevel_Verification.equals("1")){
				return "success_one";
			}else if(Multilevel_Verification.equals("2")){
				return "success_two";
			}else{
				return SUCCESS;
			}
		}
	}

	public void loginDowith() {
		String  Multilevel_Verification = SysConfigUtil.getSysConfigValue("Multilevel_Verification");
		if(Multilevel_Verification.equals("1") || Multilevel_Verification.equals("2")){
			// 验证用户名密码+验证码
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取session验证码
			String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			int nLoginStatus = 0;
			if(checkalphabet != "" && !checkalphabet.equals("")){
				if(kaptchaExpected.equalsIgnoreCase(checkalphabet)){
					nLoginStatus = irpUserService.login(irpUser);
					if(nLoginStatus>0 && "1".equals(saveLogin)){
						addCookie(IRPUSERNAME, irpUser.getUsername());
						addCookie(IRPPASSWORD, irpUser.getPassword());
					}
				}
			}
			request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "");
			ActionUtil.writer("" + nLoginStatus);
		}else{
			int nLoginStatus = irpUserService.login(irpUser);
			if(nLoginStatus>0 && "1".equals(saveLogin)){
				addCookie(IRPUSERNAME, irpUser.getUsername());
				addCookie(IRPPASSWORD, irpUser.getPassword());
			}
			ActionUtil.writer("" + nLoginStatus);
		}
	}
	/**
	 * 
	 * 手机端登录入口
	 * @param mobile
	 * @return
	 */
	public void loginDowithMobile(){
		
		IrpUser _irpUser = new IrpUser();
		
		_irpUser.setUsername(mobileusername);
		_irpUser.setPassword(mobilepwd);
		
		int nLoginStatus = irpUserService.login(_irpUser);
		if(nLoginStatus>0 && "1".equals(saveLogin)){
			addCookie(IRPUSERNAME, _irpUser.getUsername());
			addCookie(IRPPASSWORD, _irpUser.getPassword());
		}
		ActionUtil.writer("" + nLoginStatus);
	}

	public String logout() {
		try {
			irpUserService.logout();
			clearCookieByName(IRPUSERNAME);
			clearCookieByName(IRPPASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * base64加密
	 * @param sValue
	 * @return
	 */
	private String base64Encryption(String sValue){
		String sBase64 = "";
		try{
			sBase64 = new BASE64Encoder().encode(sValue.getBytes("UTF-8"));
		}catch(UnsupportedEncodingException ex){
			logger.error(ex);
		}
		return sBase64;
	}
	
	/**
	 * base64解密
	 * @param sValue
	 * @return
	 */
	private String base64Decrypt(String sValue){
		String sStr = "";
		try{
			 byte[] bytes = new BASE64Decoder().decodeBuffer(sValue);
	         sStr = new String(bytes, "UTF-8");
		}catch(IOException ex){
			logger.error(ex);
		}
		return sStr;
	}
	
	/**
	 * 添加Cookie
	 * @param response
	 * @param sCookieName
	 * @param sCookieValue
	 */
	private void addCookie(String sCookieName, String sCookieValue){
		Cookie username_Cookie = new Cookie(sCookieName,base64Encryption(sCookieValue));
		username_Cookie.setMaxAge(MAXAGE);
		ServletActionContext.getResponse().addCookie(username_Cookie);
	}
	
	/**
	 * 删除指定的cookie字段
	 * @param cookieName
	 */
	private void clearCookieByName(String cookieName){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse(); 
		Cookie[] mycookie = request.getCookies();
		if(mycookie!=null){
			for(int i = 0;i<mycookie.length;i++){
				if(cookieName.equals(mycookie[i].getName())){
					mycookie[i].setValue(null);
					mycookie[i].setMaxAge(0);
					response.addCookie(mycookie[i]);
					break;
				}
			}
		}
	}
	
	/**
	 * 从request对象中获取指定的cookie字段值
	 * @param request
	 * @param cookieName
	 * @return
	 */
	private String getCookieByName(String cookieName){
		Cookie[] mycookie = ServletActionContext.getRequest().getCookies();
		String strName = "";
		if(mycookie!=null){
			for(int i=0;i<mycookie.length;i++){
				if(cookieName.equals(mycookie[i].getName())){
					strName = mycookie[i].getValue();
					break;
				}
			}
		}
		return base64Decrypt(strName);
	}
	//将中文转换为英文
    private String getEnameFirst(String name) {
    	String str="";
         HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
         pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置样式
         pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
         pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
         try {
        	 str= PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
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
    
    public String oauthLogin() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String sLoginId,sLoginKey;
    	if("qq".equals(type)){
        	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/qqlogindowith.action";
        	sLoginId = SysConfigUtil.getSysConfigValue("QQ_LOGIN_ID");
        	sLoginKey = SysConfigUtil.getSysConfigValue("QQ_LOGIN_KEY");
        	QQConnectConfig.updateProperties("app_ID", sLoginId);
        	QQConnectConfig.updateProperties("app_KEY", sLoginKey);
        	QQConnectConfig.updateProperties("redirect_URI", redirectURI);
        	QQConnectConfig.updateProperties("scope", "get_user_info");
        	try {
        		oauthLoginUrl = new Oauth().getAuthorizeURL(ServletActionContext.getRequest());
    		} catch (QQConnectException e) {
    			e.printStackTrace();
    		}
    	}else if("weibo".equals(type)){
    		try {
    			String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/weibologindowith.action";
    			sLoginId = SysConfigUtil.getSysConfigValue("WEIBO_LOGIN_ID");
            	sLoginKey = SysConfigUtil.getSysConfigValue("WEIBO_LOGIN_KEY");
            	WeiboConfig.updateProperties("client_ID", sLoginId);
            	WeiboConfig.updateProperties("client_SERCRET", sLoginKey);
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
    
    public String qqLoginDowith() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = null,openID = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
            	// 我们的网站被CSRF攻击了或者用户取消了授权
				//做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                IrpUser qqLoginUser = irpUserService.findIrpUserByQQLogin(accessToken, openID);
                if(qqLoginUser!=null){
                	int loginState = irpUserService.loginByName(qqLoginUser.getUsername());
                	if(loginState==1 || loginState==2){
                		return "personal";
                	} else if(loginState==3){
                		return "enterprise";
                	} else{
                		return "loginError";
                	}
                }else{
                	UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                	UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                	if (userInfoBean.getRet() == 0) {
                		irpUser = new IrpUser();
                		irpUser.setUsername(userInfoBean.getNickname().trim()+System.currentTimeMillis());
                		irpUser.setNickname(userInfoBean.getNickname().trim());
                		String sGender = userInfoBean.getGender().trim();
                		if(sGender.equals("男")){
                			irpUser.setSex(1);
                		}else if(sGender.equals("女")){
                			irpUser.setSex(2);
                		}
                    	irpUser.setQqtoken(accessToken.trim());
                    	irpUser.setQquserid(openID.trim());
                    	irpUser.setUserpic(userInfoBean.getAvatar().getAvatarURL100());
                    	this.type="qq";
                		return SUCCESS;
                	}
                }
            }
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
    	return ERROR;
	}
    
    public String weiboLoginDowith() {
    	if(code==null || code.length()==0){
    		return "loginError";
    	}
    	try {
    		weibo4j.http.AccessToken accessTokenObj = new weibo4j.Oauth().getAccessTokenByCode(code);
    		String accessToken = null,openID = null;
    		if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            }else{
            	accessToken = accessTokenObj.getAccessToken();
            	Account am = new Account();
            	am.client.setToken(accessToken);
            	openID = am.getUid().getString("uid");
            	IrpUser weiboLoginUser = irpUserService.findIrpUserByWeiboLogin(accessToken, openID);
                if(weiboLoginUser!=null){
                	int loginState = irpUserService.loginByName(weiboLoginUser.getUsername());
                	if(loginState==1 || loginState==2){
                		return "personal";
                	} else if(loginState==3){
                		return "enterprise";
                	} else{
                		return "loginError";
                	}
                }else{
                	Users um = new Users();
                	um.client.setToken(accessToken);
                	User user = um.showUserById(openID);
                	if (user!=null) {
                		irpUser = new IrpUser();
                		irpUser.setUsername(user.getScreenName().trim()+System.currentTimeMillis());
                		irpUser.setNickname(user.getScreenName().trim());
                		String sGender = user.getGender().trim();
                		if(sGender.equals("m")){
                			irpUser.setSex(1);
                		}else if(sGender.equals("f")){
                			irpUser.setSex(2);
                		}
                    	irpUser.setWeibotoken(accessToken.trim());
                    	irpUser.setWeibouserid(openID.trim());
                    	irpUser.setUserpic(user.getProfileImageUrl());
                    	this.type="weibo";
                		return SUCCESS;
                	}
                }
            }
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ERROR;
	}
    
    public String oauthLoginReg() {
		return SUCCESS;
	}
    
    public void oauthBindingDowith() {
    	int nLoginStatus = irpUserService.login(irpUser);
    	if(LoginUtil.getLoginUser()!=null){
            IrpUser oauthUser = new IrpUser();
            oauthUser.setUserid(LoginUtil.getLoginUserId());
            if("qq".equals(type)){
            	oauthUser.setQqtoken(irpUser.getQqtoken());
                oauthUser.setQquserid(irpUser.getQquserid());
            }else if ("weibo".equals(type)) {
            	oauthUser.setWeibotoken(irpUser.getWeibotoken());
                oauthUser.setWeibouserid(irpUser.getWeibouserid());
			}
            if("1".equals(saveLogin)){
            	String sFileName = SysFileUtil.saveNetFile(irpUser.getUserpic(), SysFileUtil.FILE_TYPE_USER_FILE);
            	oauthUser.setUserpic(sFileName);
            }
            irpUserService.userBindingEdit(oauthUser);
    	}
		ActionUtil.writer("" + nLoginStatus);
	}
    private int chatcontentnums;
    private IrpUser loginuserobj;
    public int getChatcontentnums() {
		return chatcontentnums;
	}

	public void setChatcontentnums(int chatcontentnums) {
		this.chatcontentnums = chatcontentnums;
	}

	public IrpUser getLoginuserobj() {
		return loginuserobj;
	}

	public void setLoginuserobj(IrpUser loginuserobj) {
		this.loginuserobj = loginuserobj;
	}
	private List<IrpUserMedal> listusermedal;
	private IrpUserMedalService irpUserMedalService;
	private IrpMedalService irpMedalService;
	private List<IrpMedal> listmedal;
	public List<IrpUserMedal> getListusermedal() {
		return listusermedal;
	}

	public void setListusermedal(List<IrpUserMedal> listusermedal) {
		this.listusermedal = listusermedal;
	}

	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}

	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}

	public List<IrpMedal> getListmedal() {
		return listmedal;
	}

	public void setListmedal(List<IrpMedal> listmedal) {
		this.listmedal = listmedal;
	}

	/**
     * 前端首页
     * @return
     */
    public String clientIndex() {
		chatcontentnums = SysConfigUtil.getSysConfigNumValue("CHATCONTENTGNUMS");
		loginuserobj = this.irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = String.valueOf(request.getParameter("type"));
		if("medal".equals(type)){
			//勋章分类列表
			String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.MEDAL);
			HashMap<String,Object> map=new HashMap<String, Object>();  
			map.put("parentid", categoryQuestionId);
			listCategory=irpCategoryService.findCategoryByConditions(map);
		}else if("goods".equals(type)){
			//兑换分类列表
			/*String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.GOODS);
			HashMap<String,Object> map=new HashMap<String, Object>();  
			map.put("parentid", categoryQuestionId);
			listCategory=irpCategoryService.findCategoryByConditions(map);*/
			String goodscategory = SysConfigUtil.getSysConfigValue(IrpCategory.GOODS);
			String[] irpgoodscategory = goodscategory.split(",");
			listCategory = new ArrayList<IrpCategory>();
			for(String category : irpgoodscategory){
				String[] result = category.split("-");
				IrpCategory irpcategory = new IrpCategory();
				irpcategory.setCdesc(result[0]);
				irpcategory.setCategoryid(Long.valueOf(result[1]));
				listCategory.add(irpcategory);
			}
		}
		
		
		//查询该用户所有勋章
		IrpUserMedal userMedal=null;
		IrpMedal medal=null;
		listusermedal=irpUserMedalService.getUserMedalByUserid(null, LoginUtil.getLoginUserId());
		listmedal=new ArrayList<IrpMedal>();
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<listusermedal.size();i++){
			userMedal=listusermedal.get(i);
			list.add(userMedal.getMedalid());
		}
		if(list.size()>0){
			listmedal=irpMedalService.findMedalByMedalidList(list);
			if(listmedal!=null&&listmedal.size()>0){
				for (IrpMedal m : listmedal) {
					int count=0;
					for (IrpUserMedal u : listusermedal) {
						if((u.getMedalid()+"").equals(m.getMedalid()+"")){
							count++;
						}
					}
					m.setUserCount(count);
				}
			}
		}
		
		
		
		
		return SUCCESS;
	}
    
	public List<IrpCategory> findChildCategoryByParentId(long _nParentId){
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("parentid", _nParentId);
		List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
		return childCategory;
	}
	
	/**
     * 发送短信验证码
     */
	public void sendSmsForLogin(){
		// 验证用户名密码+验证码
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取session验证码
		String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		StringBuffer sb = new StringBuffer();
		sb.append("您的验证码为:");
		sb.append(kaptchaExpected);
		if(phonenumber !=null&&!"".equals(phonenumber)){
			SmsUtil.sendMsg_webchinese(sb.toString(), phonenumber);
		}
		ActionUtil.writer("" + 1);
	}
	
    private IrpGroupService irpGroupService;
    private List<IrpGroup> groupList;
    private List<IrpGroup> list;
    private Long groupid;
    
    public List<IrpGroup> getList() {
		return list;
	}

	public void setList(List<IrpGroup> list) {
		this.list = list;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}

	public List<IrpGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<IrpGroup> groupList) {
		this.groupList = groupList;
	}

	public String addressBookIndex() {
		chatcontentnums = SysConfigUtil.getSysConfigNumValue("CHATCONTENTGNUMS");
		loginuserobj = this.irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		groupList=irpGroupService.findGroupsByParentId((long) 1);
		return SUCCESS;
	}
	public String userGroup() {
		chatcontentnums = SysConfigUtil.getSysConfigNumValue("CHATCONTENTGNUMS");
		loginuserobj = this.irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		IrpGroup group=irpGroupService.findGroupByGroupId(groupid);
		groupList=irpGroupService.findGroupsByParentId(group.getParentid());
		list=irpGroupService.currentGroup(groupid,list,(long) 1);
		return SUCCESS;
	}
	public List<IrpGroup> findChildGroupByParentId(long groupid) {
		chatcontentnums = SysConfigUtil.getSysConfigNumValue("CHATCONTENTGNUMS");
		loginuserobj = this.irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		groupList=irpGroupService.findGroupsByParentId(groupid);
		return groupList;
	}
	
	/**
	 * 获得图片
	 * @param _docid
	 * @return
	 */
	public String coverPath(String attachedids){
		String filePath="";
		String[] _attachedid=attachedids.split(",");
		for(int j=0;j<_attachedid.length;j++){
			Long attachedid=Long.parseLong(_attachedid[j]);
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(attachedid); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_300X300");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			} 
		}
		return filePath;
	}
	
    /**
     * 管理端首页
     * @return
     */
    public String adminIndex() {
		return SUCCESS;
	}
}