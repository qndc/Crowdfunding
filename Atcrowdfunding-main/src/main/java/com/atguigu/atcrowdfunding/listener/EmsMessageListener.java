package com.atguigu.atcrowdfunding.listener;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.*;

import com.atguigu.atcrowdfunding.util.SmsUtil;
import com.google.gson.Gson;


public class EmsMessageListener implements MessageListener{
	
	private static final String SIGNNAME = "我的学习分享";

	@Override
	public void onMessage(Message message) {
		try {
			if (message != null && message instanceof MapMessage) {
				MapMessage mapMessage = (MapMessage) message;
				Map<String, String> map = new HashMap<String, String>();  
				Enumeration names = mapMessage.getMapNames();
		        while (names.hasMoreElements()) {  
		            String key = (String) names.nextElement();  
		            map.put(key, mapMessage.getString(key));  
		        }  
		        System.err.println(new Gson().toJson(map));
		        SmsUtil.sendSms(map.get("templateCode"), new Gson().toJson(map), map.get("tel"), SIGNNAME);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
