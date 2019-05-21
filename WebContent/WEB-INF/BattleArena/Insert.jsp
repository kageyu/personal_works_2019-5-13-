
<%@ page import="javax.servlet.http.HttpSession"%>

<%
    HttpSession hs = request.getSession();
    String enterdValue = "";
    String mail = session.getAttribute("mail").toString();
    String password = session.getAttribute("password").toString();
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        enterdValue = "";
    }
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>



<h1>新規ユーザー登録</h1>
<form action="./Insert" method="post">
<dt>メールアドレス:</dt>
<input type="text" name="mail" value="mail"<%  %> />
<br>
<dt>パスワード:</dt>
<input type="text" name="password" value="password"<%  %> />

<p><input type="submit" name="submit" value="登録する" /></p>
</form>




</body>
</html>