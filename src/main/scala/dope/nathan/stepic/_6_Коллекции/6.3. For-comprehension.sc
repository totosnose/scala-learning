// for-comprehension - синтаксический сахар из дух ключевых слов for ... yield ...,
// который позволяет записать цепочку вызовов методов .map, flatMap, withFilter
// в выражение в императивном стиле

val nums = List(2, 5, 1, 7, 4)

// map
val nums2 = nums.map(_ * 2)

// for-comprehension
val nums3 =
  for (x <- nums)
    yield x * 2


// flatMap
val num4 = nums.flatMap(1 to _).map(_ * 2)

// for-comprehension
val nums5 =
  for (x <- nums; y <- 1 to x)
    yield y * 2


// flatMap, filter, map
val num6 = nums.flatMap(1 to _).filter(_ > 3).map(_ * 2)

// for-comprehension (вместо ";" - "{}")
val nums7 = for {
  x <- nums
  y <- 1 to x if y > 3
} yield y * 2


// withFilter - аналог filter, используемый в for-yield выражениях для производительности,
// а именно позволяет не создавать промежуточные коллекции

// flatMap, withFilter, map
val num8 = nums.flatMap(x =>
  (1 to x)
    .withFilter(y => y > 3)
    .map(y => y * 2)
)

val nums9 = for {
  x <- nums
  y <- 1 to x if y > 3
} yield y * 2


// синтаксис for-comprehension расчитан на то, чтобы создавать меньше промежуточных коллекций
// или создавать промежуточные коллекции меньшего размера

// flatMap, withFilter, map
val num10 =
  nums.flatMap(x =>
    (1 to x)
      .withFilter(y => y > 3)
      .map(y => (y, y * 2))
      .flatMap{ case (y, y2) =>
        nums
          .withFilter(z => z < y)
          .map(z => z + y2 - y)
      }
  )

// for-comprehension
val nums11 = for {
  x <- nums
  y <- 1 to x if y > 3
  y2 = y * 2
  z <- nums if z < y2
} yield z + y2 - y

// Область применения
// - быстрый выход с Option или Either
// - обход сложных коллекций из scala.collection.immutable
// - неблокирующие вычисления с Future, Task, IO
// - комбинаторы асинхронных коллекций Source, Observable, Stream
// - запись вычислений при функциональном подходе

// todo homework
val list1 = List(3, 1, 5, 7)
val list2 = List(2, 4, 6, 8)
val list3 = List(1, 3, 5, 8, 10, 12, 14)
val res = for {
  x <- list1
  y <- list2 if y != x
  z <- list3 if z == x * y
}  yield (x, y)


for (i <- preres.indices) {
  if (preres(i)._1 > preres(i + 1)._1)
    preres(i) = preres(i + 1)
}