package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BaseaddressDto;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.HouseinfoDto;
import com.ssafy.happyhouse.model.SubwayDto;

public interface SearchDao {
	// baseAddress 업로드
	List<BaseaddressDto> get_baseaddress() throws SQLException;	
	// 하우스 selectOne
	List<HouseinfoDto> get_houseinfo(Map<String, String> map) throws SQLException;
	// 해당 동에 있는 하우스 selectAll
	List<HousedealDto> get_housedeal(int no) throws SQLException;
	// 관심아파트 등록
	void reg_interest(Map<String, String> map) throws SQLException;
	// 관심아파트 보여주기
	List<HouseinfoDto> get_interest(String userid) throws SQLException;
	//지하철 정보 반환
	List<SubwayDto> get_subwayinfo() throws SQLException;
	//getComment 
	List<CommentDto> get_comment(int aptno) throws SQLException;
	//insertComment
	public int insert_comment(CommentDto comment);
	//deleteComment
	public int delete_comment(int no);
	
}
