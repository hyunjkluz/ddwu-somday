/**
 * 
 */
package com.somday.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.somday.service.MailService;
import com.somday.service.SecurityService;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Since : Aug 23, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 23, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
@Controller
@Api(value="인증 API", description="인증 API입니다.")
@RequestMapping(value = "/apis/auth/mail")
public class MailAuthController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailAuthController.class);
	
	@Autowired
	private MailService mailService;
	@Autowired
	private SecurityService securityService;

	/**
	 * @HTTP Method : POST 
	 * @URI : /api/auth/mail
	 * @Method 설명 : 학교 메일로 인증번호 발송
	 * @변경이력 :
	 */
	@ApiOperation(value="학교 메일로 인증번호 전송")
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity postMailAuth(@RequestBody HashMap<String, Object> requestMap) {
		try {
			String studentId = (String)requestMap.get("studentId");
			
			if (studentId == null) {
				return new ResponseEntity<>(Response.res(StatusCode.BAD_REQUEST, ResponseMessage.NULL_VALUE), HttpStatus.BAD_REQUEST);
			} 
			
			Integer verificationCode = securityService.sixDigitCode();
			
			Integer authId = mailService.registerAuthCode(studentId, verificationCode);
			
			if (authId == null) {
				return new ResponseEntity<>(Response.res(StatusCode.DB_ERROR, ResponseMessage.INSERT_AUTHCODE_FAIL), HttpStatus.BAD_REQUEST);
			}

			String title = "[SOMDAY 본인인증] 동덕여자대학교 학생인증 메일입니다.";
			String content = "<h2>SOMDAY 입니다.</h2><br><br>" 
					+ "<h3>" + studentId + "님</h3>" + "의 인증코드는<h3>" + verificationCode + "</h3><p>입니다.<p>"
					+ "<p>10분 이내로 어플에 인증 코드를 입력해주시길 바랍니다.<p>"
					+ "<p>(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)</p>"
					+ "<p>SOMDAY 문의 메일 : ____________</p>";
			
			String to = studentId + "@dongduk.ac.kr";
			
			mailService.send(title, content, to, null);	
			
			return new ResponseEntity<>(Response.res(StatusCode.CREATED, ResponseMessage.SEND_MAIL_OK), HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.SEND_MAIL_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /mail/{studentId}/code?code={code}
	 * @Method 설명 : 인증번호 검사
	 * @변경이력 :
	 */
	@ApiOperation(value="인증번호 검사")
	@RequestMapping(method=RequestMethod.GET, value="/{studentId}/code")
	public ResponseEntity checkAuthCode(@PathVariable String studentId, @RequestParam("code") Integer code) {
		try {
			String result = mailService.chechAuthCode(studentId, code);
			
			if (result.equals("NO_AUTH")) {
				return new ResponseEntity<>(Response.res(StatusCode.AUTHCODE_NO_HISTORY, ResponseMessage.NO_AUTHCODE_HISTORY), HttpStatus.BAD_REQUEST);
			}
			
			if (result.equals("NOT_MATCH")) {
				return new ResponseEntity<>(Response.res(StatusCode.AUTHCODE_NOT_MATCH, ResponseMessage.CHECK_AUTHCODE_FAIL), HttpStatus.BAD_REQUEST);
			}
			
			if (result.equals("EXPIRED")) {
				return new ResponseEntity<>(Response.res(StatusCode.AUTHCODE_EXPIRED, ResponseMessage.EXPIRED_AUTHCODE), HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.CHECK_AUTHCODE_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.SEND_MAIL_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
