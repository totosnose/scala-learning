// упаковка значений в кортежи для совместной обработки
val symbol = Array("<", "-", ">")
val counts = Array(2, 10, 2)
// массив пар
val pairs = symbol.zip(counts)

// совместная обработка пар
for ((s, n) <- pairs) Console.print(s * n)

// toMap - коллекция пар => ассоциативный массив
symbol.zip(counts).toMap