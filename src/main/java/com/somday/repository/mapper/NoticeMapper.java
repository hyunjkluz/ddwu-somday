/**
 * 
 */
package com.somday.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.somday.vo.NoticeVO;

/**
 * @Since : Aug 25, 2020 
 * @Author kimhyunjin
 * <pre>
 * -----------------
 * 개정이력
 * Aug 25, 2020 kimhyunjin : 최초작성 
 * </pre>
 *
 */
public interface NoticeMapper {
	NoticeVO[] selectNoticeByCategory(@Param("categoryId")String categoryId);
	
	NoticeVO[] selectAllNotice(@Param("majorNoticeCategory")String majorNoticeCategory);
	
	NoticeVO[] selectNoticeByCategoryAndTitle(@Param("categoryId")String categoryId, @Param("searchWord")String searchWord);
	
	NoticeVO[] selectAllNoticeByTitle(@Param("majorNoticeCategory")String majorNoticeCategory, @Param("searchWord")String searchWord);
	
	NoticeVO selectNoticeById(@Param("id")Integer noticeId);
	
	String selectMajorNoticeCategory(@Param("majorId")Integer majorId);
}
