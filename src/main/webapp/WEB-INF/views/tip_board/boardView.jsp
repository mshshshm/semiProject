<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel = "stylesheet" href ="/campus/css/search.css">
    <link rel = "stylesheet" href ="/campus/css/view.css">
    <link rel = "stylesheet" href ="/css/media.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
   	* {
	      margin: 0;
	      padding: 0;
	      box-sizing: border-box;
	}
		
  .comment {
    width: 100%;
  }
  
  .comment .title {
    padding: 15px 15px;
    border-bottom: 1px dashed #ddd;
    font-size: 1.2rem;
  }
  
  .comment .info {
    padding: 15px;
    border-bottom: 1px solid #999;
    font-size: 0;
  }

  .comment .info textarea {
    height: 100px;
    width: 100%;
    resize: none;
    border: 1px solid #ccc;
    border-radius: 10px;
    padding: 1em;
    margin-bottom: 5px;
  }

  .comment .info button {
    padding: 0.5em;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 1rem;
    color: black;
    font-size: 1.2rem;
    cursor: pointer;
  }

  #replyList {
    width: 100%;
  }

  #replyList .title {
    padding: 15px 15px;
    border-bottom: 1px dashed #ddd;
    font-size: 1.2rem;
  }
  
  #replyList .info {
    padding: 15px;
    border-bottom: 1px solid #999;
    font-size: 0;
  }
  
  #replyList .info dl {
    position: relative;
    display: inline-block;
    padding: 0 20px;
  }
  
  #replyList .info dl:first-child {
    padding-left: 0;
  }

   
  #replyList .info dl::before {
    content: "";
    display: block;
    width: 1px;
    height: 13px;
    background: #ddd;
    position: absolute;
    top: 1px;
    left: 0;
  }
  
  #replyList .info dl:first-child::before {
    display: none;
  }
  
  #replyList .info dl dt,
  #replyList .info dl dd {
    display: inline-block;
    font-size: 1rem;
  }
  
  #replyList.info dl dd {
    margin-left: 10px;
    color: #777;
  }
  
  #replyList .comment_output {
    padding: 12.5px;
    font-size: 1rem;
  }
	</style>
    <script>
	function boardDel() {
		if(confirm("글을 정말로 삭제하시겠습니까?")) {
			location.href = "/campus/tip_board/delete?no=${vo.boardno}";
		}
	}
	//ajax를 이용한 댓글등록, 수정, 삭제, 목록
	$(function() {
		//목록
		function replyList() {
			$.ajax({
				url:'${pageContext.servletContext.contextPath}/tip_boardReply/list',
				data:{no:${vo.boardno}},
				type: "GET",
				success:function(result){
					console.log(result);
					
					var tag = ""; //댓글목록 태그(수정, 삭제)
					
					$(result).each(function(i, rVO) {
						tag += '<div class="comment_view"><div class="info">';
						tag += '<dl><dt>글쓴이　</dt><dd id="comment_author">' + rVO.userid + '</dd></dl>';
						tag += '<dl><dt>작성일　</dt><dd id="comment_date">' + rVO.writedate + '</dd></dl>';
 
						//수정, 삭제버튼은 로그인 아이디와 댓글쓴이의 아이디가 같을때
						//goguma == 'goguma'
						if ('${logId}' == rVO.userid) {
							tag += "<input type='button' value='Edit'/>";
							tag += "<input type='button' value='Del' title='"+rVO.replyno+"'/>";
													
							tag += "<div class = 'comment_output' id = 'comment_content'>" + rVO.content + "</div></div>"; //댓글내용
							
							//수정폼 -> 댓글 글번호, 댓글내용이 폼에 있어야함.
							tag += "<div style = 'display: none'><form method = 'post'>";
							tag += "<input type='hidden' name='replyno' value='"+ rVO.replyno +"'/>";
							tag += "<textarea name='content' style='width: 400px; height: 80px;'>" + rVO.content + "</textarea>";
							tag += "<input type='submit' value='댓글수정하기'/>";
							tag += "</form></div>";
						} else {
							tag += "<div class = 'comment_output' id = 'comment_content'>" + rVO.content + "</div></div>"; //댓글내용
						}
						tag += "</li>";
					});
					
					$("#replyList").html(tag);
				},
				error:function(e){
					console.log(e.responseText);
				}
			});
		}
		//등록
		$("#replyForm").submit(function() {
			//form태그의 action을 중지한다.
			event.preventDefault();
			
			//content 입력확인
			if($("#comment_input").val()=="") {
				alert("댓글을 입력 후, 등록하세요..");
				return false;
			}
			//form의 데이터를 query로 만들기 n=39&content=예쁘네
			var params = $(this).serialize();
					 //= "no" = + $("no").val() + "&content = " + $("#content").val()
				$.ajax({
					url: "/campus/tip_boardReply/write",
					data: params,
					type: "POST",
					success:function(result) {
						console.log(result);
						$("#comment_input").val("");
						replyList();
						//목록 새로 출력
					},
					error:function(e) {
						console.log(e.responseText);
					}
				});
		});
		//수정폼 보여주기
		$(document).on('click','#replyList input[value=Edit]',function() {
			$(this).parent().css('display','none'); //부모숨기기 : 댓글내용
			$(this).parent().next().css('display','block'); //댓글수정
		});
		// 수정(DB)
		$(document).on('submit', '#replyList form', function() {
			event.preventDefault();
			var params = $(this).serialize();
			var url = "${pageContext.servletContext.contextPath}/boardReply/editOk";
			
			$.ajax({
				url: url,
				data: params,
				type: 'POST',
				success:function(result) {
					console.log(result);
					if(result == '0') { // 댓글 수정 실패
						alert("댓글이 수정실패되었습니다.");
					}else {
						replyList(); // 댓글목록 다시 선택
					}
				},
				error:function(error) {
					console.log(error.responseText);
				}
			});
		});
		
		// 댓글삭제
		$(document).on('click', '#replyList input[value=Del]', function() {
			if(confirm("삭제하시겠습니까?")){
				// 삭제할 레코드번호
				var replyno = $(this).attr("title");
				
				$.ajax({
					url: "${pageContext.servletContext.contextPath}/tip_boardReply/delete",
					data: {
						replyno: replyno
					},
					type: "GET",
					success:function(result) {
						replyList(); // 댓글 목록 다시 뿌리기
					},
					error:function(e) {
						console.log(e.responseText);
					}
				});
			}
		});
		// 목록출력 함수 호출
		replyList();
	});
