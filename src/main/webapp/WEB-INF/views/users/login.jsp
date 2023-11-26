<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<style>
	#login{
		margin: 160px auto;
		width: 430px;
		height: 580px;
		border: 3px solid black;
		border-radius: 10px;
	}
	#logoDiv{
		width: 310px;
		height: 85px;
		position: absolute;
		background: white;
		margin: 0 60px;
	}
	#logoDiv img{
		margin: 0 30px;
	}
	#mainDiv{
		width: 340px;
		margin: 62.5px auto;
	}
	#mainDiv>img{
		width: 200px;
		height: 200px;
		margin: 25px 70px;
	}
	#loginform input{
		height: 40px;
		margin: 7.5px auto;
	}
	#userid, #userpwd{
		width: 340px;
		margin-top: 5px;
	}
	#logIn{
		width: 350px;
	}
	#sub>li{
		float: left;
		height: 20px;
		width: 33%;
		text-align: center;
		line-height: 20px;
		margin-top: 7.5px;
	}
	#sub>li:nth-child(2), li:first-child{
		border-right: 1px solid rgb(192, 192, 192);
	}
</style>
<script>
	function loginCheck(){
		var userid = document.getElementById("userid").value;
		if(userid == ""){
			alert("아이디를 입력하세요.");
			return false;
		}
		var userpassword = document.getElementById("userpwd").value;
		if(userpassword == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
	}
	let win;
	var x;
	var y;
	function center(){
		var sW = screen.width;
		var sH = screen.height;
		var pW = win.outerWidth;
		var pH = win.outerHeight;
		
		x = sW/2 - pW/2;
		y = sH/2 - pH/2;
	}
	function open_findId(){
		win = window.open("${pageContext.request.contextPath}/users/findId", "w", "left=100px, top=200px, width=700px, height=600px");
		center();
		win.moveTo(x, y);
	}
	function open_findPw(){
		win = window.open("${pageContext.request.contextPath}/users/findPw", "w", "left=100px, top=200px, width=700px, height=600px");
		center();
		win.moveTo(x, y);
	}
</script>
	<main id="login">
		<div id="logoDiv">
			<img src="${pageContext.servletContext.contextPath}/img/page_main/logo.png" name="loginlogo" id="loginlogo"/>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/users/loginOk" id="loginform" onsubmit="return loginCheck()">
			<div id="mainDiv">
				<img src="${pageContext.servletContext.contextPath}/img/page_Login/userMan.png"/><br/>
				<input type="text" name="userid" id="userid" placeholder="아이디를 입력해주세요."/><br/>
				<input type="password" name="userpwd" id="userpwd" placeholder="비밀번호를 입력해주세요."/><br/>
				<input type="submit" class="button_com" name="logIn" id="logIn" value="로그인"/><br/>
				<ul id="sub">
					<li><a href="${pageContext.request.contextPath}/users/create_membership">회원가입</a></li>
					<li><a href="#"><span onclick="open_findId()">아이디 찾기</span></a></li>
					<li><a href="#"><span onclick="open_findPw()">비밀번호 찾기</span></a></li>
				</ul>
			</div>
		</form>
	</main>
