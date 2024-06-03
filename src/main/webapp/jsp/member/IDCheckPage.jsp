<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check</title>
</head>
<body>
<h2> ID 중복 확인</h2>
<form method="post" name="IDCheckPage" action="gshop.do?command=IDCheck">
		User ID : <input type="text" name="userid" value="${userid}">
		<input type="submit" value="검색"><br><br><br>
</form>
<div id="res">
		<c:if test="${result == 1}">
				<script type="text/javascript">
				opener.document.JoinPage.userid.value="";
				opener.document.JoinPage.reid.value="";
				</script>
				${userid} 는 이미 사용 중인 아이디입니다..
		</c:if>
		<c:if test="${result == -1}">
				${userid} 은(는) 사용 가능한 ID 입니다!!
				<input type="submit" value="쓸게요" onClick="idok('${userid}')">
		</c:if>
</div>
<script type="text/javascript" src="<c:url value='resources/js/member.js' />"></script>
</body>
</html>