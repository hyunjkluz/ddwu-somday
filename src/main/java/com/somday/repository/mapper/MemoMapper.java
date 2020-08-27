/**
 * 
 */
package com.somday.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.somday.vo.MemoVO;

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
public interface MemoMapper {
	MemoVO[] selectMemoByStudentId(@Param("studentId") String studentId);

	Integer insertMemo(@Param("studentId") String studentId, @Param("content") String content);

	Integer updateMemoById(@Param("id") Integer memoId, @Param("content") String content);

	Integer deleteMemoById(@Param("id") Integer memoId);
	
	Integer updateMemoChecked(@Param("id") Integer memoId, @Param("checked")boolean checked);

}
