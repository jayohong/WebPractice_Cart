<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Cart 10元商店</title>

<!-- 
  在<link>标签中，“rel=stylesheet”，rel是关联的意思，关联的是一个样式表(stylesheet)文档，
  它表示这个link在文档初始化时将被使用。
  Link标签有两个作用： 1. 定义文档与外部资源的关系；   2. 是链接样式表。  
  href 属性用于指定超链接目标的 URL
  -->

<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>

<!-- <style> 用來設定 HTML 文件的樣式，在 <style> 裡面你可以寫 CSS 來排版瀏覽器該怎麼渲染你的頁面。 -->
<!-- CSS padding : 內距的設定, padding 是不可以設定負值的 -->
<body style="padding: 15px">

	<form class="pure-form" method="post"
		action="${pageContext.request.contextPath}/servlet/cart">
		<fieldset>
			<legend>10元商店-請選擇禮品</legend>

			<!-- name是控制元件的名稱(多個控制元件可以取同一個名稱),value是控制元件的值; -->
			編號:<input type="text" name="id" value="${user.id}"
				readonly="readonly"> <!-- readonly不可修改 -->
			<p />
			
			禮品:
			<!-- var代表当前条目的变量名称  items要被循环的信息 -->
			
			<!--
			流程說明 
			1 開啟index.jsp
			2.被PathRedirectFilter攔截並重導至/servlet/cart(CartController(get方式))
			3 因為不帶參數所以攜帶products資料再重導回index.jsp(此時才有products的資料)
			 -->
			<c:forEach var="p" items="${ products }">
				<input type="checkbox" name="data" value="${ p.id }"> ${ p.name }
			</c:forEach>
			<p />
			<button type="submit" class="pure-button pure-button-primary">加入購物車</button>
			<button type="button"
				onclick="location.href='${pageContext.request.contextPath}/servlet/cart?type=1'"
				class="pure-button pure-button-primary">查詢購物車</button>
			<button type="button"
				onclick="location.href='${pageContext.request.contextPath}/servlet/cart?type=2'"
				class="pure-button pure-button-primary">查詢訂單紀錄</button>
			<button type="button"
				onclick="location.href='${pageContext.request.contextPath}/servlet/logoout'"
				class="pure-button pure-button-primary">登出</button>
		</fieldset>

	</form>

</body>
</html>