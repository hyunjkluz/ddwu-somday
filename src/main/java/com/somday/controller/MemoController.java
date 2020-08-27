/**
 * 
 */
package com.somday.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.somday.req.vo.MemoReq;
import com.somday.req.vo.TokenReq;
import com.somday.service.MemoService;
import com.somday.service.SecurityService;
import com.somday.utils.ResponseMessage;
import com.somday.utils.StatusCode;
import com.somday.vo.MemoVO;
import com.somday.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Controller
@Api(value = "인증 API", description = "메모 조작 API입니다.")
@RequestMapping(value = "/apis/memo")
public class MemoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemoController.class);

	@Autowired
	private SecurityService securityService;
	@Autowired
	private MemoService memoService;

	/**
	 * @HTTP Method : GET
	 * @URI : /apis/memo
	 * @Method 설명 : 사용자의 전체 메모 조회
	 * @변경이력 :
	 */
	@ApiOperation(value="사용자의 전체 메모 조회")
	@GetMapping("")
	public ResponseEntity<?> getUsersAllMemo(HttpServletRequest request) {
		try {
			TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");

			MemoVO[] memoList = memoService.getAllMemoByStudentId(tokenInfo.getId());

			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_SELECT_SUCCESS, memoList), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("메모 조회 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.DB_SELECT_FAIL),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @HTTP Method : POST
	 * @URI : /apis/memo
	 * @Method 설명 : 메모 저장
	 * @변경이력 :
	 */
	@ApiOperation(value="메모 저장")
	@PostMapping("")
	public ResponseEntity<?> postUserMemo(HttpServletRequest request, @RequestBody @Valid MemoReq memoReq) {
		try {
			TokenReq tokenInfo = (TokenReq) request.getAttribute("tokenBody");

			memoService.postMemo(tokenInfo.getId(), memoReq.getContent());

			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_INSERT_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("메모 삽입 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.DB_INSERT_FAIL),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @HTTP Method : PUT
	 * @URI : /apis/memo/{meoId}
	 * @Method 설명 : 메모 내용 수정
	 * @변경이력 :
	 */
	@ApiOperation(value="메모 내용 수정")
	@PutMapping("/{memoId}")
	public ResponseEntity<?> putUserMemo(HttpServletRequest request, @RequestBody @Valid MemoReq memoReq, @PathVariable Integer memoId) {
		try {
			Integer isUpdate = memoService.updateMemo(memoId, memoReq.getContent());

			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_UPDATE_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("메모 갱신 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.DB_UPDATE_FAIL),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @HTTP Method :DELETE
	 * @URI : /apis/memo/{memoId}
	 * @Method 설명 : 메모 삭제
	 * @변경이력 :
	 */
	@ApiOperation(value="메모 삭제")
	@DeleteMapping("/{memoId}")
	public ResponseEntity<?> deleteUserMemo(HttpServletRequest request, @PathVariable Integer memoId) {
		try {
			Integer isDelete = memoService.deleteMemo(memoId);

			return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_DELETE_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("메모 삭제 에러 : " + e.toString());
			return new ResponseEntity<>(Response.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.DB_DELETE_FAIL),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @HTTP Method : PUT
	 * @URI : /apis/memo/{memoId}/checked?checked=
	 * @Method 설명 : 메모 체크 상태 변
	 * @변경이력 :
	 */
	@PutMapping("/{memoId}/checked")
	public ResponseEntity<?> updateMemoCheckedState(@PathVariable Integer memoId, @RequestParam(value="checked") boolean checked ) {
		memoService.updateMemoCheckedState(memoId, checked);
		return new ResponseEntity<>(Response.res(StatusCode.OK, ResponseMessage.DB_UPDATE_SUCCESS), HttpStatus.OK);
	}

}
