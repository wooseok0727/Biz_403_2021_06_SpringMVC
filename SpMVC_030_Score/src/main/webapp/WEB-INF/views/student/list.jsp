<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
button.student {
	background-color: rgba(0,0,0,0.8);
	color: white;
}
</style>

	<h2>학생정보 리스트</h2>
	<table>
	<tr>
		<th>학번</th>
		<th>이름</th>
		<th>학과</th>
		<th>학년</th>
		<th>연락처</th>
		<th>주소</th>
	</tr>
	<c:choose>
		<c:when test="${empty STLIST}">
			<tr>
				<td colspan="6">데이터가 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${STLIST}" var="LIST">
				<tr data-stnum="${LIST.st_num}">
					<td>${LIST.st_num}</td>
					<td>${LIST.st_name}</td>
					<td>${LIST.st_dept}</td>
					<td>${LIST.st_grade}</td>
					<td>${LIST.st_tel}</td>
					<td>${LIST.st_addr}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div class="btn_box">
	<button class="student insert">학생등록</button>
	<button class="student home">처음으로</button>
</div>