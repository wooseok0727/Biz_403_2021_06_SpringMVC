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

h1 {
 text-align: center;
}

#container {
	display: flex;
	flex-direction: column;
}
</style>
</head>
<body>
	<h1>내 갤러리</h1>
	<%@ include file="/WEB-INF/views/include/include_nav.jspf" %>
	<div id="container">
	<c:choose>
		<c:when test="${BODY eq 'G_INPUT'}">
			<%@ include file="/WEB-INF/views/gallery/input.jsp" %>	
		</c:when>
		<c:when test="${BODY eq 'G_LIST'}">
			<%@ include file="/WEB-INF/views/gallery/list.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'G_DETAIL'}">
			<%@ include file="/WEB-INF/views/gallery/detail.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'G_DETAIL2'}">
			<%@ include file="/WEB-INF/views/gallery/detail2.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'JOIN'}">
			<%@ include file="/WEB-INF/views/member/join.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'LOGIN'}">
		<%@ include file="/WEB-INF/views/member/login.jsp" %>
		</c:when>
		<c:otherwise>
			<a href="${rootPath}/gallery/input">이미지 등록</a>
		</c:otherwise>
	</c:choose>
	</div>
	<c:forEach items="${FILES}" var="FILE">
		<a href="${rootPath}/files/${FILE}" target="_NEW">
		<img src="${rootPath}/files/${FILE}" width="100px" height="100px"/>
		</a>	
	</c:forEach>
</body>
<script>
let main_nav = document.querySelector("nav#main_nav");
if(main_nav) {
	
	main_nav.addEventListener("click",(e)=>{
		let menu = e.target;
		if(menu.tagName === "LI") {
			
			if(menu.id === "join") {
				location.href = "${rootPath}/member/join";
			} else if(menu.id === "login") {
				location.href = "${rootPath}/member/login/nav";
			} else if(menu.id === "logout") {
				location.href = "${rootPath}/member/logout";
			} else if(menu.id === "image_create") {
				location.href = "${rootPath}/gallery/input";
			} else if(menu.id === "home") {
				location.href = "${rootPath}/";
			}
		}
	});
}
</script>
</html>