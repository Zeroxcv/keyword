package com.lisk.keyword.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Ltp {
	// webapi接口地址
	private static final String WEBTTS_URL = "http://ltpapi.xfyun.cn/v1/ke";
	// 应用ID
	private static final String APPID = "5de504b4";
	// 接口密钥
	private static final String API_KEY = "407d034d23e84608fd06dfc59bb28ee9";




	private static final String TYPE = "dependent";

	/**
	 * 封装数据
	 */
	public static String getLtpData(String text)throws UnsupportedEncodingException{
//		System.out.println(text.length());
		Map<String, String> header = Ltp.buildHttpHeader();

		String result = HttpUtil.doPost1(WEBTTS_URL, header, "text=" + URLEncoder.encode(text, "utf-8"));
		if(null == result)
			return "error";
//		System.out.println("itp 接口调用结果：" + result);
		return result;
	}


	/**
	 * 组装http请求头
	 */
	private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		String param = "{\"type\":\"" + TYPE +"\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		return header;
	}
}
