abstract class Writer {
  def write(msq: String)
}

class StringWriter extends Writer {
  val target = new StringBuilder

  override def write(msg: String) =
    target.append(msg)

  override def toString() =
    target.toString()
}

trait UpperCaseFilter extends Writer {
  abstract override def write(msg: String) = {
    super.write(msg.toUpperCase())
  }
}

trait ProfanityFilter extends Writer {
  abstract override def write(msg: String) = {
    super.write(msg.replace("stupid", "s*****"))
  }
}

def writeStuff(writer: Writer) = {
  writer.write("This is stupid")
  println(writer)
}

writeStuff(new StringWriter)
writeStuff(new StringWriter with UpperCaseFilter)
writeStuff(new StringWriter with ProfanityFilter)
// it's work but next example has a problem
// (заменил и увеличил замененное)
writeStuff(new StringWriter with UpperCaseFilter
  with ProfanityFilter)
// the problem
// (увеличил, но при этом значение уже не соответствует для замены)
writeStuff(new StringWriter with ProfanityFilter
  with UpperCaseFilter)


