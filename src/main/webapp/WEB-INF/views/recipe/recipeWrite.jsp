<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- ckeditor -->
<link rel="stylesheet" href="/campus/css/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="/campus/js/ckeditor.js"></script>
<style>
	#subject{width: 1293px; height:25px; line-height: 25px; font-size: 20px;}
	body, ul, li{margin:0; padding:0; list-style-type:none;}
	#container {width: 1300px;}
</style>
<script>
	window.onload = function(){
		CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
	}

	$(function(){
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
			return true;
		});
	});
</script>
<main>
	<h1>자료올리기 폼</h1>
	<!-- file첨부가 있는 form태그는 시작태그에 enctype속성이 반드시 있어야 서버로 파일을 보낼 수 있다. -->
	<form method="post" id="dataForm" action="${pageContext.servletContext.contextPath}/recipe/writeOk" enctype="multipart/form-data">
		<ul>
			<li>제목</li>
			<li><input type="text" name="subject" id="subject"/></li>
			<li>글내용</li>
			<li><textarea name="content" id="content"></textarea></li>
			<!-- 첨부파일 -->
			<li>첨부파일</li>
			<li>
				<div><input type="file" name="filename"/><input type="button" value=" + "/></div>
			</li>
			
			<li style="padding-top:10px">
				<input type="submit" value="자료올리기"/>
				<input type="reset" value="다시쓰기"/>
			</li>
		</ul>
	</form>
</main>