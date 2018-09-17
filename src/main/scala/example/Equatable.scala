package example

object Equatable {
  type Derive[A, O] = implicit Equatable[A] => O
  def equal[A]: (A, A) => Derive[A, Boolean] = implicitly[Equatable[A]].equal(_, _)
}

trait Equatable[A] {
  def equal(value1: A, value2: A): Boolean
}