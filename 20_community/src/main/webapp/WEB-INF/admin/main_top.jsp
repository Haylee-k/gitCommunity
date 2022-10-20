<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<style>
 #navbar {  
	height: 50px;  
 }  
 .d-flex{
	padding-top: 20px;
    padding-bottom: 5px;
 }
 .mid_main{
	height: 150px;  
 }
 .container {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
}
hr {
    background:pink;
    height:1px;
    border:0;
}
</style>    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

   <!--부트와치 부트스트랩-->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/minty/bootstrap.min.css" integrity="sha384-H4X+4tKc7b8s4GoMrylmy2ssQYpDHoqzPa9aKXbDwPoPUA3Ra8PA5dGzijN+ePnH" crossorigin="anonymous">

</head>

<body id="page-top">

<c:if test="${loginInfo.id eq null }">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="main.ad">Move up</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarColor02">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="main.ad">Home
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">동호회</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">운동 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="main.ad">공지사항</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="list.mem">회원목록</a>
        </li>        
      </ul>
      
      <form class="d-flex">
        <input class="form-control me-sm-2" type="text" placeholder="Search">
        <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
      </form>
        <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="register.mem"> 회원가입 </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.mem"> 로그인 </a>
        </li>
      	</ul>
    </div>
  </div>
</nav>
</c:if>


<c:if test="${loginInfo.id ne null }">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="main.mall">Move up</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarColor02">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="main.ad">Home
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="list.bd">동호회</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">운동 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="main.ad">공지사항</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="list.mem">회원목록</a>
        </li>        
    </ul>
    
      <form class="d-flex">
        <input class="form-control me-sm-2" type="text" placeholder="Search">
        <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
      </form>
        <ul class="navbar-nav me-auto">
        <c:if test="${loginInfo.id eq 'admin'}">
        	<li class="nav-item">
    	      	<a class="nav-link" href="main.mall">사용자 홈</a>
	        </li>
        </c:if>
        <li class="nav-item">
          ${loginInfo.id }님 &nbsp;|&nbsp;
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp"> 로그아웃 </a>
        </li>
      	</ul>
    </div>
  </div>
</nav>
</c:if>