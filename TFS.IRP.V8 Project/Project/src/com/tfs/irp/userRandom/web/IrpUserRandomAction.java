package com.tfs.irp.userRandom.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userRandom.entity.IrpUserrandom;
import com.tfs.irp.userRandom.service.IrpUserRandomService;
import com.tfs.irp.userRandom.util.EmailUtils;
import com.tfs.irp.util.ActionUtil;

public class IrpUserRandomAction extends ActionSupport {

	private IrpUser irpUser;
	private IrpUserRandomService irpUserRandomService;
	private IrpUserService irpUserService;
	private String email;
	private String checkCode;
	private List<IrpUser> irp;
	private IrpUserrandom irpUserRandom;
	private String sendEmails;
	private String friendlyshow;
	private String userName;
	private String passWord;
	private String name;
	private String checkalphabet;
	
	
	public String getCheckalphabet() {
		return checkalphabet;
	}

	public void setCheckalphabet(String checkalphabet) {
		this.checkalphabet = checkalphabet;
	}

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSendEmails() {
		return sendEmails;
	}

	public void setSendEmails(String sendEmails) {
		this.sendEmails = sendEmails;
	}
	
	public IrpUserrandom getIrpUserRandom() {
		return irpUserRandom;
	}

	public void setIrpUserRandom(IrpUserrandom irpUserRandom) {
		this.irpUserRandom = irpUserRandom;
	}

	public List<IrpUser> getIrp() {
		return irp;
	}

	public void setIrp(List<IrpUser> irp) {
		this.irp = irp;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public IrpUserRandomService getIrpUserRandomService() {
		return irpUserRandomService;
	}

	public void setIrpUserRandomService(IrpUserRandomService irpUserRandomService) {
		this.irpUserRandomService = irpUserRandomService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public String validateuser() {
		return SUCCESS;
	}
	
	/**
	 * 验证用户
	 * @return
	 */
	public String findUser(){
		HttpServletRequest request =ServletActionContext.getRequest();
		String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(checkalphabet != "" && !checkalphabet.equals("")){
			if(kaptchaExpected.equalsIgnoreCase(checkalphabet)){
				request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "");
				List<IrpUser> irp = irpUserService.findUserByUsername(irpUser.getUsername());
					if(irp.size()<=0 || irp==null){
						friendlyshow="用户不存在";
						return ERROR;
					}else{
						irpUser =irp.get(0);
						return SUCCESS;
					}
			}
			request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "");
			friendlyshow="验证码输入有误";
			return ERROR;
		}
		request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "");
		friendlyshow="请输入验证码！";
		return ERROR;
	}
	/**
	 * 发送邮件
	 */
	public void sendEmail(){
			int nRows=0;
				List<IrpUser> list =irpUserService.finds(email,irpUser.getUsername());			
				if(list.size()<=0){
					nRows=0;
				}else{
					int j= (int) ((Math.random()*9+1)*100000);
					String randoms = String.valueOf(j);
					int users=irpUserRandomService.addUserRandom(list.get(0),randoms);
					if(users>0){
					boolean flog =EmailUtils.sendResetPasswordEmail(list.get(0),randoms);				
						if(flog){	
							sendEmails="密码找回链接已发至您的邮箱:"+list.get(0).getEmail()+",请前往修改密码！";
							nRows=1;
						}else{
							nRows=2;
						}
					}else{
						nRows =2;
					}
				}									
			ActionUtil.writer(String.valueOf(nRows));
		}
	/**
	 * 验证链接
	 * @return
	 */
	public String Findlink(){
		List<IrpUser> irp =irpUserService.findUserByUsername(userName);
		if(irp.size()<=0 || irp==null){
			friendlyshow ="用户不存在";
			return ERROR;
		}else{
			List<IrpUserrandom> random =irpUserRandomService.findUserRandom(irp.get(0).getUserid());
			if(random.size()<=0 || random==null){
				friendlyshow="链接不存在";
				return ERROR;
			}else{
				Date date=random.get(0).getSendtime();
				Date date1 = new Date();				
				if(date1.getTime()-1800000<date.getTime()){
					if(userName.equals(irp.get(0).getUsername()) & checkCode.equals(random.get(0).getRandoms())){
						return SUCCESS;
					}else{
						friendlyshow="当前链接出错";
						return ERROR;
					}
				}else{
					friendlyshow="链接超时";
					irpUserRandomService.delUserRandom(random.get(0).getRandomid());
					return ERROR;
				}
				
			}
		}
	}	
	/**
	 * 修改密码
	 */
	public void updatePwd(){
		List<IrpUser> irp = irpUserService.findUserByUsername(userName);
		int nRows=0;
		if(irp.size()<=0 || irp==null){
			return;
		}else if(passWord==null || "".equals(passWord)){
			return;
		}else{
			List<IrpUserrandom> random =irpUserRandomService.findUserRandom(irp.get(0).getUserid());			
			if(random.size()<=0 || random==null){
				return;
			}if(checkCode.equals(random.get(0).getRandoms())){
				nRows = irpUserService.updatePassWordByUserids(irp.get(0).getUserid()+"", passWord);
				if(nRows>0){
					irpUserRandomService.delUserRandom(random.get(0).getRandomid());
				}else{
					nRows=0;
				}
			}
		}
		ActionUtil.writer(String.valueOf(nRows));
	}
}
