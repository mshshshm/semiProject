<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/find_common.css" type="text/css"/>
<style>
	ul{
		margin-left: 40px;
	}
</style>
<script>
	$(function(){
		// header태그와 footer태그의 내용을 지우기
		$("header").html("");
		$("footer").html("").css("display", "none");
		
	});
	function pwchk(){
		// 비밀번호체크
		var password = document.getElementById("userpwd").value;
		if(password == ""){
			alert("새 비밀번호를 입력해주세요.");
			return false;
		}
		reg = /^[a-zA-z0-9]{8,16}$/
		if(!reg.test(password)){
			alert("비밀번호는 8~16자까지 허용\n다시입력해주세요.");
			return false;
		}
		// 비밀번호 확인 체크
		var passwordchk = document.getElementById("userPwChk").value;
		if(passwordchk == ""){
			alert("새 비밀번호확인를 입력해주세요.");
			return false;
		}
		if(passwordchk != password){
			alert("비밀번호가 일치하지 않습니다.\n다시입력해주세요.");
			return false;
		}
	}
</script>
<main id="fmain">
	<h1>비밀번호 재설정</h1>
	<form method="post" action="${pageContext.servletContext.contextPath}/users/resetpwdOk" onsubmit="return pwchk()" id="findform">
		<div class="find_form">
			<input type="hidden" name="userid" id="userid" value="${vo.userid }"/>
			<ul>
				<li><label>비밀번호</label></li>
				<li><input type="password" name="userpwd" id="userpwd" placeholder="새 비밀번호를 입력해주세요."/></li>
				<li><label>비밀번호 확인</label></li>
				<li><input type="password" name="userPwChk" id="userPwChk" placeholder="새 비밀번호 확인를 입력해주세요."/></li>
			</ul>	
		</div>
		<div id="search">
			<input type="submit" class="button_com" name="find_bn" id="find_bn" value="재설정"/>	
		</div>
	</form>
</main>