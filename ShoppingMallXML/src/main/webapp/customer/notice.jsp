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
	body{
		background-color: #f5f5f5 !important;
	}
	.body{
		width : 1000px;
		margin : 0px auto;
		margin-bottom : 20px;
		border: 1px solid #d2ddd2;
		padding: 20px;
		background-color: white;
	}
	div.top{
		text-align: right;
	}
	a{
		text-decoration: none;
		color: #666;
	}
	.banner{
		display: inline;
	}
	.searchbar{
		display:inline-block;
		width: 500px;
		height:40px;
		border: 4px solid #2e8de5; 
		border-radius: 6px;
		margin:30px auto;
	}
	.searchbar_input{
		outline: none;
		border: 0px;
		width:500px;
		padding: 0px;
		font-size: 30px;
	}
	.item_list_info{
		float: left;
	}
	.img{
		
	}
	.pname{
		width:400px;
	}
	.price{
		width:100px;
		font-size: 16px;
		font-weight: bold;
	}
	.seller{
		width:100px;
	}
	.pg{
		text-align: center;
	}
	.footMenu{
		text-align: center;
	}
</style>
<script type="text/javascript">
	window.addEventListener("load", function(){
		var nDetail=document.getElementById("nDetail");
	});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
<%-- 	<form action="notice.do">
		<select name="f">
		<c:if test="${field=='PNAME' }">
			<option value="PNAME" selected="selected">제목</option>
			<option value="CONTENT">내용</option>
		</c:if>
		<c:if test="${field=='CONTENT' }">
			<option value="PNAME">제목</option>
			<option value="CONTENT" selected="selected">내용</option>
		</c:if>
		</select>
		<input type="text" name="q" value="${query }"> 
		<input type="submit" value="검색">
	</form> --%>

<ul class="media-list">
<c:forEach var="n" items="${list }">
<div class="media">
  <div class="media-left ">
    <a href="../customer/noticeDetail.do?seq=${n.seq }">
      <img class="media-object thumbnail" src="../customer/upload/${n.img}" alt="이미지없음" width="120px;" height="120px;">
    </a>
  </div>
  <div class="media-body media-middle">
    <div class="item_list_info pname"><a href="../customer/noticeDetail.do?seq=${n.seq }" id="nDetail" target="_blank">${n.pname }</a></div>
	<div class="item_list_info price">${n.price }원</div>
  </div>
</div> 
</c:forEach> 
</ul>

	<p>${pg } / ${finalPage } Page</p>
	<!-- paging -->
<nav class="pg">
<ul class="pagination">
	<c:if test="${sPage!=1 }">
	    <li>
	      <a href="../customer/notice.do?pg=${sPage-1}&f=${field}&q=${query}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	</c:if>
	<c:if test="${sPage==1 }">
		<li class="disabled">
	      <span>
	        <span aria-hidden="true">&laquo;</span>
	      </span>
	    </li>
    </c:if>
    
	<c:forEach var="num" begin="${sPage }" end="${sPage+4}">
		<c:if test="${num<=finalPage }">
			<c:if test="${num==pg }">
				<li class="active"><a>${num}</a></li>
			</c:if>
			<c:if test="${num!=pg }">
				<li><a href="../customer/notice.do?pg=${num }&f=${field}&q=${urlQuery}">${num }</a></li>
			</c:if>
		</c:if>
	</c:forEach>
	
	<c:if test="${sPage+4<finalPage}">
	    <li>
	      <a href="../customer/notice.do?pg=${sPage+5}&f=${field}&q=${urlQuery}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	</c:if>
 	<c:if test="${sPage+4>=finalPage}">
		<li class="disabled">
	      <span>
	        <span aria-hidden="true">&raquo;</span>
	      </span>
	    </li>
    </c:if> 
  </ul>
</nav>	


	
	<div class="footMenu"><a class="btn btn-primary" href="../customer/noticeReg.do">글쓰기</a> <a class="btn btn-primary" href="../joinus/index.do">메인으로</a></div>

</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>