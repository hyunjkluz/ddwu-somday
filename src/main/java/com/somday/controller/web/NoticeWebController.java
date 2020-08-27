/**
 * 
 */
package com.somday.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.somday.req.vo.NoticeUploadReq;
import com.somday.service.NoticeService;
import com.somday.utils.CommonUtil;
import com.somday.utils.S3Util;
import com.somday.utils.UploadFileUtils;
import com.somday.vo.NoticeCategoryVO;
import com.somday.vo.Response;

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
@RequestMapping(value = "/web/notice")
@SessionAttributes({ "categories", "noticeUploadReq" })
public class NoticeWebController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeWebController.class);

	S3Util s3 = new S3Util();
	String bucketName = "almombucket";

	@Autowired
	private NoticeService noticeService;

	@ModelAttribute("categories")
	public List<NoticeCategoryVO> getNoticeCategories() {
		return new ArrayList<NoticeCategoryVO>() {
			{
				add(new NoticeCategoryVO("CT02", "취업"));
				add(new NoticeCategoryVO("CT03", "공모전"));
				add(new NoticeCategoryVO("CT04", "편의시설"));
			}
		};
	}

	@ModelAttribute("noticeUploadReq")
	public NoticeUploadReq getNoticeUploadForm() {
		return new NoticeUploadReq();
	}

	@GetMapping("/upload.do")
	public String viewNoticeUploadPage() {
		return "notice/RegisterNotice";

	}

	@PostMapping("/upload")
	public String uploadNotice(@Valid @ModelAttribute("noticeUploadReq") NoticeUploadReq uploadReq,
			BindingResult result, SessionStatus sessionStatus) throws Exception {
		LOGGER.info(uploadReq.toString());
		if (result.hasErrors()) {
			return "notice/RegisterNotice";
		}
		LOGGER.info("파일 개수 : " + uploadReq.getFileList().size());

		List<String> imageS3Urls = new ArrayList<>();
		String uploadPath = "notice/" + uploadReq.getCategoryId();

		for (MultipartFile mf : uploadReq.getFileList()) {
			if (CommonUtil.isNotNull(mf.getOriginalFilename())) {
				uploadReq.setFileList(null);
				break;
			}
			String s3Url = UploadFileUtils.uploadFile(uploadPath, mf.getOriginalFilename(), mf.getBytes());
			imageS3Urls.add(s3Url);
		}

		LOGGER.info(imageS3Urls.toString());
		sessionStatus.isComplete();

		return "notice/NoticeList";
	}

}
