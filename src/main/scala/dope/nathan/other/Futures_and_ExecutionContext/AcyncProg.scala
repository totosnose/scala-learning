package dope.nathan.other.Futures_and_ExecutionContext

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

// Resume:
// * Each task is run on its own thread
// * Both tasks are starting at the same time and run concourrently
// * The main program continues before the tasks completes
// * The whole program took only 2s to execute (the duration of the longest task)

object AcyncProg {
  def taskA(): Future[Unit] = Future {
    debug("Starting taskA")
    Thread.sleep(1000) // wait 1secs
    debug("Finished taskA")
  }

  def taskB(): Future[Unit] = Future {
    debug("Starting taskB")
    Thread.sleep(2000) // wait 2secs
    debug("Finished taskB")
  }

  def main(args: Array[String]): Unit = {
    debug("Starting Main")
    val futureA = taskA()
    val futureB = taskB()
    debug("Continuing Main")
    // wait for both future to complete before exiting
    Await.result(futureA zip futureB, Duration.Inf)
  }
}
