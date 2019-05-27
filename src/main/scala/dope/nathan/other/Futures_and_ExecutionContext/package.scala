package dope.nathan.other

// http://www.beyondthelines.net/computing/scala-future-and-execution-context/
package object Futures_and_ExecutionContext {

  def debug(message: String): Unit = {
    val now = java.time.format.DateTimeFormatter.ISO_INSTANT
      .format(java.time.Instant.now)
      .substring(11, 23) // keep only time component
    val thread = Thread.currentThread.getName()
    println(s"$now [$thread] $message")
  }

}

// There are 3 ways to ways to use the global execution context:

// 1. Importing the global execution context:
//  import scala.concurrent.ExecutionContext.Implicits.global

// 2. Using an implicit variable:
//  implicit val executor =  scala.concurrent.ExecutionContext.global

// 3. Passing explicitly the execution context
//  Future { /* do something */ }(executor)
