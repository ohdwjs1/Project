<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		margin:0px auto;
	}
	#error{
		color: red;
	}
	.line{
		width:1000px;
		height:1px;
		background-color:#0e6ad3;
		border: 1px solid #0e6ad3;
	}
	.btn1{
		text-decoration:none;
		width:200px;
		height: 70px;
		text-align: center;
		font-size: 25px;
		font-weight: bold;
		padding: 7px 50px;
		border-radius: 3px;
		border: 1px solid #2e8de5;
		background-color: #2e8de5;
		color: white;
	}
	span{
		display:inline-block;
		width:80px;
	}
	a{
		font-style: normal;
		text-decoration:none;
		color: #676767;
		
	}
</style>
<script type="text/javascript">
	window.addEventListener("load",function(){
		var btn_sub=document.getElementById("btn_sub");
		btn_sub.onclick=function(){
			var mid=document.getElementById("mid");
			var pwd=document.getElementById("pwd");
			
			if(mid.value==""){
				alert("아이디를 입력하세요");
				return false;
			}else if(pwd.value==""){
				alert("비밀번호를 입력하세요");
				return false;
			}
		};
	});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
	<form class="form-horizontal" action="login.do" method="post">
		<c:if test="${!empty error}">
	<div id="error">${error }</div>
		</c:if>

  <div class="form-group">
    <label for="mid" class="col-sm-2 control-label">아이디</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="mid" name="mid" placeholder="아이디">
    </div>
  </div>
  <div class="form-group">
    <label for="pwd" class="col-sm-2 control-label">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="pwd" name="pwd" placeholder="비밀번호">
    </div>
  </div>
  <div class="form-group">
  	<div class="col-sm-offset-2 col-sm-10">
  		<a href="findId.do">아이디 찾기</a>|<a href="findPwd.do">비밀번호 찾기</a>|<a href="./join.do">회원가입</a>
  	</div>
  </div>		
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default" id="btn_sub">로그인</button>
    </div>
  </div>
<!-- 			<label class="lb"><span>아이디</span><input type="text" name="mid" id="mid"></label><br>
			<label class="lb"><span>비밀번호</span><input type="password" name="pwd" id="pwd"></label>
			<br>
			<input class="btn1" type="submit" value="로그인" id="btn_sub"> -->

	</form>
	
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>