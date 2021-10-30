<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart 10元商店-訂單紀錄</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding:15px">
	<form class ="pure-form"> 
		<fieldset>
			<legend>10元商店-訂單紀錄</legend>
			編號: <input type="text" name="id" value="${ user.id }" readonly="readonly" ><p/>
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>id</th><th>userId</th><th>productId</th><th>productName</th><th>ts</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${ orders }">
						<c:if test="${ p.id eq o.productId }">
									
						</c:if>
					
					</c:forEach>
				</tbody>
			</table>			
		</fieldset>
	</form>

</body>
</html>