/**
 * 
 */
package com.somday.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somday.error.DBError;
import com.somday.repository.mapper.StudentMapper;
import com.somday.req.vo.StudentInfoReq;
import com.somday.req.vo.StudentInfoUpdateReq;
import com.somday.utils.CommonUtil;
import com.somday.vo.EncryptedVO;
import com.somday.vo.StudentInfoVO;

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
@Service
public class StudentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentMapper studentMapper;

	public String getStudentId(String encryptedStudentId) {
		return studentMapper.selectStudentIdByStudentId(encryptedStudentId);
	}

	public String getId(String encryptedStudentId) {
		return studentMapper.selectIdByStudentId(encryptedStudentId);
	}

	public StudentInfoVO getStudentInfo(String studentId) {
		try {
			return studentMapper.selectStudentByStudentId(studentId);
		} catch (NullPointerException nullExp) {
			return null;
		}
	}

	public Integer createStudent(StudentInfoReq info) {
		LOGGER.info(info.toString());
		return studentMapper.insertStudent(info);
	}

	public Integer putPasswordById(String id, String encryptedPw) {
		return studentMapper.updatePasswordById(id, encryptedPw);
	}

	public Integer putStudentInfo(StudentInfoUpdateReq newInfo) throws DBError {
		Integer result = studentMapper.updateStudentInfo(newInfo);
		if (CommonUtil.isNotNull(result)) {
			throw new DBError("학생 개인정보 수정 DB 에러");
		}
		return result;
	}

}
