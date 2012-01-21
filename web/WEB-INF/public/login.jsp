<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Регистрация</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    </head>
    <body>
       <div class="main">
            <s:a href="index"><div class="maintitle" align="center">Клуб рецептов японской кулинарии</div></s:a>
            <hr/><s:if test="#session.user">
                Здравствуйте, <s:property value="#session.user.firstName"/>!        
            </s:if>
            <s:else>
                Вы не авторизованы
                <s:url id="urlLogin" action="login"/>
                <s:a href="%{urlLogin}">Вход</s:a> /
                <s:url id="urlRegister" action="register"/>
                <s:a href="%{urlRegister}">Регистрация</s:a>
            </s:else>
            <div class="language">
                Язык:
                <s:url id="url" action="index">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English /</s:a>
                <s:url id="url" action="index">
                    <s:param name="request_locale">ru</s:param>
                </s:url>
                <s:a href="%{url}">Русский /</s:a>
                <s:url id="url" action="index">
                    <s:param name="request_locale">jp</s:param>
                </s:url>
                <s:a href="%{url}">日本語</s:a>
                </div>
                <hr/>
            <div class="content">
            Авторизуйтесь, пожалуйста!<br/>
        <s:form action="authentication" namespace="/" name="authorization" label="Авторизация">
            <s:actionerror />
            <s:textfield name="email" label="e-mail"/>
            <s:password name="password" label="пароль"/>
            <s:submit value="Войти"/>
        </s:form>   
            </div>
            <div class="hFooter"></div>
        </div>
        <div class="footer"><hr/>
            <h4> © Клуб рецептов Зайнуллиной Маргариты. 2011 </br>
                При использовании материалов ссылка на сайт обязательна.</h4>
        </div>
    </body>        
</html>
