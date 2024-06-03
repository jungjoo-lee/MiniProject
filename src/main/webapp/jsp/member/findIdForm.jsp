<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<form method="post" name="findIdForm" action="gshop.do?command=findId";>
	<h2>아이디 찾기</h2>
		<div class="field">
			<label>이름</label><input name="name" type="text"/>
		</div>
	<div class="field">
			<label>이메일</label><input name="email" type="text" id="email"/>
	</div>
		<input type="submit" value="이메일 인증 받기" onClick="return findId();"/>
		<div style="font-size:80%; font-weight:bold">${message}</div>
</form>
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>