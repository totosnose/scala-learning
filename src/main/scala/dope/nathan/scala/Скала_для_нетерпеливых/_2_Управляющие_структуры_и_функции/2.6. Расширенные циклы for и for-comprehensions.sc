// несколько генераторов
for (i <- 1 to 3; j <- 1 to 3)
  print((10 * i + j) + " ")

// ограничитель (guard) - логическое условие,
// с предшевствующим ему ключевым словом if
for (i <- 1 to 3; j <- 1 to 3 if i != j)
  print((10 * i + j) + " ")

// любое количество определений,
// вводящих переменные для использования внутри цикла
for (i <- 1 to 3; from = 4 - i; j <- from to 3)
  print((10 *i + j) + " ")


// for-comprehension/ for-генератор
// инструкция yield для конструирования коллекции
for (i <- 1 to 10)
  yield i % 3

// генерируемые коллекции по типу
// совместимы с первым генератором
for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar
for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar

// {}
for { i <- 1 to 3
      from = 4 - 1
      j <- from to 3 }
  print(i + j)