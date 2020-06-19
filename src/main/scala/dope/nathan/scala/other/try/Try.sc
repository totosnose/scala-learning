import scala.util.{Failure, Success, Try}
val name: Option[String] = Some("Anton")
case class Details(fileName: String, error: String)
val details = Details("", "")

def process(details: Details): Unit = {
    println(details)
  }

def incorrectProcessing(details: Details): Unit = {
    println(details)
  }

Try(name.get) match {
  case Success(v) => process(details.copy(fileName = v))
  case Failure(ex) => incorrectProcessing(details.copy(error = ex.getMessage))
}

Try(name.get).fold(
    ex => incorrectProcessing(details.copy(error = ex.getMessage)),
    v => process(details.copy(fileName = v))
  )

Try(name.get)
  .map(n => process(details.copy(fileName = n)))
  .getOrElse(incorrectProcessing(details.copy(error = "Some error!")))
