define(["dojo/dom", 'dojo/_base/declare'], function(dom, declare) {
    var ret = {};
    ret.bar = function() {
        alert("Hello from FooModule!");
    };
        
    return ret;    
});