<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Авторизуйтесь, пожалуйста!</h1>
        <s:form action="authentication" namespace="/" name="authorization" label="Авторизация">
            <s:actionerror />
            <s:textfield name="email" label="e-mail"/>
            <s:password name="password" label="пароль"/>
            <s:submit value="Войти"/>
        </s:form>
    </body>
</html>
