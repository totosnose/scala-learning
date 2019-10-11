// Кортежи - предопределенные типы, предстаялющие последовательности значений фиксированной длины

// можно упаковать несколько значений в такую последовательность, перечислив их внутри скобок, разделяя запятой
// val tuple = (value1, ..., valueN)

// так выглядит тип такого кортежа
// type Tupletype = (Type1, ..., TypeN)

// кортежи полезны, чтобы возвращать из функции несколько значений сразу
def divMod(x: Int, y: Int): (Int, Int) = (x / y, x % y)

def firstLastAndCount(line: String): (Char, Char, Int) =
  (line(0), line.last, line.length)

// распаковка кортежей
// val (val1: Type1, val2: Type2, ..., valN) = tupl

// объявить сразу несколько переменных с помощью кортежа
val (div, mod) = divMod(17, 5)
val (first, last, count) = firstLastAndCount("Scala")

// использование в pattern matching
def showDiv(x: Int, y: Int) =
  divMod(x, y) match {
    case (d, r) => s"$x is $d * $y + $r"
  }

// обращение к элементу (без распаковки) через специальные поля
val dm = divMod(17, 5)

val div1 = dm._1
val mod1 = dm._2

// пары элементов
val nameAndPopulation = "Moscow" -> 12e6

val pairs = List(1 -> "one", 2 -> "two", 3 -> "three")

val intAndString: (Int, String) = 1 -> "one"

// swap - меняет элементы местами с изменением типов
val stringAndInt: (String, Int) = intAndString.swap


// -----------------------Практика -----------------------------------------------------------------

// Алгоритм Евклида
// есть a и b, нужно найти такие x и y, чтобы
// a * x + b * y = d, где d - наибольший общий делитель чисел a и b
def euclid(a: Int, b: Int): (Int, Int) = {
  // несколько специальных случаев:
  // - чтобы 1е число всегда было больше 2го
  if (b > a) euclid(b, a).swap // выполняем функцию в обратном порядке и меняем местами коэффициенты
  // если все найдено( это означает, что одно из чисел (а именно  b, т.к. оно наименьшее) равно 0,
  // т.к. наибольший общий делитель числа с 0 - это само число)
  else if (b == 0) (1, 0) // наибольшим общим делителем теперь является число а, следовательно a * 1, а b * 0 (или "на что угодно")
  // если ни одно из условий не выполнилось и осталась пара натуральных чисел, то рассматриваем основной рекурсивный случай
  else {
    val d = a / b // вычисляем частное
    val r = a % b // и остаток от деления a на b
    //рекурсия
    val (x, y) = euclid(b, r) // распаковка
    (y, x - d * y) // коэф. для a, коэфю для b
  }
}

euclid(7, 4)
euclid(234, 168)

def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = {
  (tuple._3, tuple._2, tuple._1)
}

def swapMore(tuple: (Int, Int, Int, Int, Int)): (String, String) = {
  val tupleLength = tuple.productIterator.toList.length - 1
  val (f, l) = (tuple.productElement(tupleLength).toString, tuple.productElement(0).toString)
  tuple.productIterator.drop(tupleLength)
  tuple.productIterator.drop(0)
  (f, l)
}

swapMore((1,2,3,4,5))