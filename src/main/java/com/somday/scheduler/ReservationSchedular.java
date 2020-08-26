/**
 * 
 */
package com.somday.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.somday.repository.mapper.WorkMapper;

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
@Component
public class ReservationSchedular {
	@Autowired
	private WorkMapper workMapper;
	
	@Scheduled(cron="0 0 0 * * *")
	public void deleteMailAuth() {
		System.out.println("야작 신청 테이블 삭제 스케줄러 시작");
		
		Date dDate = new Date();
		dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyy/MM/dd HH");
		String yesterday = dSdf.format(dDate);
		
		workMapper.deleteYesterdayData(yesterday);
		System.out.println("야작 신청 테이블 삭제 스케줄러 성공");
	}

}

