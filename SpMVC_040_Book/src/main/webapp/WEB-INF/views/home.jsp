<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>My Homepage</title>
<style>
* {
	box-sizing:border-box;
	margin: 0;
	padding: 0;
}

p b {
	color: blue;
}

nav#main_nav {
	background-color: rgba(0,255,0,0.7);
	display: flex;
	justify-content: center;
	align-items: center;
}

nav#main_nav form {
	margin: 0.6rem;
	width: 50%;
}

nav#main_nav input {
	padding: 8px;
	border: 0;
	outline: 0;
	width: 100%;
	border-radius: 10px;
}

section.content_box {
	border: 1px solid blue;
	padding: 12px 16px;
	display: flex;
	flex-wrap: wrap;
}

section.content_box div.content {
	border: 1px solid blue;
	margin: 5px auto;
	display: flex;
	width: 30%;
	height: 30vh;
	overflow: auto;
}

section.content_box div.content img {
	flex: 1;
	width: 30%;
	height: 50%;
}

section.content_box div.content div {
	flex: 2;
	margin: 2px;
}

@media (min-width:1200px) {
	section.content_box div.content {
		width: 20%;
	}
}

@media (max-width:700px) {
	section.content_box div.content {
		width: 95%;
	}
}

a {
	text-decoration: none;
}

a:hover {
	color: green;
}

</style>
</head>
<body>
	
	<nav id="main_nav">
		<form>
			<input name="search" placeholder="도서명을 입력 후 Enter...">
		</form>	
	</nav>
	
	
	<section class="content_box">
		<c:forEach items="${BOOKS}" var="BOOK">
		<div class="content">
			<img src="${BOOK.image}">
			<div>
				<p class="title"><a href="${BOOK.link}" target="_NEW"> ${BOOK.title}</a></p>
				<p class="desc"><strong>소개 : </strong>${BOOK.description}</p>
				<p class="author"><strong>저자 : </strong>${BOOK.author}</p>
				<p class="publisher"><strong>출판사 : </strong>${BOOK.publisher}</p>
				<button class="insert">내 서재등록</button>
			</div>
		</div>
		</c:forEach>
	</section>
</body>
</html>