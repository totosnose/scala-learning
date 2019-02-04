// while
var n = 10
var r = 1
while (n > 0) {
  r = r * n
  n -=1
}

print(r)

// for-to
n = 10
r = 1
for (i <- 1 to n) {
  r = r * i
}

print(r)

// foreach
r = 1
val expr = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
for (i <- expr) {
  r = r * i
}

print(r)

//for-until
val s = "Hello"
var sum = 0
for (i <- 0 until s.length)
  sum += s(i)

print(sum)

// или вообще вот так
sum = 0
for (ch <- "Hello") sum += ch


// break and continue - нет, вместо этого можно:
// логическая переменная управления
// вложенная функция (можно выполнить return в середине)
// break - возбуждение и перехват исключения
// (нужно избегать последний механизм)
import scala.util.control.Breaks._

breakable {
  for (i <- expr) {
    if (i == 5) break()
    println(i)
  }
}

