package com.atguigu.scw.user.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.atguigu.scw.vo.resp.AppResponse;

@Component
public class SmsTemplate {
	
	@Value("${sms.host}")
    String host ;
	
	@Value("${sms.path}")
    String path ;
	
	@Value("${sms.method}")
    String method ;
	
	@Value("${sms.appcode}")
    String appcode ;
	
	public AppResponse<String> sendSms(Map<String, String> querys) {

	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE " + appcode);
	    
/*	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("mobile", "159xxxx9999");
	    querys.put("param", "code:1234");
	    querys.put("tpl_id", "TP1711063");*/
	    Map<String, String> bodys = new HashMap<String, String>();

	    try {
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	return AppResponse.ok(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return AppResponse.fail(null);
	    }
	}
	
 
}

