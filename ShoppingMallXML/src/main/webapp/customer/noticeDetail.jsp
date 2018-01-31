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
<style>
	.body{
		width:1000px;
		margin: 0px auto;
	}
	.img{
		float: left;
	}
	.seller{
		font-size: 20px;
		font-weight: bold;
		border-bottom: 1px solid gray;
	}
	.pname{
		font-size:40px;
	}
	.price{
		font-size:35px;
		font-weight:bold;
		
	}
	.item{
		width: 700px;
	}
	.btn_area{
		width:1000px;
		height:50px;
	}
	.ul_reply{
		list-style: none;
	}
	.ul_reply li{
		padding: 10px 20px;
		border-radius:0px 10px 10px 10px;
		color: #333;
		background-color: white;
		font-size: 12px;
		width:200px;
		
	}
	.div_reply{
		border: 1px solid #ced0d7;
		background-color: rgb(207, 215, 233);
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn_buy").click(function(){
			fm.action="../purchase/order.do";
		});
	});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
<br>
<div class="media">
  <div class="media-left media-middle">
    <a href="#">
      <img class="media-object" src="..." alt="...">
    </a>
  </div>
  <div class="media-body">
    <h4 class="media-heading">Middle aligned media</h4>
    ...
  </div>
</div>
<form name="fm" action="../purchase/cart.do" method="post">
	<input type="hidden" value="${n.seq }" name="seq">
	<input type="hidden" value="${n.pname}" name="pname">
	<input type="hidden" value="${n.price }" name="price">
	<div class="container">
		<div class="img">
			<img src="http://localhost/shoppingMall/customer/upload/${n.img}" width="300px;" height="300px;">
		</div>
		<div class="item">
			<div class="pname">${n.pname}</div>
			<div class="price"><fmt:formatNumber value="${n.price }" pattern="#,###"></fmt:formatNumber>원</div>
			<div class="content">${n.content }</div>
			<div class="amount"><input type="text" value="1" name="amount"></div>
		</div>
		<br>
	</div>
	<div class="btn_area">
		<input class="btn btn-info" type="submit" value="장바구니">
		<%-- <a class="btn_buy btn" href="../purchase/order.do?seq=${n.seq">구매하기</a> --%>
		<input type="submit" id="btn_buy" class="btn btn-primary" value="구매하기">
	</div>
</form>
<h2>프리미엄 상품평</h2>
<div class="div_reply">
	<c:forEach var="r" items="${list }">
		<ul class=" ul_reply">
			<li>
				<strong>${r.m_id }</strong>
				<div>${r.content }</div>
				<div>${r.regdate }</div>
			</li>
		</ul>
	</c:forEach>
</div>
<c:if test="${mid==n.seller }">
<a href="noticeModify.do?seq=${n.seq }">수정</a> <a href="noticeDel.do?seq=${n.seq }">삭제</a><a href="notice.do">목록으로</a>
</c:if>
<c:if test="${mid!=n.seller }">
<a href="notice.do">목록으로</a>
</c:if>
</div>
</body>
</html>