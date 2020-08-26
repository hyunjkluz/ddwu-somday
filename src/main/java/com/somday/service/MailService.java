/**
 * 
 */
package com.somday.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.somday.repository.mapper.MailAuthMapper;
import com.somday.utils.PropertyUtil;
import com.somday.vo.MailAuthVO;

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
@PropertySource("classpath:config/application.properties")
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailAuthMapper mailAuthMapper;

	public boolean send(String title, String content, String to, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			
			messageHelper.setFrom(PropertyUtil.getProperty("somday.official.email")); 
			messageHelper.setTo(to); 
			messageHelper.setSubject(title); 
			messageHelper.setText(content, true);
			
			// 파일 첨부시 
//			FileSystemResource fsr = new FileSystemResource();
//		    messageHelper.addAttachment(filename, fsr);

			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Integer registerAuthCode(String studentId, Integer code) {
		return mailAuthMapper.insertCode(studentId, code);
	}
	
	public String chechAuthCode(String studentId, Integer code) throws ParseException {
		MailAuthVO studentMailAuth = mailAuthMapper.selectCodeByStudentId(studentId);
		
		if (studentMailAuth == null) {
			return "NO_AUTH";
		}
		
		boolean isExpired = studentMailAuth.isExpried();
		
		if (!isExpired) {
			return "EXPIRED";
		}
		
		if (studentMailAuth.getCode().equals(code)) {
			return "OK";
		}

		return "NOT_MATCH";
	}
}