</script>
</head>
<body>
    <main>
        <div class="board_wrap">
            <div class="container2">
                <form id="searchForm" action="https://www.google.com/search" method="GET">
                    <div class="search">
                        <div class="icon"></div>
                        <div class="input">
                            <input type = "text" placeholder="Search" id = "mysearch">
                        </div>
                        <span class="clear" onclick = "document.getElementById('mysearch').value = ''"></span>
                    </div>
                </form>
            </div>
            <div class="board_title">
                <strong>자유게시판</strong>
                <p>누구나 자유롭게 글을 게시할 수 있는 공간입니다.</p>
            </div>
            <div class="board_list_wrap">
                <div class="category">
                    <ul>
                        <li>
                            <button><a href="/campus/board/list">자유</button>
                        </li>
                        <li>
                            <button><a href="/campus/qna_board/list">QnA</a></button>
                        </li>
                        <li>
                            <button><a href="/campus/media_board/list">미디어</a></button>
                        </li>
                        <li>
                            <button><a href="/campus/tip_board/list">Tip</a></button>
                        </li>
                    </ul>
                </div>
            <div class="board_view">
                <div class="title">${vo.subject }</div>
                <div class="info">
                    <dl>
                        <dt>번호</dt>
                        <dd id="board_number">${vo.boardno }</dd>
                    </dl>
                    <dl>
                        <dt>글쓴이</dt>
                        <dd id="board_author">${vo.userid }</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd id="board_date">${vo.writedate }</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd id="board_view_num">${vo.hit }</dd>
                    </dl>
                </div>
                <div class="cont">
                    ${vo.content }
                </div>
            </div>
            <div class="comment">
	                <div class="title">댓글</div>
			                <div class="info">
			                	<c:if test="${logStatus=='Y' }">
				             	  	<form method="post" id="replyForm">
				             	  	    <input type="hidden" name="boardno" value="${vo.boardno }">
					                    <textarea id="comment_input" name="content" placeholder="댓글을 입력하세요..."></textarea>
					                    <br>
					                    <button class="btn btn-primary">작성</button>
				                    </form>
				                </c:if>
			                </div>
	           </div>
            <ul id="replyList">
            </ul>
            <div class="bt_wrap">
                <a href="/campus/tip_board/list?nowPage=${pVO.nowPage}<c:if test="${pVO.searchWord!=null}">&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">목록</a>
                <c:if test="${vo.userid==logId}">
					<a href="/campus/tip_board/edit?no=${vo.boardno}">수정</a>
					<a href="javascript:boardDel()">삭제</a>
				</c:if>
            </div>
            <script src = "/campus/js/script.js"></script>
</body>
</html>