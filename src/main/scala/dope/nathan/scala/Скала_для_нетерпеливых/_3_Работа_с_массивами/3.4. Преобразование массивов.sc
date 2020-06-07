import scala.collection.mutable.ArrayBuffer

// не изменение массива, а создание нового
// через for-comprehension /см 2.6
val a = Array(2, 3, 5, 7, 11)

// for (...) yield - создаст новую коллекцию того же типа (массив или буфер)
// будет содержать значения, следующие за yield, вычисляемые в каждой итерации
val result = for (elem <- a) yield 2 * elem

// guard - ограничитель: выражение if внутри for
// получается новая коллекция
for (elem <- a if elem % 2 == 0) yield 2 * elem

// или
a.filter(_ % 2 == 0).map(2 * _)
// или
a.filter { _ % 2 == 0 } map { 2 * _ }

// пример: дана последовательность целых чисел, нужно удалить отрицательные, кроме первого

// неэффективно - удалять из буффера
var b0 = Array(-1, 2, 10, -3, 3)
var b = b0.toBuffer

var first0 = true
var n = b.length
var i = 0
while (i < n) {
  if (b(i) >= 0) i += 1
  else  {
    if (first0) { first0 = false; i += 1 }
    else { b.remove(i); n -= 1}
  }
}

// эффективно - скопировать все неотрицательные числа в начало
var c = ArrayBuffer[Int](-1, 2, 10, -3, 3)

// соберем все индексы
var first = false
val indexes = for (i <- c.indices if first || c(i) >= 0) yield {
  if (c(i) < 0) first = false; i
}
// переместим элементы в новые позиции и отсечем конец
for (j <- indexes.indices) c(j) = c(indexes(j))
c.trimEnd(a.length - indexes.length)