/**
 * 
 */
package com.somday.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.somday.req.vo.StudentInfoReq;
import com.somday.req.vo.StudentInfoUpdateReq;
import com.somday.vo.StudentInfoVO;
import com.somday.vo.StudentSimpleVO;

/**
 * @Since : Aug 24, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public interface StudentMapper {

	String selectStudentIdByStudentId(@Param("encryptedStudentId") String encryptedStudentId);
	
	String selectIdByStudentId(@Param("encryptedStudentId") String encryptedStudentId);

	StudentInfoVO selectStudentByStudentId(@Param("studentId") String studentId);

	Integer insertStudent(StudentInfoReq info);

	Integer updatePasswordById(@Param("id")String id, @Param("newPw")String encryptedPw);
	
	Integer updateStudentInfo(StudentInfoUpdateReq info);
	
	StudentSimpleVO selectStudentSimpleInfoById(@Param("id")String id);
	
}
