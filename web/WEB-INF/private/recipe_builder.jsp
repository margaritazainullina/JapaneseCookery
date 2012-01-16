<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Конструктор рецептов</title>
    </head>
    <body>
        <h1>Конструктор рецептов</h1>
        <s:form action="recipeCreate" namespace="/private">
            <s:if test="#session.recipe==null">
                <s:label value="Заполните info часть рецепта" />
                <s:hidden name="operation" value="info"/>
            </s:if>
            <s:textarea name="text" rows="4" cols="100" />
            <s:submit value="Добавить" />
        </s:form>
        
        <s:url id="url_delete" namespace="/private" action="recipeCreate" method="delete"/>
        <s:a href="%{url_delete}">Удалить рецепт и вернуться в свой профиль</s:a>
        

        
        <div id="recipeDisplay">
            <h2>Отображение рецепта</h2>
            <s:if test="#session.recipe">
                <s:label value="%{#session.recipe.xml}" />
            </s:if>
            <s:else>
                <s:label value="Рецепт пустой" />
            </s:else>
        </div>
    </body>
</html>