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
import com.somday.utils.MajorCateUtil;
import com.somday.vo.NoticePagingVO;
import com.somday.vo.NoticeVO;
import com.somday.vo.Pagination;
import com.somday.vo.PagingVO;

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
	public static final Integer PAGE_SIZE = 10;

	@Autowired
	private NoticeMapper noticeMapper;

	/*
	 * 학과 ID를 넣으면 해당 카테고리 ID를 찾아 리스트 반환
	 */
	public NoticePagingVO getAllNotice(Integer majorId, Integer page) {
		String majorNoticeCategory = MajorCateUtil.format(majorId);
		
		PagingVO paging = new PagingVO();
		paging.setPage(page);
		
		Integer noticeCount = noticeMapper.countAllNotice(majorNoticeCategory);
		paging.calcPage(noticeCount);
		
		Integer start = (page - 1) * PAGE_SIZE;
		
		NoticeVO[] noticeList = noticeMapper.selectAllNotice(majorNoticeCategory, start, PAGE_SIZE);
		
		return new NoticePagingVO(noticeList, paging);
	}

	/*
	 * 카테고리별 공지 확인
	 */
	public NoticePagingVO getNoticeByCategory(String categoryId, Integer majorId, Integer page) {
		if (!CommonUtil.isNotNull(majorId)) {
			categoryId = MajorCateUtil.format(majorId);
		}
		
		PagingVO paging = new PagingVO();
		paging.setPage(page);
		
		Integer noticeCount = noticeMapper.countCategoryAllNotice(categoryId);
		paging.calcPage(noticeCount);
		
		Integer start = (page - 1) * PAGE_SIZE;
		
		NoticeVO[] noticeList =  noticeMapper.selectNoticeByCategory(categoryId, start, PAGE_SIZE);
		
		return new NoticePagingVO(noticeList, paging);
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
		String majorNoticeCategory = MajorCateUtil.format(majorId);
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

	public NoticeVO getMajorTopNotice(Integer majorId) {
		String majorNoticeCategory = MajorCateUtil.format(majorId);
		
		NoticeVO sss = noticeMapper.selectMajorTopNotice(majorNoticeCategory);
		LOGGER.info(sss.toString());
		LOGGER.info(sss.getRegisteredAt().toString());
		return sss;
	}
}
