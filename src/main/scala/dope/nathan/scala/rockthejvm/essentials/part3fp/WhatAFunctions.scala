package dope.nathan.scala.rockthejvm.essentials.part3fp

object WhatAFunctions extends App {

  // DREAM: use functions as first class elements
  // PROBLEM: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types OOTB = Function1[A, B]

  val stringToInt = new Function1[String, Int] {
    override def apply(element: String): Int = element.toInt
  }

  println(stringToInt("1") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(1, 4))

  def superAdder: Int => Int => Int = a => b => a + b

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
