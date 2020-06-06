// Пакеты - пространства имен
package dope.nathan {

  object Module {
    def name = "Nathan"
  }

}

// Импорт - для использования других пространств имен
import dope.nathan.Module

object Main0 {
  val myName = Module.name
}

// Объект - тоже пространство имен
import dope.nathan.Module.name

object Main1 {
  val myName = name
}

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
package dope.nathan2 {

  object Module {
    def name = "Nathan"

    def name2 = "J"
  }

}

// можем импортировать несколько имен из пространства
import  dope.nathan2.Module.{name, name2}

// можем импортировать все имена из пространства
import  dope.nathan2.Module._

// можем переименовать при импорте (например, при импорте одинаковых имен)
import  dope.nathan2.Module.{name => nathan}

// можем импортировать все имена из пространства, неимпортируя отдельные имена
import  dope.nathan2.Module.{name2 => _, _}