
// класс public по-умолчанию
class Counter {
  private var value = 0           // обязательная инициализация полей
  def increment() { value += 1 }  // методы public по-умолчанию
  def current() = value
}

// создание объекта
val myCounter = new Counter // или new Counter()
myCounter.increment()       // или myCounter.increment - без скобок, если без параметров
println(myCounter.current())

// ставить или не ставить скобки? (best practise)
// методы мутаторы (mutator) - изменяют состояние объекта
myCounter.increment()

// методы аксессоры (accessor) - не изменяют состояние обекта
println(myCounter.current)

// объявив current без скобок - обязываешь пользователя придерживаться синтаксиса