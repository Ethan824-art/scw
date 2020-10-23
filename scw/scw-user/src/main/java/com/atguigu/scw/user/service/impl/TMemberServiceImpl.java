package com.atguigu.scw.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.bean.TMemberExample;
import com.atguigu.scw.user.bean.TMemberExample.Criteria;
import com.atguigu.scw.user.enums.UserExceptionEnum;
import com.atguigu.scw.user.exception.UserException;
import com.atguigu.scw.user.mapper.TMemberMapper;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;


@Transactional(readOnly=true)
@Service
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper tMemberMapper;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	Logger log = LoggerFactory.getLogger(TMemberServiceImpl.class);
	
	@Transactional
	@Override
	public int savaMember(UserRegistVo vo) {		
		
		try {
			
			TMember tMember = new TMember();
			BeanUtils.copyProperties(vo, tMember);		
			tMember.setUsername(vo.getLoginacct());	
			
			//密码加密保存
			String userpswd = vo.getUserpswd();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String encode = encoder.encode(userpswd);
			
			tMember.setUserpswd(encode);
			
			int i = tMemberMapper.insertSelective(tMember);
			log.debug("注册会员成功-{}",tMember);
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("注册会员失败-{}",e.getMessage());
			throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
		}
	}

	@Override
	public UserRespVo getUserByLogin(String loginacct, String password) {
		
		UserRespVo vo = new UserRespVo();
		
		TMemberExample tMemberExample = new TMemberExample();
		Criteria criteria = tMemberExample.createCriteria();
		
		criteria.andLoginacctEqualTo(loginacct);
		
		List<TMember> list = tMemberMapper.selectByExample(tMemberExample);
		
		if(list == null || list.size() == 0) {
			
			throw new UserException(UserExceptionEnum.USER_UNEXISTS);

		}
		
		TMember member = list.get(0);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(!encoder.matches(password,member.getUserpswd())) { //密码比较
			throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
		}
		
		BeanUtils.copyProperties(member, vo);
		
		String tokenID = UUID.randomUUID().toString().replace("-", "");
		vo.setAccessToken(tokenID);
		
		stringRedisTemplate.opsForValue().set(tokenID, member.getId().toString());
		
		return vo;
	}

}
