package dope.nathan.scala.rockthejvm.essentials.part3fp

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]

  // higher-order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B

  def ++[B >: A](list: MyList[B]): MyList[B]

  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start

  override def printElements: String = ""

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = Cons(element, this)

  /*
    [1, 2, 3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty)))
   */
  override def map[B](transformer: A => B): MyList[B] = {
    Cons(transformer(h), t.map(transformer))
  }

  /*
    [1, 2] ++ [3, 4, 5]
    = new Cons(1, [2] ++ [3, 4, 5])
    = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  /*
    [1, 2].flatMap(n => [n, n + 1])
    = [1].flatMap(n => [n, n + 1]) ++ [2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty
    = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  /*
    [1, 2, 3].filter(n % 2 == 0) =
       [2, 3].filter(n % 2 == 0) =
       = new Cons(2, [3].filter(n % 2 == 0))
       = new Cons(2, Empty.filter(n % 2 == 0))
       = new Cons(2, Empty)
   */
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedTail: MyList[A]): MyList[A] = {
      if (sortedTail.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedTail.head) <= 0) Cons(x, sortedTail)
      else Cons(sortedTail.head, insert(x, sortedTail.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

}

object TestList extends App {
  val intList      = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneIntList = Cons(1, Cons(2, Cons(3, Empty)))
  val intList2     = Cons(4, Cons(5, Empty))
  val strList      = Cons("a", Cons("b", Cons("c", Empty)))

  println(intList.tail.tail.head)
  println(intList.add(4).head)
  println(intList)

  println(strList)

  println(intList.map(_ * 2))
  println((intList ++ intList2).toString)
  println(intList.flatMap(elem => Cons(elem, new Cons(elem + 1, Empty))))

  println(intList == cloneIntList)

  intList.foreach(println)
  println(intList.sort((x, y) => y - x))
  println(intList.zipWith[String, String](strList, _ + "-" + _))
  println(intList.fold(0)( _ + _))

  // for comprehensions
  val comb = for {
    n <- intList
    s <- strList
  } yield n + "-" + s
  println(comb)
}
