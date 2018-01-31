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
		width: 1000px;
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
.div_info{
	width:800px;
	border-top: 2px solid black;
}
.tb_info{
	text-align: left;
	
}
.tb_info tr{
	height: 30px;
}
.tb_info th{
	background-color: #f5f5f5;
	padding: 5px 10px;
}
.tb_info td{
	padding: 5px 10px;
}
.result{
	font-size: 30px;
	font-family: Tahoma;
	
}
.f_r{
	color: red;
}
.btn{
	width:170px;
	height: 70px;
	background-color: red;
	color: white;
	font-weight: bold;
	cursor: pointer;
	font-size: 20px;
	border: none;
	
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
<form action="orderProc.do?cart=${cart}" method="post">
	<input type="hidden" value="${b.n_seq }" name="seq">
	<input type="hidden" value="${b.price }" name="price">
	<input type="hidden" value="${b.stock }" name="amount">
<h2>주문상품</h2>
<div class="table">
<table>
	<thead>
		<tr>
			<th>상품명</th>
			<th>상품금액</th>
			<th>수량</th>
		</tr>
	</thead>
	<c:set var="sum" value="0"></c:set>
	<c:set var="amount" value="0"></c:set>
		<c:if test="${empty list }">
			<tr class="tb_border">
				<td>상품명</td>
				<td><fmt:formatNumber value="${b.stock*b.price}" pattern="#,###"></fmt:formatNumber>원</td>
				<td>${b.stock }</td>
				<c:set var="sum" value="${sum+(b.stock*b.price) }"></c:set>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach var="cb" items="${list }">
				<tr class="tb_border">
					<td>상품명</td>
					<td><fmt:formatNumber value="${cb.stock*cb.price}" pattern="#,###"></fmt:formatNumber>원</td>
					<td>${cb.stock }</td>	
					<c:set var="sum" value="${sum+(cb.stock*cb.price) }"></c:set>				
				</tr>
			</c:forEach>
		</c:if>
</table>
</div>
	거래번호:${b.n_seq }<br>
	<h2>구매자정보</h2>
	<div class="div_info">
		<table class="tb_info">
			<tr>
				<th>주문자</th>
				<td>${m.name }</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>${m.phone }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${m.email }</td>
			</tr>
			<tr>
				<th>배송지</th>
				<td><input type="text" name="address" value="${m.address }"></td>
			</tr>
		</table>
	</div>
	<h2>결제정보</h2>
	<div class="div_info">
	
		<table class="tb_info tb_result">
			<tr>
				<th>주문금액</th>
				<th>할인금액</th>
				<th>결제예정금액</th>
			</tr>
			<tr>
				<td class="result"><fmt:formatNumber value="${sum}" pattern="#,###"></fmt:formatNumber>원</td>
				<td class="result">0원</td>
				<td class="result f_r"><fmt:formatNumber value="${sum}" pattern="#,###"></fmt:formatNumber>원</td>
			</tr>
		</table>
	</div>
	<br>
	<input type="submit" class="btn" value="결제하기">
</form>

</div>
</body>
</html>