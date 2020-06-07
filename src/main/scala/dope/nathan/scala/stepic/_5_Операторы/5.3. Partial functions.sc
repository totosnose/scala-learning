// PartialFunction[-A, +B]
// имеет тип входного и возвращаемого параметра, но есть и дополнительный метод:
// def isDefinedAt(input:  A): Boolean,
// который для любого входного параметра, который он может обработать,
// вернет true, а для остальных - false

// - это "воплащение" pattern matching в типы,
// т.е. можно взять pattern matching и, с помощью него, задать частичную функцию

// частичная функция деления числа 10 на параметр без остатка
val divide10: PartialFunction[Int, Int] = {
  case 1 => 10
  case 2 => 5
  case 5 => 2
  case 10 => 1
}

divide10.isDefinedAt(2)
divide10.isDefinedAt(3)

divide10(2)
// divide10(3) == throw new MatchError

// метод .collect (есть у всех коллекций), например, принимает на вход частичные функции
// возьмет частичную функцию, пробежится по всем элементам коллекции
// и для каждого элемента проверит, подходит ли он для частичной функции,
// и, если подходит, то получит результат и вернет коллекцию из полученных результатов
List.range(1, 11)
List.range(1, 11).collect(divide10)

val log: PartialFunction[Double, Double] = {
  case d: Double if d > 0 => Math.log(d)
}

// при использовании частичной функции НЕ получим NaN
val list: List[Double] = List(4, 16, 25, -9)

//val result = list.map(Math.log)
val result = list.collect(log)


// -----------------------Практика -----------------------------------------------------------------
case class Jar(name: String, value: Int, price: Double)
val jars = List(
  Jar("Морской синий 6л", 6, 3000),
  Jar("Огненно-красный 12л", 12, 5000),
  Jar("Огненно-коричневый 1л", 1, 5000)
)

val discount: PartialFunction[Jar, String] = {
  case j if j.value > 10 => s"${j.name} ${j.price * 0.1}"
  case j if j.value > 4 => s"${j.name} ${j.price * 0.05}"
}

jars.collect(discount)


