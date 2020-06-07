// нельзя передать уже имеющуюся последовательность
// Seq - единственный параметр
def sum(args: Int*) = {
  var result = 0
  for (arg <- args) result += arg
  result
}

val s = sum(1, 4, 9, 16, 25)

// если один аргумент,
// то д.б. единственное целое число,
// а не диапазон целых чисел
//val s = sum(1 to 5) - ошибка

// _* - параметр интерпретированный,
// как последовательность аргументов
val s0 = sum(1 to 5: _*)

// _* - обязательно для определения рекурсивной функции
// с переменным числом аргументов
def recursiveSum(args: Int*): Int = {
  if (args.isEmpty) 0
  else args.head + recursiveSum(args.tail: _*)
}

// head - начальный элемент последовательности
// tail - все остальные элементы последовательности,
// это также объект Seq => нужно использовать _*

// вызов java-метода с переменным число аргументов типа Object
// например, PrintStream.printf или MessageFormat.format
// простые типы н.б. преобразовать вручную - .asInstanceOf[AnyRef]
import java.text.MessageFormat
val str = MessageFormat.format("The answer to {0} is {1}", "everything",
  42.asInstanceOf[AnyRef])