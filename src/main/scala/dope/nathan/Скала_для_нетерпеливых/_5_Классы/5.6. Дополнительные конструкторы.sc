// в Scala может быть произвольное количество конструкторов
// primary constructor (главный - один)
// auxiliary constructors (дополнительные - много)

// Дополнительные - похожи на Java конструкторы, за исключением:
// + они называются this
// + каждый дополнительный конструктор должен начинаться
// вызовом дополнительного конструктора, объявленного выше,
// или вызовом главного конструктора

// класс без явного объявления главного конструктора
// получает главный конструктор без аргументов
// + два дополнительных конструктора в классе
class Person {
  private var name = ""
  private var age = 0

  def this(name: String) {  // дополнительный конструктор
    this()                  // вызов главного конструктора
    this name = name
  }

  def this(name: String, age: Int) {  // другой дополнительный конструктор
    this(name)                        // вызов предыдущего дополнительного конструктора
    this.age = age
  }
}

// создание объекта 3мя способами
val p1 = new Person // главный конструктор
val p2 = new Person("Fred") // 1й доп. конструктор
val p3 = new Person("Fred", 42) // 2й доп. конструктор
