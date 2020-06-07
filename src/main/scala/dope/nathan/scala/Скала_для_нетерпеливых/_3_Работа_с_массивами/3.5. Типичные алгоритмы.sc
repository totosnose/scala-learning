import scala.collection.mutable.ArrayBuffer


// суммирование для целочисленных, вещественныхтипов или BigInteger, BigDecimal
Array(1, 7, 2, 9).sum

// sorted
val b = ArrayBuffer(1, 7, 2, 9)
val bSorted = b.sorted // новый буфер

// для min/max и quickSort типы элементов д. поддерживать операции сравнения
// т.е. числа, строки и др. типы с trait Ordered

// min/max
ArrayBuffer("Mary", "had", "a", "little", "lamb").max

// quickSort - сортировка массива на месте
val a = Array(1, 7, 2, 9)
scala.util.Sorting.quickSort(a) // новый массив

// разделители для вывода
a.mkString(" and ")
a.mkString("<", ",", ">")

// но toString бесполезен, только сообщает тип
a.toString
b.toString()