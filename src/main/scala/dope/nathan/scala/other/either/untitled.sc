case class SubTest(str: Option[String])
case class TestRecord(x: String, opt1: Option[String], opt2: Option[String], subTest: Option[SubTest])
case class Status(errors: Seq[String])

val subTest = SubTest(Some(""))
val testRecord = TestRecord("", None, None, Some(subTest))

def check[T](value: Option[T]): Either[String, T] = {
    value.toRight("Fail")
  }

def validate(testRecord: TestRecord): Either[List[String], TestRecord] = {
    val fields = List(
      testRecord.opt1,
      testRecord.opt2,
      testRecord.subTest
    )

    val errors = fields.map(check).filter(_.isLeft)

    if (errors.isEmpty) {
      Right(testRecord)
    } else {
      Left(for (Left(e) <- errors) yield e)
    }

  }

validate(testRecord)