// обобющенный тип

final case class NamedInt(name: String, value: Int)

final case class NamedDouble(name: String, value: Double)

// приведение к общему типу
// [A] - имя нового параметра типа; на него можно сослаться в конструкторе, теле класса и т.п.
final case class Named[A](name: String, value: A)

Named[Int]("Nathan", 6000)
Named[Double]("Nathan", 6000.0d)


// параметр типа не обязательно передавать явно при создании экземпляра обобщенного типа,
// если компилятор сам может вычислить его из типов передаваемых аргументов
final case class Named2[A](name: String, value: A) {
  def withName(newName: String): Named[A] =
    Named(newName, value)
}

Named2("Nathan", 6000).withName("Anton")


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

def fooo(a: Int): String  = s"${a.toString}000"

Named4("Nathan", 6).mapValue(fooo)
// или
Named4("Nathan", 6).mapValue(x => s"${x.toString}000")


// абстрактные типы (аналогично неабстрактным)
trait Named5[T] {
  def name: String
  def value: T
  def modify[A](f: A => A): Named5[A]
}

// при создании экземпляра абстрактного типа компилятор проверяет,
// что выражения, которые определяют абстрактные члены имеют совместимый тип
// с параметром типа, переданным в конкретную реализацию
def foo: Int => Int = _ + 1
def namedInt(n: String, v: Int): Named5[Int] =
  new Named5[Int]{
    def name = n
    def value = v
//    def modify[Int](f: Int => Int) = namedInt(n, foo(v)) // wtf?
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

// Рекурсивно ограниченная квантификация (f-bounded quantification) (см. Бонус)
trait Comparable[A <: Comparable[A]] {
  def compare(x: A): Int
}

// -----------------------Бонус --------------------------------------------------------------------
// F-bounded quantification

// отстой! как минимум, потому что возвращаемое значение - супертип
{
  trait Entity{
    def create(): Entity
    def read(id: Long): Option[Entity]
    def update(f: Entity => Entity): Entity
    def delete(id: Long): Unit
  }

  class Apple extends Entity {
    override def create(): Entity = ???
    override def read(id: Long): Option[Entity] = ???
    override def update(f: Entity => Entity): Entity = ???
    override def delete(id: Long): Unit = ???
  }

  class Orange extends Entity {
    override def create(): Entity = ???
    override def read(id: Long): Option[Entity] = ???
    override def update(f: Entity => Entity): Entity = ???
    override def delete(id: Long): Unit = ???
  }
}

// лучше, но все еще отстой, потому что в качестве типа можно "просунуть", что угодно
{
  trait Entity[E]{
    def create(): E
    def read(id: Long): Option[E]
    def update(f: E => E): E
    def delete(id: Long): Unit
  }

  class Apple extends Entity[Apple] {
    override def create(): Apple = ???
    override def read(id: Long): Option[Apple] = ???
    override def update(f: Apple => Apple): Apple = ???
    override def delete(id: Long): Unit = ???
  }

  class Orange extends Entity[Orange] {
    override def create(): Orange = ???
    override def read(id: Long): Option[Orange] = ???
    override def update(f: Orange => Orange): Orange = ???
    override def delete(id: Long): Unit = ???
  }

  // тут начинается зашквар
  class FlyingSaucer

  class Mango extends Entity[FlyingSaucer] {
    override def create(): FlyingSaucer = ???
    override def read(id: Long): Option[FlyingSaucer] = ???
    override def update(f: FlyingSaucer => FlyingSaucer): FlyingSaucer = ???
    override def delete(id: Long): Unit = ???
  }
}

// лучше, но отстой продолжается, т.к. соответствующие типы все еще можно "подсовывать" друг другу
{
  trait Entity[E <: Entity[E]]{
    def create(): E
    def read(id: Long): Option[E]
    def update(f: E => E): E
    def delete(id: Long): Unit
  }

  class Apple extends Entity[Apple] {
    override def create(): Apple = ???
    override def read(id: Long): Option[Apple] = ???
    override def update(f: Apple => Apple): Apple = ???
    override def delete(id: Long): Unit = ???
  }

  // тут начинается зашквар
  class Orange extends Entity[Apple] {
    override def delete(id: Long): Unit = ???
    override def create() = ???
    override def read(id: Long) = ???
    override def update(f: Apple => Apple) = ???
  }
}

// отлично! теперь невозможно сделать вышеописанную дичь
{
  trait Entity[E <: Entity[E]]{ self: E =>
    def create(): E
    def read(id: Long): Option[E]
    def update(f: E => E): E
    def delete(id: Long): Unit
  }

  class Apple extends Entity[Apple] {
    override def create(): Apple = ???
    override def read(id: Long): Option[Apple] = ???
    override def update(f: Apple => Apple): Apple = ???
    override def delete(id: Long): Unit = ???
  }

  class Orange extends Entity[Orange] {
    override def create(): Orange = ???
    override def read(id: Long): Option[Orange] = ???
    override def update(f: Orange => Orange): Orange = ???
    override def delete(id: Long): Unit = ???
  }
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

// про View Bounds and Context Bounds
// https://stackoverflow.com/questions/4465948/what-are-scala-context-and-view-bounds