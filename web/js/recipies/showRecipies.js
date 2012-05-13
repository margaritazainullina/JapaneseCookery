define(["dojo/domReady!"],
    function() {
        return {
            bar : function(domDoc, node) {
                require(["dojo/_base/array", "dojo/dom-construct"], 
                    function(array, domConstruct){
                        var table = domConstruct.create("table", null ,node);
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("name")[0].firstChild.data
                        }, domConstruct.create("tr", null, table));
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("info")[0].firstChild.data
                        }, domConstruct.create("tr", null, table));
                        domConstruct.create("td", { 
                            innerHTML: "Ингредиенты",
                            className: "recipetitle2"
                        }, domConstruct.create("tr", null, table));
                        array.forEach(domDoc.getElementsByTagName("ingredient"), function(entry, i){
                            domConstruct.create("td", {                            
                                innerHTML: entry.firstChild.data
                            }, domConstruct.create("tr", null, table));
                        });
                        domConstruct.create("td", {                            
                            innerHTML: "Готовка"
                        }, domConstruct.create("tr", null, table));
                        array.forEach(domDoc.getElementsByTagName("step"), function(entry, i){
                            domConstruct.create("td", {                            
                                innerHTML: entry.firstChild.data
                            }, domConstruct.create("tr", null, table));
                        });
                        domConstruct.create("td", {                            
                            innerHTML: "Фото"
                        }, domConstruct.create("tr", null, table));
                    });  
            }
        }
    }
    )