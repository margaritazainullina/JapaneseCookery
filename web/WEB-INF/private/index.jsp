<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Мой аккаунт</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h3>Hello <s:property value="#session.user.firstName" /></h3>
        <s:debug/>
    </body>
</html>
