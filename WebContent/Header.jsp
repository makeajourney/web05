<%@ page import="spms.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// Member member = (Member)session.getAttribute("member");
%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
	SPMS(Simple Project Management System)
	<c:if test="${!empty member.email }">
		<span style="float:right;">
			${member.name}
			<a style="color:white;" href="${pageContext.request.contextPath }/auth/logout.do">logout</a>
		</span>
	</c:if>
</div>