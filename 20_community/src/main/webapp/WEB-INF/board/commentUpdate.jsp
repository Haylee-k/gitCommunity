<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../mall/main_top.jsp" %>

<style>
.table table-hover{
	width : 60%;
	height : 60%;
	margin : auto;
}
.table{
	width:70%;
	margin : auto;
}
.err{
	font-size: 9pt;
	color: red;
	font-weight: bold;
	}
.table-dark{
	width:70%;
	margin : auto;
}
.table-info{
	width:70%;
	margin : auto;
}
.table-light{
	width:70%;
	margin : auto;
}
.table-active{
	width:70%;
	margin : auto;
}
</style>

<!-- 띄기! -->
<div class="mid_main">
</div>

<div class="main">
</div>

<div class="row">
<div class="col col-md-2" ></div>
<div class="col-md-8" >


<form name="cupdate" method="POST" action="CommentUpdate.bd" >
<input type="hidden" name="renum" value="${comment.renum }">
<input type="hidden" name="bdnum" value="${comment.bdnum }">
<input type="hidden" name="pageNumber" value="${pageNumber }"> 

<table align="center" class="table-active" border="1">

<tr> 
    <td>
    	<br>
		글번호 : ${comment.renum } <br> 
       	작성자 : ${comment.writer } <br>
      <fmt:parseDate var="parseDate" value="${comment.reg_date }"  pattern="yyyy-MM-dd HH:mm" />
      <fmt:formatDate var="newDate" value="${parseDate }" pattern="yyyy-MM-dd HH:mm" />
      	작성일 : ${newDate } <br>
      <textarea class="form-control" id="exampleTextarea" rows="3" name="content">  ${comment.content }  </textarea>	
      
      
    <input type="submit" value="댓글수정"	class="btn btn-outline-success" style="height: 50px; width:200px; font-size: 18px;" > 
	<input type="button" value="댓글삭제"	class="btn btn-outline-danger" style="height: 50px; width:200px; font-size: 18px;"
						OnClick="location.href='CommentDelete.bd?bdnum=${article.num}&pageNumber=${pageNumber}&&renum=${comment.renum }'"> 
	    <br><br>
	</td>  
</tr>

</table>
</form> 


<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<%@ include file="../mall/main_bottom.jsp" %>
