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
		</td>
	</tr>	

	<tr>
		<td align="center" colspan="5" height=300>			
			${article.content }
		</td>
	</tr>
	
	<tr>
		<td align="center" colspan="5" >			
			<img height=100 width=100 
				src="<%=request.getContextPath()%>/resources/${article.image }">
			
		</td>
	</tr>	
	


	<tr class="table-light">
		<td colspan=5 align="right">
			<input type="button" value="글수정"	class="btn btn-outline-success" OnClick="location.href='update.bd?num=${article.num}&pageNumber=${pageNumber}&memberId=${loginInfo.id }'">
			<input type="button" value="글삭제"	class="btn btn-outline-danger" OnClick="location.href='delete.bd?num=${article.num}&pageNumber=${pageNumber}&memberId=${loginInfo.id }'">
			<input type="button" value="답글쓰기"	class="btn btn-outline-warning" OnClick="window.location='reply.bd?num=${article.num}&pageNumber=${pageNumber}'">
			<input type="button" value="글목록"	class="btn btn-outline-info" OnClick="window.location='list.bd?pageNumber=${pageNumber}'">

 		</td>
	</tr>	



</table>
</form>



</div>
<!-- <div class="col-md-4" ></div> -->
</div>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<%@ include file="../mall/main_bottom.jsp" %>
