<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Клуб рецептов японской кулинарии</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    </head>
    <body>
        <div class="main">
            <div class="maintitle" align="center"><s:text name="welcome.message"/></div>
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
                
                <ul class="menu">
                <li><s:url id="main" namespace="/" action="index"/>
            <s:a href="%{main}">Главная</s:a> </li>
                <li><s:url id="exit" namespace="/private" action="exit"/>
            <s:a href="%{exit}">Выйти</s:a></li>
                <li><s:url id="url" namespace="/private" action="recipeBuilder"/>
            <s:a href="%{url}">Добавить рецепт</s:a></li>
                 </ul>    
                <div class="hint">На сайте онлайн: гостей, зарегистрированные пользователи</div>

                <div class="content">
                    <div class="title">Мои рецепты</div> 
                <s:form theme="xhtml" action="myRecipies" namespace="/private">
                    <s:select list="categories" name="category" label="Выберите категорию рецепта" emptyOption="false" />
                    <s:submit value="Показать" />
                </s:form>
                <div>
                    <ul>
                        <s:iterator value="recipies" var="recipe">
                            <li><s:property value="html" escape="false" /></li>
                            <hr size="1px" />
                        </s:iterator>
                    </ul>
                </div>
            </div>
            <div class="hFooter"></div>
        </div>
        <div class="footer"><hr/>
            © Клуб рецептов Зайнуллиной Маргариты. 2011 - 2012 
            При использовании материалов ссылка на сайт обязательна.</h4>
    </div>
</body>
</html>