/**
 * 
 */
package com.somday.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * @Since : Aug 26, 2020 
 * @Author kimhyunjin
 * <pre>
 * -----------------
 * 개정이력
 * Aug 26, 2020 kimhyunjin : 최초작성 
 * </pre>
 *
 */
@PropertySource("classpath:config/application.properties")
public class S3Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(S3Util.class);

	private static final String ACCESS_KEY = PropertyUtil.getProperty("aws.credentials.access.key"); 
	private static final String SECRET_KEY = PropertyUtil.getProperty("aws.credentials.secret.key");; 
	private static final String S3_REGION = PropertyUtil.getProperty("aws.s3.bucket.region");;
	private static final String S3_BUCKET = PropertyUtil.getProperty("aws.s3.bucket");;

	private AmazonS3 conn;

	public S3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

		conn = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(S3_REGION)
                .build();
	}

	// 버킷 리스트를 가져오는 메서드이다.
//	public List<Bucket> getBucketList() {
//		return conn.listBuckets();
//	}

	// 버킷을 생성하는 메서드이다.
//	public Bucket createBucket(String bucketName) {
//		return conn.createBucket(bucketName);
//	}

	// 폴더 생성 (폴더는 파일명 뒤에 "/"를 붙여야한다.)
	public void createFolder(String folderName) {
		conn.putObject(S3_BUCKET, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}

	// 파일 업로드
	public void fileUpload(String fileName, byte[] fileData) throws FileNotFoundException {

		String filePath = (fileName).replace(File.separatorChar, '/'); // 파일 구별자를 `/`로 설정(\->/) 이게 기존에 / 였어도 넘어오면서 \로 바뀌는 거같다.
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(fileData.length);   //메타데이터 설정 -->원래는 128kB까지 업로드 가능했으나 파일크기만큼 버퍼를 설정시켰다.
	    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); //파일 넣음

		conn.putObject(S3_BUCKET, filePath, byteArrayInputStream, metaData);
	}

	// 파일 삭제
	public void fileDelete(String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(S3_BUCKET, imgName);
		LOGGER.info("삭제성공");
	}

	// 파일 URL
	public String getFileURL(String fileName) {
		LOGGER.info("넘어오는 파일명 : "+fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(S3_BUCKET, imgName)).toString();
	}

}