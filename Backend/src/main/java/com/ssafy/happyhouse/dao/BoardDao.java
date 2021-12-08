package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;

public interface BoardDao {
	public List<BoardDto> selectBoard();
	public BoardDto selectBoardByNo(int no);
	public int insertBoard(BoardDto board);
	public int updateBoard(BoardDto board);
	public int deleteBoard(int no);
}
