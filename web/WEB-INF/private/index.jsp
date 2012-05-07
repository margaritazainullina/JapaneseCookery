<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><s:text name="welcome.message"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dojoroot/dojo/dojo.js"
            djConfig="isDebug:true" data-dojo-config="parseOnLoad: true">
        </script>
        <script type="text/javascript">
            // загрузить модуль
            dojo.require("dtdg.ShowRecipies");
            dojo.require("dojox.xml.parser");
            dojo.addOnLoad(function() {
                dojo.xhrGet({
                    url : "getAjaxXML.action",  //the relative URL
                    load : function(response, ioArgs) {
                        console.log("successful xhrGet", response, ioArgs);
                        console.log(response);
                        dojo.byId("foo").innerHTML = response;
                        return response; //always return the response back
                    },
                    error : function(response, ioArgs) {
                        console.log("failed xhrGet", response, ioArgs);
                        return response; //always return the response back
                    }
                });
            });                
        </script>        
    </head>
    <body>
        <div id="foo"></div>
    </body>
</html>