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
<script type="text/javascript">
$(document).ready(function(){
	$("#btn_sub").click(function(){				
		if($("#name").val()==""){
			alert("이름을 입력하세요.");

		}else if($("#email").val()==""){
			alert("이메일을 올바르게 입력하세요.");

		}else if($("#mid").val()==""){
			alert("아이디를 올바르게 입력하세요.");
		}else{
				$.ajax({
		        url: "findId.do",
		        data:{"name":$("#name").val(),"email":$("#email").val(),"mid":$("mid").val()},
		        type: 'POST',
		        async:'false',
		        dataType:"json",
		        error : function(error) {
	        		$("#modalName").text("");
	        		$("#modalId").text("");
					$("#modalPwd").text("");
					$("#modalRegdate").text("");	
		        	$("#modalId").text("등록된 정보가 존재하지 않습니다.");
		        	
		        },
		        success: function(res) {
		        		$("#modalName").text("이름 : "+res["name"]);
		        		$("#modalId").text("아이디 : "+res["mid"]);
						$("#modalPwd").text("패스워드 : "+res["pwd"]);
						$("#modalRegdate").text("가입일 : "+res["regdate"]);	
		        	
		        }
			});
				$("#myModal").modal("show");
		}
	})
});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="body">
	<form class="form-horizontal" action="findPwd.do" method="post">
	  <div class="form-group">
	    <label for="mid" class="col-sm-2 control-label">ID</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" id="mid" name="mid" placeholder="아이디">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="name" class="col-sm-2 control-label">이름</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="name" name="name" placeholder="이름">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="email" class="col-sm-2 control-label">이메일</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="email" name="email" placeholder="이메일">
	    </div>
	  </div>	
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-default" id="btn_sub">비밀번호 찾기</button>
	    </div>
	  </div>
	</form>
	
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호 찾기</h4>
      </div>
      <div class="modal-body">
        <span id="modalName"></span><br>
        <span id="modalId"></span><br>
        <span id="modalPwd"></span><br>
        <span id="modalRegdate"></span>
      </div>
      <div class="modal-footer">
     	<a class="btn btn-default" href="#" data-dismiss="modal">닫기</a>
        <a class="btn btn-primary" href="../joinus/login.do">로그인</a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>