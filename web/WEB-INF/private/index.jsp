<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Клуб рецептов японской кулинарии</title>
        <style type="text/css">
            * {margin: 0; padding: 0;} 

            html,body {
                height: 100%; 
                font-family: Verdana; 
                font-size: 11pt;
                background-color: #ffffcc; 
                color: #333; 
            }
            .main { 
                min-height: 100%;
                position: relative;
            }
            * html .main { 
                height: 100%; 
            }
            .hFooter { 
                height: 30px; 
            }
            .footer { 
                height: 30px; 
                margin-top: -30px; 
                color: #999999; 
                font-size: 9pt;
                font-family: Verdana; 
                font-weight: normal;
            }
            .maintitle {
                color: #a52a2a; 
                font-size: 24pt;
                font-family: Monotype Corsiva; 
                font-weight: normal;
                margin: 10px; 
            }
            .menu{
                position: relative;
                border: solid 1px black;
                width: 200px; 
                height: 35px;
                margin: 1px; 
                background: #a52a2a;
                font-size: 14pt;
                font-family: Verdana; 
                color: #ffffcc;
            }
            .hint {
                font-size: 12pt;
                margin: 1px; 
                border: dashed 1px black;
                width: 200px; 
                height: 350px;
            }
            .context{
                font-size: 12pt;
                font-family: Verdana; 
                font-weight: normal;
                position: absolute;
                left: 220px; 
                top:90px;
                margin: 5px;
            }
            .title{
                color:#a52a2a;
                margin: 10px;
                font-size: 20pt;
            }
            a.none{
                text-decoration: none; 
            } </style>
    </head>
    <body>
        <div class="main">
            <s:a href="index"><div class="maintitle" align="center">Клуб рецептов японской кулинарии</div></s:a>
            <hr/> <div class="language">
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
                
                <s:url id="fake" namespace="/" action="fake"/>
                <s:a href="%{fake}"><div class="menu">Мои рецепты</div></s:a>
               
                <s:url id="url" namespace="/private" action="recipeBuilder"/>
                <s:a href="%{url}"><div class="menu">Добавить рецепт</div></s:a>
                
            
            
            <div class="hint">На сайте онлайн: гостей, зарегистрированные пользователи</div>

            <div class="context">
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
