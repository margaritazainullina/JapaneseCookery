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
            var dojoConfig = {
                async: true, 
                baseUrl: "/cook/js/",
                tlmSiblingOfDojo: false,
                parseOnLoad: true, 
                packages: [
                    { name: "dojo", location: "//ajax.googleapis.com/ajax/libs/dojo/1.7.2/" },
                    { name: "recipies", location: "./recipies" }
                ]
            };
        </script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dojo/dojo.js"
                data-dojo-config="async: true">
        </script>        
        <script type="text/javascript">
            require(
            ["dojo/on", "dojo/mouse", "recipies/show", "dojo/domReady!"],
            function(on, mouse, show) {
                on(document.getElementById("foo"), mouse.enter, function(evt){ show.bar(); });
            }
            );            
        </script>         

        <%--script type="text/javascript">
            // загрузить модуль
            dojo.require("dtdg.ShowRecipies");
            dojo.require("dojox.xml.parser");
            dojo.addOnLoad(function() {
                dojo.xhrGet({
                    url : "getAjaxXML.action",  //the relative URL
                    load : function(response, ioArgs) {
                        console.log("successful xhrGet", response, ioArgs);
                        console.log(response);
                        var str = response.toString();
                        //var arr = new Array('[' + str + ']');
                        console.log("str = " + str);
                        
                        
                        dojo.byId("foo").innerHTML = response;
                        return response; //always return the response back
                    },
                    error : function(response, ioArgs) {
                        console.log("failed xhrGet", response, ioArgs);
                        return response; //always return the response back
                    }
                });
            });                
        </script--%>        
    </head>
    <body>
        <div id="foo"></div>
    </body>
</html>