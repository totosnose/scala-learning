// val - никогда не будет изменяться
class Message {
  val timeStamp = new java.util.Date() // val = private + getter (т.е. только чтение)
}



// не может быть изменено клиентом, но можно другим путем
class Counter {
  private var value = 0          // private + метод чтения def current
  def increment() { value += 1 }
  def current = value            // current без скобок в объявлении
}

val myCounter = new Counter
val n = myCounter.current

// резюме
// реализация свойств (переменных состояния):
// 1. var foo: Scala синтезирует методы чтения и записи;
// 2. val foo: Scala синтезирует только методы чтения;
// 3. Сами определяем методы foo и foo_=;
// 4. Cами определяем метод foo.

// нельзя создать свойство только с setter'ом