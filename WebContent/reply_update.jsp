<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
��ۼ���
<form method="post" action="update2.do" >
	<table>
		<tr>
			<td>��۹�ȣ</td>
			<td>${no} <input type="hidden" value="${no}" name="no"/><input type="hidden" value="${board_no}" name="board_no"/></td>	
		</tr>
		<tr>
			<td>����</td>
			<td><input type="text" name="text" size="82"/></td>	
		</tr>
		
		<tr>
			<td rowspan="2"><input type="submit" value="����" /></td>
		</tr>
	</table>
	</form>
	
	
</body>
</html>


