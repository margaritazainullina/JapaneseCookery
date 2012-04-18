//всегда начинайте модуль с инструкции dojo.provide
dojo.provide("dtdg.Recipe");
dojo.require("dojox.xml.parser");
dtdg.Recipe = function(xmldoc, element) {
    // Create the <table> element
    var table = document.createElement("table");
    // Create the header row of <th> elements in a <tr> in a <thead>
    var thead = document.createElement("thead");
    var header = document.createElement("tr");
    var cell = document.createElement("th");
    cell.appendChild(document.createTextNode("Hello world!"));
    header.appendChild(cell);
    // Put the header into the table
    thead.appendChild(header);
    table.appendChild(thead);
    
//    for(var i = 0; i < schema.columns.length; i++) {
//        var c = schema.columns[i];
//        var label = (typeof c == "string")?c:c.label;
//        var cell = document.createElement("th");
//        cell.appendChild(document.createTextNode(label));
//        header.appendChild(cell);
//    }
//    // Put the header into the table
//    thead.appendChild(header);
//    table.appendChild(thead);
//
//    // The remaining rows of the table go in a <tbody>
//    var tbody = document.createElement("tbody");
//    table.appendChild(tbody);
//
//    // Now get the elements that contain our data from the xml document
//    var xmlrows = xmldoc.getElementsByTagName(schema.rowtag);
//
//    // Loop through these elements. Each one contains a row of the table.
//    for(var r=0; r < xmlrows.length; r++) {
//        // This is the XML element that holds the data for the row
//        var xmlrow = xmlrows[r];
//        // Create an HTML element to display the data in the row
//        var row = document.createElement("tr");
//
//        // Loop through the columns specified by the schema object
//        for(var c = 0; c < schema.columns.length; c++) {
//            var sc = schema.columns[c];
//            var tagname = (typeof sc == "string")?sc:sc.tagname;
//            var celltext;
//            if (tagname.charAt(0) == '@') {
//                // If the tagname begins with '@', it is an attribute name
//                celltext = xmlrow.getAttribute(tagname.substring(1));
//            }
//            else {
//                // Find the XML element that holds the data for this column
//                var xmlcell = xmlrow.getElementsByTagName(tagname)[0];
//                // Assume that element has a text node as its first child
//                var celltext = xmlcell.firstChild.data;
//            }
//            // Create the HTML element for this cell
//            var cell = document.createElement("td");
//            // Put the text data into the HTML cell
//            cell.appendChild(document.createTextNode(celltext));
//            // Add the cell to the row
//            row.appendChild(cell);
//        }
//
//        // And add the row to the tbody of the table
//        tbody.appendChild(row);
//    }

    // Set an HTML attribute on the table element by setting a property.
    // Note that in XML we must use setAttribute( ) instead.
    table.frame = "border";

    // Now that we've created the HTML table, add it to the specified element.
    // If that element is a string, assume it is an element ID.
    if (typeof element == "string") element = document.getElementById(element);
    element.appendChild(table);
}