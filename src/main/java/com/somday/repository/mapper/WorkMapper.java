/**
 * 
 */
package com.somday.repository.mapper;

import org.apache.ibatis.annotations.Param;

import com.somday.vo.RoomVO;
import com.somday.vo.WorkVO;

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
public interface WorkMapper {

	WorkVO selectReservationByStudentId(@Param("studentId") String studentId);

	Integer insertTodayReservation(@Param("studentId") String studentId, @Param("roomId") Integer roomId);

	Integer deleteTodayReservation(@Param("id") Integer workId);

	RoomVO[] selectRoomOfMajor(@Param("majorId") Integer majorId);
	
	Integer deleteYesterdayData(@Param("yesterday")String yesterday);

}
