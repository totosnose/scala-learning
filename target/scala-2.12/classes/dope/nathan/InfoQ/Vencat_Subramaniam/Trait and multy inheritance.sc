// trait as interface and abstract class at the same time
trait Friend {
  val name: String
  def listen() = println("I'm " + name + " listening")
}

class Human(val name: String) extends Friend

class Animal(val name: String)
// как множественное наследование
class Dog(override val name: String) extends Animal(name) with Friend
// смотри на объект
class Cat(override val name: String) extends Animal(name)
// полиморфизм?
def seekHelp(friend: Friend) = {
  friend.listen()
}

val sam = new Human("Sam")
val buddy = new Dog("Buddy")
val bob = new Cat("Bob") with Friend // anonymous inner class

sam.listen()
buddy.listen()
bob.listen()

seekHelp(sam)
seekHelp(buddy)
seekHelp(bob)


