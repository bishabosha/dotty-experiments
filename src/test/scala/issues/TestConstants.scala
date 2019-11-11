package issues

import org.junit.{ Test => test }
import org.junit.Assert._

import Constants._
import Tac._
import Ast._

class TestConstants {
  @test def matchIntLiteral: Unit = IntLiteral(9) match
    case i: IntLiteral => assertEquals(i.value, 9)

  @test def matchConstant: Unit = (IntLiteral(9): Constant) match
    case i: IntLiteral    => assertEquals(i.value, 9)
    case i: StringLiteral => ???

  @test def matchASrc: Unit = (IntLiteral(9): ASrc) match
    case i: IntLiteral    => assertEquals(i.value, 9)
    case _: Identifier    => ???
    case _: Temporary     => ???
}
