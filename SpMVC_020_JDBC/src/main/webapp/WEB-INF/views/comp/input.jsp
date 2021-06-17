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
	<form method="POST">
		<fieldset>
			<legend>출판사 정보</legend>
			<div>
				<label>출판사명</label><input name="cp_title" id="cp_title"
					placeholder="">
			</div>
			<div>
				<label>대표자명</label><input name="cp_ceo" id="cp_ceo" placeholder="">
			</div>
			<div>
				<label>전화번호</label><input name="cp_tel" id="cp_tel" placeholder="">
			</div>
			<div>
				<label>주소</label><input name="cp_addr" id="cp_addr" placeholder="">
			</div>
			<div>
				<label>주요장르</label><input name="cp_genre" id="cp_genre"
					placeholder="">
			</div>
		</fieldset>
	</form>
	<div>
		<div class="btn_box">
			<button type="button" class="btn_save comp">저장</button>
			<button type="reset" class="btn_reset comp">다시작성</button>
			<button type="button" class="btn_list comp">리스트로</button>
		</div>
	</div>
	</section>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
</body>
<script>
document.querySelector("button.btn_save.comp")
	.addEventListener("click",()=>{
		location.href="${rootPath}/comp/insert"
	})
</script>
</html>