package com.atguigu.scw.user.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserRespVo {

	//令牌 登录后会分配给当前用户一个临时的令牌值 以后对系统的任何访问必须携带这个令牌 否则 拒绝访问 必须去重新登录 
	//会把令牌的值当成key 用户id当成value 存在redis中 这样以后可以通过令牌查到用户的id值 从而查询当前用户的全部信息
	@ApiModelProperty("访问令牌,请妥善保管,以后每次请求都要带上")
	private String accessToken;
	
    private String loginacct;

    private String username;

    private String email;

    private String authstatus ="0";

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthstatus() {
		return authstatus;
	}

	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getAccttype() {
		return accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}

	public UserRespVo(String accessToken, String loginacct, String username, String email, String authstatus,
			String usertype, String realname, String cardnum, String accttype) {
		super();
		this.accessToken = accessToken;
		this.loginacct = loginacct;
		this.username = username;
		this.email = email;
		this.authstatus = authstatus;
		this.usertype = usertype;
		this.realname = realname;
		this.cardnum = cardnum;
		this.accttype = accttype;
	}

	public UserRespVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRespVo [accessToken=" + accessToken + ", loginacct=" + loginacct + ", username=" + username
				+ ", email=" + email + ", authstatus=" + authstatus + ", usertype=" + usertype + ", realname="
				+ realname + ", cardnum=" + cardnum + ", accttype=" + accttype + "]";
	}


    
    
}
