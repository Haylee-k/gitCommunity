<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/mall/main_top.jsp" %>

<style>
.error{
		color: pink;
		font-size: 15;
}

</style>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	$(document).ready(function(){
		//alert('1')
		
		var isCheck = false;
		var isChange = false;
		var use;
		
		$('#id_check').click(function(){
			//alert('2')
			isCheck = true; //중복체크를 누르면 트루상태
			
			$.ajax({
				url : "idcheck.mem",
				data : ({
					inputid : $("input[name=id]").val()
				}),
				success : function(data){
					if($('input[name="id"]').val()==""){
						$('#idMessage').html("<font color=red>아이디를 입력해주세요</font>");
						$('#idMessage').show();
					}
					else if(data=="YES"){
						$('#idMessage').html("<font color=blue>사용가능한 아이디입니다</font>");
						$('#idMessage').show();
						use = "possible"; //사용가능아이디면 possible상태
						isChange = false; //사용가능아이디면 isChange = false;상태로 유지
					}
					else{
						$('#idMessage').html("<font color=red>이미 등록된 아이디입니다</font>");
						$('#idMessage').show();	
						use = "impossible"; //사용불가능아이디면 impossible상태
					}
				}
			})
		}); //#id_check
		
		$('input[name=id]').keydown(function(){
			$('#idMessage').css('display','none'); //아이디자리에 키를 놓으면 메세지가 사라지도록
			isChange = true;  //아이디자리에 키를 놓으면 isChange = true상태로 바뀜
			use = ""; //사용가능 여부를 다시 알수없게 되기때문에 ""로~
		});
		
		$('btnSubmit').click(function(){
			//alert('3')
			if(use=="impossible"){
				alert('이미 사용중인 아이디입니다.');
				return false;
			}
			else if(isCheck==false || isChange ==true){ //중복체크 누르기 전상태, 키다운해서 아이디수정한 상태
				alert('중복체크를 하세요');
				return false;
			}
		}); //btnSubmit
		
	}); //ready
	
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("zipcode4").value = extraAddr;
                
                } else {
                    document.getElementById("zipcode4").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode1').value = data.zonecode;
                document.getElementById("zipcode2").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("zipcode3").focus();
            }
        }).open();
    }
</script>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>

<div class="main">
</div>
   
        <div class="row">
<div class="col col-md-4" ></div>
<div class="col col-md-4" > <!-- style="border: 1px solid red" -->
<form:form method="post" action="register.mem" commandName="member" >
  <fieldset>
    <legend>회원가입</legend>
        
    <div class="form-group">
      <label for="name" class="form-label mt-4">name</label>
      <input type="text" class="form-control" name="name" id="name" placeholder="Enter name" value="${member.name}">
    	<form:errors cssClass="error" path="name" />
    </div>
    
<%--     <div class="form-group">
      <label for="id" class="form-label mt-4">id</label>
      <input type="text" class="form-control" id="id" placeholder="Enter id" value="${member.id}">
    </div> --%>
	<label for="id" class="form-label mt-4">id<small class="form-text text-muted">아이디는 영문,숫자만 가능하며 4~10자리까지 가능합니다.</small></label>  
    <div class="input-group mb-3">
         
      <input type="text" class="form-control" placeholder="Enter id" name="id" value="${member.id}"
      	aria-label="Recipient's username" aria-describedby="id_check">
      <button class="btn btn-primary" type="button" id="id_check">중복체크</button><br>    	
    </div>
         <span id="idMessage"></span><br>
         <form:errors cssClass="error" path="id" />
          
            	
    <div class="form-group">
      <label for="exampleInputPassword1" class="form-label mt-4">Password<small id="emailHelp" class="form-text text-muted">비밀번호는 영문,숫자만 가능하며 4~10자리까지 가능합니다.</small></label>
      <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password" value="${member.password}">
    	
    	<form:errors cssClass="error" path="password" />
    </div>
        
    <div class="form-group">
      <label for="exampleInputEmail1" class="form-label mt-4">Email address</label>
      <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value="${member.email}">
    	<form:errors cssClass="error" path="email" />
    </div>

    <div class="form-group">
      <label for="hp" class="form-label mt-4">hp</label>
      <input type="text" class="form-control" name="hp" id="hp" placeholder="hp" value="${member.hp}">
    	<form:errors cssClass="error" path="hp" />
    </div>
    
 
 <div class="form-group">  
 <label class="col-form-label mt-4" for="zipcode">주소</label><br> 
<input type="text" class="form-control" name="zipcode1" id="zipcode1" placeholder="우편번호" value="${member.zipcode1}">
<input type="button" class="btn btn-secondary" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
<input type="text" class="form-control" name="zipcode2" id="zipcode2" placeholder="주소" value="${member.zipcode2}">
<input type="text" class="form-control" name="zipcode3" id="zipcode3" placeholder="상세주소" value="${member.zipcode3}">
<input type="text" class="form-control" name="zipcode4" id="zipcode4" placeholder="참고항목" value="${member.zipcode4}">
</div>      
 
 
 <br>
   <div class="d-grid gap-2">
    <button id="btnSubmit" type="submit" class="btn btn-lg btn-primary" >Submit</button>
</div>

  </fieldset>
</form:form>

</div>
<div class="col-md-4" ></div>
</div>

<!-- 띄어쓰기용 div -->
<div class="mid_main">
</div>
<%@ include file="../mall/main_bottom.jsp" %>
