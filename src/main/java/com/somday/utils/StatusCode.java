/**
 * 
 */
package com.somday.utils;

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
public class StatusCode {
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int NO_CONTENT = 204;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int SERVICE_UNAVAILABLE = 503;
	public static final int DB_ERROR = 600;
	
	public static final int AUTHCODE_NO_HISTORY=701;
	public static final int AUTHCODE_NOT_MATCH=702;
	public static final int AUTHCODE_EXPIRED=703;
	
	public static final int TOKEN_CREATE_ERROR=444;
	
	public static final int REFRESH_TOKEN=408;
}
