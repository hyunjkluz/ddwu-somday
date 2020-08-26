/**
 * 
 */
package com.somday.error;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.Response;

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
@ControllerAdvice
@EnableWebMvc
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRestExceptionHandler.class);
	
	
	/**
	 * 특정 핸들러가 없는 다른 예외
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		LOGGER.info("특정 핸들러가 없는 다른 예외" + ex.toString());
		
		String exceptionName = ex.getClass().getSimpleName();
		LOGGER.info(exceptionName);
		
		if (exceptionName.equals("NoToken")) {
			return new ResponseEntity<>(Response.res(StatusCode.UNAUTHORIZED, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
		}
		
		if (exceptionName.equals("ExpiredJwtException")) {
			return new ResponseEntity<>(Response.res(StatusCode.AUTHCODE_EXPIRED, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
		} 

		if (exceptionName.equals("SignatureException")) {
			return new ResponseEntity<>(Response.res(StatusCode.AUTHCODE_NOT_MATCH, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
		}
		
		if (exceptionName.equals("DBError") || exceptionName.equals("BadSqlGrammarException")) {
			return new ResponseEntity<>(Response.res(StatusCode.DB_ERROR, ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (exceptionName.equals("IllegalArgumentException")) {
			return new ResponseEntity<>(Response.res(StatusCode.BAD_REQUEST, ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
		}	
		
		return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * BindException :이 예외는 치명적인 바인딩 오류가 발생할 때 throw됩니다.
	 * MethodArgumentNotValidException : @Valid 주석이 달린 인수 가 유효성 검사에 실패 하면이 예외가 발생 합니다.
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
		LOGGER.info("첫번째 에러에 걸림");
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    
	    
	    Response response = new Response(StatusCode.BAD_REQUEST, errors.toString());
	    
	    return handleExceptionInternal(
	      ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/** 
	 * MissingServletRequestPartException :이 예외는 멀티 파트 요청의 일부를 찾을 수 없을 때 발생합니다.
	 * MissingServletRequestParameterException :이 예외는 요청 누락 매개 변수가있을 때 발생합니다.
	 */
	@Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
		LOGGER.info("두번째 에러에 걸림");
	    String error = ex.getParameterName() + " parameter is missing";
	    
	    
	    Response response = new Response(StatusCode.BAD_REQUEST, error);
	    
	    return new ResponseEntity<Object>(
	    		response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * ConstrainViolationException :이 예외는 제약 조건 위반의 결과를보고합니다.
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
		LOGGER.info("세번째 에러에 걸림");
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }
	 
	    Response response = new Response(StatusCode.BAD_REQUEST, errors.toString());
	    
	    return new ResponseEntity<Object>(
	    		response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();
	 
	    
	    Response response = new Response(StatusCode.BAD_REQUEST, error);
	    
	    return new ResponseEntity<Object>(
	    		response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 404 NOT FOUD ERROR
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
	 
	    Response response = new Response(StatusCode.NOT_FOUND, error);
	    
	    return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
}

