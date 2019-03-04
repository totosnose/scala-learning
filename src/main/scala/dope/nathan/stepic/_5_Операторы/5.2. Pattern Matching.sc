// сопоставление с образцом - "самое богатое" ветвление в Scala
//{
//  case 1 ⇒ "one"
//  case 2 ⇒ "two"
//}

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
  case 2 ⇒ "two"
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

// case class - структура данных
case class Address(country: String, city: String)

// при наличие case class структуры можно распаковать поля с помощью Pattern Matching
def addressInfo(address: Address): String = address match {
  case Address("Russia", _) ⇒ "russian"
  case Address("Japan", _) ⇒ "japanese"
  case _ ⇒ "no info"
}