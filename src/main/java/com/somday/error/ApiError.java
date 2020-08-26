/**
 * 
 */
package com.somday.error;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @Since : Aug 24, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class ApiError {

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ApiError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiError [status=" + status + ", message=" + message + ", errors=" + errors + "]";
	}

}
