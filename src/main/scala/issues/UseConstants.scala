package issues

import Constants._
import Tac._
import Ast._

class TestConstants {
  def matchIntLiteral: Unit = IntLiteral(9) match
    case i: IntLiteral => ???

  def matchConstant: Unit = (IntLiteral(9): Constant) match
    case i: IntLiteral    => ???
    case i: StringLiteral => ???

  def matchASrc: Unit = (IntLiteral(9): ASrc) match
    case i: IntLiteral    => ???
    case _: Identifier    => ???
    case _: Temporary     => ???
}
