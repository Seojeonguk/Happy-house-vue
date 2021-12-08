package com.ssafy.happyhouse.model;

public class BaseaddressDto {
	Integer no;
	String city;
	String code;
	String dongcode;
	String gugun;
	String dong;
	String lat;
	String lng;
	
	public BaseaddressDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDongcode() {
		return dongcode;
	}

	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += city + "/";
		str += code + "/";
		str += dongcode + "/";
		str += gugun + "/";
		str += dong + "/";
		str += lat + "/";
		str += lng;
		return str;
	}
}
