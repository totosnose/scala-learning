// процедуры - функции, "не возвращающие" значения
// {} без "=" - возвращает тип Unit
def box(s: String) {
  val border = "-" * s.length + "--\n"
  println(border + "|" + s + "|\n" + border)
}

// или
def box0(s: String): Unit = {

}

box("Hello")

