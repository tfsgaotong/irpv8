package com.tfs.irp.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tfs.irp.util.license.CryptUtil;

public class ServerSocketListener implements ServletContextListener {
	private ServletContext context = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		this.context = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		context.setAttribute("IRP_LICENSE", CryptUtil.findLocalLicense());
	}

}
