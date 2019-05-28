// тип Option[A] - может содержать значение типа A
// подтип Some[A] - контейнер, содержащий значение
// подтип None - ничего не содержит

// решение проблем пустой ссылки
def divide(x: Int, y: Int): Option[Int] =
  if (y == 0) None else Some(x / y)

divide(1, 0)

// проверка в pattern matching
def showDivide(x: Int, y: Int): String =
  divide(x, y) match {
    case Some(d) => s"$x = $d * $y"
    case None => "null division"
}

showDivide(1, 0)

// вместо pattern matching существует много встроенных методов
divide(7, 0).getOrElse(0)
divide(7, 0).orElse(divide(7, 2))

// преобразование
divide(7, 4).map(x => x + 6)
divide(17, 3).flatMap(x => divide(x, 3))

// фильтрация
divide(7, 4).filter(x => x > 2)

// map + filter = collect
divide(17, 3).collect{
  case x if x > 4 => x + 4
}

// альтернатива проверке pattern matching
def showDivide2(x: Int, y: Int): String =
  divide(x, y)
    .map(d => s"$x = $d * $y")
    .getOrElse("null division")


// практика
val string = "scala + [stepik] = love"

string.indexOf("[")
string.indexOf("7")

def indexOf(s: String, pattern: String): Option[Int] =
  Option(s.indexOf(pattern)).filter(_ >= 0)

indexOf(string, pattern = "[")
indexOf(string, pattern = "7")

// поиск пары соответствующих скобок в индексах
def brackets(s: String): Option[(Int, Int)] =
  indexOf(s, pattern = "[").flatMap{ opening =>
    indexOf(s, pattern = "]")
      .filter(_ > opening)
      .map(closing => (opening, closing))
  }

brackets(string)
brackets("dfasa ] sda [ as")

// поиск значения в скобках
def cutBrackets(s: String): Option[String] =
brackets(s).map{ case (opening, closing) =>
    s.substring(opening + 1, closing)
}

cutBrackets(string)


def foo(list: List[Int]): Int =
  list.find(i => (i % 3) == 0).getOrElse(0) * 2

foo(List(1, 2, 4, 5))