/**
 * 
 */
package com.somday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somday.repository.mapper.NoticeMapper;
import com.somday.utils.CommonUtil;
import com.somday.vo.NoticeVO;

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
@Service
public class NoticeService {
	public static final Logger LOGGER = LoggerFactory.getLogger(NoticeService.class);

	@Autowired
	private NoticeMapper noticeMapper;

	/*
	 * 학과 ID를 넣으면 해당 카테고리 ID를 찾아 리스트 반환
	 */
	public NoticeVO[] getAllNotice(Integer majorId) {
		String majorNoticeCategory = getMajorNoticeCategory(majorId);
		return noticeMapper.selectAllNotice(majorNoticeCategory);
	}

	/*
	 * 카테고리별 공지 확인
	 */
	public NoticeVO[] getNoticeByCategory(String categoryId, Integer majorId) {
		if (!CommonUtil.isNotNull(majorId)) {
			categoryId = getMajorNoticeCategory(majorId);
		}
		return noticeMapper.selectNoticeByCategory(categoryId);
	}

	/*
	 * 학과에 맞는 공지 카테고리 ID 조회
	 */
	public String getMajorNoticeCategory(Integer majorId) {
		return noticeMapper.selectMajorNoticeCategory(majorId);
	}

	/*
	 * 전체 공지 제목으로 검색
	 */
	public NoticeVO[] searchAllNoticeBytitle(Integer majorId, String searchWord) {
		String majorNoticeCategory = getMajorNoticeCategory(majorId);
		return noticeMapper.selectAllNoticeByTitle(majorNoticeCategory, searchWord);
	}

	/*
	 * 카테고리별 공지 제목으로 검색
	 */
	public NoticeVO[] searchNoticeByCategoryAndTitle(String categoryId, String searchWord, Integer majorId) {
		if (!CommonUtil.isNotNull(majorId)) {
			categoryId = getMajorNoticeCategory(majorId);
		}
		return noticeMapper.selectNoticeByCategoryAndTitle(categoryId, searchWord);
	}

	/*
	 * 공지사항 디테일 확인
	 */
	public NoticeVO getNoticeDetailById(Integer noticeId) {
		return noticeMapper.selectNoticeById(noticeId);
	}
}
