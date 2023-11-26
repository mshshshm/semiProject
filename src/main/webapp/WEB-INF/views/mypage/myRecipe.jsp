<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/mypage.css">
</head>
<body>
    <main class="main">
        <div class="info">
            <h2>MY PAGE</h2>
            <div class="user_profile">
                <div class="user_profile_photo">
                    <img src="${pageContext.servletContext.contextPath}/img/page_mypage/default.jpeg">
                </div>
                <span class="user_nick_name">${loginUser.usernickname}</span>
            </div>
            <div class="user_info"> 
                <ul>
                    <li class="info_item">이름 : ${loginUser.username}</li>
                    <li class="info_item">아이디 : ${loginUser.userid}</li>
                    <li class="info_item">이메일 : ${loginUser.email}</li>
                    <li class="info_item">거주지 : ${loginUser.addr}</li>
                </ul>
            </div>
            <div class="user_etc">
				<a href="mypageEdit">
					<button>회원정보 수정</button>
				</a>
            </div>
        </div>

        <div class="recipe">
            <div class="my_recipe recipe_list">
                <div class="my_recipe_header recipe_list_header">
                    <span>내가 게시한 레시피</span>
                </div>

                <div class="my_recipe_list">
                    <ul>

                        <c:if test="${recipeList.size() == 0}">
                            <li class="my_recipe_item recipe_item">
                                <div class="name_box">
                                    <p class="recipe_name">등록한 레시피가 없습니다</p>
                                </div>
                            </li>
                        </c:if>
                        <c:forEach items="${recipeList}" var="recipeList">
                            <li class="my_recipe_item recipe_item">
                                <a href="/campus/recipe/view/${recipeList.recipeno}">
                                    <div class="img_wrap">
                                    <img src="${pageContext.servletContext.contextPath}/img/page_mypage/1.jpg"> <%-- 수정 --%>
                                    </div>
                                    <div class="name_box">
                                        <p class="recipe_name">${recipeList.subject}</p>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="page">
                    <ul class="pagination">
                        <c:if test="${pVO.nowPage==1 }">
                            <li class="page-item"><a class="page-link a-disable">prev</a></li>
                        </c:if>
                        <c:if test="${pVO.nowPage>1}">
                            <li class="page-item"><a class="page-link" href="/mypage/recipe?nowPage=${pVO.nowPage-1}">prev</a></li>
                        </c:if>
                        <c:forEach var="p" begin="${pVO.startPage }" end="${pVO.startPage+pVO.onePageCount-1}">
                            <c:if test="${p<=pVO.totalPage }">
                                <c:if test="${p==pVO.nowPage }">
                                    <li class="page-item">
                                    <a class="page-link page-active" href="/mypage/recipe?nowPage=${p}">${p}</a>
                                </c:if>
                                <c:if test="${p!=pVO.nowPage }">
                                    <li class="page-item">
                                    <a class="page-link" href="/mypage/recipe?nowPage=${p}">${p}</a>
                                </c:if>
                                </li>
                            </c:if>
                        </c:forEach>
                        <!-- next page -->
                        <c:if test="${pVO.nowPage<pVO.totalPage }">
                            <li class="page-item"><a class="page-link" href="/mypage/recipe?nowPage=${pVO.nowPage+1}">next</a></li>
                        </c:if>
                        <c:if test="${pVO.nowPage>=pVO.totalPage }">
                            <li class="page-item"><a class="page-link a-disable">next</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </main>
</body>
</html>