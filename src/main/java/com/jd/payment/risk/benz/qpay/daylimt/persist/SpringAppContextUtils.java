package com.jd.payment.risk.benz.qpay.daylimt.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppContextUtils {

	private static Logger lgr = LoggerFactory.getLogger(SpringAppContextUtils.class);
	
	private static final String[] configLocation = 
			new String[] { "classpath:/spring/spring-config-common-hummer-benz.xml"};

	private static ApplicationContext appContext = null;
	
	
	
	public static void init(){
		lgr.debug("spring load application config xml ....");
		appContext = new ClassPathXmlApplicationContext(configLocation);
		lgr.info(" spring context inited over..............");
	}
	
	public static ApplicationContext getSpringContext(){
		
		return appContext;
	}
}
