import scala.io._

object Main extends App{
  trait StringProcessor {
    def process(input: String): String
  }

  val tokenDeleter = new StringProcessor {
    def process(input: String): String = input.replaceAll("""[.,:;"'\-()!?]""", "")
  }

  val shortener = new StringProcessor {
    def process(input: String): String = if (input.length > 20) input.substring(0, 19) + " ..." else input
  }

  val toLowerConvertor = new StringProcessor {
    def process(input: String): String = input.toLowerCase
  }

  val res = shortener.process(tokenDeleter.process(toLowerConvertor.process(StdIn.readLine())))

  println(res)
}

