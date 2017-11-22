package com.tfs.irp.mobile.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.config.service.IrpConfigService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.erWM;

/**
 * @author Administrator
 *
 */
public class MobileLogin extends ActionSupport{
	
	private HttpServletRequest request;
	
	public MobileLogin(){
		
		request = ServletActionContext.getRequest();
	}
	
	private IrpUserService userservice;

	private IrpConfigService configservice;
	
	/***
	 * irp_config
	 * 根据key取value
	 * @return
	 */ 
	public void getCkvalByCkey(){
		String ckey = request.getParameter("ckey");
		
		String cvalue = "";
		if (ckey.isEmpty()==false) {
			cvalue = "[{\"result\":"+"\""+this.configservice.selectCValueByCKey(ckey)+"\"}]";
		}    
		ActionUtil.writer(cvalue);
	}
	private String sessionid;
	/***
	 * 返回二维码页面
	 * @return
	 */
	public String eWM(){
		HttpSession session = request.getSession();
		//生成二维码
		String sessionidstr = session.getId();
		
		String imgPath = SysConfigUtil.getSysConfigValue("MOBILELOGINADDRADDR")+sessionidstr+".jpg";
		/*String imgPath = request.getRealPath("\\")+sessionidstr+".jpg"; */
        String content = SysConfigUtil.getSysConfigValue("MOBILELOGINADDR")+sessionidstr;  
  
        erWM handler = new erWM();  
        handler.encoderQRCode(content, imgPath);  
  
        System.out.println("encoder QRcode success");  
        
		sessionid =  SysConfigUtil.getSysConfigValue("MOBILELOGINADDRPATH")+sessionidstr+".jpg";
		return SUCCESS;
	}
	
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	 * 接收用户信息并保存
	 * @return
	 */
	public void mobileLoginBoolew(){
		HttpSession session = request.getSession();
		 
		String utoken = "";
		String str = request.getParameter("str");
		String pic = request.getParameter("pic");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String s_1 = sdf.format(Calendar.getInstance().getTime());
		long today = Long.valueOf(s_1);
		long d_1 = Long.parseLong(str.substring(8));
	 
		long d_2 = Long.parseLong(s_1)/5+1;
		long token =  (d_1/d_2)-today;
		utoken = ""+token;
		System.out.println("token=="+token);
		session.setAttribute("mobiletoken", utoken); 
		session.setAttribute("mobilepic", pic);
		//获取uname和utoken
		
		long s_5 = 0;
		if (str!=null && str.isEmpty()==false) {
			//验证用户是否存在
			if (utoken!=null && utoken.isEmpty()==false) {
				IrpUser user = this.userservice.boolUserTokenAndUser(utoken);
				if (user!=null) {
				long tokenlong = Long.parseLong(str); 

				 
				System.out.println("tokenlong==="+tokenlong);
				long s_2 = Long.parseLong(s_1)*3+4+tokenlong;
				
				System.out.println("s_2==="+s_2); 
				
				long s_3 = Long.parseLong(Long.toOctalString(Long.parseLong(s_1)))+today;
				System.out.println("s_3==="+s_3);
				long s_4 = Long.parseLong("1"+s_3+"1");
				System.out.println("s_4==="+s_4);
				
				s_5 = s_2+s_4;
				
				System.out.println("s_5=="+s_5+"ppp"+session.getAttribute("mobiletoken"));
				
				
				}else{ 
					//非法状态  查询到的用户名为空 
				}  
			}else{
				//非法状态    用户名和密码没写 
			} 
		}else{
			//非法状态  没有请求参数 
		}
		String cvalue = "[{\"result\":"+"\""+s_5+"\"}]";
		ActionUtil.writer(cvalue);
	} 
	/**
	 * 确认登录
	 */
	public String confirmLogin(){
		HttpSession session = request.getSession();
		Object mtobj = session.getAttribute("mobiletoken");
		Object mtpic = session.getAttribute("mobilepic");
		
		
		ServletContext application = request.getServletContext();
		
		
		System.out.println("mtobj=="+mtobj);
		if (mtobj!=null) {
			//IrpUser user = this.userservice.boolUserTokenAndUser(mtobj.toString());
			
			application.setAttribute(""+mtobj,""+mtpic);
		} 
		System.out.println("接收成功-----");
		return SUCCESS;
	}
	/**
	 * 循环判断session
	 */
	public void lgUserGs(){ 
		int msg = 0;  
		
		ServletContext application = request.getServletContext(); 
		
		String sessid = request.getSession().getId();
		
		if (application!=null) {
			Enumeration<String> attributeNames = application.getAttributeNames();
	        while (attributeNames.hasMoreElements()) {
	            String nextElement = attributeNames.nextElement(); 
	            Object attribute = application.getAttribute(nextElement);
	            if (sessid.equals(attribute)) {
	            	IrpUser user = this.userservice.boolUserTokenAndUser(nextElement);
	    			if (user!=null) {
	    				msg = this.userservice.LoginEW(user);
	    			} 
				} 
	            
	        }
		 
		}  
		ActionUtil.writer(msg+"");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		
		
		
		
		
		
		
		
		
		//System.out.println(Long.toOctalString(25));
/*			Long cc = 110016666666816l;
			System.out.println(cc*3+4+12312313123l);*/
/*		int a = 25;
		int b = Integer.parseInt(Integer.toOctalString(a))+25;
		String cc = "1"+b+"1";
		System.out.println(cc);
		
		System.out.println(Integer.valueOf(cc.substring(1, cc.length()-1))-25);
		System.out.println(Integer.valueOf(Integer.valueOf(cc.substring(1, cc.length()-1))-25+"",8));
		
		
		*/
		
/*		String utoken = "1111111111";
		String str = "0001100174220";
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String s_1 = sdf.format(Calendar.getInstance().getTime());
		System.out.println("s_1"+s_1);
		long d_1 = Long.parseLong(str.substring(8));
		System.out.println("d_1="+d_1);
		long d_2 = Long.parseLong(s_1)/5+1;
		System.out.println("d_2="+d_2);
		long token =  d_1/d_2-25;
		utoken = ""+token;
		System.out.println("token=="+token);*/
	/*	String dd = "a";
		if (dd!=null && dd.isEmpty()==false) {
			System.out.println("aa");	
		}
		*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	public IrpUserService getUserservice() {
		return userservice;
	}
	public void setUserservice(IrpUserService userservice) {
		this.userservice = userservice;
	} 
	public IrpConfigService getConfigservice() {
		return configservice;
	}
	public void setConfigservice(IrpConfigService configservice) {
		this.configservice = configservice;
	}
}
