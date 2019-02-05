class Counter {
  private var value = 0                   // обращение допустимо во всех объектах своего класса
  private[this] var valueSuperPrivate = 0 // обращение допустимо только к полю текущего объекта
  def increment() { value += 1 }

  // метод может иметь доступ к приватным полям во всех объектах своего класса
  def isLess(other: Counter) = value < other.value // имеет доступ к приватному полю другого объекта
//  def isLess2(other: Counter) = valueSuperPrivate < other.valueSuperPrivate // невозможно
}

// для private-полей класса Scala генерирует private-методы
// для private-полей объекта Scala не генерирует get/set-методы

// права доступа к отдельным классам
// private[ClassName] - методы только указанного класса имеют доступ к полю
// может быть определяемого или внешнего класса (см. 5.8)