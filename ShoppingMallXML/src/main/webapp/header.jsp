<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div.top_left{
		float: left;
	}
	div.top{ 
		text-align: right;
	}
	.nav_top{
		color: white !important;
	}
	.categorie{
		margin-bottom: 20px; 
	}
	.top{
		margin-bottom:0px;
	}
	.banner{
		display: inline;
		height: 40px;
	}
	.searchbar_input{
		outline: none;
		border: 0px;
		width:500px;
		padding: 0px;
		font-size: 30px;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".searchbar_input").keydown(function(key){
 		if(key.keyCode==13){
			var query=$(".searchbar_input").val();
			location.href="../customer/notice.do?q="+query;
		} 
	});
});
</script>
</head>
<body>
<div class="header">
<nav class="navbar navbar-default top" style="background:#1f8ce6">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand nav_top" href="../joinus/index.do">12번가</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-left" role="search" action="../customer/notice.do?">
        <div class="form-group">
          <input type="text" class="form-control" id="searchbar_input" placeholder="Search" name="q">
        </div>
        <button type="submit" class="btn btn-default">검색</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
     	<c:if test="${empty mid}">
	        <li><a class="nav_top" href="../joinus/join.do">회원가입</a></li>
			<li><a class="nav_top" href="../joinus/login.do">로그인</a></li>
			<li><a class="nav_top" href="../purchase/cart.do">장바구니</a></li>
		</c:if>
		<c:if test="${!empty mid}">
			<li>${name }님 환영합니다.</li>
			<li><a class="nav_top" href="../customer/noticeReg.do">판매글쓰기</a></li>
			<li><a class="nav_top" href="../joinus/myinfo.do">내 정보</a></li>
			<li><a class="nav_top" href="../joinus/mypage.do">정보수정</a></li>
			<li><a class="nav_top" href="../purchase/cart.do">장바구니</a></li>
			<li><a class="nav_top" href="../joinus/logout.do">로그아웃</a></li>
		</c:if>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<!-- 카테고리 -->
<nav class="navbar navbar-default categorie">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-center">
        <li><a href="../customer/notice.do?cate=fashion">패션</a></li>
        <li><a href="../customer/notice.do?cate=food">식품</a></li>
        <li><a href="../customer/notice.do?cate=furniture">가구</a></li>
        <li><a href="../customer/notice.do?cate=digital">디지털</a></li>
        <li><a href="../customer/notice.do?cate=sports">스포츠</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
</body>
</html>