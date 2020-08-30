/**
 * 
 */
package com.somday.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class NoticeVO {
	private Integer id;
	private NoticeCategoryVO category;
	private MajorVO major;
	private String title;
	private List<NoticeImgVO> images;
	private String content;
	private Date registeredAt;
	private Date updatedAt;

	public NoticeVO() {
	}

	/**
	 * @param id
	 * @param category
	 * @param major
	 * @param title
	 * @param images
	 * @param content
	 * @param registeredAt
	 * @param updatedAt
	 */
	public NoticeVO(Integer id, NoticeCategoryVO category, MajorVO major, String title, List<NoticeImgVO> images,
			String content, Date registeredAt, Date updatedAt) {
		super();
		this.id = id;
		this.category = category;
		this.major = major;
		this.title = title;
		this.images = images;
		this.content = content;
		this.registeredAt = registeredAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public NoticeCategoryVO getCategory() {
		return category;
	}

	public MajorVO getMajor() {
		return major;
	}

	public String getTitle() {
		return title;
	}

	public List<NoticeImgVO> getImages() {
		return images;
	}

	public String getContent() {
		return content;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategory(NoticeCategoryVO category) {
		this.category = category;
	}

	public void setMajor(MajorVO major) {
		this.major = major;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImages(List<NoticeImgVO> images) {
		this.images = images;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


}
