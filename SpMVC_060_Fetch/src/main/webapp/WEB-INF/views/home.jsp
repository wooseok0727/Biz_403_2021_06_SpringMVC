<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
</head>
<body>
	<p><a href="${rootPath}/json">JSON 바로가기</a></p>
	<p><a href="${rootPath}/form">Form 바로가기</a></p>
</body>
</html>