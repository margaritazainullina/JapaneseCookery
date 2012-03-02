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
                <s:a href="%{url}">Русский </s:a>
                <s:url id="url" action="index" namespace="/">
                    <s:param name="request_locale">jp</s:param>
                </s:url>
                <s:a href="%{url}">日本語</s:a>
                </div>
                <hr/>
                 <s:url id="main" namespace="/" action="index"/>
                 <s:a href="%{main}"><div class="menu">Главная</div></s:a>                
                 <s:url id="exit" namespace="/private" action="exit"/>
                 <s:a href="%{exit}"><div class="menu">Выйти</div></s:a>
                <s:url id="url" namespace="/private" action="recipeBuilder"/>
                <s:a href="%{url}"><div class="menu">Добавить рецепт</div></s:a>
                
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
                            <li><s:property value="xml" /></li>
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
