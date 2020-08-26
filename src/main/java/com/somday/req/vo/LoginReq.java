/**
 * 
 */
package com.somday.req.vo;

import javax.validation.constraints.NotNull;

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
public class LoginReq {
	@NotNull
	private String studentId;
	@NotNull
	private String password;

	public LoginReq() {
	}

	/**
	 * @param studentId
	 * @param password
	 */
	public LoginReq(@NotNull String studentId, @NotNull String password) {
		super();
		this.studentId = studentId;
		this.password = password;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginReq [studentId=" + studentId + ", password=" + password + "]";
	}

}
