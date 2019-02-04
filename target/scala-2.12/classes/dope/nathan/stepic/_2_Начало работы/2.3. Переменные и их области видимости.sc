// значение

// именованное значение. вычисляется однажды и сразу
val x = "value"


// функция без параметров. вычисляется каждый раз, когда на них ссылаются
def y = "value"

// ленивое значение. вычисляется однажды при первом обращении
// (нечто среднее между val & def)
lazy val z = "value"

// def & lazy val - не будут вычислены, если их не вызовут

// переменная.
var a = "value"
a = "new value"

// использование:
// для val - ссылаемся в последующем коде
val b = 1
val c = 2
val d = b + c

// для def || lazy val - можно сослаться даже к коду перед ними
val f = g + h
def g = 1
lazy val h = 2

// область видимости
val s = "Outer";
{
  val s = "Inner" // приоритетна в своем скоупе
  println(s)
}

{
  val p = "Inner"
  println(p)
}
//println(p) // ошибка

//val line: String = "8 11"
//val arr = line.split(" ").map(_.toInt)
//val res = arr(0) - arr(1)

val line = "8 11 2"
val arr = line.split(" ").map(_.toInt)
//val res = arr(0) - arr(1)
