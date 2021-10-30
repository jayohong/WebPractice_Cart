<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart login</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding:15px">
	<form class="pure-form" method="post">
		<fieldset>
			<legend>10元商店 login</legend>
			帳號: <input type="text" name="name" var=""><p />
			密碼: <input type="text" name="password" var=""><p />
			<p/>
			<button type="submit" class="pure-button pure-button-primary">登入</button>
		</fieldset>
	</form>
</body>
</html>