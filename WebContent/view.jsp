<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
	<input type="button" value="로그아웃"  onclick="javascript:location.href('./logout.do')"/>
	
	<table >
			<tr>
				<td>번호</td><td>${bvo.no}</td><td>${bvo.title}</td>
			</tr>
			<tr>
				<td>내용</td><td colspan="2">${bvo.text}</td>
			</tr>	
			<tr>
				<td>아이디 </td>
				<td>${bvo.userId}</td>
				<td> [${bvo.clientIp}]  ${bvo.theTime}</td>
				
			</tr>
			<tr>
				<td>첨부파일 </td>
				<td> <a href="file_down.do?no=${bvo.no}&ofn=${bvo.ofn}&fsn=${bvo.fsn}">${bvo.ofn}</a> </td> 
				<td> <jl:if test="${bvo.userId eq id || uvo.auth eq '0001'}">글<a href="board_delete.do?no=${bvo.no}">[삭제]</a></jl:if></td> 
			</tr>
	</table>
	<br />
	댓글 
	<table >
		<tr >
			<th>번호</th>
			<th>글쓴이</th>
			<th>내용</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<jl:forEach var="t" items="${rls}" varStatus="vs">
			<tr >
				<td>${t.no}</td>
				<td>${t.userId}</td>
				<td>${t.text}</td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="reply_delete.do?no=${t.no}&board_no=${bvo.no}">[삭제]</a></jl:if></td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="reply_update.do?no=${t.no}&board_no=${bvo.no}">[수정]</a></jl:if></td>
			</tr>
		</jl:forEach>
	</table>
	
	<form method="POST" action ="reply_insert.do">
	<input type="text" name="text"size="50"/>
	<input type="hidden" name="board_no" value="${bvo.no}" />
	<input type="submit" value="입력">	
	</form>
	
	
</body>
</html>


