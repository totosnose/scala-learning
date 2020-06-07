// singleton
// lazy constructor without params
object  Accounts {
  private var lastNumber = 0

  def newUniqueNumber(): Int = {
    lastNumber += 1
    lastNumber
  }
}
