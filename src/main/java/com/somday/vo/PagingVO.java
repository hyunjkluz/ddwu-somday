/**
 * 
 */
package com.somday.vo;

/**
 * @Since : Aug 26, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 26, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class PagingVO {
	private Integer pageSize = 2;
	private Integer page;
	private Integer remainPage;

	public PagingVO() {
		super();
	}

	/**
	 * @param page
	 * @param endPage
	 */
	public PagingVO(Integer page, Integer remainPage) {
		super();
		this.page = page;
		this.remainPage = remainPage;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public Integer getRemainPage() {
		return remainPage;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRemainPage(Integer endPage) {
		this.remainPage = endPage;
	}

	public void calcPage(Integer totalCount) {
		Integer totalPage = (int) Math.ceil((double)totalCount / (double)this.pageSize);

		if (totalPage <= 1 ) {
			this.remainPage = 0;
		} else {
			this.remainPage = totalPage - this.page;
		}
	}

	@Override
	public String toString() {
		return "PagingVO [page=" + page + ", remainPage=" + remainPage + "]";
	}

}
