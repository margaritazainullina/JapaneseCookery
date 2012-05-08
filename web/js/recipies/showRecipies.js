define(
    ["dojo/domReady!"],
    function() {
        var ret = {};
        ret.bar = function(dom, array, element) {
            
            dom.byId(element).innerHTML = array.toString();
        };
        return ret;    
    }
);  