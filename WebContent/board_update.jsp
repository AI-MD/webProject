<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
글수정
<form method="post" action="update.do?cp=${cp}" >
	<table>
		<tr>
			<td>글번호</td>
			<td>${vo.no } <input type="hidden" value="${vo.no }" name="no"/></td>	
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" size="82"/></td>	
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="60" rows="30" name="text"></textarea></td>
		</tr>
		<tr>
			<td rowspan="2"><input type="submit" value="전송" /></td>
		</tr>
	</table>
	</form>
	
	
</body>
</html>


