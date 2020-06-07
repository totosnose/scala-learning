// case class - mutable структура с набором полей
case class Cat (name: String, age: Int) {
  // как и у обычного класса может быть тело
  def greeting: String = s"Hi, I'm $name"
}

// для case class автоматически генерируется объект-компаньон,
// который содержит несколько методов, например apply, который позволяет
// создавать объекты без ключевого слова new
val cat = Cat("Norbert", 3)

// каждый параметр case class автоматически становится его полем (т.е. val)
println(cat.name)
println(cat.age)

// так же автоматически генерируются методы:
// toString
println(cat)
// equals и hashcode
cat == Cat("Norbert", 3)
// copy со списком параметров, таких же, как и в конструкторе и со значениями
// по умолчанию из текущего объекта
cat.copy(name = "Барселон")

