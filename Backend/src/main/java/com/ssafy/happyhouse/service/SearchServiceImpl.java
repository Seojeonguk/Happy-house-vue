package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.Util.Util;
import com.ssafy.happyhouse.dao.SearchDao;
import com.ssafy.happyhouse.model.BaseaddressDto;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.GuguncodeDto;
import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.HouseinfoDto;
import com.ssafy.happyhouse.model.SidocodeDto;
import com.ssafy.happyhouse.model.SubwayDto;


@Service
public class SearchServiceImpl implements SearchService {
private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BaseaddressDto> get_baseaddress() throws Exception {
		return sqlSession.getMapper(SearchDao.class).get_baseaddress();
	}
	
	@Override
	public List<HouseinfoDto> get_houseinfo(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(SearchDao.class).get_houseinfo(map);
	}
	
	@Override
	public List<HousedealDto> get_housedeal(int no) throws Exception {
		return sqlSession.getMapper(SearchDao.class).get_housedeal(no);
	}
	
	@Override
	public void reg_interest(Map<String, String> map) throws Exception {
		sqlSession.getMapper(SearchDao.class).reg_interest(map);
	}
	
	@Override
	public List<HouseinfoDto> get_interest(String userid) throws Exception {
		return sqlSession.getMapper(SearchDao.class).get_interest(userid);
	}
	
	@Override
	public List<SubwayDto> get_subwayinfo() throws SQLException{
		return sqlSession.getMapper(SearchDao.class).get_subwayinfo();
	}
	
	@Override
	public List<CommentDto> get_comment(int aptno) throws SQLException {
		return sqlSession.getMapper(SearchDao.class).get_comment(aptno);
	}

	@Override
	public int insert_comment(CommentDto comment) throws SQLException {
		return sqlSession.getMapper(SearchDao.class).insert_comment(comment);
	}

	@Override
	public int delete_comment(int no) throws SQLException {
		return sqlSession.getMapper(SearchDao.class).delete_comment(no);
	}
	
	
	
}
