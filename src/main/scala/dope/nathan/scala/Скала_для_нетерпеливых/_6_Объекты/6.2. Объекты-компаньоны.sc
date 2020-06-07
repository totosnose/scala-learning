// определение объекта-компаньона в том же файле с таким же названием, как класс;
// доступ к приватным полям друг друга;
// однако объект-компаньон вне области видимости класса,
// поэтому вызов методов выглядит так: Account.newUniqueNumber()

class Account {
  val id = Account.newUniqueNumber()
  private var balance = 0.0
  def deposit(amount: Double) { balance += amount }
}

object  Account {
  private var lastNumber = 0

  private def newUniqueNumber(): Int = {
    lastNumber += 1
    lastNumber
  }
}