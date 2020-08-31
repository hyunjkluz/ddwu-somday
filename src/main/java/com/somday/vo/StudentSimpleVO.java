/**
 * 
 */
package com.somday.vo;

/**
 * @Since : Aug 31, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 31, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class StudentSimpleVO {
	private String name;
	private String studentId;
	private String step;
	private MajorVO major;

	public StudentSimpleVO() {
		super();
	}

	/**
	 * @param name
	 * @param studentId
	 * @param major
	 */
	public StudentSimpleVO(String name, String studentId, String step, MajorVO major) {
		super();
		this.name = name;
		this.studentId = studentId;
		this.step = step;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public String getStudentId() {
		return studentId;
	}

	public MajorVO getMajor() {
		return major;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setMajor(MajorVO major) {
		this.major = major;
	};

}
