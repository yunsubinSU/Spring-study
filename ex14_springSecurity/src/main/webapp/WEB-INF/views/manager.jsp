<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- 태그 라이브러리 지시자 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MANAGER</h1>
	<hr>
	<p>PRINCIPAL : <sec:authentication property="principal"/> </p>
	<p>USERDTO : <sec:authentication property="principal.userDto"/> </p>
	<p>principal로 꺼낸 ID : <sec:authentication property="principal.username"/></p><hr/>
	<p>사용자의 이름: <sec:authentication property="principal.userDto.username"/></p><hr/>
	<p>사용자의 아이디: <sec:authentication property="principal.userDto.username"/></p><hr/>
	<p>사용자의 권한 목록: <sec:authentication property="principal.userDto.role"/></p><hr/>
	<sec:authorize access="isAuthenticated()">
		<p>로그인 사용자입니다.</p>
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
		<p>비로그인 사용자입니다. 로그인 해주세요.</p>
	</sec:authorize>
	<a href="${pageContext.request.contextPath}/logout" 
	style="text-decoration: none;  color: red; border: 5px solid;">
	로그아웃
	</a>
	
</body>
</html>