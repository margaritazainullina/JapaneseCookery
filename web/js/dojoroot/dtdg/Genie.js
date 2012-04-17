//всегда начинайте модуль с инструкции dojo.provide
dojo.provide("dtdg.Genie");
//создание пространства имен для джинна
dtdg.Genie = function() {}
//набор предсказаний чем-то напоминает 8 волшебных шаров
dtdg.Genie.prototype._predictions = [
	"As I see it, yes",
	"Ask again later",
	"Better not tell you now",
	"Cannot predict now",
	"Concentrate and ask again",
	"Don't count on it",
	"It is certain",
	"It is decidedly so",
	"Most likely",
	"My reply is no",
	"My sources say no",
	"Outlook good",
	"Outlook not so good",
	"Reply hazy, try again",
	"Signs point to yes",
	"Very doubtful",
	"Without a doubt",
	"Yes",
	"Yes - definitely",
	"You may rely on it"
];
//функция инициализации, конструирующая интерфейс
dtdg.Genie.prototype.initialize = function() {
		var label = document.createElement("p");
		label.innerHTML = "Ask a question. The genie knows the answer...";
		var question = document.createElement("input");
		question.size = 50;
		var button = document.createElement("button");
		button.innerHTML = "Ask!";
		button.onclick = function() {
			alert(dtdg.Genie.prototype._getPrediction());
			question.value = "";
		}
		var container = document.createElement("div");
		container.appendChild(label);
		container.appendChild(question);
		container.appendChild(button);
		dojo.body().appendChild(container);
	}
//основная функция реализации взаимодействия
dtdg.Genie.prototype._getPrediction = function() {
		//получить псевдослучайное число в диапазоне от 0 до 19, которое будет играть роль индекса предсказания
		var idx = Math.round(Math.random()*19)
		return this._predictions[idx];
	}