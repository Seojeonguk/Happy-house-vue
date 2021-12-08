package com.ssafy.happyhouse.model;

public class SidocodeDto {
	String sido_code;
	String sido_name;
	
	public SidocodeDto() {}
	
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getSido_name() {
		return sido_name;
	}
	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += sido_code;
		str += "/";
		str += sido_name;
		return str;
	}
}
