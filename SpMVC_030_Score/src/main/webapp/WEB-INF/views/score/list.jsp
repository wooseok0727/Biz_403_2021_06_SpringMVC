<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
button.score {
	background-color: rgba(0,0,0,0.8);
	color: white;
}
</style>
<table>
	<tr>
		<th>NO</th>
		<th>학번</th>
		<th>이름</th>
		<th>과목코드</th>
		<th>과목</th>
		<th>점수</th>
	</tr>
	<c:choose>
		<c:when test="${empty SCLIST}">
			<tr>
				<td colspan="6">데이터가 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${SCLIST}" var="LIST">
				<tr>
					<td>${LIST.sc_seq}</td>
					<td>${LIST.sc_stnum}</td>
					<td>${LIST.sc_stname}</td>
					<td>${LIST.sc_sbcode}</td>
					<td>${LIST.sc_sbname}</td>
					<td class="number">${LIST.sc_score}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>

<div class="btn_box">
	<button class="score insert">성적등록</button>
	<button class="score student list">학생정보</button>
</div>

