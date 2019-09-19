import scala.annotation.tailrec
import scala.io.StdIn._

object Main extends App{
  def kOrder(list: List[Int], k: Int): Int = {
    def go(array: Array[Int] = list.toArray, low: Int = 0, high: Int = list.length - 1): Int = {
      val mid: Int = partition(array, low, high)

      mid match {
        case m if m > k => go(array, low, mid)
        case m if m < k => go(array, mid + 1, high)
        case _ => array(mid)
      }
    }
    go()
  }

  def partition(a: Array[Int], l: Int, r: Int): Int = {
    val p = a(r)
    var i = l
    for (j <- l to r) {
      if (a(j) < p) {
        swap(a, i, j)
        i += 1
      }
    }
    swap(a, i, r)
    i
  }


  def swap(a: Array[Int], i: Int, j: Int): Unit = {
    val t = a(i); a(i) = a(j); a(j) = t
  }

  println(kOrder(List(3, 8, 1, 12, 23), 4))
}