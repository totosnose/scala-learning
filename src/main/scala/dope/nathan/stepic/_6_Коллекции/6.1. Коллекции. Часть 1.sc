// неизменяемые
// import scala.collection.immutable._
// List[+A], Vector[+A], Stream[+A], Set[+A], Map[K, +V]

// изменяемые
// import scala.collection.mutable._
// Buffer[A], Set[A], Map[A, B], Builder[-E, +C]

// надтипы
// import scala.collection._
// Seq[+A], Set[+A], Map[K, +V], Iterator[+A]

// специализированные
// Array[A], String


// Array
// - эффективный (+),
// - низкоуровневый (-),
// - фиксированный размер
// - имеет специализированные версии для примитивных типов (эффективность)

val ints = Array(1, 2, 3)

ints(2)

ints(2) = 6

ints(2)

//ints(3) = 6 // error


// String
// - массив символов
// - неизменяемый
// - любое изменение выделяет новыую строку

val language = "Scala"
val platform = "Stepik"

val course = language + " " + platform  // неэффективно (при + выделение новых строк)
val cource1 = s"$language $platform"  // интерполяция - эффективно!

val char: Char = course(3)


// Изменяемые коллекции

// - состояние может меняться со временем
// - эффективны для большого количества операций подряд (к отл. от неизм-х)
// - Копирование неэффективно (нужно выделить новое место и скопировать все элементы)

// Buffer[A] - саморастущий массив (добавление элементов с конца)
// Set[A] - набор уникальных элементов
// Map[K, V] - ассоциативный массив "ключ-значение"
// Builder[E, Coll] - спец. промежуточный накопитель для построения коллекций

import scala.collection.mutable

val string = mutable.Buffer[String]()

string += "scala"
string += "+"
string += "stepik"
string += "="
string += "love"

string.mkString(" ")


// Неизменяемые коллекции

// - наиболее удобны
// - состояние неизменно
// - достаточно эффективное создание копий при изменении (создавая новую коллекцию, происходит копирование,
//   но не всегда с выделением пространства для всех элементов, т.к. неизм-е коллекции могут переиспользовать
//   пространство исходных коллекций)
// - т.к. неизменяемые, то Hashable -> можно использовать в качестве ключа в Map или храниться в Set
// - коварианты ?

// List[A] - Односвязный оконечный список
// - легко добавить элемент в начало (при этом имеющийся список станет "хвостом" нового списка)

// Stream[A] - Ленивый связный список, потенциально бесконечный
// - "хвост" вычисляется лениво

// Vector[A] - Индексированный список (неизменяемая версия массива)
// - легко получить элемент по индексу, добавить элемент в начало и конец

// Set[A] - Набор уникальных элементов

// Map[K, V] - Ассоциативный массив "ключ-значение"

import scala.collection.immutable._
val  initial = Vector[String]("stepik")
val mid = "scala" +: "+" +: initial
val strings = mid :+ "=" :+ "love"

strings.mkString(" ")


// -----------------------Практика -----------------------------------------------------------------

import scala.util.Random

val list = List(2, 5, 7, 7, 4)

list.sorted
list.dropWhile(_ != 1)

// список с рандомными элементами
/*List.fill(количество элементов)(вычисляемые элементы)*/
val randomList = List.fill(Random.nextInt(10000))(Random.nextInt(1000))

// сортировка слиянием (для односвязных списков - ок)
// as & bs - заранее отсортированные списки
@scala.annotation.tailrec
def merge(as: List[Int], bs: List[Int], acc: List[Int] = Nil): List[Int] = {
  // проверяем не пустой ли первый список
  as match {
    // переворачиваем список, т.к. меньшие эл-ты помещали в начало,
    // и добавляем оставшиеся эл-ты списка bs в качестве хвоста
    case List() => acc.reverse ++ bs
    case a +: restA => bs match { // :: подходит только для списков, +: - для всех сиквесов
      case List() => acc.reverse ++ as
      case b +: restB =>
        if (a < b) merge(restA, bs, a :: acc)
        else       merge(as, restB, b :: acc)
    }
  }
}

merge(List(2, 5, 6), List(1, 4, 9))

// непосредственно алгоритм сортировки
def mergeSort(as: List[Int]): List[Int] = as match {
  case Nil | _ :: Nil => as // если коллекция пустая или состоит из одного элемента
  case _ =>
    val (left, right) = as.splitAt(as.length / 2) // разрежет список на две части


    val leftSorted = mergeSort(left)
    val rightSorted = mergeSort(right)
    println(leftSorted)
    println(rightSorted)
    merge(leftSorted, rightSorted)
}

mergeSort(list) == list.sorted
mergeSort(randomList) == randomList.sorted
