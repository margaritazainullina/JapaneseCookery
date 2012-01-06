<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><s:text name="index.message"/></h1>
        <h3>Languages</h3>
        <ul>
            <li>
                <s:url id="url" action="index">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English</s:a>
                </li>

                <li>
                <s:url id="url" action="index">
                    <s:param name="request_locale">ru</s:param>
                </s:url>
                <s:a href="%{url}">Русский</s:a>
                </li>
                <li>
                <s:url id="url" action="index">
                    <s:param name="request_locale">jp</s:param>
                </s:url>
                <s:a href="%{url}">日本語</s:a>
                </li>
            </ul>
        <s:url id="url" action="login" namespace="/" />
        <s:a href="%{url}">Залогиниться</s:a><br/>
        <s:url id="url" action="index" namespace="/private" />
        <s:a href="%{url}">Зайти в секретную часть</s:a><br/>
        <s:debug/>
    </body>
</html>