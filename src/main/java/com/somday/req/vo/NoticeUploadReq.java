/**
 * 
 */
package com.somday.req.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Since : Aug 27, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 27, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class NoticeUploadReq {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private String categoryId;
	private List<MultipartFile> fileList;
	private String src;

	public NoticeUploadReq() {
		super();
	}

	/**
	 * @param title
	 * @param content
	 * @param categoryId
	 * @param fileList
	 * @param src
	 */
	public NoticeUploadReq(@NotBlank String title, @NotBlank String content, @NotBlank String categoryId,
			List<MultipartFile> fileList, String src) {
		super();
		this.title = title;
		this.content = content;
		this.categoryId = categoryId;
		this.fileList = fileList;
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public String getSrc() {
		return src;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "NoticeUploadReq [title=" + title + ", content=" + content + ", categoryId=" + categoryId + ", fileList="
				+ fileList + ", src=" + src + "]";
	}

}
