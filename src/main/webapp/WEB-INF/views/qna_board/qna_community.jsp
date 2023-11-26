<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel = "stylesheet" href ="/campus/css/search.css">
<link rel = "stylesheet" href ="/campus/css/community.css">
<link rel = "stylesheet" href ="/campus/css/media.css">
<style>
	* {
	    margin: 0;
	    padding: 0;
	    box-sizing: border-box;
	}
  .board_list {
    width: 100%;
    border-top: 2px solid #000;
  }
  
  .board_list > div {
    border-bottom: 1px solid #999;
    font-size: 0;
  }
  
  .board_list > div.top {
    border-bottom: 1px solid #999;
  }
  
  .board_list > div:last-child {
    border-bottom: 1px solid #000;
  }
  
  .board_list > div > div {
    display: inline-block;
    padding: 15px 0;
    text-align: center;
    font-size: 1rem;
  }
  
  .board_list > div.top > div {
    font-weight: 600;
  }
  
  .board_list .num {
    width: 10%;
  }
  
  .board_list .title {
    width: 60%;
    text-align: left;
  }
  
  .board_list .top .title {
    text-align: center;
  }
  
  .board_list .writer {
    width: 10%;
  }
  
  .board_list .date {
    width: 10%;
  }
  
  .board_list .count {
    width: 10%;
  }
	.page {
		height: 40px;
	}
	.page li {
		float: left;
		height: 40px;
		line-height: 40px;
		padding: 10px;
	}
</style>
<html>
<head>
	<title>자유게시판</title>
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
                <strong>QnA 게시판</strong>
                <p>빠르게 궁금증을 해결하기 위해 질문할 수 있는 게시판입니다.</p>
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
                <div class="board_list">
                    <div class="top">
                        <div class="num">번호</div>
                        <div class="title">제목</div>
                        <div class="writer">글쓴이</div>
                        <div class="date">작성일</div>
                        <div class="count">조회</div>
                    </div>
                    <c:forEach var="bVO" items="${list}">
                    <div>
                        <div class="num">${bVO.boardno }</div>
                        <div class="title"><a href="/campus/qna_board/view?no=${bVO.boardno}&nowPage=${pVO.nowPage}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">${bVO.subject}</a></div>
                        <div class="writer">${bVO.userid }</div>
                        <div class="date">${bVO.writedate }</div>
                        <div class="count">${bVO.hit }</div>
                     </div>
                    </c:forEach>
                   </div>
                <div class = "page">
					<ul>
						<!-- 현재보는 페이지(pVO.nowPage)를 기준으로 이전표시 -->
						<c:if test="${pVO.nowPage==1 }">
							<li>prev</li>
						</c:if>
						<c:if test="${pVO.nowPage>1}">
							<li><a href="/campus/qna_board/list?nowPage=${pVO.nowPage-1}<c:if test='${pVO.searchWord!=null}'>&searchkey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">prev</a></li>
						</c:if>
						<!-- startPage에서 한번에 출력할 페이지 수 만큼 페이지 번호를 출력한다. nowPage -->
						<c:forEach var="p" begin="${pVO.startPage }" end="${pVO.startPage+pVO.onePageCount-1}">
							<c:if test="${p<=pVO.totalPage }">
								<c:if test="${p==pVO.nowPage }"> <!-- 현재페이지일때 -->
									<li style="background-color:#ddd">
								</c:if>
								<c:if test="${p!=pVO.nowPage }"> <!-- 현재페이지가 아닐때 -->
									<li>
								</c:if>
								<a href="/campus/qna_board/list?nowPage=${p}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">${p}</a>
								</li>
						    </c:if>
						</c:forEach>
						<!-- next page -->
						<c:if test="${pVO.nowPage<pVO.totalPage }">
							<li><a href="/campus/qna_board/list?nowPage=${pVO.nowPage+1}">next</a></li>
						</c:if>
						<c:if test="${pVO.nowPage>=pVO.totalPage }">
							<li>next</li>
						</c:if>
					</ul>
				</div>
                <div class="bt_wrap">
                    <a href="/campus/qna_board/write" class="on">등록</a>
                    <!--<a href="#">수정</a>-->
                </div>
            </div>
        </div>
    </main>
    <script src = "/campus/js/script.js"></script>
	</body>
</html>