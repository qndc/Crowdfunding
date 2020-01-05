package com.atguigu.atcrowdfunding.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.Map;

public class SmsUtil {
	private static DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI4FieR9AHyMpC1D9BnDsv",
			"BnEVOvEKianyIkE1zWMv0ERA3N9yxP");
	private static IAcsClient client = new DefaultAcsClient(profile);

	private static void log_print(String functionName, Object result) {
		Gson gson = new Gson();
		System.out.println(gson.toJson(result));
	}

	/**
	 * 发送短信验证码
	 * @param templateCode	验证码
	 * @param templateParam	验证码对应的参数赋值
	 * @param phoneNumbers	电话号码
	 * @param signName	签名
	 * @return
	 * @throws ClientException
	 */
	public static String sendSms(String templateCode, String templateParam, String phoneNumbers, String signName)
			throws ClientException {
		CommonRequest request = new CommonRequest();
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");

		request.putQueryParameter("PhoneNumbers", phoneNumbers);

		request.putQueryParameter("SignName", signName);

		request.putQueryParameter("TemplateCode", templateCode);

		request.putQueryParameter("TemplateParam", templateParam);
		CommonResponse commonResponse = client.getCommonResponse(request);
		String data = commonResponse.getData();
		String sData = data.replaceAll("''", "");
		log_print("sendSms", sData);
		Gson gson = new Gson();
		Map map = (Map) gson.fromJson(sData, Map.class);
		Object bizId = map.get("BizId");
		return bizId.toString();
	}

	/**
	 * 查询发送情况
	 * @param bizId
	 * @throws ClientException
	 */
	public static void querySendDetails(String bizId) throws ClientException {
		CommonRequest request = new CommonRequest();
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("QuerySendDetails");

		request.putQueryParameter("PhoneNumber", "156xxxxxxxx");

		request.putQueryParameter("SendDate", "20191010");

		request.putQueryParameter("PageSize", "10");

		request.putQueryParameter("CurrentPage", "1");

		request.putQueryParameter("BizId", bizId);
		CommonResponse response = client.getCommonResponse(request);
		log_print("querySendDetails", response.getData());
	}
}
