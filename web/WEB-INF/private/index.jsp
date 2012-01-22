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
                 <s:url id="exit" namespace="/private" action="exit"/>
                 <s:a href="%{exit}"><div class="menu">Выйти</div></s:a>
                
                <s:url id="fake" namespace="/" action="fake"/>
                <s:a href="%{fake}"><div class="menu">Мои рецепты</div></s:a>
               
                <s:url id="url" namespace="/private" action="recipeBuilder"/>
                <s:a href="%{url}"><div class="menu">Добавить рецепт</div></s:a>
                
            <div class="hint">На сайте онлайн: гостей, зарегистрированные пользователи</div>

            <div class="content">
                <div class="title">Мой профиль</div> 
                Имя:<br/>
                Фамилия:<br/>
                e-mail:<br/>
                <s:if test="#session.user.photo">
            <s:url id="url" namespace="/private" action="showPhoto" /> 
            <img src="<s:property value='#url'/>" width="160"/>
        </s:if>
        <s:elseif test="#session.user.sex=='Male'">
            <s:url id="url" namespace="/" action="showImage"> 
                <s:param name="src">boy.jpg</s:param>
            </s:url>
            <img src="<s:property value='#url'/>" width="160"/>
        </s:elseif>
        <s:else>
            <s:url id="url" namespace="/" action="showImage">
                <s:param name="src">girl.jpg</s:param>
            </s:url>
            <img src="<s:property value='#url'/>" width="160"/>
        </s:else>  
                <s:form action="fileUpload" method="post" namespace="private" enctype="multipart/form-data" >
                    <s:file name="userImage" label="Ваше фото" />
                    <s:submit />
                </s:form> 
                <br/>
            </div>
            <div class="hFooter"></div>
        </div>
        <div class="footer"><hr/>
            © Клуб рецептов Зайнуллиной Маргариты. 2011 
            При использовании материалов ссылка на сайт обязательна.</h4>
    </div>
</body>
</html>
