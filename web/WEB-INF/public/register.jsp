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
            <div class="maintitle" align="center">Клуб рецептов японской кухни</div>
            <hr/>
            <div class="statusbar">

                <s:if test="#session.user">
                    <s:text name="hello.message"/> <s:property value="#session.user.firstName"/>!        
                </s:if>
                <s:else>
                    <s:text name="notauthorized.message"/>
                    <s:url id="urlLogin" action="login" namespace="/"/>
                    <s:a href="%{urlLogin}"><s:text name="entry.message"/></s:a> /
                    <s:url id="urlRegister" action="register" namespace="/"/>
                    <s:a href="%{urlRegister}"><s:text name="register.message"/></s:a>
                </s:else>

                <div class="language">
                    <s:text name="language.message"/>
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
            </div>
            <hr/>
            <br/>
            <div class="content1">
                Введите данные для регистрации <br/>
                <s:actionerror theme="xhtml" />
                <s:form action="record_user" namespace="/" name="record_user" label="Авторизация" theme="simple">
                    <table>
                        <tr>
                            <td><label for="firstName">Имя</label></td>
                            <td> <s:textfield name="firstName" theme="xhtml" maxLength="50" size="20" /></td>
                        </tr>
                        <tr> 
                            <td> <label for="lastName">Фамилия</label> </td>
                            <td> <s:textfield name="lastName" theme="xhtml" maxLength="50" size="20" /></td>
                        </tr>
                        <tr> 
                            <td> <label for="email">E-mail</label> </td>
                            <td> <s:textfield name="email" theme="xhtml" maxLength="50" size="20" /></td>
                        </tr>
                        <tr> 
                            <td> <label for="password">Пароль</label> </td>
                            <td> <s:password name="password" theme="xhtml" showPassword="true" maxLength="50" size="20"/></td>
                        </tr>
                        <tr> 
                            <td> <label for="sex">Пол</label> </td>
                            <td> <s:radio name="sex" list="{'Male', 'Female'}" theme="xhtml" /></td>
                        </tr>
                        <tr>
                            <td> <s:submit type="submit" value="OK"/></td>
                            <td><s:reset type="button" key="reset" value="Очистить" />
                            </td>
                        </tr>
                    </table>
                </s:form> 
            </div>
            </div>    
             <div class="hFooter"></div>
    </div>
    <div class="footer1"><hr/>
        <div class="divfooter"><h4>©<s:text name="footer1.message"/><br/><s:text name="footer2.message"/></h4></div>
    </div>
    </body>        
</html>