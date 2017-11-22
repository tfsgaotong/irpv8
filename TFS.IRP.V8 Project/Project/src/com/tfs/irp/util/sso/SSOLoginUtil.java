package com.tfs.irp.util.sso;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ApplicationContextHelper;

public class SSOLoginUtil {
	
	private String sSessionId;
	
	public SSOLoginUtil(String sSessionId) {
		this.sSessionId=sSessionId;
	}
	
	public boolean SSOLogin() {
		String sUri=getRootPath()+"user/sso.action";
		String sEncode="UTF-8";
		String sUnameKey="email";
		String sPwdKey="password";
		String sDefaultPwd="tfsadmin";
		String sTrueNameKey="name";
		String sEmailKey="email";
		String sSexKey="sex";
		String sSexMVal="男";
		String sSexFVal="女";
		String sIDKey="id";
		String sContent = sandHttpRequest(sUri, sEncode);
		try {
			JSONObject jsonObj = JSONObject.fromObject(sContent);
			String sUserName = jsonObj.getString(sUnameKey);
			if(sUserName!=null && !sUserName.isEmpty()){
				IrpUserService userService  = getIrpUserService();
				List<IrpUser> users = userService.findUserByUsername(sUserName);
				if(users==null || users.isEmpty()){
					IrpUser regUser = new IrpUser();
					regUser.setUsername(sUserName);
					if(sPwdKey!=null && !sPwdKey.isEmpty()){
						regUser.setPassword(jsonObj.getString(sPwdKey));
					}else{
						regUser.setPassword(sDefaultPwd);
					}
					if(sTrueNameKey!=null && !sTrueNameKey.isEmpty()){
						regUser.setTruename(jsonObj.getString(sTrueNameKey));
					}
					if(sEmailKey!=null && !sEmailKey.isEmpty()){
						regUser.setEmail(jsonObj.getString(sEmailKey));
					}
					if(sIDKey!=null && !sIDKey.isEmpty()){
						regUser.setSynsrcid(jsonObj.getString(sIDKey));
					}
					if(sSexKey!=null && !sSexKey.isEmpty()){
						Integer nSex = null;
						String sSex = jsonObj.getString(sSexKey);
						if(sSexMVal.equals(sSex)){
							nSex = new Integer(1);
						}else if (sSexFVal.equals(sSex)) {
							nSex = new Integer(2);
						}
						regUser.setSex(nSex);
					}
					userService.regUser(regUser);
				}
				int nLoginStatus = userService.loginByName(sUserName);
				if(nLoginStatus==2 || nLoginStatus==3){
					return true;
				}
			}else{
				return false;
			}
		} catch (JSONException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * 发送请求获得数据
	 * @param _sUri
	 * @param _sEncodeing
	 * @return
	 */
	private String sandHttpRequest(String _sUri, String _sEncode) {
		String sContent = null;
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(_sUri);
		if(this.sSessionId==null)
			return "";
		method.setRequestHeader("Cookie", "JSESSIONID="+this.sSessionId);
		method.setRequestHeader("Content-Type", "text/html;charset="+_sEncode);
		try {
			int nStatus = client.executeMethod(method);
			if(HttpStatus.SC_OK==nStatus){
				sContent = new String(method.getResponseBody(), _sEncode);
			}else{
				sContent = new String();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sContent;
	}
	
	private IrpUserService getIrpUserService() {
		ApplicationContext wac1 = ApplicationContextHelper.getContext();
		return (IrpUserService) wac1.getBean("irpUserService");
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
