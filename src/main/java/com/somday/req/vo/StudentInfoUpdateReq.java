/**
 * 
 */
package com.somday.req.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Since : Aug 26, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 26, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class StudentInfoUpdateReq {
	private String id;
	@NotNull
	private Integer majorId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String password;
	@NotEmpty
	private String phone;
	private String encryptedPw;
	private String encryptedPhone;

	public StudentInfoUpdateReq() {
	}

	/**
	 * @param id
	 * @param majorId
	 * @param name
	 * @param password
	 * @param phone
	 * @param encryptedPw
	 * @param encryptedPhone
	 */
	public StudentInfoUpdateReq(String id, @NotNull Integer majorId, @NotNull String name, @NotEmpty String password,
			@NotEmpty String phone, String encryptedPw, String encryptedPhone) {
		super();
		this.id = id;
		this.majorId = majorId;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.encryptedPw = encryptedPw;
		this.encryptedPhone = encryptedPhone;
	}

	public String getId() {
		return id;
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

	public String getPhone() {
		return phone;
	}

	public String getEncryptedPw() {
		return encryptedPw;
	}

	public String getEncryptedPhone() {
		return encryptedPhone;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setEncryptedPw(String encryptedPw) {
		this.encryptedPw = encryptedPw;
	}

	public void setEncryptedPhone(String encryptedPhone) {
		this.encryptedPhone = encryptedPhone;
	}

	@Override
	public String toString() {
		return "StudentInfoUpdateReq [id=" + id + ", majorId=" + majorId + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", encryptedPw=" + encryptedPw + ", encryptedPhone=" + encryptedPhone + "]";
	}

}
