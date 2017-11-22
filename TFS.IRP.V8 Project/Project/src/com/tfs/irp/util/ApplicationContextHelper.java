package com.tfs.irp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}
	
	public static ApplicationContext getContext(){
		return applicationContext;
	}
}
