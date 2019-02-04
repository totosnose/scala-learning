// Пакеты - пространства имен
package dope.nathan

object Module {
  def name = "Nathan"
}

// Импорт - для использования других пространств имен
import dope.nathan.Module

object Main {
  val myName = Modelu.name
}

// Объект - тоже пространство имен
import dope.nathan.Module.name

object Main {
  val myName = name
}

// Идентификаторы
// - чтобы что-то импортировать, нужно панимать,
// какие идентификаторы являются стабильными
// - стабильные идентификаторы указывают на значения,
// которые точно не будут менять

// Стабильные:
// + package
// + параметры (любой функции)
// + val
// + lazy val
// + object

// Нестабильные:
// + def (каждый раз может возвращать новое значение)

// Множественный импорт
package dope.nathan

object Module {
  def name = "Nathan"
  def name2 = "J"
}

// можем импортировать несколько имен из пространства
import  dope.nathan.Module.{name, name2}

// можем импортировать все имена из пространства
import  dope.nathan.Module._

// можем переименовать при импорте (например, при импорте одинаковых имен)
import  dope.nathan.Module.{name => nathan}

// можем импортировать все имена из пространства, неимпортируя отдельные имена
import  dope.nathan.Module.{_, name2 => _}