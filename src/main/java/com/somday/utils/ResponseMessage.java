/**
 * 
 */
package com.somday.utils;

/**
 * @Since : Aug 23, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 23, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class ResponseMessage {
	public static final String NO_CONTENT = "데이터 없음";
    public static final String NULL_VALUE = "필요한 값 없음";
    
	public static final String INVALID_TOKEN = "토큰값이 유효하지 않습니다.";
	public static final String EMPTY_TOKEN = "토큰값이 존재하지 않습니다.";

	public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
	
	public static final String SEND_MAIL_OK="성공적으로 메일 발송";
	public static final String SEND_MAIL_FAIL="메일 전송 실패";
	
	public static final String INSERT_AUTHCODE_FAIL="인증코드 저장 실패";
	public static final String CHECK_AUTHCODE_SUCCESS="인증코드 매칭 성공";
	public static final String CHECK_AUTHCODE_FAIL="인증코드 매칭 실패";
	public static final String NO_AUTHCODE_HISTORY="인증코드 발송 내역 없음";
	public static final String EXPIRED_AUTHCODE="인증코드 기간 만료";
	
	public static final String TOKEN_CREATED="토큰 생성 성공";
	public static final String INSERT_STUDENT_FAIL="학생 저장 실패";
	public static final String CREATE_TOKEN_FAIL="토큰 생성 실패";
	
	public static final String EXISTS_STUDENT="이미 존재하는 학생입니다";
	public static final String NOT_EXISTS_STUDENT="존재하지 않는 학생입니다";
	
	public static final String LOGIN_FAIL="로그인 실패";
	public static final String REFRESH_TOKEN="재로그인 필요";
	
	public static final String DB_SELECT_FAIL="조회 실패";
	public static final String DB_INSERT_FAIL="삽입 실패";
	public static final String DB_UPDATE_FAIL="갱신 실패";
	public static final String DB_DELETE_FAIL="삭제 실패";
	
	public static final String DB_SELECT_SUCCESS="조회 성공";
	public static final String DB_INSERT_SUCCESS="삽입 성공";
	public static final String DB_UPDATE_SUCCESS="갱신 성공";
	public static final String DB_DELETE_SUCCESS="삭제 성공";
}
