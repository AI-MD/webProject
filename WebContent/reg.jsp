
<%@page contentType="text/html; charset=utf-8"
		pageEncoding="euc-kr" %>
<html>
<body>

	ȸ�� ���� �Է¾��<br/>
	<form method="POST" action="reg_add.do" id="joinForm1"  name="joinForm1">
		ID  :<input type="text" name="userId"  /><br/>
		��ȣ :<input type="password" name="passwd" /><br/>
		��ȣȮ�� :<input type="password" name="passwd1" /><br/>
		�̸� :<input type="text" name="userName" /><br/>
		<select name="photo">
			<option value="apple.png">�������</option>	
			<option value="banana.png">�ٳ�������</option>
			<option value="orange.png">����������</option>
			<option value="kiwi.png">Ű������</option>
		</select><br/>
		<input type="button" value="����" onclick="formChk();"/>
	</form>

<script language='javascript'>
function formChk(){
    if(document.joinForm1.userId.value==''){
      alert("id�� �Է��ϼ���!!");
      document.joinForm1.userId.focus();
      return;
    }else if(document.joinForm1.passwd.value==''){
      alert("password�� �Է��ϼ���!!");    
      document.joinForm1.passwd.focus();
      return;
    }else if(document.joinForm1.passwd1.value!=document.joinForm1.passwd.value){
        alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.!!");    
        document.joinForm1.passwd1.focus();
        return;
      }else if(document.joinForm1.userName.value==''){
        alert("�̸��� �Է��ϼ���!!");    
        document.joinForm1.userName.focus();
        return;
      }else{
       document.joinForm1.submit(); 
    }
    }
</script>
</body>
</html>



