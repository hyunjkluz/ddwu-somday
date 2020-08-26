/**
 * 
 */
package com.somday.req.vo;

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
public class TokenReq {
	private String id;
	private String encryptedStudentId;
	private Integer majorId;

	/**
	 * @param id
	 * @param studentId
	 * @param majorId
	 */
	public TokenReq(String id, String encryptedStudentId, Integer majorId) {
		super();
		this.id = id;
		this.encryptedStudentId = encryptedStudentId;
		this.majorId = majorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEncryptedStudentId() {
		return encryptedStudentId;
	}

	public void setEncryptedStudentId(String encryptedStudentId) {
		this.encryptedStudentId = encryptedStudentId;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	@Override
	public String toString() {
		return "TokenReq [id=" + id + ", encryptedStudentId=" + encryptedStudentId + ", majorId=" + majorId + "]";
	}

}
