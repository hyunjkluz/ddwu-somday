/**
 * 
 */
package com.somday.vo;

import java.sql.Date;

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
public class StudentInfoVO {
	private String id;
	private String name;
	private String studentId;
	private String password;
	private String step;
	private String type;
	private MajorVO major;
	private String registeredAt;

	public StudentInfoVO() {
	}

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param step
	 * @param type
	 * @param major
	 * @param registeredAt
	 */
	public StudentInfoVO(String id, String name, String studentId, String password, String step, String type,
			MajorVO major, String registeredAt) {
		super();
		this.id = id;
		this.name = name;
		this.studentId = studentId;
		this.password = password;
		this.step = step;
		this.type = type;
		this.major = major;
		this.registeredAt = registeredAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MajorVO getMajor() {
		return major;
	}

	public void setMajor(MajorVO major) {
		this.major = major;
	}

	public String getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(String registeredAt) {
		this.registeredAt = registeredAt;
	}

	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", name=" + name + ", password=" + password + ", step=" + step + ", type=" + type
				+ ", major=" + major + ", registeredAt=" + registeredAt + "]";
	}

}
