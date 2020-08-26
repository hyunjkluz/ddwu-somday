/**
 * 
 */
package com.somday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somday.error.DBError;
import com.somday.repository.mapper.WorkMapper;
import com.somday.utils.CommonUtil;
import com.somday.vo.RoomVO;
import com.somday.vo.WorkVO;

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
@Service
public class WorkService {
	public static final Logger LOGGER = LoggerFactory.getLogger(WorkService.class);
	
	@Autowired
	private WorkMapper workMapper;
	
	public WorkVO getWorkReservation(String studentId) {
		return workMapper.selectReservationByStudentId(studentId);
	}
	
	public RoomVO[] getRoomList(Integer majorId) {
		RoomVO[] roomList = workMapper.selectRoomOfMajor(majorId);
		
		if (CommonUtil.isNotNull(roomList)) {
			throw new IllegalArgumentException("학과 번호 오류");
		}
		
		return roomList;
	}
	
	public void postWorkReservation(String studentId, Integer roomId) throws DBError {
		if (workMapper.insertTodayReservation(studentId, roomId) == null) {
			throw new DBError("DB 삽입 에러");
		}
	}
	
	public void deleteWorkReservation(Integer workId) throws DBError {
		if (workMapper.deleteTodayReservation(workId) == null) {
			throw new DBError("DB 삭제 에러");
		}
	}
	
}
