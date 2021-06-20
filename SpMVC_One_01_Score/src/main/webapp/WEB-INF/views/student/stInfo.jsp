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
          <th>NO.</th>
          <th>학번</th>
          <th>이름</th>
          <th>전공</th>
          <th>학년</th>
          <th>연락처</th>
          <th>주소</th>
        </tr>
       	<c:forEach items="${ST}" var="ST" varStatus="i" >
		<tr data-num="${ST.st_num}">
			<td>${i.count}</td>
			<td>${ST.st_num}</td>
			<td>${ST.st_name}</td>
			<td>${ST.st_dept}</td>
			<td>${ST.st_grade}</td>
			<td>${ST.st_tel}</td>
			<td>${ST.st_addr}</td>
		</tr>	
		</c:forEach>
		<form action="stInfo/stinsert" id="stinsert" method="POST">
			<tr class="main_insert" data-insert="1" style="display: none;">
				<td>학생추가</td>
				<td></td>
				<td><input autocomplete="off" name="st_name"></td>
				<td><input autocomplete="off" name="st_dept"></td>
				<td><input autocomplete="off" name="st_grade"></td>
				<td><input autocomplete="off" name="st_tel"></td>
				<td class="insert"><input autocomplete="off" name="st_addr" />
			</tr>
		</form>	
      </table>
		<div class="btn_container">
			<div id="btn_box">
				<button type="button" class="score_btn st_insert1" onclick="st_insert()"
				style="display: '';'">학생추가</button>
				<button type="button" class="score_btn st_insert2"
					onclick="st_insert2()" style="display: none;">추가</button>
			</div>
		</div>
	</section>
</body>
<script>
let main_insert = document.querySelector(".main_insert");
let button1 = document.querySelector(".st_insert1");
let button2 = document.querySelector(".st_insert2");

function st_insert() {
	if(main_insert.style.display === "none") {
		  main_insert.style.display = "";
		  button1.style.display = "none";
		  button2.style.display = "";
	} else {
		  main_insert.style.display = "none";
		  button1.style.display = "";
		  button2.style.display = "none";
	}
};
function st_insert2() {
  main_insert.style.display = "none";
  document.querySelector("form#stinsert").submit();  
}
</script>
</html>