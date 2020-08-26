/**
 * 
 */
package com.somday.req.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class StudentInfoReq {
	private String id;
	@NotEmpty
	private String studentId;
	@NotNull
	private Integer majorId;
	@NotNull
	private String name;
	@NotEmpty
	private String password;
	@NotEmpty
	private String phone;
	private String encryptedStudentId;
	private String encryptedPw;
	private String encryptedPhone;

	public StudentInfoReq() {
	}

	/**
	 * @param id
	 * @param studentId
	 * @param majorId
	 * @param name
	 * @param password
	 * @param phone
	 * @param encryptedStudentId
	 * @param encryptedPw
	 * @param encryptedPhone
	 */
	public StudentInfoReq(String id, @NotEmpty String studentId, @NotNull Integer majorId, @NotNull String name,
			@NotEmpty String password, @NotEmpty String phone, String encryptedStudentId, String encryptedPw,
			String encryptedPhone) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.majorId = majorId;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.encryptedStudentId = encryptedStudentId;
		this.encryptedPw = encryptedPw;
		this.encryptedPhone = encryptedPhone;
	}

	public String getId() {
		return id;
	}

	public String getEncryptedPw() {
		return encryptedPw;
	}

	public String getEncryptedStudentId() {
		return encryptedStudentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEncryptedPhone() {
		return encryptedPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setEncryptedPhone(String encryptedPhone) {
		this.encryptedPhone = encryptedPhone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEncryptedPw(String encryptedPw) {
		this.encryptedPw = encryptedPw;
	}

	public void setEncryptedStudentId(String encryptedStudentId) {
		this.encryptedStudentId = encryptedStudentId;
	}

	@Override
	public String toString() {
		return "StudentInfoReq [id=" + id + ", studentId=" + studentId + ", majorId=" + majorId + ", name=" + name
				+ ", password=" + password + ", phone=" + phone + ", encryptedStudentId=" + encryptedStudentId
				+ ", encryptedPw=" + encryptedPw + ", encryptedPhone=" + encryptedPhone + "]";
	}

}
