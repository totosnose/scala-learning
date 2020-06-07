abstract class UndoableAction(val description: String) {
  def undo(): Unit
  def redo(): Unit
}

object DoNothingAction extends UndoableAction("Do nothing") {
  override def undo(): Unit = Unit

  override def redo(): Unit = Unit
}

val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)