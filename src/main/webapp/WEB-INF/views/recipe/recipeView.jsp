<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#recipe_view{
	width: 1300px;
	margin: 0 auto;
}
#recipe_view button{
	border: none;
	background: none;
}
#recipe_header{
	width: 100%; height: 140px;
}
#title_RV{
	width: 800px; height: 75px; line-height: 75px;
	margin: 0 auto;
	font-size: 30px;
	font-weight: bold;
	text-align: center;
	margin-top: 100px;
	border: 3px solid black;
	overflow: hidden;
}
#writer{
	width: 50%; height: 60px;
	float: left;
	display: flex;
    align-items: center;
}
#dateShare{
	width: 50%; height: 60px;
	float: right;
	text-align: right;
}
#icon_writer{
	padding:2px;
	width: 40px; height: 40px;
	margin-left: 100px;
	border: 2px solid black;
	border-radius: 50%;
}
#name_writer{
	font-size: 20px;
	line-height: 60px;
	margin-left: 10px;
}
#date_recipe{
	font-size: 15px;
	line-height: 60px;
	color: darkgray;
	margin-right: 20px;
}
#icon_share{
	width: 40px; height: 40px;
	float: right;
	margin-top: 10px;
	margin-right: 100px;
}
/*메인 게시글*/
#textbox_recipe{
	width: 1100px; height: auto;
	border:5px solid gray;
	border-radius: 5px;
	margin: 0 auto;
	text-align: center;
	padding: 30px;
}

#recipe_context{
	width: 1000px; height: auto;
	margin: 0 auto;
	text-align: center;
}

#view_left{
	width: 50%; height: 40px;
	line-height: 40px;
	float: left;
	display: flex;
    align-items: center;
    padding-top: 10px;
    margin-bottom: 5px;
}
#view_left form{
	display: flex;
}
#view_right{
	width: 50%; height: 40px;
	padding-top: 10px;
	line-height: 40px;
	float: right;
	text-align: right;
	margin-bottom: 5px;
}
#view_left img{
	width: 40px; height: 40px;
}
#heart_recipe{
	margin-left: 100px;
}
#scrap{
	margin-right: 100px;
}
#scrap>img{
	width: 35px; height: 35px;
}
#replyArea{
	margin: 0 auto;
	display: flex;
	width: 1100px; height: auto;
}
#replyArea_content{
	float:left;
}

.profile_writer>img{
	width: 40px; height: 40px;
}
#coment{
		width:600px; height:50px;
}
#replyList>li{
	border-bottom:1px solid #ddd;
}
</style>
<script>
//ajax를 이용한 댓글목록, 수정, 삭제, 목록
$(function(){
	//목록
	function replyList(){
		$.ajax({
			url:'${pageContext.servletContext.contextPath}/RecipeReply/list',
			data:{no:${vo.recipeno}},
			type:'GET',
			success:function(result){
				console.log(result);
				
				var tag = ""; //댓글목록 태그(수정, 삭제)
				
				$(result).each(function(i, rVO){
					tag += "<li><div><b>"+rVO.userid+"</b>("+rVO.writedate+")";
					
					//수정, 삭제버튼은 로그인 아이디와 댓글 글쓴이의 아이디가 같을때
					if('${logId}' == rVO.userid){
						tag += "<input type='button' value='Edit'/>";
						tag += "<input type='button' value='Del' title='"+ rVO.replyno +"'/>";
						
						tag += "<p>"+rVO.coment+"</p></div>";//댓글내용
						
						//수정폼 -> 댓글번호, 댓글내용이 폼에 있어야 한다.
						tag += "<div style='display:none'><form>";
						tag += "<input type='hidden' name='replyno' value='"+rVO.replyno+"'/>";
						tag += "<textarea name='coment' style='width:400px;height:80px;'>"+rVO.coment+"</textarea>";
						tag += "<input type='submit' value='댓글수정하기'/>";
						tag += "</form></div>"
					}else{
						tag += "<p>"+rVO.coment+"</p></div>";
					}
					tag += "</li>";
				});
				console.log(tag);
				$("#replyList").html(tag);
				
			},error:function(e){
				console.log(e.responseText);
			}
		});	
		
	}
	
	//등록
	$("#replyForm").submit(function(){
		//form태그의 action을 중지한다.
		event.preventDefault();
		
		//coment입력확인
		if($("#coment").val()==""){
			alert("댓글을 입력후 등록하세요..");
			return false;
		}
		
		// form의 데이터를 query로 만들기 
		var params = $(this).serialize();
		
		$.ajax({
			url : "/campus/recipeReply/write",
			data : params,
			type : "POST",
			success:function(result){
				console.log(result);
				$("#coment").val("");
				//목록 새로 출력
			},error:function(e){
				console.log(e.responseText);
			}
		});
	});
	//수정폼
	$(document).on('click','#replyList input[value=Edit]',function(){
		$(this).parent().css('display','none');//부모숨기기 : 댓글내용
		$(this).parent().next().css('display','block');// 댓글수정폼
	});
	//수정(DB)
	$(document).on('submit', '#replyList form', function(){
		event.preventDefault();//기본이벤트 제거
		var params = $(this).serialize();
		var url = "${pageContext.servletContext.contextPath}/recipeReply/editOk"
		$.ajax({
			url:url,
			data:params,
			type:'POST',
			success:function(result){
				console.log('댓글수정', result);
				if(result=='0'){//댓글수정실패
					alert("댓글수정 실패하였습니다.")
				}else{
					replyList();//댓글목록 다시 선택
				}
			},error:function(){
				console.log(error.responseText);
			}
		});
	});
	
	//댓글 삭제
	$(document).on('click','#replyList input[value=Del]',function(){
		if(confirm("삭제하시겠습니까?")){
			//삭제할 레코드번호
			var replyno = $(this).attr("title");
			
			$.ajax({
				url : "${pageContext.servletContext.contextPath}/recipeReply/delete",
				data : {
					replyno : replyno
				},
				type:"GET",
				success:function(result){
					replyList();//댓글목록 가시 뿌리기
				},error:function(e){
					console.log(e.responseText);
				}
			});
		}
	});
	//목록출력 함수호출
	replyList();
});

