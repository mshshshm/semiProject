<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<style>
   	* {
	      margin: 0;
	      padding: 0;
	      box-sizing: border-box;
	}
	#newPwDiv h1{
		margin-bottom: 50px;
	}
	#newPwDiv{
		margin: 300px auto;
		width: 700px;
		border: 3px solid black;
		border-radius: 10px;
		overflow: auto;
	}
	#newPwDiv li{
		float: left;
		height: 40px;
		line-height: 40px;
		width: 40%;
		margin:10px auto;
	}
	#newPwDiv li:nth-child(2n){
		width: 60%;
	}
	#newPwDiv li:last-of-type{
		width: 100%;
		text-align: center;
		margin-top: 45px;
		margin-bottom: 35px;
	}
	#newPwDiv label{
		width: 120px;
		font-size: 16pt;
		margin-left: 50px;
		margin-right: 50px;
	}
	#newPwDiv input{
		height: 40px;
		margin: 7.5px auto;
	}
	#userpwd, #newpwd, #userPwChk{
		width: 340px;
		margin-top: 5px;
	}
	#reset_bn{
		width: 300px;
	}
</style>
<script>
	$(function(){
		$("header").html("");
		$("footer").html("").css("display", "none");
	});
	function pwchk(){
		var nowpassword = document.getElementById("usernowPW").value;
		if(nowpassword == ""){
			alert("현재비밀번호를 입력하세요");
			return false;
		}
		if(nowpassword == "qwer1234"){
			alert("현재비밀번호가 틀립니다.");
			return false;
		}
		// 새로운 비밀번호
		var password = document.getElementById("usernewPW").value;
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
<main>
	<div id="newPwDiv">
		<form method="post" action="${pageContext.servletContext.contextPath}/mypage/editpwdOk" id="frm" onsubmit="return pwchk()">
			<h1>비밀번호 재설정</h1>
			<ul>
				<li><label>현재 비밀번호</label></li>
				<li><input type="password" name="userpwd" id="userpwd" placeholder="현재 비밀번호를 입력하세요."/></li>
				<li><label>새 비밀번호</label></li>
				<li><input type="password" name="newpwd" id="newpwd" placeholder="새 비밀번호를 입력하세요."/></li>
				<li><label>새 비밀번호 확인</label></li>
				<li><input type="password" name="userPwChk" id="userPwChk" placeholder="새 비밀번호 확인을 입력하세요."/></li>
				<li><input type="submit" class="button_com" name="reset_bn" id="reset_bn" value="재설정"/></li>
			</ul>
		</form>
	</div>
</main>