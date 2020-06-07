// Пакеты - пространства имен
//package dope.nathan.scala.stepic {
//
//  object Module {
//    def name = "Nathan"
//  }
//
//}

// Импорт - для использования других пространств имен
//import dope.nathan.scala.stepic._

//object Main0 {
//  val myName = Module.name
//}

// Объект - тоже пространство имен
//import dope.nathan.scala.stepic.Module.name

//object Main1 {
//  val myName = name
//}

// Идентификаторы
// - чтобы что-то импортировать, нужно понимать, какие идентификаторы являются стабильными
// - стабильные идентификаторы указывают на значения, которые точно не будут менять

// Стабильные:
// + package
// + параметры (любой функции)
// + val
// + lazy val
// + object

// Нестабильные:
// + def (каждый раз может возвращать новое значение)

// Множественный импорт
//package dope.nathan.scala.stepic {
//
//  object Module2 {
//    def name = "Nathan"
//
//    def name2 = "J"
//  }
//
//}

// можем импортировать несколько имен из пространства
//import  dope.nathan.scala.stepic.Module2.{name, name2}

// можем импортировать все имена из пространства
//import  dope.nathan.scala.stepic.Module2._

// можем переименовать при импорте (например, при импорте одинаковых имен)
//import  dope.nathan.scala.stepic.Module.{name => nathan}

// можем импортировать все имена из пространства, неимпортируя отдельные имена
//import  dope.nathan.scala.stepic.Module.{name2 => _, _}