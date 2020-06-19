//package dope.nathan.scala.pckg

class Protection

class Person {
  protected[this] val isProtected = true // ограничивает доступность к текущему объекту, как private[this] (см. 5.4)
  protected def createProtection = new Protection // доступно только для подклассов
//  protected[pckg] def description = "Будет доступен в собственном пакете" // доступ в собственном пакете
}

class Witness extends Person {
  val protection = createProtection
}

val person = new Person
//person.createProtection // невозможно