<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	body{
		background-color: #f5f5f5 !important;
	}
.body{
	width:1000px;
	margin: 0px auto;
	margin-bottom : 20px;
	border: 1px solid #d2ddd2;
	padding: 20px;
	background-color: white;
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
.result{
	width:600px;
}
.result h4{
	background-color:#305177;
	padding: 12px 20px;
	color: white;
	font-size: 100%;
}
.result_ul{
	list-style: none;
}
.result_ul li{
	padding:15px;
	font-weight: bold;
}
.r_title{
	
}
.r_amount{
	font-weight: normal;
	font-size: 13px;
}
.r_sum{
	font-size: 22px;
	font-family: Tahoma;
	float: right;
}
.r_sum_f{
	font-size: 30px;
	font-family: Tahoma;
	float: right;
	color: red;
}
.d_btn{
	text-align: center;
	
}


</style>
</head>

<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
<h2> 장바구니 </h2>
<div class="table">
<table>
	<thead>
		<tr>
			<th>상품명</th>
			<th>수량</th>
			<th>상품금액</th>
		</tr>
	</thead>
	<c:set var="sum" value="0"></c:set>
	<c:set var="amount" value="0"></c:set>
	<c:forEach var="cart" items="${list}">
		<tr class="tb_border">
			<td>상품명</td>
			<td>${cart.stock }</td>
			<td><fmt:formatNumber value="${cart.stock*cart.price}" pattern="#,###"></fmt:formatNumber>원</td>			
			<c:set var="sum" value="${sum+(cart.stock*cart.price) }"></c:set>
		</tr>
		
	</c:forEach>
</table>
</div>

<div class="result">
	<h4>결제 예정금액</h4>
	<ul class="result_ul">
		<li>
			<span class="r_title">상품가격</span>
			<span class="r_amount">(${fn:length(list)}개)</span>
			<span class="r_sum">
			<fmt:formatNumber value="${sum }" pattern="#,###"></fmt:formatNumber>
			원
			</span>
			
		</li>
		<li>할인금액
		<span class="r_sum">0원</span></li>
		<li>총가격
			<span class="r_sum_f">
			<fmt:formatNumber value="${sum }" pattern="#,###"></fmt:formatNumber>
			원
			</span>
		</li>
	</ul>	
</div>
<div class="d_btn">
	
	<a href="order.do?cart=on" class="btn btn-primary">주문결제</a>
</div>
</div>
</body>
</html>