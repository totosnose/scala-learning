package dope.nathan.scala.rockthejvm.essentials.part2oop

// Scala 3
object Enums {

//  // sealed by default
//  enum Permissions {
//    case READ, WRITE, EXECUTE, NONE
//
//    // add  fields/methods
//    def openDoc(): Unit = {
//      if (this == READ) println("opening doc")
//      else println("reading not allowed")
//    }
//  }
//
//  val somePermissions: Permissions = Permissions.READ
//
//  enum PermissionsWithBits(bits: Int) {
//    case READ extends PermissionsWithBits(4) // 100
//    case WRITE extends PermissionsWithBits(2) // 010
//    case EXECUTE extends PermissionsWithBits(1) // 001
//    case NONE extends PermissionsWithBits(0) // 000
//  }
//
//  object PermissionsWithBits {
//    def fromBits(bits: Int): PermissionsWithBits = // whatever
//      PermissionsWithBits.NONE
//  }
//
//  // standard API
//  val somePermissionsOrdinal = somePermissions.ordinal
//  val allPermissions = PermissionsWithBits.values
//  def readPermission: Permissions = Permissions.valueOf("READ")
//
//  def main(args: Array[String]): Unit = {
//    somePermissions.openDoc()
//    println(somePermissionsOrdinal)
//  }

}
