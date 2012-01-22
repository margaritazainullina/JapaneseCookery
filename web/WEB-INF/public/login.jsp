<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Регистрация</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <style>.errormessage {color: red;}</style>
    </head>
    <body>
        <div class="main">
            <div class="maintitle" align="center">Клуб рецептов японской кулинарии</div>
            <hr/><s:if test="#session.user">
                Здравствуйте, <s:property value="#session.user.firstName"/>!        
            </s:if>
            <s:else>
                Вы не авторизованы
                <s:url id="urlLogin" action="login" namespace="/"/>
                <s:a href="%{urlLogin}">Вход</s:a> /
                <s:url id="urlRegister" action="register" namespace="/"/>
                <s:a href="%{urlRegister}">Регистрация</s:a>
            </s:else>
            <div class="language">
                Язык:
                <s:url id="url" action="index" namespace="/">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English /</s:a>
                <s:url id="url" action="index" namespace="/">
                    <s:param name="request_locale">ru</s:param>
                </s:url>
                <s:a href="%{url}">Русский /</s:a>
                <s:url id="url" action="index" namespace="/">
                    <s:param name="request_locale">jp</s:param>
                </s:url>
                <s:a href="%{url}">日本語</s:a>
            </div>
            <hr/>
            <div class="content">
                Авторизуйтесь, пожалуйста!<br/>

                <s:form action="authentication" namespace="/" name="authorization" label="Авторизация" theme="simple">
                    <s:actionerror /><table>
                        <tr>
                            <td><label for="email">e-mail</label></td>
                            <td><s:textfield name="email" id="e-mail"/></td>
                        </tr>
                        <tr> 
                            <td> <label for="password">Пароль</label> </td>
                            <td><s:password name="password" id="password"/></td>
                        </tr>
                        <tr> 
                            <td rowspan="2"> <s:submit value="Войти"/></td>
                        </tr>
                    </table>
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
