// TWITTER
// res0 - автоматически создаваемое имя для переменной
1 + 1

// val - константы
val two = 1 + 1

// var - переменные
var name = "steve"
name = "marius"

// def - функции
def addOne(m: Int): Int = m + 1
val three = addOne(2)

def four() = 2 + 2
four()
four

// анонимные функции
(x: Int) => x + 1
//res3(1) не сработало
//res4: Int = 2 д.б. такой результат

// сохранение функции в переменной
val addTwo = (x: Int) => x + 2
addTwo(1)

//анонимные функции в качестве параметра
addTwo(addTwo(1))
//addTwo(res4) не сработало

// функция состоит из множества выражений
def timesTwo(i: Int): Int = {
  println("hello world")
  i * 2
}

// анонимная функция состоит из множества выражений
{ i: Int =>
  println("hello world")
  i * 2
}

// частичный вызов функций
def adder(m: Int, n: Int) = m + n
val add2 = adder(2, _: Int)
add2(3)

// каррирование передача одних аргументов в функцию прямо сейчас
// а других через некоторое время
def multiply(m: Int)(n: Int): Int = m * n

// вызов функции напрямую с двумя аргументами
multiply(2)(3)

// передача первого аргумента, а второй - объявлен,
// как частично вызываемый
val timesTwo = multiply(2) _
//timesTwo(3) не сработало

// каррирование функции функцию с множеством аргументов
(adder _).curried

// переменное количество аргументов
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}
capitalizeAll("rarity", "applejack")
