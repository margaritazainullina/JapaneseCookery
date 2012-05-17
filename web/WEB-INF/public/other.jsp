<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Клуб рецептов японской кулинарии</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <style type="text/css">
            @import "http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dijit/themes/claro/claro.css";
        </style>
        <script type="text/javascript">
            var dojoConfig = { async: true, tlmSiblingOfDojo: false, parseOnLoad: true, 
                packages:[{location: "../dijit", name: "dijit"},{location: "../dojox",name: "dojox"},{location: ".", name: "dojo"},                        
                    { location: "/cook/js/recipies", name: "recipies" }]};
        </script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dojo/dojo.js"></script>  
        <script type="text/javascript"> 
            require( ["dojo/_base/array","dojo/domReady!"],
            function(array) {
                var arr = <s:property value="jsonIdCategory"/>;
                console.log(arr);
                if (arr != ""){
                    array.forEach(arr, function(entry, i){
                        require(["recipies/showRecipies","dojox/xml/parser","dojo/_base/xhr","dojo/dom",
                            "dojo/dom-construct", "dojo/dom-class"],
                        function(showRecipies, parser, xhr, dom, domConstruct, domClass) {
                            xhr.get({
                                url: "/cook/getByIdRecipeXML.action", content: { id: entry.id },
                                load: function(response) {
                                    var domDoc = parser.parse(response);
                                    
                                    var node = domConstruct.create("div", {
                                        category: entry.category
                                    }, null);
                                    domClass.add(node, "recipeClass");
                                    
                                    var start = response.indexOf("<image>", 0) + 7;
                                    var stop = response.indexOf("</image>", start);
                                    var imageStr = response.substr(start, stop - start);
                                    showRecipies.bar(domDoc, node, imageStr);
                                    domConstruct.place(node, dom.byId("xmlContent"));
                                    return response;
                                },
                                error: function(response) {
                                    console.log("failed xhrGet", response);
                                    return response; //always return the response back
                                },  preventCache: true
                            });
                        });  
                    }
                );
                } else {
                    require(["dojo/dom-construct", "dojo/dom"],
                    function(domConstruct, dom) {
                        domConstruct.create("div",{innerHTML: "Увы, ни одного рецепта из категории 'Разное' нет в базе."},dom.byId("xmlContent"));
                    });
                }
            });           
        </script>
    </head>
    <body>
        <div class="main">
            <div class="maintitle" align="center"><s:text name="welcome.message"/></div>
            <hr/>
            <div class="statusbar">
                <s:if test="#session.user">
                    <s:text name="hello.message"/><s:property value="#session.user.firstName"/>!        
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
            <div id="hint" class="hint">На сайте онлайн: гостей, зарегистрированные пользователи</div>
            <div class="content">
                <h4 class="title">Другие</h4> 
                <div id="xmlContent"></div>
            </div>
        </div>
    </body>
</html>