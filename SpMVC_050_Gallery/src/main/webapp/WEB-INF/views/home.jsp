<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	width: 100vw;
	height: 100vh;
	display: flex;
	flex-direction:column;
	justify-content: center;
	align-items: center;
}

#g_container {
	margin: 30px auto;
	display: flex;
}

</style>
</head>
<body>
	<h1>내 갤러리</h1>
	
	<c:choose>
		<c:when test="${BODY eq 'G_INPUT'}">
			<%@ include file="/WEB-INF/views/gallery/input.jsp" %>	
		</c:when>
		<c:when test="${BODY eq 'G_LIST'}">
			<%@ include file="/WEB-INF/views/gallery/list.jsp" %>
			<a href="${rootPath}/gallery/input">이미지 등록</a>
		</c:when>
		<c:when test="${BODY eq 'G_DETAIL'}">
			<%@ include file="/WEB-INF/views/gallery/detail.jsp" %>
			<a href="${rootPath}/gallery">리스트로</a>
		</c:when>
		<c:otherwise>
			<a href="${rootPath}/gallery/input">이미지 등록</a>
		</c:otherwise>
	</c:choose>
	<c:forEach items="${FILES}" var="FILE">
		<a href="${rootPath}/files/${FILE}" target="_NEW">
		<img src="${rootPath}/files/${FILE}" width="100px" height="100px"/>
		</a>	
	</c:forEach>
</body>
</html>