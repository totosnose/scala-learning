package dope.nathan.scala.rockthejvm.essentials.part3fp

import scala.annotation.tailrec
import scala.concurrent.Future

object Curring extends App {

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, x)

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  def plusOne: Int => Int = (x: Int) => x + 1
  def plusOneTenTimes: Int => Int = nTimesBetter(plusOne, 10)

  println(plusOneTenTimes(1))

  def superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  def add3: Int => Int              = superAdder(3)

  println(add3(10))
  println(superAdder(3)(10))

  def curriedFormatter(c: String)(d: Double): String = c.format(d)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry(f: (Int, Int) => Int): Int => Int => Int =
    a => b => f(a, b)

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (a, b) => f(a)(b)

  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    a => g(f(a))

  def superAdder2: Int => Int => Int = toCurry(_ + _)

  def add4 = superAdder2(4)
  println(add4(17))

  def simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  def add2 = (x: Int) => x + 2
  def times3 = (x: Int) => x * 3

  val add2AndTimes3 = compose(add2, times3)
  val Times3AndAdd2 = andThen(add2, times3)

  println(add2AndTimes3(4))
  println(Times3AndAdd2(4))
}