<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.body{
	width:1000px;
	margin: 0px auto;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
아이디:${member.mid }<br>
비밀번호:${member.pwd }<br>
등록일:${member.regdate }<br>
<a href="login.do">로그인</a>
</div>
</body>
</html>