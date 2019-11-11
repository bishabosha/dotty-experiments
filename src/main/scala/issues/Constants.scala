package issues

object Tac
  type ASrc = Ast.Symbolic | Constants.IntLiteral

object Constants
  type Constant = StringLiteral | IntLiteral

  opaque type StringLiteral = String
  opaque type IntLiteral    = Int

  def IntLiteral(i: Int): IntLiteral = i
  def StringLiteral(s: String): StringLiteral = s

  given (c: IntLiteral)
    def value: Int = c

  given (s: StringLiteral)
    def value: String = s

object Ast
  type Symbolic = Identifier | Temporary

  case class Identifier(name: String, scopeId: Long)
  class Temporary
