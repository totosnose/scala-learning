package dope.nathan.other.Futures_and_ExecutionContext

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