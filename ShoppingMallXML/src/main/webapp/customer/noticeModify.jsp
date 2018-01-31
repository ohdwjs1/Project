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
판매글 수정<br>
<form action="noticeModifyProc.do" method="post">
	제품명:<input type="text" name="pname" value="${n.pname }"><br>
	갯수:<input type="text" name="stock" value="${n.stock }"><br>
	가격:<input type="text" name="price" value="${n.price }"><br>
	내용:<input type="text" name="content" value="${n.content }"><br>
	<input type="hidden" name="seq" value="${n.seq }">
	<input type="submit" value="수정">
	<a href="noticeDetail.do?seq="${n.seq}>뒤로</a>
</form>
</div>

</body>
</html>