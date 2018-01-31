<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
.table{
	width:800px;
	border-top: 2px solid #b0b0b0;
}
table{
	width: 800px;
	border-bottom: 2px solid #c8c8c8;
	border-collapse: collapse;
}
table thead th{
	height:26px;
	background-color: #e8e8e8;
	color:#777;
	font-weight: normal;
}
.tb_border td{
	padding: 13px;
}
a{
	font-style: normal;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
<div>
	포인트&nbsp;&nbsp;<fmt:formatNumber value="${m.mileage }" pattern="#,###"></fmt:formatNumber>
</div>
<div class="table">
<table>
	<thead>
		<tr>
			<th>날짜</th>
			<th>상품</th>
			<th>수량</th>
			<th>상품금액</th>
			<th>확인</th>
		</tr>
	</thead>
	<c:forEach var="b" items="${list}">
		<tr class="tb_border">
			<td>${b.dealdate }</td>
			<td><a href="../customer/noticeDetail.do?seq=${b.seq_notices}">${b.pname }</a></td>
			<td>${b.amount }</td>
			<td><fmt:formatNumber value="${b.amount*b.sumprice}" pattern="#,###"></fmt:formatNumber>원</td>
			<td><a href="../customer/reply.do?seq=${b.seq_notices}">상품평</a></td>			
		</tr>		
	</c:forEach>
</table>
</div>
</div>
</body>
</html>