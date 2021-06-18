<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/include/include_head.jspf" %>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
	
		<section class="view_sec2">
		<c:if test="${not empty FLAG}">
		<article>
		<table>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전공</th>
				<th>학년</th>
			</tr>
			<tr>
				<td>${ST.st_num}</td>
				<td>${ST.st_name}</td>
				<td>${ST.st_dept}</td>
				<td>${ST.st_grade}</td>
			</tr>
		</table>
		</article>
		<article>
		<table>
			<tr>
				<th>No.</th>
				<th>과목명</th>
				<th>점수</th>
			</tr>
			
			<c:forEach items="${SC}" var="SC" varStatus="i">
			<tr data-seq="${SC.sc_seq}">
				<td>${i.count}</td>
				<td>${SC.sc_subject}</td>
				<td>${SC.sc_score}</td>
			</tr>
			</c:forEach>	
			<tr>
				<td>총점</td>
				<c:forEach items="${SC}" var="SC1" >
				<c:set var="SC_COUNT" value="${SC_COUNT + 1}" />
				<c:set var="SC_SUM" value="${SC_SUM + SC1.sc_score}" />
				</c:forEach>
				<td>${SC_COUNT}</td>
				<td>${SC_SUM}</td>
			</tr>
		</table>
		<div class="btn_box">
		<button class="sc_update">성적추가</button>
			<button class="st_update">학생정보 수정</button>
			<button class="st_delete">학생정보 삭제</button>
		</div>
		</article>
		</c:if>
		<c:if test="${empty FLAG}">
		<article>
		<form id="st_update" method="POST">
		<table>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전공</th>
				<th>학년</th>
				<th>연락처</th>
				<th>주소</th>
			</tr>
			<tr class="input">
				<td>${ST.st_num}</td>
				<td><input id="stName" value="${ST.st_name}" /></td>
				<td><input id="stDept" value="${ST.st_dept}" /></td>
				<td><input id="stGrade" value="${ST.st_grade}" /></td>
				<td><input id="stTel" value="${ST.st_tel}" /></td>
				<td><input value="${ST.st_addr}" /></td>
			</tr>
		</table>
		<div class="btn_box">
			<button class="st_update1" type="button">수정하기</button>
		</div>
		</form>
		</article>
		</c:if>
	</section>
</body>
<script>

let st_update = document.querySelector("button.st_update");
if(st_update) {
	st_update.addEventListener("click",()=>{
		location.href="${rootPath}/editlist/edit?st_num=" + ${ST.st_num};
	})
};
document.querySelector("button.st_update1").addEventListener("click",()=>{
	
	let stName = document.querySelector("input#stName");
	let stDept = document.querySelector("input#stDept");
	let stGrade = document.querySelector("input#stGrade");
	let stTel = document.querySelector("input#stTel");
	
	if(stName.value === "") {
		alert("이름을 입력하세요");
		stName.focus();
		return false;
	}
	if(stDept.value === "") {
		alert("전공을 입력하세요");
		stDept.focus();
		return false;
	}
	if(stGrade.value === "") {
		alert("학년을 입력하세요");
		stGrade.focus();
		return false;
	}
	if(stTel.value === "") {
		alert("연락처를 입력하세요");
		stTel.focus();
		return false;
	}
	if(confirm("수정할까요")) {
		document.querySelector("form#st_update").submit();
	}
})	
</script>
</html>