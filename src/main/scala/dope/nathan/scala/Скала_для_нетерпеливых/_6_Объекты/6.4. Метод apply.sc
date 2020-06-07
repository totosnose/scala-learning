// apply в объектах
// метод apply у Array позволяет создавать массив так:
Array("0", "1", "2")
Array(Array(1, 7), Array(2, 9))

// разные вещи
Array(100)     // Array[Int](100)
new Array(100) // Array[Nothin](...)

// пример использования apply
class Account private (val id: Int, initialBalance: Double) {
  private val balance = initialBalance
}

object Account {
  private var number = 0

  private def newUniqueNumber() = {
    number += 1
    number
  }

  def apply(initialBalance: Double): Account = {
    new Account(newUniqueNumber(), initialBalance)
  }
}

val account = Account(1000.0)