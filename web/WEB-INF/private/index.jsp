<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Мой аккаунт</title>
    </head>
    <body>
        <s:if test="#session.user">
            <h4>Здравствуйте, <s:property value="#session.user.firstName"/>!</h4>        
        </s:if>
        <h1>Рита, что будешь делать дальше?</h1>
        <s:form action="fileUpload" method="post" namespace="private" enctype="multipart/form-data" >
            <s:file name="userImage" label="Ваше фото" />
            <s:submit />
        </s:form> 
        Uploaded Image:
        <br/>
        <s:if test="#session.user.photo">
            <s:url id="url" namespace="/private" action="showPhoto" /> 
            <img src="<s:property value='#url'/>" width="160"/>
        </s:if>
        <s:else>
            <s:url id="url_default" namespace="/" action="showImage" > 
                <s:param name="src">boy.jpg</s:param>
            </s:url>
            <img src="<s:property value='#url_default'/>" width="160"/>
        </s:else>            
        <s:debug/>
    </body>
</html>