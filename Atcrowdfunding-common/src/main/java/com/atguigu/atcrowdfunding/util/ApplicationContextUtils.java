package com.atguigu.atcrowdfunding.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext工具类，用来设置application对象
 * @author dc
 *
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	
	public static ApplicationContext applicationContext ;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}

}
