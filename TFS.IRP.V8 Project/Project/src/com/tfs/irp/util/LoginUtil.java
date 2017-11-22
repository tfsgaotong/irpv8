package com.tfs.irp.util;


import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.session.OnlineUserBindingListener;

public class LoginUtil {
	private final static String LOGIN_USER_NAME = "LoginUser";
	
	private final static String ONLINE_USER_NAME = "OnlineUserList";
	
	public static IrpUser getLoginUser() {
		IrpUser loginUser = null;
		try {
			loginUser = getLoginUser(getSession());
		} catch (NullPointerException e) {
			IrpUserDAO irpUserDAO = (IrpUserDAO) ApplicationContextHelper.getContext().getBean("irpUserDAO");
			try {
				loginUser = irpUserDAO.selectByPrimaryKey(1L);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return loginUser;
	}
	/**
	 * 张银珠，获取用户名称显示在页面显示
	 */
	public static String getUserNameString(IrpUser irpUser){
		String cValue=""; 
		//判断，昵称 
		String nickName=irpUser.getNickname();
		String trueName=irpUser.getTruename();
		if(nickName!=null && nickName.length()>0){
			cValue=nickName;//昵称
		}else if(trueName!=null &&  trueName.length()>0){//真实姓名
		    cValue=trueName;//昵称
		}else{
			cValue=irpUser.getUsername();//昵称
		} 
		return cValue;
	}
	/**
	 * 根据用户名判断此用户在页面该显示的名字
	 * @param _username
	 * @return
	 */
	public String getShowNameByUsername(String _username){
		ApplicationContext wac= ApplicationContextHelper.getContext();
		IrpUserService irpUserService = (IrpUserService) wac.getBean("irpUserService");
		return irpUserService.findShowNameByUsername(_username);
	}
	/**
	 * 根据用户id判断此用户在页面该显示的名字
	 * @param _username
	 * @return
	 */
	public String getShowNameByUserid(Long _userid){
		ApplicationContext wac= ApplicationContextHelper.getContext();
		IrpUserDAO irpUserDAO =   (IrpUserDAO) wac.getBean("irpUserDAO");
		return irpUserDAO.findShowNameByUserid(_userid);
	}
	/**
	 * 获得当前登录用户对象
	 * 
	 * @return
	 */
	public static IrpUser getLoginUser(HttpSession session) {
		IrpUser loginUser = null;
		Object objUserListener = session.getAttribute(LOGIN_USER_NAME);
		if (objUserListener != null) {
			OnlineUserBindingListener userListener = (OnlineUserBindingListener) objUserListener;
			loginUser = userListener.getLoginUser();
		}
		return loginUser;
	}
	
	/**
	 * 获得当前登录用户ID
	 * @return
	 */
	public static long getLoginUserId() {
		IrpUser loginUser = getLoginUser();
		if(loginUser==null){
			return 0L;
		}else{
			return loginUser.getUserid();
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param _irpUser
	 */
	public static int login(IrpUser _irpUser) {
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpUserService iIrpUserDao = (IrpUserService) ac.getBean("irpUserService");
		return iIrpUserDao.login(_irpUser);
	}
	
	/**
	 * 用户注销
	 */
	public static void logout() {
		logout(getSession());
	}
	
	/**
	 * 用户注销
	 */
	public static void logout(HttpSession session) {
		session.invalidate();
	}
	
	/**
	 * 获得Session
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获得当前在线用户集合
	 * @return
	 */
	public static Map<String, IrpUser> getOnlineUserList() {
		ServletContext application = getSession().getServletContext();
		@SuppressWarnings("unchecked")
		Map<String, IrpUser> onlineUserList = (Map<String, IrpUser>) application.getAttribute(ONLINE_USER_NAME);
		return onlineUserList;
	}
	/**
	 * 更新缓存中的用户信息
	 * @param irpUser
	 */
	public static void updateCacheUser(IrpUser irpUser) {
		IrpUser loginUser = getLoginUser();
		//更新session用户
		if(loginUser.getUserid().longValue()==irpUser.getUserid().longValue()){
			updateUserInfo(irpUser, loginUser);
		}
		//获得在线用户
		IrpUser onlineUser = getOnlineUserList().get(irpUser.getUsername());
		if(onlineUser!=null){
			updateUserInfo(irpUser, onlineUser);
		}
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param toUser
	 */
	private static void updateUserInfo(IrpUser newUser, IrpUser toUser) {
		if(newUser.getNickname()!=null && !newUser.getNickname().equals(toUser.getNickname())){
			toUser.setNickname(newUser.getNickname());
		}
		if(newUser.getTruename()!=null && !newUser.getTruename().equals(toUser.getTruename())){
			toUser.setTruename(newUser.getTruename());
		}
		if(newUser.getSex()!=null && (toUser.getSex()==null || newUser.getSex().intValue()!=toUser.getSex().intValue())){
			toUser.setSex(newUser.getSex());
		}
		if(newUser.getBirthday()!=null && (toUser.getBirthday()==null || newUser.getBirthday().getTime()!=toUser.getBirthday().getTime())){
			toUser.setBirthday(newUser.getBirthday());
		}
		if(newUser.getEmail()!=null && !newUser.getEmail().equals(toUser.getEmail())){
			toUser.setEmail(newUser.getEmail());
		}
		if(newUser.getMobile()!=null && !newUser.getMobile().equals(toUser.getMobile())){
			toUser.setMobile(newUser.getMobile());
		}
		if(newUser.getTel()!=null && !newUser.getTel().equals(toUser.getTel())){
			toUser.setTel(newUser.getTel());
		}
		if(newUser.getQq()!=null && !newUser.getQq().equals(toUser.getQq())){
			toUser.setQq(newUser.getQq());
		}
		if(newUser.getProvince()!=null && !newUser.getProvince().equals(toUser.getProvince())){
			toUser.setProvince(newUser.getProvince());
		}
		if(newUser.getCity()!=null && !newUser.getCity().equals(toUser.getCity())){
			toUser.setCity(newUser.getCity());
		}
		if(newUser.getArea()!=null && !newUser.getArea().equals(toUser.getArea())){
			toUser.setArea(newUser.getArea());
		}
		if(newUser.getLocation()!=null && !newUser.getLocation().equals(toUser.getLocation())){
			toUser.setLocation(newUser.getLocation());
		}
		if(newUser.getExpertintro()!=null && !newUser.getExpertintro().equals(toUser.getExpertintro())){
			toUser.setExpertintro(newUser.getExpertintro());
		}
		if(newUser.getUserpic()!=null && !newUser.getUserpic().equals(toUser.getUserpic())){
			toUser.setUserpic(newUser.getUserpic());
		}
		if(newUser.getQqtoken()!=null && !newUser.getQqtoken().equals(toUser.getQqtoken())){
			toUser.setQqtoken(newUser.getQqtoken());
		}
		if(newUser.getQquserid()!=null && !newUser.getQquserid().equals(toUser.getQquserid())){
			toUser.setQquserid(newUser.getQquserid());
		}
		if(newUser.getWeibotoken()!=null && !newUser.getWeibotoken().equals(toUser.getWeibotoken())){
			toUser.setWeibotoken(newUser.getWeibotoken());
		}
		if(newUser.getWeibouserid()!=null && !newUser.getWeibouserid().equals(toUser.getWeibouserid())){
			toUser.setWeibouserid(newUser.getWeibouserid());
		}
	}

	/**
	 * 根据用户ID查询用户对象
	 * @param _nUserId
	 * @return
	 */
	public static IrpUser findUserById(long _nUserId) {
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpUserService irpUserService = (IrpUserService) ac.getBean("irpUserService");
		return irpUserService.findUserByUserId(_nUserId);
	}
}
