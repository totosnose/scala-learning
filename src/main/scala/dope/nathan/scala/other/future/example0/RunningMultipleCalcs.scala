package dope.nathan.scala.other.future.example0

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object RunningMultipleCalcs extends App {
  println("starting futures...")
  val result1 = Cloud.runAlgorithm(10)
  val result2 = Cloud.runAlgorithm(20)
  val result3 = Cloud.runAlgorithm(30)

  println("before for-comprehension")   // it can be map, flatMap, etc. (come back to package file and read)
  val result = for {                    // : Future[Int] !!!
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield r1 + r2 + r3

  println("before onSuccess")
  result onComplete {
      case Success(value) => println(s"total = $value")
      case Failure(e) => e.printStackTrace()
  }

  println("before sleep at the end")
  Sleeper.sleep(2000)  // important: keep the jvm alive
}

object Cloud {
  def runAlgorithm(i: Int): Future[Int] = Future {
    Sleeper.sleep(Random.nextInt(500))
    val result = i + 10
    println(s"returning result from cloud: $result")
    result
  }
}

object Sleeper {
  def sleep(time: Long) { Thread.sleep(time) }
}