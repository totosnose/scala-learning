import scala.util.matching.Regex

val name = "friend"

// конкатенация
val greet = "hello " + name

// интерполяция
val greet2 = s"hello $name"

// переносы строк
val s =
  """|переносы
     |строк
     |не
     |потеряны
  """.stripMargin

// Операции
val str = "aaabbb"

str.startsWith("aa")
str.endsWith("bb")
str.contains("ab")

// Регулярные выражения
s.matches("a+b+")

val regex = "a+b+".r

// Приведение к строке
val x = 2

// явное приведение
x.toString

// приведение с использованием конкатенации
"x is " + x


// -----------------------Практика -----------------------------------------------------------------
val y = BigInt(2).pow(10000).toString()

y.contains("777")

// !!! первое вхождение двух знаков подряд
val reg = "(.)\\1\\1".r
reg.findFirstIn(y)

val ss =
  """
    |dgdsg
    |dsa
    |12313
    |7845
  """.stripMargin

ss.split("\n").length

val pattern = new Regex("[^_][a-z]_*")
val test = "_snake_case"
println((pattern findAllIn test).mkString(","))