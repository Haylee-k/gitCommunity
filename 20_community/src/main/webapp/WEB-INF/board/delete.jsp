<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="./../mall/main_top.jsp" %>

<style>
.err{
	font-size: 9pt;
	color: red;
	font-weight: bold;
	}
</style>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>

 <script>
		
</script> 

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<div class="main">
</div>
   
        <div class="row">
<div class="col col-md-5" ></div>
<div class="col col-md-3" > 



<form name="df" method="post" action="delete.bd?pageNumber=${pageNumber}"  > <!-- onsubmit="deleteCheck()" -->
<input type="hidden" name="num" value="${num}">
	<table>
		<tr>
			<td>
				<h3>
					<small class="text-muted">비밀번호를 입력해주세요.</small>
				</h3>
			</td>
	
		</tr>
		<tr>
			<td>
 				<div class="form-group">
  				    <label for="exampleInputPassword1" class="form-label mt-4">Password</label>
 				     <input type="password" class="form-control" name="password" placeholder="비밀번호"><br>

  				</div>
			</td>
		
		</tr>
		<tr>
			<td>
			    <div class="d-grid gap-2">
	  				<button type="submit" class="btn btn-lg btn-danger"  style="height: 50px; width: 200px; font-size: 16px;">글삭제</button>
	  				<button class="btn btn-lg btn-info" type="button" style="height: 50px; width: 200px; font-size: 16px;" onclick="location.href='list.bd?pageNumber=${pageNumber}'">글목록</button>
				</div>

			</td>
		
		</tr>
	</table>
	
</form>





</div>
<div class="col-md-4" ></div>
</div>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<div class="main">
</div>


    <%@ include file ="./../mall/main_bottom.jsp" %>