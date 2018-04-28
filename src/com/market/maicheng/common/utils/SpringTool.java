package com.market.maicheng.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 通过该类即可在普通工具类里获取spring管理的bean 
 * @author Shinobi
 *
 */
public class SpringTool implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (SpringTool.applicationContext == null) {  
			SpringTool.applicationContext = applicationContext;  
		}
		
	}
	
	public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    public static Object getBean(String name) {  
        return getApplicationContext().getBean(name);  
    }  

}
