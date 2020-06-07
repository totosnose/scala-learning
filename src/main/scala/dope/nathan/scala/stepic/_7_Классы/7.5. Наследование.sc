// Наследование

// любые определения типов могут содержать список базовых типов
//class A extends P1 with P2 with P3 with ...
//trait B extends P1 with P2 with P3 with ...
//object C extends P1 with P2 with P3 with ...
//case class D() extends P1 with P2 with P3 with ...

// определения из базовых типов автоматически становятся доступными
// в их наследниках и экзеплярах
trait  Animal {
  def name: String
}
trait Woofing extends Animal {
  def woof(): Unit = println(s"$name говорит: гав")
}

val woofer = new Woofing {
  override def name: String = "Барбос"
}

woofer.woof()
println(woofer.name)

// Переопределение
// абстрактные определения могут быть заменены на конкретные при наследовании
trait  Animal2 {
  def name: String
  def greeting = s"Привет, я - $name"
}

object Pegasus extends Animal2 {
  // если был def без параметров, то его можно переопределить на val или lazy val
  val name = "Pegasus"

  // переопределение родительской реализации с ее переиспользованием через super
  override def greeting: String =
    s"${super.greeting}, крылатый скакун"
}

println(Pegasus.greeting)

// Наследование от классов
abstract class Animal3
abstract class Plant

trait  Greeter
// если в списке наследования есть класс, он должен идти первым в списке наследования
// DDD - наследование только от одного класса
object Pegasus2 extends Animal3 /*with Plant*/ with Greeter

// если у базового класса есть параметры, то необходимо задать значение для всех параметров базового класса
// при определении объекта или класса наследника
abstract class Animal4(name: String)

// для trait не нужно передавать параметры базового класса, НО!
// при определении конкретного наследника такого trait,
// наследнику будет необходимо унаследовать базовый класс
trait Greeter2 extends Animal4

object Pegasus3 extends Animal4("Pegasus") with Greeter2

// Линеаризация trait
// если есть несколько trait, которые являются потомками одного базового трейта,
// то при создании их общего наследника, то будет происходить ЛИНЕАРИЗАЦИЯ
trait Animal5
trait Greeter3 extends Animal5
trait Mammal extends Animal5

trait Pegasus extends Animal5 with Greeter3 with Mammal


// -----------------------Практика -----------------------------------------------------------------

// бинарное дерево Ints (алгебраический тип данных или ADT)

// узел
// sealed - чтобы невозможно было создать наследников Node в другом файле
sealed trait Node {
  // список значений в дереве слева направо
  def values: List[String]

  // добавление элемента слева
  def +:(value: String): Node

  // альтернативный подход добавления элемента в конец
  def :+(value: String): Node =
    this match {
      case leaf@Leaf(_) => // проверка на конкретный экземпляр
        Branch(leaf, Leaf(value))
      case Branch(left, right) =>
        Branch(left, right :+ value)
    }

  // concat деревьев
  // если известно конечное количество вариаций case class'ов, то можно определить метод на месте
  def ++(node: Node): Node = Branch(this, node)

}

// два подтипа
// разветвление со ссылками на левый и правый узлы
case class Branch(left: Node, right: Node) extends Node {
  override def values = left.values ++ right.values

  // по сути создается разветвление, где к левому поддереву добавляется элемент слева
  override def +:(value: String) = Branch(value +: left, right)
}

// лист (конец дерева)
case class Leaf(value: String) extends Node {
  override def values = List(value)

  // по сути создается новое разветвление, где листом слева будет новый лист (с новым значением),
  // а листом справа будет текущий элемент
  override def +:(value: String) = Branch(Leaf(value), this)
}

// простое дерево
val tree = Branch(Branch(Leaf("one"), Leaf("two")), Leaf("three"))

tree.values

// результат добавление элемента слева
val tree2 = "zero" +: tree

tree2.values

(tree ++ tree2 :+ "four").values
