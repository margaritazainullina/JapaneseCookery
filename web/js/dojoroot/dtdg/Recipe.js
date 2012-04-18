//всегда начинайте модуль с инструкции dojo.provide
dojo.provide("dtdg.Recipe");
dojo.require("dojox.xml.parser");
//создание пространства имен для рецепта
dtdg.Recipe = function() {
    // Parse text and generate an XML DOM
    var xml = "<tnode><node>Some Text</node><node>Some Other Text</node></tnode>";
    var dom = dojox.xml.parser.parse(xml);

    // Walk DOM and attach into the display how many child nodes were parsed out.
    var ap = dojo.byId("xmlContent");
    var docNode = dom.documentElement;
    ap.appendChild(document.createTextNode("Document contains: " + docNode.childNodes.length + " elements"));
    ap.appendChild(document.createElement("br"));
    ap.appendChild(document.createElement("br"));
    // Write text content into the display.
    for(var i = 0; i < docNode.childNodes.length; i++){
        ap.appendChild(document.createTextNode("Element: [" + i + "] contains text: " + 
            dojox.xml.parser.textContent(docNode.childNodes[i])));
        ap.appendChild(document.createElement("br"));
    }
    // Write out the XML text obtained from converting the DOM back.
    ap.appendChild(document.createElement("br"));
    ap.appendChild(document.createTextNode("Document XML: " + dojox.xml.parser.innerXML(docNode)));
    ap.appendChild(document.createElement("br"));
    ap.appendChild(document.createElement("br"));
}