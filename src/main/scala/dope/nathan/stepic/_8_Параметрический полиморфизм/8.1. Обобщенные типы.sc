// обобющенный тип

final case class NamedInt(name: String, value: Int)

final case class NamedDouble(name: String, value: Double)

// приведение к общему типу
// [A] - имя нового параметра типа; на него можно сослаться в конструкторе, теле класса и т.п.
final case class Named[A](name: String, value: A)

Named[Int]
Named[Double]

// параметр типа не обязательно передавать явно при создании экземпляра обобщенного типа,
// если компилятор сам может вычислить его из типов передаваемых аргументов
final case class Named2[A](name: String, value: A) {
  def withName(newName: String): Named[A] =
    Named(newName, value)
}


// когда ссылаемся на обобщенный тип, можно передавать его в другие обобщенные типы в теле
final case class Named3[A](name: String, value: A) {
  def toMap: Map[String, A] = Map(name -> value)
}

// если обобщенный тип имеет внутри себя обобщенные методы, то
// и параметры, и возвращаемый результат, и тело таких методов
// могут ссылаться, как на параметр типа своего метода [B],
// так и на параметр типа обобщенного класса [A]
final case class Named4[A](name: String, value: A) {
  def mapValue[B](f: A => B): Named[B] =
    Named(name, f(value))
}

// абстрактные типы (аналогично неабстрактным)
trait Named5[T] {
  def name: String
  def value: T
  def modify[A](f: A => A): Named5[A]
}

// при создании экземпляра абстрактного типа компилятор проверяет,
// что выражения, которые определяют абстрактные члены имеют совместимый тип
// с параметром типа, переданным в конкретную реализацию
def namedInt(n: String, v: Int): Named5[Int] =
  new Named5[Int]{
    def name = n
    def value = v
//    def modify[Int](f: Int => Int) = namedInt(n, f(v)) // wtf?
    override def modify[A](f: A => A) = ???
  }


// при наследовании от абстрактного типа
trait Named6[A] {
  def name: String
  def value: A
}

// необходимо передать уже известный параметр типа
// возможно опеределенный на базе какого-либо параметра типа (List[A])
case class Named6List[A](name: String, value: List[A]) extends Named6[List[A]]


// любой тип может иметь несколько параметров типа (правила аналогичны)
final case class Dict[K, V](items: List[(K, V)])


// параметры типов могут иметь границы:

trait Item {
  def key: String
  def value: String
}

trait Item1[K, V] {
  def key: K
  def value: V
}

// "сверху" - т.е. тип д.б.подтипом указанного типа
final case class Dict1[I <: Item](items: List[I]) {

  // "снизу" - т.е. тип д.б.надтипом указанного типа
  def +:[J >: I](item: J): Dict1[J] = Dict1(item :: items)
}

def dict[I <: Item] (items: I*): Dict1[I] = Dict1(items.toList)


// параметры типов (type params) могут ссылаться друг на друга в ограничениях
case class Dict2[K, V, I <: Item1[K, V]](items: List[I])

// Рекурсивно ограниченная квантификация (f-bounded quantification)
trait Comparable[A <: Comparable[A]] {
  def compare(x: A): Int
}


// -----------------------Практика -----------------------------------------------------------------

// Исправление особенности типа Map (замена value при совпадении key) через создание нового типа
final case class MultiMap[Key, Value](items: Map[Key, List[Value]]) {
  override def toString: String =
    s"MultiMap(${items.map{ case (key, values) =>
      s"$key -> ${values.mkString(", ")}"}
      .mkString("; ")
    })"

  def add(key: Key, value: Value): MultiMap[Key, Value] =
    MultiMap(items + (key -> (items.get(key) match {
      case Some(values) => value :: values
      case None => List(value)
    })))

  def map[B](f: Value => B): MultiMap[Key, B] =
    MultiMap(items.mapValues(_.map(f)))
}

// ps: у case class уже есть метод apply
object MultiMap {
 def apply[Key, Value](items: (Key, Value)*): MultiMap[Key, Value] =
   MultiMap(items.groupBy(_._1).mapValues(_.map(_._2).toList))
}

val dict = MultiMap(
  "apple" -> "fruit",
  "pear" -> "fruit",
  "carrot" -> "orange",
  "apple" -> "delicious",
  "carrot" -> "vegetable"
)

dict.add("pear", "delicious").add("melon", "yellow")

dict.map(_.toUpperCase)
dict.map(_.length)