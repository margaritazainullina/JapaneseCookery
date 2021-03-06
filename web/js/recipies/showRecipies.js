define(["dojo/domReady!"],
    function() {
        return {
            bar : function(domDoc, node, imageStr) {
                require(["dojo/_base/array", "dojo/dom-construct"], 
                    function(array, domConstruct){
                        var table = domConstruct.create("table", null ,node);
                        // name
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("name")[0].firstChild.data,
                            className: "recipetitle"
                        }, domConstruct.create("tr", null, table));
                        // info
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("info")[0].firstChild.data
                        }, domConstruct.create("tr", null, table));

                        domConstruct.create("td", { 
                            innerHTML: "Ингредиенты",
                            className: "recipetitle2"
                        }, domConstruct.create("tr", null, table));
                        // ingredients
                        array.forEach(domDoc.getElementsByTagName("ingredient"), function(entry, i){
                            var unit = entry.getAttribute("unit");
                            var amount = entry.getAttribute("amount");
                            domConstruct.create("td", {
                                innerHTML: entry.firstChild.data + " " + amount + " " + unit
                            }, domConstruct.create("tr", null, table));
                        });
                        domConstruct.create("td", {
                            innerHTML: "Готовка",
                            className: "recipetitle2"
                        }, 
                        domConstruct.create("tr", null, table));
                        array.forEach(domDoc.getElementsByTagName("step"), function(entry, i){
                            domConstruct.create("td", {
                                innerHTML: entry.firstChild.data
                            }, domConstruct.create("tr", null, table));
                        });
                        var tdPhoto = domConstruct.create("td", null, domConstruct.create("tr", null, table));
                        domConstruct.create("img", {  
                            src: "data:image/jpg/png/gif;base64," + imageStr, 
                            width: "300px"
                        }, tdPhoto);
                     //   domConstruct.create("br");    
                    });  
            }
        }
    })