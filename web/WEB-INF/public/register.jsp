<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
        <h2>Регистрация</h2>
        <s:form action="register" namespace="/" name="register" label="Авторизация">
            <s:actionerror />
            <s:textfield name="email" label="Email"/>
            <s:password name="password" label="Пароль" showPassword="true"/>
            <s:textfield name="firstName" label="Имя"/>
            <s:textfield name="lastName" label="Фамилия"/>
            <s:radio name="sex" label="Пол" list="{'Male', 'Female'}" />
            <s:submit value="Зарегистрироваться"/>
        </s:form>
    </body>
</html>