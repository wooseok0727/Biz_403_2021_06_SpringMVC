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
	<h1>JSON</h1>
	<button id="json_1">JSON 문자열</button>


</body>
<script>
var rootPath = '${rootPath}'
</script>
<script src="${rootPath}/static/js/json.js?ver=2021-07-20-003"></script>
</html>