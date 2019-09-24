// с наследованием и доступом связано несколько особенностей,
// управляемых ключевыми словами - модификаторами

// FINAL
// final + class - нельзя создавать наследников класса
final class Human
//class SuperHuman extends Human // error!

// final + любой член of class или trait - нельзя переопределять член класса
class Human2{
  final def powerLevel: Int = 100
}

class SuperHuman extends Human2 {
//  override def powerLevel: Int = 404
}


// SEALED
// - более мягкая версия final =>
// sealed + class и trait - можно создать наследников, но только в том же файле,
// где находится предок. По сути, означает, что заранее известен набор наследников.
sealed trait Weekday

case object Monday extends Weekday
case object Tuesday extends Weekday
case object Wednesday extends Weekday
case object Thursday extends Weekday
case object Freeday extends Weekday
case object Saturday extends Weekday
case object Sunday extends Weekday


// PRIVATE
// private + член of class или trait - член может использоваться только
// в данном классе или компаньоне класса

class Dog{
  private val kind: String = "dog"
}

object Dog {
  def kindOf(dog: Dog): String = dog.kind
}

//(new Dog).kind //error!

// private [this] - член класса м.б. использован только в данном объекте,
// т.о. нельзя использовать ни в компаньене, ни в других экземплярах того же самого класса
class Bird {
  private [this] val kind: String = "bird"
//  def kindOf(bird: Bird): String = bird.kind // error!
}

// private [...] - если необходимо разрешить доступ к члену класса, например,
// для всех определний в каком-то пакете или в вышестоящем классе (полезно для вложенных классов)
//package dope.nathan.stepic

abstract class Cat {
  private [Cat] val age: Int
  private [stepic] val kind: String
  private [nathan] val name: String
}

// PROTECTED
// - связан непосредственно с наследованием
// protected + член of class или trait - доступ к этому члену класса есть только
// у наследников этого класса
trait Animal {
  protected val kind: String
}

class Dog2 extends Animal {
  protected val kind = "dog"
  def kindOf(dog: Dog2): String = dog.kind
}

//(new Dog2).kind // error!

// protected [...] - аналогично private [...]

// к параметрам конструктора, если они определены, как val, можно дописывать модификаторы и override
trait Animal2 {
  protected val kind: String = "animal"
}

class Mammal(override protected val kind: String) extends Animal2 {
  def printKind = println(kind)
  class Bar(bar1: Int) {
    private[this] val bar2: Boolean = true
  }
}

(new Mammal("dog")).printKind
//(new Mammal("dog")).kind // error!

val m = new Mammal("")
new m.Bar(1)