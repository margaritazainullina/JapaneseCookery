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
                    { location: ".",        name: "dojo"  },                        
                    { location: "/cook/js/recipies", name: "recipies" }]};
        </script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.2/dojo/dojo.js"
                data-dojo-config="async: true">
        </script>     
        <script type="text/javascript">
            require(["dojo/_base/array", "dojo/_base/xhr", "dojo/dom", "recipies/showRecipies"],
            function(array, xhr, dom, showRecipies) {
                xhr.get({
                    url: "getAjaxXML.action",
                    load: function(response) {
                        var arr = response.toString().split(",");
                        array.forEach(arr, function(entry, i){
                            require(["dojox/xml/parser","dojo/_base/xhr","dojo/dom","dojo/dom-construct","dojo/domReady!"],
                            function(parser, xhr, dom, domConstruct) {
                                xhr.get({
                                    url: "getByIdRecipeXML.action", content: { id: entry },
                                    load: function(response) {
                                        var domDoc = parser.parse(response);
                                        var node = domConstruct.create("div");
                                        showRecipies.bar(domDoc, node);
                                        
                                        domConstruct.place(node, dom.byId(xmlContent));
                                        return response;
                                    },
                                    error: function(response) {
                                        console.log("failed xhrGet", response);
                                        return response; //always return the response back
                                    },  preventCache: true
                                });
                            });  
                        });                            
                        return response;
                    },
                    error: function(response) {
                        console.log("failed xhrGet", response);
                        return response; //always return the response back
                    }, preventCache: true
                });
            }
        );  
        </script>         
    </head>
    <body>
        <div id="xmlContent" />
    </body>
</html>