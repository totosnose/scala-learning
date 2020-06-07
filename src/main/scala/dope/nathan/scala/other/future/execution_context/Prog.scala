package dope.nathan.scala.other.future.execution_context

import dope.nathan.scala.other.future.execution_context.FuturesWithExecutionContext._

object Prog {
  def taskA(): Unit = {
    debug("Starting taskA")
    Thread.sleep(1000) // wait 1secs
    debug("Finished taskA")
  }

  def taskB(): Unit = {
    debug("Starting taskB")
    Thread.sleep(2000) // wait 2secs
    debug("Finished taskB")
  }

  def main(args: Array[String]): Unit = {
    debug("Starting Main")
    taskA()
    taskB()
    debug("Finished Main")
  }
}
