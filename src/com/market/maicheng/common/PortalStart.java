package com.market.maicheng.common;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


/**
 * spring启动加载类
 * 用于项目启动添加加载项
 * @author Snake-note
 *
 */
public class PortalStart implements ServletContextListener {
	static Logger logger = Logger.getLogger(PortalStart.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("######################Spring启动##############################");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
