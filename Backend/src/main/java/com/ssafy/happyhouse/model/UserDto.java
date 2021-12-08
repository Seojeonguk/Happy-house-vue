package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {
	@ApiModelProperty(value = "회원ID")
	private String userid;
	@ApiModelProperty(value = "이름")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String userpwd;
	@ApiModelProperty(value = "Email")
	private String email;
	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "가입일")
	private String joindate;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

}