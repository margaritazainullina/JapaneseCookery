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
        <s:form theme="xhtml" action="recipeCreate" namespace="/private">
            <s:if test="#session.xpath=='root/info'">
                <s:label value="Заполните info часть рецепта" />
                <s:textarea name="text" rows="4" cols="100" />
                <s:submit value="Добавить" />
            </s:if>
            <s:if test="#session.xpath=='root/prepare'">
                <s:label value="Заполните prepare часть рецепта" />
                <s:label value="Добавьте ингридиент" />
                <s:select list="{'чайная ложка', 'столовая ложка', 'грамм'}" 
                          name="unit" label="Выберите единицу измерения" />
                <s:textfield label="количество" name="amount" />
                <s:textarea name="text" rows="4" cols="100" />
                <s:submit value="Добавить" />
            </s:if>            

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