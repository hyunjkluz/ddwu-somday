package com.somday.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.somday.error.DBError;
import com.somday.req.vo.MemoReq;
import com.somday.req.vo.TokenReq;
import com.somday.service.WorkService;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.Response;
import com.somday.vo.RoomVO;
import com.somday.vo.WorkVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 */

/**
 * @Since : Aug 25, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 25, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
@Controller
@Api(value = "야작 신청 API", description = "야작 신청 관련 API입니다.")
@RequestMapping(value = "/apis/reservation/work")
public class WorkController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);
	
	@Autowired
	private WorkService workService;
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/reservation/work
	 * @Method 설명 : 당일 야작 신청 조회
	 */
	@ApiOperation(value="당일 야작 신청 조회")
	@GetMapping("")
	public ResponseEntity<?> getWorkReservation(HttpServletRequest request) {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		WorkVO reservation = workService.getWorkReservation(tokenInfo.getId());
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, reservation), HttpStatus.OK);
		
	}
	
	/**
	 * @HTTP Method : POST
	 * @URI : /apis/reservation/work/room/{roomId}
	 * @Method 설명 : 당일 야작 신청 조회
	 */
	@ApiOperation(value="야작 신청")
	@PostMapping("/room/{roomId}")
	public ResponseEntity<?> postWorkReservation(HttpServletRequest request, @PathVariable Integer roomId) throws DBError {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		workService.postWorkReservation(tokenInfo.getId(), roomId);
			
		return new ResponseEntity<>(Response.res(StatusCode.CREATED, ResponseMessage.DB_INSERT_SUCCESS), HttpStatus.CREATED);
		
	}
	
	/**
	 * @HTTP Method : DELETE
	 * @URI : /apis/reservation/work/{workId}
	 * @Method 설명 : 당일 야작 신청 조회
	 */
	@ApiOperation(value="야작 신청 취소")
	@DeleteMapping("/{workId}")
	public ResponseEntity<?> deleteWorkReservation(HttpServletRequest request, @PathVariable Integer workId) throws DBError {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		workService.deleteWorkReservation(workId);

		return new ResponseEntity<>(Response.res(StatusCode.CREATED, ResponseMessage.DB_DELETE_SUCCESS), HttpStatus.CREATED);
		
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/reservation/work/room
	 * @Method 설명 : 야작 강의실 조회
	 */
	@ApiOperation(value="야작 강의실 조회")
	@GetMapping("/room")
	public ResponseEntity<?> getWorkRoom(HttpServletRequest request) {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
			
		RoomVO[] roomList = workService.getRoomList(tokenInfo.getMajorId());
			
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, roomList), HttpStatus.OK);
	}

}
