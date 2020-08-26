/**
 * 
 */
package com.somday.controller;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.somday.error.ExpiredTokenError;
import com.somday.req.vo.TokenReq;
import com.somday.service.SecurityService;
import com.somday.utils.CommonUtil;

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
public class AuthInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

	@Autowired
	private SecurityService securityService;

	private String HEADER_TOKEN_KEY = "authorization";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String jwt = request.getHeader(HEADER_TOKEN_KEY);
		LOGGER.info("@AuthInterceptor 진입");

		if (!CommonUtil.isNotNull(jwt)) {

			String JWT_SECRET_KEY = securityService.getJwtSecretKey();

			Date now = new Date();
			if (Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY)).parseClaimsJws(jwt)
					.getBody().getExpiration().before(now)) {
				LOGGER.info("토큰 유효기간 지남");
				throw new ExpiredTokenError("토큰 유효기간 지남");
			}

			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY))
					.parseClaimsJws(jwt).getBody();

			TokenReq token = new TokenReq((String) claims.get("id"), (String) claims.get("studentId"),
					(Integer) claims.get("majorId"));

			LOGGER.info("토큰 해독 완료");
			request.setAttribute("tokenBody", token);
			return true;
		}
		LOGGER.info("토큰 없음");
		throw new ExpiredTokenError("토큰이 없습니다.");
	}

}
