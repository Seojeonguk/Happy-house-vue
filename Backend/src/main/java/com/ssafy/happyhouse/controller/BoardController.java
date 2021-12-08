package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@Api(value="HappyHouse/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;
	
	 @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
		@GetMapping
		public ResponseEntity<List<BoardDto>> retrieveBoard() throws Exception {
			return new ResponseEntity<List<BoardDto>>(boardService.retrieveBoard(), HttpStatus.OK);
		}

	    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)    
		@GetMapping("{no}")
		public ResponseEntity<BoardDto> detailBoard(@PathVariable int no) {
			return new ResponseEntity<BoardDto>(boardService.detailBoard(no), HttpStatus.OK);
		}

	    @ApiOperation(value = "새로운 게시글을 등록한다.", response = String.class)
		@PostMapping
		public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
			if (boardService.writeBoard(board) > 0) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}

	    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다.", response = String.class)
		@PutMapping("{no}")
		public ResponseEntity<String> updateBoard(@RequestBody BoardDto board) {
			
			if (boardService.updateBoard(board) > 0) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}

	    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)
		@DeleteMapping("{no}")
		public ResponseEntity<String> deleteBoard(@PathVariable int no) {
			if (boardService.deleteBoard(no) > 0) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	

}
