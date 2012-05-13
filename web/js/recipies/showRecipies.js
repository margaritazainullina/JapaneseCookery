define(["dojo/domReady!"],
    function() {
        return {
            bar : function(domDoc, node, imageStr) {
                require(["dojo/_base/array", "dojo/dom-construct"], 
                    function(array, domConstruct){
                        var table = domConstruct.create("table", null ,node);
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("name")[0].firstChild.data
                        }, domConstruct.create("tr", null, table));
                        domConstruct.create("td", {                            
                            innerHTML: domDoc.getElementsByTagName("info")[0].firstChild.data
                        }, domConstruct.create("tr", null, table));
                        domConstruct.create("td", { innerHTML: "Ингредиенты"
                        }, domConstruct.create("tr", null, table));
                        array.forEach(domDoc.getElementsByTagName("ingredient"), function(entry, i){
                            domConstruct.create("td", { innerHTML: entry.firstChild.data
                            }, domConstruct.create("tr", null, table));
                        });
                        domConstruct.create("td", { innerHTML: "Готовка" }, 
                            domConstruct.create("tr", null, table));
                        array.forEach(domDoc.getElementsByTagName("step"), function(entry, i){
                            domConstruct.create("td", { innerHTML: entry.firstChild.data
                            }, domConstruct.create("tr", null, table));
                        });
                        var tdPhoto = domConstruct.create("td", null, domConstruct.create("tr", null, table));
                        domConstruct.create("img", {  
                            src: "data:image/jpg/png/gif;base64," + imageStr, width: "300px"
                        }, tdPhoto);
                    });  
            }
        }
    })