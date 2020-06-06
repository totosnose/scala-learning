// допустимо вкладывать всё, что угодно, во всё, что угодно
// (функции в другие функции, классы в другие классы)
import scala.collection.mutable.ArrayBuffer

class Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

//каждый экземпляр получит свой собственный класс Member
// и собственное поле members,
// т.е. chatter.Member и myFace.Member - разные классы
val chatter = new Network
val myFace = new Network

// в Java иначе: внутренний класс принадлежит внешнему классу

// чтобы создать новый внутренний объект,
// достаточно new с именем типа: new chatter.Member.

val fred = chatter.join("Fred")
val wilma = chatter.join("Wilma")
fred.contacts += wilma              // OK
val barney = myFace.join("Barney")  // имеет тип myFace.Member
// fred.contacts += barney          // невозможно, т.к.
// класс myFace.Member не совместим с классом chatter.Member

// обходы вышеприведенной проблемы:
// 1. Определение Member в другом месте (companion object)
object Network1 {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }
}
class Network1 {
  private val members = new ArrayBuffer[Network1.Member]

  def join(name: String): Network1.Member = {
    val m = new Network1.Member(name)
    members += m
    m
  }
}

// 2. Использование проекции типов (type projection) Network#Member (член любой группы) (см гл.18)
class Network2 {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Network#Member]
  }
}

// во вложенном классе доступна ссылка this, указывающая на внешний класс.
// можно определить псевдоним для этой ссылки
class Network3(val name: String) { outer =>
  class Member(val name: String) {
    def description = s"$name inside ${outer.name}"
  }

  def display: String = new Member("in").description
}

val net = new Network3("out")
net.display
