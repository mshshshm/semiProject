<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/form_common.css" type="text/css"/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>

   	* {
	      margin: 0;
	      padding: 0;
	      box-sizing: border-box;
	}
    
	.edit_profile{
		width: 680px;
		margin: 20px auto;
		border: 3px solid black;
		border-radius: 10px;
		overflow: auto;
	}
	#userimg{
		width: 120px;
		height: 120px;
		overflow: hidden;
		border-radius: 70%;
	}
	#nickname{
		width: 120px;
		height: 30px;
		text-align: center;
		font-size: 20pt;
	}
	.edit_profile input, label{
		margin-left: 185px;
	}
	.edit_profile input{
		width: 300px;
		height: 40px;
		border: 1px solid;
		border-radius: 5px;
		margin-top: 10px;
		margin-bottom: 20px;
	}
	.edit_profile label{
		width: 300px;
		font-size: 16pt;
	}
	#save, #cancel{
		width: 100px;
		height: 30px;
	}
	#nickname, #userimg{
		margin: 10px 275px;
	}
	#userpwd{
		float: left;
		margin-bottom: 0px;
	}
	#viewpw{
		width: 45px;
		height: 30px;
		margin-top: 15px;
		margin-bottom: 5px;
	}
	#editpw{
		width: 100px;
		height: 25px;
		margin-top: 5px;
		margin-bottom: 10px;
		margin-left: 390px;
	}
	#save{
		float: left;
		margin-left: 185px;
	}
	#cancel{
		margin-left: 105px;
	}
	.edit_profile li:last-of-type{
		float: right;
		width: 100px;
		height: 30px;
		margin-top: 15px;
		margin-bottom: 15px;
		margin-right: 185px;
	}
	#search, #zipcode{
		float: left;
	}
	#zipcode{
		width: 180px;
	}
	#search{
		width: 100px;
		margin-left: 20px;
	}
	#addr, #search, #zipcode{
		margin-bottom: 5px;
	}
	#accountdelModal {
		display: none;
		text-align: center;
		position: absolute;
		margin: 0px 300px;
		width: 680px;
		height: 100%;
		z-index: 1;
	}
	#accountdelModal h2 {
		text-align: center;
	}
	#accountdelModal button {
		float: left;
		display:inline-block;
		width:100px;
		margin: 10px 25px;
	}

	#accountdelModal .modal_body {
		width:300px;
		overflow: auto;
		margin:100px auto;
		padding:0;
		background:#fff;
		border:2px solid #666;
		text-align: center;
	}

	#accountdelModal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	#accountDel {
		width: 100px;
		border: none;
		background-color: white;
		color: black;
		font-size: 1.1em;
	}
</style>
<script>
	$(function() {
	    $(document).on('click', '#viewpw', function(){
	        $('#userpwd').toggleClass('active');
	        if($('#userpwd').hasClass('active')){
	            $('#userpwd').attr('type',"text");
	            $('#viewpw').attr('src',"${pageContext.servletContext.contextPath}/img/page_mypage/open.png")
	        }else{
	            $('#userpwd').attr('type','password');
	            $('#viewpw').attr('src',"${pageContext.servletContext.contextPath}/img/page_mypage/close.png")
	        }
	    });
	    
		// 우편번호 찾기
		$(document).on('click', '#search',function() {
			new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipcode').value = data.zonecode;
	                document.getElementById("addr").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("addrdetail").focus();
	            }
			}).open();
		});
		$(document).on('click', '#deleteAccount', function() {
			location.href = "${pageContext.servletContext.contextPath}/mypage/delete?userid=${vo.userid}";
		});
		$(document).on('click', '#accountDel', function(){
			window.scrollTo({top: 0, behavior: "smooth"});
			$("#accountdelModal").attr("style", "display:block");
		});
		$("#close_btn").click(function(){
			$("#accountdelModal").attr("style", "display:none");
		});
		$(document).on('click', '#editpw', function(){
			location.href = "${pageContext.servletContext.contextPath}/mypage/editpwd";
		});
	});
</script>
<main>
	<div id="accountdelModal">
		<div class="modal_body">
			<h2>회원탈퇴</h2>
			<b>정말로 회원탈퇴를 하시겠습니까?</b>
			<button type="button" id="deleteAccount">확인</button>
			<button type="button" id="close_btn">취소</button>
		</div>
		<div class="modal_layer"></div>
	</div>
	<form method="post" action="${pageContext.servletContext.contextPath}/mypage/mypageEditOk" class="edit_profile" id="frm">
		<ul>
			<li><h1>회원정보 수정</h1></li>
			<li><img src="${pageContext.servletContext.contextPath}/img/page_mypage/userMan.png" name="userimg" id="userimg"/></li>
			<li><input type="text" value="${vo.usernickname}" name="usernickname" id="nickname"/></li>
			<li><label>아이디</label></li>
			<li>
				<input type="hidden" name="userid" id="userid" value="${vo.userid }" />
				<input type="text" name="useridview" id="useridview" value="${vo.userid }" disabled />
			</li>
			<li><label>비밀번호</label></li>
			<li id="pw"><input type="password" name="userpwd" id="userpwd" value="${vo.userpwd }" disabled /></li>
			<li><img src="${pageContext.servletContext.contextPath}/img/page_mypage/close.png" name="viewpw" id="viewpw"/></li>
			<li><button type="button" class="button_com" name="editpw" id="editpw">비밀번호 변경</button></li>
			<li><label>이메일</label></li>
			<li><input type="text" name="email" id="email" value="${vo.email }"/></li>
			<li><label>거주지역</label></li>
			<li>
				<input type="text" name="zipcode" id="zipcode" value="${vo.zipcode }"/>
				<input type="button" class="button_com" id="search" name="search" value="우편번호찾기"/>
			</li>
			<li><input type="text" name="addr" id="addr"value="${vo.addr }"/></li>
			<li><input type="text" name="addrdetail" id="addrdetail" placeholder="상세주소 입력" value="${vo.addrdetail }"/></li>
			<li><label>전화번호</label></li>
			<li><input type="text" placeholder="전화번호 입력" name="tel" id="tel"value="${vo.tel }"/></li>
			<li><input type="submit" class="button_com" name="save" id="save" value="저장" /></li>
			<li><a href="${pageContext.servletContext.contextPath}/mypage/mypage"><input type="button" class="button_com" name="cancel" id="cancel" value="취소" /></a></li>
			<li><button type="button" class="button_com" id="accountDel">회원탈퇴</button></li>
		</ul>
	</form>
</main>