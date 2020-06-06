//abstract class DiffList[A](calculate: List[A] => List[A]) {
//  def prepend(s: List[A]): DiffList[A]
//
//  def append(s: List[A]): DiffList[A]
//
//  def result: List[A]
//}
//
//final class DiffListImpl[A](listFunc: List[A] => List[A]) extends DiffList[A](listFunc) {
//  def prepend(s: List[A]) = {
//    listFunc(s)
//    new DiffListImpl(listFunc(s) => s)
//  }

//  def append(s: List[A]) = new DiffListImpl(listFunc).append(s)

//  def result = ???
//}

//def f(arr: List[Int]): List[Int] = f(List(1,2,3))

List(1,2,3).foldRight(0) { (m: Int, n: Int) => m }