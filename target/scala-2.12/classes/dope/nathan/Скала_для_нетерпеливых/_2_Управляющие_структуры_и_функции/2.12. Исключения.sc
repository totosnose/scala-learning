// нет checked исключений
// throw - тип Nothing
throw new IllegalArgumentException("x should not be negative")

// если одна из ветвей имеет тип Nothing,
// то тип всего выражения - тип другой ветви
val x = - 1
if (x >= 0) { math.sqrt(x)
} else throw new IllegalArgumentException("x should not be negative")

// try/catch основан на patter matching
// _: - анонимная переменная
import java.io.{IOException, InputStream}
import java.net.{MalformedURLException, URL}
val url = "http://horstmann.com/fred-tinygif"
def process(url: URL)

try {
  process(new URL(url))
} catch {
  case _: MalformedURLException => println("Bad URL:" + url)
  case ex: IOException => ex.printStackTrace()
}

// try/finally - освобождение ресурсов независимо от
// наличия или отсутствия исключений
val in = new URL(url).openStream()
def process1(stream: InputStream)

try {
  process1(in)
} finally {
  in.close()
}

// try/catch/finally
try {
  process1(in)
} catch {
  case _: MalformedURLException => println("Bad URL:" + url)
  case ex: IOException => ex.printStackTrace()
} finally {
  in.close()
}