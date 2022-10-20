<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../mall/main_top.jsp" %>


<script>

	function insert(){
		//alert("2");
		location.href = "insert.bd";
	}
	
</script>

<style>
.table{
	width:70%;
	margin : auto;
}
.table table-hover{
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

   
   
  <table class="table table-hover" >
  	<tr >
  		<td align="left" >
  			<br>
  			<h5>글목록(전체 글: ${pageInfo.totalCount} )</h5>
  		</td>
  		<td align="center" >
<form class="d-flex" action="list.bd" method="post">
   <select name="whatColumn">
      <option value="">전체검색</option>
      <option value="subject">글제목</option>
      <option value="writer">작성자</option>
   </select>
   
   <input class="form-control me-sm-2" name="keyword" type="text" placeholder="Search" style="height: 40px; width: 295px; font-size: 16px;" >
   <input class="btn btn-outline-primary" type="submit" value="Search">
</form>
  		</td>
  		<td align="right" >
  		  	<br>
  			<button  type="button" class="btn btn-outline-secondary" onclick="insert()">추가하기</button>
  		</td>
  	</tr>
  </table>	
 <table class="table table-hover" >
    <tr class="table-active">
      <th scope="col">번호</th>
      <th scope="col">말머리</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회</th>
      <!-- <th scope="col">추천</th> -->    
    </tr>

  <c:forEach var="board" items="${lists}" >
    <tr >
       <td>${board.num }</td>
      <td>${board.sports }</td>
      <td>
      	<c:if test="${board.re_level >0 }">
      		<c:set var="width" value="${board.re_level * 20}"/>
      		<img src="./resources/images/level.gif" width="${width}" height="15">	
 			<span class="badge bg-info" >re</span>
      	</c:if>
      		<a href ="content.bd?num=${board.num}&pageNumber=${pageInfo.pageNumber}">
      			${board.subject }
      			<c:if test="${board.readcount >= 25}">
      				<span class="badge bg-danger" >hot</span>
      			</c:if>
      		</a>	
      </td>
      <td>${board.writer }</td>
      <td>
      	<fmt:parseDate var="formattedDay" value="${board.reg_date }" pattern="yyyy-MM-dd"/>
      	<fmt:formatDate var="newDay" value="${formattedDay}" pattern="yyyy-MM-dd"/>
      	${newDay}      	
      </td>
      <td>${board.readcount }</td> 
   
    </tr>
  </c:forEach> 
</table>

<br><br>

<div style="text-align:center">
    <button type="button" class="btn btn-outline-secondary">${pageInfo.pagingHtml }</button>
</div>


</div>
<!-- <div class="col-md-4" ></div> -->
</div>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>
<%@ include file="../mall/main_bottom.jsp" %>
