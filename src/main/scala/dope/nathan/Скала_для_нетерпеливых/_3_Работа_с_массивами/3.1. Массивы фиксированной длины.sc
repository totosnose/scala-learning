// Array - фиксированный массив; в JVM, как обычный массив
// 0...
val nums = new Array[Int](10)

// null...
val a = new Array[String](10)

// без new при наличие начальных значений
val s = Array("Hello", "World")
s(0) = "Goodbye"
