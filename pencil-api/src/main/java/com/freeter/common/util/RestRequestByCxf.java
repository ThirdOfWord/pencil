package com.freeter.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RestRequestByCxf {
	private static final Logger log = Logger.getLogger(RestRequestByCxf.class.getName());

	private static ObjectMapper objectMapper = new ObjectMapper();

	private static boolean formateOutputJson = true;

	/*public static void main(String[] args) throws IOException {
		openCard();
	}*/


	private static String userName = "qianbitou";
	private static String userPass = "3uUb0paf";
	private static String key = "VAxi9BpHWCvIc730";
	private static String url = "http://apis.wlfacaiyu.com/sms/openCard";

	private static WebClient createClient(String url) {
		WebClient client = WebClient.create(url);
		// 非常重要
		client.type("application/json;charset=UTF-8");
		return client;
	}
	/**
	 * 短信开卡请求
	 * 
	 * @return
	 * @throws IOException
	 */
	public static JsonNode openCard(String phone,String number,String info) throws IOException {
		WebClient client = createClient(url);
		client.type("application/json;charset=UTF-8");
		String tradeNo = AESUtil.getTradeNo();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tradeNo", tradeNo);// 必填参数
		parameters.put("userName", userName);// 必填参数
		parameters.put("userPassword", userPass);// 必填参数
		
		parameters.put("phones", phone);
		parameters.put("content", info);//这是一条测试短信
		parameters.put("etnumber", "");
		String sign = AESUtil.encrypt(objectMapper.writeValueAsString(parameters),key);
		parameters.put("sign", sign);

		parameters.put("userPassword", AESUtil.MD5(userPass));// 必填参数
		String body = objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(parameters);
		printJsonString("开卡请求", body);
		Response response = client.post(body);
		return printResult("开卡完成", response);
	}

	// 打印发送请求的JSON数据
	private static void printJsonString(String phase, String json) {
		System.out.println("\n+++ 发送请求[" + phase + "] +++");
		System.out.println(json);
	}
	private static JsonNode printResult(String phase, Response response) {
		System.out.println("\n=== " + phase + " ===");

		try {
			InputStream stream = (InputStream) response.getEntity();
			int available = 0;
			available = stream.available();

			if (available == 0) {
				System.out.println("nothing returned, response code: "
						+ response.getStatus());
				return null;
			}
			JsonNode responseNode = objectMapper.readTree(stream);
			if (formateOutputJson) {
				System.out.println(objectMapper
						.writerWithDefaultPrettyPrinter().writeValueAsString(
								responseNode));
			} else {
				System.out.println(objectMapper
						.writeValueAsString(responseNode));
			}
			return responseNode;
		} catch (IOException e) {
			System.err.println("catch an exception: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
