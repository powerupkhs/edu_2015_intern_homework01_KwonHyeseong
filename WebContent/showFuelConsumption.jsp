<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>차량별 연료 소비량</title>
</head>
<body>
	<table>
		<tr>
			<td align="center" width="750"><h4>< 차량별 연료소모량 ></h4></td>
		</tr>
	</table>
	
	<table border="1">
		<tr height="40">
			<th>차량타입</th>
			<th>차량번호</th>
			<th>차량연비</th>
			<th>운행거리</th>
			<th>연료소모량</th>
		</tr>
		<c:forEach var="carFuelConsumptions" items="${carFuelConsumptions}">
			<tr height="40">
				<td align="center" width="150">${carFuelConsumptions.key.type}</td>
				<td align="center" width="150">${carFuelConsumptions.key.number}</td>
				<td align="center" width="150">${carFuelConsumptions.key.info.fuelEfficiency}</td>
				<td align="center" width="150">${carFuelConsumptions.value.driveDistance}</td>
				<td align="center" width="150">${carFuelConsumptions.value.fuelConsumption}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
