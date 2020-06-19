class Person {
  val health = 0

  final var happiness = false

  final def checkHealth(): Unit =
    if (health < 100) {
      happiness = false
    } else {
      happiness = true
    }
}

final class HealthyPerson extends Person {
  override val health = 100

  //  override var happiness = false // невозможно

  checkHealth()
}

// class D extends Employee // невозможно

val e = new HealthyPerson
e.health
e.happiness