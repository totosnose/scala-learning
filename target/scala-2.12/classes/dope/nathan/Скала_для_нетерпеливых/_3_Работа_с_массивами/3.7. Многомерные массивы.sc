// Array[Array[Double]] массивы массивов
// ofDim - создание
val matrix = Array.ofDim[Double](3, 4) // 3 строки, 4 столбца

// matrix(row)(column) = 42
matrix(1)(1) = 0

// строки разной длины
val triangle = new Array[Array[Int]](10)
for (i <- 0 until triangle.length)
  triangle(i) = new Array[Int](i + 1)
