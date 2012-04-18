<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><s:text name="welcome.message"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dojoroot/dojo/dojo.js"
            djConfig="isDebug:true" data-dojo-config="parseOnLoad: true"></script>
        <script type="text/javascript">
            // загрузить модуль
            dojo.require("dtdg.Recipe");
            dojo.require("dojox.xml.parser");
            // обеспечить безопасное обращение к dtdg.Genie внутри addOnLoad
            dojo.addOnLoad(function() {
                var dom1 = dojox.xml.parser.parse('<s:property value="recipe.xml" escape="false"/>');
                // Walk DOM and attach into the display how many child nodes were parsed out.
                var divNode = dojo.byId("xmlContent");
                var docNode1 = dom1.documentElement;
                divNode.appendChild(document.createTextNode("Document contains: " + docNode1.childNodes.length + " elements"));
                
                var schema = {
                    rowtag: "contact",
                    columns: [
                        { tagname: "@name", label: "Name" },
                        { tagname: "email", label: "Address" }
                    ]
                };
                var xmlStr = '<?xml version="1.0"?>' +
                                '<contacts>' +
                                '<contact name="Able Baker"><email>able@example.com</email></contact>' +
                                '<contact name="Careful Dodger"><email>dodger@example.com</email></contact>' +
                                '<contact name="Eager Framer" personal="true"><email>framer@example.com</email>' +
                                '</contact>' +
                                '</contacts>';  // Read the XML data
                var xmldoc = dojox.xml.parser.parse(xmlStr);           
                dtdg.Recipe(xmldoc, schema, "addresses");  // Convert to an HTML table
              //dtdg.Recipe();
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
            <s:url id="fake" namespace="/" action="fake"/>
            <ul class="menu">
                <li><s:a href="%{fake}"><s:text name="soup.message"/></s:a></li>
                <li><s:a href="%{fake}"><s:text name="noodles.message"/></s:a></li>
                <li><s:a href="%{fake}"><s:text name="sushi.message"/></s:a></li>
                <li><s:a href="%{fake}"><s:text name="other.message"/></s:a></li>
                <li><s:a href="%{fake}"><s:text name="desserts.message"/></s:a></li>
                <li><s:url id="url" action="index" namespace="/private" />
                    <s:a href="%{url}"><s:text name="profile.message"/></s:a></li>
                </ul> 

                <div class="hint"><s:text name="hint.message"/></div>
                
            <div class="content">
                <div class="recofday1"><s:text name="recofday.message"/></div>
                <div id="addresses"></div>
                <div id="xmlContent"></div>
                <%--s:property value="recipe.html" escape="false" /--%>
            </div>
        </div>
        <div class="hFooter"></div>
    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp" />    
</body>
</html>