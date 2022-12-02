<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
    
<div class="container">
<input type = "hidden" id = "num" name = "num" value = "${board.num}"/>
		<div class="form-group">
			<label for="title">제목 :</label> <input type="text"
				class="form-control" id="title" value = "${board.title}" name="title">
		</div>

		<div class="form-group">
			<label for="email">글쓴이 :</label> <input type="text"
				class="form-control" id="writer" value = "${board.writer}" name="writer" 
				readonly = "readonly">
		</div>
		<div class="form-group">
			<label for="content">내용 :</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>
		    <button type="button" class="btn btn-primary" id = "btnUpdate">수정</button>    
</div>	

<script>
$("#btnUpdate").click(function(){
	
	data = {
			"num" : $("#num").val(),
			"title" : $("#title").val(),
			"content" : $("#content").val(),
	}
	
	$.ajax({
		type : "put",
		url : "/app08/board/update",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify(data)
	})
	.done(function(){
		alert("수정 성공")
		location.href = "/app08/board/list"
	})
	.fail(function(e){
		alert("수정 실패 : " +e)
	})
})
</script>

<%@ include file = "../includes/footer.jsp" %>	    