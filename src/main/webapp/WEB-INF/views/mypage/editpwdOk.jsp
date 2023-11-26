<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	alert("비밀번호가 변경되었습니다.");
	location.href="${pageContext.servletContext.contextPath}/mypage/mypageEdit";
</script>