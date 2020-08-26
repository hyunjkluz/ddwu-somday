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
public class MemoVO {
	private Integer id;
	private String content;

	/**
	 * @param id
	 * @param content
	 */
	public MemoVO(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public MemoVO() {
	}

	public Integer getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MemoVO [id=" + id + ", content=" + content + "]";
	}

}
