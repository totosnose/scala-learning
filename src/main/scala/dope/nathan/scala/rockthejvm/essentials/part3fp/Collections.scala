package dope.nathan.scala.rockthejvm.essentials.part3fp

object Collections {

  //                                             trait Traversable
  //                                                     |
  //                                               trait Iterable
  //                  /                                  |                                       \
  //                Set                                 Seq                                     Map
  //          (no duplicates)                                                                  (k, v)
  //                 |                          /                  \                             |
  //          HashSet, SortedSet          IndexedSeq              LinearSeq              HashMap, SortedMap
  //                                  (quickly accessed)      (some form ordering)
  //                                          |                        |
  //                                Vector, String, Range   List, Stream, Stack, Queue
  //                                   (const accessed)

  // trait Traversable:
  // maps:        map, flatMap, filter, collect
  // conversions: toArray, toList, toSeq
  // sizeInfo:    isEmpty, size, nonEmpty
  // tests:       exists, forall
  // folds:       foldLeft, foldRight, reduceLeft, reduceRight
  // retrieval:   head, find, tail

  // LinearSeq:
  // head, tail, isEmpty: O(1)
  // most operations are O(n): length, reverse

  // List:
  // is sealed
  // has subclasses (Nil and ::)

  // Vector:
  // effectively constant indexed read and write: O(log32(n))
  // fast element addition: append/prepend
  // implemented as a fixed-branched trie (branch factor 32)
  // good performance for large sizes (cash optima)

  // Writing. List vs Vector
  // List:
  // keeps reference to tail
  // updating an element in the middle takes long
  // Vector:
  // depth of rhe tree is small
  // needs to replace an entire 32-element

}
