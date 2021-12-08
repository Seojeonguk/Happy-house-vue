package com.ssafy.happyhouse.model;

public class GuguncodeDto {
	String gugun_code;
	String gugun_name;
	
	public GuguncodeDto() {
		// TODO Auto-generated constructor stub
	}
	public String getGugun_code() {
		return gugun_code;
	}
	public void setGugun_code(String gugun_code) {
		this.gugun_code = gugun_code;
	}
	public String getGugun_name() {
		return gugun_name;
	}
	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += gugun_code;
		str += "/";
		str += gugun_name;
		return str;
	}
}
