package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.HousedealDto;

public interface StatsService {
	public List<HousedealDto> MainMostExpensive() throws Exception; // 메인페이지에서 사용할 top5 비싼가격 
	public List<HousedealDto> MainCheapest() throws Exception;	// 메인 페이지에서 사용할 top5 싼가격

	public List<HousedealDto> AvgDong() throws Exception; //동별 평균 가격
	public List<HousedealDto> BuildYearDong() throws Exception; //동별 평균 건축 년도
	public List<HousedealDto> MostExpensive() throws Exception; //가장 비싼집 10개
	public List<HousedealDto> Cheapest() throws Exception; //가장 싼 집 10개 
}
