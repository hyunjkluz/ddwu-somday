/**
 * 
 */
package com.somday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somday.repository.mapper.MemoMapper;
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
@Service
public class MemoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemoService.class);

	@Autowired
	private MemoMapper memoMapper;

	public MemoVO[] getAllMemoByStudentId(String studentId) {
		return memoMapper.selectMemoByStudentId(studentId);
	}

	public Integer postMemo(String studentId, String content) {
		return memoMapper.insertMemo(studentId, content);
	}

	public Integer updateMemo(Integer memoId, String content) {
		return memoMapper.updateMemoById(memoId, content);
	}

	public Integer deleteMemo(Integer memoId) {
		return memoMapper.deleteMemoById(memoId);
	}
	
	public Integer updateMemoCheckedState(Integer memoId, boolean checked) {
		return memoMapper.updateMemoChecked(memoId, checked);
	}

}
