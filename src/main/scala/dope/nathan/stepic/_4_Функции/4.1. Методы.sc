def plusOne(number: Int): Int = number + 1
plusOne(4)

// тип результата иногда можно опускать
def plusTwo(number: Int) = number + 2

// несколько параметров
def plus(x: Int, y: Int, z: Int): Int = x + y + z

// несколько списков параметров
def plusO(x: Int, y: Int, z: Int) (o: Int): Int = x + y + z + o
plusO(1,2,3)(0)

// методы без параметров - переменная, которая будет вычисляться каждый раз при ее вызове
def sixty: Int = 6 * 10

// более сложный метод с фигурными скобками с возвращаемым значением
def plusAndPrint(x: Int, y: Int): Int = {
  val res = x + y
  println(s"$x + $y = $res")
  res
}

plusAndPrint(4, 4)

// более сложный метод с фигурными скобками без возвращаемого значения
def plusAndPrint2(x: Int, y: Int): Unit = {
  val res = x + y
  println(s"$x + $y = $res")
}

// методы можно вызывать внутри других БЛОКОВ, в т.ч. внутри других методов
// вложенный метод может ссылаться на все параметры внешнего
def plusMul(q: Int, x: Int, y: Int): Int = {
  def mul(u: Int) = q * u
  mul(x) + mul(y)
}

// повторяемые парамаетры - p: Int*
// в теле метода такие параметры становятся коллекцией - Seq[]
def sumAllTimes(u: Int, nums: Int*): Int = u * nums.sum

// значения по умолчанию
def plus3(x: Int, y: Int = 0, z: Int = 0): Int =
  100 * x + 10 * y + z

plus3(1)
plus3(1, 2)
plus3(1, 2, 3)

plus3(x = 1)
plus3(1, z = 2)
plus3(1, z = 2, y = 3)

// передача по имени - z: => Int
// для сложновычисляемых выражений и содержащих slide-эффекты,
// в теле метода такие параметры вычисляются только тогда,
// когда они действительно необходимы,
// НО вычисляется КАЖДЫЙ РАЗ при обращении
def replaceNegative(x: Int, z: => Int): Int =
  if (x >= 0) x else z

replaceNegative(1, 3 * 3 * 3)
replaceNegative(-1, 3 * 3 * 3)

// если список параметров содержит ровно один параметр,
// то его значение можно передать блоком в фигурных скобках (не сработало)

replaceNegative(1, {
  println("calculated")
  3 * 3 * 3
})

replaceNegative(-1, {
  println("calculated")
  3 * 3 * 3
})

// или так
def replaceNegative2(x: Int) (z: => Int): Int =
  if (x >= 0) x else z

replaceNegative2(1) {
  println("calculated")
  3 * 3 * 3
}

// рекурсия (неэффективно) - ОБЯЗАТЕЛЬНО указывать тип
def sumRange(from: Int, to: Int): Int =
  if(to < from) 0
  else from + sumRange(from + 1, to)

sumRange(1, 10)

// хвостовая рекурсия
// - если вызовы самой себя происходят только
// в "хвостовых точках" вычислений, функция
// м.б. оптимизирована в "хвостовую рекурсию",
// которая внутри себя будет представлять из себя цикл

// - необходимо использовать аннотацию @tailrec,
// чтобы быть уверенным, что компилятор выполнит
// хвостовую оптимизацию
def sumRange(from: Int, to: Int, acc: Int = 0): Int =
  if(to < from) acc
  else sumRange(from + 1, to, acc + from)

sumRange(1, 10)

// Практика
// рекурсия
def sumEven(n: Int): Int = {
  def go(i: Int): Int =
    if (i > n) 0
    else i + go(i + 2)

  go(2)
}

sumEven(100)
sumEven(10000)
// множественный вызов go() самой себя => переполнение стека
//sumEven(100000)

// хвостовая рекурсия
def sumEven2(n: Int): Long = {
  def go(i: Int, acc: Long): Long = // добавление аккумулятора
    if (i > n) acc                  // итоговое возвращение аккумулятора
    else go(i + 2, acc + i)         // не возвращаемся из функции для прибавления к него чего-либо
                                    // а аккумулируем данные в ней
  go(2, 0)
}

sumEven2(100)
sumEven2(10000)
sumEven2(100000)