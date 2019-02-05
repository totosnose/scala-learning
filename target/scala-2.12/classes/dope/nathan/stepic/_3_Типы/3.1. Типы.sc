// Подтипы
// - кроме типов существуют отновшения между типами (отношения подтипизации)

// A <: B
// - A является подтипом B, когда все значения типа A
// могут использоваться, как значения типа B

// A <: Any
// - все типы являются подтипом Any
// Any - тип всех значений (очень общий надтип всех типов)
// например, функция, принимающая какие угодно агрументы, имеет параметр типа Any


// A <: AnyRef (ссылочные типы)
// Любой ссылочный тип является подтипом AnyRef
// AnyRef - тип всех ссылочных значений

// A <: AnyVal (примитивные типы)
// Любой примитивный тип является подтипом AnyVal
// AnyVal - тип всех примитивных значений

// Nothing <: A
// Любой тип является надтипом Nothing
// Nothing - тип, использучемый для ошибок и исключений
// (не используется, для генерации экземпляров)

// Примитивные типы (9)
// - целые числа:   + Byte    (8 bit, 1 byte)   -2^7 ... (2^7) - 1
//                  + Short   (16 bit, 2 byte)  -2^15 ... (2^15) - 1
//                  + Int     (32 bit, 4 byte)  -2^31 ... (2^31) - 1
//                  + Long    (64 bit, 8 byte)  -2^63 ... (2^63) - 1
// - дробные числа: + Float   (32 bit, 4 byte) // более экономичный
//                  + Double  (64 bit, 8 byte) // более точный
// - символы:       + Char    (16 bit, 2 byte)
// - булевые зн-я:  + Boolean // true, false
// - единичный тип: + Unit // возвращает "()" - ничто

// Работа подтипов
val x: String = "Nathan"   // ссылочный тип
val y: AnyRef = x          // привели к типу более низкого уровня
val z: Any = y             // привели к типу еще более низкого уровня

val x0: Int = 1            // примитивный тип
val y0: AnyVal = x0        // привели к типу более низкого уровня
val z0: Any = y0           // привели к типу еще более низкого уровня


// Практика
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