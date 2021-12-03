package dope.nathan.scala.rockthejvm.essentials.part2oop

object Generic {

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // mainQ: If Cat extends Animal, does a List[Cat] extends a List[Animal] ?
  // A1: yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal                       = new Cat
  val coVariantList: CovariantList[Animal] = new CovariantList[Cat]

  // subQ: Can we do animalList.add(new Dog) ?
  // subA: We return List[Animal]
  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
    // A = Cat
    // B = Dog = Aninal => B = Animal
  }

  // A2: No, List[Cat] and List[Animal] are two separated things = INVARIANCE
  class InvariantList[A]
  val inVariantList: InvariantList[Animal] = new InvariantList[Animal]

  class X[A]
  // A3: NO, Changing the semantic: Trainer[Animal] has more experience, including as Trainer[Cat] = CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types (use generic class only for certain types that are either a subclass)
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

}
