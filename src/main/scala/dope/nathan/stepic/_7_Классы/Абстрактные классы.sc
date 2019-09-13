// trait - абстрактный тип, и почти, как класс, но:
// - нет списка параметров (в версии 2.12)
// - некоторые члены могут быть не определны

trait Animal {
  def name: String
  val greeting: String = s"Hi, I'm $name"
}


// abstract class:
// - некоторые члены могут быть не определны
abstract class Animal2(name: String) {
  val greeting: String = s"Hi, I'm $name"
}

// пустые тела
abstract class Animal3(name: String)

trait Mammal

// создание экземпляра абстрактного типа напрямую невозможно -
// необходимо переопределение методов

// фактически это создание нового конкретного, но анонимного типа (класса),
// являющегося подтипом абстрактного класса и доопределяющего этот абстрактный класс
val animal = new Animal {
  override def name = "Bobik"
}

// даже, если все абстрактные члены определены или их нет вовсе,
// нужно поставить {}
val mammal = new Mammal {}


// блоки инициализации в абстрактных кллассах
trait Animal4 {
  def name: String
  val greeting: String = s"Hi, I'm $name"

  println("$name is created")
}

val animal4 = new Animal4 {
  def name = "Bobik"
}

// при создании абстрактного класса необходимо передавать все параметры
val animal2 = new Animal2("Bobik"){}