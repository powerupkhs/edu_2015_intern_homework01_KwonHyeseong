<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>차량별 연료 소비량 계산</title>
</head>
<body>
	<table>
		<tr>
			<td align="center" width="300"><h4>< 차량별 연료소모량 계산 ></h4></td>
		</tr>
	</table>

	<form method="POST" action="/edu_2015_intern_homework01_KwonHyeseong/car">
		<table border="1">
			<tr height="40">
				<th>차량번호</th>
				<th>운행거리</th>
			</tr>
			<c:forEach var="cars" items="${cars}">
				<tr height="40">
					<td align="center" width="150">${cars.key}</td>
					<td align="center"><input type="text" name="${cars.key}" value="0"></td>
				</tr>
			</c:forEach>		
			<tr height="40">
				<td colspan="2" align="center"><input type="submit" value="계산"></td>
			</tr>
		</table>
	</form>
</body>
</html>
