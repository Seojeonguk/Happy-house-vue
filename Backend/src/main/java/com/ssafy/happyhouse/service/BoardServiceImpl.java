package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.model.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

    @Override
	public List<BoardDto> retrieveBoard() {
		return sqlSession.getMapper(BoardDao.class).selectBoard();
	}
    
  	@Override
	public int writeBoard(BoardDto board) {
  		return sqlSession.getMapper(BoardDao.class).insertBoard(board);
	}

	@Override
	public BoardDto detailBoard(int no) {
		return sqlSession.getMapper(BoardDao.class).selectBoardByNo(no);
	}

	@Override
	@Transactional
	public int updateBoard(BoardDto board) {
		return sqlSession.getMapper(BoardDao.class).updateBoard(board);
	}

	@Override
	@Transactional
	public int deleteBoard(int no) {
		return sqlSession.getMapper(BoardDao.class).deleteBoard(no);
	}
}
