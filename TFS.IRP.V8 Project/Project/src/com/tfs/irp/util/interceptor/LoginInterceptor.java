package com.tfs.irp.util.interceptor;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Parameter;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.license.CryptUtil;
import com.tfs.irp.util.license.GetMacAddr;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.service.IrpValueConfigService;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {
	private String[] aValue;

	/* init 方法 ，仅创建Interceptor时调用一次 */
	public void init() {
	}

	/* destroy 方法 ，仅摧毁Interceptor时调用一次 */
	public void destroy() {
	}

	/* intercpte 方法，Interceptor创建后，每次拦截调用 */
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
/*		if (!CryptUtil.checkLicense(ac.getApplication().get("IRP_LICENSE")
				.toString())) {
			throw new ServletException("注册码已过期！请使用机器码["+GetMacAddr.getMachineCode()+"]重新申请注册码");
		}*/
		aValue = ac.getApplication().get("notCheckFilter").toString()
				.split(",");
		HttpServletRequest request = ServletActionContext.getRequest();
		String token  = request.getParameter("token");
		if(token == null){
			// 判断是否不验证地址
			if (!isNotCheckFilter(ac.getName()) && LoginUtil.getLoginUser() == null) {
				LoginUtil.logout();
				String sRequestURL = ServletActionContext.getRequest().getRequestURI();
				sRequestURL = sRequestURL.substring(sRequestURL.indexOf('/', 1)+1);
				String sAppUrl = ServletActionContext.getRequest().getContextPath();
				Map<String,String[]> parameters = ServletActionContext.getRequest().getParameterMap();
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
				ServletActionContext.getResponse().sendRedirect(sAppUrl+"/login.action?requesturl="+java.net.URLEncoder.encode(sRequestURL+sQuestStr, "UTF-8"));
				return null;
			}
		}else{
			mobileAction.getlogin(token);
		}
		
		String result = invocation.invoke(); // 继续调用后续拦截器
		// 拦截器之后的代码
		if (!"error".equals(result)) {
			String sMedhotName = ac.getName();
			//浏览知识积分在DocumentAction里单独处理。
			if("showdocumentinfo".equals(sMedhotName)){
				return result;
			}
			IrpValueConfigService valueConfigService = getIrpValueConfigService();
			List<IrpValueConfig> list = valueConfigService.findValueConfigByMethodName(sMedhotName);
			if (list != null && list.size() > 0) {
				for (IrpValueConfig irpValueConfig : list) {
					Long nUserId = null;
					if (irpValueConfig.getParameters() != null
							&& irpValueConfig.getParameters().length() > 0) {
						// 有方法状态参数
						try {
							String sParam = irpValueConfig.getParameters();

							Map<String, String> mParam = new HashMap<String, String>();
							String[] aParam = sParam.split("=");
							mParam.put(aParam[0], aParam[1]);

							Object objdao = getBean(irpValueConfig.getBeandao());
							String beankey = irpValueConfig.getBeanidname();
							Parameter value = ac.getParameters().get(
									beankey);
							Object[] oParam = new Object[1];
							oParam[0] = new Long(value.getValue());
							Object returnObj = getMethod(objdao, "selectByPrimaryKey", oParam);
							// 获得返回对象的获得用户值
							Object statusid = getMethod(returnObj,
									letterFirstToUpper(aParam[0]),
									new Object[0]);
							if (statusid != null) {
								statusid = Long.valueOf(statusid.toString());
							}
							Object cruserid = getMethod(returnObj,
									letterFirstToUpper(irpValueConfig
											.getUsername()), new Object[0]);
							if (cruserid != null) {
								nUserId = Long.valueOf(cruserid.toString());
							}

							if (aParam[1].toString()
									.equals(statusid.toString())) {
								// 执行对应的操作
								// 增加总积分和经验
								this.getIrpUserService().updateUserSumScoreEx(
										nUserId, irpValueConfig.getScore(),
										irpValueConfig.getExperience());
								// 增加具体积分和经验
								this.addScore(nUserId,
										irpValueConfig.getName(),
										irpValueConfig.getScore(),
										irpValueConfig.getExperience());
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						// 没有方法状态参数
						if (irpValueConfig.getBeandao() != null
								&& irpValueConfig.getBeanidname() != null) {
							// 包含dao和主键
							String sBeandao = irpValueConfig.getBeandao();
							Object objdao = getBean(sBeandao);
							
							String beankey = irpValueConfig.getBeanidname();
							Object[] oParam = new Object[1];
							/*String[] value = (String[]) ac.getParameters().get(
									beankey);*/
							Parameter value =  ac.getParameters().get(
									beankey);

							oParam[0] = new Long(value.getValue());

							Object returnObj = getMethod(objdao,
									"selectByPrimaryKey", oParam);

							// 获得返回对象的获得用户值
							Object cruserid = getMethod(returnObj,
									letterFirstToUpper(irpValueConfig
											.getUsername()), new Object[0]);
							if (cruserid != null) {
								nUserId = Long.valueOf(cruserid.toString());
							}

							// 增加总积分和经验
							this.getIrpUserService().updateUserSumScoreEx(
									nUserId, irpValueConfig.getScore(),
									irpValueConfig.getExperience());
							// 增加具体积分和经验
							this.addScore(nUserId, irpValueConfig.getName(),
									irpValueConfig.getScore(),
									irpValueConfig.getExperience());
						} else {
							// 不包含dao和主键
							nUserId = LoginUtil.getLoginUserId();
							// 增加总积分和经验
							this.getIrpUserService().updateUserSumScoreEx(
									nUserId, irpValueConfig.getScore(),
									irpValueConfig.getExperience());
							// 增加具体积分和经验
							this.addScore(nUserId, irpValueConfig.getName(),
									irpValueConfig.getScore(),
									irpValueConfig.getExperience());
						}
					}

				}
			}
		}
		return result;
	}

	private IrpValueConfigService getIrpValueConfigService() {
		ApplicationContext wac = ApplicationContextHelper.getContext();
		return (IrpValueConfigService) wac.getBean("irpValueConfigServiceImpl");
	}

	private IrpUserValueLinkService getIrpUserValueLinkService() {
		ApplicationContext wac1 = ApplicationContextHelper.getContext();
		return (IrpUserValueLinkService) wac1
				.getBean("irpUserValueLinkService");
	}

	private IrpUserService getIrpUserService() {
		ApplicationContext wac1 = ApplicationContextHelper.getContext();
		return (IrpUserService) wac1.getBean("irpUserService");

	}

	/**
	 * 增加具体积分
	 * 
	 * @param _userid
	 * @param _valueckey
	 */
	private void addScore(long _userid, String _valueckey, long _score,
			long _experience) {

		IrpUserValueLink irpUserValueLink = new IrpUserValueLink();

		// 执行新增积分
		irpUserValueLink.setUserid(_userid);
		irpUserValueLink.setValueckey(_valueckey);
		irpUserValueLink.setScore(_score);
		irpUserValueLink.setExperience(_experience);
		this.getIrpUserValueLinkService().addIrpUserValueLink(irpUserValueLink);
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

	private Object getBean(String sBeanName) {
		ApplicationContext wac = ApplicationContextHelper.getContext();
		return wac.getBean(sBeanName);
	}

	private Object getMethod(Object owner, String methodName, Object[] args)
			throws Exception {
		Class ownerClass = owner.getClass();
		Class[] argsClass = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(owner, args);
	}

	private String letterFirstToUpper(String _str) {
		char[] ch = _str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (i == 0) {
				ch[0] = Character.toUpperCase(ch[0]);
			}
		}
		StringBuffer a = new StringBuffer();
		a.append(ch);
		return "get" + a.toString();
	}

}
