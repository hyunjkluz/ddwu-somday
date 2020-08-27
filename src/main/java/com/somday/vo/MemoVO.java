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
	private boolean checked;

	/**
	 * @param id
	 * @param content
	 */
	public MemoVO(Integer id, String content, boolean checked) {
		super();
		this.id = id;
		this.content = content;
		this.checked = checked;
	}

	public MemoVO() {
	}

	public Integer getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "MemoVO [id=" + id + ", content=" + content + ", checked=" + this.checked + "]";
	}

}
