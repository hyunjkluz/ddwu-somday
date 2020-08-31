/**
 * 
 */
package com.somday.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.somday.error.DBError;
import com.somday.req.vo.StudentInfoReq;
import com.somday.req.vo.StudentInfoUpdateReq;
import com.somday.req.vo.TokenReq;
import com.somday.service.SecurityService;
import com.somday.service.StudentService;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.EncryptedVO;
import com.somday.vo.Response;
import com.somday.vo.StudentInfoVO;
import com.somday.vo.StudentSimpleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@RequestMapping(value = "/apis/student")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private SecurityService securityService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * @HTTP Method : POST
	 * @URI : /apis/student
	 * @Method 설명 : 회원가입
	 * @변경이력 :
	 */
	@ApiOperation(value="회원가입")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> registerStudent(@RequestBody @Valid StudentInfoReq info) {
		try {
			LOGGER.info("컨트롤러 들어옴");
			String encryptedPw = securityService.encrypt(info.getPassword());
			String encryptedStudentId = securityService.encrypt(info.getStudentId());
			String encryptedPhone = securityService.encrypt(info.getPhone());
			LOGGER.info("암호화된 학번 : " + encryptedStudentId);
			
			String existsStudentId = studentService.getStudentId(encryptedStudentId);
			LOGGER.info(existsStudentId);
			if (existsStudentId != null) {
				return new ResponseEntity<>(Response.res(StatusCode.BAD_REQUEST, ResponseMessage.EXISTS_STUDENT), HttpStatus.BAD_REQUEST);
			}

			info.setId("ST" + securityService.uniqId());
			info.setEncryptedPw(encryptedPw);
			info.setEncryptedStudentId(encryptedStudentId);
			info.setEncryptedPhone(encryptedPhone);
			
			LOGGER.info("컨트롤러 정보 : " + info.toString());
			Integer isCreated = studentService.createStudent(info);
			
			if (isCreated == null) {
				return new ResponseEntity<>(Response.res(StatusCode.DB_ERROR, ResponseMessage.INSERT_STUDENT_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			TokenReq tokenInfo = new TokenReq(info.getId(), encryptedStudentId, info.getMajorId());
			String newToken = securityService.createToken(tokenInfo);
			
			if (newToken == null) {
				return new ResponseEntity<>(Response.res(StatusCode.TOKEN_CREATE_ERROR, ResponseMessage.CREATE_TOKEN_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<>(Response.res(StatusCode.CREATED, ResponseMessage.TOKEN_CREATED, newToken), HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.info("계정 생성 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @HTTP Method : PUT
	 * @URI : /apis/student/update
	 * @Method 설명 : 개인정보 변
	 * @변경이력 :
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateStudent(HttpServletRequest request, @RequestBody @Valid StudentInfoUpdateReq newInfo) throws DBError, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		String encryptedPhone = securityService.encrypt(newInfo.getPhone());
		String encryptedPw = securityService.encrypt(newInfo.getPassword());
			
		newInfo.setEncryptedPw(encryptedPw);
		newInfo.setEncryptedPhone(encryptedPhone);
		newInfo.setId(tokenInfo.getId());
			
		studentService.putStudentInfo(newInfo);
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_UPDATE_SUCCESS), HttpStatus.OK);
			
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/student/info
	 * @Method 설명 :
	 * @변경이력 :
	 */
	@GetMapping("/info")
	public ResponseEntity<?> getStudentInfo(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		StudentSimpleVO studentInfo = studentService.getStudentSimpleInfoById(tokenInfo.getId());
		String encryptedStudnetId = studentInfo.getStudentId();
		
		studentInfo.setStudentId(securityService.decrypt(encryptedStudnetId));
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, studentInfo), HttpStatus.OK);
	}
}

	
