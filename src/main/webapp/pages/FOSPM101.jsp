<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="heimlich" uri="appCommonURL"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HEIMLICH</title>
<script type="text/javascript" language="javascript"></script>
</head>
<body>
	<table>
		<tr>
			<td>
				<heimlich:checkboxTag category="CustCode" name="dto.CustCode" id="CustCode"/>
			</td>
			<td>
				<heimlich:radioTag category="CustCode" key="year" name="dto.CustCode" id="CustCode" includedKeys="AA"/>						
			</td>
			<td>
				<heimlich:selectTag category="CustCode" name="dto.CustCode" id="CustCode" excludedKeys="AA"/>
			</td>
			<td>
                <heimlich:multipleSelectTag size="5" showKey="Y" category="CustCode" id="list" name="dto.CustCode" style="width: 350px;"/>
            </td>	
		</tr>
	</table>