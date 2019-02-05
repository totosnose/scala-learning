// lazy init - небесплатно, при каждом обращении
// к lazy значению, потокобезопасный метод проверяет
// было ли оно инициализировано

// (подходит для:
// дорогостоящих инициализаций +
// иниц. циклических зависимостей +
// ленивые структуры данных)

// читает все символы из файла в строку
// вычисляется немедленно, в момент определения word0
val world0 = scala.io.Source.fromFile(
  "/usr/share/dict/words").mkString

// lazy-значния между val и def
// вычислится при первом обращении к word1
// файл не откроется, пока программа не обратится к words
lazy val world1 = scala.io.Source.fromFile(
  "/usr/share/dict/words").mkString

// вычисляется каждый раз при обращении к words2
def world2 = scala.io.Source.fromFile(
  "/usr/share/dict/words").mkString

