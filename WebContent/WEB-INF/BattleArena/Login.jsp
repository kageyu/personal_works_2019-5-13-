<%@page import = "javax.servlet.http.HttpSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BattleArenaログイン</title>
</head>
<body>

<%--
out.print("Hello World!");
--%>


<h1>ログイン</h1>
<form action="./Login" method="post">
<dt>メールアドレス:</dt>
<dd><input type="text" name="mail" /></dd>
<br>
<dt>パスワード:</dt>
<dd><input type="text" name="password" /></dd>
<p><input type="submit" name="submit" value="ログイン" /></p>
</form>

<%--
<form action="./Insert" method="post"><p><input type="submit" name="submit" value="新規登録" /></p></form>
--%>

</body>
</html>