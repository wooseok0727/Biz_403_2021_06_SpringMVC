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
								<td><input id="st_num" value="${ST.st_num}"
									readonly></td>
							</tr>
							<tr>
								<td>이름</td>
								<td><input id="st_name" name="st_name"
									value="${ST.st_name}"></td>
							</tr>
							<tr>
								<td>전공</td>
								<td><input id="st_dept" name="st_dept"
									value="${ST.st_dept}"></td>
							</tr>
							<tr>
								<td>학년</td>
								<td><input id="st_grade" name="st_grade"
									value="${ST.st_grade}"></td>
							</tr>
							<tr>
								<td>연락처</td>
								<td><input id="st_tel" name="st_tel" value="${ST.st_tel}"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input name="st_addr" value="${ST.st_addr}"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="btn_container">
					<div id="btn_box2">
						<button class="score_btn">성적추가</button>
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
				<div class="score list">
					<table class="container">
						<tr>
							<th>NO.</th>
							<th>과목명</th>
							<th>점수</th>
						</tr>
						<c:forEach items="${SC}" var="SC" varStatus="i">
						<form action="editlist/scdelete" id="scdelete${i.count}" method="post">
							<tr>
								<input name="sc_seq" value="${SC.sc_seq}" type="hidden">
								<input name="st_num" value="${ST.st_num}" type="hidden">
								<td>${i.count}</td>
								<td>${SC.sc_subject}</td>
								<td class="delete">${SC.sc_score}<button type="button" class="score_btn sc_delete" onclick="scdelete(scdelete${i.count})">삭제</button></td>
							</tr>
						</form>
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
					<div id="btn_box">
						<button type="button" class="score_btn">성적추가</button>
						<button type="button" class="std_btn std_edit">학생정보수정</button>
						<button type="button" class="std_btn">학생정보삭제</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script>
let main_con = document.querySelector("#main_con");
let stName = document.querySelector("input#st_name");
let stDept = document.querySelector("input#st_dept");
let stGrade = document.querySelector("input#st_grade");
let stTel = document.querySelector("input#st_tel");
let btn = document.querySelector("#btn_box");
let btn2 = document.querySelector("#btn_box2");

function scdelete(scseq) {
	if(confirm("삭제할까요")) {
		"#" + scseq.submit();
	}
}

btn.addEventListener("click",(e)=>{
	let tagName = e.target.tagName;
	if(tagName === "BUTTON") {
		let buttonText = e.target.textContent;
		if(buttonText === "학생정보수정") {
			main_con.style.transform = "rotateY(-180deg)"
		} else if(buttonText === "학생정보삭제") {
			if(${not empty SC}) {
				alert("성적정보가 있는 학생은 삭제할 수 없습니다")
				return false;
			} else if(confirm("${ST.st_name} 학생을 " + "삭제하시겠습니까?")) {
				document.location.replace("${rootPath}/editlist/stdelete?st_num=" + ${ST.st_num})
			}
		}
	}
});

btn2.addEventListener("click",(e)=>{	
	let tagName = e.target.tagName;
	if(tagName === "BUTTON") {
		let buttonText = e.target.textContent;
	if(buttonText === "뒤로가기") {
		main_con.style.transform = "rotateY(0)"
	} else if(buttonText === "수정완료") {
		
		if(st_name.value === "") {
			alert("이름을 입력하세요");
			st_name.focus();
			return false;
		}
		if(st_dept.value === "") {
			alert("전공을 입력하세요");
			st_dept.focus();
			return false;
		}
		if(st_grade.value === "") {
			alert("학년을 입력하세요");
			st_grade.focus();
			return false;
		}
		if(st_tel.value === "") {
			alert("연락처를 입력하세요");
			st_tel.focus();
			return false;
		}
		if(confirm("수정할까요")) {
			document.querySelector("form#st_update").submit();
		}
	  }
	}
	});	
</script>
</html>