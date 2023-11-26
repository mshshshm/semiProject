<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/find_common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<script src="${pageContext.servletContext.contextPath}/js/email_select.js"></script>
<script>
	$(function(){
		// header태그와 footer태그의 내용을 지우기
		$("header").html("");
		$("footer").html("").css("display", "none");
		
		$(document).on('submit', '#findform',function(){
			// 이메일 체크
			var email = document.getElementById("email1").value;
			var domain = document.getElementById("domain_txt").value;
			var temail = email + "@" + domain;
			document.getElementById("email").setAttribute("value", temail);
			reg = /^\w{4,14}[@][a-z]{2,8}[.][a-z]{2,3}([.][a-z]{2,3})?$/;
			if(!reg.test(temail)){
				alert("이메일이 잘못입력되었습니다.\n다시입력해주세요.");
				return false;
			}
			// 이름체크
			if($("#username").val() == "") {
				alert("이름을 입력하세요.");
				return false;
			}
			
			if($("#email1").val() == "") {
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			if($("#domain").val() == "") {
				alert("이메일의 도메인을 입력해주세요.");
				return false;
			}
			return true;
		});
	});
	
</script>
<main id="fmain">
	<h1>아이디 찾기</h1>
	<form method="get" action="${pageContext.servletContext.contextPath}/users/findidOk" id="findform">
		<div class="find_form">
			<ul>
				<li><label>이름</label></li>
				<li><input type="text" name="username" id="username" placeholder="이름을 입력해주세요."/></li>
				<li><label>이메일</label></li>
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
			</ul>	
		</div>
		<div id="search">
			<input type="submit" class="button_com" name="find_bn" id="find_bn" value="찾기"/>			
		</div>
	</form>
</main>
