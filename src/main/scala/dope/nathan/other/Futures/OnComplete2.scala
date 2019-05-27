package dope.nathan.other.Futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

// Run one thing, but don’t block, use callback (callback method: onComplete)
object OnComplete2 extends App {
  println("starting calculation ...")
  val f = Future {
    sleep(Random.nextInt(500))
    -42
    //13
    //42/0
  }
  println("before onComplete")


  private def validateData(data: Int): Unit = {
    data match {
      case a => if (a < 0)
        throw new Exception(s"Exeption x > 0")
      case _ =>
    }
  }

  f.map(data => {
    validateData(data)
    Map("data" -> data)
  }).recoverWith {
    case e: Throwable =>
      println("Can't fetch the note attachments due to exception", e)
      Future.successful(Map("data" -> 0))
  }.map(m => m.values.foreach(println(_)))

  // do the rest of your work
  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)
  sleep(2000)
}
