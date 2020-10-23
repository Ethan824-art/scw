package com.atguigu.scw.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.user.component.SmsTemplate;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
public class UserLoginRegistController {
	
	@Autowired
	SmsTemplate smsTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TMemberService tMemberService;
	
	@ApiOperation(value="用户登陆") 
	@ApiImplicitParams(value={
			@ApiImplicitParam(value="登陆账号",name="loginacct"),
			@ApiImplicitParam(value="用户密码",name="password")
	})
	@PostMapping("/login")
	public AppResponse<UserRespVo> login(String loginacct,String password){
		
		Logger log = LoggerFactory.getLogger(UserLoginRegistController.class);
		
		try {
			UserRespVo respVo = tMemberService.getUserByLogin(loginacct,password);		
			log.debug("登录成功-{}",loginacct);
			return AppResponse.ok(respVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登录失败-{}-{}",loginacct,e.getMessage());
			AppResponse resp = AppResponse.fail(null);
			resp.setMsg(e.getMessage());
			
			return resp;
			
		}
	} 
	
	@ApiOperation(value="用户注册") 
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo vo){
		
		String loginacct = vo.getLoginacct();
		
		if(!StringUtils.isEmpty(loginacct)) {
			
			String code = stringRedisTemplate.opsForValue().get(loginacct);
			
			if(!StringUtils.isEmpty(code)) {
										
				if(code.equals(vo.getCode())) {
					
					//保存数据 
					int i = tMemberService.savaMember(vo);
					
					if(i==1) {	
						return AppResponse.ok("ok");
					}else {
						
						return AppResponse.fail(null);
					}
					
					
					
				}else {
					AppResponse res = AppResponse.fail(null);
					res.setMsg("验证码不一致，请重新输入");
					return res;
				}
				
			}else {
				AppResponse res = AppResponse.fail(null);
				res.setMsg("验证码已失效，请重新输入");
				return res;
			}

		}else {
			AppResponse res = AppResponse.fail(null);
			res.setMsg("输入的用户名为空");
			return res;
		}
			
	} 
	
	
	
	
	@ApiOperation(value="发送短信验证码") 
	@PostMapping("/sendsms")
	public AppResponse<Object> sendsms(String loginacct){
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			code.append(new Random().nextInt(10));
		}
		
		Map<String, String> querys = new HashMap<String, String>();
	    querys.put("mobile", loginacct);
	    querys.put("param", "code:"+code);
	    querys.put("tpl_id", "TP1711063");
		
		smsTemplate.sendSms(querys);
		
		
		stringRedisTemplate.opsForValue().set(loginacct, code.toString());
		
		return AppResponse.ok("ok");
	} 
	
	@ApiOperation(value="验证短信验证码") 
	@PostMapping("/valide")
	public AppResponse<Object> valide(){
		return AppResponse.ok("ok");
	} 
	
	@ApiOperation(value="重置密码") 
	@PostMapping("/reset")
	public AppResponse<Object> reset(){
		return AppResponse.ok("ok");
	} 
	
	

}
