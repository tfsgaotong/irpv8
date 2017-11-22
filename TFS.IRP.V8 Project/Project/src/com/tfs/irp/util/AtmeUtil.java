package com.tfs.irp.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.microblogatme.service.IrpMicroblogAtmeKeyService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;

public class AtmeUtil {
	
	private IrpUserService irpUserService;
	private IrpMicroblogAtmeKeyService irpMicroblogAtmeKeyService;
	private IrpUserPrivacyService irpUserPrivacyService;
	private IrpMicroblogFocusService irpMicroblogFocusService;
	public ServletContext servletContext;
	public AtmeUtil(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpUserService =(IrpUserService) ac.getBean("irpUserService");
		irpMicroblogAtmeKeyService=(IrpMicroblogAtmeKeyService) ac.getBean("irpMicroblogAtmeKeyServiceImpl");
		irpUserPrivacyService=(IrpUserPrivacyService) ac.getBean("irpUserPrivacyService");
		irpMicroblogFocusService=(IrpMicroblogFocusService) ac.getBean("irpMicroblogFocusServiceImpl");
		
	}
	/**
	 * 处理@ 发送@信息
	 * @param _content  发送的内容
	 * @param _microblogid  发送所在微知的id
	 * @return
	 */
	public  void disposeATME(String _content,Long _microblogid){
		
		List<IrpUser> irpUser = null;
		
		 Set set = getAtmeSet(_content);
		 if(set.size()>0){
		 Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			
			irpUser = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(iterator.next().toString()));
			if(irpUser.isEmpty()==false){
				IrpMicroblogAtmeKey irpMicroblogAtmeKey = new IrpMicroblogAtmeKey();
				Long userid = irpUser.get(0).getUserid();
				boolean privacyatme = getWhetherAtme(userid);
				if(privacyatme!=false){
					irpMicroblogAtmeKey.setUserid(userid);
					irpMicroblogAtmeKey.setMicroblogid(_microblogid);
					irpMicroblogAtmeKeyService.addMicroblogAtmeKey(irpMicroblogAtmeKey);
				}
				
			}
			}
		}		
				
	}
	
	/**
	 * 判断当前登录用户是否能Atme到指定的用户
	 * @return
	 */
	public boolean getWhetherAtme(Long _userid){
		
		boolean  conftypeatme = false;	
		
		IrpUserPrivacy irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(_userid, IrpUserPrivacy.MICROBLOGATME);
			
		int commenttype =  irpUserPrivacy.getPrivacyvalue();
		
		Long loginuserid = LoginUtil.getLoginUserId();
		
		if(commenttype==IrpUserPrivacy.ALLUSER){
			//不包括黑名单里的所有人
			conftypeatme = true;
		}else if(commenttype==IrpUserPrivacy.BYFOCUSUSER){
			//关注我的人
		if(_userid.equals(loginuserid)){
				
			conftypeatme = true;
				
			}else{
				
				List<IrpMicroblogFocus> focuslist =	this.irpMicroblogFocusService.findMicroblogUserId(_userid);
				
		          for (int i = 0; i <focuslist.size(); i++) {
					
					if(loginuserid.equals(focuslist.get(i).getFocususerid())){
						
						conftypeatme = true;
						break;
					 }
				}
				
			}
		}else if(commenttype==IrpUserPrivacy.FOCUSUSER){

			//我关注的人
			if(_userid.equals(loginuserid)){
				
				conftypeatme = true;
				
			}else{
				
				List<IrpMicroblogFocus> byfocuslist =	this.irpMicroblogFocusService.findMicroblogFocusUserId(_userid);

				for (int i = 0; i <byfocuslist.size(); i++) {

					if(loginuserid.equals(byfocuslist.get(i).getUserid())){				
						conftypeatme = true;
						break;
					 }	
				}	
				
			}
		}else{
			conftypeatme = true;
		}
			
			return conftypeatme;
		
	}
	/**
	 * 获得@ 真是存在的人
	 * @return
	 */
	public Set getAtmeSet(String _str){
		
		Set set = new HashSet();
		_str = _str.replace(","," ").replace("."," ").replace(";"," ").replace(":"," ").replace("，"," ").replace("。"," ").replace("；"," ").replace("："," ");
		_str = _str.replaceAll("\\s{1,}"," ");
		String strarray[] = _str.split("@");
		for (int i = 1; i < strarray.length; i++) {
			String strone = "";
			if(strarray[i].indexOf(" ")>0){
				strone = strarray[i].substring(0,strarray[i].indexOf(" ")).replace("<br/>","");		
			}else{
				strone = strarray[i].replace("<br/>","");
			}
		
	      List<IrpUser> irpUser = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(strone));
	      
	      if(irpUser.isEmpty()==false){
	    	
	    	  set.add(strone);
	    	  
	    	  
	      }

		}
		
		return set;
	}  
	/**
	 * 给所有的@的人 加链接
	 * @param _str
	 * @return
	 */
	public String getAtmeStr(String _str){
		List<IrpUser> irpUser = null;
		
		 Set set = getAtmeSet(_str);
		 
		 if(set.size()>0){
		 Iterator iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String users = iterator.next().toString();
			 
			 irpUser = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(users));	
			 
			 _str = _str.replace("@"+users,"<a target=\"_bank\" href=\""+getRootPath()+"site/client_to_index_person.action?personid="+irpUser.get(0).getUserid()+"\" class=\"linkb14\">@"+users+"</a>");	
		}
		 }
		return _str;
	}
	/**
	 * 获取当前路径
	 * @return
	 */
	public String getRootPath(){
		String _location = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		_location = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		return _location;
	}
	
}
