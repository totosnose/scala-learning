class Person {
  val id                        = 0
  override def toString: String = s"${getClass.getName} with $id" // override обязательно
}

class Student extends Person {
  override val id       = 1              // override обязательно
  override def toString = super.toString // вызов родительского метода super
}
