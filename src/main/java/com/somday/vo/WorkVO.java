/**
 * 
 */
package com.somday.vo;

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
	private RoomVO room;

	public WorkVO() {
	}

	/**
	 * @param id
	 * @param studentId
	 * @param room
	 */
	public WorkVO(Integer id, String studentId, RoomVO room) {
		super();
		this.id = id;
		this.studentId = studentId;
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

	@Override
	public String toString() {
		return "WorkVO [id=" + id + ", studentId=" + studentId + ", room=" + room + "]";
	}

}
