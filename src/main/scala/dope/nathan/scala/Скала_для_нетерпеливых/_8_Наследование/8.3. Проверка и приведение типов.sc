class Person
class HealthyPerson extends Person

val person = new Person
val healthyPerson = new HealthyPerson

// проверка
val b: Boolean = healthyPerson.isInstanceOf[Person]
// приведение
val p: Person = healthyPerson.asInstanceOf[Person]

// проверка именно класса, а не подкласса
val b2 = person.getClass == classOf[Person]
val b3 = person.getClass == classOf[HealthyPerson]

// лучше через match
p match {
  case person2: HealthyPerson => person2.getClass.getName
  case person1: Person => person1.getClass.getName
  case _ => "unknown type"
}