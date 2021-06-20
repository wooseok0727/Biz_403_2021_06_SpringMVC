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
							<tr data-insert="1" >
								<td>학번</td>
								<td><input id="st_num" value="${ST.st_num}" autocomplete='off'
									readonly></td>
							</tr>
							<tr data-insert="1" >
								<td>이름</td>
								<td><input id="st_name" name="st_name" maxlength='20' autocomplete='off'
									value="${ST.st_name}"></td>
							</tr>
							<tr data-insert="1" >
								<td>전공</td>
								<td><input id="st_dept" name="st_dept" maxlength='20' autocomplete='off'
									value="${ST.st_dept}"></td>
							</tr>
							<tr data-insert="1" >
								<td>학년</td>
								<td><input id="st_grade" name="st_grade" type="number" min="1" max="3"
								autocomplete='off' value="${ST.st_grade}"></td>
							</tr>
							<tr data-insert="1" >
								<td>연락처</td>
								<td><input id="st_tel" name="st_tel" maxlength='15'
								autocomplete='off' value="${ST.st_tel}"></td>
							</tr>
							<tr data-insert="1" >
								<td>주소</td>
								<td><input name="st_addr" maxlength='125' autocomplete='off' value="${ST.st_addr}"></td>
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
						<form action="editlist/scinsert" id="scinsert" method="POST">
							<tr class="main_insert" style="display: none;">
								<td>성적추가</td>
								<td><select name="sc_subject">
										<option value="국어">국어</option>
										<option value="영어">영어</option>
										<option value="수학">수학</option>
										<option value="과학">과학</option>
								</select></td>
								<td class="insert"><input id="sc_score" type="number" min="0" max="100" name="sc_score" autocomplete='off'/>
								<button type="button" class="score_btn sc_insert" onclick="sc_insert2()">추가</button></td>
								<input type="hidden" name="sc_stnum" value="${ST.st_num}">
							</tr>
						</form>
					</table>
				</div>
				<div class="btn_container">
					<div id="btn_box">
						<button type="button" class="score_btn" onclick="sc_insert()">성적추가</button>
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
let btn = document.querySelector("#btn_box");
let btn2 = document.querySelector("#btn_box2");
let main_insert = document.querySelector(".main_insert");
let scScore = document.querySelector("input#sc_score");

function sc_insert2() {
	
	if(scScore.value === "" || scScore.value === NaN || scScore.value < 0 || scScore.value > 100) {
		alert("점수를 정확히 입력하세요");
		scScore.focus();
		return false;
	} else {
		document.querySelector("form#scinsert").submit();
	}
}

function sc_insert() {
	if(main_insert.style.display === "none") {
		  main_insert.style.display = "";
		  
	} else {
		  main_insert.style.display = "none";
	}
};

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
		if(st_grade.value === "" || st_grade.value === NaN || st_grade.value < 0 || st_grade.value > 3) {
			alert("학년을 정확히 입력하세요");
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