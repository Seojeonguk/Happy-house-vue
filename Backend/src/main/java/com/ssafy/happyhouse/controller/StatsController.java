package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HousedealDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.service.StatsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stats")
@CrossOrigin("*")
@Api(value="HappyHouse/stats")
public class StatsController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private StatsService statsService;
	
	@ApiOperation(value = "랭킹 상위 5개 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/rankingtop5") //일반적으로 이 방법을 더 많이 씀.
	public ResponseEntity<Map<String,List<HousedealDto>>> MainrankingList() throws Exception { //response entity를 return 해줌. 넘겨줘야하는 데이터와 상태코드 같이 넘겨줄 수 있음.
		Map<String,List<HousedealDto>> map = new HashMap<>();
		
		map.put("mostExpensivetop", statsService.MainMostExpensive());
		map.put("cheapesttop", statsService.MainCheapest());
		return new ResponseEntity<Map<String,List<HousedealDto>>>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "전체 통계 자료를 반환한다.", response = List.class)
	@GetMapping(value = "/show") //일반적으로 이 방법을 더 많이 씀.
	public ResponseEntity<Map<String,List<HousedealDto>>> statsList() throws Exception { //response entity를 return 해줌. 넘겨줘야하는 데이터와 상태코드 같이 넘겨줄 수 있음.
		Map<String,List<HousedealDto>> map = new HashMap<>();
		
		map.put("avgDong", statsService.AvgDong());
		map.put("buildYearDong", statsService.BuildYearDong());
		
		return new ResponseEntity<Map<String,List<HousedealDto>>>(map, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "랭킹 자료를 반환한다.", response = List.class)
	@GetMapping(value = "/mostExpensive") //일반적으로 이 방법을 더 많이 씀.
	public ResponseEntity<List<HousedealDto>> mostExpensive() throws Exception { //response entity를 return 해줌. 넘겨줘야하는 데이터와 상태코드 같이 넘겨줄 수 있음.
		return new ResponseEntity<List<HousedealDto>>(statsService.MostExpensive(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "랭킹 자료를 반환한다.", response = List.class)
	@GetMapping(value = "/cheapest") //일반적으로 이 방법을 더 많이 씀.
	public ResponseEntity<List<HousedealDto>> cheapest() throws Exception { //response entity를 return 해줌. 넘겨줘야하는 데이터와 상태코드 같이 넘겨줄 수 있음.		
		return new ResponseEntity<List<HousedealDto>>(statsService.Cheapest(), HttpStatus.OK);
	}
	
	
}
