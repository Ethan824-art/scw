package com.atguigu.scw.user.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootConfiguration
public class AppSwaggerConfig {
	
	@RestController
	public class HelloController {

	@GetMapping("/hello")
	public String hello(String name) {
		
		return "OK:"+name;
		
	 }
		
	}

}
