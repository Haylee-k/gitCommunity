<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin/main_top.jsp" %>    
<%@ include file="../common/common.jsp" %>

<style>
.table{
	width:70%;
}
</style>

<!-- 일단 어드민이 확인할수있는 걸로 옮길건데.. 우선 여기에.. -->
<div class="mid_main">
</div>

<div class="main">
</div>

        <div class="row">
<div class="col col-md-2" ></div>


<table class="table table-hover" border="1">
	 <tr class="table-warning">
		<th>번호</th>
		<th>이름</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>휴대전화</th>
		<th>주소</th>
		<th>포인트</th>


<c:forEach var="list" items="${list}">
	 <tr class="table-light">
		<td>${list.num }</td>
		<td>${list.name }</td>
		<td>${list.id }</td>
		<td>${list.password }</td>
		<td>${list.email }</td>
		<td>${list.hp }</td>
		<td>${list.zipcode1 } ${list.zipcode2 } ${list.zipcode3 } ${list.zipcode4 }</td>
		<td>${list.mpoint }</td>
	</tr>
</c:forEach>	
</table>


</div>
</div>