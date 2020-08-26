/**
 * 
 */
package com.somday.error;

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
@SuppressWarnings("serial")
public class NoToken extends Exception {
	private final int ERR_CODE;

	public NoToken(String msg, int errCode) { 
		super(msg);
		ERR_CODE = errCode;
	}

	public NoToken(String msg) {
		this(msg, 401);
	}

	public int getErrCode() {
		return ERR_CODE;

	}

}
