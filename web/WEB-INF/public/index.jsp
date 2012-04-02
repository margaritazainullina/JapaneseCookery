<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><s:text name="welcome.message"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
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
             
                <s:property value="recipe.html" escape="false" />
            </div>
        </div>

        <div class="hFooter"></div>
    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp" />    
</body>
</html>