package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value="HappyHouse/user")
public class UserController {
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
private static final String SUCCESS = "success";
private static final String FAIL = "fail";
	
@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "모든 회원의 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/user") //일반적으로 이 방법을 더 많이 씀.
	public ResponseEntity<List<UserDto>> userList() { //response entity를 return 해줌. 넘겨줘야하는 데이터와 상태코드 같이 넘겨줄 수 있음.
		List<UserDto> list = userService.userList();
		if(list != null && !list.isEmpty()) { //리스트가 있다면 (자동으로 json으로 컨버팅), 제대로 넘어갔다면 ok
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		} else { //리스트를 못 얻어왔으면 no_content로 제대로 데이터가 넘어오지 않았음을 알려줌.
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = " 새로운 회원을 등록한다.", response = String.class)
	@PostMapping(value = "/user")
	public ResponseEntity<String> userRegister(@RequestBody UserDto userDto) {
		int cnt = userService.userRegister(userDto);
		if(cnt != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "한 회원의 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<UserDto> userInfo(@PathVariable("userid") String userid) {
		logger.debug("파라미터 : {}", userid);
		UserDto userDto = userService.userInfo(userid);
		if(userDto != null)
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "한 회원의 정보를 수정한다.", response = List.class)
	@PutMapping(value = "/user")
	public ResponseEntity<String> userModify(@RequestBody UserDto userDto) {
		int cnt = userService.userModify(userDto);
		if(cnt != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "한 회원을 삭제한다.", response = List.class)
	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<String> userDelete(@PathVariable("userid") String userid) {
		int cnt = userService.userDelete(userid);
		if(cnt != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
