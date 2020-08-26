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
public class RoomVO {
	private Integer id;
	private String name;
	private Integer majorId;

	public RoomVO() {
	}

	/**
	 * @param id
	 * @param name
	 * @param majorId
	 */
	public RoomVO(Integer id, String name, Integer majorId) {
		super();
		this.id = id;
		this.name = name;
		this.majorId = majorId;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getMajorId() {
		return majorId;
	}

	@Override
	public String toString() {
		return "RoomVO [id=" + id + ", name=" + name + ", majorId=" + majorId + "]";
	}

}
