/**
 * 
 */
package com.somday.vo;

import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;

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
public class Response<T> {

	private int status;

	private String message;

	private T data;

	public Response(final int status, final String message) {
		this.status = status;
		this.message = message;
		this.data = null;
	}

	public Response(int status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> Response<T> res(final int status, final String message) {
		return res(status, message, null);
	}

	public static <T> Response<T> res(final int status, final String message, final T t) {
		return new Response<T>(status, message, t);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static final Response FAIL_DEFAULT_RES = new Response(StatusCode.INTERNAL_SERVER_ERROR,
			ResponseMessage.INTERNAL_SERVER_ERROR);

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}