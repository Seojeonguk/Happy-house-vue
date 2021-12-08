package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.Util.Util;
import com.ssafy.happyhouse.model.BaseaddressDto;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.GuguncodeDto;
import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.HouseinfoDto;
import com.ssafy.happyhouse.model.SidocodeDto;
import com.ssafy.happyhouse.model.SubwayDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.service.SearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
@Api(value="HappyHouse/search")
public class SearchController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private SearchService searchService;
	
	@ApiOperation(value = "base address", notes = "기본 주소 받아오기")
	@GetMapping("/get_baseaddress")
	public ResponseEntity<List<BaseaddressDto>> baseaddressList() {
		List<BaseaddressDto> list = null;
		try {
			list = searchService.get_baseaddress();
			return new ResponseEntity<List<BaseaddressDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "아파트 목록", notes = "검색 내용에 해당하는 아파트 목록을 반환해 줍니다.")
	@PostMapping(value = "/get_houseinfo")
	public ResponseEntity<List<HouseinfoDto>> aptList(@RequestBody Map<String, String> map) {
		List<HouseinfoDto> list = null;
		try {
			list = searchService.get_houseinfo(map);
			return new ResponseEntity<List<HouseinfoDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "아파트 거래내역", notes = "검색 아파트의 거래 내역 목록을 반환해 줍니다.")
	@GetMapping(value = "/get_housedeal/{infono}")
	public ResponseEntity<List<HousedealDto>> AptdealList(@PathVariable("infono") int no) {
		List<HousedealDto> list = null;
		try {
			list = searchService.get_housedeal(no);
			return new ResponseEntity<List<HousedealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "subway info", notes = "지하철 노선도 정보 받아오기")
	@GetMapping("/get_subwayinfo")
	public ResponseEntity<List<SubwayDto>> subwayInfo() {
		List<SubwayDto> list = null;
		try {
			list = searchService.get_subwayinfo();
			return new ResponseEntity<List<SubwayDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "관심 아파트 목록", notes = "로그인한 사용자의 관심아파트 목록을 반환해 줍니다.")
	@PostMapping(value = "/get_interest_ajax")
	public ResponseEntity<List<HouseinfoDto>> User_Interest_List(HttpSession session) {
		List<HouseinfoDto> list = null;
		try {
			UserDto user = (UserDto) session.getAttribute("userinfo");
			
			list = searchService.get_interest(user.getUserid());
			
			return new ResponseEntity<List<HouseinfoDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
			// TODO: handle exception
		}
	}
	
	@ApiOperation(value = "관심 아파트 설정", notes = "관심 아파트를 추가합니다.")
	@GetMapping(value = "/reg_interest/{aptno}")
	public ResponseEntity<String> reg_interest(HttpSession session, @PathVariable("aptno") String aptno) {
		try {
			Map<String,String> map = new HashMap<String, String>();
			UserDto user = (UserDto) session.getAttribute("userinfo");
			map.put("userid", user.getUserid());
			map.put("aptno", aptno);
			searchService.reg_interest(map);
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "아파트 별 코멘트", notes = "검색 아파트의 코멘트 목록을 반환해 줍니다.")
	@GetMapping(value = "/get_comment/{infono}")
	public ResponseEntity<List<CommentDto>> commentList(@PathVariable("infono") int aptno) {
		List<CommentDto> list = null;
		try {
			list = searchService.get_comment(aptno);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
	}
	
	@ApiOperation(value = "새로운 코멘트를 등록한다.", response = String.class)
	@PostMapping("/insert_comment")
	public ResponseEntity<String> writeComment(@RequestBody CommentDto comment) throws SQLException {
		if (searchService.insert_comment(comment) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	 
	@ApiOperation(value = "코멘트 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/delete_comment/{infono}")
	public ResponseEntity<String> deleteComment(@PathVariable int no) throws SQLException{
		if (searchService.delete_comment(no) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
