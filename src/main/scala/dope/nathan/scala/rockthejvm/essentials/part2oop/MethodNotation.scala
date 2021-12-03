package dope.nathan.scala.rockthejvm.essentials.part2oop

object MethodNotation extends App {
  val alice = new Person("Alice", "Alice in WL")
  val woody = new Person("Woody", "Woody Woodpecker")

  println(alice likes alice.favoriteMovie)
  println(alice +  woody)
  println(!alice)
  println(alice.isAlive)
  println(alice())

}

class Person(val name: String, val favoriteMovie: String) {
  def likes(movie: String): Boolean = movie == favoriteMovie
  def +(person: Person): String = s"${this.name} is handing out with ${person.name}"
  def unary_! : String = s"$name, what the heck?!"
  def isAlive: Boolean = true
  def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
}
