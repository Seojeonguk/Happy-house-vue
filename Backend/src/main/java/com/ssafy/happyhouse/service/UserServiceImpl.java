package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.model.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserid() == null || userDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(UserDao.class).login(userDto);
	}
		
	@Override
	public List<UserDto> userList() {
		return sqlSession.getMapper(UserDao.class).userList();
	}

	@Override
	public UserDto userInfo(String userid) {
		return sqlSession.getMapper(UserDao.class).userInfo(userid);
	}

	@Override
	public int userRegister(UserDto userDto) {
		return sqlSession.getMapper(UserDao.class).userRegister(userDto);
	}

	@Override
	public int userModify(UserDto userDto) {
		return sqlSession.getMapper(UserDao.class).userModify(userDto);
	}

	@Override
	public int userDelete(String userid) {
		return sqlSession.getMapper(UserDao.class).userDelete(userid);
	}
	
}
