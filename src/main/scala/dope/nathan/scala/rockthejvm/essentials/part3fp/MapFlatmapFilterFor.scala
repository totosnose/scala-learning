package dope.nathan.scala.rockthejvm.essentials.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  val numbers = List(1, 2, 3, 4)
  val chars   = List('a', 'b', 'c', 'd')

  val res = numbers.flatMap { n =>
    chars.map(c => s"$n-$c")
  }

  println(res)

}
