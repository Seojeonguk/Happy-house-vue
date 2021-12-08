package com.ssafy.happyhouse.model;

public class HouseinfoDto extends BaseaddressDto {
	Integer no;
	String dong;
	String AptName;
	String code;
	String buildYear;
	String jibun;
	String lat;
	String lng;
	String img;
	
	public HouseinfoDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAptName() {
		return AptName;
	}

	public void setAptName(String aptName) {
		AptName = aptName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		String str = "";
		str += no + "/";
		str += dong + "/";
		str += AptName + "/";
		str += code + "/";
		str += buildYear + "/";
		str += jibun + "/";
		str += lat + "/";
		str += lng + "/";
		str += img;
		return str;
	}
}
