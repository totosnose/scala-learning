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
