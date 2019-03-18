// СОПОСТАВЛЕНИЕ С ОБРАЗЦОМ
// - "самое богатое" ветвление в Scala

val x = 1
x match {
  case 1 ⇒ "one"
  case 2 ⇒ "two"
}

def numberName(x: Int) = x match {
  case 1 ⇒ "one"
  case 2 ⇒ "two"
}
numberName(1)
numberName(2)
//numberName(3) // throw MatchError

// решение throw MatchError
def numberName2(x: Int) = x match {
  case 1 ⇒ "one"
  case 2 ⇒ "two"
  case _ ⇒ "unknown"
}
numberName2(1)
numberName2(2)
numberName2(3)

// порядок ИМЕЕТ значение (как в try catch)
def numberName3(x: Int) = x match {
  case 1 ⇒ "one"
  case _ ⇒ "unknown"
//  case 2 ⇒ "two"
}
numberName3(1)
numberName3(2)
numberName3(3)

// МОЖНО матчить несколько значений в одном case
def numberName4(x: Int) = x match {
  case 1 ⇒ "one"
  case 2 ⇒ "two"
  case 3 | 4 ⇒ "three or four"
  case _ ⇒ "unknown"
}
numberName4(1)
numberName4(2)
numberName4(3)
numberName4(4)
numberName4(5)

// МОЖНО писать условия для матчинга внутри case
def numberName5(x: Int) = x match {
  case 1 ⇒ "one"
  case 2 ⇒ "two"
  case x if x % 2 == 0 ⇒ "unknown even"
  case _ ⇒ "unknown odd"
}
numberName5(1)
numberName5(2)
numberName5(3)
numberName5(4)



// CASE CLASS
// - структура данных

case class Address(country: String, city: String)

// при наличие case class структуры можно распаковать поля с помощью Pattern Matching
def addressInfo(address: Address): String = address match {
  case Address("Russia", _) ⇒ "russian"
  case Address("Japan", _) ⇒ "japanese"
  case _ ⇒ "no info"
}

def addressInfo2(address: Address): String = address match {
  case Address("Russia", "Moscow") ⇒ "russian capital"
  case Address("Russia", _) ⇒ "russian"
  case Address("Japan", "Tokio") ⇒ "japanese capital"
  case Address("Japan", _) ⇒ "japanese"
  case _ ⇒ "no info"
}
// и использовать
def addressInfo3(address: Address): String = address match {
  case Address("Russia", "Moscow") ⇒ "russian capital"
  case Address("Russia", city) ⇒ s"russian $city"
  case Address("Japan", "Tokio") ⇒ "japanese capital"
  case Address("Japan", city) ⇒ s"japanese $city"
  case _ ⇒ "no info"
}



// КОЛЛЕКЦИИ

// возвращает сумму всех элементов списка (хвостовая рекурсия)
def sum(xs: List[Int], start: Int = 0): Int = xs match {
    case x :: rest => sum(rest, start + x)  // разобраться
    case Nil       => start                 // проверка до пустого списка
  }

// суммирование для списков, как минимум, из 3х элементов
def sum2(xs: List[Int], start: Int = 0): Int = xs match {
    case List() => start                                // все пустые списки
    case List(x) => start + x                           // списки из 1го элемента и тд
    case List(x, y) => start + x + y
    case List(x, y, z) => start + x + y + z
    case _ => throw new Exception("too many elements")
  }

// суммирование для любых типов с большим количеством элементов
def sum3(xs: List[Int], start: Int = 0) = xs match {
  case List() => start                                  // пустой список
  case List(x, rest@_*) => sum(rest.toList, start + x)  // @_* - все остальные элементы справа от указанных
}

// суммирование для любых типов с большим количеством элементов (даже общего поддтипа всех коллекций)
def sum4(xs: Seq[Int], start: Int = 0) = xs match {
  case Seq() => start                                  // пустой список
  case Seq(x, rest@_*) => sum(rest.toList, start + x)  // @_* - все остальные элементы справа от указанных
}

val list = List(1, 2, 3)
sum3(list)



// РЕГУЛЯРНЫЕ ВЫРАЖЕНИЯ

val address = "\\w+, \\w+".r
def isAddress(string: String): Boolean = string match {
  case address() => true
  case _         => false
}

isAddress("Saratov, Russia")
isAddress("Soviet Union")

// вытягивание элементов с регулярными выражениями (как с коллекциями и case class)
val address2 = "(\\w+), (\\w+)".r // () - определение групп регулярных выражений
case class Address2(country: String, city: String)


// опциональное формирование экземпляра структуры Address с country и city
def readAddress(string: String): Option[Address2] =
string match {
  case address2(city, country) =>
    Some(Address2(country, city))
  case _                       =>
    None
}

readAddress("Saratov, Russia").orNull
readAddress("Soviet Union").orNull

// pattern matching у регулярных выражений также, как и у коллекций (без перечисления всех групп ())
def readAddress2(string: String): List[String] =
  string match {
    case address2(parts@_*) => parts.toList
    case _                  => List()
  }

readAddress2("Saratov, Russia")
readAddress2("Soviet Union")



// КОМБИНАЦИЯ
// - использование разных способов
val regex = "(Russia|Japan)".r
case class Address3(country: String, city: String)

def getInfo(address: Address3): String =
  address match {
    case Address3(regex(country), city) =>
      s"$city, $country"
    case Address3(_, _) => "unknown country"
  }


getInfo(Address3("Russia", "Saratov"))
getInfo(Address3("USSR", "Saratov"))

//TODO last 50 sec