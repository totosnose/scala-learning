// если Map получен в результате вызова Java-метода
import java.awt.Font
import java.util

// преобразование изменяемого дерева
import scala.collection.JavaConversions.mapAsScalaMap // устаревший вариант
val scores: scala.collection.mutable.Map[String, Int] = new util.TreeMap[String, Int]()
scores += ("Alice" -> 1)

// преобразование Properties
import scala.collection.JavaConversions.propertiesAsScalaMap // устаревший вариант
val props: scala.collection.Map[String, String] = System.getProperties

// чтобы передать scala:collection.Map в метод с параметром util.Map
// нужно неявное обратное преобразование
import scala.collection.JavaConversions.mapAsJavaMap
import java.awt.font.TextAttribute._
val attrs = Map(FAMILY -> "Serif", SIZE -> 12) // Scala Map
val font = new Font(attrs) // Java Map