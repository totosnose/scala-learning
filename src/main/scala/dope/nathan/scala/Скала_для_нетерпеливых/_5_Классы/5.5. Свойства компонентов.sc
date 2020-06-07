import scala.beans.BeanProperty
//import scala.reflect.BeanProperty
class Person {
  @BeanProperty var name: String = _
}

// в результате сгенерируется 4 метода:
// 1. name: String
// 2. name_=(neValue: String): Unit
// 3. getName(): String
// 4. setName(newValue: String): Unit

// Если поле определяется, как Главный конструктов (см. 5.7)
// и для него нужно создать методы доступа в соответствии
// со спецификацией JavaBeans, то:
class Person1 (@BeanProperty var name: String)

//(см. табл. 5.1 "Методы, автоматически генерируемые для полей")