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
                    <img src="${pageContext.servletContext.contextPath}/img/page_mypage/default.jpeg"> <%-- 수정 --%>
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
                    <a href="recipe">더 보기 ></a>
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
                            <a href="/campus/recipe/view/${recipeList.recipeno}"> <%-- 수정 --%>
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
            </div>
            <div class="my_wishlist recipe_list">
                <div class="my_wishlist_header recipe_list_header">
                    <span>찜 목록</span>
                    <a href="heart">더 보기 ></a>
                </div>

                <div class="my_wishlist_item">
                    <ul>
                        <c:if test="${heartList.size() == 0}">
                            <li class="my_wishlist_item recipe_item">
                                    <div class="name_box">
                                        <p class="recipe_name">찜 목록이 비었습니다. </br>좋아하는 음식을 찜해봐요</p>
                                    </div>
                            </li>
                        </c:if>
                        <c:forEach items="${heartList}" var="heartList">
                        <li class="my_wishlist_item recipe_item">
                            <a href="/campus/recipe/view/${heartList.recipeno}"> <%-- 수정 --%>
                                <div class="img_wrap">
                                    <img src="${pageContext.servletContext.contextPath}/img/page_mypage/kimchi.jpg"> <%-- 수정 --%>
                                </div>
                                <div class="name_box">
                                    <p class="recipe_name">${heartList.subject}</p>
                                </div>
                            </a>
                        </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>
        </div>
    </main>

</body>
</html>