/**
 * 
 */
package com.somday.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.somday.vo.MailAuthVO;

/**
 * @Since : Aug 24, 2020 
 * @Author kimhyunjin
 * <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성 
 * </pre>
 *
 */
public interface MailAuthMapper {
	MailAuthVO selectCodeByStudentId(@Param("studentId")String studentId);
	
	Integer insertCode(@Param("studentId")String studentId, @Param("code") Integer code);
	

}
