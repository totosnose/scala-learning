// вывод в консоль
println("hello friend")

// сокращение через App
object Main extends App {
  println("hello friend")
  println("by mr.robot")
}

// java
object Main2 {
  def main(args: Array[String]): Unit = {
    println("hello friend")
  }
}

// чтение с консоли
import scala.io.StdIn.readLine

object Main3 {
  def main(args: Array[String]): Unit = {
    println("hello, enter line")
    val line = readLine()
    print("you entered: ")
    println(line)
  }
}