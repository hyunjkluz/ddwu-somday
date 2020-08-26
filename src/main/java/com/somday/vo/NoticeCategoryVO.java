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
public class NoticeCategoryVO {
	private String id;
	private String name;

	public NoticeCategoryVO() {
	}

	/**
	 * @param id
	 * @param name
	 */
	public NoticeCategoryVO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "NoticeCategoryVO [id=" + id + ", name=" + name + "]";
	}

}
