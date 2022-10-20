<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/mall/main_top.jsp" %>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<div class="main">
</div>
   
        <div class="row">
<div class="col col-md-5" ></div>
<div class="col col-md-2" > <!-- style="border: 1px solid red" -->
<form method="post" action="login.mem" >
  <fieldset>
    <legend>로그인</legend>
         
    <div class="form-group">
    	<label for="id" class="form-label mt-4">id</label>    
      <input type="text" class="form-control" name="id" placeholder="아이디" >
    </div>
            	
    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label mt-4">Password</label>
      <input type="password" class="form-control" name="password" placeholder="비밀번호">
    </div>
    <br>
    <div class="d-grid gap-2">
	  <button class="btn btn-lg btn-secondary" type="submit" style="height: 50px; width: 295px; font-size: 16px;">로그인</button>
	</div>

  </fieldset>
</form>

</div>
<div class="col-md-5" ></div>
</div>
    