package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BaseaddressDto;
import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.GuguncodeDto;
import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.HouseinfoDto;
import com.ssafy.happyhouse.model.SidocodeDto;
import com.ssafy.happyhouse.model.SubwayDto;

public interface SearchService {
	//기본 기능
	public List<BaseaddressDto> get_baseaddress() throws Exception;
	public List<HouseinfoDto> get_houseinfo(Map<String, String> map) throws Exception;
	public List<HousedealDto> get_housedeal(int no) throws Exception;
	//추가기능(관심지역, 지하철 정보)
	public void reg_interest(Map<String, String> map) throws Exception;
	public List<HouseinfoDto> get_interest(String userid) throws Exception;
	public List<SubwayDto> get_subwayinfo() throws SQLException;
	
	//추가기능(코멘트)
	public List<CommentDto> get_comment(int aptno) throws SQLException;
	public int insert_comment(CommentDto comment) throws SQLException;
	public int delete_comment(int no) throws SQLException;
	
}
