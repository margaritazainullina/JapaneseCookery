<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><s:text name="welcome.message"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <style type="text/css">
            @import "http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dijit/themes/claro/claro.css";
        </style>
        <script type="text/javascript">
            var dojoConfig = { async: true, tlmSiblingOfDojo: false, parseOnLoad: true, 
                packages: [
                    { location: "../dijit", name: "dijit" },
                    { location: "../dojox", name: "dojox" },
                    { location: ".",        name: "dojo" },                        
                    { location: "/cook/js/recipies", name: "recipies" }
                ]
            };
        </script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dojo/dojo.js"
                data-dojo-config="async: true">
        </script>     
        <script type="text/javascript"> 
            <s:if test="#attr.recipe">
                require(
                ["dojo/dom-construct", "dojo/dom", "dojox/xml/parser", "recipies/showRecipies", "dojo/domReady!"],
                function(domConstruct, dom, parser, showRecipies) {
                    var xml = '<s:property value="recipe.xml" escape="false"/>';
                    var domDoc = parser.parse(xml);
                    var start = xml.indexOf("<image>", 0) + 7;
                    var stop = xml.indexOf("</image>", start);
                    var imageStr = xml.substr(start, stop - start);
                    var node = domConstruct.create("div");
                    showRecipies.bar(domDoc, node, imageStr);
                    domConstruct.place(node, dom.byId(xmlContent));
                } );           
            </s:if>
            <s:else>
                require(
                ["dojo/dom", "dojo/domReady!"],
                function(dom) {
                    var a = dom.byId("xmlContent");
                    a.appendChild(document.createTextNode("Ни одного рецепта нет в базе"));                
                }                
            );
            </s:else>
        </script>   
        <script type="text/javascript">
            require(["dojo/_base/xhr", "dojo/on", "dojo/dom", "dojo/domReady!"],
            function(xhr, on, dom) {
                function refreshContent() {
                    xhr.get({
                        url: "hint.action",
                        load: function(newContent) {
                            dom.byId("a1").innerHTML = newContent;
                        },
                        error: function() {},
                        preventCache: true
                    });
                }
                refreshContent();
                on(dom.byId("a1"), "click", refreshContent);
            });
        </script>
    </head>
    <body>
        <div class="main">
            <div class="maintitle" align="center"><s:text name="welcome.message"/></div>
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
            <s:url id="recipesByCategory" namespace="/" action="recipesByCategory"/>
            <ul class="menu">
                <li><s:a href="%{recipesByCategory}?category=soup"><s:text name="soup.message"/></s:a></li>
                <li><s:a href="%{recipesByCategory}?category=noodles"><s:text name="noodles.message"/></s:a></li>
                <li><s:a href="%{recipesByCategory}?category=sushi"><s:text name="sushi.message"/></s:a></li>
                <li><s:a href="%{recipesByCategory}?category=other"><s:text name="other.message"/></s:a></li>
                <li><s:a href="%{recipesByCategory}?category=desserts"><s:text name="desserts.message"/></s:a></li>
                <li><s:url id="url" action="index" namespace="/private" />
                    <s:a href="%{url}"><s:text name="profile.message"/></s:a>
                </li>
            </ul> 
            <div id="a1" class="hint"></div>
            <div class="content">
                <div class="recofday1"><s:text name="recofday.message"/></div>
                <div id="xmlContent"></div>

            </div>
        </div>
        <div class="hFooter"></div>
    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp" />    
</body>
</html>