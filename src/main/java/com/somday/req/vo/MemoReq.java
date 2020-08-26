/**
 * 
 */
package com.somday.req.vo;

import javax.validation.constraints.NotNull;

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
public class MemoReq {
	@NotNull
	private String content;

	public MemoReq() {
	}

	/**
	 * @param sontent
	 */
	public MemoReq(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setSontent(String content) {
		this.content = content;
	}

}
