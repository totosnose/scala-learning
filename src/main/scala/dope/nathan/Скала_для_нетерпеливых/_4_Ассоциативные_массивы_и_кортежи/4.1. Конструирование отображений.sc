// Ассоциативные массивы - Maps
// + по-умолчанию создаются в виде хеш-таблиц,
// но есть возможность создавать их в виде деревьев
// + бывают неизменяемые и изменяемые
// + можно передавать между Scala и Java

// Кортеж - агрегат из n объектов, необязательно одного типа
// Пара - обычный кортеж с n = 2
// + удобно для агрегирования значений

// создание ассоциативного массива
// неизменяемое отображение Map[String, Int]
val scoresFinal1 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
val scoresFinal2 = Map(("Alice", 10), ("Bob", 3), "Cindy" -> 8)

// изменяемое отображение Map[String, Int]
val scoresNotFinal = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)

// пустой ассоциативный массив
val scoresEmpty = scala.collection.mutable.HashMap[String, Int]




