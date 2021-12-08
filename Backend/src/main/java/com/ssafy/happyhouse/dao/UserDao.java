package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserDao {
	public UserDto login(UserDto userDto) throws SQLException;	
//	REST
	public List<UserDto> userList();
	public UserDto userInfo(String userid);
	public int userRegister(UserDto userDto);
	public int userModify(UserDto userDto);
	public int userDelete(String userid);
}
