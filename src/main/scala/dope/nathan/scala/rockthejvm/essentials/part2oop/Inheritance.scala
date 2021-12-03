package dope.nathan.scala.rockthejvm.essentials.part2oop

object Inheritance {

  // preventing using
  //  1 - use private on member (use member into class)
  //  2 - use protected on member (use member into class and subclasses)

  // preventing overrides
  //  1 - use final on member
  //  2 - use final on the entire class
  //  3 - seal the class = softer than final (extend classes in just THIS FILE)

  // traits vs abstract classes
  // 1 - traits do not have construct parameters (in Scala 3, this is now possible)
  // 2 - multiple traits may be inherited by the same class
  // 3 - trait = behavior, abstract class = "thing"

}
