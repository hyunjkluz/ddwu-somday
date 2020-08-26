/**
 * 
 */
package com.somday.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
 
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import com.somday.req.vo.TokenReq;
import com.somday.utils.PropertyUtil;

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
public class SecurityService {
	private String iv;
	private Key keySpec;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);
	
	private static final String JWT_SECRET_KEY = PropertyUtil.getProperty("somday.jwt.secret.key");
	
	private static final String SECRET_KEY = PropertyUtil.getProperty("somday.secret.key");
	
	private static final String CYPER_INSTANCE = PropertyUtil.getProperty("cyper.instance");
	
	/**
	 * * 16자리의 키값을 입력하여 객체를 생성한다. *  
	 * @param key * 암/복호화를 위한 키값 * 
	 * @throws UnsupportedEncodingException * 키값의 길이가 16이하일 경우 발생
	 */
	public SecurityService() throws UnsupportedEncodingException {
		this.iv = SECRET_KEY.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = SECRET_KEY.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		this.keySpec = keySpec;
	}

	/**
	 * * AES256 으로 암호화 한다. 
	 * @param str * 암호화할 문자열 
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException 
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String str)
			throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance(CYPER_INSTANCE);
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		return enStr;
	}

	/**
	 * * AES256으로 암호화된 txt 를 복호화한다. 
	 * @param str * 복호화할 문자열 
	 * @throws NoSuchAlgorithmException 
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String decrypt(String str)
			throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance(CYPER_INSTANCE);
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		return new String(c.doFinal(byteStr), "UTF-8");
	}

	/**
	 * Create Random Six digits 100000 (포함) 999999 (포함) 사이의 임의 정수를 반환
	 */
	public Integer sixDigitCode() {
		return (int) (Math.floor(Math.random() * 900000) + 100000);
	}
	
	/**
	 * Create Unique Id
	 */
	public String uniqId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * Create JWT
	 */
	public String createToken(TokenReq info) {
		// 토큰을 서명하기 위해 사용해야할 알고리즘 선택
        SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;
 
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
        		.setIssuedAt(Date.from(Instant.now()))
        		.setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)))
                .setSubject("SOMDAY")
                .claim("id", info.getId())
                .claim("majorId", info.getMajorId())
                .claim("studentId", info.getEncryptedStudentId())
                .signWith(signatureAlgorithm, signingKey);
        
        return builder.compact();
	}
	
	/**
	 * Decrypt JWT
	 */
	public TokenReq decryptToken(String token) {
		try {
	        Claims claims = Jwts.parser()
	                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY))
	                .parseClaimsJws(token).getBody();
	        
	        return new TokenReq((String)claims.get("id"), (String)claims.get("studentId"), (Integer)claims.get("majorId"));
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return null;
		}
    }
	
	public String getJwtSecretKey() {
		return JWT_SECRET_KEY;
	}
}
