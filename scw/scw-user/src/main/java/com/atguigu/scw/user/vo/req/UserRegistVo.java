package com.atguigu.scw.user.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserRegistVo {
	
	@ApiModelProperty("手机号")	
	private String loginacct;
	
	@ApiModelProperty("密码")
	private String userpswd;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	@ApiModelProperty("验证码")
	private String code;
	
	@ApiModelProperty("用户类型：0：企业  1：个人")
	private String usertype;
	
	

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getUserpswd() {
		return userpswd;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "UserRegistVo [loginacct=" + loginacct + ", userpswd=" + userpswd + ", email=" + email + ", code=" + code
				+ ", usertype=" + usertype + "]";
	}

	public UserRegistVo(String loginacct, String userpswd, String email, String code, String usertype) {
		super();
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.email = email;
		this.code = code;
		this.usertype = usertype;
	}

	public UserRegistVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
