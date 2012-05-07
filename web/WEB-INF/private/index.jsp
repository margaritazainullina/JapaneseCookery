<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Клуб рецептов японской кухни</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
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
            <ul class="menu">
                <li><s:url id="exit" namespace="/private" action="exit"/>
                    <s:a href="%{exit}">Выйти</s:a></li>
                <li><s:url id="myRecipies" namespace="/private" action="myRecipies"/>
                    <s:a href="%{myRecipies}">Мои рецепты</s:a>
                </li>
                <li><s:url id="url" namespace="/private" action="recipeBuilder"/>
                    <s:a href="%{url}">Добавить рецепт</s:a>
                </li>
            </ul>
            <div class="hint">На сайте онлайн: гостей, зарегистрированные пользователи</div>

            <div class="content">
                <div class="title">Мой профиль</div> 
                Имя: <s:property value="#session.user.firstName"/><br/>
                Фамилия: <s:property value="#session.user.lastName"/><br/>
                e-mail: <s:property value="#session.user.email"/><br/>
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
            <div class="divfooter"><h4>©<s:text name="footer1.message"/><br/><s:text name="footer2.message"/></h4></div>
        </div>
    </body>
</html>