</script>
<script>
	//url주소 복사
	function clip(){
	
		var url = '';
		var textarea = document.createElement("textarea");
		document.body.appendChild(textarea);
		url = window.document.location.href;
		textarea.value = url;
		textarea.select();
		document.execCommand("copy");
		document.body.removeChild(textarea);
		alert("URL이 복사되었습니다.")
	}
	function recipeDel(){
		if(confirm("글을 정말로 삭제하시겠습니까?")){
			location.href = "/campus/recipe/delete?recipeno=${vo.recipeno}";
		}
	}
	

</script>

<div id="recipe_view">
	<div id="recipe_header">
		<div id="title_RV">${vo.subject }</div>
		<div id="writer">
			<span><img src="/campus/img/page_recipe/icon_profile.png" id="icon_writer"/></span>
			<span id="name_writer">${vo.userid }</span>
		</div>
		<div id="dateShare">
			<span id="date_recipe">${vo.writedate }</span>
			<span class="button gray medium">
				<a href="#" onclick="clip(); return false;">
					<img src="/campus/img/page_recipe/icon_share.png" id="icon_share">
				</a>
			</span>
		</div>
	</div>
	<div id="textbox_recipe">
		<div id="recipe_context">
			${vo.content }
		</div>
		<c:if test="${logStatus=='Y' && logId==vo.userid}">
			<a href="/campus/recipe/edit?recipeno=${vo.recipeno}">수정</a>
			<a href="javascript:recipeDel()">삭제</a>
		</c:if> 
	</div>
	<div id="view_left">
		<form method="post">
			<button id="heart_recipe" class="recipe_btn">
				<img src="/campus/img/post/heart.png"/>
			</button>
			<div>20</div>
		</form>
		<form>
			<button id="reply_recipe">
				<img src="/campus/img/post/reply.png"/>
			</button>
			<div>19</div>
		</form>
	</div>
	<form id="view_right" action="/recipe/scrap">
		<button id="scrap">
			<img src="/campus/img/page_recipe/icon_scrap_out.png"/>
		</button>
	</form>
	<!-- 댓글 -->
	<div id="replyArea">
		<div id="replyArea_content">
			<!-- 로그인 상태일때 댓글쓰기 -->
			<c:if test="${logStatus=='Y' }">
				<form method="post" id="replyForm">
					<!-- 원글 글번호 -->
					<input type="hidden" name="no" value="${vo.recipeno }"/>
					<textarea name="coment" id="coment"></textarea>
					<button>댓글등록</button>
				</form>
			</c:if>
			<!-- 댓글목록 -->
			<div>댓글 목록</div>
			<ul id="replyList"></ul>
		</div>
	</div>
</div>