//Scala => Java
import scala.collection.JavaConverters.bufferAsJavaList
import scala.collection.mutable.ArrayBuffer

// обертывание буфера в java-класс с интерфесом java.util.List
val command = ArrayBuffer("ls", "-al", "/home/cay")
//val pb = new ProcessBuilder(command) - не сработало

// Java => Scala
import scala.collection.JavaConverters.asScalaBuffer
import scala.collection.mutable.Buffer

// нельзя использовать тип ArrayBuffer, обернутый объект будет типа Buffer
//val cmd: Buffer[String] = pb.command() - не сработало

