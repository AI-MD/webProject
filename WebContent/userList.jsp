<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
	<input type="button" value="�α׾ƿ�"  onclick="javascript:location.href('./logout.do')"/>
	
	<table >
		<tr bgcolor="#abcdef">
			<th>���̵�</th>
			<th>����Ʈ</th>
			<th>����</th>
		</tr>
		<jl:forEach var="ut" items="${ul}" varStatus="vs">
			<tr bgcolor="${vs.index%2!=0?'#aabbcc':'#ccbbaa'} ">
				<td>${ut.userId}</td>
				<td>${ut.point}</td>
				<td>
					<jl:if test="${ut.auth eq '0001'}">������</jl:if>
					<jl:if test="${ut.auth eq '0002'}">����ȸ��</jl:if>
					<jl:if test="${ut.auth eq '0003'}">��ȸ��</jl:if>
				</td>
			</tr>
		</jl:forEach>
	</table>
	<br />
	
	
</body>
</html>


