def senderId(id: Long): Either[Long, String] = {

  Right(id.toString)
//  Left(id)
}
val s = senderId(11)


var s2: Either[Int, String] = Left(11)
s2 = Right("11")

s2 match {
  case Left(x) if x == 11 => println(s"-------------------> LSender: senderId = $x .")
  case Right(x) if x == "11" => println(s"-------------------> RSender: senderId = $x .")
  case _ ⇒ println(s"-------------------> Sender hasn't senderId: Either[SourceId, String] = ??? .")
}
