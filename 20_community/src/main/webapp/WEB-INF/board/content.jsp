<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/mall/main_top.jsp"%>


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
.table-Secondary{
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

<!--    제목
작성자 아이피? |시간 |조회 |추천|댓글
작성내용
추천? 
댓글 -->
<form>    	
<table align="center" class="table table-hover">	
	<tr class="table-light">
		<td align="center" colspan="5" >
			<br>
			<h4>${article.subject }</h4>
		</td>
	</tr>
	
	<tr > 
		<td align="center" colspan="1" >			
			글번호 : ${article.num } 
		</td>
		<td align="center" colspan="1" >			
			작성자 : ${article.writer }	 	 
		</td>
		<td align="center" colspan="1" >	
			작성일 : 		
			<fmt:parseDate var="formatDate" value="${article.reg_date }" pattern="yyyy-MM-dd HH:mm" />
			<fmt:formatDate var="newDate" value="${formatDate }" pattern="yyyy-MM-dd HH:mm"/>
			${newDate }
		</td>
		<td align="center" colspan="1" >			
			조회수 : ${article.readcount }  
		</td>
<!-- 		<td align="center" colspan="1" >			
			추천수 :  ? 
		</td> -->
		<td align="center" colspan="1" >			
			댓글수 :	? 
			
<!-- //이거는 그러면 테이블을 하나 더 만들고 댓글을 하나 추가할때마다 댓글수가 늘어나도록 했어야 하는듯? 그래야 제목옆에도 댓글수?를 달수있고..-->
		</td>
	</tr>	

	<tr>
		<td align="center" colspan="5" height=300>			
			${article.content }
		</td>
	</tr>
	
	<tr>
		<td align="center" colspan="5" >			
			<img height=300 width=600 
				src="<%=request.getContextPath()%>/resources/${article.image }">
			
		</td>
	</tr>	
	


	<tr class="table-light">
		<td colspan=5 align="right">
			<input type="button" value="글수정"	class="btn btn-outline-success" OnClick="location.href='update.bd?num=${article.num}&pageNumber=${pageNumber}&memberId=${loginInfo.id }'">
			<input type="button" value="글삭제"	class="btn btn-outline-danger" OnClick="location.href='delete.bd?num=${article.num}&pageNumber=${pageNumber}&memberId=${loginInfo.id }'">
			<input type="button" value="답글쓰기"	class="btn btn-outline-warning" OnClick="window.location='reply.bd?num=${article.num}&pageNumber=${pageNumber}&memberId=${loginInfo.id }'">
			<input type="button" value="글목록"	class="btn btn-outline-info" OnClick="window.location='list.bd?pageNumber=${pageNumber}'">

 		</td>
	</tr>	



</table>
</form>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<form name="re" method="post" action="comment.bd">
<input type="hidden" name="bdnum" value="${article.num }">
<input type="hidden" name="writer" value="${loginInfo.id }">
<input type="hidden" name="pageNumber" value="${pageNumber }">

<table align="center" class="table-Secondary">
<tr> 
    <td>
      <textarea class="form-control" id="exampleTextarea" rows="3" name="content"> </textarea>	
	</td>
</tr>
<tr> 	
	<td align="right">
		<input type="submit" value="댓글작성"	class="btn btn-lg btn-outline-dark" style="height: 50px; width:200px; font-size: 18px;"> <!-- 	OnClick="window.location=''" -->
	</td>
</tr>

</table>
</form>


<form >
<table align="center" class="table-light" >

<c:forEach var="comment" items="${lists }" >

<tr> 
    <td>

       ${comment.renum } <br> 
       ${comment.writer } <br>
      <fmt:parseDate var="parseDate" value="${comment.reg_date }"  pattern="yyyy-MM-dd HH:mm" />
      <fmt:formatDate var="newDate" value="${parseDate }" pattern="yyyy-MM-dd HH:mm" />
       ${newDate } <br>
      <h6><b> ${comment.content } </b></h6>
    <input type="button" value="댓글수정"	class="btn btn-outline-success" 
    		OnClick="location.href='CommentUpdate.bd?bdnum=${article.num}&pageNumber=${pageNumber}&renum=${comment.renum }&memberId=${loginInfo.id }'" > 

	<input type="button" value="댓글삭제"	class="btn btn-outline-danger" 
			OnClick="location.href='CommentDelete.bd?bdnum=${article.num}&pageNumber=${pageNumber}&&renum=${comment.renum }&memberId=${loginInfo.id }'">
     	 <hr>
	</td>  
</tr>

</c:forEach>
</table>
</form>



</div>
<!-- <div class="col-md-4" ></div> -->
</div>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<%@ include file="../mall/main_bottom.jsp" %>
