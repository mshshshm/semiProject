<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/campus/js/jquery.bxslider.js"></script>
<script src="/campus/js/slick.min.js"></script>
<script src="/campus/js/home.js"></script>
<link rel="stylesheet" href="/campus/css/jquery.bxslider.css" type="text/css"/>
<link rel="stylesheet" href="/campus/css/slick-theme.css" type="text/css"/>
<link rel="stylesheet" href="/campus/css/slick.css" type="text/css"/>
<link rel="stylesheet" href="/campus/css/home.css" type="text/css"/>

<script>
	$(function(){
		function newsList(){
			$.ajax({
				
			});
		}
	});
</script>
<body class="container">
<div class="main_top">      
	<ul id="slider">
		<li><a href="#"><img src="/campus/img/page_main/banner.png"/></a></li>
		<li><a href="#"><img src="/campus/img/page_main/banner.png"/></a></li>
		<li><a href="#"><img src="/campus/img/page_main/banner.png"/></a></li>
		<li><a href="#"><img src="/campus/img/page_main/banner.png"/></a></li>
	</ul>
</div>
<div class="main_recipe">
<h3 style="display:inline;" class="main_title">이달의 레시피</h3>
<a href="/campus/recipe/list"><img src="/campus/img/page_main/btn_more_2.png" class="btn_more"/></a>
<div class="main_recipe_list">
    <div class="list01">  
            <c:forEach var="f" items="${fileList }">
				<a href="${pageContext.servletContext.contextPath }/uploadfile/${f.filename}">
					<img src="${pageContext.servletContext.contextPath }/uploadfile/${f.filename}"/>
				</a>
			</c:forEach>    
    </div>
</div>
</div>
<div id="postbox">
	<h3 class="main_title">게시글</h3>
	<div id="post">
		<ul id="post_line">
			<c:forEach var="dVO" items="${listB}">
				<li>
					<a href="/campus/board/view?no=${dVO.boardno}">
						<!--<img class="post_line_img" src="${pageContext.servletContext.contextPath }/uploadfile/${fileList.filename}" title="게시글 제목" style="display: inline;"/>-->
						<img class="post_line_img" src="/img/page_main/01.png" title="게시글 제목" style="display: inline;"/>
						<div class="post_line_cap">
							<div class="post_line_title">${dVO.subject }</div>
							<div class="post_line_view">조회수 : ${dVO.hit }</div>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div id="post_best">
		<img src="/campus/img/post/crown.png" style="display: inline;" id="crown">
		<h4 style="display: inline;" id="most_title">인기 게시글(조회수 높은 순)</h4>
		<a href="/campus/board/list"><img src="/campus/img/post/btn_more.png" id="most_btn_more"/></a>
		<ul id="most_list">
			<c:forEach var="bVO" items="${qnalist}">
				<li>
					<div class="board_type">자유</div>
					<a href="/campus/qna_board/view?no=${bVO.boardno}" style="padding-left: 3px;" class="mostList_title">${bVO.subject}</a>
					<div>&nbsp</div>
					<button class="heartBtn">
						<img src="/campus/img/post/heart.png"/>
					</button>
					<div>&nbsp30&nbsp&nbsp</div>
					<button type="submit" id="reply">
						<img src="/campus/img/post/reply.png"/>
					</button>
					<div>&nbsp30</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div id="news_box">
	<h3 class="main_title"  style="display: inline;">뉴스</h3>
	<a href="#"><img src="/campus/img/page_main/btn_more_2.png" class="btn_more"/></a>
	<div class="tabMenu">
		<!-- 메뉴 -->
		<ul>
			<li><label for="m1" class="text-shadow-pop-right">건강식단</label></li>
			<li><label for="m2" class="text-shadow-pop-right">식재료</label></li>
			<li><label for="m3" class="text-shadow-pop-right">다이어트</label></li>
			<li><label for="m4" class="text-shadow-pop-right">제철음식</label></li>
		</ul>
		<input type="radio" name="menu" id="m1" checked/>
		<input type="radio" name="menu" id="m2"/>
		<input type="radio" name="menu" id="m3"/>
		<input type="radio" name="menu" id="m4"/>
		<!--컨텐츠(서브메뉴)-->
		<div>
			<ul class="news_detail">
				<li class="main_news">
					<a href="${main.newsUrl }">
						<div class="news_com">${com.newsCompany }</div>
						<div class="news_title">${main.newsTitle }</div>
						<div class="news_date">${date.newsDate}</div>
					</a>
				</li>
				<li class="sub_news" >
					<c:forEach var="nVo" items="${sub }">
						<a href="${nVo.newsUrl }"><div class="spread-underline">${nVo.newsTitle} </div></a>
					</c:forEach>
				</li>
			</ul>
		</div>
		<div>
			<ul class="news_detail">
				<li class="main_news">
					<a href="${main2.newsUrl }">
						<div class="news_com">${com2.newsCompany }</div>
						<div class="news_title">${main2.newsTitle }</div>
						<div class="news_date">${date2.newsDate}</div>
					</a>
				</li>
				<li class="sub_news" >
					<c:forEach var="nVo2" items="${sub2 }">
						<a href="${nVo2.newsUrl }"><div class="spread-underline">${nVo2.newsTitle} </div></a>
					</c:forEach>
				</li>
			</ul>
		</div>
		<div>
			<ul class="news_detail">
				<li class="main_news">
					<a href="${main3.newsUrl }">
						<div class="news_com">${com3.newsCompany }</div>
						<div class="news_title">${main3.newsTitle }</div>
						<div class="news_date">${date3.newsDate}</div>
					</a>
				</li>
				<li class="sub_news" >
					<c:forEach var="nVo3" items="${sub3 }">
						<a href="${nVo3.newsUrl }"><div class="spread-underline">${nVo3.newsTitle} </div></a>
					</c:forEach>
				</li>
			</ul>
		</div>
		<div>
			<ul class="news_detail">
				<li class="main_news">
					<a href="${main4.newsUrl }">
						<div class="news_com">${com4.newsCompany }</div>
						<div class="news_title">${main4.newsTitle }</div>
						<div class="news_date">${date4.newsDate}</div>
					</a>
				</li>
				<li class="sub_news" >
					<c:forEach var="nVo4" items="${sub4 }">
						<a href="${nVo4.newsUrl }"><div class="spread-underline">${nVo4.newsTitle} </div></a>
					</c:forEach>
				</li>
			</ul>
		</div>
		
	</div>
</div>


