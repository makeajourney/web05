<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 목록</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"></jsp:include>
		
		<h1>회원 목록</h1>
		<p><a href='add'>신규 회원</a></p>

		<c:forEach var="member" items="${members }">
		${member.no },
		<a href='update?no=${member.no }'>${member.name }</a>,
		${member.email },
		${member.createDate }
		<a href='delete?no=${member.no }'>[delete]</a><br>
		</c:forEach>
		<jsp:include page="/Tail.jsp"></jsp:include>
	</body>
</html>