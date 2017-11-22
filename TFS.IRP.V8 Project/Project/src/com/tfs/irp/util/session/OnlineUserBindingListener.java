package com.tfs.irp.util.session;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.directwebremoting.ScriptSession;
import org.springframework.context.ApplicationContext;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;

public class OnlineUserBindingListener implements HttpSessionBindingListener {
	private final String ONLINE_USER_NAME = "OnlineUserList";
	
	IrpUser loginUser;
	
	public IrpUser getLoginUser() {
		return loginUser;
	}

	public OnlineUserBindingListener(IrpUser user){
        this.loginUser = user;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        // 把用户名放入在线列表
        Object object = application.getAttribute(ONLINE_USER_NAME);
        Map<String, IrpUser> onlineUserList = null;
        // 第一次使用前，需要初始化
        if (object == null) {
        	onlineUserList = new HashMap<String, IrpUser>();
            application.setAttribute(ONLINE_USER_NAME, onlineUserList);
        }else{
        	onlineUserList = (Map<String, IrpUser>) object;
        	
        }
        onlineUserList.put(this.loginUser.getUsername(), this.loginUser); 
       

	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        //注销日志
        LogUtil logUtil = new LogUtil("USER_LOGOUT", loginUser, application);
        
        //注销后修改用户登录时间
        ApplicationContext ac = ApplicationContextHelper.getContext();
        IrpUserDAO irpUserDAO = (IrpUserDAO) ac.getBean("irpUserDAO");
		IrpUser updateIrpUser = new IrpUser();
		updateIrpUser.setUserid(loginUser.getUserid());
		updateIrpUser.setLastlogouttime(new Date());
		try {
			irpUserDAO.updateByPrimaryKeySelective(updateIrpUser);
		} catch (SQLException e) {
			logUtil.errorLogs(e);
			e.printStackTrace();
		}
 
        // 从在线列表中删除用户名
		Map<String, IrpUser> onlineUserList = (Map<String, IrpUser>) application.getAttribute(ONLINE_USER_NAME);
        onlineUserList.remove(loginUser.getUsername());
        String scriptsessionstr = "scriptsession"+loginUser.getUserid();
        onlineUserList.remove(scriptsessionstr);     
        
        logUtil.successLogs();
	}

}
