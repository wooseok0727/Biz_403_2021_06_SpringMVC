<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section class="main_sec list">
		<table class="container">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>과학</th>
				<th>총점</th>
				<th>평균</th>
			</tr>
			<c:forEach items="${SC}" var="SC">
				<tr data-num="${SC.st_num}">
					<td>${SC.st_num}</td>
					<td>${SC.st_name}</td>
					<td>${SC.sc_kor}</td>
					<td>${SC.sc_eng}</td>
					<td>${SC.sc_math}</td>
					<td>${SC.sc_sci}</td>
					<td>${SC.sc_scores}</td>
					<td>${SC.sc_avg}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>