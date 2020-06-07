import scala.collection.mutable.ArrayBuffer

// def append(elems: A*): Unit
var b = new ArrayBuffer[Int]
b.append(1)

// def appendAll(xs: TraversableOnce[A]): Unit
b.appendAll(b)
b.mkString(", ")

// def count(p: (A) => Boolean): Int (предикат)
b.count(_ > 0)

//def += (elem: A): ArrayBuffer.this.type (this, для цепочки вызовов)
b += 4 -= 5 -=1 +=6

// def copyToArray[B >: A] (xs: Array[B]): Unit
var a = Array[Int](2, 1)
b.copyToArray(a)
a.mkString(", ")

// def max[B>: A] (implicit cmp: Ordering[B]): A
a.max

// def padTo[B>: A, That] (len: Int, elem: B)
// (implicit bf: CanBuildFrom [ArrayBuffer[A]])
// [ArrayBuffer[A], B, That]): That
// или
// def padTo (len: Int, elem: A) ArrayBuffer[A]

a.padTo(5, Int)
a.mkString(" ")



