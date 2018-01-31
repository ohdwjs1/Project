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
	<form action="noticeReg.do" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td>카테고리</td>
				<td>
					<select name="categorie">
						<option value="fashion" selected="selected">패션</option>
						<option value="food">음식</option>
						<option value="furniture">가구</option>
						<option value="digital">디지털</option>
						<option value="sports">스포츠</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="pname"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td>갯수</td>
				<td><input type="text" name="stock"></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><input name="file" type="file"/></td>
			</tr>
		</table>
		<input type="submit" value="글쓰기">
		<a href="../index.do"></a>
	</form>
</div>
</body>
</html>