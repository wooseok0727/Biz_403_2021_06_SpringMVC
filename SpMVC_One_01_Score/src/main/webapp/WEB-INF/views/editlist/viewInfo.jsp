<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>

	<section class="main_sec">
		<div id="main_con">
			<div class="eContainer front">
				<div class="student1">
					<form id="st_update" method="POST">
						<table id="table1" class="container">
							<tr>
								<td>학번</td>
								<td><input name="st_num" value="${ST.st_num}" readonly></td>
							</tr>
							<tr>
								<td>이름</td>
								<td><input name="st_name" value="${ST.st_name}"></td>
							</tr>
							<tr>
								<td>전공</td>
								<td><input name="st_dept" value="${ST.st_dept}"></td>
							</tr>
							<tr>
								<td>학년</td>
								<td><input name="st_grade" value="${ST.st_grade}"></td>
							</tr>
							<tr>
								<td>연락처</td>
								<td><input name="st_tel" value="${ST.st_tel}"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input name="st_addr" value="${ST.st_addr}"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="btn_container">
					<div class="btn_box">
						<button class="score_btn">성적추가</button>
						<button class="score_btn">성적수정</button>
						<button class="score_btn">성적삭제</button>
						<button class="std_btn std_complete">수정완료</button>
						<button class="std_btn std_back">뒤로가기</button>
					</div>
				</div>
			</div>
			<div class="eContainer back">
				<div class="student1">
					<table id="table1" class="container">
						<tr>
							<td>학번</td>
							<td>${ST.st_num}</td>
						</tr>
						<tr>
							<td>이름</td>
							<td>${ST.st_name}</td>
						</tr>
						<tr>
							<td>전공</td>
							<td>${ST.st_dept}</td>
						</tr>
						<tr>
							<td>학년</td>
							<td>${ST.st_grade}</td>
						</tr>
					</table>
				</div>
				<div class="score">
					<table class="container">
						<tr>
							<th>NO.</th>
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
							<td>총합</td>
							<c:forEach items="${SC}" var="SC1">
								<c:set var="SC_COUNT" value="${SC_COUNT + 1}" />
								<c:set var="SC_SUM" value="${SC_SUM + SC1.sc_score}" />
							</c:forEach>
							<td>${SC_COUNT}</td>
							<td>${SC_SUM}</td>
						</tr>
					</table>
				</div>
				<div class="btn_container">
					<div class="btn_box">
						<button class="score_btn">성적추가</button>
						<button class="score_btn">성적수정</button>
						<button class="score_btn">성적삭제</button>
						<button class="std_btn std_edit">학생정보수정</button>
						<button class="std_btn">학생정보삭제</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script>
let main_con = document.querySelector("#main_con");
document.querySelector("button.std_edit").addEventListener("click",()=>{
	main_con.style.transform = "rotateY(-180deg)"
})
document.querySelector("button.std_back").addEventListener("click",()=>{
	main_con.style.transform = "rotateY(0)"
})


document.querySelector("button.std_complete").addEventListener("click",()=>{
	
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