package com.atguigu.scw.user.exception;

import com.atguigu.scw.user.enums.UserExceptionEnum;

public class UserException extends RuntimeException { //RuntimeException异常 事务会自动回滚
	
	public UserException(){
		
	}
	
	public UserException(UserExceptionEnum enums){
		
		super(enums.getMessage());
	}
	
}
