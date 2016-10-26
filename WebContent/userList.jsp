<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
	<input type="button" value="로그아웃"  onclick="javascript:location.href('./logout.do')"/>
	
	<table >
		<tr bgcolor="#abcdef">
			<th>아이디</th>
			<th>포인트</th>
			<th>권한</th>
		</tr>
		<jl:forEach var="ut" items="${ul}" varStatus="vs">
			<tr bgcolor="${vs.index%2!=0?'#aabbcc':'#ccbbaa'} ">
				<td>${ut.userId}</td>
				<td>${ut.point}</td>
				<td>
					<jl:if test="${ut.auth eq '0001'}">관리자</jl:if>
					<jl:if test="${ut.auth eq '0002'}">감사회원</jl:if>
					<jl:if test="${ut.auth eq '0003'}">정회원</jl:if>
				</td>
			</tr>
		</jl:forEach>
	</table>
	<br />
	
	
</body>
</html>


