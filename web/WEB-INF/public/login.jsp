<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body>
        <s:if test="#session.user">
            <h4>Здравствуйте, <s:property value="#session.user.firstName"/>!</h4>        
        </s:if>
        <s:else><h4>Вы не авторизованы</h4></s:else>
        <h1>Авторизуйтесь, пожалуйста!</h1>
        <s:form action="authentication" namespace="/" name="authorization" label="Авторизация">
            <s:actionerror />
            <s:textfield name="email" label="e-mail"/>
            <s:password name="password" label="пароль"/>
            <s:submit value="Войти"/>
        </s:form>
    </body>
</html>
