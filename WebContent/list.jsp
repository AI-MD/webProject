<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>

	</head>
<body>
	<input type="button" value="�α׾ƿ�"  onclick="javascript:location.href('./logout.do')"/>
	<input type="button" value="������� "  onclick="javascript:location.href('./userList.do')"/>
	count ${count} 
		 
	<table >
		<tr bgcolor="#abcdef">
			<th>��ȣ</th>
			
			<th>���� (ip) (���ϸ�)</th>
			<th>�۾���</th>
			<th>�۾��ð�</th>
			<th>��ȸ��</th>
			<th>����</th>
			<th>����</th>
			<th>��õ</th>
			<th>��õ��</th>
		</tr>
		<jl:forEach var="t" items="${l}" varStatus="vs">
			<tr bgcolor="${vs.index%2!=0?'#aabbcc':'#ccbbaa'} ">
				<td>${t.no}</td>
				
				<td><a href="view.do?no=${t.no}">${t.title}</a> [${t.clientIp}] 
				<a href="file_down.do?no=${t.no}&ofn=${t.ofn}&fsn=${t.fsn}">${t.ofn}</a></td>
				<td>${t.userId}<img src="images/${t.photo}" width="20%"/></td>
				<td>${t.theTime}</td>
				<td>${t.count}</td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="board_delete.do?no=${t.no}&cp=${cp}">[����]</a></jl:if></td>
				<td><jl:if test="${t.userId eq id || uvo.auth eq '0001'}"><a href="board_update.do?no=${t.no}&cp=${cp}">[����]</a></jl:if></td>
				<td><a href ="like.do?no=${t.no}&cp=${cp}" >���ƿ�</a></td>
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
			<option value="title">����</option>	
			<option value="text">����</option>
			<option value="user_Id">�۾���</option>
			<option value="reText">��۳���</option>
	</select>
	<input type="text" name="searchword" size="30"/>
	<input type="submit" value="�˻�"/>
	<input type="Button" value ="�۾���" onclick="javascript:location.href('./add.do')"/>
	<input type="Button" value ="���ΰ�ħ" onclick="javascript:location.href('./list.do')"/>
	</form>
	
	
	<!-- 
	<form method="post" action="add2.do" enctype="multipart/form-data">
		<input type="text" name="text" size="82"/>
		<input type="file" name="one" /> 
		<input type="submit" value="����" />
	</form>
	 -->
	
</body>
</html>


