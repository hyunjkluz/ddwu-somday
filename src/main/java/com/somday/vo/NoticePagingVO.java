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
public class NoticePagingVO {
	private NoticeVO[] noticeList;
	private PagingVO pagination;

	/**
	 * @param topNotice
	 * @param noticeList
	 * @param pagination
	 */
	public NoticePagingVO(NoticeVO[] noticeList, PagingVO pagination) {
		super();
		this.noticeList = noticeList;
		this.pagination = pagination;
	}


	public NoticeVO[] getNoticeList() {
		return noticeList;
	}

	public PagingVO getPagination() {
		return pagination;
	}


	public void setNoticeList(NoticeVO[] noticeList) {
		this.noticeList = noticeList;
	}

	public void setPagination(PagingVO pagination) {
		this.pagination = pagination;
	}

}
