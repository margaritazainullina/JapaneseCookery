dojo.provide("dtdg.Recipe");
dojo.require("dojox.xml.parser");
dtdg.Recipe = function(xmldoc, element) {
    // Create the <table> element
    var table = document.createElement("table");
    // Create the header row of <th> elements in a <tr> in a <thead>
    var thead = document.createElement("thead");
    var header = document.createElement("tr");
    var cell = document.createElement("th");
    // Заполняем первый th значением тега name
    var name = xmldoc.getElementsByTagName("name")[0];
    cell.appendChild(document.createTextNode(name.firstChild.data));
    header.appendChild(cell);
    // Put the header into the table
    thead.appendChild(header);
    table.appendChild(thead);
    
    // Создаем tr
    var trInfo = document.createElement("tr");
    var cellInfo = document.createElement("td");
    // Заполняем td значением тега info
    var info = xmldoc.getElementsByTagName("info")[0];
    cellInfo.appendChild(document.createTextNode(info.firstChild.data));
    trInfo.appendChild(cellInfo);    
    table.appendChild(trInfo);
    
    // Создаем tr 'Ингредиенты'
    var trIngredientTitle = document.createElement("tr");
    var cellIngredientTitle = document.createElement("td");
    cellIngredientTitle.appendChild(document.createTextNode("Ингредиенты"));
    trIngredientTitle.appendChild(cellIngredientTitle);    
    table.appendChild(trIngredientTitle);
    // Покажем ingredient
    var ingredientRows = xmldoc.getElementsByTagName("ingredient");
    for(var i = 0; i < ingredientRows.length; i++) {
        var ingredientTr = document.createElement("tr");
        var ingredientCell = document.createElement("td");
        var currentIngredient = ingredientRows[i];
        var cellIngredientText = currentIngredient.getAttribute("unit") + ' ' + 
            currentIngredient.getAttribute("amount") + ' ' 
            + currentIngredient.firstChild.data;
        ingredientCell.appendChild(document.createTextNode(cellIngredientText));
        ingredientTr.appendChild(ingredientCell);    
        table.appendChild(ingredientTr);
    }
    // Создаем tr 'Готовка'
    var trCookTitle = document.createElement("tr");
    var cellCookTitle = document.createElement("td");
    cellCookTitle.appendChild(document.createTextNode("Готовка"));
    trCookTitle.appendChild(cellCookTitle);    
    table.appendChild(trCookTitle);    
    
    // Покажем cook
    var stepRows = xmldoc.getElementsByTagName("step");
    for(var i = 0; i < stepRows.length; i++) {
        var stepTr = document.createElement("tr");
        var stepCell = document.createElement("td");
        var currentStep = stepRows[i];
        var cellStepText = currentStep.firstChild.data;
        stepCell.appendChild(document.createTextNode(cellStepText));
        stepTr.appendChild(stepCell);    
        table.appendChild(stepTr);
    }    
    // Покажем фотку если она есть!
    var imgTr = document.createElement("tr");
    var imgCell = document.createElement("td");
    var img = document.createElement("img");
    img.setAttribute("alt", "альтернативный текст");
    img.setAttribute("width", "200px");
    img.setAttribute("height", "300px");
    var lobImg = 'data:image/png;base64,' + xmldoc.getElementsByTagName("image")[0].firstChild.data;
    
    img.setAttribute("src", lobImg);
    
    
    imgCell.appendChild(img);    
    imgTr.appendChild(imgCell);    
    table.appendChild(imgTr);
    
    table.frame = "border";

    if (typeof element == "string") element = document.getElementById(element);
    element.appendChild(table);
}