/**
 * 
 */
package com.somday.vo;

import java.util.Arrays;
import java.util.Date;

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
	private NoticeVO[] images;
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
	public NoticeVO(Integer id, NoticeCategoryVO category, MajorVO major, String title, NoticeVO[] images,
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

	public NoticeVO[] getImages() {
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

	@Override
	public String toString() {
		return "NoticeVO [id=" + id + ", category=" + category + ", major=" + major + ", title=" + title + ", images="
				+ Arrays.toString(images) + ", content=" + content + ", registeredAt=" + registeredAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
