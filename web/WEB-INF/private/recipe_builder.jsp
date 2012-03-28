<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Конструктор рецептов</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <style> .errorMessage{ color:red; }</style>
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
                    <li><s:url id="url_complete" namespace="/private" action="recipeProduction" method="complete"/>
                    <s:a href="%{url_complete}">Сохранить рецепт</s:a> </li>
                <li><s:url id="url_delete" namespace="/private" action="recipeProduction" method="delete"/>
                    <s:a href="%{url_delete}">Удалить рецепт</s:a>
                    </li>
                    <li><s:url id="url_cook" namespace="/private" action="recipeProduction" method="cook"/>
                    <s:a href="%{url_cook}">К разделу "готовка"</s:a></li>
                <li><s:url id="url_image" namespace="/private" action="recipeProduction" method="image"/>
                    <s:a href="%{url_image}">Добавить фото</s:a> </li>
                </ul> 
                <div class="content">
                    <div class="title">Конструктор рецептов</div>
                <s:if test="#session.xpath=='root/info'">
                    <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                        <s:textfield name="recipeName" value="" label="Название"/>
                        <s:textarea name="info" rows="2" cols="60" value="" label="Краткая информация" />
                        <s:select list="categories" name="category" label="Выберите категорию рецепта" />
                        <s:submit value="Добавить" />
                    </s:form>
                </s:if>
                <s:if test="#session.xpath=='root/prepare'">
                    <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                        <s:label value="Ингридиенты:" />
                        <s:select list="{'чайная ложка', 'столовая ложка', 'грамм'}" 
                                  name="unit" label="Единица измерения" />
                        <s:textfield label="количество" name="amount" value=""/>
                        <s:textarea name="text" rows="2" cols="60" value="" />
                        <s:submit value="Добавить" />
                    </s:form>
                </s:if>            
                <s:if test="#session.xpath=='root/cook'">
                    <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                        <s:label value="Приготовление" />
                        <s:textarea name="text" rows="4" cols="60" value="" />
                        <s:submit value="Добавить" />
                    </s:form>
                </s:if> 
                <s:if test="#session.xpath=='root/image'">
                    <s:form action="imageUpload" method="post" namespace="private" enctype="multipart/form-data" >
                        <s:label value="Добавьте общее фото рецепта" />
                        <s:file name="image" label="Фото" />
                        <s:submit />
                    </s:form> 
                </s:if>             
            </div>
            <br/>
            <hr/>

            <div id="recipeDisplay" align="center">
                <div class="title">Как будет отображаться рецепт</div>
                <s:action executeResult="true" name="recipePreview" />
                <s:if test="#session.recipe.isPhotoAdded">
                    <h4>Отображение фото рецепта</h4>
                    <s:url id="url" namespace="/private" action="recipeShowPhoto" /> 
                    <img src="<s:property value='#url'/>" width="160"/>
                </s:if>
            </div>
            <s:debug />
        </div>

        <div class="hFooter"></div>
    </div>
    <div class="footer"><hr/>
        <div class="divfooter"><h4>©<s:text name="footer1.message"/><br/><s:text name="footer2.message"/></h4></div>
    </div>         
</body>
</html>