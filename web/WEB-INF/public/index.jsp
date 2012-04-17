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
		dojo.require("dojox.xml.parser");
		
		dojo.ready(function(){
		   // Parse text and generate an XML DOM
		   var xml = "<tnode><node>Some Text</node><node>Some Other Text</node></tnode>";
		   var dom = dojox.xml.parser.parse(xml);
		
		   // Walk DOM and attach into the display how many child nodes were parsed out.
		   var ap = dojo.byId("xmlContent");
		   var docNode = dom.documentElement;
		   ap.appendChild(document.createTextNode("Document contains: " + docNode.childNodes.length + " elements"));
		   ap.appendChild(document.createElement("br"));
		   ap.appendChild(document.createElement("br"));
		
		   // Write text content into the display.
		   for(var i = 0; i < docNode.childNodes.length; i++){
			 ap.appendChild(document.createTextNode("Element: [" + i + "] contains text: " + 
			 									dojox.xml.parser.textContent(docNode.childNodes[i])));
			 ap.appendChild(document.createElement("br"));
		   }
		
		   // Write out the XML text obtained from converting the DOM back.
		   ap.appendChild(document.createElement("br"));
		   ap.appendChild(document.createTextNode("Document XML: " + dojox.xml.parser.innerXML(docNode)));
		   ap.appendChild(document.createElement("br"));
		   ap.appendChild(document.createElement("br"));
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


            <div id="xmlContent"></div>


            <div class="content">
                <div class="recofday1"><s:text name="recofday.message"/></div>

                <s:property value="recipe.html" escape="false" />
            </div>
        </div>

        <div class="hFooter"></div>
    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp" />    
</body>
</html>