<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10元商店-購物車</title>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding:15px">
	<form class="pure-form" method="post">
		<fieldset>
			<legend>10元商店-購物車</legend>
			編號:<input type="text" name="id" value="${ user.id }" readonly>
			<p />
			禮品:
			<c:forEach var="d" items="${data}">
				<!-- ${ d }: -->
				<c:forEach var="p" items="products">
					
					<!-- 
					<c:if test="<boolean>" var="<string>" scope="<string>"> ...</c:if> 
					test:条件
					var:用于存储条件结果的变量
					scope:var属性的作用域
					-->
					
					<c:if test="${ p.id eq d}">
						${p.name} 
					</c:if>
					
				</c:forEach>
			
			</c:forEach>
			<p />
			<button type="submit" class="pure-button pure-button-primary">結帳</button>
			<button type="button" 
					onclick="location.href'${pageContext.request.contextPath}/form.index.jsp'"
					class="pure-button pure-button-primary">繼續購物</button>
			<button type="button" 
					onclick="location.href'${pageContext.request.contextPath}/servlet/cart?type=2'"
					class="pure-button pure-button-primary">查詢訂單紀錄</button>
			
		</fieldset>
	</form>

</body>
</html>