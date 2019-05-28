import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

// Неявные параметры — это параметры, которые могут быть автоматически переданы в функцию
// из контекста ее вызова. Для этого в нем должны быть однозначно определены и помечены
// ключевым словом implicit переменные соответствующих типов
def printContext(implicit ctx: String) = println(ctx)

implicit val ctx = "Hello world"

printContext


// неявные параметров с поддержкой обобщенных типов
case class Context[T](message: String)

def printContextAwared[T](x: T)(implicit ctx: Context[T]) = println(s"${ctx.message}: $x")

implicit val ctxInt = Context[Int]("This is Integer")
implicit val ctxStr = Context[String]("This is String")

printContextAwared(1)
printContextAwared("string")


// неявные параметры могут быть методами
implicit def dateTime: LocalDateTime = LocalDateTime.now()

def printCurrentDateTime(implicit dt: LocalDateTime) = println(dt.toString)

printCurrentDateTime
Thread.sleep(1000)
printCurrentDateTime

// неявные параметры-функции могут принимать неявные параметры
implicit def dateTime(implicit zone: ZoneId): ZonedDateTime = ZonedDateTime.now(zone)

def printCurrentDateTime2(implicit dt: ZonedDateTime) = println(dt.toString)

implicit val utc = ZoneOffset.UTC

printCurrentDateTime2