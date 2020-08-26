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
public class DBError extends Exception {
	private final int ERR_CODE;

	public DBError(String msg, int errCode) { 
		super(msg);
		ERR_CODE = errCode;
	}

	public DBError(String msg) {
		this(msg, 600);
	}

	public int getErrCode() {
		return ERR_CODE;

	}

}
