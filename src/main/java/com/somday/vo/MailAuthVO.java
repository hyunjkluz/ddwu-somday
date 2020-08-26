/**
 * 
 */
package com.somday.vo;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class MailAuthVO {
	private Integer id;
	private String studentId;
	private Integer code;
	private Date registeredAt;

	public MailAuthVO() {}

	/**
	 * @param id
	 * @param studentId
	 * @param code
	 * @param registeredAt
	 * @param expriedAt
	 */
	public MailAuthVO(Integer id, String studentId, Integer code, Date registeredAt) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.code = code;
		this.registeredAt = registeredAt;
	}

	public Integer getId() {
		return id;
	}

	public String getStudentId() {
		return studentId;
	}

	public Integer getCode() {
		return code;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	
	public boolean isExpried() throws ParseException {
		long reqTime = System.currentTimeMillis();

		long timeDiff = reqTime - registeredAt.getTime();
		
		System.out.println("시간차(밀리초) : " + timeDiff);
		
		if (timeDiff >= 0 && timeDiff <= 600000) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "MailAuthVo [id=" + id + ", studentId=" + studentId + ", code=" + code + ", registeredAt=" + registeredAt  + "]";
	}

}
