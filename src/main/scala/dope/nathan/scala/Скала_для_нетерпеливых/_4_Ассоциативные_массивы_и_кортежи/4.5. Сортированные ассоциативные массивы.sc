// Ассоциативные массивы:
// + хеш-функции
// + сбалансированные деревья

// по-умолчанию:
// + хеш-таблицы

// в отсутствии хорошей хеш-функции для ключей или
// при необходимости хранения отсортированных ключей:
// + cбалансированные деревья

// неизменяемое дерево вместо хэш-таблицы
val scoresFinal = scala.collection.immutable.SortedMap(
  "Alice" -> 10, "Fred" -> 7, "Bob" -> 3, "Cindy" -> 8)
//scoresFinal -= "Alice" // невозможно

// изменяемое дерево (в версии 2.9. - отсутствует)
// до появления изменяемых деревьев использовалось
// TreeMap из Java (см. гл.13)
val scoresNotFinal = scala.collection.mutable.SortedMap(
  "Alice" -> 10, "Fred" -> 7, "Bob" -> 3, "Cindy" -> 8)
scoresNotFinal -= "Alice"

// LinkedHashMap - обход ключей в порядке их добавления
val months = scala.collection.mutable.LinkedHashMap(
  "January" -> 1,
  "February" -> 2,
  "March" -> 3
)