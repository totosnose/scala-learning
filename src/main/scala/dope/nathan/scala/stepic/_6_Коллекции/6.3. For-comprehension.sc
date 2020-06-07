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


// -----------------------Практика -----------------------------------------------------------------

import java.lang.Math.sqrt

def fun(x: Double, y: Double) =
  (sqrt(x) + sqrt(y)) / sqrt(x + y)

fun(4, -4)

def sqrtE(x: Double): Either[String, Double] =
  if (x < 0) Left(s"$x < 0 !") else Right(sqrt(x))

def divE(x: Double, y: Double): Either[String, Double] =
  if (y == 0) Left("zero division !") else Right(x / y)

// flatMap
def funE(x: Double, y: Double): Either[String, Double] =
  sqrtE(x).flatMap { sx =>
    sqrtE(y).flatMap { sy =>
      sqrtE(x + y).flatMap { sxy =>
        divE(sx + sy, sxy)
      }
    }
  }

funE(4, 5)
funE(-2, 5)
funE(3, -4)
funE(0, 0)

// for-comprehension
def funEF(x: Double, y: Double): Either[String, Double] =
  for {
    sx <- sqrtE(x)
    sy <- sqrtE(y)
    sxy <- sqrtE(x + y)
    res <- divE(sx + sy, sxy)
  } yield res

funEF(4, 5)
funEF(-2, 5)
funEF(3, -4)
funEF(0, 0)


// Памятка
val rs = for { x <- Some(1) ; y <- None } yield (x, y)
for { (k,v) <- Map("a" -> 1, "b" -> 2) } yield k
//for { x <- Some(1) ; y <- Left("year") } yield x + y
//for { x <- Some(2); y <- List(1,2,3,4) } yield x + y