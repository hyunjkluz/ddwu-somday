/**
 * 
 */
package com.somday.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.somday.req.vo.TokenReq;
import com.somday.service.NoticeService;
import com.somday.utils.CommonUtil;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.NoticePagingVO;
import com.somday.vo.NoticeVO;
import com.somday.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Controller
@Api(value="인증 API", description="공지사항 관련 API입니다.")
@RequestMapping(value="/apis/notice")
public class NoticeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/notice
	 * @Method 설명 : 공지사항 뷰 전체 조회
	 */
	@ApiOperation("공지사항 전체 조회")
	@GetMapping("")
	public ResponseEntity<?> getAllNotice(HttpServletRequest request, 
			@RequestParam(required = false, defaultValue = "1") Integer page) {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		NoticePagingVO noticeList = noticeService.getAllNotice(tokenInfo.getMajorId(), page);
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeList), HttpStatus.OK);
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/notice/category/{categoryId}
	 * @Method 설명 : 공지사항 - 카테고리별 공지 확인(major : 학과공지, CT02 : 취업, CT03 : 공모전)
	 */
	@ApiOperation(value="공지사항 - 카테고리별 공지 확인", notes = "categoryId 값 = (major : 학과공지, CT02 : 취업, CT03 : 공모전)")
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getNoticeByCategory(HttpServletRequest request, @PathVariable String categoryId,
			@RequestParam(required = false, defaultValue = "1") Integer page) {
		
		final String[] CATEGORY_ONLY = { "major", "CT02", "CT03", "CT04"};
		boolean check = Arrays.stream(CATEGORY_ONLY).anyMatch(categoryId::equals);
		
		if (!check) {
			return new ResponseEntity<>(Response.res(StatusCode.INVALID_RANGE, ResponseMessage.INCORRECT_VALUE), HttpStatus.BAD_REQUEST);
		}
		
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		NoticePagingVO noticeList = null;
		
		if (categoryId.equals("major")) {
			noticeList = noticeService.getNoticeByCategory(categoryId, tokenInfo.getMajorId(), page);
		} else {
			noticeList = noticeService.getNoticeByCategory(categoryId, null, page);
		}
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeList), HttpStatus.OK);
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/notice/{noticeId}
	 * @Method 설명 : 공지사항 내용 상세 조회
	 */
	@ApiOperation(value="공지사항 내용 상세 조회")
	@GetMapping("/{noticeId}")
	public ResponseEntity<?> getNoticeDetail(@PathVariable Integer noticeId) {
		NoticeVO noticeDetail = noticeService.getNoticeDetailById(noticeId);
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeDetail), HttpStatus.OK);
	}
	
	/**
	 * @HTTP Method : GET 
	 * @URI : /apis/notice/search?searchword={searchWord}
	 * @Method 설명 : 전체 공지사항 검색
	 */
	@ApiOperation(value="검색 - 전체 공지사항")
	@GetMapping("/search")
	public ResponseEntity<?> searchNoticeBytitle(HttpServletRequest request, @RequestParam String searchWord) {
		if(CommonUtil.isNotNull(searchWord)) {
			return new ResponseEntity<>(Response.res(StatusCode.NULL_VALUE, ResponseMessage.NULL_VALUE), HttpStatus.BAD_REQUEST);
		}
		
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		NoticeVO[] noticeList = noticeService.searchAllNoticeBytitle(tokenInfo.getMajorId(), searchWord);
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeList), HttpStatus.OK);
	}
	
	/**
	 * @HTTP Method : GET
	 * @URI : /apis/notice/search/category/{categoryId}?searchword={searchWord}
	 * @Method 설명 : 카테고리별 공지사항 검색(major : 학과공지, CT02 : 취업, CT03 : 공모전)
	 */
	@ApiOperation(value="검색 - 카테고리별 공지사항", notes = "categoryId 값 = (major : 학과공지, CT02 : 취업, CT03 : 공모전)")
	@GetMapping("/search/category/{categoryId}")
	public ResponseEntity<?> searchNoticeByCategoryAndTitle(HttpServletRequest request, @PathVariable String categoryId, @RequestParam String searchWord) {
		final String[] CATEGORY_ONLY = { "major", "CT02", "CT03", "CT04"};
		boolean check = Arrays.stream(CATEGORY_ONLY).anyMatch(categoryId::equals);
		
		if (!check) {
			return new ResponseEntity<>(Response.res(StatusCode.INVALID_RANGE, ResponseMessage.INCORRECT_VALUE), HttpStatus.BAD_REQUEST);
		}
		
		if(CommonUtil.isNotNull(searchWord)) {
			return new ResponseEntity<>(Response.res(StatusCode.NULL_VALUE, ResponseMessage.NULL_VALUE), HttpStatus.BAD_REQUEST);
		}
		
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		NoticeVO[] noticeList = null;
		
		if (categoryId.equals("major")) {
			noticeList = noticeService.searchNoticeByCategoryAndTitle(categoryId, searchWord, tokenInfo.getMajorId());
		} else {
			noticeList = noticeService.searchNoticeByCategoryAndTitle(categoryId, searchWord, null);
		}
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeList), HttpStatus.OK);
	}
	
	
	@GetMapping("/top")
	public ResponseEntity<?> getMajorTopNotice(HttpServletRequest request) {
		TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");
		
		NoticeVO noticeList = noticeService.getMajorTopNotice(tokenInfo.getMajorId());
		
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, noticeList), HttpStatus.OK);
	}
	
	
	
	

}
