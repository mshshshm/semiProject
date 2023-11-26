<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<script src="${pageContext.servletContext.contextPath}/js/email_select.js"></script>
<style>
	.crt_mem_main {
		width: 800px;
		margin: 150px auto;
		border: 3px solid black;
		border-radius: 10px;
		overflow: auto;
	}
	
	#frm h4 {
		margin-left: 115px;
	}
	
	#frm b {
		color: red;
	}

	#frm li {
		float: left;
		height: 40px;
		line-height: 40px;
		width: 40%;
		margin:10px auto;
	}
	
	#frm li:nth-child(2n) {
		width: 60%;
	}
	
	#frm label {
		width: 120px;
		font-size: 16pt;
		margin-left: 115px;
		margin-right: 50px;
	}
	
 	#frm input {
		width: 300px;
		height: 40px;
	}
	#idCheck{
		width: 80px;
	}
	#createlogo {
		width: 160px;
		height: 35px;
		margin-top: 35px;
		margin-left: 600px;
	}
	
	#email_list {
		margin-bottom: 45px;
	}
	
	#email_list>input {
		width: 132px;
	}
	
	#frm li:last-of-type{
		width: 100%;
		text-align: center;
		margin-top: 45px;
		margin-bottom: 35px;
	}
</style>
<script>
	$(function(){
		// 아이디 중복검사
		$("#idCheck").click(function(){
			if($("#userid").val() == "") {
				alert("아이디를 입력후 중복검사하세요!");
				return false;
			}
			window.open("${pageContext.request.contextPath}/users/idCheck?userid=" + $("#userid").val(), "idCheck", "width=500px, height=300px");
		});
		$("#userid").keyup(function(){
			$("#chk").val("N");
		});
		//유효성검사 - 아이디, 비번, 이름, 연락처
		$(document).on('submit', '#frm', function(){
			
			// 아이디 첵크
			var userid = document.getElementById("userid").value;
			var reg = /^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$/;
			if(!reg.test(userid)){
				alert("아이디는 첫번째 글자는 영문자 영대소문자, 숫자, _만 허용합니다.\n문자수는 6~12자까지 허용\n다시입력해주세요.");
				return false;
			}
			if($("#chk").val()==="N"){//중복검사
				alert("아이디중복검사하세요..");
				return false;
			}
			
			// 비밀번호체크
			var password = document.getElementById("userpwd").value;
			if(password == ""){
				alert("비밀번호를 입력해주세요.");
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
				alert("비밀번호확인를 입력해주세요.");
				return false;
			}
			if(passwordchk != password){
				alert("비밀번호가 일치하지 않습니다.\n다시입력해주세요.");
				return false;
			}

			// 이름체크
			var username = document.getElementById("username").value;
			if(username == ""){
				alert("이름을 입력하세요.");
				return false;
			}
			reg = /^[가-힣]{2,7}$/;
			if(!reg.test(username)){
				alert("이름은 한글 2~7자까지 허용\n다시입력해주세요.")
				return false;
			}
			
			// 이메일 체크
			var email = document.getElementById("email1").value;
			if(email == ""){
				alert("이메일을 입력하세요.");
				return false;
			}
			var domain = document.getElementById("domain_txt").value;
			if(domain == ""){
				alert("이메일 도메인을 입력하세요.");
				return false;
			}
			var temail = email + "@" + domain;
			document.getElementById("email").setAttribute("value", temail);
			reg = /^\w{4,14}[@][a-z]{2,8}[.][a-z]{2,3}([.][a-z]{2,3})?$/;
			if(!reg.test(temail)){
				alert("이메일이 잘못입력되었습니다.\n다시입력해주세요.");
				return false;
			}
			
			// 닉네임체크
			var nickname = document.getElementById("usernickname").value;
			if(nickname == ""){
				alert("닉네임을 입력하세요.");
				return false;
			}
			reg = /^[a-zA-Z가-힣_0-9]{2,16}$/;
			if(!reg.test(nickname)){
				alert("닉네임은 영대소문자, 한글, _만 허용합니다.\n문자수는 2~16자까지 허용\n다시입력해주세요.");
				return false;
			}
			
			// 전화번호 체크
			var tel = document.getElementById("tel").value;
			if(tel == ""){
				return true;
			}
			reg = /^(010|02|031|032|033|041|043|051|052|053|054|055|061|062|063|064)[-][0-9]{3,4}[-][0-9]{4}$/;
			if(!reg.test(tel)){
				alert("전화번호가 잘못입력되었습니다.\n다시입력해주세요.");
				return false;
			}
			
			return true;				
		});
	});
</script>
<main class="crt_mem_main">
	<form method="post" action="${pageContext.request.contextPath}/users/createOk" id="frm">
			<h1>회원가입</h1>
			<h4><b>*</b> 반드시 기입</h4>
			<ul>
				<li><label>아이디 <b>*</b></label></li>
				<li>
					<input type="text" name="userid" id="userid" placeholder="아이디를 입력해주세요."/>
					<button type="button" class="button_com" id="idCheck" name="idCheck">아이디체크</button>
					<input type="hidden" name="chk" id="chk" value="N"/>
				</li>
				<li><label>비밀번호 <b>*</b></label></li>
				<li><input type="password" name="userpwd" id="userpwd" placeholder="비밀번호를 입력해주세요."/></li>
				<li><label>비밀번호 확인 <b>*</b></label></li>
				<li>
					<input type="password" name="userPwChk" id="userPwChk" placeholder="비밀번호확인을 입력해주세요."/>
				</li>
				<li><label>이름 <b>*</b></label></li>
				<li><input type="text" name="username" id="username" placeholder="이름을 입력해주세요."/></li>
				<li><label>닉네임 <b>*</b></label></li>
				<li><input type="text" name="usernickname" id="usernickname" placeholder="닉네임을 입력해주세요."/></li>	
				<li><label>전화번호</label></li>
				<li><input type="text" name="tel" id="tel" placeholder="전화번호를 입력해주세요."/></li>
				<li><label>이메일 <b>*</b></label></li>
				<li id="email_list">
					<input type="hidden" name="email" id="email"/>
					<input type="text" name="email1" id="email1" placeholder="이메일 입력"/> @
					<input type="text" name="domain" id="domain_txt" placeholder="직접입력"/>
					<select id="domain_list" onchange="selectDomain()">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="nate.com">nate.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="google.com">google.com</option>
					</select>
				</li>
				<li><input type="submit" class="button_com" value="회원가입" name="join" id="join"/></li>
			</ul>
			<a href="${pageContext.servletContext.contextPath}/">
				<img src = "${pageContext.servletContext.contextPath}/img/page_main/logo.png" name="logo" id="createlogo"/>
			</a>
		</form>
</main>