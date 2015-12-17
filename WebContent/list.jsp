<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>

	</head>
<body>
	<input type="button" value="로그아웃"  onclick="javascript:location.href('./logout.do')"/>
	<input type="button" value="유저목록 "  onclick="javascript:location.href('./userList.do')"/>
	count ${count} 
		 
	<table >
		<tr bgcolor="#abcdef">
			<th>번호</th>
			
			<th>제목 (ip) (파일명)</th>
			<th>글쓴이</th>
			<th>글쓴시간</th>
			<th>조회수</th>
			<th>삭제</th>
			<th>수정</th>
			<th>추천</th>
			<th>추천수</th>
		</tr>
		<jl:forEach var="t" items="${l}" varStatus="vs">
			<tr bgcolor="${vs.index%2!=0?'#aabbcc':'#ccbbaa'} ">
				<td>${t.no}</td>
				
				<td><a href="view.do?no=${t.no}">${t.title}</a> [${t.clientIp}] 
				<a href="file_down.do?no=${t.no}&ofn=${t.ofn}&fsn=${t.fsn}">${t.ofn}</a></td>
				<td>${t.userId}<img src="images/${t.photo}" width="20%"/></td>
				<td>${t.theTime}</td>
				<td>${t.count}</td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="board_delete.do?no=${t.no}&cp=${cp}">[삭제]</a></jl:if></td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="board_update.do?no=${t.no}&cp=${cp}">[수정]</a></jl:if></td>
				<td><a href ="like.do?no=${t.no}&cp=${cp}" >좋아요</a></td>
				<td>${t.recommand}</td>
			</tr>
			
		</jl:forEach>
	</table>
	<br />
	<p style="margin-left: 50px">
	
   	<jl:forEach var="ts"  begin="${1}"  end="${np}">
   	<a href="list.do?cp=${ts}">${ts}</a>
   	&nbsp;
   	</jl:forEach>
   	</p>
   	
	<form method="post" action="list.do" >
	<select name="field">
			<option value="title">제목</option>	
			<option value="text">내용</option>
			<option value="user_Id">글쓴이</option>
			<option value="reText">댓글내용</option>
	</select>
	<input type="text" name="searchword" size="30"/>
	<input type="submit" value="검색"/>
	<input type="Button" value ="글쓰기" onclick="javascript:location.href('./add.do')"/>
	<input type="Button" value ="새로고침" onclick="javascript:location.href('./list.do')"/>
	</form>
	
	
	<!-- 
	<form method="post" action="add2.do" enctype="multipart/form-data">
		<input type="text" name="text" size="82"/>
		<input type="file" name="one" /> 
		<input type="submit" value="전송" />
	</form>
	 -->
	
</body>
</html>


