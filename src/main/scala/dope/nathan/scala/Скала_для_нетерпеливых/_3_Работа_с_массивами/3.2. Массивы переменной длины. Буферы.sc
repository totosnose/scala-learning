// ArrayBuffer - ArrayList в Java
import scala.collection.mutable.ArrayBuffer

// пустой буфер, подготовленный для хранения целых чисел
val b = ArrayBuffer[Int]()
// или
val b0 = new ArrayBuffer[Int]

// добавление элемента в конец
b += 1
b += (1, 2, 3, 5)

// добавление коллекции в конец
b ++= Array(8, 13, 21)

// удаление последних 5 элементов
b.trimEnd(5)

// вставка в позицию c индексом 2
b.insert(2, 6)
// любое количество
b.insert(2, 7, 8, 9)

// удаление по индексу
b.remove(2)

// удаление по индексу и количество удаляемых элементов
b.remove(2, 3)

// создание массива с заранее неизвестным количеством элементов
// через создание ArrayBuffer
val a = b.toArray

// обратное преобразование массива в ArrayBuffer
a.toBuffer

