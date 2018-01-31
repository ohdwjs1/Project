<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	color: #676767;
	background-color: #f5f5f5;
}
.body{
	width:1000px;
	margin: 0px auto;
	text-align: center;
	border: 1px solid #d2ddd2;
	background-color: white;
	padding: 20px;
	margin: 20px auto;
}
.body h1{
	border-bottom: 1px solid #EEE;
}
.required{
	color: red;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
	<h1>회원가입</h1>
	<form class="form-horizontal" action="join.do" method="post">
		<p class="alert alert-info">회원정보를 입력해주세요. 모두 입력하셔야 가입이 가능합니다.</p>
		<div class="form-group">
			<label for="mid" class="col-sm-2 control-label">
			<span class="required">*</span>아이디</label>
			<div class="col-sm-6">
				<input class="form-control" type="text" name="mid" id="mid">
			</div>
		</div>
		<div class="form-group">
			<label for="pwd" class="col-sm-2 control-label">
			<span class="required">*</span>비밀번호</label>
			<div class="col-sm-6">
				<input class="form-control" type="password" name="pwd" id="pwd">
			</div>
		</div>
		<div class="form-group">
			<label for="pwd2" class="col-sm-2 control-label">
			<span class="required">*</span>비밀번호 확인</label>
			<div class="col-sm-6">
				<input class="form-control" type="password" name="pwd2" id="pwd2">
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">
			<span class="required">*</span>이름</label>
			<div class="col-sm-6">
				<input class="form-control" type="text" name="name" id="name">
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">
			<span class="required">*</span>휴대폰번호</label>
			<div class="col-sm-6">
				<input class="form-control" type="text" name="phone" id="phone">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">
			<span class="required">*</span>이메일 주소</label>
			<div class="col-sm-6">
				<input class="form-control" type="text" name="email" id="email">
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="가입">
		<a class="btn btn-warning" href="index.do">되돌아가기</a>
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>