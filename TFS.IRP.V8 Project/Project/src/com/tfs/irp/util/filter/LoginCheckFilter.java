package com.tfs.irp.util.filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.license.CryptUtil;
import com.tfs.irp.util.license.GetMacAddr;

@SuppressWarnings("serial")
public class LoginCheckFilter extends HttpServlet implements Filter {
	private String[] aValue;
	
	public static String DBNAME = "";

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
/*		if (!CryptUtil.checkLicense(session.getServletContext()
				.getAttribute("IRP_LICENSE").toString())) {
			throw new ServletException("注册码已过期！请使用机器码["
					+ GetMacAddr.getMachineCode() + "]重新申请注册码");
		}*/
		
		String sAppUrl = request.getContextPath();
		String sUri = request.getRequestURI();
		String sPageName = sUri.substring((sUri.lastIndexOf('/') + 1));
		RightUtil rightUtil = new RightUtil();
		
		if (isNotCheckFilter(sPageName) || sUri.equals(sAppUrl + "/")) {
			filterChain.doFilter(request, response);
		} else if ((session == null || LoginUtil.getLoginUser(session) == null)) {
			LoginUtil.logout(session);
			String sRequestURL = request.getRequestURI();
			sRequestURL = sRequestURL.substring(sRequestURL.indexOf('/', 1)+1);
			Map<String,String[]> parameters = request.getParameterMap();
			Iterator<String> iterator = parameters.keySet().iterator();
			String sQuestStr = "";
			while (iterator.hasNext()) {
				String sKey = iterator.next();
				String sValue = parameters.get(sKey)[0];
				if(sQuestStr.equals("")){
					sQuestStr = "?"+sKey+"="+sValue;
				}else{
					sQuestStr += "&"+sKey+"="+sValue;
				}
			}
			response.sendRedirect(sAppUrl+"/login.action?requesturl="+java.net.URLEncoder.encode(sRequestURL+sQuestStr, "UTF-8"));
		} else if (sUri.indexOf("/admin/index.jsp") >= 0) {
			List<IrpManagementOper> list = rightUtil.findManagementOper(LoginUtil.getLoginUser(session));
			String sType = request.getParameter("type");
			if(list!=null && list.size()>0 && isExist(list, sType)){
				filterChain.doFilter(request, response);
			}else{
				LoginUtil.logout(session);
				response.sendRedirect(sAppUrl);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
	private boolean isExist(List<IrpManagementOper> list, String sType){
		boolean success = false;
		if(sType==null || sType.length()==0){
			sType = list.get(0).getOpername();
		}
		for (IrpManagementOper irpManagementOper : list) {
			if(irpManagementOper==null)
				continue;
			if(irpManagementOper.getName().equals(sType)){
				success = true;
				break;
			}
		}
		return success;
	}

	private boolean isNotCheckFilter(String _sPageName) {
		boolean isCheck = false;
		for (int i = 0; i < aValue.length; i++) {
			if (_sPageName.equals(aValue[i])) {
				isCheck = true;
				break;
			}
		}
		return isCheck;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String sNotCheckFilter = filterConfig.getServletContext()
				.getInitParameter("notCheckFilter"); 
		DBNAME = PropertiesInfo();
		this.aValue = sNotCheckFilter.split(",");
	}
	public static String PropertiesInfo() {
		Properties prop = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String classesPath = path.substring(6);
		if(classesPath.indexOf(":")==-1){
			classesPath = path.substring(5);
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(classesPath + "/sqlMapping.properties");
			prop.load(in); 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String dbdriver = prop.getProperty("jdbc.driverClassName");
		String dbname = "qita";
		if(dbdriver.contains("oracle")==true){
			dbname = "oracle";
		}else if(dbdriver.contains("mysql")==true){
			dbname = "mysql";
		}else if(dbdriver.contains("sqlserver")==true){
			dbname = "sqlserver";
		}  
		return dbname;
	}
	/**
	 * 验证数据哭名称中是否含有关键字
	 * @return
	 */
	public static String PropertiesKeyWord() {
		Properties prop = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String classesPath = path.substring(6);
		FileInputStream in = null;
		try {
			in = new FileInputStream(classesPath + "/keywords.properties");
			prop.load(in); 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String words="";
		String dbdriver= prop.getProperty("keywords");
		if(dbdriver!=""){
			words=dbdriver;
		}
		return words;
	}
}
