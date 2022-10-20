<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../mall/main_top.jsp"%>

<style>
.table-active{
	width : 30%;
	margin : auto;
}
	.err{
	font-size: 9pt;
		color: red;
	font-weight: bold;
	}
</style>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<div class="main">
</div>
   
<!--         <div class="row">
<div class="col col-md-4" ></div>
<div class="col col-md-6" > -->


<form:form commandName="board" method="post" action="insert.bd" enctype="multipart/form-data">
      	<input type="hidden" name="writer" value=${loginInfo.id }>

      	
<table align="center" width="600" border="2" class="table-active">	
	<tr class="table-light">
		<td align="center" colspan="2" >
			<br>
			<h4>글쓰기</h4>
		</td>
	</tr>
	
	<tr >
		<td>			
    <div class="form-group">
      <label for="sports" class="form-label mt-4">말머리</label>
      	</td>
      	<td>
      <select class="form-select" id="sports" name="sports">
      	<option value="">선택하세요</option>
        <option value="구기종목" <c:if test="${board.sports eq '구기종목'}"> selected </c:if>>구기종목</option>
        <option value="수영" <c:if test="${board.sports eq '수영'}"> selected </c:if>> 수영</option>
        <option value="헬스" <c:if test="${board.sports eq '헬스'}"> selected </c:if>> 헬스</option>
        <option value="등산" <c:if test="${board.sports eq '등산'}"> selected </c:if>> 등산</option>
        <option value="자전거" <c:if test="${board.sports == '자전거'}"> selected </c:if>> 자전거</option>
		<option value="다른운동" <c:if test="${board.sports == '다른운동'}"> selected </c:if>> 다른운동</option>      
      </select>
   	<form:errors cssClass="err" path="sports"/>	
    </div>	
		</td>
	</tr>	

	<tr>
		<td>		
<div class="form-group">
  <label class="col-form-label mt-4" for="writer">작성자</label>
       	</td>
      	<td>
      	${loginInfo.id }
  <%-- <input type="text" class="form-control" placeholder="작성자" name="writer" id="writer" value="${board.writer }"> --%>
</div>	
		</td>
	</tr>
	
	<tr>
		<td>		
<div class="form-group">
  <label class="col-form-label mt-4" for="subject">제목</label>
      	</td>
      	<td>
  <input type="text" class="form-control" placeholder="제목" name="subject" id="subject" value="${board.subject }">
	<form:errors cssClass="err" path="subject"/>	
</div>	
		</td>
	</tr>
	
	<tr>
		<td>			
    <div class="form-group">
      <label for="exampleInputEmail1" class="form-label mt-4">Email </label>
      	</td>
      	<td>
      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" 
      			name="email"  value="${board.email }" >
	<form:errors cssClass="err" path="email"/>	
    </div>
		</td>
	</tr>	

	<tr>
		<td>	
    <div class="form-group">
      <label for="exampleTextarea" class="form-label mt-4">내용</label>
      	</td>
      	<td>
      <textarea class="form-control" id="exampleTextarea" rows="3" name="content" >${board.content }</textarea>
	<form:errors cssClass="err" path="content"/>
    </div>	
		</td>
	</tr>	
	
 	<tr>
		<td>	
    <div class="form-group">
      <label for="upload" class="form-label mt-4">그림파일</label>
      	</td>
      	<td>
      <input class="form-control" type="file" name="upload" id="formFile" value=${board.image }>
      <form:errors cssClass="err" path="image"/>
    </div>
 		</td>
	</tr>	 
	
	<tr>
		<td>	   	
    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label mt-4">Password</label>
      	</td>
      	<td>
      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
      		name="passwd"  value="${board.passwd }">
      <form:errors cssClass="err" path="passwd"/>
    </div>
 		</td>
	</tr>	

	<tr class="table-light">
		<td colspan=2 align="right">
		<input type="submit" class="btn btn-outline-primary" value="글쓰기" > 
		<input type="reset"	value="다시작성" class="btn btn-outline-warning"> 
		<input type="button" value="목록보기"	class="btn btn-outline-info"
				OnClick="window.location='list.bd'">
 		</td>
	</tr>	



</table>
</form:form>


<!-- </div>
<div class="col-md-2" ></div>
</div> -->

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>
<%@ include file="../mall/main_bottom.jsp" %>
