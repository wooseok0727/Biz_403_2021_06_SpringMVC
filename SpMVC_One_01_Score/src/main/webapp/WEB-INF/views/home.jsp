<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/include/include_head.jspf" %>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
    <section class="main_sec list">
      <table class="container">
        <tr>
          <th>학번</th>
          <th>이름</th>
          <th>전공</th>
          <th>학년</th>
          <th>응시과목</th>
          <th>총점</th>
          <th>평균</th>
        </tr>
       	<c:forEach items="${LIST}" var="LIST" >
		<tr data-num="${LIST.st_num}">
			<td>${LIST.st_num}</td>
			<td>${LIST.st_name}</td>
			<td>${LIST.st_dept}</td>
			<td>${LIST.st_grade}</td>
			<td>${LIST.sc_subject}</td>
			<td>${LIST.sc_scores}</td>
			<td>${LIST.sc_avg}</td>
		</tr>	
		</c:forEach>	
      </table>
    </section>
</body>
</html>