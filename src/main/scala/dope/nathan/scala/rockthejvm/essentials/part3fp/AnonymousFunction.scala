package dope.nathan.scala.rockthejvm.essentials.part3fp

object AnonymousFunction extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2
  val doubler2: Int => Int = _ * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (x, y) => x + y
  val adder2: (Int, Int) => Int = _ + _

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // instance of AnonymousFunction
  println(justDoSomething()) // value

  def func = (s: String) => s + " !"
  def func2 = (s: String) => s + " ?"

}
