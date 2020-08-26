/**
 * 
 */
package com.somday.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.somday.req.vo.LoginReq;
import com.somday.req.vo.TokenReq;
import com.somday.service.SecurityService;
import com.somday.service.StudentService;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.Response;
import com.somday.vo.StudentInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @Since : Aug 24, 2020 
 * @Author kimhyunjin
 * <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성 
 * </pre>
 *
 */
@Controller
@Api(value="인증 API", description="회원관리 API입니다.")
@RequestMapping(value = "/apis/auth")
public class AuthController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private SecurityService securityService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * @HTTP Method : POST
	 * @URI : /apis/auth/student/token
	 * @Method 설명 : 로그인
	 * @변경이력 :
	 */
	@ApiOperation(value="로그인")
	@ApiResponse(code=500, message="INTERNAL SERVER ERROR")
	@PostMapping(value="/student/token")
	public ResponseEntity registerStudent(@RequestBody @Valid LoginReq loginInfo) {
		try {
			String encryptedStudentId = securityService.encrypt(loginInfo.getStudentId());
			
			StudentInfoVO studentInfoVO = studentService.getStudentInfo(encryptedStudentId);
			
			if (studentInfoVO == null) {
				return new ResponseEntity<>(Response.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_EXISTS_STUDENT), HttpStatus.BAD_REQUEST);
			}
			
			String encryptedPw = securityService.encrypt(loginInfo.getPassword());
			
			if (!studentInfoVO.getPassword().equals(encryptedPw)) {
				return new ResponseEntity<>(Response.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL), HttpStatus.BAD_REQUEST);
			}
			
			TokenReq tokenInfo = new TokenReq(studentInfoVO.getId(), encryptedStudentId, studentInfoVO.getMajor().getId());
			String newToken = securityService.createToken(tokenInfo);
			LOGGER.info("new TOKEN : " + newToken);
			
			if (newToken == null) {
				return new ResponseEntity<>(Response.res(StatusCode.TOKEN_CREATE_ERROR, ResponseMessage.CREATE_TOKEN_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.TOKEN_CREATED, newToken), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("로그인 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @HTTP Method : PUT
	 * @URI : /apis/auth/student/change/password
	 * @Method 설명 : 비밀번호 변경
	 * @변경이력 :
	 */
	@ApiOperation(value="비밀번호 변경")
	@PutMapping(value="/student/change/password")
	public ResponseEntity updatePassword(@RequestBody LoginReq info) {
		try {
			String encryptedStudentId = securityService.encrypt(info.getStudentId());
			String encryptedNewPw = securityService.encrypt(info.getPassword());
			
			String id = studentService.getId(encryptedStudentId);
			if (id == null) {
				return new ResponseEntity<>(Response.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_EXISTS_STUDENT), HttpStatus.BAD_REQUEST);
			}
			
			Integer isUpdate = studentService.putPasswordById(id, encryptedNewPw);
			
			if (isUpdate == null) {
				return new ResponseEntity<>(Response.res(StatusCode.DB_ERROR, ResponseMessage.DB_UPDATE_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<>(Response.res(StatusCode.NO_CONTENT, ResponseMessage.REFRESH_TOKEN), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("비밀번호 변경 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.SEND_MAIL_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
