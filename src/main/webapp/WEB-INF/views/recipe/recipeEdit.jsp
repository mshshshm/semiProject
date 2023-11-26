<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ckeditor -->
<link rel = "stylesheet" href ="/campus/css/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="/campus/js/ckeditor.js"></script>
<style>
	#subject{width: 1000px;}
	#file_list b{cursor:pointer;}
</style>
<script>
	window.onload = function(){
		CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
	}
	
	$(function(){
		let fCount = ${fileCount};//이미 업로드된 파일의 갯수
		//파일첨부 추가하기
		$(document).on('click', "input[value=' + ']", function(){
			//이벤트가 발생한 객체의 부모의부모에 추가하기
			$(this).parent().parent().append('<div><input type="file" name="filename"/><input type="button" value=" + "/> </div>');
			//이벤트가 발생한 +버튼의 value를 -로 변경하기
			$(this).val(' - ');
		});
		//첨부파일 삭제하기
		$(document).on('click', "input[value=' - ']", function(){
			$(this).parent().remove();
		});
		
		$("#dataForm").submit(function(){
			if($("subject").val()==""){
				alert("제목을 입력하세요.");
				return false;
			}
			//첨부파일의 객수 구하기 - 반드시 1개이상 첨부
			let fileCount = 0;
			$("input[name=filename]").each(function(){
				if($(this).val()!=""){
					fileCount++;
				}
			});
			//새로첨부+기존파일수
			if(fileCount+fCount<1){
				alert("1개이상의 파일을 첨부하여야 합니다.");
				return false;
			}
			// ----------------추가하기
			return true;
		});
		
		//이미 첨부된 파일 삭제처리
		$("#file_list b").click(function(){
			//부모 div는 숨기고 다음에 있는 input은 name속성의 값을 delFile로 설정한다.
			$(this).parent().css("display", "none");
			$(this).parent().next().attr("name", "delFile");
			
			//파일의 갯수 감소시키기
			fCount--;
		});
	});
</script>
<main>
	<h1>자료올리기 수정폼</h1>
	<!-- file첨부가 있는 form태그는 시작태그에 enctype속성이 반드시 있어야 서버로 파일을 보낼 수 있다. -->
	<form method="post" id="dataForm" action="${pageContext.servletContext.contextPath}/recipe/recipeOk" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${vo.recipeno }"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="subject" id="subject" value="${vo.subject}"/></li>
			<li>글내용</li>
			<li><textarea name="content" id="content">${vo.content}</textarea></li>
			<!-- 첨부파일 -->
			<li>첨부파일</li>
			<li id="file_list">
				<!-- 기존 업로드 파일 목록 -->
				<c:forEach var="fVO" items="${fList}">
					<div>${fVO.filename } <b>X</b></div>
					<input type="hidden" name="" value="${fVO.filename}"/>
				</c:forEach>
				
				<div><input type="file" name="filename"/><input type="button" value=" + "/></div>
			</li>
			
			<li style="padding-top:10px">
				<input type="submit" value="자료실 글수정"/>
				<input type="reset" value="다시쓰기"/>
			</li>
		</ul>
	</form>
</main>