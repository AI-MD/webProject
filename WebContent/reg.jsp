
<%@page contentType="text/html; charset=utf-8"
		pageEncoding="euc-kr" %>
<html>
<body>

	회원 가입 입력양식<br/>
	<form method="POST" action="reg_add.do" id="joinForm1"  name="joinForm1">
		ID  :<input type="text" name="userId"  /><br/>
		암호 :<input type="password" name="passwd" /><br/>
		암호확인 :<input type="password" name="passwd1" /><br/>
		이름 :<input type="text" name="userName" /><br/>
		<select name="photo">
			<option value="apple.png">사과사진</option>	
			<option value="banana.png">바나나사진</option>
			<option value="orange.png">오렌지사진</option>
			<option value="kiwi.png">키위사진</option>
		</select><br/>
		<input type="button" value="전송" onclick="formChk();"/>
	</form>

<script language='javascript'>
function formChk(){
    if(document.joinForm1.userId.value==''){
      alert("id를 입력하세요!!");
      document.joinForm1.userId.focus();
      return;
    }else if(document.joinForm1.passwd.value==''){
      alert("password를 입력하세요!!");    
      document.joinForm1.passwd.focus();
      return;
    }else if(document.joinForm1.passwd1.value!=document.joinForm1.passwd.value){
        alert("비밀번호가 일치하지 않습니다.!!");    
        document.joinForm1.passwd1.focus();
        return;
      }else if(document.joinForm1.userName.value==''){
        alert("이름을 입력하세요!!");    
        document.joinForm1.userName.focus();
        return;
      }else{
       document.joinForm1.submit(); 
    }
    }
</script>
</body>
</html>



