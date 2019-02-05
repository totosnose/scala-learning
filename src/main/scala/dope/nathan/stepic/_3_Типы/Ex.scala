package dope.nathan.stepic._3_Типы

import scala.io.StdIn._

object Ex extends App {
  val in = readLine().toInt
  for (i <- 1 to in) {
    if ((in >> 1) != 0)
      println(i)
  }
}
