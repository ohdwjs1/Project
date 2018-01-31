<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<h1>회원정보 설정 </h1>
<form action="mypage.do" method="post">
<table>
	<tr>
		<td>이름</td>
		<td>${m.name}</td>
	</tr>
	<tr>
		<td>사용자ID</td>
		<td>${m.mid }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${m.pwd }</td>
	</tr>
	<tr>
		<td>E-mail</td>
		<td><input type="text" name="email" value="${m.email }"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone" value="${m.phone }"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><input type="text" name="address" value="${m.address }"></td>
	</tr>
</table>
<input type="submit" value="수정">
</form>
<a href="../joinus/withdraw.do">회원탈퇴</a>
<a href="../joinus/index.do">뒤로가기</a>
</div>
</body>
</html>