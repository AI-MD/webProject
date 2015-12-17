<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<html>

<body>

	
	<div  style="background:#abc; margin-top: 300px;"  >
		
		<form method="POST" action="login2.do">
			<p align="right" style="margin-right: 30px">
			UserId &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="userId" size="12"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
			PasssWrod <input type="password" name="passwd"size="12"/>
			
			
			<input type="submit" value="LOGIN" height="100%"/>
			<input type="button" value="회원가입"  onclick="javascript:location.href('./reg.do')"/>
			</p>
		</form>
		
	</div>
</body>
</html>