// похоже на функцию из-за ()
val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)

// при отсутствии выбросит исключение
val bobsScore = scores("Bob")
// или
// но вернет объект Option (см гл.14 )
// со занчением типа Some(значение), либо None
val alicesScore = scores.get("Alice")

// contains - проверка наличия ключа
val bobsScoreAgain = if (scores.contains("Bob")) scores("Bob") else 0

// или сокращенная форма записи
val cindysScore = scores.getOrElse("Bob", 0)
