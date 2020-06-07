// обход массива или ArrayBuffer в цикле for
val a = Array(1, 2, 3)

// until - class RichInt
for (i <- 0 until a.length)
  println(i + ": " + a(i))

// или
for (i <- a.indices)
  println(i + ": " + a(i))

// until = for (i <- range)
0 until 10
// или
0 until(10)

// четные элементы
0 until (a.length, 2)

// обойти элементы в обратном порядке
a.indices.reverse

// без индексов, как foreach
// elem - a(1)..a(n)
for (elem <- a)
  println(elem)