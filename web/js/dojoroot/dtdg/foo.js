/* Инструкция dojo.provide указывает, что файл .js с исходным программным
кодом образует модуль dtdg.foo. Семантически модуль dtdg.foo также
образует пространство имен для функций, входящих в состав модуля. На диске
этот файл мог бы иметь имя foo.js и находиться в каталоге dtdg. */
dojo.provide("dtdg.foo");
//Обратите внимание: функции объявляются относительно пространства имен модуля
dtdg.foo.fibonacci = function(x) {
	if (x < 0) throw Exception("Illegal argument");
	if (x <= 1) return x;
	return dtdg.foo.fibonacci(x-1) + dtdg.foo.fibonacci(x-2);
}