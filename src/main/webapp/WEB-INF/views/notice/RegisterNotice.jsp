<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성하기</title>
</head>
<body>
	<spring:hasBindErrors name="noticeUploadReq" />
	<form:form modelAttribute="noticeUploadReq" action="/Somday/web/notice/upload" method="post" enctype="multipart/form-data">
		
		<form:errors path="categoryId" />
		<form:select path="categoryId" multiple="false">
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</form:select>
		
		
		<label for="title">제목</label><form:errors  path="title" />
		<form:input path="title" name="title" /><br/>
		
		<label for="content">내용</label><form:errors path="content" />
		<form:textarea rows="6"  path="content" name="content"/><br/>
		
        <form:input type="file" multiple="multiple" path="fileList" name="file"  accept="image/gif, image/jpeg, image/png, image/jpg" />
        <form:input path="src" name="src" />
        
        <button type="submit">등록</button>
    </form:form>
</body>
</html>