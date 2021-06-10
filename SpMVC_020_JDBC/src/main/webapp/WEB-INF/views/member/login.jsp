<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/include/include_head.jspf" %>
	<style>
	
		form#login_form {
			width: 400px;
			padding: 40px;
			margin: 100px auto;
			
			background-color: #191919;
			text-align: center;
			
			
		}
		
		form#login_form h2 {
			color:white;
			font-weight: 500;
		}
		
		form#login_form input {
		
			outline: 0;
			display: block;
			
			width: 200px;
			margin: 20px auto;
			
			padding: 14px 10px;
			color: white;
			border-radius: 25px;
			text-align:center;
			border: 2px solid green;
			
			background: none;
			border: 2px solid #3498db;
			
			transition: 0.3s;
		}
		
		form#login_form input:focus {
		width: 280px;
		border-color: #2eccc71;
	}
	
		form#login_form button:hover {
		background-color: blue;
	}
		
		form#login_form button {
			border: 0;
			outline: none;
			background: none;
			display: block;
			margin: 20px auto;
			text-align: center;
			border: 1px solid #2ecc71;
			color: white;
			border-radius: 25px;
			cursor: pointer;
		}
		
	</style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
		<form id="login_form" method="POST">
			<h2>로그인</h2>
			<input name="m_username" placeholder="사용자 ID"/>
			<input type="password" name="m_password" placeholder="비밀번호"/>
			<button>로그인</button>
		</form>
	

	<%@ include file="/WEB-INF/views/include/include_footer.jspf" %>
</body>
<script>
document.querySelector("button.btn_book_insert")
	.addEventListener("click",()=>{
		location.href="${rootPath}/books/insert"
	})
</script>
</html>