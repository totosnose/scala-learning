// в Scala getters & setters генерируются автоматически
// (но с другими именами)

// в примере Scala сгенерит для JVM класс с приватым полем age
class Person0 {
  var age = 0                 // public getter & setter
  private var name = "Nick"   // private getter & setter

  val number = 1              // public getter
  private val lname = "Lif"   // private getter

  private[this] var essence = "x" // методы не создадутся (см. 5.4)
}

// работа с get & set
val nick = new Person0
println(nick.age)             // nick.age() - getter/чтение
nick.age = 21                 // nick.age_=(21) - setter/запись

// переопределение методов чтения и записи
class Person {
  private var privateAge = 0

  def age = privateAge
  // "age_=" - setter
  def age_= (newValue: Int): Unit = {
    if (newValue > privateAge) privateAge = newValue
  }
}

// пример в работе
val fred = new Person
fred.age = 30
fred.age = 21
println(fred.age)

