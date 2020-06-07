package dope.nathan.scala.other.future.execution_context

import scala.concurrent.ExecutionContext

// http://www.beyondthelines.net/computing/scala-future-and-execution-context/

object FuturesWithExecutionContext {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  def debug(message: String): Unit = {
    val now = java.time.format.DateTimeFormatter.ISO_INSTANT
      .format(java.time.Instant.now)
      .substring(11, 23) // keep only time component
    val thread = Thread.currentThread.getName
    println(s"$now [$thread] $message")
  }

}
