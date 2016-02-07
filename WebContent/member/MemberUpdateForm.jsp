<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
<jsp:useBean id="member"
	scope="request"
	class="spms.vo.Member"></jsp:useBean>
 -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Member Information</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"></jsp:include>
		<h1>회원정보</h1>
		<form action='update' method='post'>
			번호: <input type='text' name='no' value='${member.no }' readonly><br>
			이름: <input type='text' name='name' value='${member.name }'><br>
			이메일: <input type='text' name='email' value='${member.email }'><br>
			가입일: ${member.createdDate}<!-- <%=member.getCreatedDate() %> --><br>
			<input type='submit' value='저장'>
			<input type='button' value='삭제' onclick='location.href="delete?no=${member.no}";'>
			<input type='button' value='취소' onclick='location.href="list";'>
		</form>
		<jsp:include page="/Tail.jsp"></jsp:include>
	</body>
</html>