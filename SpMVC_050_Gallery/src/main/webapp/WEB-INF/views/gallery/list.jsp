<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<style>
	div.g_box {
		display: flex;
	}
	div.g_box div:first-of-type {
		flex: 1;
	}
	div.g_box div:last-of-type {
		flex: 3;
	}

</style>

<div id="g_container">
<c:forEach items="${GALLERYS}" var="GALLERY">
<div class="g_box">
	<div>
		<img src="${rootPath}/files/${GALLERY.g_image}" width="100px"/>
	</div>
	<div>
		<h3>
			<a href="${rootPath}/gallery/detail/${GALLERY.g_seq}">${GALLERY.g_subject}</a>
		</h3>
		<p>${GALLERY.g_content}</p>
	</div>
</div>
</c:forEach>
</div>