//package dope.nathan.stepic._3_Типы

object Example extends App {
  val message: String = "hello friend"

  val message2: AnyRef = message

//  val message3: String = message2   // невозможно

  val message4: Any = Math.acos(1.0)

//  val message5 = Math.acos(message) // невозможно

  val message6: Unit = Math.acos(1.0) // не возвращает результат, а объект "()"
  val message7 = println(message2)    // Unit - обозначает тип последовательности действий

  val message8: Double = {
    val x = 1.0
    Math.acos(x)
  }

  println(message2)
  println(message4)
  println(message6)
  println(message7)
  println(message8)
}
