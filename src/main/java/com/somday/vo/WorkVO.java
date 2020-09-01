/**
 * 
 */
package com.somday.vo;

import java.util.Date;

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
public class WorkVO {
	private Integer id;
	private String studentId;
	private Date registeredAt;
	private RoomVO room;

	public WorkVO() {
	}

	/**
	 * @param id
	 * @param studentId
	 * @param room
	 */
	public WorkVO(Integer id, String studentId, Date registeredAt, RoomVO room) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.registeredAt = registeredAt;
		this.room = room;
	}

	public Integer getId() {
		return id;
	}

	public String getStudentId() {
		return studentId;
	}

	public RoomVO getRoom() {
		return room;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public void setRoom(RoomVO room) {
		this.room = room;
	}

}
