package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;


public interface BoardService {
	
	public List<BoardDto> retrieveBoard();
	public BoardDto detailBoard(int no);
	public int writeBoard(BoardDto board);
	public int updateBoard(BoardDto board);
	public int deleteBoard(int no);
}
