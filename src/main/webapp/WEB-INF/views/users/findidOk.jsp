<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<style>
	p{
		font-size: 1.5em;
	}
	b{
		font-size: 2em;
		color: blue;
	}
	button{
		margin: 5px;
		height: 30px;
		float: right;
	}
</style>
<script>
	$(function() {
		// header태그와 footer태그의 내용을 지우기
		$("header").html("");
		$("footer").html("").css("display", "none");		
	});
</script>
<main>
	<h1>아이디 찾기</h1>
	<p>찾으신 아이디는 <b>'${vo.userid }'</b>입니다.</p>
	<ul>
		<li>
			<button type="button" class="button_com" id="close_bn" onclick="self.close()">닫기</button>
			<button type="button" class="button_com" id="find_pw_bn" onclick="window.location.href = '${pageContext.servletContext.contextPath}/users/findPw'">비밀번호 찾기</button>
		</li>
	</ul>
</main>