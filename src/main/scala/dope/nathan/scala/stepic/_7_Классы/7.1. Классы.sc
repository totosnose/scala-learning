class Dog {
  val name = "Gray"
  def woof(): Unit = println("гав")
}

val dog = new Dog

println(dog.name)
dog.woof()


// пустое тело класса
class Cat


// класс с параметрами (основного конструктора класса)
class Dog2(name: String) {
  def woof(): Unit = println(s"$name говорит: гав")
}

val dog2 = new Dog2("Gray")
dog2.woof()


// val - если нужно, чтобы параметр конструктора стал полем класса,
// тогда на них можно ссылаться, как на члены класса
class Dog3(val name: String) {
  def woof(): Unit = println(s"$name говорит: гав")
}

val dog3 = new Dog3("Gray")
println(dog3.name)
dog3.woof()


// во время инициализации классы могут проводить какую-то работу
// в блоках инициализации, например: side-эффекты, изменение переменных и т.п.
class Dog4(name: String) {
  println("$name родился!!!")

  def woof(): Unit = println(s"$name говорит: гав")
}

val dog4 = new Dog4("Gray")
dog4.woof()

