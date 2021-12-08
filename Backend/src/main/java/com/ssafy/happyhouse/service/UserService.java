package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	
	
	public List<UserDto> userList();
	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid);
	public int userRegister(UserDto userDto);
	public int userModify(UserDto userDto);
	public int userDelete(String userid);

}
