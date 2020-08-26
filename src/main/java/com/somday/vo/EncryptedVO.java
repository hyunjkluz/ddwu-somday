/**
 * 
 */
package com.somday.vo;

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
public class EncryptedVO {
	private String encryptedPhone;
	private String encryptedStudentId;
	private String encryptedPw;

	public EncryptedVO() {
	}

	/**
	 * @param encryptedPhone
	 * @param encryptedStudentId
	 * @param encryptedPW
	 */
	public EncryptedVO(String encryptedStudentId, String encryptedPW, String encryptedPhone) {
		super();
		this.encryptedPhone = encryptedPhone;
		this.encryptedStudentId = encryptedStudentId;
		this.encryptedPw = encryptedPW;
	}

	public String getEncryptedPhone() {
		return encryptedPhone;
	}

	public String getEncryptedStudentId() {
		return encryptedStudentId;
	}

	public String getEncryptedPw() {
		return encryptedPw;
	}

	public void setEncryptedPhone(String encryptedPhone) {
		this.encryptedPhone = encryptedPhone;
	}

	public void setEncryptedStudentId(String encryptedStudentId) {
		this.encryptedStudentId = encryptedStudentId;
	}

	public void setEncryptedPw(String encryptedPW) {
		this.encryptedPw = encryptedPW;
	}

	@Override
	public String toString() {
		return "EncryptedVO [encryptedPhone=" + encryptedPhone + ", encryptedStudentId=" + encryptedStudentId
				+ ", encryptedPW=" + encryptedPw + "]";
	}

}
