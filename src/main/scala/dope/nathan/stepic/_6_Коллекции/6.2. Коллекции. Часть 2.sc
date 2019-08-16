val list = List(1, 2, 3, 2)

val vector = Vector(1, 2, 3, 2)

val stream = Stream(1, 2, 3, 2)

val set = Set(1, 2, 3, 2)

val map = Map("Москва" -> 12e6, "Питер" -> 5e6)


// для последовательностей (list, vector, stream)
val phrase1 = Vector("+")

// добавить справа (только для Vector добавить в конец (справа +:) будет достаточно эффективным)
val phrase2 = phrase1 :+ "Stepik"

// добавить слева
val phrase3 = "Scala" +: phrase2

val phrase4 = Stream.empty[String]

val phrase5 = phrase4 :+ "=" :+ "Love"

// конкатенация 2х коллекция - "++"

val phrase = phrase3 ++ phrase5

phrase.mkString

// неупорядоченные коллекции (map, set)
// добавить - "+"

val cities1 = Map("Питер" -> 5e6, ("Москва" -> 12e6))

val cities2 = cities1 + ("Саратов" -> 1e6)

val cities3 = List("Волгоград" -> 1e6)

val cities = cities2 ++ cities3

// apply у последовательностей
// можно передать индекс элемента (в качестве аргумента) в переменную,
// в которой хранится последовательность
phrase(1)

// apply у ассоциативных массивов
// можно передать ключ
cities("Саратов")

//todo part 2 this step