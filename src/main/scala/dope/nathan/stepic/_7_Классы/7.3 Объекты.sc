// Объект - экземпляр уникального типа
// Можно рассматривать, как модули (набор определений)

object Pegasus {
  val name: String = "Pegasus"
  def introduce = "I'm the winged stallion"

  // могут содержать блоки инициализации (вызов при первом упоминании объекта)
  println("Pegasus is initialazed")
}

// можно ссылаться по имени
// получение типа объекта через "object.type"
val pegasus: Pegasus.type = Pegasus

// статика через объекты-компаньоны
// (для классов, либо для абстрактных типов)
class Cat(val name: String, val age: Int)

// объект-компаньон имеет то же имя, что и тип
// должен быть определен в том же исходном файле
object Cat {
  def apply(name: String, age: Int): Cat =
    new Cat(name, age)
}

// "красивый" метод toString
case object Unicorn

// extractor objects - объекты-компаньоны с реализацией метода unapply
// unapply позволяет "разбирать" объекты, получая аргументы, из которых он был создан
// в case class реализован по-умолчанию => удобный паттерн матчинг
case class Dog(name: String, ownerName: String)
val rex = Dog("Rex", "Josh")

rex match {
  case Dog(name, ownerName) => println(s"My owner is $ownerName")
  case _ => println("I am not a dog")
}
