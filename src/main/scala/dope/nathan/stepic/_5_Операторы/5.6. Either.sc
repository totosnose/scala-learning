// Either[A, B] - исключающее или (одно из двух)
// подтип Left[A, B] - содержит A
// подтип Left[A, B] - содержит B
// (см конвенцию)

val numOrStr1: Either[Double, String] = Left(2.12)
val numOrStr2: Either[Double, String] = Right("Scala")

def info(numOrStr: Either[Double, String]): String =
  numOrStr match {
    case Left(num) => s"number $num"
    case Right(str) => s"string $str"
  }

info(numOrStr1)
info(numOrStr2)

// удобнее для обработки ошибок (без pattern matching)
// разница с Option - в Either есть описание, почему значение отсутствует
def sqrt(x: Double): Either[String, Double] =
  if (x < 0) Left("negative number")
  else Right(Math.sqrt(x))

// замена на значение по-умолчанию
sqrt(7).getOrElse(0)

// заменить Right на Left, если значение в Right не подходит под условие
// с сопровождением информации об ошибке, можно так:
sqrt(3).filterOrElse(_ > 2, "too small")

// преобразование
sqrt(7).map(_.toString) // заменит Right одно типа на другой тип
sqrt(7).flatMap(x => sqrt(x)) // вернет Right, если изначально был Right и следующая функция тоже вернула Right

// Метод flatMap действует до первой "ошибки"-Left
Right(1).flatMap(_ => Left(2)).flatMap(_ => Left(3))


// -----------------------Практика -----------------------------------------------------------------

def euclid(a: Int, b: Int): (Int, Int) = {
  if (b > a) euclid(b, a).swap
  else if (b == 0) (1, 0)
  else {
    val d = a / b
    val r = a % b
    val (x, y) = euclid(b, r)
    (y, x - d * y)
  }
}


def divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
  if (p._2 == 0 || q._2 == 0 || q._1 == 0)
    Left("Zero divisor")
  else if (Math.abs(p._1) > Math.abs(p._2) || Math.abs(q._1) > Math.abs(q._2))
    Left("Invalid input")
  else {
    val x = (p._1 * q._2, p._2 * q._1)
    if (Math.abs(x._1) > Math.abs(x._2))
      Left("Improper result")
    else
      Right(euclid(x._1, x._2))
  }
}

val p = (2, 4)
val q = (2, 4)
divide(p)(q)