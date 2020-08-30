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
public class NoticeImgVO {
	private Integer id;
	private String imageUrl;

	public NoticeImgVO() {
	}

	/**
	 * @param id
	 * @param imageUrl
	 */
	public NoticeImgVO(Integer id, String imageUrl) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "NoticeImgVO [id=" + id + ", imageUrl=" + imageUrl + "]";
	}

}
