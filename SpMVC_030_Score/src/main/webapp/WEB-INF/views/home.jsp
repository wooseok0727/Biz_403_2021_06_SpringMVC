<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>my</title>
</head>
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
	flex-direction: column;
	overflow: hidden;
}
header {
	background: url("${rootPath}/static/images/header_background.jpg") no-repeat;
	background-size: 100% 100%;
	background-position: top;
	background-attachment: fixed;
	 
	text-align: center;
	text-shadow: 1px 1px 1px black;
	color: white;
	padding: 2rem;'
}

section#main_sec {
	flex:1;
	width: 100vw;
	display: flex;
	flex-direction: column;
	/* background: linear-gradient(to bottom, blue, red); */
	background: url("${rootPath}/static/images/header_background.jpg") no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
}

h2 {
	width: 80%;
	color: white;
	margin: 10px auto 0 auto;
	padding: 1rem;
	border: 1px solid #aaa;
	text-align: center;
	
}

table {
	border: 0;
	width: 80%;
	border-collapse: collapse;
	border-spacing: 0;
	margin: 10px auto;
}

tr {
	border-top: 1px solid ;
}

tr:last-child {
	border-bottom: 1px solid;
}

tr:nth-of-type(odd) {
	background-color: rgba(204,204,204,0.7);
}

tr:nth-of-type(even) {
	background-color: rgba(238,238,238,0.7);
}

tr:nth-of-type(n + 2) {
	border: 1px solid black;
}

tr:hover td{
	background-color: rgba(170,170,170,0.7);
	cursor: pointer;
}

td,th {
	border-right: 1px solid;
	padding: 8px 12px;
	text-align: center;
	border: 1px solid black;
}

td.number {
	text-align: right;
}

td:last-child, th:last-child {
	border: none;
}

div.btn_box {
	width:80%;
	/* 
		table의 margin: 10px auto로 설정되어 있기 때문에
		top margin 은 0으로 bottom margin은 10px 좌우는 auto
	*/
	margin: 0px auto 10px auto;
	padding: 5px;
	text-align: right;
}

div.btn_box button {
	border: 0;
	outline: 0;
	padding: 12px 16px;
	margin-left: 10px;
	border-radius: 5px;
}

button:hover {
	box-shadow: 2px 2px 2px 2px black; 
	cursor: pointer;
}

form {
	width: 50%;
	margin: 0 auto 10px auto;
}

fieldset {
	background-color: rgba(238,238,238,0.7);
	border: 1px solid white;
	border-radius: 10px;
	padding: 0.7rem;
}

form label, form input {
	display: inline-block;
	margin: 5px;
	padding: 8px 16px;
}

form label {
	width: 30%;
	text-align: right;
	color: black;
	font-weight: bold;
}

form input {
	width: 60%;
	outline: 0;
	border: rgba(170,170,170,0.5);
	border-radius: 50px;
	background-color: rgba(255,255,255,0.5);
}

form input:hover {
	background: #bbb;
}

form button {
	background-color: black;
	color: white;
}
</style>
<body>
	<!-- header -->
	<header>
		<h1>성적처리</h1>
		<p>성적처리 시스템 2021</p>
	</header>
	
	<!-- section -->
	<section id="main_sec">
		<c:choose>
			<c:when test="${BODY == 'SCORE_VIEW'}">
				<%@ include file="/WEB-INF/views/score/list.jsp"%>
			</c:when>
			<c:when test="${BODY == 'STUDENT_LIST'}">
				<%@ include file= "/WEB-INF/views/student/list.jsp" %>
			</c:when>
			<c:when test="${BODY == 'STUDENT_INPUT'}">
				<%@ include file= "/WEB-INF/views/student/input.jsp" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/main.jsp"%>
			</c:otherwise>
		</c:choose>
	</section>
</body>
<script>
/*
 	JS 코드에서 event를 등록할 때 현재 화면에 없는 DOM 요소애
 	addEvent를 설정하면 없는 함수라는 오류가 발생한다
 	그 이유는 현재 화면에 없는 DOM 요소를 querySelector하면
 	그 결과값이 null 이기 때문에 발생하는 문제이다
 	
 	JS 코드를 통합하여 모음으로 관리할때는
 	addEvent를 하려고 하는 요소가 있는지를 먼저 검사한 후
 	addEvent를 수행해 주어야 한다
 */
let std_list = document.querySelector("button.student.list"); 
let std_insert = document.querySelector("button.student.insert");
let home = document.querySelector("button.student.home"); 

// std_list가 있으면
if(std_list) {
	std_list.addEventListener("click",(e)=>{
		location.href = "${rootPath}/student"
	});
}
if(std_insert) {
	std_insert.addEventListener("click",(e)=>{
		location.href = "${rootPath}/student/insert"
	});
}
if(home) {
	home.addEventListener("click",(e)=>{
		location.href = "${rootPath}/"
	});	
}
</script>
</html>