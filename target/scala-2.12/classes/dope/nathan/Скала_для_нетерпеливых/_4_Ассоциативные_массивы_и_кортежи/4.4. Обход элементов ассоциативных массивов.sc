import scala.sys.process

// обход через for ((k, v) <- map) process k and v
// позволяют использовать Pattern Matching
// (получать пару без лишних вызовов методов) (см. гл.14)
val map = Map("A" -> 0, "B" -> 1, "C" -> 2)

// получить Set
map.keySet

// перебрать значения
for (v <- map.values) println(v)

// инвертированный ассоциативный массив
val map1 = for ((k, v) <- map) yield (v, k)


