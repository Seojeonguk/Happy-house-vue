package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.StatsDao;
import com.ssafy.happyhouse.model.HousedealDto;

@Service
public class StatsServiceImpl implements StatsService {
	private static final Logger logger = LoggerFactory.getLogger(StatsServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<HousedealDto> MainCheapest() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(StatsDao.class).MainMostExpensive();
	}
	
	@Override
	public List<HousedealDto> MainMostExpensive() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(StatsDao.class).MainCheapest();
	}
	
	@Override
	public List<HousedealDto> AvgDong() throws Exception {
		return sqlSession.getMapper(StatsDao.class).AvgDong();
	}

	@Override
	public List<HousedealDto> BuildYearDong() throws Exception {
		return sqlSession.getMapper(StatsDao.class).BuildYearDong();
	}

	@Override
	public List<HousedealDto> MostExpensive() throws Exception {
		return sqlSession.getMapper(StatsDao.class).MostExpensive();
	}

	@Override
	public List<HousedealDto> Cheapest() throws Exception {
		return sqlSession.getMapper(StatsDao.class).Cheapest();
	}

}
