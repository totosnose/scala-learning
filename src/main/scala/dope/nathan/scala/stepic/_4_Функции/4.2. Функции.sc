// функция - значение, которое может быть использовано, как метод

// Тип параметра => Тип результата
// val  x: Int => Int = ...
// максимальное количество параметров у функции - 22
// val  y: (Int, Int) => Int = ...

// Лямбда-абстрация
// задать значение (определить функцию) можно с помощью лямбда-выражений
val addOne: Int => Int = x => x + 1
val plus: (Int, Int) => Int = (x, y) => x + y

// или

// можно указывать тип параметров прямо в лямбда-абстракции,
// тогда scala попробует вывести тип всего выражения сама
val addOne1 = (x: Int) => x + 1
val plus1 = (x: Int, y: Int) => x + y

// Краткая запись
// если каждый из параметров используется только один раз,
// и все параметры используются только по порядку,
// в котором они определяются в списке параметров,
// то вместо этих параметров можно ставить запись с подчеркиванием
val addOne2: Int => Int = _ + 1
val plus2 = (_: Int) + (_ : Int)

// Эта-конверсия (превращение метода в функцию)
// оператор "_" после имени метода
def addOne3(x: Int) = x + 1
val add1 = addOne3 _

// если компилятор понимает, что значением должна быть функция,
// "_" можно не ставить
def plus3(x: Int, y: Int) = x + y
val pl: (Int, Int) => Int = plus3

// функции м.б. использованы везде, где м.б. использованы обычные значения =>
// + передавать в качестве параметров
// + возвращать в качестве результата
def greaterOn(f: Int => Int): (Int, Int) => Boolean =
  (x, y) => f(x) > f(y)
val greaterOnOnes = greaterOn(x => x % 10)

greaterOnOnes(23, 45)
greaterOnOnes(27, 45)

// Каррирование (для чего?)
// - представление функции от 2х(например) параметров, как:
// функция от 1го параметра, которая возвращает еще одну
// функцию от 2го параметра, которая ... и т.д. и в конце - результат
// или
// - другими словами: представить функцию многих параметров, как
// последовательность функций от одного параметра, возвращающих результат
def plus4: Int => Int => Int = x => y => x + y
plus4(1)(2)

// можно превратить функцию многих параметров
// в каррированный вариант с помощью обычной лямбды,
// а можно - методом curried

val plus5 = (x: Int, y: Int, z: Int) => x + y + z
val plus5c: Int => Int => Int => Int = plus5.curried

plus5c(1)(2)(3)

// Композиция
// - если есть две функции, и результат одной применить к результату другой,
// то можно исользовать готовые методы для композиции
val plus6 = (_: Int) + 1
val mul = (_: Int) * 3

val plusThenMul = plus6 andThen mul // передаст результат plus6, как аргумент в mul
val plusBeforeMul = plus6 compose mul // передаст результат mul, как аргумент в plus6

plusThenMul(5)
plusBeforeMul(5)

// Задачи
val mul3 = 3 * (_: Double)
val pow2 = (x: Double) => x * x

// Метод andThen превращается в функцию (эта-конверсия),
// которая принимает на вход сначала mul3
// и возвращает ее композицию с pow2, а затем принимает число,
// над которым требуется произвести вычислительную операцию.
println((pow2.andThen[Double] _ )(mul3)(5))


object LessonData {
  def searchInArray(cond: Int => Boolean, array: List[Int]): List[Int] = {
    array.filter(cond)
  }
}

val searchOdd: List[Int] => List[Int] = LessonData.searchInArray(_ % 2 == 1, _)
val searchOdd2 = (LessonData.searchInArray _).curried(_ % 2 == 1)
println(searchOdd(List(8, 11, 12))) // List(11)


// -----------------------Практика -----------------------------------------------------------------
// функция принимающая аргумент типа Int
val add11 = (_: Int) + 1

// (f: Int => Int) - можем передать функцию, которая возвращает Int
// (...) => f(42) - в функцию f, которую передали ранее в качестве аргумента
// можем передать аргумент типа Int
val calc42 = (f: Int => Int) => f(42)

// что-то вроде инкапсуляции?
calc42(add11)

// или
// лямбды могут принимать анонимные функции
calc42(_ + 7)

// передача функции, которая вычисляет сумму всех чисел от 1...n
def sumTo(x: Int): Int = if (x == 0) 0 else x + sumTo(x - 1)

// сумма чисел от 1 до 42
calc42(sumTo)

// или запись в теле
// функция не имеет никакого имени =>
// нужно сделать из любой произвольной функции ее рекурсивную версию
// calc42((x: Int) => if(x == 0) 0 else x + this)
// эту проблему решает Y-combinator, который находит fix-point (неподвижную точку)
// необходимо искать неподвижную точку из функции, которая принимает на вход функцию
// и возвращает ее рекурсивную версию
// def fix(f: (=> - параметр функции f (рекурсивная версия) будет ленивым (см. далее)
def fix(f: (=> Int => Int) => Int => Int): Int => Int = f(fix(f))
fix(rec => x => if (x == 0) 0 else x + rec(x - 1))(7)

// запись рекурсии в анонимной форме
calc42(fix(rec => x => if (x == 0) 0 else x + rec(x - 1)))

// -----------------------Бонус -----------------------------------------------------------------
// Подробнее про аргументы ленивой оценки

def calculateData: String = {
  print("Calculating expensive data!")
  "some expensive data"
}

def dumbMediator(preconditions: Boolean = false, data: String): Option[String] = {
  println("Applying dumb mediator")
  if (preconditions) Some(data) else None
}

def smartMediator(preconditions: Boolean = false, data: => String): Option[String] = {
  println("Applying smart mediator")
  if (preconditions) Some(data) else None
}

println(smartMediator(data = calculateData))
println(dumbMediator(data = calculateData))

// Подробнее о записи функций

// одна и та же функция!
val f0 = (i: Int) => { i % 2 == 0 }
val f3: Int => Boolean = i => i % 2 == 0
val f4: Int => Boolean = _ % 2 == 0

// implicit approach
val add0 = (x: Int, y: Int) => { x + y }
val add111 = (x: Int, y: Int) => x + y

// explicit approach
val add2: (Int, Int) => Int = (x,y) => { x + y }
val add3: (Int, Int) => Int = (x,y) => x + y
