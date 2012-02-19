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
        <h1>Конструктор рецептов</h1>
        <s:if test="#session.xpath=='root/info'">
            <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                <s:label value="Заполните info часть рецепта" />
                <s:textfield name="name" value="" label="Название"/>
                <s:textarea name="info" rows="4" cols="100" value="" label="Краткая информация" />
                <s:select list="categories" name="category" label="Выберите категорию рецепта" />
                <s:submit value="Добавить" />
            </s:form>
        </s:if>
        <s:if test="#session.xpath=='root/prepare'">
            <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                <s:label value="Заполните prepare часть рецепта" />
                <s:label value="Добавьте ингридиент" />
                <s:select list="{'чайная ложка', 'столовая ложка', 'грамм'}" 
                          name="unit" label="Выберите единицу измерения" />
                <s:textfield label="количество" name="amount" value=""/>
                <s:textarea name="text" rows="4" cols="100" value="" />
                <s:submit value="Добавить" />
            </s:form>
        </s:if>            
        <s:if test="#session.xpath=='root/cook'">
            <s:form theme="xhtml" action="recipeProduction" namespace="/private">
                <s:label value="Заполните cook часть рецепта" />
                <s:label value="Добавьте step" />
                <s:textarea name="text" rows="4" cols="100" value="" />
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
        <s:url id="url_cook" namespace="/private" action="recipeProduction" method="cook"/>
        <s:a href="%{url_cook}">Перейти к разделу "готовка"</s:a>

        <s:url id="url_image" namespace="/private" action="recipeProduction" method="image"/>
        <s:a href="%{url_image}">Добавить фото к рецепту</s:a>        

        <s:url id="url_complete" namespace="/private" action="recipeProduction" method="complete"/>
        <s:a href="%{url_complete}">Сохранить рецепт и вернуться в свой профиль</s:a>        

        <s:url id="url_delete" namespace="/private" action="recipeProduction" method="delete"/>
        <s:a href="%{url_delete}">Удалить рецепт и вернуться в свой профиль</s:a>
        <div id="recipeDisplay">
            <h4>Отображение рецепта</h4>
            <s:action executeResult="true" name="recipePreview" />
            <s:if test="#session.recipe.isPhotoAdded">
              <h4>Отображение фото рецепта</h4>
              <s:url id="url" namespace="/private" action="recipeShowPhoto" /> 
             <img src="<s:property value='#url'/>" width="160"/>
            </s:if>
        </div>
        <s:debug />
    </body>
</html>