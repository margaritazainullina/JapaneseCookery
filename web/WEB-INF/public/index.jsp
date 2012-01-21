<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Клуб рецептов японской кулинарии</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    </head>
    <body>
        <div class="main">
            <s:a href="index"><div class="maintitle" align="center">Клуб рецептов японской кулинарии</div></s:a>
            <hr/><s:if test="#session.user">
                <h4>Здравствуйте, <s:property value="#session.user.firstName"/>!</h4>        
            </s:if>
            <s:else>
                Вы не авторизованы
                <s:url id="urlLogin" action="login"/>
                <s:a href="%{urlLogin}">Вход</s:a> /
                <s:url id="urlRegister" action="register"/>
                <s:a href="%{urlRegister}">Регистрация</s:a>
            </s:else>

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

                <hr/>
            <s:a href="fake"><div class="menu">Супы</div></s:a>
            <s:a href="fake"><div class="menu">Лапша</div></s:a>
            <s:a href="fake"><div class="menu">Суши/Роллы</div></s:a>
            <s:a href="fake"><div class="menu">Другие блюда</div></s:a>
            <s:a href="fake"><div class="menu">Дессерты</div></s:a>
            <s:url id="url" action="index" namespace="/private" />
            <div class="menu"><s:a href="%{url}">Мой профиль</s:a><br/></div>
                <div class="hint">А знаете ли вы, что ....</div>

                <div class="content">
                    <h2>Рецепт дня:</h2>
                    <div class="title">Роллы с тунцом</div> 
                <s:url id="maguro" namespace="/" action="showImage"> 
                    <s:param name="src">maguro.jpg</s:param>
                </s:url>
                <img src="<s:property value='#maguro'/>" width="550" height="341" alt="Роллы с тунцом"/>
                <br/>
                <s:a href="fake">Читать рецепт</s:a>

                </div>
            <s:url id="url" action="register" namespace="/" />
            <s:a href="%{url}">Зарегистрироваться</s:a><br/>
            <s:url id="url" action="login" namespace="/" />
            <s:a href="%{url}">Залогиниться</s:a><br/>

            <s:url id="url" action="index" namespace="/private" />
            <s:a href="%{url}">Зайти в секретную часть</s:a><br/>
            <s:debug/>
            <div class="hFooter"></div>
        </div>
        <div class="footer"><hr/>
            <h4>© Клуб рецептов Зайнуллиной Маргариты. 2011 
                При использовании материалов ссылка на сайт обязательна.</h4>
        </div>       
    </body>
</html>