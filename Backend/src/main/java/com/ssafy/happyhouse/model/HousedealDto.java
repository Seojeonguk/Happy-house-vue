package com.ssafy.happyhouse.model;

public class HousedealDto extends HouseinfoDto {
	String dong;
	String AptName;
	String dealAmount;
	String dealYear;
	String dealMonth;
	String dealDay;
	String area;
	String floor;
	String type;
	String rentMoney;
	
	public HousedealDto() {
		// TODO Auto-generated constructor stub
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

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}

	@Override
	public String toString() {
		String str = "";
		str += dong + "/";
		str += AptName + "/";
		str += dealAmount.trim() + "/";
		str += dealYear + "/";
		str += dealMonth + "/";
		str += dealDay + "/";
		str += area + "/";
		str += floor + "/";
		str += type + "/";
		str += rentMoney;
		return str;
	}
}
