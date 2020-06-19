import scala.collection.immutable

case class SubTest(str: Option[String])
case class TestRecord(x: String, opt1: Option[String], opt2: Option[String], subTest: Option[SubTest])
case class Status(errors: Seq[String])

val subTest = SubTest(Some("str"))
val testRecord = TestRecord("name", None, None, Some(subTest))

def check[T](value: Option[T]): Either[String, T] = {
    value.toRight("Fail")
  }

def validate(testRecord: TestRecord): Either[List[String], TestRecord] = {
  val fields = List(
    testRecord.opt1,
    testRecord.opt2,
    testRecord.subTest
  )

  val errors = fields.map(check).flatMap(_.left.toSeq)

  if (errors.isEmpty) Right(testRecord)
  else Left(errors)
}

validate(testRecord)


def check2[A]: PartialFunction[A, Either[String, _]] = {
  case opt: Option[_] => opt.toRight(s"The field is empty.")
  case b => Right(b)
}

def validate2(testRecord: TestRecord): Either[List[String], TestRecord] = {
  val fields = List(
    testRecord.x,
    testRecord.opt1,
    testRecord.opt2,
    testRecord.subTest
  )

  val test = fields.map(check2)
  test.foreach(println)


  val errors = fields.map(check2).flatMap(_.left.toSeq)

  if (errors.isEmpty) Right(testRecord)
  else Left(errors)
}
validate2(testRecord)