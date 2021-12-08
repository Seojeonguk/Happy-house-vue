package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.UserDto;

public interface StatsDao {
//	REST
	public List<HousedealDto> MainMostExpensive(); //가장 비싼집 10개
	public List<HousedealDto> MainCheapest(); //가장 싼 집 10개
	public List<HousedealDto> AvgDong(); //동별 평균 가격
	public List<HousedealDto> BuildYearDong(); //동별 평균 건축 년도
	public List<HousedealDto> MostExpensive(); //가장 비싼집 10개
	public List<HousedealDto> Cheapest(); //가장 싼 집 10개
}
