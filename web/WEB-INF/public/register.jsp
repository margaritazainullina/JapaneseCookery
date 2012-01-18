<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Регистрация</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/> 
    </head>
    <body>
        <div class="main">
        <s:if test="#session.user">
            <h4>Здравствуйте, <s:property value="#session.user.firstName"/>!</h4>        
        </s:if>
        <s:else><h4>Вы не авторизованы</h4></s:else>        
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
                
            Введите данные для регистрации <br/><br/>
            <s:form action="register" namespace="/" name="register" label="Авторизация">
                <s:actionerror />
                <s:textfield name="firstName" label="Имя"  maxLength="50" size="20" />
                <s:textfield name="lastName" label="Фамилия"  maxLength="50" size="20" />
                <s:textfield name="email" label="Email" maxLength="50" size="20" />
                <s:password name="password" label="Пароль" showPassword="true" maxLength="50" size="20"/>
                <s:radio name="sex" label="Пол" list="{'Male', 'Female'}" />
                <s:submit value="Зарегистрироваться"/><br/>
                <s:submit type="reset" value="Очистить"/> 
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