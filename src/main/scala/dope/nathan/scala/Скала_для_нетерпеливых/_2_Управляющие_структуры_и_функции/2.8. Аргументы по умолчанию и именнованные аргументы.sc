// default
def decorate(str: String, left: String = "[", right: String = "]") =
  left + str + right

// вызов без default-аргументов
decorate("Hello")
// вызов с переопределенными default-аргументами
decorate("Hello", "<<", ">>")
// вызов, если число аргументов меньше числа параметров -
// применяются default-аргументы с конца
decorate("Hello", ">>[")

// при передаче аргументов можно писать имена параметров
// что гарантирует порядок
decorate(left = "<<<", str = "Hello", right = ">>>")

// смешивание именованных и неименованных аргументов
// сначала неименованные
decorate("Hello", right = "]<<<")